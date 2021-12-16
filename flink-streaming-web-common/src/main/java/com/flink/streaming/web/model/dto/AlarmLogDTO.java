package com.flink.streaming.web.model.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.flink.streaming.web.enums.AlarmLogStatusEnum;
import com.flink.streaming.web.enums.AlarmLogTypeEnum;
import com.flink.streaming.web.model.entity.AlartLog;
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


    public static AlartLog toEntity(AlarmLogDTO alarmLogDTO) {
        if (alarmLogDTO == null) {
            return null;
        }
        AlartLog alartLog = new AlartLog();
        alartLog.setId(alarmLogDTO.getId());
        alartLog.setJobConfigId(alarmLogDTO.getJobConfigId());
        alartLog.setMessage(alarmLogDTO.getMessage());
        alartLog.setType(alarmLogDTO.getAlarMLogTypeEnum().getCode());
        alartLog.setStatus(alarmLogDTO.getAlarmLogStatusEnum().getCode());
        alartLog.setFailLog(alarmLogDTO.getFailLog());
        alartLog.setCreateTime(alarmLogDTO.getCreateTime());
        alartLog.setEditTime(alarmLogDTO.getEditTime());
        alartLog.setCreator(alarmLogDTO.getCreator());
        alartLog.setEditor(alarmLogDTO.getEditor());
        alartLog.setJobName(alarmLogDTO.getJobName());
        return alartLog;
    }

    public static AlarmLogDTO toDTO(AlartLog alartLog) {
        if (alartLog == null) {
            return null;
        }
        AlarmLogDTO alarmLogDTO = new AlarmLogDTO();
        alarmLogDTO.setId(alartLog.getId());
        alarmLogDTO.setJobConfigId(alartLog.getJobConfigId());
        alarmLogDTO.setMessage(alartLog.getMessage());
        alarmLogDTO.setAlarMLogTypeEnum(AlarmLogTypeEnum.getAlarmLogTypeEnum(alartLog.getType()));
        alarmLogDTO.setAlarmLogStatusEnum(AlarmLogStatusEnum.getAlarmLogStatusEnum(alartLog.getStatus()));
        alarmLogDTO.setFailLog(alartLog.getFailLog());
        alarmLogDTO.setCreateTime(alartLog.getCreateTime());
        alarmLogDTO.setEditTime(alartLog.getEditTime());
        alarmLogDTO.setCreator(alartLog.getCreator());
        alarmLogDTO.setEditor(alartLog.getEditor());
        alarmLogDTO.setJobName(alartLog.getJobName());
        return alarmLogDTO;
    }

    public static List<AlarmLogDTO> toListDTO(List<AlartLog> alartLogList) {
        if (CollectionUtil.isEmpty(alartLogList)) {
            return Collections.emptyList();
        }
        List<AlarmLogDTO> list = new ArrayList<>();
        for (AlartLog alartLog : alartLogList) {
            AlarmLogDTO alarmLogDTO = AlarmLogDTO.toDTO(alartLog);
            if (alartLog != null) {
                list.add(alarmLogDTO);
            }
        }
        return list;
    }


}
