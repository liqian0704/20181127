package com.yeepay.g3.core.ymf.entity.pay.param;

import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.utils.common.json.JSONUtils;

/**
 * 扫码支付订单信息
 * @author xiaobin.liu
 *
 */
public class YmfOrderParam {
	
	/**
	 * 响应码
	 */
	private String resCode ;
	/**
	 * 响应消息
	 */
	private String resMsg ;
	/**
	 * 顾客姓名
	 */
	private String receiverName ;
	/**
	 * 交易备注
	 */
	private String remark ;
	/**
	 * 物流流水号
	 */
	private String codOrderNo ;
	/**
	 * 顾客电话
	 */
	private String receiverTel ;
	/**
	 * 地址
	 */
	private String receiverAddr ;
	/**
	 * 支付方式
	 */
	private String payMode ;
	/**
	 * 签收状态
	 */
	private String signStatus ;
	/**
	 * 支付状态
	 */
	private String payStatus ;
	/**
	 * 支付金额
	 */
	private String payAmt ;
	/**
	 * 支付通道
	 */
	private String payChannel ;
	/**
	 * 支付明细
	 */
	private String payDetail ;
	/**
	 * 签收日期
	 */
	private String signInDate ;
	/**
	 * 支付时间
	 */
	private String payDate ;
	/**
	 * 派送须知
	 */
	private String sendNotice ;
	/**
	 * 发件商条形码
	 */
	private String sendCompanyBarCode ;
	/**
	 * 商户订单号
	 */
	private String customerRequestId ;
	/**
	 * 订单金额
	 */
	private String transAmt ;
	/**
	 * 退货金额
	 */
	private String refundAmt ;
	private String customerNumber ;
	private String customerKey ;
	private String customerName ;
	private String openId ;
	/**
	 * 商户简称
	 */
	private String custShotName ;
	/**
	 * 业务类型
	 */
	private BusinessType businessType;
	/**
	 * 签名
	 */
	private String orderSign ;
	/**
	 * 二维码id
     */
	private String qrCode ;
	/**
	 * logo地址
	 */
	private String customerLogo ;

	public String getCustomerLogo() {
		return customerLogo;
	}

	public void setCustomerLogo(String customerLogo) {
		this.customerLogo = customerLogo;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCustShotName() {
		return custShotName;
	}
	public void setCustShotName(String custShotName) {
		this.custShotName = custShotName;
	}
	
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public String getOrderSign() {
		return orderSign;
	}
	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getCustomerRequestId() {
		return customerRequestId;
	}
	public void setCustomerRequestId(String customerRequestId) {
		this.customerRequestId = customerRequestId;
	}
	public String getSendNotice() {
		return sendNotice;
	}
	public void setSendNotice(String sendNotice) {
		this.sendNotice = sendNotice;
	}
	public String getSendCompanyBarCode() {
		return sendCompanyBarCode;
	}
	public void setSendCompanyBarCode(String sendCompanyBarCode) {
		this.sendCompanyBarCode = sendCompanyBarCode;
	}
	public String getSignInDate() {
		return signInDate;
	}
	public void setSignInDate(String signInDate) {
		this.signInDate = signInDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayDetail() {
		return payDetail;
	}
	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCodOrderNo() {
		return codOrderNo;
	}
	public void setCodOrderNo(String codOrderNo) {
		this.codOrderNo = codOrderNo;
	}
	public String getReceiverTel() {
		return receiverTel;
	}
	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Override
	public String toString() {
		return JSONUtils.toJsonString(this);
	}
}
