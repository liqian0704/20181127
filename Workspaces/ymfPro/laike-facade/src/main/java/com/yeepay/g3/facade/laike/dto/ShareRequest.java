package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.BoolEnum;

/**
 * Description: 分享链接请求DTO
 * Author: wei.li
 * Date: 17/8/21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ShareRequest extends BaseRequest {

    /**
     * 本地是否有推客汇
     */
    private BoolEnum hasAlliance;

    public BoolEnum getHasAlliance() {
        return hasAlliance;
    }

    public void setHasAlliance(BoolEnum hasAlliance) {
        this.hasAlliance = hasAlliance;
    }
}
