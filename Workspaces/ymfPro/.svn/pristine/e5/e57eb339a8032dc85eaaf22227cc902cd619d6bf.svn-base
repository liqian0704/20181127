package com.yeepay.g3.core.laike.event;

import com.yeepay.g3.core.laike.biz.AppNotifyBiz;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 异步发消息
 * Author: jiawen.huang
 * Date: 17/1/3
 * Time: 18:46
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class SendAppMsgHandler extends BaseEventListener {

	private static Logger LOGGER = LoggerFactory.getLogger(SendAppMsgHandler.class);

	@Autowired
	private AppNotifyBiz appNotifyBiz;

	@Override
	public String getListenedEventName() {
		return ConstantUtil.SEND_APP_MSG_EVENT;
	}

	@Override
	public void doAction(Object... objects) {
		LOGGER.info("[laike_sys] - [入参] - [SendAppMsgHandler] - [" + objects + "]");
		long startTime = System.currentTimeMillis();
		try {
			BaseRequest request = new BaseRequest();
			String memberNo = (String) objects[0];
			request.setMemberNo(memberNo);
			appNotifyBiz.pushOpenMsg2APP(request);
		} catch (Throwable e) {
			LOGGER.error("[laike_sys] - [异步调用异常] - [SendAppMsgHandler] - errorMsg:[" + e + "]");
		} finally {
			long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[laike_sys] - [用时] - [SendAppMsgHandler] - 耗时[" + time + "]毫秒");
		}
	}
}
