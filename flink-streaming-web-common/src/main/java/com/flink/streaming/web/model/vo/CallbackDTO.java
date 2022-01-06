package com.flink.streaming.web.model.vo;

import com.flink.streaming.web.model.dto.JobConfigDTO;
import lombok.Data;

/**
 * @author zhuhuipei
 * @Description
 * @date 2021/2/21
 * @time 22:20
 */
@Data
public class CallbackDTO {

    private String appId;

    private String jobName;

    private String deployMode;

    private Long jobConfigId;

    private Integer autoRestore;

    public static CallbackDTO to(JobConfigDTO jobConfigDTO) {
        CallbackDTO callbackDTO = new CallbackDTO();
        callbackDTO.setJobConfigId(jobConfigDTO.getId());
        callbackDTO.setJobName(jobConfigDTO.getJobName());
        callbackDTO.setDeployMode(jobConfigDTO.getDeployModeEnum().name());
        callbackDTO.setAppId(jobConfigDTO.getJobId());
        callbackDTO.setAutoRestore(jobConfigDTO.getAutoRestore());
        return callbackDTO;
    }
}
