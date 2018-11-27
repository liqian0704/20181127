package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.RoleFuncService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 角色权限服务层
 * Author: jiawen.huang
 * Date: 17/08/21
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class RoleFuncServiceImpl extends AbstractService implements RoleFuncService {

    @Override
    public void save(RoleFunctionEntity roleFunctionEntity) {
        try {
            roleFunctionRepository.save(roleFunctionEntity);//ROLE_ID&FUNC_ID联合唯一索引冲突
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_REPEAT, e);
        }
    }

    @Override
    public void batchSave(Long funcId, List<String> roleIds, String operator, String description) {
        try {
            for (String roleId : roleIds) {
                RoleFunctionEntity roleFunctionEntity = roleFunctionRepository.findByRoleAndFunId(roleId, funcId);
                if (null == roleFunctionEntity) {
                    roleFunctionEntity = new RoleFunctionEntity();
                    roleFunctionEntity.setRoleId(roleId);
                    roleFunctionEntity.setOperator(operator);
                    roleFunctionEntity.setFunctionId(funcId);
                    roleFunctionEntity.setDescription(description);
                    save(roleFunctionEntity);
                } else if (roleFunctionEntity.getAvailable()) {
                    roleFunctionRepository.toggleAvailable(roleFunctionEntity);
                }
            }
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_REPEAT, e);
        }
    }

    @Override
    public void update(RoleFunctionEntity roleFunctionEntity) {
        Integer num = roleFunctionRepository.update(roleFunctionEntity);
        if (0 == num) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_NOT_EXIST);
        }
    }

    @Override
    public RoleFunctionEntity findOne(Long id) {
        RoleFunctionEntity roleFunctionEntity = roleFunctionRepository.findById(id);
        if (null == roleFunctionEntity) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_NOT_EXIST);
        }
        return roleFunctionEntity;
    }

    @Override
    public RoleFunctionEntity findByRoleAndFunId(String roleId, Long funcId) {
        RoleFunctionEntity roleFunctionEntity = roleFunctionRepository.findByRoleAndFunId(roleId, funcId);
        if (null == roleFunctionEntity) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_NOT_EXIST);
        }
        return roleFunctionEntity;
    }

    @Override
    public List<String> findRoleIdsByFunId(Long funcId) {
        return roleFunctionRepository.findByFunId(funcId);
    }

    @Override
    public void closeAll(Long funcId, List<String> roleIds, String operator, String description) {
        String roleIdsStr = StringUtils.join(roleIds, ",");
        Integer num = roleFunctionRepository.closeAll(funcId, roleIdsStr, operator, description);
        if (num != roleIds.size()) {
            throw new LaikeSysException(ErrorCode.ROLE_FUNC_NOT_EXIST);
        }
    }
}
