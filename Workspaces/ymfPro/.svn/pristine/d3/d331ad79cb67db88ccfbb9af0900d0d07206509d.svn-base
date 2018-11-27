package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleListDTO;

/**
 * Description: 用户权限
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 14:31
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface UserPermissionBiz {

    /**
     * 查询角色
     *
     * @param roleId
     * @return
     */
    RoleDTO findRoleById(String roleId);

    /**
     * 查询功能
     *
     * @param id
     * @return
     */
    FuncRoleDTO findFuncById(Long id);

    /**
     * 创建新角色
     *
     * @param requestDTO
     * @return
     */
    BaseResponse createRole(RoleDTO requestDTO);

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
     * 禁用／启用功能
     *
     * @param funcId
     * @param operator
     * @param reason
     * @return
     */
    BaseResponse toggleFunc(Long funcId, String operator, String reason);
}
