package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.app.LikerProductionFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 来客增值业务接口
 * Author: wei.li
 * Date: 17/4/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class LikerProductionFacadeImpl extends AbstractFacade implements LikerProductionFacade {

    @Override
    public ProductionListResponse list(BaseRequest request) {
        ProductionListResponse response = new ProductionListResponse();
        response.setAccountResponse(openAccountBiz.findOpenAccount(request));
        response.setList(settleS0Biz.findAppServiceInfo(request));
        return response;
    }

    @Override
    public ProductionResponse open(OpenProductionRequest request) {
        return settleS0Biz.open(request);
    }

    @Override
    public BaseResponse applyDevice(ApplyBizRequest request) {
        return applyBiz.applyDevice(request);
    }

    @Override
    public CreditProductResponse getCreditCardProductInfo(BaseRequest request) {
        return applyBiz.getCreditCardProductInfo(request);
    }

    @Override
    public ApplyCreditCardResponse applyCreditCard(ApplyBizRequest request) {
        CheckUtils.notEmpty(request.getMemberNo(), "会员号");
        return applyBiz.applyCreditCard(request);
    }

    @Override
    public ShareResponse getShareLink(ShareRequest request) {
        return applyBiz.getShareLink(request);
    }
}
