package com.flink.streaming.web.ao.impl;

import com.flink.streaming.common.enums.JobTypeEnum;
import com.flink.streaming.web.alarm.DingDingAlarm;
import com.flink.streaming.web.ao.AlarmServiceAO;
import com.flink.streaming.web.ao.JobServerAO;
import com.flink.streaming.web.ao.TaskServiceAO;
import com.flink.streaming.web.common.FlinkJobStatus;
import com.flink.streaming.web.common.SystemConstants;
import com.flink.streaming.web.common.util.YarnUtil;
import com.flink.streaming.web.config.AlarmPoolConfig;
import com.flink.streaming.web.config.SavePointThreadPool;
import com.flink.streaming.web.enums.*;
import com.flink.streaming.web.exceptions.BizException;
import com.flink.streaming.web.model.dto.JobConfigDTO;
import com.flink.streaming.web.model.vo.CallbackDTO;
import com.flink.streaming.web.rpc.FlinkRestRpcAdapter;
import com.flink.streaming.web.rpc.YarnRestRpcAdapter;
import com.flink.streaming.web.rpc.model.JobInfo;
import com.flink.streaming.web.rpc.model.JobStandaloneInfo;
import com.flink.streaming.web.service.JobAlarmConfigService;
import com.flink.streaming.web.service.JobConfigService;
import com.flink.streaming.web.service.SavepointBackupService;
import com.flink.streaming.web.service.SystemConfigService;
import com.flink.streaming.web.thread.AlarmDingdingThread;
import com.flink.streaming.web.thread.AlarmHttpThread;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-22
 * @time 19:59
 */
@Component
@Slf4j
public class TaskServiceAOImpl implements TaskServiceAO {

    @Autowired
    private JobConfigService jobConfigService;

    @Autowired
    private FlinkRestRpcAdapter flinkRestRpcAdapter;

    @Autowired
    private YarnRestRpcAdapter yarnRestRpcAdapter;

    @Autowired
    private AlarmServiceAO alarmServiceAO;

    @Autowired
    private JobServerAO jobYarnServerAO;

    @Autowired
    private JobServerAO jobStandaloneServerAO;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private JobAlarmConfigService jobAlarmConfigService;

    @Autowired
    private SavepointBackupService savepointBackupService;

    private ThreadPoolExecutor threadPoolExecutor = AlarmPoolConfig.getInstance().getThreadPoolExecutor();

    @Override
    public void checkJobStatus() {
        // 配置开启且为以下状态的需要检测job的状态
        List<JobConfigDTO> jobConfigDTOList = jobConfigService.findCheckStatusJobs();
        if (CollectionUtils.isEmpty(jobConfigDTOList)) {
            log.warn("配置已开启中没有正在运行的任务");
            return;
        }

        for (JobConfigDTO jobConfigDTO : jobConfigDTOList) {
            if (JobTypeEnum.SQL_BATCH.equals(jobConfigDTO.getJobTypeEnum())){
                log.warn("批任务不需要状态校验");
                continue;
            }
            List<AlarmTypeEnum> alarmTypeEnumList = jobAlarmConfigService.findByJobId(jobConfigDTO.getId());
            switch (jobConfigDTO.getDeployModeEnum()) {
                case YARN_PER:
                    this.checkYarn(jobConfigDTO, alarmTypeEnumList);
                    break;
                case LOCAL:
                case STANDALONE:
                    this.checkStandalone(jobConfigDTO, alarmTypeEnumList);
                    break;
                default:
                    break;
            }
            this.sleep();
        }

    }

    private List<JobConfigDTO> unexceptedJobList = new ArrayList<>();
    /**
     * 检查非正常任务并恢复通知
     *
     * @author Kevin.Lin
     * @date 2022-1-6 11:43:29
     */
    @Override
    public void checkUnexceptedJob() {
        if (this.unexceptedJobList.isEmpty()) {
            return;
        }
        List<JobConfigDTO> jobConfigDTOList = new ArrayList<>();
        Collections.copy(jobConfigDTOList, unexceptedJobList);
        checkStandaloneJobStatus(jobConfigDTOList);
    }

    /**
     * 检查job任务状态
     *
     * @author Kevin.Lin
     * @date 2022-1-6 11:49:38
     */
    @Override
    public void checkStandaloneJobStatus() {
        // 配置开启且为以下状态的需要检测job的状态
        List<JobConfigDTO> jobConfigDTOList = jobConfigService.findCheckStatusJobs();
        if (CollectionUtils.isEmpty(jobConfigDTOList)) {
            log.warn("配置已开启中没有正在运行的任务");
            return;
        }
        checkStandaloneJobStatus(jobConfigDTOList);
    }

