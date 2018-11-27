package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.OfficialManageBiz;
import com.yeepay.g3.facade.laike.dto.boss.OfficialInviteResponse;
import org.springframework.stereotype.Component;

/**
 * Description:官网相关服务
 * Author: wei.li
 * Date: 17/7/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class OfficialManageBizImpl extends AbstractBiz implements OfficialManageBiz {

    @Override
    public OfficialInviteResponse isOfficialLord(String inviteCode) {
        OfficialInviteResponse response = new OfficialInviteResponse();
        response.setOfficialCode(allianceService.isOfficialLord(inviteCode));
        return response;
    }
}
