package com.yeepay.g3.core.laike.service;


import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;

import java.util.List;

/**
 * Description: 角色权限服务层接口
 * Author: jiawen.huang
 * Date: 17/08/21
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface RoleFuncService {

    /**
     * 创建
     *
     * @param roleFunctionEntity
     */
    void save(RoleFunctionEntity roleFunctionEntity);

    /**
     * 创建多项关系
     *
     * @param funcId
     * @param roleIds
     * @param operator
     * @param description
     */
    void batchSave(Long funcId, List<String> roleIds, String operator, String description);

    /**
     * 更新
     *
     * @param roleFunctionEntity
     */
    void update(RoleFunctionEntity roleFunctionEntity);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    RoleFunctionEntity findOne(Long id);

    /**
     * 根据roleCode&&funcId和查询
     *
     * @param roleId
     * @param funcId
     * @return
     */
    RoleFunctionEntity findByRoleAndFunId(String roleId, Long funcId);

    /**
     * 查询roleIds
     *
     * @param funcId
     * @return roleIds
     */
    List<String> findRoleIdsByFunId(Long funcId);

    /**
     * 根据funcId和roleIds关系对禁用
     *
     * @param funcId
     * @param roleIds
     * @param operator
     * @param description
     */
    void closeAll(Long funcId, List<String> roleIds, String operator, String description);
}
