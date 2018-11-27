package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.PushMsgBiz;
import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import com.yeepay.g3.facade.laike.dto.boss.PushMsgRequest;
import com.yeepay.g3.facade.laike.dto.boss.PushMsgResponse;
import com.yeepay.g3.facade.laike.enumtype.PushType;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description: 消息biz实现d
 * Author: jiawen.huang
 * Date: 16/11/09
 * Time: 17:01
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class PushMsgBizImpl extends AbstractBiz implements PushMsgBiz {

	@Override
	public PushMsgResponse createMsg(PushMsgRequest requestDTO) {
		PushMsgEntity entity = pushMsgService.save(convert2Entity(requestDTO));
		return convert2DTO(entity);
	}

	@Override
	public PushMsgResponse editMsg(PushMsgRequest requestDTO) {
		pushMsgService.findByMessageNo(requestDTO.getMessageNo());
		PushMsgEntity entity = convert2Entity(requestDTO);
		pushMsgService.updateBeforeSend(entity);
		return convert2DTO(entity);
	}

	@Override
	public PushMsgResponse delete(String messageNo, String operator) {
		PushMsgEntity entity = pushMsgService.findByMessageNo(messageNo);
		entity.setOperator(operator);
		pushMsgService.delete(entity);
		return convert2DTO(entity);
	}

	@Override
	public PushMsgResponse push2Customers(PushMsgRequest requestDTO) {
		PushMsgEntity message = pushMsgService.findByMessageNo(requestDTO.getMessageNo());
		String jpushId = jPushService.push2Customers(message);
		message.setJpushId(jpushId);
		message.setOperator(requestDTO.getOperator());
		message.setPushTime(new Date());
		message.setPushType(PushType.TO_CUSTOMER);
		pushMsgService.updateAfterSend(message);
		return convert2DTO(message);
	}

	@Override
	public PushMsgResponse broadcastMsg(PushMsgRequest requestDTO) {
		PushMsgEntity message = pushMsgService.findByMessageNo(requestDTO.getMessageNo());
		String jpushId = jPushService.broadcastMsg(message);
		message.setJpushId(jpushId);
		message.setOperator(requestDTO.getOperator());
		message.setPushTime(new Date());
		message.setPushType(PushType.BROADCAST);
		pushMsgService.updateAfterSend(message);
		return convert2DTO(message);
	}

	@Override
	public PushMsgResponse pushMsg(PushMsgRequest requestDTO) {
		PushMsgEntity message = pushMsgService.findByMessageNo(requestDTO.getMessageNo());
		String jpushId = jPushService.pushMsg(message);
		message.setJpushId(jpushId);
		message.setOperator(requestDTO.getOperator());
		message.setPushTime(new Date());
		message.setPushType(PushType.T0_TAG);
		pushMsgService.updateAfterSend(message);
		return convert2DTO(message);
	}


	private PushMsgEntity convert2Entity(PushMsgRequest requestDTO) {
		PushMsgEntity entity = new PushMsgEntity();
		entity.setOperator(requestDTO.getOperator());
		entity.setTitle(requestDTO.getTitle());
		entity.setMessageNo(requestDTO.getMessageNo());
		entity.setAppVersionId(requestDTO.getAppVersionId());
		entity.setContent(requestDTO.getContent());
		entity.setLifeStart(requestDTO.getLifeStart());
		entity.setLifeEnd(requestDTO.getLifeEnd());
		entity.setManufacturer(requestDTO.getManufacturer());
		entity.setModel(requestDTO.getModel());
		entity.setPhoneNumbers(requestDTO.getPhoneNumbers());
		entity.setRole(requestDTO.getRole());
		entity.setType(requestDTO.getType());
		entity.setUrl1(requestDTO.getUrl1());
		entity.setUrl2(requestDTO.getUrl2());
		return entity;
	}

	private PushMsgResponse convert2DTO(PushMsgEntity entity) {
		PushMsgResponse response = new PushMsgResponse();
		response.setOperator(entity.getOperator());
		response.setTitle(entity.getTitle());
		response.setMessageNo(entity.getMessageNo());
		response.setAppVersionId(entity.getAppVersionId());
		response.setContent(entity.getContent());
		response.setLifeStart(entity.getLifeStart());
		response.setLifeEnd(entity.getLifeEnd());
		response.setManufacturer(entity.getManufacturer());
		response.setModel(entity.getModel());
		response.setPhoneNumbers(entity.getPhoneNumbers());
		response.setRole(entity.getRole());
		response.setType(entity.getType());
		response.setUrl1(entity.getUrl1());
		response.setUrl2(entity.getUrl2());
		return response;
	}
}
