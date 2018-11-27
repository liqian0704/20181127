package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.enumtype.InviteType;

/**
 * Description: 代理关系服务
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 19:36
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AgentRelationService {

	/**
	 * 查询代理关系和产品
	 *
	 * @param inviteType 邀请方式
	 * @param inviteCode 邀请码/二维码id
	 */
	String checkAgentBiz(InviteType inviteType, String inviteCode);

	/**
	 * 根据邀请方式和码检查是代理还是销售直销
	 *
	 * @param accountOpenEntity
	 * @return 更新AccountOpenEntity的邀请方式和代理商号或者直销销售号
	 */
	AccountOpenEntity checkInviteBiz(AccountOpenEntity accountOpenEntity);

	/**
	 * 绑定业务员
	 *
	 * @param accountOpenEntity
	 */
	void bindBusinessMan(AccountOpenEntity accountOpenEntity);

    /**
     * 检查代理商秒到开通状态
     *
     * @param merchantNo
     * @return
     */
    boolean checkAgentS0Open(String merchantNo);
}
