package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * Description: 通知短信service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:49
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface NotifyService {

	/**
	 * 发送验证码短信
	 *
	 * @param phoneNo
	 * @param code
	 * @param smsTypeEnum
	 */
	void sendSmsRandom(String phoneNo, String code, SmsTypeEnum smsTypeEnum);

	/**
	 * 发送自定义短信
	 *
	 * @param phoneNo 手机号
	 * @param content 短信内容
	 */
	void sendCustomSMS(String phoneNo, String content);

	/**
	 * 群发自定义短信
	 *
	 * @param phoneNos 手机号们
	 * @param content  群发内容
	 */
	void sendCustomSMS(List<String> phoneNos, String content);

	/**
	 * 发送异常邮件
	 *
	 * @param title
	 * @param message
	 */
	void sendErrorEmail(String title, String message);

	/**
	 * 发送运营业务邮件
	 *
	 * @param contact         联系人
	 * @param messages        邮件内容
	 * @param attatchmentName 附件名称
	 * @param attatchments    附件，邮件中附件用${attatchName}表示
	 */
	void sendOperationEmail(ConfigEnum contact, Map<String, Object> messages, String attatchmentName, byte[] attatchments);
}
