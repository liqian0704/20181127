package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.boss.PushMsgRequest;
import com.yeepay.g3.facade.laike.dto.boss.PushMsgResponse;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/11/9
 * @Time: 下午3:53
 */
public interface PushMsgBiz {

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
	PushMsgResponse editMsg(PushMsgRequest requestDTO);

	/**
	 * 逻辑作废
	 *
	 * @param messageNo
	 * @return
	 */
	PushMsgResponse delete(String messageNo, String operator);

	/**
	 * 定向发送消息
	 *
	 * @param requestDTO
	 * @return
	 */
	PushMsgResponse push2Customers(PushMsgRequest requestDTO);

	/**
	 * 广播发送消息
	 *
	 * @param requestDTO
	 * @return
	 */
	PushMsgResponse broadcastMsg(PushMsgRequest requestDTO);

	/**
	 * 指定tag发送消息
	 *
	 * @param requestDTO
	 * @return
	 */
	PushMsgResponse pushMsg(PushMsgRequest requestDTO);
}
