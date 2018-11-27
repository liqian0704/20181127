package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.PushMsgEntity;

/**
 * Description: 极光推送服务层接口
 * Author: jiawen.huang
 * Date: 16/3/2
 * Time: 12:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface JPushService {

	/**
	 * 定点发布消息
	 *
	 * @param message
	 * @return messageNo
	 */
	String push2Customers(PushMsgEntity message);

	/**
	 * 广播消息
	 *
	 * @param message
	 * @return messageNo
	 */
	String broadcastMsg(PushMsgEntity message);

	/**
	 * 带条件的推送消息
	 *
	 * @param message
	 * @return messageNo
	 */
	public String pushMsg(PushMsgEntity message);
}
