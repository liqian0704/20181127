package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * Description: 开户基本信息请求
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 15:12
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OpenAccountRequest extends BaseRequest {

	/**
	 * 是否直接提交
	 */
	private boolean submit = false;

	/**
	 * 邀请方式
	 */
	private InviteType inviteType;

	/**
	 * 邀请码/商编
	 */
	private String inviteCode;

	/**
	 * 商户名称
	 */
	private String merchantName;

	/**
	 * 商户号
	 */
	private String merchantNo;

	/**
	 * 当InviteType为：INVITECODE & SIGNEDPAPER 时为代理商号，
	 * SELLER_DIRECT_SALE时为销售号，DIRECT_SALE时为空
	 */
	private String agentNo;

	/**
	 * 代理商业务员
	 */
	private String businessMan;

	/**
	 * 开户单号
	 */
	private String opRegNo;

	/**
	 * 企业类型
	 */
	private CompanyTypeEnum companyType;

	/**
	 * companyType为ENTERPRISE是营业执照号，ENTER_UNION为统一信用代码证号
	 */
	private String bizNo;

	/**
	 * 营业执照开始时间
	 */
	private String bizNoStart;

	/**
	 * 营业执照失效时间
	 */
	private String bizNoEnd;

	/**
	 * 税务登记证号
	 */
	private String taxNo;

	/**
	 * 组织机构代码证
	 */
	private String orgNo;

	/**
	 * 开户许可证
	 */
	private String accountLicense;

	/**
	 * 组织机构代码证开始时间
	 */
	private String orgNoStart;

	/**
	 * 组织机构代码证失效时间
	 */
	private String orgNoEnd;

	/**
	 * 产品表
	 */
	private String products;

	/**
	 * 法人姓名
	 */
	private String legalName;

	/**
	 * 法人身份证号
	 */
	private String legalIdCard;

	/**
	 * 法人身份开始时间
	 */
	private String legalIdStart;

	/**
	 * 法人身份失效时间
	 */
	private String legalIdEnd;

	/**
	 * 结算银行卡号
	 */
	private String settleCardNo;

	/**
	 * 结算银行卡户名
	 */
	private String settleCardName;

	/**
	 * 结算银行编号
	 */
	private String settleBankCode;

	/**
	 * 结算银行总行
	 */
	private String settleBankName;

	/**
	 * 结算银行分行编号
	 */
	private String branchBankCode;

	/**
	 * 结算银行分行
	 */
	private String branchBankName;

	/**
	 * 结算银行开户省编码
	 */
	private String bankProvinceCode;

	/**
	 * 结算银行开户省
	 */
	private String bankProvinceName;

	/**
	 * 结算银行开户市编码
	 */
	private String bankCityCode;

	/**
	 * 结算银行开户市
	 */
	private String bankCityName;

	/**
	 * 开户定位
	 */
	private String openLbs;

	/**
	 * 商户一级分类
	 */
	private String merLevel1No;

	/**
	 * 商户一级分类名称
	 */
	private String merLevel1NoName;

	/**
	 * 商户二级分类
	 */
	private String merLevel2No;

	/**
	 * 商户二级分类名称
	 */
	private String merLevel2NoName;

	/**
	 * 商户所在地址省
	 */
	private String merProvince;

	/**
	 * 商户所在地址省
	 */
	private String merProvinceName;

	/**
	 * 商户所在地址市
	 */
	private String merCity;

	/**
	 * 商户所在地址市
	 */
	private String merCityName;

	/**
	 * 商户所在地址区
	 */
	private String merDistrict;

	/**
	 * 商户所在地址区
	 */
	private String merDistrictName;

	/**
	 * 商户所在详细地址
	 */
	private String merAddress;

    /**
     * 协议签名
     */
	private String signImg;

    /**
     * 身份证证正面
     */
    private String idcardImg1;

    /**
     * 身份证证反面
     */
    private String idcardImg2;

    /**
     * 银行卡照片
     */
    private String bankcardImg;

    /**
     * 手持身份证
     */
    private String bankcardImg2;

    /**
     * OCR识别的身份证
     */
    private String ocrLegalIdCard;

    /**
     * OCR识别的结算银行卡
     */
    private String ocrSettleCardNo;

	public InviteType getInviteType() {
		return inviteType;
	}

	public void setInviteType(InviteType inviteType) {
		this.inviteType = inviteType;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getBusinessMan() {
		return businessMan;
	}

	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}

	public String getOpRegNo() {
		return opRegNo;
	}

	public void setOpRegNo(String opRegNo) {
		this.opRegNo = opRegNo;
	}

	public CompanyTypeEnum getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyTypeEnum companyType) {
		this.companyType = companyType;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getBizNoStart() {
		return bizNoStart;
	}

	public void setBizNoStart(String bizNoStart) {
		this.bizNoStart = bizNoStart;
	}

	public String getBizNoEnd() {
		return bizNoEnd;
	}

	public void setBizNoEnd(String bizNoEnd) {
		this.bizNoEnd = bizNoEnd;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgNoStart() {
		return orgNoStart;
	}

	public void setOrgNoStart(String orgNoStart) {
		this.orgNoStart = orgNoStart;
	}

	public String getOrgNoEnd() {
		return orgNoEnd;
	}

	public void setOrgNoEnd(String orgNoEnd) {
		this.orgNoEnd = orgNoEnd;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalIdCard() {
		return legalIdCard;
	}

	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}

	public String getLegalIdStart() {
		return legalIdStart;
	}

	public void setLegalIdStart(String legalIdStart) {
		this.legalIdStart = legalIdStart;
	}

	public String getLegalIdEnd() {
		return legalIdEnd;
	}

	public void setLegalIdEnd(String legalIdEnd) {
		this.legalIdEnd = legalIdEnd;
	}

	public String getSettleCardNo() {
		return settleCardNo;
	}

	public void setSettleCardNo(String settleCardNo) {
		this.settleCardNo = settleCardNo;
	}

	public String getSettleCardName() {
		return settleCardName;
	}

	public void setSettleCardName(String settleCardName) {
		this.settleCardName = settleCardName;
	}

	public String getSettleBankCode() {
		return settleBankCode;
	}

	public void setSettleBankCode(String settleBankCode) {
		this.settleBankCode = settleBankCode;
	}

	public String getSettleBankName() {
		return settleBankName;
	}

	public void setSettleBankName(String settleBankName) {
		this.settleBankName = settleBankName;
	}

	public String getBankProvinceCode() {
		return bankProvinceCode;
	}

	public void setBankProvinceCode(String bankProvinceCode) {
		this.bankProvinceCode = bankProvinceCode;
	}

	public String getBankCityCode() {
		return bankCityCode;
	}

	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}

	public String getOpenLbs() {
		return openLbs;
	}

	public void setOpenLbs(String openLbs) {
		this.openLbs = openLbs;
	}

	public String getMerLevel1No() {
		return merLevel1No;
	}

	public void setMerLevel1No(String merLevel1No) {
		this.merLevel1No = merLevel1No;
	}

	public String getMerLevel2No() {
		return merLevel2No;
	}

	public void setMerLevel2No(String merLevel2No) {
		this.merLevel2No = merLevel2No;
	}

	public String getMerProvince() {
		return merProvince;
	}

	public void setMerProvince(String merProvince) {
		this.merProvince = merProvince;
	}

	public String getMerCity() {
		return merCity;
	}

	public void setMerCity(String merCity) {
		this.merCity = merCity;
	}

	public String getMerDistrict() {
		return merDistrict;
	}

	public void setMerDistrict(String merDistrict) {
		this.merDistrict = merDistrict;
	}

	public String getMerAddress() {
		return merAddress;
	}

	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
	}

	public String getAccountLicense() {
		return accountLicense;
	}

	public void setAccountLicense(String accountLicense) {
		this.accountLicense = accountLicense;
	}

	public String getBankProvinceName() {
		return bankProvinceName;
	}

	public void setBankProvinceName(String bankProvinceName) {
		this.bankProvinceName = bankProvinceName;
	}

	public String getBankCityName() {
		return bankCityName;
	}

	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}

	public String getMerProvinceName() {
		return merProvinceName;
	}

	public void setMerProvinceName(String merProvinceName) {
		this.merProvinceName = merProvinceName;
	}

	public String getMerCityName() {
		return merCityName;
	}

	public void setMerCityName(String merCityName) {
		this.merCityName = merCityName;
	}

	public String getMerDistrictName() {
		return merDistrictName;
	}

	public void setMerDistrictName(String merDistrictName) {
		this.merDistrictName = merDistrictName;
	}

	public String getBranchBankCode() {
		return branchBankCode;
	}

	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public String getMerLevel1NoName() {
		return merLevel1NoName;
	}

	public void setMerLevel1NoName(String merLevel1NoName) {
		this.merLevel1NoName = merLevel1NoName;
	}

	public String getMerLevel2NoName() {
		return merLevel2NoName;
	}

	public void setMerLevel2NoName(String merLevel2NoName) {
		this.merLevel2NoName = merLevel2NoName;
	}

	public boolean getSubmit() {
		return submit;
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}

	public String getSignImg() {
		return signImg;
    }

	public void setSignImg(String signImg) {
		this.signImg = signImg;
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

    public String getOcrLegalIdCard() {
        return ocrLegalIdCard;
    }

    public void setOcrLegalIdCard(String ocrLegalIdCard) {
        this.ocrLegalIdCard = ocrLegalIdCard;
    }

    public String getOcrSettleCardNo() {
        return ocrSettleCardNo;
    }

    public void setOcrSettleCardNo(String ocrSettleCardNo) {
        this.ocrSettleCardNo = ocrSettleCardNo;
    }

    @Override
    public String toString() {
		final StringBuffer sb = new StringBuffer("OpenAccountRequest{");
		sb.append("submit='").append(submit).append('\'');
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append("inviteType='").append(inviteType).append('\'');
		sb.append("inviteCode='").append(inviteCode).append('\'');
		sb.append("merchantName='").append(merchantName).append('\'');
		sb.append("merchantNo='").append(merchantNo).append('\'');
		sb.append("agentNo='").append(agentNo).append('\'');
		sb.append("opRegNo='").append(opRegNo).append('\'');
		sb.append("companyType='").append(companyType).append('\'');
		sb.append("bizNo='").append(HiddenCodeUtil.hiddenBankCardNO(bizNo)).append('\'');
		sb.append("bizNoStart='").append(bizNoStart).append('\'');
		sb.append("bizNoEnd='").append(bizNoEnd).append('\'');
		sb.append("taxNo='").append(HiddenCodeUtil.hiddenBankCardNO(taxNo)).append('\'');
		sb.append("orgNo='").append(HiddenCodeUtil.hiddenBankCardNO(orgNo)).append('\'');
		sb.append("accountLicense='").append(HiddenCodeUtil.hiddenBankCardNO(accountLicense)).append('\'');
		sb.append("orgNoStart='").append(orgNoStart).append('\'');
		sb.append("orgNoEnd='").append(orgNoEnd).append('\'');
		sb.append("legalName='").append(HiddenCodeUtil.hiddenName(legalName)).append('\'');
		sb.append("legalIdCard='").append(HiddenCodeUtil.hiddenIdentityCode(legalIdCard)).append('\'');
		sb.append("legalIdStart='").append(legalIdStart).append('\'');
		sb.append("legalIdEnd='").append(legalIdEnd).append('\'');
		sb.append("settleCardNo='").append(HiddenCodeUtil.hiddenBankCardNO(settleCardNo)).append('\'');
		sb.append("settleCardName='").append(HiddenCodeUtil.hiddenIdentityCode(settleCardName)).append('\'');
		sb.append("settleBankCode='").append(settleBankCode).append('\'');
		sb.append("settleBankName='").append(settleBankName).append('\'');
		sb.append("bankProvinceCode='").append(bankProvinceCode).append('\'');
		sb.append("bankCityCode='").append(bankCityCode).append('\'');
		sb.append("merLevel1No='").append(merLevel1No).append('\'');
		sb.append("merLevel2No='").append(merLevel2No).append('\'');
		sb.append("merProvince='").append(merProvince).append('\'');
		sb.append("merCity='").append(merCity).append('\'');
		sb.append("merDistrict='").append(merDistrict).append('\'');
		sb.append("merAddress='").append(merAddress).append('\'');
        sb.append("idcardImg1='").append(idcardImg1).append('\'');
        sb.append("idcardImg2='").append(idcardImg2).append('\'');
        sb.append("bankcardImg='").append(bankcardImg).append('\'');
        sb.append("bankcardImg2='").append(bankcardImg2).append('\'');
        sb.append("ocrLegalIdCard='").append(HiddenCodeUtil.hiddenIdentityCode(ocrLegalIdCard)).append('\'');
        sb.append("ocrSettleCardNo='").append(HiddenCodeUtil.hiddenBankCardNO(ocrSettleCardNo)).append('\'');
        sb.append('}');
        return sb.toString();
    }
}