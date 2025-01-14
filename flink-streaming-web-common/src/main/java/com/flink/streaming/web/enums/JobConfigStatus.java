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

    FAIL(-1, "失败", true, true, false),

    RUN(1, "运行中", true),

    STOP(0, "停止", false),

    STARTING(2, "启动中", true),

    SUCCESS(3, "提交成功", true),

    UNKNOWN(-2, "未知", true, false, false),

    RESTARTING(99, "重启中", true, false, true),

    SUSPENDED(100, "已挂起", true, false, true),

    FINISHED(98, "已完成", false),

    CANCELED(97, "已退出", true, true, false),

    CANCELING(96, "退出中", true, false, true),

    FAILING(95, "退出中", true, false, true)
    ;

    private Integer code;

    private String desc;

    private boolean beAlarm = false;

    private boolean beRestart = false;

    private boolean beCancel = false;

    JobConfigStatus(Integer code, String desc, boolean beCancel) {
        this.code = code;
        this.desc = desc;
        this.beCancel = beCancel;
    }

    JobConfigStatus(Integer code, String desc, boolean beAlarm, boolean beRestart, boolean beCancel) {
        this.code = code;
        this.desc = desc;
        this.beAlarm = beAlarm;
        this.beRestart = beRestart;
        this.beCancel = beCancel;
    }

    public boolean willAlarm() {
        return this.beAlarm;
    }

    public boolean willRestart() {
        return this.beRestart;
    }

    public boolean canCancel() {
        return this.beCancel;
    }

    public static JobConfigStatus getJobConfigStatus(Integer code) {
        for (JobConfigStatus jobConfigStatus : JobConfigStatus.values()) {
            if (jobConfigStatus.getCode().equals(code)) {
                return jobConfigStatus;
            }
        }
        return JobConfigStatus.UNKNOWN;
    }

    public static JobConfigStatus getJobConfigStatusByStr(String status) {
        for (JobConfigStatus jobConfigStatus : JobConfigStatus.values()) {
            if (jobConfigStatus.getDesc().equals(status)) {
                return jobConfigStatus;
            }
        }
        return JobConfigStatus.UNKNOWN;
    }
}
