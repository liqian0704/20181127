package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.AppNotifyBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.enumtype.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description: 支付通知biz实现
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:47
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppNotifyBizImpl extends AbstractBiz implements AppNotifyBiz {

	@Override
	public BaseResponse pushPayMsg2APP(PayNotifyRequest request) {
		//检查账户是否开通
		AccountOpenEntity accountOpenEntity = accountOpenService.findAccount2Push(request.getMerchantNo());
		//配置消息
		PushMsgEntity pushMsgEntity = createPayMsg(request, accountOpenEntity);
		//推送
		pushMsg(pushMsgEntity);
		return new BaseResponse();
	}

	@Override
	public BaseResponse pushOpenMsg2APP(BaseRequest request) {
		//检查用户注册情况
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		//检查账户
		AccountOpenEntity accountOpenEntity = accountOpenService.findExistById(userEntity.getAccountId());
		//配置消息
		PushMsgEntity pushMsgEntity = createOpenMsg(accountOpenEntity);
		//推送
		pushMsg(pushMsgEntity);
		//短信
		notifyService.sendCustomSMS(userEntity.getPhoneNo(), pushMsgEntity.getContent());
		return new BaseResponse();
	}

	/**
	 * 创建支付消息
	 *
	 * @param request
	 * @param accountOpenEntity
	 * @return
	 */
	private PushMsgEntity createPayMsg(PayNotifyRequest request, AccountOpenEntity accountOpenEntity) {
		return createMsg(request.getExternalSystem(), accountOpenEntity.getMemberNo(), request.getContentJson(), MsgTypeEnum.PAY);
	}

	private PushMsgEntity createOpenMsg(AccountOpenEntity accountOpenEntity) {
		String content = "您的开户状态有更新，当前状态为：" + (accountOpenEntity.getAccountType().equals(AccountType.LOL) ?
				accountOpenEntity.getLolOpenStatus().getDisplayName() : accountOpenEntity.getOpenStatus().getDisplayName());
		return createMsg((accountOpenEntity.getAccountType().equals(AccountType.LOL) ? ExternalSystem.ALLIANCE : ExternalSystem.LIKER), accountOpenEntity.getMemberNo(), content, MsgTypeEnum.SYS);
	}

	private PushMsgEntity createMsg(ExternalSystem system, String memberNo, String content, MsgTypeEnum msgTypeEnum) {
		PushMsgEntity pushMsgEntity = new PushMsgEntity();
		pushMsgEntity.setOperator(system.getKey());
		pushMsgEntity.setTitle(msgTypeEnum.getDisplayName());
		pushMsgEntity.setPhoneNumbers(memberNo);
		pushMsgEntity.setContent(content);
		pushMsgEntity.setType(msgTypeEnum);
		pushMsgService.save(pushMsgEntity);
		return pushMsgEntity;
	}

	private void pushMsg(PushMsgEntity pushMsgEntity) {
		String jpushId = jPushService.push2Customers(pushMsgEntity);
		pushMsgEntity.setJpushId(jpushId);
		pushMsgEntity.setPushStatus(PushStatus.SENDED);
		pushMsgEntity.setPushTime(new Date());
		pushMsgEntity.setPushType(PushType.TO_CUSTOMER);
		pushMsgService.updateAfterSend(pushMsgEntity);
	}
}
