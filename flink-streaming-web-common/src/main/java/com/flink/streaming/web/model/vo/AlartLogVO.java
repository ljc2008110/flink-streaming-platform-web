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
public class AlartLogVO {

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


    public static AlartLogVO toVO(AlarmLogDTO alarmLogDTO) {
        if (alarmLogDTO == null) {
            return null;
        }
        AlartLogVO alartLogVO = new AlartLogVO();
        alartLogVO.setId(alarmLogDTO.getId());
        alartLogVO.setJobConfigId(alarmLogDTO.getJobConfigId());
        alartLogVO.setJobName(alarmLogDTO.getJobName());
        alartLogVO.setMessage(alarmLogDTO.getMessage());
        alartLogVO.setStatus(alarmLogDTO.getAlarmLogStatusEnum().getCode());
        if (alarmLogDTO.getAlarMLogTypeEnum() != null) {
            alartLogVO.setTypeDesc(alarmLogDTO.getAlarMLogTypeEnum().getDesc());
        }
        if (alarmLogDTO.getAlarmLogStatusEnum() != null) {
            if (AlarmLogStatusEnum.SUCCESS.equals(alarmLogDTO.getAlarmLogStatusEnum())) {
                alartLogVO.setStatusDesc(alarmLogDTO.getAlarmLogStatusEnum().getDesc());
            } else {
                alartLogVO.setStatusDesc("<span style=\"color: red\">" + alarmLogDTO.getAlarmLogStatusEnum().getDesc() + "</span>");
            }

        }
        alartLogVO.setFailLog(alarmLogDTO.getFailLog());
        alartLogVO.setCreateTime(DateUtil.format(alarmLogDTO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));

        return alartLogVO;
    }


    public static List<AlartLogVO> toListVO(List<AlarmLogDTO> alarmLogDTOList) {
        if (CollectionUtil.isEmpty(alarmLogDTOList)) {
            return Collections.emptyList();
        }

        List<AlartLogVO> list = new ArrayList<>();
        for (AlarmLogDTO alarmLogDTO : alarmLogDTOList) {
            AlartLogVO alartLogVO = AlartLogVO.toVO(alarmLogDTO);
            if (alartLogVO != null) {
                list.add(alartLogVO);
            }
        }

        return list;

    }

}
