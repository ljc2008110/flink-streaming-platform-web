package com.flink.streaming.web.service.impl;

import com.flink.streaming.web.base.TestRun;
import com.flink.streaming.web.service.JobConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @create 2022/1/5 18:50
 * @since v1.0.0
 */
@Slf4j
public class JobConfigServiceImplTest extends TestRun {

    @Autowired
    private JobConfigService jobConfigService;

    @Test
    public void findByJobId() {
        log.info("{}", jobConfigService.findByJobId("9518e173d6342e8b6260ea8ed45bf69f"));
    }
}