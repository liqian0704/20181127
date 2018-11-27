package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.facade.LikerQueryFacade;
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
public class LikerQueryFacadeImpl extends AbstractFacade implements LikerQueryFacade {

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
}
