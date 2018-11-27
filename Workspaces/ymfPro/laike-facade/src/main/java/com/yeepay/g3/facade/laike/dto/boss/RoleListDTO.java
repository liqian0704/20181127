package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.dto.BaseResponse;

import java.util.List;

/**
 * Description: 角色列表
 * Author: jiawen.huang
 * Date: 2017/8/30
 * Time: 15:27
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RoleListDTO extends BaseResponse {

    List<RoleDTO> roleDTOList;

    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }

    public void setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
    }
}
