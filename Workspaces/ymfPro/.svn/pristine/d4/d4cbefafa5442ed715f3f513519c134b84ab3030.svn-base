package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.PassivePayRequest;
import com.yeepay.g3.facade.laike.dto.PassivePayResponse;
import com.yeepay.g3.facade.laike.dto.PayCodeRequest;
import com.yeepay.g3.facade.laike.dto.PayCodeResponse;
import com.yeepay.g3.facade.laike.facade.app.AppPayFacade;
import org.springframework.stereotype.Component;

/**
 * Description:支付facadeimpl
 * Author: jiawen.huang
 * Date: 16/12/7
 * Time: 17:16
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppPayFacadeImpl extends AbstractFacade implements AppPayFacade {

	@Override
	public PayCodeResponse generatePayCode(PayCodeRequest request) {
		return qrCodePayBiz.generatePayCode(request);
	}

	@Override
	public PassivePayResponse passivePay(PassivePayRequest request) {
		return qrCodePayBiz.passivePay(request);
	}
}
