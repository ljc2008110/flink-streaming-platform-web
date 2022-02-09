package com.flink.streaming.web.service.impl;

import com.flink.streaming.web.enums.YN;
import com.flink.streaming.web.mapper.AlarmLogMapper;
import com.flink.streaming.web.model.dto.AlarmLogDTO;
import com.flink.streaming.web.model.dto.PageModel;
import com.flink.streaming.web.model.entity.AlarmLog;
import com.flink.streaming.web.model.param.AlarmLogParam;
import com.flink.streaming.web.service.AlarmLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-25
 * @time 21:43
 */
@Slf4j
@Service
public class AlarmLogServiceImpl implements AlarmLogService {


    @Autowired
    private AlarmLogMapper alarmLogMapper;

    @Override
    public void addAlarmLog(AlarmLogDTO alarmLogDTO) {
        if (alarmLogDTO == null) {
            return;
        }
        alarmLogMapper.insert(AlarmLogDTO.toEntity(alarmLogDTO));
    }


    @Override
    public AlarmLogDTO findLogById(Long id) {
        return AlarmLogDTO.toDTO(alarmLogMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageModel<AlarmLogDTO> queryAlarmLog(AlarmLogParam alarmLogParam) {
        if (alarmLogParam == null) {
            alarmLogParam = new AlarmLogParam();
        }
        PageHelper.startPage(alarmLogParam.getPageNum(), alarmLogParam.getPageSize(), YN.Y.getCode());

        //只能查最近30天的
        Page<AlarmLog> page = alarmLogMapper.selectByParam(alarmLogParam);
        if (page == null) {
            return null;
        }
        PageModel<AlarmLogDTO> pageModel = new PageModel<>();
        pageModel.setPageNum(page.getPageNum());
        pageModel.setPages(page.getPages());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setTotal(page.getTotal());
        pageModel.addAll(AlarmLogDTO.toListDTO(page.getResult()));
        return pageModel;

    }
}
