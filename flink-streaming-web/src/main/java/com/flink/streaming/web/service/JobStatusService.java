/*
 * Copyright(c)2002-2022, 北京意锐新创科技有限公司
 * 项目名称: flink-streaming-platform-web
 * Date: 2022/1/5
 * Author: linjinchun(kevin)
 */
package com.flink.streaming.web.service;

/**
 * <p></p>
 *
 * @author Kevin.Lin
 * @date 2022/1/5 14:05
 * @since v1.0.0
 */
public interface JobStatusService {
    /**
     * 同步配置开启的任务状态
     */
    void syncJobStatus();

    /**
     * 获取所有状态异常的任务
     */
    void checkUnExpectedJobStatus();
}
