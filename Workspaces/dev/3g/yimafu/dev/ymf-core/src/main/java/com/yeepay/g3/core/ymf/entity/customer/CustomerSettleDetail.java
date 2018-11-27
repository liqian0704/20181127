package com.yeepay.g3.core.ymf.entity.customer;

import com.yeepay.g3.facade.ymf.enumtype.trade.SettleType;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 出款详情
 * Author: jiawen.huang
 * Date: 17/4/25
 * Time: 15:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class CustomerSettleDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 商户号
	 */
	private String customerNumber;

	/**
	 * 易宝订单号
	 */
	private String yeepayOrderId;

	/**
	 * 发起出款批次
	 */
	private String batchId;

	/**
	 * 发起出款金额
	 */
	private BigDecimal settleAmount;

	/**
	 * 实际到账金额
	 */
	private BigDecimal settleRealAmount;

	/**
	 * 交易手续费
	 */
	private BigDecimal tradeFeeAmount;

	/**
	 * 结算手续费
	 */
	private BigDecimal settleFeeAmount;

	/**
	 * 银行手续费
	 */
	private BigDecimal bankFeeAmount;

	/**
	 * 出款方式：秒到、T1
	 */
	private SettleType settleType;

	/**
	 * 出款时间
	 */
	private Date settleTime;

	/**
	 * 记录创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getYeepayOrderId() {
		return yeepayOrderId;
	}

	public void setYeepayOrderId(String yeepayOrderId) {
		this.yeepayOrderId = yeepayOrderId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public BigDecimal getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(BigDecimal settleAmount) {
		this.settleAmount = settleAmount;
	}

	public BigDecimal getSettleRealAmount() {
		return settleRealAmount;
	}

	public void setSettleRealAmount(BigDecimal settleRealAmount) {
		this.settleRealAmount = settleRealAmount;
	}

	public BigDecimal getTradeFeeAmount() {
		return tradeFeeAmount;
	}

	public void setTradeFeeAmount(BigDecimal tradeFeeAmount) {
		this.tradeFeeAmount = tradeFeeAmount;
	}

	public BigDecimal getSettleFeeAmount() {
		return settleFeeAmount;
	}

	public void setSettleFeeAmount(BigDecimal settleFeeAmount) {
		this.settleFeeAmount = settleFeeAmount;
	}

	public BigDecimal getBankFeeAmount() {
		return bankFeeAmount;
	}

	public void setBankFeeAmount(BigDecimal bankFeeAmount) {
		this.bankFeeAmount = bankFeeAmount;
	}

	public SettleType getSettleType() {
		return settleType;
	}

	public void setSettleType(SettleType settleType) {
		this.settleType = settleType;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
