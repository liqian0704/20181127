package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;

/**
 * Description: 用户鉴权biz接口
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 16:25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface UserAuthBiz {

	/**
	 * 发送注册短信验证码
	 *
	 * @param requestDTO
	 * @return
	 */
	BaseResponse sendRegisterSms(SendRegisterSmsRequest requestDTO);

	/**
	 * 验证注册短信验证码并且注册
	 *
	 * @param requestDTO
	 * @return
	 */
	BaseResponse verifyRegisterSms(VerifyRegisterSmsRequest requestDTO);

	/**
	 * 用户登陆
	 *
	 * @param requestDTO
	 * @return
	 */
	LoginResponse login(LoginRequest requestDTO);

	/**
	 * 注销
	 *
	 * @param reuqest
	 * @return
	 */
	BaseResponse logout(LogoutReuqest reuqest);

	/**
	 * 刷新tk
	 *
	 * @return
	 */
	RefreshTKResponse refreshTK(RefreshTKRequest request);

	/**
	 * 修改密码
	 *
	 * @param request
	 * @return
	 */
	BaseResponse changePwd(ChangePwdRequest request);

	/**
	 * 发送找回密码短信
	 *
	 * @param request
	 * @return
	 */
	BaseResponse findPwdBySms(BaseRequest request);

	/**
	 * 找回密码验证短信和重置
	 *
	 * @param request
	 * @return
	 */
	BaseResponse resetPwdBySms(ResetPwdRequest request);

    /**
     * 检查手机号是否注册
     *
     * @param phoneNo
     * @return
     */
    BaseResponse checkRegister(String phoneNo, AppSourceEnum appSourceEnum);
}
