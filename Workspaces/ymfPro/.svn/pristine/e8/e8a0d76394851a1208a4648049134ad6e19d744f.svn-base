package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerResponse;
import com.yeepay.g3.facade.laike.facade.MerchantBossManageFacade;
import org.springframework.stereotype.Component;

/**
 * Description: boss后台商户注册、管理facade
 * Author: wei.li
 * Date: 17/3/1
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class MerchantBossManageFacadeImpl extends AbstractFacade  implements MerchantBossManageFacade{

    @Override
    public RegisterMerResponse registerMer(RegisterMerRequest requestDTO) {
        return merchantManageBiz.registerMer(requestDTO);
    }
}
