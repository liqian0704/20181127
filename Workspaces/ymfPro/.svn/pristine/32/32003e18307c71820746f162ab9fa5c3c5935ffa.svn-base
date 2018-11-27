package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * @Description:用户登陆请求类
 * @Author: zhaoyu.cui
 * @Date: 16/9/6
 * @Time: 上午11:00
 */
public class LoginRequest extends BaseRequest {

	/**
	 * 登陆密码密文
	 */
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("LoginRequest{");
		sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
