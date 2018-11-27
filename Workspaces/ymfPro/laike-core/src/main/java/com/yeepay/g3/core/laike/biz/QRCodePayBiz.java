package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.PassivePayRequest;
import com.yeepay.g3.facade.laike.dto.PassivePayResponse;
import com.yeepay.g3.facade.laike.dto.PayCodeRequest;
import com.yeepay.g3.facade.laike.dto.PayCodeResponse;

/**
 * Description: 二维码支付业务接口层
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 17:25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface QRCodePayBiz {

	/**
	 * 生成支付二维码
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
