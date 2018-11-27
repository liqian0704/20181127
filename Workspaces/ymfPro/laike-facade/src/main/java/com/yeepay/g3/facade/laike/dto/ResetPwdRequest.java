package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * Description: 通过短信重置密码
 * Author: jiawen.huang
 * Date: 16/11/14
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ResetPwdRequest extends BaseRequest {

	/**
	 * 新密码
	 */
	private String newPwd;

	/**
	 * 短信验证码
	 */
	private String smsCode;

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ResetPwdRequest{");
		sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("smsCode='").append(HiddenCodeUtil.hiddenVerifyCode(smsCode)).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
