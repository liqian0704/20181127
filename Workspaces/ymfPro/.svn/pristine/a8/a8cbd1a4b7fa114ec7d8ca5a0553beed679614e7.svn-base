package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description: 查询biz
 * Author: jiawen.huang
 * Date: 16/11/18
 * Time: 15:08
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface QueryBiz {

	/**
	 * 查询银行所属市信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryBankCity(QueryBankRequest request);

	/**
	 * 获取对应的地区信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryAreaInfo(QueryAreaInfoRequest request);

	/**
	 * 查询分行信息
	 *
	 * @param request
	 * @return
	 */
	QueryResponse queryBranchBank(QueryBankRequest request);

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
     * 卡bin信息查询
     *
     * @param requset
     * @return
     */
    CardBinResponse queryCardBinAndSettleList(CardBinRequset requset);

    /**
     * 查询费率及限额信息
     *
     * @param request
     * @return
     */
    CalFeeResponse queryCalFeeAndLimit(BaseRequest request);

    /**
     * 查询经纬度
     *
     * @param request
     * @return
     */
    QueryResponse queryGeologyLocation(LbsRequest request);
}
