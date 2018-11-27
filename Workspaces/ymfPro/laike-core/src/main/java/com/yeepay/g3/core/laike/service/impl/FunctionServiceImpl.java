package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.FunctionEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.FunctionService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Description: 功能服务层实现
 * Author: jiawen.huang
 * Date: 17/08/21
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class FunctionServiceImpl extends AbstractService implements FunctionService {

    @Override
    public void save(FunctionEntity functionEntity) {
        try {
            functionRepository.save(functionEntity);
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.FUNCTION_REPEAT, e);//funcCode数据库判断重复
        }
    }

    @Override
    public void update(FunctionEntity functionEntity) {
        Integer num = functionRepository.update(functionEntity);
        if (0 == num) {
            throw new LaikeSysException(ErrorCode.FUNCTION_NOT_EXIST);
        }
    }

    @Override
    public FunctionEntity findOne(Long id) {
        FunctionEntity functionEntity = functionRepository.findById(id);
        if (null == functionEntity) {
            throw new LaikeSysException(ErrorCode.FUNCTION_NOT_EXIST);
        }
        return functionEntity;
    }

    @Override
    public FunctionEntity findByCode(String functionCode) {
        FunctionEntity functionEntity = functionRepository.findByCode(functionCode);
        if (null == functionEntity) {
            throw new LaikeSysException(ErrorCode.FUNCTION_NOT_EXIST);
        }
        return functionEntity;
    }

    @Override
    public FunctionEntity findActiveByCode(String functionCode) {
        FunctionEntity functionEntity = findByCode(functionCode);
        if (!functionEntity.getAvailable()) {
            throw new LaikeSysException(ErrorCode.FUNCTION_NOT_AVAILABLE, functionEntity.getReason());
        }
        return functionEntity;
    }

    @Override
    public void toggleAvailable(FunctionEntity functionEntity) {
        FunctionEntity entity = this.findOne(functionEntity.getId());
        entity.setOperator(functionEntity.getOperator());
        entity.setReason(functionEntity.getReason());
        Integer num = functionRepository.toggleAvailable(entity);
        if (0 == num) {
            throw new LaikeSysException(ErrorCode.FUNCTION_NOT_EXIST);
        }
    }
}
