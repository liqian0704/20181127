package com.yeepay.g3.facade.laike.dto.alliance;

import com.yeepay.g3.facade.laike.dto.QueryBaseRequest;
import com.yeepay.g3.facade.laike.enumtype.AccountType;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;

/**
 * Description: 联盟查询请求
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 11:54
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AllianceRequest extends QueryBaseRequest {

	/**
	 * 账户类型
	 */
	private AccountType accountType;

	/**
	 * 开通状态
	 */
	private BoolEnum openStatus;

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BoolEnum getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(BoolEnum openStatus) {
		this.openStatus = openStatus;
	}
}
