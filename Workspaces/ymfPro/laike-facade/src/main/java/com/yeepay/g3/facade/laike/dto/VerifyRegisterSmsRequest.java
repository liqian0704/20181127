package com.yeepay.g3.facade.laike.dto;


import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * @Description:新用户注册短信验证请求类
 * @Author: zhaoyu.cui
 * @Date: 16/9/5
 * @Time: 下午3:37
 */
public class VerifyRegisterSmsRequest extends BaseRequest {

	/**
	 * 密码密文
	 */
	private String pwd;

	/**
	 * 短信验证码
	 */
	private String smsCode;

	/**
	 * imei
	 */
	private String imei;

	/**
	 * 坐标
	 */
	private String location;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("VerifyRegisterSmsRequest{");
		sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("location='").append(HiddenCodeUtil.hiddenMobile(location)).append('\'');
		sb.append("smsCode='").append(HiddenCodeUtil.hiddenVerifyCode(smsCode)).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
