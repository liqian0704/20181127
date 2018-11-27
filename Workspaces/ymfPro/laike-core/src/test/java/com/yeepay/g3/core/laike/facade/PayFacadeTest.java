package com.yeepay.g3.core.laike.facade;

import com.google.gson.Gson;
import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.app.AppPayFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: PayNotifyFacadeTest
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 16:56
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PayFacadeTest extends BaseTest {

	@Autowired
	private AppPayFacade payFacade;

	@Test
	public void generatePayURL() {
		PayCodeRequest request = new PayCodeRequest();
		request.setGratuityAmount(new Amount(100));
		request.setMemberNo("212468314268");
		request.setPaySource(PaySource.NCPAY);
		request.setIp("223.223.193.194");
		request.setOrderAmount(new Amount(10000));
		PayCodeResponse response = payFacade.generatePayCode(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

	@Test
	public void passivePay() {
		PassivePayRequest request = new PassivePayRequest();
		request.setGratuityAmount(new Amount(0.01));
		request.setMemberNo("212468316337");
		request.setIp("223.223.193.194");
		request.setUserPayCode("1309420326972606");
		request.setOrderAmount(new Amount(0.01));
		request.setDeviceSn("duy1whiuhduihydwuehd");
		BaseResponse response = payFacade.passivePay(request);
		System.out.print(new Gson().toJson(response));
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

}
