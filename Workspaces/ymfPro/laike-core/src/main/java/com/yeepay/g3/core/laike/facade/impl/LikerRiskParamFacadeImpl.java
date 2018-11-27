package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.RiskQueryRequest;
import com.yeepay.g3.facade.laike.dto.RiskQueryResponse;
import com.yeepay.g3.facade.laike.facade.LikerRiskParamFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/2/24
 * Time: 16:24
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class LikerRiskParamFacadeImpl extends AbstractFacade implements LikerRiskParamFacade {

	@Override
	public RiskQueryResponse queryNCPayRiskParam(RiskQueryRequest riskQueryRequest) {
		CheckUtils.notEmpty(riskQueryRequest, "RiskQueryRequest");
		CheckUtils.notEmpty(riskQueryRequest.getExternalSystem(), "ExternalSystem");
		CheckUtils.notEmpty(riskQueryRequest.getMerchantNo(), "MerchantNo");
		return likerRiskParamBiz.queryNCPayRiskParam(riskQueryRequest);
	}
}
