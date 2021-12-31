package com.flink.streaming.web.common.util;

import com.flink.streaming.web.base.TestRun;
import com.flink.streaming.web.common.SystemConstants;
import com.flink.streaming.web.enums.SysConfigEnum;
import com.flink.streaming.web.enums.SysConfigEnumType;
import com.flink.streaming.web.model.dto.JobConfigDTO;
import com.flink.streaming.web.model.dto.JobRunParamDTO;
import com.flink.streaming.web.model.dto.SystemConfigDTO;
import com.flink.streaming.web.service.JobConfigService;
import com.flink.streaming.web.service.SystemConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @create 2021/12/24 16:01
 * @since v1.0.0
 */
public class CommandUtilTest extends TestRun {

    @Autowired
    private SystemConfigService systemConfigService;

    @Test
    public void buildCancelCommandForCluster() throws Exception {
        String jobId = "6fbf5f1abd49915b9a98a41e6493209e";
        String jmPath = "10.10.11.134:8082";
        String savepointPath = "/opt/data/flink/savepoints/";
        Map<String, String> systemConfigMap = SystemConfigDTO.toMap(systemConfigService.getSystemConfig(SysConfigEnumType.SYS));
        String flinkBinPath = SystemConstants.buildFlinkBin(systemConfigMap.get(SysConfigEnum.FLINK_HOME.getKey()));

        CommandUtil.buildCancelCommandForCluster(jobId, jmPath,
                flinkBinPath, savepointPath);
    }
}