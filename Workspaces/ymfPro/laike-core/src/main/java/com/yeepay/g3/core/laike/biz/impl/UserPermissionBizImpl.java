package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.UserPermissionBiz;
import com.yeepay.g3.core.laike.dtoparser.impl.FuncRoleDTOConvert;
import com.yeepay.g3.core.laike.dtoparser.impl.RoleDTOConvert;
import com.yeepay.g3.core.laike.entity.FunctionEntity;
import com.yeepay.g3.core.laike.entity.RoleEntity;
import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.boss.FuncRoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleDTO;
import com.yeepay.g3.facade.laike.dto.boss.RoleListDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 用户权限聚合业务
 * Author: jiawen.huang
 * Date: 2017/8/23
 * Time: 14:32
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class UserPermissionBizImpl extends AbstractBiz implements UserPermissionBiz {

    @Override
    public RoleDTO findRoleById(String roleId) {
        RoleEntity roleEntity = roleService.findOne(roleId);
        return new RoleDTOConvert().convert2DTO(roleEntity);
    }

    @Override
    public FuncRoleDTO findFuncById(Long id) {
        FunctionEntity functionEntity = functionService.findOne(id);
        return new FuncRoleDTOConvert().convert2DTO(functionEntity);
    }


    @Override
    public BaseResponse createRole(RoleDTO requestDTO) {
        RoleEntity roleEntity = new RoleDTOConvert().convert2Entity(requestDTO);
        roleService.save(roleEntity);
        return new BaseResponse();
    }

    @Override
    public BaseResponse modifyRole(RoleDTO requestDTO) {
        RoleEntity roleEntity = new RoleDTOConvert().convert2Entity(requestDTO);
        roleService.update(roleEntity);
        return new BaseResponse();
    }

    @Override
    public RoleListDTO listRoles() {
        List<RoleEntity> list = roleService.list();
        List<RoleDTO> dtoList = Lists.newArrayList();
        for (RoleEntity roleEntity : list) {
            dtoList.add(new RoleDTOConvert().convert2DTO(roleEntity));
        }
        RoleListDTO roleListDTO = new RoleListDTO();
        roleListDTO.setRoleDTOList(dtoList);
        return roleListDTO;
    }

    @Override
    public BaseResponse createFunc(FuncRoleDTO requestDTO) {
        FunctionEntity functionEntity = new FuncRoleDTOConvert().convert2Entity(requestDTO);
        functionService.save(functionEntity);
        for (String roleId : requestDTO.getRoleIds()) {
            RoleEntity roleEntity = roleService.findOne(roleId);
            RoleFunctionEntity roleFunctionEntity = new RoleFunctionEntity();
            roleFunctionEntity.setFunctionId(functionEntity.getId());
            roleFunctionEntity.setOperator(functionEntity.getOperator());
            roleFunctionEntity.setRoleId(roleEntity.getId());
            roleFuncService.save(roleFunctionEntity);
        }
        return new BaseResponse();
    }

    @Transactional
    @Override
    public BaseResponse modifyFunc(FuncRoleDTO requestDTO) {
        FunctionEntity functionEntity = new FuncRoleDTOConvert().convert2Entity(requestDTO);
        FunctionEntity existEunc = functionService.findOne(functionEntity.getId());
        List<String> existRoleIds = roleFuncService.findRoleIdsByFunId(existEunc.getId());
        List<String> requestRoleIds = requestDTO.getRoleIds();
        List<String> closeRoleIds = Lists.newArrayList();
        List<String> addRoleIds = Lists.newArrayList();
        closeRoleIds.addAll(existRoleIds);
        addRoleIds.addAll(requestRoleIds);
        for (String requestRoleId : requestRoleIds) {
            if (existRoleIds.contains(requestRoleId)) {
                closeRoleIds.remove(requestRoleId);
                addRoleIds.remove(requestRoleId);
            }
        }
        roleFuncService.closeAll(functionEntity.getId(), closeRoleIds, requestDTO.getOperator(), requestDTO.getReason());
        //对新对创建，原来关闭的启用
        roleFuncService.batchSave(functionEntity.getId(), addRoleIds, requestDTO.getOperator(), requestDTO.getReason());
        functionService.update(functionEntity);
        return new BaseResponse();
    }

    @Override
    public BaseResponse toggleFunc(Long funcId, String operator, String reason) {
        FunctionEntity functionEntity = new FunctionEntity();
        functionEntity.setId(funcId);
        functionEntity.setOperator(operator);
        functionEntity.setReason(reason);
        functionService.toggleAvailable(functionEntity);
        return new BaseResponse();
    }
}
