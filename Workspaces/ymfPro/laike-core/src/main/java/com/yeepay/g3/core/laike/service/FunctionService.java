package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.FunctionEntity;

/**
 * Description: 功能服务层接口
 * Author: jiawen.huang
 * Date: 17/08/21
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface FunctionService {

    /**
     * 创建
     *
     * @param functionEntity
     */
    void save(FunctionEntity functionEntity);

    /**
     * 更新
     *
     * @param functionEntity
     */
    void update(FunctionEntity functionEntity);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    FunctionEntity findOne(Long id);

    /**
     * 根据唯一的functionCode查询
     *
     * @param functionCode
     * @return
     */
    FunctionEntity findByCode(String functionCode);

    /**
     * 根据唯一的functionCode查询启用的
     *
     * @param functionCode
     * @return
     */
    FunctionEntity findActiveByCode(String functionCode);

    /**
     * 启用/关闭
     *
     * @param functionEntity
     */
    void toggleAvailable(FunctionEntity functionEntity);
}
