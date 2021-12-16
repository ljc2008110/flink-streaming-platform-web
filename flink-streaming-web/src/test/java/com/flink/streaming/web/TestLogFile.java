/*
 * Copyright(c)2002-2021, 北京意锐新创科技有限公司
 * 项目名称: flink-streaming-platform-web
 * Date: 2021/11/30
 * Author: linjinchun(kevin)
 */
package com.flink.streaming.web;

import com.flink.streaming.web.base.TestRun;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.Objects;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @create 2021/11/30 14:40
 * @since v1.0.0
 */
@Slf4j
public class TestLogFile extends TestRun {

    @Test
    public void testLogFile() {
        String hostName = "linjinchun";
        String flinkDir = "E:\\subsys\\ubuntu\\rootfs\\opt\\flink\\";
        String logPath = flinkDir + "log/";
        File logDir = new File(logPath);
        if (logDir.exists()) {
            File[] files = logDir.listFiles(pathname -> {return pathname.getName().contains("client");});
            File logFile = null;
            for (File file : files) {
                if (Objects.isNull(logFile)) {
                    logFile = file;
                } else if (file.getName().contains(hostName) && !logFile.getName().contains(hostName)) {
                    logFile = file;
                } else if (file.getName().contains(hostName) && logFile.getName().contains(hostName)
                        && file.lastModified() > logFile.lastModified()) {
                    logFile = file;
                }
            }

            log.info("filname: {}, {}", logPath, logFile.getAbsolutePath());
        }
    }
}
