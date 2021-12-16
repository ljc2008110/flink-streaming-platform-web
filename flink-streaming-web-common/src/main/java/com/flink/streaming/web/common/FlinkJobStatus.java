package com.flink.streaming.web.common;

/**
 * <p>flink job在flink集群中的状态值枚举类</p>
 *
 * @author : Kevin.Lin
 * @create : 2021/9/2 15:31
 * @since : v1.0.0
 */
public enum FlinkJobStatus {
    /**
     * 运行中
     */
    RUNNING("RUNNING"),
    /**
     * 已创建
     */
    CREATED("CREATED"),
    /**
     * 重启中
     */
    RESTARTING("RESTARTING"),
    /**
     * 失败中
     */
    FAILING("FAILING"),
    /**
     * 已失败
     */
    FAILED("FAILED"),
    /**
     * 已完成
     */
    FINISHED("FINISHED"),
    /**
     * 退出中
     */
    CANCELLING("CANCELLING"),
    /**
     * 已退出
     */
    CANCELED("CANCELED"),
    /**
     * 已挂起
     */
    SUSPENDED("SUSPENDED");

    /**
     * flink job 在flink集群中的状态值
     */
    private final String status;

    private FlinkJobStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
