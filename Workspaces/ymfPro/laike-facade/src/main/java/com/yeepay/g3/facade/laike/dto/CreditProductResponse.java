package com.yeepay.g3.facade.laike.dto;

import java.util.List;

/**
 * Description: 现金贷产品信息
 * Author: wei.li
 * Date: 17/4/7
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class CreditProductResponse extends BaseResponse {

    /**
     * 产品信息
     */
    private List<ProductInfo> productInfoList;

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }
}
