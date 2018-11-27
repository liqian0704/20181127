package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.LikerRiskParamBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.dto.RiskQueryRequest;
import com.yeepay.g3.facade.laike.dto.RiskQueryResponse;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

/**
 * Description: 来客交易风险系数biz实现
 * Author: jiawen.huang
 * Date: 17/2/24
 * Time: 16:34
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class LikerRiskParamBizImpl extends AbstractBiz implements LikerRiskParamBiz {

	@Override
	public RiskQueryResponse queryNCPayRiskParam(RiskQueryRequest riskQueryRequest) {
		if (!riskQueryRequest.getExternalSystem().equals(ExternalSystem.YMF)) {
			throw new LaikeSysException(ErrorCode.PARAM_EXCEPTION);
		}
		AccountOpenEntity accountOpenEntity = accountOpenService.findByMerchantNo(riskQueryRequest.getMerchantNo());
		if (null == accountOpenEntity) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
		}
		RiskQueryResponse response = new RiskQueryResponse();
		response.setCompanyType(accountOpenEntity.getCompanyType());
		response.setLegalIdCard(accountOpenEntity.getLegalIdCard());
		response.setLegalName(accountOpenEntity.getLegalName());
		response.setMerchantName(accountOpenEntity.getMerchantName());
		response.setMerchantNo(accountOpenEntity.getMerchantNo());
		return response;
	}
}
