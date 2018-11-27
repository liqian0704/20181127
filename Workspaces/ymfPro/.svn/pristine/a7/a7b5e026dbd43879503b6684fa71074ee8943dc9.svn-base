package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;

/**
 * Description: 支付通知biz
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:46
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppNotifyBiz {

	/**
	 * 向商户推送支付成功订单
	 *
	 * @param request
	 * @return
	 */
	BaseResponse pushPayMsg2APP(PayNotifyRequest request);

	/**
	 * 向商户推送开户通知
	 *
	 * @param request
	 * @return
	 */
	BaseResponse pushOpenMsg2APP(BaseRequest request);
}