    private void checkStandaloneJobStatus(List<JobConfigDTO> jobConfigDTOList) {
        List<String> toRestoreJobList = new ArrayList<>();
        List<String> toUnexceptedJobList = new ArrayList<>();
        List<String> restoreFail = new ArrayList<>();
        jobConfigDTOList.stream().filter(jobConfigDTO -> !JobTypeEnum.SQL_BATCH.equals(jobConfigDTO.getJobTypeEnum()))
                .forEach(jobConfigDTO -> {
                    JobStandaloneInfo jobStandaloneInfo = flinkRestRpcAdapter.getJobInfoForStandaloneByAppId(
                            jobConfigDTO.getJobId(), DeployModeEnum.STANDALONE);
                    String flinkStatus = Objects.isNull(jobStandaloneInfo) ? "UNKNOWN" : jobStandaloneInfo.getState();
                    if (!jobConfigDTO.equalFlinkJobStatus(flinkStatus)) {
                        jobConfigDTO.setFlinkJobStatus(flinkStatus);
                        if (JobConfigStatus.RUN.equals(jobConfigDTO.getStatus())) {
                            toRestoreJobList.add(jobConfigDTO.getJobName());
                        } else {
                            toUnexceptedJobList.add(jobConfigDTO.getJobName() + ": " + flinkStatus);
                            this.unexceptedJobList.add(jobConfigDTO);
                        }
                    }
                    if (JobConfigStatus.FAIL.equals(jobConfigDTO.getStatus())
                            || JobConfigStatus.UNKNOWN.equals(jobConfigDTO.getStatus())) {
                        try {
                            this.autoRestoreJobStandalone(CallbackDTO.to(jobConfigDTO), SystemConstants.USER_NAME_TASK_AUTO);
                            jobStandaloneInfo = flinkRestRpcAdapter.getJobInfoForStandaloneByAppId(
                                    jobConfigDTO.getJobId(), DeployModeEnum.STANDALONE);
                            this.unexceptedJobList.remove(jobConfigDTO);
                            jobConfigDTO.setFlinkJobStatus(jobStandaloneInfo.getState());
                            toRestoreJobList.add(jobConfigDTO.getJobName());
                        } catch (Exception e) {
                            log.warn("restore job jobid: {}, jobname: {}, fail. {}", jobConfigDTO.getJobId(), jobConfigDTO.getJobName(), e);
                            restoreFail.add(jobConfigDTO.getJobName());
                        }
                    }
                });
        StringBuilder alarmMsg = new StringBuilder("Flink任务状态检测通知，");
        boolean hasMsg = false;
        if (!toRestoreJobList.isEmpty()) {
            alarmMsg.append("已恢复任务：").append(toRestoreJobList.stream().collect(Collectors.joining(", ")).toString());
            hasMsg = true;
        }
        if (!toUnexceptedJobList.isEmpty()) {
            alarmMsg.append("状态异常任务：").append(toUnexceptedJobList.stream().collect(Collectors.joining(", ")).toString());
            hasMsg = true;
        }
        if (!restoreFail.isEmpty()) {
            alarmMsg.append("恢复失败任务：").append(restoreFail.stream().collect(Collectors.joining(", ")).toString());
            hasMsg = true;
        }
        if (hasMsg) {
            this.justDingdingAlarm(alarmMsg.toString());
        }
    }

    @Autowired
    private DingDingAlarm dingDingAlarm;
    /**
     * 钉钉告警
     * @author Kevin.Lin
     * @date 2022-1-6 16:21:17
     */
    private void justDingdingAlarm(final String content) {
        final String alartUrl = systemConfigService.getSystemConfigByKey(SysConfigEnum.DINGDING_ALARM_URL.getKey());
        if (StringUtils.isEmpty(alartUrl)) {
            log.warn("#####钉钉告警url没有设置，无法告警#####");
            return;
        }
        try {
            dingDingAlarm.send(alartUrl, content);
        } catch(Exception e) {
            log.error("dingDingAlarm.send is error", e);
        }
    }

