package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.QueryBaseRequest;
import com.yeepay.g3.facade.laike.dto.QueryListResponse;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.dto.alliance.ShareStatisticsResponse;
import com.yeepay.g3.facade.laike.dto.alliance.StatisticsResponse;
import com.yeepay.g3.facade.laike.facade.app.AllianceQueryFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 展业查询
 * Author: jiawen.huang
 * Date: 2017/6/23
 * Time: 20:19
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class AllianceQueryFacadeImpl extends AbstractFacade implements AllianceQueryFacade {

	@Override
	public StatisticsResponse queryStatistics(BaseRequest requset) {
		return queryPageBiz.queryStatisticsAll(requset);
	}

	@Override
	public QueryListResponse queryLolTradeDetail(AllianceRequest requset) {
		return queryPageBiz.queryLolTradeDetail(requset);
	}

	@Override
	public QueryListResponse queryAllianceDetail(AllianceRequest requset) {
		return queryPageBiz.queryAllianceDetail(requset);
	}

	@Override
	public QueryListResponse queryAllianceMerchantCount(QueryBaseRequest request) {
		return queryPageBiz.queryAllianceMerchantCount(request);
	}

	@Override
	public ShareStatisticsResponse queryAllianceShareAll(QueryBaseRequest request) {
		return queryPageBiz.queryAllianceShareAll(request);
	}

	@Override
	public QueryListResponse queryAllianceShareDetail(QueryBaseRequest request) {
		return queryPageBiz.queryAllianceShareDetail(request);
	}
}
