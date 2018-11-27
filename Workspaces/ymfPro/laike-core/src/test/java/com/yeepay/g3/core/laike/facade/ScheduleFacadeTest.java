package com.yeepay.g3.core.laike.facade;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.facade.LikerScheduleFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 20:39
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class ScheduleFacadeTest extends BaseTest {

	@Autowired
	private LikerScheduleFacade likerScheduleFacade;

	@Test
	public void test() {
		likerScheduleFacade.gatherDailyDeviceApply();
	}
}
