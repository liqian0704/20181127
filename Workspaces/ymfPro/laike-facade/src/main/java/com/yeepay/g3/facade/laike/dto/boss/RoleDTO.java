package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.annotations.NotEmpty;

/**
 * Description: 角色传输对象
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 20:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RoleDTO {

    private String id;

    /**
     * 上级
     */
    @NotEmpty(value = "上级")
    private String parentId;

    /**
     * 角色名称
     */
    @NotEmpty(value = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 操作员
     */
    @NotEmpty(value = "操作员")
    private String operator;

    /**
     * 描述
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
