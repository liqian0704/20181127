package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.RoleEntity;

import java.util.List;

/**
 * Description: 角色服务层接口
 * Author: jiawen.huang
 * Date: 2017/8/21
 * Time: 16:55
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface RoleService {

    /**
     * 创建
     *
     * @param functionEntity
     */
    void save(RoleEntity functionEntity);

    /**
     * 更新
     *
     * @param functionEntity
     */
    void update(RoleEntity functionEntity);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    RoleEntity findOne(String id);

    /**
     * 列出所有
     *
     * @return
     */
    List<RoleEntity> list();
}
