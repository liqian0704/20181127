package com.yeepay.g3.core.laike.facade.app.impl;

import com.yeepay.g3.core.laike.facade.impl.AbstractFacade;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.app.AppLikerQueryFacade;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/18
 * Time: 15:05
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class AppLikerQueryFacadeImpl extends AbstractFacade implements AppLikerQueryFacade {

	@Override
	public QueryListResponse queryMsg(QueryPushMsgRequest request) {
		return queryPageBiz.queryMsg(request);
	}

	@Override
	public QueryListResponse queryOrder(QueryOrderRequest request) {
		return queryPageBiz.queryOrder(request);
	}

	@Override
	public QueryListResponse querySettle(QueryOrderRequest request) {
		return queryPageBiz.querySettle(request);
	}

	@Override
	public QueryListResponse querySettleAll(QueryOrderRequest request) {
		return queryPageBiz.querySettleAll(request);
	}

	@Override
	public QueryResponse queryBankCity(QueryBankRequest request) {
		return queryBiz.queryBankCity(request);
	}

	@Override
	public QueryResponse queryBranchBank(QueryBankRequest request) {
		return queryBiz.queryBranchBank(request);
	}

	@Override
	public QueryResponse queryAreaInfo(QueryAreaInfoRequest request) {
		return queryBiz.queryAreaInfo(request);
	}

	@Override
	public QueryResponse queryAccountBalance(BaseRequest request) {
		return queryBiz.queryAccountBalance(request);
	}

	@Override
	public QueryResponse queryQRCode(BaseRequest request) {
		return queryBiz.queryQRCode(request);
	}

	@Override
	public QueryResponse querySettleBank(BaseRequest request) {
		return queryBiz.querySettleBank(request);
	}

    @Override
	public QueryTodayResponse queryToday(QueryOrderRequest request) {
		return queryPageBiz.queryToday(request);
	}

    @Override
    public CardBinResponse queryCardBinAndSettleList(CardBinRequset request) {
        return queryBiz.queryCardBinAndSettleList(request);
    }

	@Override
	public QueryListResponse queryShopName(QueryBaseRequest request) {
		return queryPageBiz.queryShopName(request);
	}

    @Override
    public CalFeeResponse queryCalFeeAndLimit(BaseRequest request) {
        return queryBiz.queryCalFeeAndLimit(request);
    }

    @Override
    public QueryResponse queryGeologyLocation(LbsRequest request) {
        return queryBiz.queryGeologyLocation(request);
    }
}
