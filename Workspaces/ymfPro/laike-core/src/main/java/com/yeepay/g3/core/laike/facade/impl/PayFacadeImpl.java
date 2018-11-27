package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.PayCodeRequest;
import com.yeepay.g3.facade.laike.dto.PayCodeResponse;
import com.yeepay.g3.facade.laike.facade.PayFacade;
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
public class PayFacadeImpl extends AbstractFacade implements PayFacade {

	@Override
	public PayCodeResponse generatePayCode(PayCodeRequest request) {
		return qrCodePayBiz.generatePayCode(request);
	}
}
