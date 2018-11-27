package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description: 业务申请
 * Author: wei.li
 * Date: 17/5/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface ApplyBiz {

    /**
     * 硬件申请
     *
     * @param request
     * @return
     */
    BaseResponse applyDevice(ApplyBizRequest request);

    /**
     * 信用卡申请
     *
     * @return
     */
    ApplyCreditCardResponse applyCreditCard(ApplyBizRequest request);

    /**
     * 获取信用卡产品列表
     *
     * @param request
     * @return
     */
    CreditProductResponse getCreditCardProductInfo(BaseRequest request);

    /**
     * 获取分享链接(未注册推客用户返回分享链接)
     *
     * @param request
     * @return
     */
    ShareResponse getShareLink(ShareRequest request);
}
