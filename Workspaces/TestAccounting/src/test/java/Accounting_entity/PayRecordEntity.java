package Accounting_entity;

import com.miitang.facade.accounting.enumtype.BeneficiaryEnum;
import com.miitang.facade.accounting.enumtype.PayStatusEnum;
import org.jfaster.mango.annotation.Getter;
import org.jfaster.mango.annotation.Setter;
import org.jfaster.mango.invoker.function.enums.EnumToStringFunction;
import org.jfaster.mango.invoker.function.enums.StringToEnumFunction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类名称: PayRecordEntity <br>
 * 类描述: 打款记录<br>
 *
 * @author: hua.jin
 * @since: 2018/3/6 上午11:13
 * @version: 1.0.0
 */

public class PayRecordEntity {
	/**
	 * 唯一流水号
	 */
	private String uniquePayNo;

	private Integer optimistic;

	/**
	 * 账单号
	 */
	private String billNo;

	/**
	 * 支付公司
	 */
	private String payCompany;

	/**
	 * 运营商商编
	 */
	private String parentMerchantNo;

	/**
	 * 运营商所在支付公司商编
	 */
	private String payCompanyParentMerchantNo;

	/**
	 * 支付公司打款订单号
	 */
	private String payCompanyPayNo;

	/**
	 * 打款金额
	 */
	private BigDecimal payAmount;

	/**
	 * 打款状态
	 */
	private PayStatusEnum payStatus;

	/**
	 * 受益人类型
	 */
	private BeneficiaryEnum beneficiary;

	/**
	 * 姓名
	 */
	private String idCardName;

	/**
	 * 身份证号
	 */
	private String idCardNo;

	/**
	 * 银行卡号
	 */
	private String bankCardNo;

	/**
	 * 银行编码
	 */
	private String bankCode;

	/**
	 * 打款请求时间
	 */
	private String payRequestDate;

	/**
	 * 打款成功时间
	 */
	private String paySuccessDate;

	private Date createDate;

	private Date updateDate;

	private String errorCode;

	private String errorMessage;

	public String getUniquePayNo() {
		return uniquePayNo;
	}

	public void setUniquePayNo(String uniquePayNo) {
		this.uniquePayNo = uniquePayNo == null ? null : uniquePayNo.trim();
	}

	public Integer getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo == null ? null : billNo.trim();
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany == null ? null : payCompany.trim();
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

	public String getPayCompanyPayNo() {
		return payCompanyPayNo;
	}

	public void setPayCompanyPayNo(String payCompanyPayNo) {
		this.payCompanyPayNo = payCompanyPayNo == null ? null : payCompanyPayNo.trim();
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@Getter(EnumToStringFunction.class)
	public PayStatusEnum getPayStatus() {
		return payStatus;
	}

	@Setter(StringToEnumFunction.class)
	public void setPayStatus(PayStatusEnum payStatus) {
		this.payStatus = payStatus;
	}

	@Getter(EnumToStringFunction.class)
	public BeneficiaryEnum getBeneficiary() {
		return beneficiary;
	}

	@Setter(StringToEnumFunction.class)
	public void setBeneficiary(BeneficiaryEnum beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getIdCardName() {
		return idCardName;
	}

	public void setIdCardName(String idCardName) {
		this.idCardName = idCardName == null ? null : idCardName.trim();
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo == null ? null : idCardNo.trim();
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode == null ? null : bankCode.trim();
	}

	public String getPayRequestDate() {
		return payRequestDate;
	}

	public void setPayRequestDate(String payRequestDate) {
		this.payRequestDate = payRequestDate == null ? null : payRequestDate.trim();
	}

	public String getPaySuccessDate() {
		return paySuccessDate;
	}

	public void setPaySuccessDate(String paySuccessDate) {
		this.paySuccessDate = paySuccessDate == null ? null : paySuccessDate.trim();
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
		this.errorCode = errorCode == null ? null : errorCode.trim();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage == null ? null : errorMessage.trim();
	}
}
