package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: AgentServiceTest
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 14:51
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AccountManageServiceTest extends BaseTest {

	@Autowired
	private AccountManageService accountManageService;

	@Test
	public void test() {
		System.out.print(accountManageService.getBalance("10040011560"));
	}
}
