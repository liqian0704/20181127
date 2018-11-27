package com.yeepay.g3.core.laike.service.impl;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.JPushService;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.enumtype.PlatformEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: 极光推送服务层实现
 * Author: jiawen.huang
 * Date: 16/3/2
 * Time: 12:05
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class JPushServiceImpl extends AbstractService implements JPushService {

	public static String title = "您有新消息";

	private static Logger LOGGER = LoggerFactory.getLogger(JPushServiceImpl.class);

	@Override
	public String push2Customers(PushMsgEntity message) {
		PushResult pushResult = null;
		PushClient pushClient;
		try {
			if (message.getOperator().equals(ExternalSystem.ALLIANCE.getKey())) {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_KEY));
			} else {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_KEY));
			}
			AudienceTarget audienceAlias = AudienceTarget.alias(Lists.newArrayList(message.getPhoneNumbers().split(",")));//自动去空值
			Audience audience = Audience.newBuilder().addAudienceTarget(audienceAlias).build();
			PushPayload pushPayload = createPushPayload(message, audience);
			LOGGER.info("JPush 推送参数======" + pushPayload.toString());
			pushResult = pushClient.sendPush(pushPayload);
			LOGGER.info("JPush 推送结果======" + pushResult.toString());
		} catch (Exception e) {
			jPushExpHandler(e);
		}
		return String.valueOf(pushResult.msg_id);
	}

	@Override
	public String broadcastMsg(PushMsgEntity message) {
		PushResult pushResult = null;
		PushClient pushClient;
		try {
			if (message.getOperator().equals(ExternalSystem.ALLIANCE.getKey())) {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_KEY));
			} else {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_KEY));
			}
			PushPayload pushPayload = createPushPayload(message, Audience.all());
			LOGGER.info("JPush 推送参数======" + pushPayload.toString());
			pushResult = pushClient.sendPush(pushPayload);
			LOGGER.info("JPush 推送结果======" + pushResult.toString());
		} catch (Exception e) {
			jPushExpHandler(e);
		}
		return String.valueOf(pushResult.msg_id);
	}

	@Override
	public String pushMsg(PushMsgEntity message) {
		PushResult pushResult = null;
		PushClient pushClient;
		try {
			//模糊推送
			if (message.getOperator().equals(ExternalSystem.ALLIANCE.getKey())) {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_JPUSH_APP_KEY));
			} else {
				pushClient = new PushClient((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_SECRET),
						(String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_JPUSH_APP_KEY));
			}
			List tags = Lists.newArrayList();
			if (StringUtils.isNotEmpty(message.getManufacturer())) {
				tags.add(message.getManufacturer());
			} else if (StringUtils.isNotEmpty(message.getModel())) {
				tags.add(message.getModel());
			}
			AudienceTarget audienceTag = AudienceTarget.tag_and(tags);
			Audience audience = Audience.newBuilder().addAudienceTarget(audienceTag).build();
			PushPayload pushPayload = createPushPayload(message, audience);
			LOGGER.info("JPush 推送参数======" + pushPayload.toString());
			pushResult = pushClient.sendPush(pushPayload);
			LOGGER.info("JPush 推送结果======" + pushResult.toString());
		} catch (Exception e) {
			jPushExpHandler(e);
		}
		return String.valueOf(pushResult.msg_id);
	}

	private PushPayload createPushPayload(PushMsgEntity message, Audience audience) {
		Platform platform = null;
		if (PlatformEnum.IOS.equals(message.getPlatformEnum())) {
			platform = Platform.ios();
		} else if (PlatformEnum.ANDROID.equals(message.getPlatformEnum())) {
			platform = Platform.android();
		} else {
			platform = Platform.all();
		}
		String msg = message.getTitle();
		Map<String, String> extras = Maps.newHashMap();
		extras.put("content", message.getContent());
		extras.put("msgNo", message.getMessageNo());
		if (null != message.getLifeStart()) {
			extras.put("lifeStart", DateUtils.LONG_DATE_FORMAT.format(message.getLifeStart()));
		}
		if (null != message.getLifeStart()) {
			extras.put("lifeEnd", DateUtils.LONG_DATE_FORMAT.format(message.getLifeEnd()));
		}
		extras.put("msgNo", message.getMessageNo());
		extras.put("type", message.getType().getValue());
		if (null != message.getAppVersionId()) {
			extras.put("versionId", message.getAppVersionId());
		}
		Notification notification = Notification.newBuilder()
				.addPlatformNotification(AndroidNotification.newBuilder()
						.setAlert(msg).setTitle(title).addExtras(extras).build())
				.addPlatformNotification(IosNotification.newBuilder()
						.setAlert(msg).addExtras(extras).build()).build();
		Options options = Options.newBuilder().setApnsProduction(true).setTimeToLive(86400).build();//保留7天,7天内在线就推送
		options.setApnsProduction((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_APNS_PRODUCTION));
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(platform)
				.setAudience(audience).setNotification(notification).setOptions(options).build();
		return pushPayload;
	}

	/**
	 * 极光异常处理
	 *
	 * @param e
	 */
	private void jPushExpHandler(Exception e) {
		LOGGER.warn("极光异常处理: ", e);
		if (e instanceof APIRequestException) {
			APIRequestException apiRequestException = (APIRequestException) e;
			if (1000 == apiRequestException.getErrorCode()
					|| 1030 == apiRequestException.getErrorCode()) {//服务器端内部逻辑错误，请稍后重试。
				throw new LaikeSysException(ErrorCode.JPUSH_INNER_EXCEPTION);
			}
			if (1003 == apiRequestException.getErrorCode()) {//参数值不合法，tag，alias，registration_id有空值
				throw new LaikeSysException(ErrorCode.JPUSH_ILLEGAL_ARGUMENT);
			}
			if (1011 == apiRequestException.getErrorCode()) {//没有满足条件（会员号）的推送目标
				throw new LaikeSysException(ErrorCode.JPUSH_USER_UNFIND);
			}
			if (1005 == apiRequestException.getErrorCode()) {//消息体，Android4000字节，iOS2000字节
				throw new LaikeSysException(ErrorCode.JPUSH_STRUCTURE_OVER_LIMIT);
			}
			throw new LaikeSysException(ErrorCode.JPUSH_SYS_API_ERROR);
		} else if (e instanceof APIConnectionException) {
			throw new LaikeSysException(ErrorCode.JPUSH_SYS_CONNECT_ERROR);
		} else {
			throw new LaikeSysException(ErrorCode.JPUSH_SYS_UNKNOW_ERROR);
		}
	}
}
