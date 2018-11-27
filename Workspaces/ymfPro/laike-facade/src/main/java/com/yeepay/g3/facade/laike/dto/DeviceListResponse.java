package com.yeepay.g3.facade.laike.dto;

import java.util.List;

/**
 * Description: 设备列表
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:34
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class DeviceListResponse extends BaseResponse {

	List<DeviceInfo> list;

	public List<DeviceInfo> getList() {
		return list;
	}

	public void setList(List<DeviceInfo> list) {
		this.list = list;
	}
}
