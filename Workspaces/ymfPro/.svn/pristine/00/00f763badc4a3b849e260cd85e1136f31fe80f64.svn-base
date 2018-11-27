package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.annotations.NotEmpty;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleListDTO;

/**
 * Description: 用户权限管理接口
 * Author: jiawen.huang
 * Date: 2017/8/24
 * Time: 16:01
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface UserPermissionFacade {

    /**
     * 创建新角色
     *
     * @param requestDTO
     * @return
     */
    BaseResponse createRole(RoleDTO requestDTO);

    /**
     * 查询
     *
     * @param roleId
     * @return
     */
    RoleDTO findRoleById(@NotEmpty(value = "角色ID") String roleId);

    /**
     * 修改角色
     *
     * @param requestDTO
     * @return
     */
    BaseResponse modifyRole(RoleDTO requestDTO);

    /**
     * 列出所有角色
     *
     * @return
     */
    RoleListDTO listRoles();

    /**
     * 查询
     *
     * @param id
     * @return
     */
    FuncRoleDTO findFuncById(@NotEmpty(value = "功能ID") Long id);

    /**
     * 新增功能
     *
     * @param requestDTO
     * @return
     */
    BaseResponse createFunc(FuncRoleDTO requestDTO);

    /**
     * 修改功能
     *
     * @param requestDTO
     * @return
     */
    BaseResponse modifyFunc(FuncRoleDTO requestDTO);

    /**
     * 禁用\启用功能
     *
     * @param funcId
     * @param operator
     * @param reason
     * @return
     */
    BaseResponse toggleFunc(@NotEmpty(value = "功能ID") Long funcId,
                            @NotEmpty(value = "操作员") String operator,
                            @NotEmpty(value = "禁用／启用原因") String reason);
}
