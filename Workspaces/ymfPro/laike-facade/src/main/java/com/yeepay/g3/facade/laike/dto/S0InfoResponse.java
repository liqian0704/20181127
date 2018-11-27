package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * Description: S0信息响应DTO
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 14:43
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class S0InfoResponse extends BaseResponse {

	/**
	 * 会员号
	 */
	private String memberNo;

	/**
	 * 代理商
	 */
	private String agentNo;

	/**
	 * 结算银行卡号
	 */
	private String settleCardNo;

	/**
	 * 结算银行编号（总行）
	 */
	private String settleBankCode;

	/**
	 * 结算银行名
	 */
	private String settleBankName;

	/**
	 * 结算银行分行
	 */
	private String branchBankCode;

    /**
     * 结算分行名
     */
    private String branchBankName;

    /**
     * 法人姓名
     */
    private String legalName;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getSettleCardNo() {
		return settleCardNo;
	}

	public void setSettleCardNo(String settleCardNo) {
		this.settleCardNo = settleCardNo;
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

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("S0InfoResponse{");
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("agentNo='").append(agentNo).append('\'');
		sb.append("settleCardNo='").append(HiddenCodeUtil.hiddenBankCardNO(settleCardNo)).append('\'');
		sb.append("settleBankCode='").append(settleBankCode).append('\'');
		sb.append("settleBankName='").append(settleBankName).append('\'');
		sb.append("branchBankCode='").append(branchBankCode).append('\'');
		sb.append("branchBankName='").append(branchBankName).append('\'');
		sb.append("legalName='").append(HiddenCodeUtil.hiddenName(legalName)).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
