package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 二维码支付响应
 * Author: jiawen.huang
 * Date: 16/12/7
 * Time: 16:11
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PayCodeResponse extends BaseResponse {

	/**
	 * 扫码url,使用工具生成二维码供扫码使用
	 */
	private String payUrl;

	/**
	 * 订单号，查结果用
	 */
	private String externalId;

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
}
