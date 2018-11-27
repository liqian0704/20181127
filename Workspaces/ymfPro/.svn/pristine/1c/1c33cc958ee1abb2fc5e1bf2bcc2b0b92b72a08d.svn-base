package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.annotations.NotEmpty;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.SendRegisterSmsRequest;
import com.yeepay.g3.facade.laike.dto.VerifyRegisterSmsRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;

/**
 * Description: 推广facade
 * Author: jiawen.huang
 * Date: 2017/6/22
 * Time: 17:37
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface LikerSpreadFacade {

	/**
	 * 发送注册短信验证码
	 *
	 * @param reuqest
	 * @return
	 */
	BaseResponse sendRegisterSms(SendRegisterSmsRequest reuqest);

	/**
	 * 验证注册短信验证码并且注册
	 *
	 * @param reuqest
	 * @return
	 */
	BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest reuqest);

    /**
     * 检查手机号是否注册
     *
     * @param phoneNo
     * @return status=FAILURE && errorCode=L10013未注册;status=SUCCESS 已注册
     */
    BaseResponse checkRegister(@NotEmpty(value = "手机号") String phoneNo,
                               @NotEmpty(value = "来源") AppSourceEnum appSourceEnum);
}
