package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 查询支行信息
 * Author: jiawen.huang
 * Date: 16/12/19
 * Time: 20:23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryBankRequest extends BaseRequest {

	/**
	 * 省编码
	 */
	private String provinceCode;

	/**
	 * 总行编码
	 */
	private String headBankCode;

	/**
	 * 市编码
	 */
	private String cityCode;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getHeadBankCode() {
		return headBankCode;
	}

	public void setHeadBankCode(String headBankCode) {
		this.headBankCode = headBankCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
