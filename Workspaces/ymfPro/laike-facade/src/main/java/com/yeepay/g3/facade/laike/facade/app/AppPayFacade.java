package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.PassivePayRequest;
import com.yeepay.g3.facade.laike.dto.PassivePayResponse;
import com.yeepay.g3.facade.laike.dto.PayCodeRequest;
import com.yeepay.g3.facade.laike.dto.PayCodeResponse;

/**
 * Description: 支付facade
 * Author: jiawen.huang
 * Date: 16/12/7
 * Time: 17:15
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppPayFacade {

	/**
	 * 创建支付二维码
	 *
	 * @param request
	 * @return
	 */
	PayCodeResponse generatePayCode(PayCodeRequest request);

	/**
	 * 商家扫码
	 *
	 * @param request
	 * @return
	 */
	PassivePayResponse passivePay(PassivePayRequest request);
}
