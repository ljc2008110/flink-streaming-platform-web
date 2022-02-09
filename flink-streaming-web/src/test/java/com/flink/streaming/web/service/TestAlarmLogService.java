package com.flink.streaming.web.service;

import com.flink.streaming.web.base.TestRun;
import com.flink.streaming.web.enums.AlarmLogStatusEnum;
import com.flink.streaming.web.enums.AlarmLogTypeEnum;
import com.flink.streaming.web.model.dto.AlarmLogDTO;
import com.flink.streaming.web.model.dto.PageModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-25
 * @time 19:27
 */
public class TestAlarmLogService extends TestRun {

    @Autowired
    private AlarmLogService alarmLogService;

    @Test
    public void addAlarmLog() {

        for (int i = 0; i <20 ; i++) {
            AlarmLogDTO alarmLogDTO = new AlarmLogDTO();
            alarmLogDTO.setJobConfigId(2L);
            alarmLogDTO.setAlarMLogTypeEnum(AlarmLogTypeEnum.DINGDING);
            alarmLogDTO.setAlarmLogStatusEnum(AlarmLogStatusEnum.SUCCESS);
            alarmLogDTO.setMessage("单测成功");
            alarmLogService.addAlarmLog(alarmLogDTO);


            AlarmLogDTO alarmLogDTO2 = new AlarmLogDTO();
            alarmLogDTO2.setJobConfigId(2L);
            alarmLogDTO2.setAlarMLogTypeEnum(AlarmLogTypeEnum.DINGDING);
            alarmLogDTO2.setAlarmLogStatusEnum(AlarmLogStatusEnum.FAIL);
            alarmLogDTO2.setMessage("单测失败");
            alarmLogDTO2.setFailLog("xxx失败");
            alarmLogService.addAlarmLog(alarmLogDTO2);
        }


    }


    @Test
    public void findLogById(){
        AlarmLogDTO alarmLogDTO = alarmLogService.findLogById(2L);
        System.out.println(alarmLogDTO);
    }


    @Test
    public void queryAlarmLog(){
        PageModel<AlarmLogDTO> pageModel= alarmLogService.queryAlarmLog(null);
        System.out.println(pageModel);
    }
}
