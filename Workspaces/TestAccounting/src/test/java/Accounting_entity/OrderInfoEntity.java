package Accounting_entity;

import com.miitang.common.enums.ChargeTypeEnum;
import com.miitang.facade.accounting.enumtype.OrderFeeStatusEnum;
import org.jfaster.mango.annotation.Getter;
import org.jfaster.mango.annotation.Setter;
import org.jfaster.mango.invoker.function.enums.EnumToStringFunction;
import org.jfaster.mango.invoker.function.enums.StringToEnumFunction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单原始数据
 *
 * @author huahua.jin
 * @date 2017-12-22 10:30:00
 */
public class OrderInfoEntity {

	/** 订单流水号 **/
    private String uniqueOrderNo;

	/** 版本号 **/
    private Integer optimistic;

	/** 业务方编码 **/
    private String bizSystemNo;

	/** 运营商商编 **/
    private String parentMerchantNo;

	/** 支付公司运营商商编 **/
    private String payCompanyParentMerchantNo;

	/** 运营商名称 **/
    private String parentMerchantName;

	/** 商户商编 **/
    private String merchantNo;

	/** 支付公司运营商商编 **/
    private String payCompanyMerchantNo;

	/** 商户名称 **/
    private String merchantName;

	/** 支付公司编码 **/
    private String payCompany;

	/** 产品编码 **/
	private String productCode;

	/** 支付金额 **/
    private BigDecimal orderAmount;

	/** 实际支付金额金额 **/
	private BigDecimal payAmount;

	/** 商户到账金额 **/
	private BigDecimal inaccountAmount;

	/** 商户扣费 **/
	private ChargeTypeEnum feeType;

    /** 手续费 **/
    private BigDecimal fee;

	/** 订单创建时间 **/
	private Date orderCreateDate;

    /** 支付成功时间 **/
	private Date paySuccessDate;

	/**
	 * 待付MT状态
	 */
	private OrderFeeStatusEnum toMtStatus;

	/**
	 * 待付MT状态
	 */
	private OrderFeeStatusEnum toPayCompanyStatus;

	/**
	 * 待付MT状态
	 */
	private OrderFeeStatusEnum toPromoterStatus;

    /** 待付蜜糖金额 **/
    private BigDecimal toMtAmount;

	/** 待付支付公司金额 **/
    private BigDecimal toPayCompanyAmount;

	/** 待付推广人金额 **/
    private BigDecimal toPromoterAmount;

    private Date createDate;

    private Date updateDate;

    private String errorCode;

    private String errorMessage;

	public String getUniqueOrderNo() {
		return uniqueOrderNo;
	}

	public void setUniqueOrderNo(String uniqueOrderNo) {
		this.uniqueOrderNo = uniqueOrderNo;
	}

	public Integer getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}

	public String getBizSystemNo() {
		return bizSystemNo;
	}

	public void setBizSystemNo(String bizSystemNo) {
		this.bizSystemNo = bizSystemNo;
	}

	public String getParentMerchantNo() {
		return parentMerchantNo;
	}

	public void setParentMerchantNo(String parentMerchantNo) {
		this.parentMerchantNo = parentMerchantNo;
	}

	public String getPayCompanyParentMerchantNo() {
		return payCompanyParentMerchantNo;
	}

	public void setPayCompanyParentMerchantNo(String payCompanyParentMerchantNo) {
		this.payCompanyParentMerchantNo = payCompanyParentMerchantNo;
	}

	public String getParentMerchantName() {
		return parentMerchantName;
	}

	public void setParentMerchantName(String parentMerchantName) {
		this.parentMerchantName = parentMerchantName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getPayCompanyMerchantNo() {
		return payCompanyMerchantNo;
	}

	public void setPayCompanyMerchantNo(String payCompanyMerchantNo) {
		this.payCompanyMerchantNo = payCompanyMerchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getPaySuccessDate() {
		return paySuccessDate;
	}

	public void setPaySuccessDate(Date paySuccessDate) {
		this.paySuccessDate = paySuccessDate;
	}

	public BigDecimal getToMtAmount() {
		return toMtAmount;
	}

	public void setToMtAmount(BigDecimal toMtAmount) {
		this.toMtAmount = toMtAmount;
	}

	public BigDecimal getToPayCompanyAmount() {
		return toPayCompanyAmount;
	}

	public void setToPayCompanyAmount(BigDecimal toPayCompanyAmount) {
		this.toPayCompanyAmount = toPayCompanyAmount;
	}

	public BigDecimal getToPromoterAmount() {
		return toPromoterAmount;
	}

	public void setToPromoterAmount(BigDecimal toPromoterAmount) {
		this.toPromoterAmount = toPromoterAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public BigDecimal getInaccountAmount() {
		return inaccountAmount;
	}

	public void setInaccountAmount(BigDecimal inaccountAmount) {
		this.inaccountAmount = inaccountAmount;
	}

	@Getter(EnumToStringFunction.class)
	public ChargeTypeEnum getFeeType() {
		return feeType;
	}

	@Setter(StringToEnumFunction.class)
	public void setFeeType(ChargeTypeEnum feeType) {
		this.feeType = feeType;
	}

	@Getter(EnumToStringFunction.class)
	public OrderFeeStatusEnum getToMtStatus() {
		return toMtStatus;
	}

	@Setter(StringToEnumFunction.class)
	public void setToMtStatus(OrderFeeStatusEnum toMtStatus) {
		this.toMtStatus = toMtStatus;
	}

	@Getter(EnumToStringFunction.class)
	public OrderFeeStatusEnum getToPayCompanyStatus() {
		return toPayCompanyStatus;
	}

	@Setter(StringToEnumFunction.class)
	public void setToPayCompanyStatus(OrderFeeStatusEnum toPayCompanyStatus) {
		this.toPayCompanyStatus = toPayCompanyStatus;
	}

	@Getter(EnumToStringFunction.class)
	public OrderFeeStatusEnum getToPromoterStatus() {
		return toPromoterStatus;
	}

	@Setter(StringToEnumFunction.class)
	public void setToPromoterStatus(OrderFeeStatusEnum toPromoterStatus) {
		this.toPromoterStatus = toPromoterStatus;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}