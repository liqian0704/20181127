package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.QueryBaseRequest;
import com.yeepay.g3.facade.laike.dto.QueryListResponse;
import com.yeepay.g3.facade.laike.dto.alliance.AllianceRequest;
import com.yeepay.g3.facade.laike.dto.alliance.ShareStatisticsResponse;
import com.yeepay.g3.facade.laike.dto.alliance.StatisticsResponse;

/**
 * Description: 展业查询
 * Author: jiawen.huang
 * Date: 2017/6/23
 * Time: 15:25
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface AllianceQueryFacade {

	/**
	 * 展业首页信息
	 *
	 * @param requset
	 * @return
	 */
	StatisticsResponse queryStatistics(BaseRequest requset);

	/**
	 * 展业交易详情
	 *
	 * @param requset
	 * @return
	 */
	QueryListResponse queryLolTradeDetail(AllianceRequest requset);

	/**
	 * 展业盟友和商户详情
	 *
	 * @param requset
	 * @return
	 */
	QueryListResponse queryAllianceDetail(AllianceRequest requset);

	/**
	 * 盟友子商户统计
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse queryAllianceMerchantCount(QueryBaseRequest request);

	/**
	 * 联盟分润及奖项查询
	 *
	 * @param request
	 * @return
	 */
	ShareStatisticsResponse queryAllianceShareAll(QueryBaseRequest request);

	/**
	 * 联盟收益记录
	 *
	 * @param request
	 * @return
	 */
	QueryListResponse queryAllianceShareDetail(QueryBaseRequest request);
}
