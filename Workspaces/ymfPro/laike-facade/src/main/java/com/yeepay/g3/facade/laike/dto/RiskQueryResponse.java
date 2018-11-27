package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;

/**
 * Description: 来客交易风险系数
 * Author: jiawen.huang
 * Date: 17/2/24
 * Time: 16:00
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class RiskQueryResponse extends BaseResponse {

	/**
	 * 商户名称
	 */
	private String merchantName;

	/**
	 * 商户号
	 */
	private String merchantNo;

	/**
	 * 企业类型
	 */
	private CompanyTypeEnum companyType;

	/**
	 * 法人姓名
	 */
	private String legalName;

	/**
	 * 法人身份证号
	 */
	private String legalIdCard;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public CompanyTypeEnum getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyTypeEnum companyType) {
		this.companyType = companyType;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalIdCard() {
		return legalIdCard;
	}

	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}
}
