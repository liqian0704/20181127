package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

/**
 * Description: 二维码支付请求基类
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 17:40
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PayCodeRequest extends BaseRequest {

	/**
	 * 不是本人
	 */
    private boolean notSelf = true;
    /**
	 * 订单金额
	 */
	private Amount orderAmount;
	/**
	 * 打赏
	 */
	private Amount gratuityAmount;
	/**
	 * 支付方式
	 */
	private PaySource paySource;

	/**
	 * ip
	 */
	private String ip;

    /**
     * 结算方式
     * TRUE: 秒到
     * FALSE：T1
     */
    private BoolEnum s0Balance;

	public Amount getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Amount orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Amount getGratuityAmount() {
		return gratuityAmount;
	}

	public void setGratuityAmount(Amount gratuityAmount) {
		this.gratuityAmount = gratuityAmount;
	}

	public PaySource getPaySource() {
		return paySource;
	}

	public void setPaySource(PaySource paySource) {
		this.paySource = paySource;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean getNotSelf() {
		return notSelf;
	}

	public void setNotSelf(boolean notSelf) {
		this.notSelf = notSelf;
	}

    public BoolEnum getS0Balance() {
        return s0Balance;
    }

    public void setS0Balance(BoolEnum s0Balance) {
        this.s0Balance = s0Balance;
    }

    @Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("PayCodeRequest{");
		sb.append("memberNo='").append(memberNo).append('\'');
        sb.append("notSelf='").append(notSelf).append('\'');
        sb.append("orderAmount='").append(orderAmount).append('\'');
		sb.append("gratuityAmount='").append(gratuityAmount).append('\'');
		sb.append("paySource='").append(paySource).append('\'');
		sb.append("ip='").append(ip).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
        sb.append("s0Balance='").append(s0Balance).append('\'');
        sb.append('}');
		return sb.toString();
	}

}
