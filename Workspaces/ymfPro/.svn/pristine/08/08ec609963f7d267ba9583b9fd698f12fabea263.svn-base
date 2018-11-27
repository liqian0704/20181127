package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.CustomerSourceResponse;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAllianceAccRequest;
import com.yeepay.g3.facade.laike.facade.AllianceBossFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 展业APP(boss用)
 * Author: wei.li
 * Date: 17/6/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AllianceBossFacadeImpl extends AbstractFacade implements AllianceBossFacade {

    @Override
    public BaseResponse updateAllianceAccount(UpdateAllianceAccRequest request) {
        CheckUtils.notEmpty(request.getId(), "入网单号");
        CheckUtils.notEmpty(request.getStatus(), "审核状态");
        return allianceAccountBiz.updateAllianceAccount(request);
    }

    @Override
    public CustomerSourceResponse queryCustomerSource(String merchantNo) {
        CheckUtils.notEmpty(merchantNo, "商户号");
        return allianceAccountBiz.queryCustomerSource(merchantNo);
    }
}
