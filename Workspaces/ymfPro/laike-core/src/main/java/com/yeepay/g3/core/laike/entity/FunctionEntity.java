package com.yeepay.g3.core.laike.entity;

import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;

import java.util.Date;

/**
 * Description: 功能，不显示指定app，因为每个api都是区分app的
 * Author: jiawen.huang
 * Date: 2017/6/21
 * Time: 19:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class FunctionEntity extends PersistenceEntity {

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 功能码(methodName)
     */
    private String functionCode;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 上次更新时间
     */
    private Date updateTime;

    /**
     * 功能级别
     */
    private String funcLevel = "00000000";

    /**
     * 启用
     */
    private Boolean available;

    /**
     * 禁用原因
     */
    private String reason;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(String funcLevel) {
        this.funcLevel = funcLevel;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean checkLevel(FuncLevelEnum levelEnum) {
        return String.valueOf(this.funcLevel.charAt(levelEnum.getValue())).equals("1");
    }
}