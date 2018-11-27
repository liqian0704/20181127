package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.ChangePwdRequest;
import com.yeepay.g3.facade.laike.dto.ResetPwdRequest;
import com.yeepay.g3.facade.laike.facade.PwdFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 密码管理
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 18:31
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class PwdFacadeImpl extends AbstractFacade implements PwdFacade {

	@Override
	public BaseResponse changePwd(ChangePwdRequest request) {
		return userAuthBiz.changePwd(request);
	}

	@Override
	public BaseResponse findPwdBySms(BaseRequest request) {
		return userAuthBiz.findPwdBySms(request);
	}

	@Override
	public BaseResponse resetPwdBySms(ResetPwdRequest request) {
		return userAuthBiz.resetPwdBySms(request);
	}
}
