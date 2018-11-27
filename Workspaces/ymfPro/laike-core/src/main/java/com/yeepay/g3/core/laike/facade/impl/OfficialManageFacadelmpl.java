package com.yeepay.g3.core.laike.facade.impl;


import com.yeepay.g3.facade.laike.dto.boss.OfficialInviteResponse;
import com.yeepay.g3.facade.laike.facade.OfficialManageFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 官网相关服务
 * Author: wei.li
 * Date: 17/7/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class OfficialManageFacadelmpl extends AbstractFacade implements OfficialManageFacade {

    @Override
    public OfficialInviteResponse isOfficialLord(String inviteCode) {
        CheckUtils.notEmpty(inviteCode, "邀请码");
        return officialManageBiz.isOfficialLord(inviteCode);
    }
}
