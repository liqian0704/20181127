package com.yeepay.g3.core.laike.biz.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.AppDeviceBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.DeviceInfo;
import com.yeepay.g3.facade.laike.dto.DeviceListResponse;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 设备业务类
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:37
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class AppDeviceBizImpl extends AbstractBiz implements AppDeviceBiz {

	@Override
	public DeviceListResponse findDeviceList(BaseRequest request) {
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
		List<LaikeTermDTO> list = deviceService.queryByMerchantNo(accountOpenEntity.getMerchantNo());
		return convertDTO(list);
	}

	private DeviceListResponse convertDTO(List<LaikeTermDTO> list) {
		DeviceListResponse response = new DeviceListResponse();
		List<DeviceInfo> responseList = Lists.newArrayList();
		for (LaikeTermDTO term : list) {
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo.setSnSerial(term.getSnSerial());
			deviceInfo.setManufacture(term.getManufacture());
			deviceInfo.setManufactureDisplayName(term.getManufactureDisplayName());
			deviceInfo.setTermType(term.getTermType());
			deviceInfo.setTermTypeDisplayName(term.getTermTypeDisplayName());
			responseList.add(deviceInfo);
		}
		response.setList(responseList);
		return response;
	}
}
