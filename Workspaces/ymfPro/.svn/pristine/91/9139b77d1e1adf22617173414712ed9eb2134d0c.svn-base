package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.annotations.NotEmpty;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.SendRegisterSmsRequest;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.facade.LikerSpreadFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 推广
 * Author: jiawen.huang
 * Date: 2017/6/22
 * Time: 19:07
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class LikerSpreadFacadeImpl extends AbstractFacade implements LikerSpreadFacade {

	@Override
	public BaseResponse sendRegisterSms(SendRegisterSmsRequest reuqest) {
		return userAuthBiz.sendRegisterSms(reuqest);
	}

	@Override
	public BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest reuqest) {
		return userAuthBiz.verifyRegisterSms(reuqest);
	}

    @Override
    public BaseResponse checkRegister(@NotEmpty(value = "手机号") String phoneNo, @NotEmpty(value = "手机号") AppSourceEnum appSourceEnum) {
        return userAuthBiz.checkRegister(phoneNo, appSourceEnum);
    }
}
