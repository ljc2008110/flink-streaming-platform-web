/*
 * Copyright(c)2002-2022, 北京意锐新创科技有限公司
 * 项目名称: flink-streaming-platform-web
 * Date: 2022/1/5
 * Author: linjinchun(kevin)
 */
package com.flink.streaming.web.service.impl;

import com.flink.streaming.web.enums.DeployModeEnum;
import com.flink.streaming.web.enums.JobConfigStatus;
import com.flink.streaming.web.model.dto.JobConfigDTO;
import com.flink.streaming.web.rpc.FlinkRestRpcAdapter;
import com.flink.streaming.web.rpc.model.JobStandaloneInfo;
import com.flink.streaming.web.service.JobConfigService;
import com.flink.streaming.web.service.JobStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @create 2022/1/5 14:17
 * @since v1.0.0
 */
@Component
@Slf4j
public class JobStatusServiceImpl implements JobStatusService {

    private final JobConfigService jobConfigService;

    private final FlinkRestRpcAdapter flinkRestRpcAdapter;

    public JobStatusServiceImpl(JobConfigService jobConfigService, FlinkRestRpcAdapter flinkRestRpcAdapter) {
        this.jobConfigService = jobConfigService;
        this.flinkRestRpcAdapter = flinkRestRpcAdapter;
    }

    /**
     * 同步配置开启的任务状态
     */
    @Override
    public void syncJobStatus() {
        // 配置开启且为以下状态的需要检测job的状态
        List<JobConfigDTO> jobConfigDTOList = jobConfigService.findCheckStatusJobs();
        if (CollectionUtils.isEmpty(jobConfigDTOList)) {
            return;
        }
        List<JobStandaloneInfo> jobStandaloneInfos = flinkRestRpcAdapter.getJobStatusListForStandlone(DeployModeEnum.STANDALONE);
        Map<String, String> jobStatusMap = jobStandaloneInfos.stream().collect(Collectors.toMap(JobStandaloneInfo::getJid, JobStandaloneInfo::getState));
        jobConfigDTOList.stream().forEach(jobConfigDTO -> {
            /*
             * 如果flink不存在该任务，则直接置为STOP状态及后续操作。
             * 1. flink返回job为非STOP状态：
             *   1.a) 如果状态相同，直接返回；
             *   1.b) 如果状态不同，则进行修改；
             * 2. flink返回job状态为STOP状态：
             *   2.a) 更新状态为STOP，并做报警及任务重新拉起操作（需要根据auto_restore状态进行恢复）。
             */
            String flinkJobStatus = jobStatusMap.get(jobConfigDTO.getJobId());
            if (Objects.isNull(flinkJobStatus)) {
                flinkJobStatus = "CANCELED";
            }
            if (!jobConfigDTO.equalFlinkJobStatus(flinkJobStatus)) {
                //变更任务状态
                log.info("发现本地任务状态和Cluster上不一致，开始更新任务状态 flinkjobstatus：{}", flinkJobStatus);
                JobConfigDTO jobConfig = JobConfigDTO.buildConfig(jobConfigDTO.getId(), flinkJobStatus);
                jobConfigService.updateJobConfigById(jobConfig);
            }
        });
    }

    /**
     * 获取所有状态异常的任务
     */
    @Override
    public void checkUnExpectedJobStatus() {

    }
}
