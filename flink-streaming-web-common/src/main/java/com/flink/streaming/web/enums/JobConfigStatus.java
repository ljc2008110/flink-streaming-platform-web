package com.flink.streaming.web.enums;

import lombok.Getter;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-07-10
 * @time 00:59
 */
@Getter
public enum JobConfigStatus {

    FAIL(-1, "失败", true, true),

    RUN(1, "运行中"),

    STOP(0, "停止"),

    STARTING(2, "启动中"),

    SUCCESS(3, "提交成功"),

    UNKNOWN(-2, "未知", true, false),

    RESTARTING(99, "重启中", true, false),

    SUSPENDED(100, "已挂起", true, false),

    FINISHED(98, "已完成"),

    CANCELED(97, "已退出", true, true),

    CANCELING(96, "退出中", true, false),

    FAILING(95, "退出中", true, false)
    ;

    private Integer code;

    private String desc;

    private boolean beAlarm = false;

    private boolean beRestart = false;

    JobConfigStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    JobConfigStatus(Integer code, String desc, boolean beAlarm, boolean beRestart) {
        this.code = code;
        this.desc = desc;
        this.beAlarm = beAlarm;
        this.beRestart = beRestart;
    }

    public boolean willAlarm() {
        return this.beAlarm;
    }

    public boolean willRestart() {
        return this.beRestart;
    }

    public static JobConfigStatus getJobConfigStatus(Integer code) {
        for (JobConfigStatus jobConfigStatus : JobConfigStatus.values()) {
            if (jobConfigStatus.getCode().equals(code)) {
                return jobConfigStatus;
            }
        }
        return JobConfigStatus.UNKNOWN;
    }
}
