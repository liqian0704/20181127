package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

import java.io.Serializable;

/**
 * Description: facade请求对象基类
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 11:54
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	protected String requestNo;

	/**
	 * 版本号
	 */
	protected String versionId;

	/**
	 * 手机号
	 */
	protected String phoneNo;

	/**
	 * 会员号
	 */
	protected String memberNo;

	/**
	 * 手机设备IMEI号
	 */
	protected String imei;

	/**
	 * 登陆坐标
	 */
	protected String location;

	/**
	 * app标签
	 */
	protected AppSourceEnum appSourceEnum;

	/**
	 * tk中还原的
	 */
	protected String oauthMemberNo;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
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

	public AppSourceEnum getAppSourceEnum() {
		return appSourceEnum;
	}

	public void setAppSourceEnum(AppSourceEnum appSourceEnum) {
		this.appSourceEnum = appSourceEnum;
	}

	public String getOauthMemberNo() {
		return oauthMemberNo;
	}

	public void setOauthMemberNo(String oauthMemberNo) {
		this.oauthMemberNo = oauthMemberNo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("BaseRequest{");
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append("oauthMemberNo='").append(oauthMemberNo).append('\'');
		sb.append('}');
		return sb.toString();
	}

}
