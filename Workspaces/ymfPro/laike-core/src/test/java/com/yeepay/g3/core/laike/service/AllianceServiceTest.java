package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 2017/6/22
 * Time: 11:10
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AllianceServiceTest extends BaseTest {

	@Autowired
	private AllianceService allianceService;

	@Test
	public void validateInviteCode() {
		Assert.assertEquals(allianceService.validateInviteCode("Y573464"), "10040011542");
    }

    @Test
    public void isOfficialLord() {
        System.err.println(new Gson().toJson(allianceService.isOfficialLord("Y573435")));
    }
}
