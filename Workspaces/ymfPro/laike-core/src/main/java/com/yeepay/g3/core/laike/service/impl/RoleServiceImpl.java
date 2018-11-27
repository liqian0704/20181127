package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.RoleEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.RoleService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Description: 角色服务层
 * Author: jiawen.huang
 * Date: 2017/8/21
 * Time: 16:55
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class RoleServiceImpl extends AbstractService implements RoleService {

    @Override
    public void save(RoleEntity roleEntity) {
        try {
            String id = UIDGenerator.generateBizUID(roleRepository.nextSequence(), BizPrefixEnum.RO.getValue());
            roleEntity.setId(id);
            RoleEntity parentRole = roleRepository.findById(roleEntity.getParentId());
            if (null == parentRole) {
                throw new LaikeSysException(ErrorCode.ROLE_PARENT_NOT_EXIST);
            }
            roleRepository.save(roleEntity);
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.ROLE_REPEAT, e);
        }
    }

    @Override
    public void update(RoleEntity roleEntity) {
        Integer num = roleRepository.update(roleEntity);
        if (0 == num) {
            throw new LaikeSysException(ErrorCode.ROLE_NOT_EXIST);
        }
    }

    @Override
    public RoleEntity findOne(String id) {
        RoleEntity roleEntity = roleRepository.findById(id);
        if (null == roleEntity) {
            throw new LaikeSysException(ErrorCode.ROLE_NOT_EXIST);
        }
        return roleEntity;
    }

    @Override
    public List<RoleEntity> list() {
        return roleRepository.findAll();
    }
}
