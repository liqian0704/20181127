package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.CreditProductResponse;
import com.yeepay.g3.facade.laike.dto.CreditRequset;
import com.yeepay.g3.facade.laike.dto.CreditResponse;

/**
 * Description: 贷款相关服务Biz
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface CreditBiz {

    /**
     * 获取贷款公司链接
     */
    CreditResponse getCreditLink(CreditRequset creditRequset);

    /**
     * 获取现金贷产品信息
     */
    CreditProductResponse getCreditProductInfo(BaseRequest baseRequest);
}
