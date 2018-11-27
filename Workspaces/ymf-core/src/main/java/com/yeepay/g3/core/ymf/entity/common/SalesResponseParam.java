package com.yeepay.g3.core.ymf.entity.common;

/**
 * 获取白玮销售数据接口
 * Created by dongxulu on 17/2/24.
 */
public class SalesResponseParam {

    private String resultJson;
    private String status;
    private String msg;

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
