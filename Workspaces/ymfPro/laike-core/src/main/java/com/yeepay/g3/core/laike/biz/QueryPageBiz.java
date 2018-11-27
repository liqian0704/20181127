package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.core.laike.utils.AppKey;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.dto.alliance.ShareStatisticsResponse;
import com.yeepay.g3.facade.laike.dto.alliance.StatisticsResponse;
import com.yeepay.g3.facade.laike.dto.boss.RegisterMerRequest;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;

/**
 * Description: 分页查询
 * Author: jiawen.huang
 * Date: 2017/6/24
 * Time: 14:14
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface QueryPageBiz {

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
	@AppKey(value = AppSourceEnum.LIKER)
	QueryListResponse queryOrder(QueryOrderRequest request);

	/**
	 * 到账查询
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.LIKER)
	QueryListResponse querySettle(QueryOrderRequest request);

	/**
	 * 到账查询
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.LIKER)
	QueryListResponse querySettleAll(QueryOrderRequest request);

	/**
	 * 查ymf商户表
	 *
	 * @param registerMerRequest
	 * @return
	 */
	@AppKey(value = AppSourceEnum.LIKER)
	QueryListResponse queryYmfCustomer(RegisterMerRequest registerMerRequest);

	/**
	 * 当日收款查询接口
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.LIKER)
	QueryTodayResponse queryToday(QueryOrderRequest request);

	/**
	 * 展业首页信息
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	StatisticsResponse queryStatisticsAll(BaseRequest request);

	/**
	 * 展业交易详情（分子商户&盟友子商户）
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	QueryListResponse queryLolTradeDetail(AllianceRequest request);

	/**
	 * 展业盟友和子商户详情
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	QueryListResponse queryAllianceDetail(AllianceRequest request);

	/**
	 * 盟友子商户统计
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	QueryListResponse queryAllianceMerchantCount(QueryBaseRequest request);

	/**
	 * 联盟分润及奖项查询
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	ShareStatisticsResponse queryAllianceShareAll(QueryBaseRequest request);

	/**
	 * 联盟收益记录
	 *
	 * @param request
	 * @return
	 */
	@AppKey(value = AppSourceEnum.ALLIANCE)
	QueryListResponse queryAllianceShareDetail(QueryBaseRequest request);

    /**
     * 查询网点名称列表
     *
     * @param request
     * @return
     */
    @AppKey(value = AppSourceEnum.LIKER)
    QueryListResponse queryShopName(QueryBaseRequest request);
}
