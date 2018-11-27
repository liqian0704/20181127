package com.yeepay.g3.facade.laike.dto;

import com.google.common.collect.Lists;
import com.yeepay.g3.facade.laike.enumtype.AppProductEnum;
import com.yeepay.g3.facade.laike.enumtype.ProductStatusEnum;

import java.util.List;

/**
 * Description: APP增值服务详情
 * Author: jiawen.huang
 * Date: 17/4/18
 * Time: 16:52
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class ProductionResponse extends BaseResponse {

	/**
	 * 名称
	 */
	private String productName;

	/**
	 * 编码
	 */
    private AppProductEnum productCode;

	/**
	 * 状态
	 */
	private ProductStatusEnum productStatusEnum;

	/**
	 * 开通时间
	 */
	private String openTime;

    /**
     * URL
     */
    private String url;

    /**
     * 费率信息
     */
	private List<CalFeeInfo> feeRate;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
    }

    public AppProductEnum getProductCode() {
        return productCode;
    }

    public void setProductCode(AppProductEnum productCode) {
        this.productCode = productCode;
	}

	public ProductStatusEnum getProductStatusEnum() {
		return productStatusEnum;
	}

	public void setProductStatusEnum(ProductStatusEnum productStatusEnum) {
		this.productStatusEnum = productStatusEnum;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public List<CalFeeInfo> getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(List<CalFeeInfo> feeRate) {
		this.feeRate = feeRate;
	}

	public void setFeeRate(CalFeeInfo feeRate) {
		List<CalFeeInfo> list = Lists.newArrayList();
		list.add(feeRate);
		this.feeRate = list;
	}
}
