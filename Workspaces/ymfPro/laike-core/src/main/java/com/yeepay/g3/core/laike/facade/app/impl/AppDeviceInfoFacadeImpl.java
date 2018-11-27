package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.DeviceListResponse;
import com.yeepay.g3.facade.laike.facade.app.AppDeviceInfoFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 设备接口
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:32
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class AppDeviceInfoFacadeImpl extends AbstractFacade implements AppDeviceInfoFacade {

	@Override
	public DeviceListResponse findDeviceList(BaseRequest request) {
		return appDeviceBiz.findDeviceList(request);
	}
}
