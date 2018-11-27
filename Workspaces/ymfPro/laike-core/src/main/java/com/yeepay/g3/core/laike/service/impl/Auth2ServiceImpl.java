package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.service.Auth2Service;
import com.yeepay.g3.facade.auth2.dto.AuthIdCardResultDTO;
import com.yeepay.g3.facade.auth2.dto.RequestIdCardAuthDTO;
import com.yeepay.g3.facade.auth2.facade.Auth2Facade;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/9/20
 * Time: 14:51
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class Auth2ServiceImpl implements Auth2Service {

//	@Reference
	private Auth2Facade auth2Facade;

	@Override
	public String authIdCard(String name, String idCard) {
		RequestIdCardAuthDTO idCardAuthDTO = new RequestIdCardAuthDTO();
		idCardAuthDTO.setName(name);
		idCardAuthDTO.setIdCardNumber(idCard);
		idCardAuthDTO.setRequestFlowId("R"+System.currentTimeMillis());
		idCardAuthDTO.setRequestSystem("laike");
		AuthIdCardResultDTO authIdCardResultDTO = auth2Facade.authIdCard(idCardAuthDTO);
		return authIdCardResultDTO.toString();
	}
}
