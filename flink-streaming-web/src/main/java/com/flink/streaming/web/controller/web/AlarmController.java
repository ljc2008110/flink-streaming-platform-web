package com.flink.streaming.web.controller.web;

import com.flink.streaming.web.enums.SysConfigEnum;
import com.flink.streaming.web.enums.SysConfigEnumType;
import com.flink.streaming.web.model.dto.AlarmLogDTO;
import com.flink.streaming.web.model.dto.PageModel;
import com.flink.streaming.web.model.param.AlarmLogParam;
import com.flink.streaming.web.model.vo.AlarmLogVO;
import com.flink.streaming.web.model.vo.PageVO;
import com.flink.streaming.web.model.vo.SystemConfigVO;
import com.flink.streaming.web.service.AlarmLogService;
import com.flink.streaming.web.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-21
 * @time 01:52
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AlarmController {

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private AlarmLogService alarmLogService;


    @RequestMapping(value = "/alarmLogList")
    public String queryAlarmLogList(ModelMap modelMap, AlarmLogParam alarmLogParam) {

        PageModel<AlarmLogDTO> pageModel = alarmLogService.queryAlarmLog(alarmLogParam);
        PageVO pageVO = new PageVO();
        pageVO.setPageNum(pageModel.getPageNum());
        pageVO.setPages(pageModel.getPages());
        pageVO.setPageSize(pageModel.getPageSize());
        pageVO.setTotal(pageModel.getTotal());

        modelMap.put("pageVO", pageVO);
        modelMap.put("alarmLogParam", alarmLogParam);
        modelMap.put("alarmLogVOList", AlarmLogVO.toListVO(pageModel.getResult()));
        modelMap.put("active", "alarmLog");
        modelMap.put("open", "alarm");
        return "screen/alarm/listPage";
    }


    @RequestMapping(value = "/alarmConfig")
    public String alarmConfig(ModelMap modelMap) {
        modelMap.put("active", "alarmConfig");
        modelMap.put("open", "alarm");
        modelMap.put("sysConfigVOList", SysConfigEnum.getSysConfigEnumByType(SysConfigEnumType.ALART.name()));
        modelMap.put("systemConfigVOList", SystemConfigVO.toListVO(systemConfigService.getSystemConfig(SysConfigEnumType.ALART)));
        return "screen/alarm/alarm_config";
    }

}
