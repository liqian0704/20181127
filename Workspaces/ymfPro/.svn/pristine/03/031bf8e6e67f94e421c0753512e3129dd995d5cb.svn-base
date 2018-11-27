package com.yeepay.g3.core.laike.facade;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.DeviceListResponse;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.facade.app.AppDeviceInfoFacade;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 21:15
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class AppDeviceFacadeTest extends BaseTest {

	@Autowired
	private AppDeviceInfoFacade appDeviceInfoFacade;

	@Test
	public void findList() {
		BaseRequest request = new BaseRequest();
		request.setMemberNo("212468315183");
		DeviceListResponse response = appDeviceInfoFacade.findDeviceList(request);
		Assert.assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
	}

}
