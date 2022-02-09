package com.flink.streaming.web.mapper;

import com.flink.streaming.web.model.entity.AlarmLog;
import com.flink.streaming.web.model.param.AlarmLogParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmLogMapper {

    /**
     * 插入告警日志
     * @param alarmLog 告警日志对象
     * @return 返回插入记录成功数
     */
    int insert(AlarmLog alarmLog);

    /**
     * 查询告警日志详情
     * @param id 告警日志id
     * @return 返回告警日志信息
     */
    AlarmLog selectByPrimaryKey(@Param("id") Long id);

    /**
     * 分页查询
     * @param alarmLogParam 告警日志
     * @return 返回告警日志分页数据
     */
    Page<AlarmLog> selectByParam(AlarmLogParam alarmLogParam);

}
