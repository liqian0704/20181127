package com.yeepay.g3.core.laike.dtoparser.impl;

import com.yeepay.g3.core.laike.dtoparser.DTOConvert;
import com.yeepay.g3.core.laike.entity.RoleEntity;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Description: 角色DTO转换器
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 21:02
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RoleDTOConvert implements DTOConvert<RoleDTO, RoleEntity> {

    @Override
    public RoleEntity convert2Entity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        try {
            BeanUtils.copyProperties(roleEntity, roleDTO);
            return roleEntity;
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }

    @Override
    public RoleDTO convert2DTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        try {
            BeanUtils.copyProperties(roleDTO, roleEntity);
            return roleDTO;
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }
}
