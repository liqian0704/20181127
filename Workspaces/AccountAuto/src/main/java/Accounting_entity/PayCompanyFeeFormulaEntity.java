package Accounting_entity;

import com.miitang.common.enums.FeeTypeEnum;
import com.miitang.facade.accounting.enumtype.FeeFormulaStatusEnum;
import org.jfaster.mango.annotation.Getter;
import org.jfaster.mango.annotation.Setter;
import org.jfaster.mango.invoker.function.enums.EnumToStringFunction;
import org.jfaster.mango.invoker.function.enums.StringToEnumFunction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 计费模型_支付公司交易费
 *
 * @author huahua.jin
 * @date 2017-12-22 10:30:00
 */
public class PayCompanyFeeFormulaEntity {
	private Long id;

	/**
	 * 版本号
	 */
	private Integer optimistic;

	/**
	 * 业务方
	 */
	private String bizSystemNo;

	/**
	 * 运营商商编
	 */
	private String parentMerchantNo;

	/**
	 * 支付公司编码
	 */
	private String payCompany;

	/**
	 * 产品编码
	 */
	private String payProduct;

	/**
	 * 状态
	 */
	private FeeFormulaStatusEnum status;

	/**
	 * 计费类型
	 */
	private FeeTypeEnum feeType;

	/**
	 * 费率
	 */
	private BigDecimal feeRate;

	/**
	 * 最小值
	 */
	private BigDecimal minAmount;

	/**
	 * 最大值
	 */
	private BigDecimal maxAmount;

	/**
	 * 固定金额
	 */
	private BigDecimal feeAmount;

	/**
	 * 生效时间
	 */
	private Date effectiveStartDate;

	/**
	 * 过期时间
	 */
	private Date effectiveEndDate;

	private Date createDate;

	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.bizSystemNo = bizSystemNo == null ? null : bizSystemNo.trim();
	}

	public String getParentMerchantNo() {
		return parentMerchantNo;
	}

	public void setParentMerchantNo(String parentMerchantNo) {
		this.parentMerchantNo = parentMerchantNo == null ? null : parentMerchantNo.trim();
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany == null ? null : payCompany.trim();
	}

	public String getPayProduct() {
		return payProduct;
	}

	public void setPayProduct(String payProduct) {
		this.payProduct = payProduct == null ? null : payProduct.trim();
	}

	@Getter(EnumToStringFunction.class)
	public FeeFormulaStatusEnum getStatus() {
		return status;
	}

	@Setter(StringToEnumFunction.class)
	public void setStatus(FeeFormulaStatusEnum status) {
		this.status = status;
	}

	@Getter(EnumToStringFunction.class)
	public FeeTypeEnum getFeeType() {
		return feeType;
	}

	@Setter(StringToEnumFunction.class)
	public void setFeeType(FeeTypeEnum feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
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
}