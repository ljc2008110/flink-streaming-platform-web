package com.flink.streaming.web.model.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.flink.streaming.web.enums.AlarmLogStatusEnum;
import com.flink.streaming.web.enums.AlarmLogTypeEnum;
import com.flink.streaming.web.model.entity.AlarmLog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
@Data
public class AlarmLogDTO {

    private Long id;

    private Long jobConfigId;


    private String jobName;


    /**
     * 消息内容
     */
    private String message;

    /**
     * 1:钉钉
     */
    private AlarmLogTypeEnum alarMLogTypeEnum;

    /**
     * 1:成功 0:失败
     */
    private AlarmLogStatusEnum alarmLogStatusEnum;


    /**
     * 失败原因
     */
    private String failLog;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date editTime;

    private String creator;

    private String editor;


    public static AlarmLog toEntity(AlarmLogDTO alarmLogDTO) {
        if (alarmLogDTO == null) {
            return null;
        }
        AlarmLog alarmLog = new AlarmLog();
        alarmLog.setId(alarmLogDTO.getId());
        alarmLog.setJobConfigId(alarmLogDTO.getJobConfigId());
        alarmLog.setMessage(alarmLogDTO.getMessage());
        alarmLog.setType(alarmLogDTO.getAlarMLogTypeEnum().getCode());
        alarmLog.setStatus(alarmLogDTO.getAlarmLogStatusEnum().getCode());
        alarmLog.setFailLog(alarmLogDTO.getFailLog());
        alarmLog.setCreateTime(alarmLogDTO.getCreateTime());
        alarmLog.setEditTime(alarmLogDTO.getEditTime());
        alarmLog.setCreator(alarmLogDTO.getCreator());
        alarmLog.setEditor(alarmLogDTO.getEditor());
        alarmLog.setJobName(alarmLogDTO.getJobName());
        return alarmLog;
    }

    public static AlarmLogDTO toDTO(AlarmLog alarmLog) {
        if (alarmLog == null) {
            return null;
        }
        AlarmLogDTO alarmLogDTO = new AlarmLogDTO();
        alarmLogDTO.setId(alarmLog.getId());
        alarmLogDTO.setJobConfigId(alarmLog.getJobConfigId());
        alarmLogDTO.setMessage(alarmLog.getMessage());
        alarmLogDTO.setAlarMLogTypeEnum(AlarmLogTypeEnum.getAlarmLogTypeEnum(alarmLog.getType()));
        alarmLogDTO.setAlarmLogStatusEnum(AlarmLogStatusEnum.getAlarmLogStatusEnum(alarmLog.getStatus()));
        alarmLogDTO.setFailLog(alarmLog.getFailLog());
        alarmLogDTO.setCreateTime(alarmLog.getCreateTime());
        alarmLogDTO.setEditTime(alarmLog.getEditTime());
        alarmLogDTO.setCreator(alarmLog.getCreator());
        alarmLogDTO.setEditor(alarmLog.getEditor());
        alarmLogDTO.setJobName(alarmLog.getJobName());
        return alarmLogDTO;
    }

    public static List<AlarmLogDTO> toListDTO(List<AlarmLog> alarmLogList) {
        if (CollectionUtil.isEmpty(alarmLogList)) {
            return Collections.emptyList();
        }
        List<AlarmLogDTO> list = new ArrayList<>();
        for (AlarmLog alarmLog : alarmLogList) {
            AlarmLogDTO alarmLogDTO = AlarmLogDTO.toDTO(alarmLog);
            if (alarmLog != null) {
                list.add(alarmLogDTO);
            }
        }
        return list;
    }


}
