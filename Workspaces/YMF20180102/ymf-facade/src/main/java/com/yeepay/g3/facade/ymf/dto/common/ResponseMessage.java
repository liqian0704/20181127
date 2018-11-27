package com.yeepay.g3.facade.ymf.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yeepay.g3.facade.ymf.exception.YmfException;

import java.io.Serializable;
import java.util.Formatter;

/**
 * Title: 统一返回消息格式
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 不序列化null字段
public class ResponseMessage implements Serializable { // hessian接口返回

    private static final long serialVersionUID = 5230949677958371747L;

    /**
     * 统一回复状态码
     * 成功是ok
     * 错误是error
     * 其他是错误码 后续扩展
     */
    private String status;

    /**
     * 统一回复消息
     */
    private String msg;

    /**
     * 统一回复扩展字段
     */
    private Object content = "";

    /**
     * 易码付异常信息
     */
    @JsonIgnore
    private YmfException exception;

    /**
     * 系统异常信息
     */
    @JsonIgnore
    private Throwable throwable;

    /**
     * 汇总信息
     */
    private CountResponse count;

    /**
     * O(∩_∩)O哈！
     */
    private boolean flag;

    /**
     * 只有消息
     * @param msg
     */
    private ResponseMessage(String msg) {
        this.status = "error";
        this.msg = msg;
    }

    /**
     * 只有消息
     * @param msg
     */
    private ResponseMessage(String msg, YmfException e) {
        this.status = "error";
        this.msg = msg;
        this.exception = e;
    }

    /**
     * 只有消息
     * @param msg
     */
    private ResponseMessage(String msg, Throwable throwable) {
        this.status = "error";
        this.msg = msg;
        this.throwable = throwable;
    }

    /**
     * ok
     */
    private ResponseMessage() {
        this.status = "ok";
    }

    /**
     * ok
     */
    private ResponseMessage(Object content) {
        this.status = "ok";
        this.content = content;
    }

    /**
     * ok
     * @return ok
     */
    public static ResponseMessage ok() {
        return new ResponseMessage();
    }

    /**
     * ok
     * @return ok
     */
    public static ResponseMessage data(Object content) {
        return new ResponseMessage(content);
    }

    /**
     * error
     * @return error
     */
    public static ResponseMessage error(String msg) {
        return new ResponseMessage(msg);
    }

    /**
     * error
     * @return error
     */
    public static ResponseMessage error(String msg, Object...args) {
        return new ResponseMessage(new Formatter().format(msg, args).toString());
    }

    /**
     * error
     * @return error
     */
    public static ResponseMessage error(String msg, YmfException t) {
        return new ResponseMessage(msg, t);
    }

    /**
     * error
     * @return error
     */
    public static ResponseMessage error(String msg, Throwable t) {
        return new ResponseMessage(msg, t);
    }

    /**
     * 是否ok
     * @return
     */
    @JsonIgnore
    public boolean isOk() {
        return "ok".equals(status);
    }

    public String getStatus() {
        return status;
    }

    public ResponseMessage setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseMessage setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getContent() {
        return content;
    }

    public ResponseMessage setContent(Object content) {
        this.content = content;
        return this;
    }

    public YmfException getException() {
        return exception;
    }

    public ResponseMessage setException(YmfException exception) {
        this.exception = exception;
        return this;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ResponseMessage setThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public CountResponse getCount() {
        return count;
    }

    public ResponseMessage setCount(CountResponse count) {
        this.count = count;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public ResponseMessage setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }
}
