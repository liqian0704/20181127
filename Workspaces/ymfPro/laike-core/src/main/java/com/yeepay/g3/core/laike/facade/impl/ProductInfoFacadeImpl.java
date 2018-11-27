package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.ProductInfoFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Component;

/**
 * Description: 产品费率等查询
 * Author: jiawen.huang
 * Date: 16/12/8
 * Time: 16:45
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class ProductInfoFacadeImpl extends AbstractFacade implements ProductInfoFacade {

	@Override
	public ProductResponse findProductInfo() {
		String productJson = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LAIKE_PRODUCT_RATE);
		ProductResponse response = JSONUtils.jsonToBean(productJson, ProductResponse.class);
		return response;
	}

	@Override
	public NewProductResponse findProductInfo(NewProductRequest request) {
		CheckUtils.notEmpty(request.getRequestNo(), "入网单号");
		return openAccountBiz.findProductInfo(request);
	}

	@Override
	public S0InfoResponse findS0Info(S0InfoRequest request) {
        CheckUtils.notEmpty(request.getMerchantNo(), "商户号");
        return settleS0Biz.findS0Info(request);
	}

}
