package com.flink.streaming.web.rpc.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flink.streaming.web.common.FlinkYarnRestUriConstants;
import com.flink.streaming.web.common.util.HttpUtil;
import com.flink.streaming.web.enums.DeployModeEnum;
import com.flink.streaming.web.enums.SysErrorEnum;
import com.flink.streaming.web.exceptions.BizException;
import com.flink.streaming.web.rpc.FlinkRestRpcAdapter;
import com.flink.streaming.web.rpc.model.JobStandaloneInfo;
import com.flink.streaming.web.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-18
 * @time 23:43
 */
@Service
@Slf4j
public class FlinkRestRpcAdapterImpl implements FlinkRestRpcAdapter {

    @Autowired
    private SystemConfigService systemConfigService;

    @Override
    public JobStandaloneInfo getJobInfoForStandaloneByAppId(String appId, DeployModeEnum deployModeEnum) {
        if (StringUtils.isEmpty(appId)) {
            throw new BizException(SysErrorEnum.HTTP_REQUEST_IS_NULL);
        }
        String res = null;
        JobStandaloneInfo jobStandaloneInfo = null;
        try {
            String url = HttpUtil.buildUrl(systemConfigService.getFlinkHttpAddress(deployModeEnum),
                    FlinkYarnRestUriConstants.getUriJobsForStandalone(appId));

            log.info("[getJobInfoForStandaloneByAppId]请求参数 appId={} url={}", appId, url);
            res = HttpUtil.buildRestTemplate(HttpUtil.TIME_OUT_1_M).getForObject(url, String.class);
            log.debug("[getJobInfoForStandaloneByAppId]请求参数结果: res={}", res);
            if (StringUtils.isEmpty(res)) {
                return null;
            } else {
                // 避免日志过长，截取关注的信息info打印
                int startIdx = res.indexOf("\"isStoppable");
                log.info("[getJobInfoForStandaloneByAppId]请求参数结果: res={}", res.substring(startIdx, startIdx + 128));
            }
            jobStandaloneInfo = JSON.parseObject(res, JobStandaloneInfo.class);
            return jobStandaloneInfo;
        } catch (HttpClientErrorException e) {
            jobStandaloneInfo = new JobStandaloneInfo();
            jobStandaloneInfo.setErrors(e.getMessage());
            log.error("json 异常 res={}", res, e);
        } catch (Exception e) {
            log.error("json 异常 res={}", res, e);
        }

        return jobStandaloneInfo;
    }

    /**
     * 获取所有任务状态
     *
     * @return 返回所有job状态列表
     */
    @Override
    public List<JobStandaloneInfo> getJobStatusListForStandlone(DeployModeEnum deployModeEnum) {
        List<JobStandaloneInfo> jobStatusList = new ArrayList<>();
        String res = null;
        try {
            String url = HttpUtil.buildUrl(systemConfigService.getFlinkHttpAddress(deployModeEnum),
                    FlinkYarnRestUriConstants.getUriForJobList());

            log.info("[getJobStatusListForStandlone]请求参数 url={}", url);
            res = HttpUtil.buildRestTemplate(HttpUtil.TIME_OUT_1_M).getForObject(url, String.class);
            log.debug("[getJobInfoForStandaloneByAppId]请求参数结果: res={}", res);
            if (StringUtils.isEmpty(res)) {
                return null;
            } else {
                // 避免日志过长，截取关注的信息info打印
                int startIdx = res.indexOf("\"isStoppable");
                log.info("[getJobStatusListForStandlone]请求参数结果: res={}", res);
                res = JSON.parseObject(res).getString("jobs");
            }
            jobStatusList = JSON.parseArray(res, JobStandaloneInfo.class);
            return jobStatusList;
        } catch (Exception e) {
            log.error("json 异常 res={}", res, e);
        }

        return jobStatusList;
    }

    @Override
    public void cancelJobForFlinkByAppId(String jobId, DeployModeEnum deployModeEnum) {
        if (StringUtils.isEmpty(jobId)) {
            throw new BizException(SysErrorEnum.PARAM_IS_NULL);
        }
        String url = HttpUtil.buildUrl(systemConfigService.getFlinkHttpAddress(deployModeEnum),
                FlinkYarnRestUriConstants.getUriCancelForStandalone(jobId));

        log.info("[cancelJobForFlinkByAppId]请求参数 jobId={} url={}", jobId, url);
        String res = HttpUtil.buildRestTemplate(HttpUtil.TIME_OUT_1_M).getForObject(url, String.class);
        log.info("[cancelJobForFlinkByAppId]请求参数结果: res={}", res);
    }

    @Override
    public String savepointPath(String jobId, DeployModeEnum deployModeEnum) {
        if (StringUtils.isEmpty(jobId)) {
            throw new BizException(SysErrorEnum.PARAM_IS_NULL);
        }
        try {
            Thread.sleep(HttpUtil.TIME_OUT_3_S);
            String url = HttpUtil.buildUrl(systemConfigService.getFlinkHttpAddress(deployModeEnum),
                    FlinkYarnRestUriConstants.getUriCheckpoints(jobId));
            log.info("[savepointPath]请求参数 jobId={} url={}", jobId, url);
            String res = HttpUtil.buildRestTemplate(HttpUtil.TIME_OUT_1_M).getForObject(url, String.class);
            if (StringUtils.isEmpty(res)) {
                return null;
            }
            String savepointPath = JSON.parseObject(res).getJSONObject("latest").
                    getJSONObject("savepoint").getString("external_path");
            if (savepointPath.startsWith("file:") && !savepointPath.startsWith("file://")) {
                savepointPath = savepointPath.replace("file:/", "file:///");
            }
            return savepointPath;
        } catch (Exception e) {
            log.error("savepointPath is error", e);
        }
        return null;


    }

    /**
     * 获取系统配置的savepoint路径
     *
     * @param deployModeEnum
     * @return
     */
    @Override
    public String getSysSavepointPath(DeployModeEnum deployModeEnum) {
        try {
            Thread.sleep(HttpUtil.TIME_OUT_3_S);
            String url = HttpUtil.buildUrl(systemConfigService.getFlinkHttpAddress(deployModeEnum),
                    FlinkYarnRestUriConstants.URI_SYS_SAVEPOINTS_PATH);
            log.info("[savepointPath]请求参数 url={}", url);
            String res = HttpUtil.buildRestTemplate(HttpUtil.TIME_OUT_1_M).getForObject(url, String.class);
            if (StringUtils.isEmpty(res)) {
                return null;
            }
            AtomicReference<String> savepointPath = new AtomicReference<String>();
            JSON.parseArray(res).stream().forEach(json -> {
                if (((JSONObject)json).getString("key").equals("state.savepoints.dir")) {
                    savepointPath.set(((JSONObject) json).getString("value"));
                    return;
                };
            });
            if (Objects.nonNull(savepointPath)) {
                return savepointPath.get();
            }
        } catch (Exception e) {
            log.error("getSysSavepointPath is error", e);
        }
        return null;
    }


}
