package com.flink.streaming.web.controller.api;

import com.flink.streaming.web.common.util.IpUtil;
import com.flink.streaming.web.common.util.LinuxInfoUtil;
import com.flink.streaming.web.enums.SysConfigEnum;
import com.flink.streaming.web.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

/**
 * @author zhuhuipei
 * @Description
 * @date 2021/5/5
 * @time 10:23
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class FlinkLogApiController {

    @Autowired
    public SystemConfigService systemConfigService;

    @RequestMapping(value = "/getFlinkLocalJobLog")
    public String  getFlinkLocalJobLog(HttpServletResponse response){
        try {
            String logPath = getLogFileName();
            log.info("日志文件地址 logPath={}",logPath);
            File file = new File(logPath);
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("text/plain; charset=utf-8");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream(),2048);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
            log.error("[getFlinkLocalJobLog is error]",ex);
            return ex.getMessage();
        }
        return "ok";
    }

    private String getLogFileName() {

        String hostName = IpUtil.getHostName();
        if (hostName.contains(".")) {
            hostName = hostName.substring(0, hostName.indexOf("."));
        }
        String flinkDir = systemConfigService.getSystemConfigByKey(SysConfigEnum.FLINK_HOME.getKey());
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
            return Objects.isNull(logFile) ? "logfile_is_not_found" : logFile.getAbsolutePath();
        }

        return "flink_dir_not_config";
    }
}
