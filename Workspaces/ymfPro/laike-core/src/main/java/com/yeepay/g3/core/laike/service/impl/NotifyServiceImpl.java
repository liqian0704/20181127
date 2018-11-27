package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.notifier.dto.NotifyFeature;
import com.yeepay.g3.facade.notifier.dto.NotifyResultDTO;
import com.yeepay.g3.facade.notifier.exception.FrequencyLimitedException;
import com.yeepay.g3.facade.notifier.exception.InvalidRecipientException;
import com.yeepay.g3.utils.common.encrypt.Digest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:50
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class NotifyServiceImpl extends AbstractService implements NotifyService {

	private final String NOTIFY_APP_NAME = "liker-app";

	private final String NOTIFY_SECRET_KEY = "uiwhiw";

	private final String SMS_RULE_NAME = "liker-app-rule";

	private final String EMAIL_RULE_NAME = "liker-app-sys-error-email";

	private final String OPERATION_EMAIL_RULE_NAME = "liker-app-operation-email";

	@Override
	public void sendSmsRandom(String phoneNo, String code, SmsTypeEnum smsTypeEnum) {
		String content = "";
		if (smsTypeEnum.equals(SmsTypeEnum.REGISTER)) {
			content = "您的注册验证码为：" + code + "。此验证码只用于注册，有效期5分钟，请不要把验证码泄露给其他人。";
		} else {
			content = "您的验证码为：" + code + "。此验证码只用于找回密码，有效期5分钟，请不要把验证码泄露给其他人。";
		}
		sendCustomSMS(phoneNo, content);
	}

	@Override
	public void sendCustomSMS(String phoneNo, String content) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("message", content);
		List<String> list = Lists.newArrayList();
		list.add(phoneNo);
		notify(SMS_RULE_NAME, list, map, null);
	}

	@Override
	public void sendCustomSMS(List<String> phoneNos, String content) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("message", content);
		notify(SMS_RULE_NAME, phoneNos, map, null);
	}

	@Override
	public void sendOperationEmail(ConfigEnum contact, Map<String, Object> messages, String attatchmentName, byte[] attatchments) {
		List<String> recipientList = convertReceiptsList(contact);
		NotifyFeature feature = null;
		if (null != attatchments) {
			feature = new NotifyFeature();
			feature.addAttatchment(attatchmentName, attatchments);
		}
		notify(OPERATION_EMAIL_RULE_NAME, recipientList, messages, feature);
	}

	@Override
	public void sendErrorEmail(String title, String message) {
		Map<String, Object> messages = Maps.newHashMap();
		messages.put("title", title);
		messages.put("message", message);
		List<String> recipientList = convertReceiptsList(ConfigEnum.LIKER_SYS_NOTIFY_EMAIL);
		notify(EMAIL_RULE_NAME, recipientList, messages, null);
	}

	/**
	 * 通用短信、邮件发送
	 *
	 * @param notifyRuleName 后台配置的通知规则
	 * @param recipientList  接受人
	 * @param messages       内容
	 * @param notifyFeature  邮件附件
	 * @return
	 */
	private NotifyResultDTO notify(String notifyRuleName, List<String> recipientList,
								   Map<String, Object> messages, NotifyFeature notifyFeature) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(NOTIFY_APP_NAME);
			sb.append(notifyRuleName);
			sb.append(org.apache.commons.lang3.StringUtils.join(recipientList, ","));
			sb.append(NOTIFY_SECRET_KEY);
			if (null == notifyFeature) {
				return notifyFacade.notify(NOTIFY_APP_NAME,
						Digest.md5Digest(sb.toString()), notifyRuleName, recipientList, messages);
			} else {
				return notifyFacade.notify(NOTIFY_APP_NAME,
						Digest.md5Digest(sb.toString()), notifyRuleName, recipientList, messages, notifyFeature);
			}
		} catch (FrequencyLimitedException e) {//FrequencyLimitedException
			throw new LaikeSysException(ErrorCode.NOTIFY_FREQUENCY_LIMIT, e);
		} catch (InvalidRecipientException e) {
			throw new LaikeSysException(ErrorCode.NOTIFY_INVALID_RECIPIENT, e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.NOTIFY_UNKNOWN_EXCEPTION, e);
		}
	}

	private List<String> convertReceiptsList(ConfigEnum contact) {
		List<String> list = (List<String>) ConfigUtils.getSysConfigParam(contact);
		List<String> recipientList = Lists.newArrayList();
		for (String recipient : list) {
			if (recipient.indexOf('@') == -1) {
				recipientList.add(recipient + "@yeepay.com");
			} else {
				recipientList.add(recipient);
			}
		}
		return recipientList;
	}
}
