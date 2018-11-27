package com.yeepay.g3.facade.laike.dto;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.Amount;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Description: 支付消息请求参数
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:34
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PayNotifyRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 交易订单金额
	 */
	private Amount orderAmount;

	/**
	 * 打赏金额
	 */
	private Amount tipAmount;

	/**
	 * 总金额
	 */
	private Amount totalAmount;

	/**
	 * 实付金额
	 */
	private Amount realPayAmount;

	/**
	 * 支付渠道
	 */
	private PaySource paySource;

	/**
	 * 收款商户
	 */
	private String merchantNo;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 支付凭证
	 */
	private String payReceipt;

	/**
	 * 支付时间
	 */
	private Date payTime;

	/**
	 * 请求系统
	 */
	private ExternalSystem externalSystem;

    /**
     * 今日交易总笔数
     */
    private Integer todayTotalCount;

    /**
     * 今日交易总金额
     */
    private Amount todayTotalAmount;

    /**
     * 账户余额
     */
    private String accountBalance = "0.00";

	public Amount getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Amount orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Amount getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(Amount tipAmount) {
		this.tipAmount = tipAmount;
	}

	public Amount getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Amount totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Amount getRealPayAmount() {
		return realPayAmount;
	}

	public void setRealPayAmount(Amount realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	public PaySource getPaySource() {
		return paySource;
	}

	public void setPaySource(PaySource paySource) {
		this.paySource = paySource;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayReceipt() {
		return payReceipt;
	}

	public void setPayReceipt(String payReceipt) {
		this.payReceipt = payReceipt;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public ExternalSystem getExternalSystem() {
		return externalSystem;
	}

	public void setExternalSystem(ExternalSystem externalSystem) {
		this.externalSystem = externalSystem;
    }

    public Integer getTodayTotalCount() {
        return todayTotalCount;
    }

    public void setTodayTotalCount(Integer todayTotalCount) {
        this.todayTotalCount = todayTotalCount;
    }

    public Amount getTodayTotalAmount() {
        return todayTotalAmount;
    }

    public void setTodayTotalAmount(Amount todayTotalAmount) {
        this.todayTotalAmount = todayTotalAmount;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getContentJson() {
        Map<String, Object> map = Maps.newHashMap();
		map.put("payReceipt", getPayReceipt());
		map.put("orderNo", getOrderNo());
		map.put("orderAmount", null == getOrderAmount() ? new BigDecimal(0.00) : getOrderAmount().getValue());
		map.put("totalAmount", null == getTotalAmount() ? new BigDecimal(0.00) : getTotalAmount().getValue());
		map.put("realPayAmount", null == getRealPayAmount() ? new BigDecimal(0.00) : getRealPayAmount().getValue());
		map.put("tipAmount", null == getTipAmount() ? new BigDecimal(0.00) : getTipAmount().getValue());
		map.put("payTime", null == getPayTime() ? null : DateUtils.getTimeStampStr(getPayTime()));
        map.put("todayTotalCount", getTodayTotalCount());
		map.put("todayTotalAmount", null == getTodayTotalAmount() ? new BigDecimal(0.00) : getTodayTotalAmount().getValue());
        map.put("todayTotalAmount", null == getTodayTotalAmount() ? new BigDecimal(0.00) : getTodayTotalAmount().getValue());
        map.put("accountBalance", getAccountBalance());
        return JSONUtils.toJsonString(map);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PayNotifyRequest{");
		sb.append("merchantNo='").append(merchantNo).append('\'');
		sb.append("orderNo='").append(orderNo).append('\'');
		sb.append("orderAmount='").append(orderAmount).append('\'');
		sb.append("tipAmount='").append(tipAmount).append('\'');
		sb.append("totalAmount='").append(totalAmount).append('\'');
		sb.append("realPayAmount='").append(realPayAmount).append('\'');
		sb.append("paySource='").append(paySource).append('\'');
		sb.append("payReceipt='").append(payReceipt).append('\'');
		sb.append("payTime='").append(payTime).append('\'');
		sb.append("externalSystem='").append(externalSystem).append('\'');
        sb.append("todayTotalCount='").append(todayTotalCount).append('\'');
        sb.append("todayTotalAmount='").append(todayTotalAmount).append('\'');
        sb.append("accountBalance='").append(accountBalance).append('\'');
        sb.append('}');
		return sb.toString();
	}
}
