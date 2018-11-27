package com.yeepay.g3.core.laike.event;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.service.AgentRelationService;
import com.yeepay.g3.core.laike.service.NotifyService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 绑定业务员
 * Author: jiawen.huang
 * Date: 17/1/3
 * Time: 18:46
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class BindBizManHandler extends BaseEventListener {

	private static Logger LOGGER = LoggerFactory.getLogger(BindBizManHandler.class);

	@Autowired
	private AgentRelationService agentRelationService;

	@Autowired
	private NotifyService notifyService;

	@Override
	public String getListenedEventName() {
		return ConstantUtil.BIND_BIZ_MAN_EVENT;
	}

	@Override
	public void doAction(Object... objects) {
		long startTime = System.currentTimeMillis();
		try {
            AccountOpenEntity accountOpenEntity = (AccountOpenEntity) objects[0];
            LOGGER.info("[laike_sys] - [入参] - [BindBizManHandler] - [" + toString(accountOpenEntity) + "]");
            agentRelationService.bindBusinessMan(accountOpenEntity);
        } catch (Throwable e) {
            String message = "objects:" + toString((AccountOpenEntity) objects[0]) + "\n" + "e:" + e.getMessage();
            notifyService.sendErrorEmail("入网绑定代理商业务员异常", message);
            LOGGER.error("[laike_sys] - [异步调用异常] - [BindBizManHandler] - errorMsg:", e);
        } finally {
            long time = (System.currentTimeMillis() - startTime);
			LOGGER.info("[laike_sys] - [用时] - [BindBizManHandler] - 耗时[" + time + "]毫秒");
		}
	}

	private String toString(AccountOpenEntity accountOpenEntity) {
		StringBuffer sb = new StringBuffer();
		sb.append("inviteType=[" + accountOpenEntity.getInviteType() + "]")
				.append("inviteCode=[" + accountOpenEntity.getInviteCode() + "]")
				.append("merchantNo=[" + accountOpenEntity.getMerchantNo() + "]")
				.append("businessMan=[" + accountOpenEntity.getBusinessMan() + "]");
		return sb.toString();
	}
}
