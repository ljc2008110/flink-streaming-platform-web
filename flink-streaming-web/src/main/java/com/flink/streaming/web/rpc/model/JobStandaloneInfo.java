package com.flink.streaming.web.rpc.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuhuipei
 * @Description
 * @date 2020-09-18
 * @time 23:50
 */
@Data
public class JobStandaloneInfo implements Serializable {

    @JSONField(name = "jid")
    private String jid;

    @JSONField(name = "state")
    private String state;

    private String errors;


}
