package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.CreditProductResponse;
import com.yeepay.g3.facade.laike.dto.CreditRequset;
import com.yeepay.g3.facade.laike.dto.CreditResponse;

/**
 * Description: 贷款服务Facade
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppCreditFacade {

    /**
     * 获取贷款链接
     *
     * @param creditRequset
     * @return
     */
    CreditResponse getCreditLink(CreditRequset creditRequset);

    /**
     * 获取现金贷产品信息
     *
     * @return
     */
    CreditProductResponse getCreditProductInfo(BaseRequest baseRequest);
}
