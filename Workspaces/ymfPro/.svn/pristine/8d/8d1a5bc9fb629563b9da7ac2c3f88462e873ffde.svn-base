package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.annotations.NotEmpty;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleListDTO;
import com.yeepay.g3.facade.laike.facade.UserPermissionFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 用户权限管理接口实现
 * Author: jiawen.huang
 * Date: 2017/8/24
 * Time: 16:02
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class UserPermissionFacadeImpl extends AbstractFacade implements UserPermissionFacade {

    @Override
    public BaseResponse createRole(RoleDTO requestDTO) {
        CheckUtils.notEmpty(requestDTO.getRoleCode(), "角色编码");
        return userPermissionBiz.createRole(requestDTO);
    }

    @Override
    public RoleDTO findRoleById(@NotEmpty(value = "角色ID") String roleId) {
        return userPermissionBiz.findRoleById(roleId);
    }

    @Override
    public BaseResponse modifyRole(RoleDTO requestDTO) {
        CheckUtils.notEmpty(requestDTO.getId(), "角色id");
        return userPermissionBiz.modifyRole(requestDTO);
    }

    @Override
    public RoleListDTO listRoles() {
        return userPermissionBiz.listRoles();
    }

    @Override
    public FuncRoleDTO findFuncById(@NotEmpty(value = "功能ID") Long id) {
        return userPermissionBiz.findFuncById(id);
    }

    @Override
    public BaseResponse createFunc(FuncRoleDTO requestDTO) {
        return userPermissionBiz.createFunc(requestDTO);
    }

    @Override
    public BaseResponse modifyFunc(FuncRoleDTO requestDTO) {
        return userPermissionBiz.modifyFunc(requestDTO);
    }

    @Override
    public BaseResponse toggleFunc(Long funcId, String operator, String reason) {
        return userPermissionBiz.toggleFunc(funcId, operator, reason);
    }
}
