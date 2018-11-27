package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.boss.PushMsgRequest;
import com.yeepay.g3.facade.laike.dto.boss.PushMsgResponse;
import com.yeepay.g3.facade.laike.facade.MsgBossManageFacade;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/11/9
 * @Time: 下午3:52
 */
@Service
public class MsgBossManageFacadeImpl extends AbstractFacade implements MsgBossManageFacade {

	@Override
	public PushMsgResponse createMsg(PushMsgRequest requestDTO) {
		return pushMsgBiz.createMsg(requestDTO);
	}

	@Override
	public PushMsgResponse editMsg(PushMsgRequest requestDTO, String messageNo) {
		return pushMsgBiz.editMsg(requestDTO);
	}

	@Override
	public PushMsgResponse delete(String messageNo, String operator) {
		return pushMsgBiz.delete(messageNo, operator);
	}

	@Override
	public PushMsgResponse push2Customers(String messageNo) {
		PushMsgRequest request = new PushMsgRequest();
		request.setMessageNo(messageNo);
		return pushMsgBiz.push2Customers(request);
	}

	@Override
	public PushMsgResponse broadcastMsg(String messageNo) {
		PushMsgRequest request = new PushMsgRequest();
		request.setMessageNo(messageNo);
		return pushMsgBiz.broadcastMsg(request);
	}

	@Override
	public PushMsgResponse pushMsg(String messageNo) {
		PushMsgRequest request = new PushMsgRequest();
		request.setMessageNo(messageNo);
		return pushMsgBiz.pushMsg(request);
	}
}
