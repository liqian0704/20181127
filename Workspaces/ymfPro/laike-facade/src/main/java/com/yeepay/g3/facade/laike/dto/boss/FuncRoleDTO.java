package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.annotations.LengthValidator;
import com.yeepay.g3.facade.laike.annotations.NotEmpty;
import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 20:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class FuncRoleDTO {

    /**
     * func_id
     */
    Long id;

    /**
     * 功能名称
     */
    @NotEmpty("功能名称")
    @LengthValidator(value = "功能名称", length = 50)
    private String functionName;

    /**
     * 功能码(methodName)
     */
    @NotEmpty(value = "功能码")
    private String functionCode;

    /**
     * 操作人
     */
    @NotEmpty(value = "操作人")
    private String operator;

    /**
     * 功能级别
     */
    private List<FuncLevelEnum> funcLevelList;

    /**
     * 修改原因
     */
    private String reason;

    /**
     * 分配role
     */
    @NotEmpty(value = "授权角色")
    private List<String> roleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<FuncLevelEnum> getFuncLevelList() {
        return funcLevelList;
    }

    public void setFuncLevelList(List<FuncLevelEnum> funcLevelList) {
        this.funcLevelList = funcLevelList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
