package com.yeepay.g3.core.ymf.entity.order;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Title: CsprocessCallbackParam.java
 * @Package com.yeepay.g3.core.ymf.entity.order
 * @Description: 清算中心回调参数实体 注：因回调参数为map，因此创建此实体，属性既map的Key
 * 				 目前不会持久化
 * @author dongxu.lu
 * @date 2016年8月25日 下午8:27:39
 * @version
 */
public class CsprocessCallbackParam {
	/**
	 * ("账务-订单未支付")-中间终态 
	 */
	public static final String NOT_PAY="NOT_PAY";
	/**
	 * ("账务-订单核对错误")-中间终态
	 */
	public static final String ORDER_CHECK_ERROR="ORDER_CHECK_ERROR";
	/**
	 * 担保订单才会回调该状态，是一个中间终态，表示账务已完成担保入账，业务方在该状态时才允许发起担保确认。
	 */
	public static final String WAIT_BIZ_CONFIRM="WAIT_BIZ_CONFIRM";
	
	public static final String SUCCESS="SUCCESS";
	
	// 交易系统编码
	private String bizSystem;
	// 清算中心系统唯一订单号
	private String externalId;
	// 商户订单请求号
	private String merchantRequestNo;
	// 交易系统内部订单号
	private String bizRequestNo;
	// 订单所属商户编号
	private String merchantNo;
	// 交易金额
	private String amount;
	// 收款方手续费
	private String targetFee;
	// 收款方手续费类型
	private String targetFeeType;
	// 付款方手续费
	private String srcFee;
	// 付款方手续费类型
	private String srcFeeType;
	// 计费类型
	private String feeChargeType;
	/**
	 * 清算中心记录状态
	 * NOT_PAY("账务-订单未支付")-中间终态 
	 * ORDER_CHECK_ERROR("账务-订单核对错误")-中间终态
	 * WAIT_BIZ_CONFIRM：担保订单才会回调该状态，是一个中间终态，表示账务已完成担保入账，业务方在该状态时才允许发起担保确认。
	 * SUCCESS("处理成功")-终态
	 */
	private String orderStatus;
	/**
	 * 分润状态
	 * WAIT_BIZ_CONFIRM：担保订单才会回调该状态，是一个中间终态，表示账务已完成担保入账，业务方在该状态时才允许发起担保确认。
	 * SUCCESS("处理成功")
	 */
	private String splitStatus;
	/**
	 * 不支持同步分账，该状态暂时没有意义
	 * INIT("初始化") 
	 * SUCCESS("处理成功")
	 */
	private String divideStatus;
	// 银行支付成功时间
	private Date paySuccessDate;
	// 商户入账时间 
	private Date inAccountDate;
	// 清算中心受理时间
	private Date receiveDate;
	
	
	public String getBizSystem() {
		return bizSystem;
	}
	public void setBizSystem(String bizSystem) {
		this.bizSystem = bizSystem;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getMerchantRequestNo() {
		return merchantRequestNo;
	}
	public void setMerchantRequestNo(String merchantRequestNo) {
		this.merchantRequestNo = merchantRequestNo;
	}
	public String getBizRequestNo() {
		return bizRequestNo;
	}
	public void setBizRequestNo(String bizRequestNo) {
		this.bizRequestNo = bizRequestNo;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTargetFee() {
		return targetFee;
	}
	public void setTargetFee(String targetFee) {
		this.targetFee = targetFee;
	}
	public String getTargetFeeType() {
		return targetFeeType;
	}
	public void setTargetFeeType(String targetFeeType) {
		this.targetFeeType = targetFeeType;
	}
	public String getSrcFee() {
		return srcFee;
	}
	public void setSrcFee(String srcFee) {
		this.srcFee = srcFee;
	}
	public String getSrcFeeType() {
		return srcFeeType;
	}
	public void setSrcFeeType(String srcFeeType) {
		this.srcFeeType = srcFeeType;
	}
	public String getFeeChargeType() {
		return feeChargeType;
	}
	public void setFeeChargeType(String feeChargeType) {
		this.feeChargeType = feeChargeType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSplitStatus() {
		return splitStatus;
	}
	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}
	public String getDivideStatus() {
		return divideStatus;
	}
	public void setDivideStatus(String divideStatus) {
		this.divideStatus = divideStatus;
	}
	public Date getPaySuccessDate() {
		return paySuccessDate;
	}
	public void setPaySuccessDate(Date paySuccessDate) {
		this.paySuccessDate = paySuccessDate;
	}
	public Date getInAccountDate() {
		return inAccountDate;
	}
	public void setInAccountDate(Date inAccountDate) {
		this.inAccountDate = inAccountDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
