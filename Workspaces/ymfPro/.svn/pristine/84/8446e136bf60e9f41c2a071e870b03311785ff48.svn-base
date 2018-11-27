package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.dto.boss.PushMsgRequest;
import com.yeepay.g3.facade.laike.dto.boss.PushMsgResponse;

/**
 * Description: 后台消息管理用facade
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface MsgBossManageFacade {

	/**
	 * 创建消息
	 *
	 * @param requestDTO
	 * @return
	 */
	PushMsgResponse createMsg(PushMsgRequest requestDTO);

	/**
	 * 修改消息
	 *
	 * @param requestDTO
	 * @return
	 */
	PushMsgResponse editMsg(PushMsgRequest requestDTO, String messageNo);

	/**
	 * 逻辑作废
	 *
	 * @param messageNo
	 * @param operator  操作员
	 * @return
	 */
	PushMsgResponse delete(String messageNo, String operator);

	/**
	 * 定向发送消息
	 *
	 * @param messageNo
	 * @return
	 */
	PushMsgResponse push2Customers(String messageNo);

	/**
	 * 广播发送消息
	 *
	 * @param messageNo
	 * @return
	 */
	PushMsgResponse broadcastMsg(String messageNo);

	/**
	 * 指定tag发送消息
	 *
	 * @param messageNo
	 * @return
	 */
	PushMsgResponse pushMsg(String messageNo);

}
