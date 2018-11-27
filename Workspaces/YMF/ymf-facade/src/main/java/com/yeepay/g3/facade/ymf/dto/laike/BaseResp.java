package com.yeepay.g3.facade.ymf.dto.laike;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;

import java.io.Serializable;

/**
 * Title: 来客接口返回基类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/8.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 不序列化null字段
public class BaseResp implements Serializable {

    private static final long serialVersionUID = 2160498113911185912L;

    private RespStatus status;

    private String errCode;

    private String errMsg;

    private Object content;

    private CountResponse count;

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public CountResponse getCount() {
        return count;
    }

    public void setCount(CountResponse count) {
        this.count = count;
    }
}
