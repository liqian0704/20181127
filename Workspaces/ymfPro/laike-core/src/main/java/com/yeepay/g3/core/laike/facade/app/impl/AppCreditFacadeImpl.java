package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.CreditProductResponse;
import com.yeepay.g3.facade.laike.dto.CreditRequset;
import com.yeepay.g3.facade.laike.dto.CreditResponse;
import com.yeepay.g3.facade.laike.facade.app.AppCreditFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 贷款服务Facade实现
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppCreditFacadeImpl extends AbstractFacade implements AppCreditFacade {

    /**
     * 获取贷款链接
     *
     * @param creditRequset
     * @return
     */
    @Override
    public CreditResponse getCreditLink(CreditRequset creditRequset) {
        return creditBiz.getCreditLink(creditRequset);
    }


    /**
     * 获取贷款产品信息
     *
     * @return
     */
    @Override
    public CreditProductResponse getCreditProductInfo(BaseRequest baseRequest) {
        return creditBiz.getCreditProductInfo(baseRequest);
    }

}
