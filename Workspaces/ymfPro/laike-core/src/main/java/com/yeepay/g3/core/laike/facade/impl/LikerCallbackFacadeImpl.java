package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.facade.LikerCallbackFacade;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 来客回调
 * Author: jiawen.huang
 * Date: 16/12/15
 * Time: 17:54
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class LikerCallbackFacadeImpl extends AbstractFacade implements LikerCallbackFacade {

	@Override
	public BaseResponse openAccResult(Map<String, String> request) {
		return openAccountBiz.callbackResult(request);
	}
}
