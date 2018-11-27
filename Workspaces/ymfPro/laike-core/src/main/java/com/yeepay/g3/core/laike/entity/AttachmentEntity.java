package com.yeepay.g3.core.laike.entity;

import com.yeepay.g3.core.laike.utils.AttachmentsAttribute;

/**
 * 开户附件表
 */
public class AttachmentEntity extends PersistenceEntity {

	/**
	 * 开户id
	 */
	private String accountId;

	/**
	 * 营业执照照片
	 */
	@AttachmentsAttribute(fieldName = "CORP_CODE")
	private String bizImg;

	/**
	 * 统一社会信用代码证
	 */
	@AttachmentsAttribute(fieldName = "UNI_CREDIT_CODE")
	private String creditCodeImg;

	/**
	 * 组织机构代码证
	 */
	@AttachmentsAttribute(fieldName = "ORG_CODE")
	private String orgImg;

	/**
	 * 银行卡开户许可证
	 */
	@AttachmentsAttribute(fieldName = "OP_BANK_CODE")
	private String permitImg;

	/**
	 * 税务登记证
	 */
	@AttachmentsAttribute(fieldName = "TAX_CODE")
	private String taxImg;

	/**
	 * 身份证证正面
	 */
	@AttachmentsAttribute(fieldName = "IDCARD_FRONT")
	private String idcardImg1;

	/**
	 * 身份证证反面
	 */
	@AttachmentsAttribute(fieldName = "IDCARD_BACK")
	private String idcardImg2;

	/**
	 * 银行卡照片
	 */
	@AttachmentsAttribute(fieldName = "SETTLE_BANKCARD")
	private String bankcardImg;

	/**
	 * 手持身份证
	 */
	@AttachmentsAttribute(fieldName = "HAND_IDCARD")
	private String bankcardImg2;

	/**
	 * 协议文件
	 */
	private String agreementImg;

	/**
	 * 协议文件2
	 */
	private String agreementImg2;

	/**
	 * 签名
	 */
    @AttachmentsAttribute(fieldName = "DZXY")
    private String signImg;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBizImg() {
		return bizImg;
	}

	public void setBizImg(String bizImg) {
		this.bizImg = bizImg;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	public String getPermitImg() {
		return permitImg;
	}

	public void setPermitImg(String permitImg) {
		this.permitImg = permitImg;
	}

	public String getTaxImg() {
		return taxImg;
	}

	public void setTaxImg(String taxImg) {
		this.taxImg = taxImg;
	}

	public String getIdcardImg1() {
		return idcardImg1;
	}

	public void setIdcardImg1(String idcardImg1) {
		this.idcardImg1 = idcardImg1;
	}

	public String getIdcardImg2() {
		return idcardImg2;
	}

	public void setIdcardImg2(String idcardImg2) {
		this.idcardImg2 = idcardImg2;
	}

	public String getBankcardImg() {
		return bankcardImg;
	}

	public void setBankcardImg(String bankcardImg) {
		this.bankcardImg = bankcardImg;
	}

	public String getBankcardImg2() {
		return bankcardImg2;
	}

	public void setBankcardImg2(String bankcardImg2) {
		this.bankcardImg2 = bankcardImg2;
	}

	public String getAgreementImg() {
		return agreementImg;
	}

	public void setAgreementImg(String agreementImg) {
		this.agreementImg = agreementImg;
	}

	public String getAgreementImg2() {
		return agreementImg2;
	}

	public void setAgreementImg2(String agreementImg2) {
		this.agreementImg2 = agreementImg2;
	}

	public String getSignImg() {
		return signImg;
	}

	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	public String getCreditCodeImg() {
		return creditCodeImg;
	}

	public void setCreditCodeImg(String creditCodeImg) {
		this.creditCodeImg = creditCodeImg;
	}
}