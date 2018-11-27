package com.yeepay.g3.core.laike.dtoparser.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.core.laike.dtoparser.DTOConvert;
import com.yeepay.g3.core.laike.entity.FunctionEntity;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.enumtype.FuncLevelEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;


/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 21:15
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class FuncRoleDTOConvert implements DTOConvert<FuncRoleDTO, FunctionEntity> {

    @Override
    public FunctionEntity convert2Entity(FuncRoleDTO funcRoleDTO) {
        FunctionEntity functionEntity = new FunctionEntity();
        try {
            BeanUtils.copyProperties(functionEntity, funcRoleDTO);
            List<FuncLevelEnum> funcLevelList = funcRoleDTO.getFuncLevelList();
            String funcLevel = functionEntity.getFuncLevel();
            for (FuncLevelEnum funcLevelEnum : funcLevelList) {
                funcLevel = funcLevel.substring(0, funcLevelEnum.getValue())
                        + "1" + funcLevel.substring(funcLevelEnum.getValue() + 1);
            }
            functionEntity.setFuncLevel(funcLevel);
            return functionEntity;
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }

    @Override
    public FuncRoleDTO convert2DTO(FunctionEntity functionEntity) {
        FuncRoleDTO funcRoleDTO = new FuncRoleDTO();
        try {
            BeanUtils.copyProperties(funcRoleDTO, functionEntity);
            List<FuncLevelEnum> funcLevelList = Lists.newArrayList();
            for (FuncLevelEnum funcLevelEnum : FuncLevelEnum.getValueList()) {
                if (functionEntity.checkLevel(funcLevelEnum)) {
                    funcLevelList.add(funcLevelEnum);
                }
            }
            funcRoleDTO.setFuncLevelList(funcLevelList);
            return funcRoleDTO;
        } catch (Exception e) {
            throw new LaikeSysException(ErrorCode.CONVERT_DTO_EXCEPTION);
        }
    }
}
