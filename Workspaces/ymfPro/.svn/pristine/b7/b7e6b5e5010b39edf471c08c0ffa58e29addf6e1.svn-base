package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.DeviceService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:38
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class DeviceServiceImpl extends AbstractService implements DeviceService {

	@Override
	public List<LaikeTermDTO> queryByMerchantNo(String merchantNo) {
		try {
			return laikeTermFacade.queryByCustomer(merchantNo);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.DEVICE_QUERY_EXCEPTION);
		}
	}

	@Override
	public void queryByMerchantNoAndSnNo(String merchantNo, String snNo) {
		try {
			if (!laikeTermFacade.validateTerm(merchantNo, snNo)) {
				throw new LaikeSysException(ErrorCode.DEVICE_RELATION_EXCEPTION);
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.DEVICE_QUERY_EXCEPTION);
		}
	}
}
