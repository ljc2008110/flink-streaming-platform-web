package com.flink.streaming.web.model.vo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.flink.streaming.web.enums.AlarmLogStatusEnum;
import com.flink.streaming.web.model.dto.AlarmLogDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-25
 * @time 23:34
 */
@Data
public class AlarmLogVO {

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
    private String typeDesc;

    /**
     * 1:成功 0:失败
     */
    private String statusDesc;

    private Integer status;


    /**
     * 失败原因
     */
    private String failLog;


    /**
     * 创建时间
     */
    private String createTime;


    public static AlarmLogVO toVO(AlarmLogDTO alarmLogDTO) {
        if (alarmLogDTO == null) {
            return null;
        }
        AlarmLogVO alarmLogVO = new AlarmLogVO();
        alarmLogVO.setId(alarmLogDTO.getId());
        alarmLogVO.setJobConfigId(alarmLogDTO.getJobConfigId());
        alarmLogVO.setJobName(alarmLogDTO.getJobName());
        alarmLogVO.setMessage(alarmLogDTO.getMessage());
        alarmLogVO.setStatus(alarmLogDTO.getAlarmLogStatusEnum().getCode());
        if (alarmLogDTO.getAlarMLogTypeEnum() != null) {
            alarmLogVO.setTypeDesc(alarmLogDTO.getAlarMLogTypeEnum().getDesc());
        }
        if (alarmLogDTO.getAlarmLogStatusEnum() != null) {
            if (AlarmLogStatusEnum.SUCCESS.equals(alarmLogDTO.getAlarmLogStatusEnum())) {
                alarmLogVO.setStatusDesc(alarmLogDTO.getAlarmLogStatusEnum().getDesc());
            } else {
                alarmLogVO.setStatusDesc("<span style=\"color: red\">" + alarmLogDTO.getAlarmLogStatusEnum().getDesc() + "</span>");
            }

        }
        alarmLogVO.setFailLog(alarmLogDTO.getFailLog());
        alarmLogVO.setCreateTime(DateUtil.format(alarmLogDTO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));

        return alarmLogVO;
    }


    public static List<AlarmLogVO> toListVO(List<AlarmLogDTO> alarmLogDTOList) {
        if (CollectionUtil.isEmpty(alarmLogDTOList)) {
            return Collections.emptyList();
        }

        List<AlarmLogVO> list = new ArrayList<>();
        for (AlarmLogDTO alarmLogDTO : alarmLogDTOList) {
            AlarmLogVO alarmLogVO = AlarmLogVO.toVO(alarmLogDTO);
            if (alarmLogVO != null) {
                list.add(alarmLogVO);
            }
        }

        return list;

    }

}
