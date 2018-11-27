package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.boss.OfficialInviteResponse;

/**
 * Description: 官网相关服务
 * Author: wei.li
 * Date: 17/7/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface OfficialManageBiz {

    /**
     * 邀请码的顶层是否为官网盟主
     *
     * @param inviteCode
     * @return
     */
    OfficialInviteResponse isOfficialLord(String inviteCode);
}
