package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.PushMsgEntity;

/**
 * @Description: 本地消息service
 * @Author: jiawen.huang
 * @Date: 16/10/26
 * @Time: 下午2:35
 */
public interface PushMsgService {

	/**
	 * 保存，不发送
	 *
	 * @param entity
	 * @return
	 */
	PushMsgEntity save(PushMsgEntity entity);

	/**
	 * 推送前修改消息
	 *
	 * @param entity
	 */
	void updateBeforeSend(PushMsgEntity entity);

	/**
	 * 推送后更新
	 *
	 * @param entity
	 */
	void updateAfterSend(PushMsgEntity entity);

	/**
	 * 逻辑删除
	 *
	 * @param entity
	 */
	void delete(PushMsgEntity entity);

	/**
	 * 根据唯一消息号查找
	 *
	 * @param messageNo
	 * @return
	 */
    PushMsgEntity findByMessageNo(String messageNo);
}
