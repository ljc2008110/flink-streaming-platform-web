package com.flink.streaming.web.rpc.impl;

import com.flink.streaming.web.base.TestRun;
import com.flink.streaming.web.enums.DeployModeEnum;
import com.flink.streaming.web.rpc.FlinkRestRpcAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @create 2021/11/29 19:13
 * @since v1.0.0
 */
@Slf4j
public class FlinkRestRpcAdapterImplTest extends TestRun {

    @Autowired
    FlinkRestRpcAdapter flinkRestRpcAdapter;

    @Test
    public void getJobStatusListForStandlone() {
        log.info("{}", flinkRestRpcAdapter.getJobStatusListForStandlone(DeployModeEnum.STANDALONE));
    }

    @Test
    public void getJobInfoForStandaloneByAppId() {
        log.info("{}", flinkRestRpcAdapter.getJobInfoForStandaloneByAppId("e3f35539f0ac210a67b435d81c1bdf93", DeployModeEnum.STANDALONE));
    }
}