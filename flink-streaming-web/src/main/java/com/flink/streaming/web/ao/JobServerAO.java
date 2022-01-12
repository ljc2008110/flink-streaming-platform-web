package com.flink.streaming.web.ao;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-07-20
 * @time 23:11
 */
public interface JobServerAO {

    /**
     * 启动任务
     *
     * @author zhuhuipei
     * @date 2020-07-20
     * @time 23:12
     */
    void start(Long id, Long savepointId, String userName);

    /**
     * 关闭任务
     *
     * @author zhuhuipei
     * @date 2020-07-20
     * @time 23:12
     */
    void stop(Long id, String userName);

    /**
     * 关闭任务
     *
     * @param id job-config-id
     * @param userName 用户名
     * @param isSavePoint 是否保存savepoint后再停
     * @author Kevin.Lin
     * @date 2021-12-24 17:23:33
     */
    void stop(Long id, String userName, Boolean isSavePoint);

    /**
     * 执行savepoint
     *
     * @author zhuhuipei
     * @date 2020-09-21
     * @time 00:41
     */
    void savepoint(Long id);


    /**
     * 开启配置
     *
     * @author zhuhuipei
     * @date 2020-08-12
     * @time 21:14
     */
    void open(Long id, String userName);

    /**
     * 关闭配置
     *
     * @author zhuhuipei
     * @date 2020-08-12
     * @time 21:14
     */
    void close(Long id, String userName);

}
