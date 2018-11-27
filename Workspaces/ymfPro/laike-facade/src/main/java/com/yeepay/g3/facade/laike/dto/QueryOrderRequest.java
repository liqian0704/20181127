package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;

/**
 * Description: 交易查询
 * Author: jiawen.huang
 * Date: 16/11/24
 * Time: 20:40
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryOrderRequest extends QueryBaseRequest {

	/**
	 * 来源
	 */
	private PaySource paySource;

	/**
	 * 状态
	 */
	private OrderStatus orderStatus;

	/**
	 * 订单类型
	 */
	private TrxType orderType;

	/**
	 * 订单唯一号
	 */
	private String externalId;

	/**
	 * 网点编号
	 */
	private String shopNumber;

    /**
     * 到账时效
     */
    private String balanceType;

    /**
     * 易宝订单号
     */
    private String yeepayOrderId;

	public PaySource getPaySource() {
		return paySource;
	}

	public void setPaySource(PaySource paySource) {
		this.paySource = paySource;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public TrxType getOrderType() {
		return orderType;
	}

	public void setOrderType(TrxType orderType) {
		this.orderType = orderType;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getYeepayOrderId() {
        return yeepayOrderId;
    }

    public void setYeepayOrderId(String yeepayOrderId) {
        this.yeepayOrderId = yeepayOrderId;
    }

    @Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("QueryOrderRequest{");
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append("paySource='").append(paySource).append('\'');
		sb.append("orderStatus='").append(orderStatus).append('\'');
		sb.append("orderType='").append(orderType).append('\'');
		sb.append("externalId='").append(externalId).append('\'');
		sb.append("shopNumber='").append(shopNumber).append('\'');
        sb.append("balanceType='").append(balanceType).append('\'');
        sb.append("yeepayOrderId='").append(yeepayOrderId).append('\'');
        sb.append('}');
		return sb.toString();
	}
}
