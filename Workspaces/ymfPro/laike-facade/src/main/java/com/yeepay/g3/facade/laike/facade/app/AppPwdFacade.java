package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.ChangePwdRequest;
import com.yeepay.g3.facade.laike.dto.ResetPwdRequest;

/**
 * Description:密码管理
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 18:28
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppPwdFacade {

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
}