    @Override
    @Deprecated
    public void checkYarnJobByStop() {
        List<JobConfigDTO> jobConfigDTOList = jobConfigService.findJobConfigByStatus(JobConfigStatus.STOP.getCode());
        if (CollectionUtils.isEmpty(jobConfigDTOList)) {
            return;
        }
        for (JobConfigDTO jobConfigDTO : jobConfigDTOList) {
            if (jobConfigDTO.getIsOpen().intValue() == YN.N.getValue()) {
                continue;
            }
            if (JobTypeEnum.SQL_BATCH.equals(jobConfigDTO.getJobTypeEnum())){
                log.warn("批任务不需要状态校验");
                return;
            }
            switch (jobConfigDTO.getDeployModeEnum()) {
                case YARN_PER:
                    String appId = null;
                    try {
                        String queueName = YarnUtil.getQueueName(jobConfigDTO.getFlinkRunConfig());
                        if (StringUtils.isEmpty(queueName)) {
                            continue;
                        }
                        log.info("check job getJobName={} queueName={}", jobConfigDTO.getJobName(), queueName);
                        appId = yarnRestRpcAdapter.getAppIdByYarn(jobConfigDTO.getJobName(), queueName);
                    } catch (BizException be) {
                        if (SysErrorEnum.YARN_CODE.getCode().equals(be.getCode())) {
                            continue;
                        }
                        log.error("[BizException]getAppIdByYarn  is error ", be);
                    } catch (Exception e) {
                        log.error("[Exception]getAppIdByYarn is error ", e);
                        continue;
                    }
                    if (!StringUtils.isEmpty(appId)) {
                        JobInfo jobInfo = yarnRestRpcAdapter.getJobInfoForPerYarnByAppId(appId);
                        if (jobInfo != null && SystemConstants.STATUS_RUNNING.equals(jobInfo.getStatus())) {
                            log.warn("执行停止操作 jobYarnInfo={} id={}", jobInfo, appId);
                            yarnRestRpcAdapter.cancelJobForYarnByAppId(appId, jobInfo.getId());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void autoSavePoint() {
        List<JobConfigDTO> jobConfigDTOList = jobConfigService.findJobConfigByStatusAndOpen(JobConfigStatus.RUN.getCode());
        // 过滤job已经启用autoRestore
        jobConfigDTOList = jobConfigDTOList.stream().filter(job -> job.getAutoRestore() == 1).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(jobConfigDTOList)) {
            log.warn("autoSavePoint is empty. 没有找到已开启auto_restore且运行中的任务。");
            return;
        }
        Integer cnt = jobConfigDTOList.size();  
        AtomicInteger finishedCnt = new AtomicInteger(0);
        AtomicInteger failedCnt = new AtomicInteger(0);
        for (JobConfigDTO jobConfigDTO : jobConfigDTOList) {

            //sql、jar 流任务才执行SavePoint
            if (JobTypeEnum.SQL_STREAMING.equals(jobConfigDTO.getJobTypeEnum())
                    || JobTypeEnum.JAR.equals(jobConfigDTO.getJobTypeEnum())) {
                SavePoint thread = new SavePoint(jobConfigDTO);
                SavePointThreadPool.getInstance().getThreadPoolExecutor().execute(thread);
                if (!waitForResult(thread)) {
                    failedCnt.getAndAdd(1);
                }
                finishedCnt.getAndAdd(1);
                log.info("process（all / finished / failed）: {} / {} / {}", cnt, finishedCnt.get(), failedCnt.get());
            }
        }
    }

    private Boolean waitForResult(SavePoint thread) {
        while (Objects.isNull(thread.getResult())) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // do nothing.
            }
        }
        return thread.result;
    }

    /**
     * 执行SavePoint
     */
    @Data
    class SavePoint implements Runnable {

        private JobConfigDTO jobConfigDTO;

        private Boolean result;

        public SavePoint(JobConfigDTO jobConfigDTO) {
            this.jobConfigDTO = jobConfigDTO;
        }

        @Override
        public void run() {
            try {
                switch (jobConfigDTO.getDeployModeEnum()) {
                    case YARN_PER:
                        jobYarnServerAO.savepoint(jobConfigDTO.getId());
                        break;
                    case LOCAL:
                    case STANDALONE:
                        jobStandaloneServerAO.savepoint(jobConfigDTO.getId());
                        break;
                }
                this.result = true;
                log.info("jobid:{}, savepoint 执行成功.", jobConfigDTO.getJobId());
            } catch (Exception e) {
                log.error("任务：{}，执行savepoint 异常 {}", jobConfigDTO.getJobId(), e);
                this.result = false;
            } finally {
                if (Objects.isNull(result)) {
                    this.result = false;
                }
            }
        }
    }


    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }


    private void checkYarn(JobConfigDTO jobConfigDTO, List<AlarmTypeEnum> alarmTypeEnumList) {
        if (StringUtils.isEmpty(jobConfigDTO.getJobId())) {
            log.error("任务配置不存在");
            return;
        }
        //查询任务状态
        JobInfo jobInfo = yarnRestRpcAdapter.getJobInfoForPerYarnByAppId(jobConfigDTO.getJobId());

        if (jobInfo != null && SystemConstants.STATUS_RUNNING.equals(jobInfo.getStatus())) {
            return;
        }

        //变更任务状态
        log.error("发现本地任务状态和yarn上不一致,准备自动修复本地web任务状态  {}", jobConfigDTO);
        JobConfigDTO jobConfig = JobConfigDTO.buildStop(jobConfigDTO.getId());
        jobConfigService.updateJobConfigById(jobConfig);

        //发送告警并且自动拉起任务
        this.alarmAndAutoJob(alarmTypeEnumList,
                SystemConstants.buildDingdingMessage(" 检测到任务停止运行 任务名称：" +
                        jobConfigDTO.getJobName()), jobConfigDTO, DeployModeEnum.YARN_PER);


    }


    private void checkStandalone(JobConfigDTO jobConfigDTO, List<AlarmTypeEnum> alarmTypeEnumList) {
        if (StringUtils.isEmpty(jobConfigDTO.getJobId())) {
            String message = SystemConstants.buildDingdingMessage(" 检测到任务jobId异常 任务名称："
                    + jobConfigDTO.getJobName());
            log.error(message);
            return;
        }
        //查询任务状态
        JobStandaloneInfo jobStandaloneInfo = flinkRestRpcAdapter.getJobInfoForStandaloneByAppId(
                jobConfigDTO.getJobId(), jobConfigDTO.getDeployModeEnum());

        /*
         * 如果flink不存在该任务，则直接置为STOP状态及后续操作。
         * 1. flink返回job为非STOP状态：
         *   1.a) 如果状态相同，直接返回；
         *   1.b) 如果状态不同，则进行修改；
         * 2. flink返回job状态为STOP状态：
         *   2.a) 更新状态为STOP，并做报警及任务重新拉起操作（需要根据auto_restore状态进行恢复）。
         */
        String flinkJobStatus = null;
        if (jobStandaloneInfo == null) {
            flinkJobStatus = "CANCELED";
        } else {
            flinkJobStatus = jobStandaloneInfo.getState();
        }

        if (!jobConfigDTO.equalFlinkJobStatus(flinkJobStatus)) {
            //变更任务状态
            log.info("发现本地任务状态和Cluster上不一致，准备自动更新任务状态 jobStandaloneInfo={}", jobStandaloneInfo);
            JobConfigDTO jobConfig = JobConfigDTO.buildConfig(jobConfigDTO.getId(), flinkJobStatus);
            jobConfigService.updateJobConfigById(jobConfig);
            // 任务恢复为RUNNING状态，钉钉任务恢复
            if (JobConfigStatus.RUN.equals(jobConfig.getStatus())) {
                String content = new StringBuilder(" 检测到任务状态已恢复：").append(flinkJobStatus).append("，任务名称：")
                        .append(jobConfigDTO.getJobName()).toString();
                this.dingdingAlarm(content, jobConfigDTO.getId());
            }
        }

        if (jobConfigDTO.getStatus().willAlarm()) {
            String content = new StringBuilder(" 检测到任务状态异常：").append(flinkJobStatus).append("，任务名称：")
                    .append(jobConfigDTO.getJobName()).toString();
            //发送告警并且自动拉起任务
            this.alarmAndAutoJob(alarmTypeEnumList,
                    SystemConstants.buildDingdingMessage(content),
                    jobConfigDTO, DeployModeEnum.STANDALONE);
        }
    }


    /**
     * 告警并且拉起任务，
     * //TODO 如果拉起失败下次将不能拉起
     *
     * @author zhuhuipei
     * @date 2021/2/28
     * @time 19:50
     */
    private void alarmAndAutoJob(List<AlarmTypeEnum> alarmTypeEnumList, String cusContent,
                                 JobConfigDTO jobConfigDTO, DeployModeEnum deployModeEnum) {


        if (CollectionUtils.isEmpty(alarmTypeEnumList)) {
            log.warn("没有配置告警，无法进行告警，并且任务将会被停止！！！");
            return;
        }

        CallbackDTO callbackDTO = CallbackDTO.to(jobConfigDTO);
        if (CollectionUtils.isEmpty(alarmTypeEnumList)) {
            return;
        }
        //告警
        for (AlarmTypeEnum alarmTypeEnum : alarmTypeEnumList) {
            switch (alarmTypeEnum) {
                case DINGDING:
                    this.dingdingAlarm(cusContent, callbackDTO.getJobConfigId());
                    break;
                case CALLBACK_URL:
                    this.httpAlarm(callbackDTO);
                    break;
            }
        }
        //自动拉起
        if (alarmTypeEnumList.contains(AlarmTypeEnum.AUTO_START_JOB)
                && jobConfigDTO.getStatus().willRestart()) {
            log.info("校验任务不存在或者已经失败/退出，将自动拉起 JobConfigId={}", callbackDTO.getJobConfigId());
            try {
                switch (deployModeEnum) {
                    case YARN_PER:
                        jobYarnServerAO.start(callbackDTO.getJobConfigId(), null,
                                SystemConstants.USER_NAME_TASK_AUTO);
                        break;
                    case STANDALONE:
                        autoRestoreJobStandalone(callbackDTO, SystemConstants.USER_NAME_TASK_AUTO);
                        break;
                }

            } catch (Exception e) {
                log.error("自动重启任务失败 JobConfigId={}", callbackDTO.getJobConfigId(), e);
            }

        }

    }

    private boolean autoRestoreJobStandalone(CallbackDTO callbackDTO, String userNameTaskAuto) {
        // 校验任务不存在或者已经失败/退出，将自动拉起；否则等待任务进入终态后再进行恢复
        JobStandaloneInfo jobInfo = flinkRestRpcAdapter.getJobInfoForStandaloneByAppId(callbackDTO.getAppId(), DeployModeEnum.STANDALONE);
        if (Objects.isNull(jobInfo) || FlinkJobStatus.FAILED.getStatus().equals(jobInfo.getState())
                || FlinkJobStatus.CANCELED.getStatus().equals(jobInfo.getState())) {
            // 不自动恢复，直接重新启动
            if (Integer.valueOf(0).equals(callbackDTO.getAutoRestore())) {
                jobStandaloneServerAO.start(callbackDTO.getJobConfigId(), null,
                        SystemConstants.USER_NAME_TASK_AUTO);
            } else { // 自动恢复，需找到备份并恢复

                jobStandaloneServerAO.start(callbackDTO.getJobConfigId(), savepointBackupService.getLatestSavepointId(callbackDTO.getJobConfigId()),
                        SystemConstants.USER_NAME_TASK_AUTO);
                log.info("任务jobid：{}，名称为：{} 已恢复成功。", callbackDTO.getAppId(), callbackDTO.getJobName());
            }
            return true;
        } else {
            log.warn("任务jobid：{}，名称为：{}， 状态为：{} 非终态，不进行自动拉起。",
                    callbackDTO.getAppId(), callbackDTO.getJobName(),
                    Objects.isNull(jobInfo) ? "未知" : jobInfo.getState());
            return false;
        }
    }

    /**
     * 钉钉告警
     *
     * @author zhuhuipei
     * @date 2021/2/28
     * @time 19:56
     */
    private void dingdingAlarm(String content, Long jobConfigId) {
        String alartUrl = systemConfigService.getSystemConfigByKey(SysConfigEnum.DINGDING_ALARM_URL.getKey());
        if (StringUtils.isEmpty(alartUrl)) {
            log.warn("#####钉钉告警url没有设置，无法告警#####");
            return;
        }
        threadPoolExecutor.execute(new AlarmDingdingThread(alarmServiceAO, content, jobConfigId, alartUrl));
    }

    /**
     * 回调函数自定义告警
     *
     * @author zhuhuipei
     * @date 2021/2/28
     * @time 19:56
     */
    private void httpAlarm(CallbackDTO callbackDTO) {
        String alartUrl = systemConfigService.getSystemConfigByKey(SysConfigEnum.CALLBACK_ALARM_URL.getKey());
        if (StringUtils.isEmpty(alartUrl)) {
            log.warn("#####回调告警url没有设置，无法告警#####");
            return;
        }
        threadPoolExecutor.execute(new AlarmHttpThread(alarmServiceAO, callbackDTO, alartUrl));
    }

}
