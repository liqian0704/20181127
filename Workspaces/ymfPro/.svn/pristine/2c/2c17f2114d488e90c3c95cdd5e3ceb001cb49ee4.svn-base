package com.yeepay.g3.core.laike.facade;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.ProductResponse;
import com.yeepay.g3.facade.laike.dto.S0InfoRequest;
import com.yeepay.g3.facade.laike.dto.S0InfoResponse;
import com.yeepay.g3.facade.laike.facade.ProductInfoFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:userAuthorityFacade
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ProductFacadeTest extends BaseTest {

	@Autowired
	private ProductInfoFacade productInfoFacade;

	@Test
	public void findOne() {
		ProductResponse response = productInfoFacade.findProductInfo();
		System.out.println("!!!" + JSONUtils.toJsonString(response));
	}

	@Test
	public void findS0Info() {
		S0InfoRequest request = new S0InfoRequest();
		request.setMerchantNo("10040011560");
		S0InfoResponse response = productInfoFacade.findS0Info(request);
		System.out.println("!!!" + JSONUtils.toJsonString(response));
	}

}
