package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 产品费率响应
 * Author: jiawen.huang
 * Date: 16/12/8
 * Time: 16:44
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ProductResponse extends BaseResponse {

	/**
	 * 结算方式
	 */
	private String settleMethod;

	/**
	 * 结算周期
	 */
	private String settleCycle;

	/**
	 * 结算日
	 */
	private String settleDate;

	/**
	 * 风险预存期
	 */
	private String riskDeadline;

	/**
	 * 最低起结金额
	 */
	private String minSettleAmt;

	/**
	 * 产品信息
	 */
	private String productInfo;

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getSettleCycle() {
		return settleCycle;
	}

	public void setSettleCycle(String settleCycle) {
		this.settleCycle = settleCycle;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getRiskDeadline() {
		return riskDeadline;
	}

	public void setRiskDeadline(String riskDeadline) {
		this.riskDeadline = riskDeadline;
	}

	public String getMinSettleAmt() {
		return minSettleAmt;
	}

	public void setMinSettleAmt(String minSettleAmt) {
		this.minSettleAmt = minSettleAmt;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
}
