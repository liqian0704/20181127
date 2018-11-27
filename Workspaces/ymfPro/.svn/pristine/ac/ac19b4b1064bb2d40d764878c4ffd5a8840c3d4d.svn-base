package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description: 查询facade
 * Author: jiawen.huang
 * Date: 16/11/18
 * Time: 14:41
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppLikerQueryFacade {

	/**
	 * 查询消息
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse queryMsg(QueryPushMsgRequest request);

	/**
	 * 交易查询
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse queryOrder(QueryOrderRequest request);

	/**
	 * 到账查询
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse querySettle(QueryOrderRequest request);

	/**
	 * 到账查询
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse querySettleAll(QueryOrderRequest request);

	/**
	 * 查询银行所属市信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryBankCity(QueryBankRequest request);

	/**
	 * 查询分行信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryBranchBank(QueryBankRequest request);

	/**
	 * 获取对应的地区信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryAreaInfo(QueryAreaInfoRequest request);

	/**
	 * 查余
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryAccountBalance(BaseRequest request);

	/**
	 * 查台签
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryQRCode(BaseRequest request);

    /**
     * 查结算银行列表
     *
     * @param request
     * @return
     */
    QueryResponse querySettleBank(BaseRequest request);

    /**
     * 当日收款查询接口
     *
     * @param request
     * @return
     */
    QueryTodayResponse queryToday(QueryOrderRequest request);

    /**
     * 卡bin查询接口
     *
     * @param request
     * @return
     */
    CardBinResponse queryCardBinAndSettleList(CardBinRequset request);

	/**
	 * 网点名称查询
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse queryShopName(QueryBaseRequest request);

    /**
     * 查询费率及限额信息
     *
     * @param request
     * @return
     */
    CalFeeResponse queryCalFeeAndLimit(BaseRequest request);

    /**
     * 查询地理位置信息
     *
     * @param request
     * @return
     */
    QueryResponse queryGeologyLocation(LbsRequest request);
}
