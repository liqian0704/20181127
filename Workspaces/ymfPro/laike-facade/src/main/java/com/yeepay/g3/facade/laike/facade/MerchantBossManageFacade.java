package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerResponse;

/**
 * Description: boss后台商户注册、管理facade
 * Author: wei.li
 * Date: 17/3/1
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface MerchantBossManageFacade {

    /**
     * 大商户注册
     * @param registerMerRequest
     * @return
     */
    RegisterMerResponse registerMer(RegisterMerRequest registerMerRequest);
}
