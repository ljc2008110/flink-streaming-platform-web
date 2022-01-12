package com.flink.streaming.web.ao.impl;

import com.alibaba.fastjson.JSON;
import com.flink.streaming.web.alarm.DingDingAlarm;
import com.flink.streaming.web.alarm.HttpAlarm;
import com.flink.streaming.web.ao.AlarmServiceAO;
import com.flink.streaming.web.enums.AlarmLogStatusEnum;
import com.flink.streaming.web.enums.AlarmLogTypeEnum;
import com.flink.streaming.web.enums.SysConfigEnum;
import com.flink.streaming.web.model.dto.AlarmLogDTO;
import com.flink.streaming.web.model.dto.JobRunLogDTO;
import com.flink.streaming.web.model.vo.CallbackDTO;
import com.flink.streaming.web.service.AlarmLogService;
import com.flink.streaming.web.service.JobRunLogService;
import com.flink.streaming.web.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-25
 * @time 19:50
 */
@Component
@Slf4j
public class AlarmServiceAOImpl implements AlarmServiceAO {

    @Autowired
    private AlarmLogService alarmLogService;
    @Autowired
    private JobRunLogService jobRunLogService;
    @Autowired
    private DingDingAlarm dingDingAlarm;
    @Autowired
    private HttpAlarm httpAlarm;
    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 钉钉告警
     * @author Kevin.Lin
     * @date 2022-1-6 16:21:17
     */
    @Override
    public void justDingdingAlarm(final String content, List<Long> jobConfigIdList) {
        final String alarmUrl = systemConfigService.getSystemConfigByKey(SysConfigEnum.DINGDING_ALARM_URL.getKey());
        if (StringUtils.isEmpty(alarmUrl)) {
            log.warn("#####钉钉告警url没有设置，无法告警#####");
            return;
        }
        boolean isSuccess = false;
        String failLog = "";
        try {
            isSuccess = dingDingAlarm.send(alarmUrl, content);
        } catch(Exception e) {
            log.error("dingDingAlarm.send is error", e);
            failLog = e.getMessage();
        }
        if (Objects.isNull(jobConfigIdList) || jobConfigIdList.isEmpty()) {
            return;
        }
        final boolean finalIsSuccess = isSuccess;
        final String finalFailLog = failLog;
        jobConfigIdList.stream().forEach(jobConfigId -> {
            this.insertLog(finalIsSuccess, jobConfigId, finalFailLog, content, AlarmLogTypeEnum.DINGDING);
        });
    }

    @Override
    public boolean sendForDingding(String url, String content, Long jobConfigId) {

        boolean isSuccess = false;
        String failLog = "";
        try {
            isSuccess = dingDingAlarm.send(url, content);
        } catch (Exception e) {
            log.error("dingDingAlarm.send is error", e);
            failLog = e.getMessage();
        }
        this.insertLog(isSuccess, jobConfigId, failLog, content, AlarmLogTypeEnum.DINGDING);

        return isSuccess;
    }

    @Override
    public boolean sendForHttp(String url, CallbackDTO callbackDTO) {

        boolean isSuccess = false;
        String failLog = "";
        try {
            isSuccess = httpAlarm.send(url, callbackDTO);
        } catch (Exception e) {
            log.error("dingDingAlarm.send is error", e);
            failLog = e.getMessage();
        }
        this.insertLog(isSuccess, callbackDTO.getJobConfigId(), failLog, JSON.toJSONString(callbackDTO),
                AlarmLogTypeEnum.CALLBACK_URL);

        return isSuccess;
    }


    private void insertLog(boolean isSuccess, Long jobConfigId, String failLog, String content,
                           AlarmLogTypeEnum alarMLogTypeEnum) {
        JobRunLogDTO jobRunLogDTO = jobRunLogService.getDetailLogById(jobConfigId);
        AlarmLogDTO alarmLogDTO = new AlarmLogDTO();
        alarmLogDTO.setJobConfigId(jobConfigId);
        alarmLogDTO.setAlarMLogTypeEnum(alarMLogTypeEnum);
        alarmLogDTO.setMessage(content);
        if (jobRunLogDTO != null) {
            alarmLogDTO.setJobName(jobRunLogDTO.getJobName());
        } else {
            alarmLogDTO.setJobName("测试");
        }
        if (isSuccess) {
            alarmLogDTO.setAlarmLogStatusEnum(AlarmLogStatusEnum.SUCCESS);
        } else {
            alarmLogDTO.setAlarmLogStatusEnum(AlarmLogStatusEnum.FAIL);
            alarmLogDTO.setFailLog(failLog);
        }
        alarmLogService.addAlarmLog(alarmLogDTO);

    }
}
