package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.SmsCodeEntity;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;

/**
 * Description: 验证码service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface SmsCodeService {

	/**
	 * 获取未过期且有效的验证码，没有创建一个
	 *
	 * @param phoneNo
	 * @param smsType
	 * @return
	 */
	SmsCodeEntity getAvaliable(String phoneNo, SmsTypeEnum smsType);

	/**
	 * 验证验证码
	 *
	 * @param phoneNo
	 * @param smsType
	 * @param smsCode
	 */
	void verify(String phoneNo, SmsTypeEnum smsType, String smsCode);
}
