package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.alliance.enums.member.MerType;

import java.util.Date;

/**
 * Description: 联盟服务
 * Author: jiawen.huang
 * Date: 2017/6/21
 * Time: 19:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface AllianceService {

	/**
	 * 检查邀请码
	 *
	 * @param inviteCode
	 * @return 邀请码所属的商户
	 * @throws com.yeepay.g3.facade.laike.exception.LaikeSysException
	 */
	String validateInviteCode(String inviteCode);

	/**
	 * 入盟
	 *
	 * @param inviteCode   邀请码
	 * @param merchantNo   商户号
	 * @param merchantName 商户名
	 * @param sales        销售名
	 * @param createTime   用户创建时间
	 * @param merType      类型(拓展来客CHILD还是盟友ALLY)
	 * @return 推广邀请码
	 */
	String joinAlliance(String inviteCode, String merchantNo, String merchantName
			, String sales, Date createTime, MerType merType);

	/**
	 * 查询盟主
	 *
	 * @param inviteCode
	 * @return
	 */
	String getLord(String inviteCode);

    /**
     * 邀请码的顶层是否为官网盟主
     *
     * @param inviteCode
     * @return
     */
    boolean isOfficialLord(String inviteCode);
}
