package com.flink.streaming.web.controller.api;

import com.flink.streaming.web.ao.TaskServiceAO;
import com.flink.streaming.web.common.RestResult;
import com.flink.streaming.web.exceptions.BizException;
import com.flink.streaming.web.enums.SysErrorEnum;
import com.flink.streaming.web.model.dto.JobConfigDTO;
import com.flink.streaming.web.service.JobConfigService;
import com.flink.streaming.web.service.SavepointBackupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-21
 * @time 01:52
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class SavepointApiController {

    @Autowired
    private SavepointBackupService savepointBackupService;
    @Autowired
    private TaskServiceAO taskServiceAO;
    @Autowired
    private JobConfigService jobConfigService;

    @RequestMapping(value = "/addSavepoint")
    public RestResult<String> addSavepoint(Long jobConfigId, String savepointPath) {
        try {
            savepointBackupService.insertSavepoint(jobConfigId, savepointPath, new Date());
        } catch (BizException e) {
            log.error("addSavepoint is error jobConfigId={},savepointPath={}", jobConfigId, savepointPath, e);
            return RestResult.error(e.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("addSavepoint  error jobConfigId={},savepointPath={}", jobConfigId, savepointPath, e);
            return RestResult.error(SysErrorEnum.ADD_SAVEPOINT_ERROR);
        }
        return RestResult.success();
    }

    @RequestMapping(value = "/autoAddSavepoint", method = RequestMethod.POST)
    public RestResult<String> addSavepoint(String jobId, String savepointPath) {
        try {
            JobConfigDTO jobConfigDTO = jobConfigService.findByJobId(jobId);
            if (Objects.nonNull(jobConfigDTO)) {
                savepointBackupService.insertSavepoint(jobConfigDTO.getId(), savepointPath, new Date());
            } else {
                log.warn("jobid: {} is not found! ", jobId);
                return RestResult.error(SysErrorEnum.ADD_SAVEPOINT_ERROR.getCode(), "jobid " + jobId + " is not found!");
            }
        } catch (BizException e) {
            log.error("addSavepoint is error jobConfigId={}, savepointPath={}", jobId, savepointPath, e);
            return RestResult.error(e.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("addSavepoint  error jobConfigId={}, savepointPath={}", jobId, savepointPath, e);
            return RestResult.error(SysErrorEnum.ADD_SAVEPOINT_ERROR);
        }
        return RestResult.success();
    }

    @RequestMapping(value = "/onekeyBackup")
    public RestResult<String> allSavepoint() {
        try {
            taskServiceAO.autoSavePoint();
        } catch (BizException e) {
            log.error("allSavepoint is error.", e);
            return RestResult.error(e.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("allSavepoint is error.", e);
            return RestResult.error(SysErrorEnum.ALL_SAVEPOINT_ERROR);
        }
        return RestResult.success();
    }

    @RequestMapping(value = "/onekeyRestore")
    public RestResult<String> allRestoreFromSavepoint() {
        try {
            savepointBackupService.restoreSavepoint();
        } catch (BizException e) {
            log.error("restoreSavepoint is error.", e);
            return RestResult.error(e.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("restoreSavepoint is error.", e);
            return RestResult.error(SysErrorEnum.ALL_RESTORE_ERROR);
        }
        return RestResult.success();
    }
}
