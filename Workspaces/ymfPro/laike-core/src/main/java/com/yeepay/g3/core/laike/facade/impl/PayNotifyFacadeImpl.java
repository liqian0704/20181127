package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.dto.QueryOrderRequest;
import com.yeepay.g3.facade.laike.dto.QueryTodayResponse;
import com.yeepay.g3.facade.laike.facade.PayNotifyFacade;
import org.springframework.stereotype.Component;

/**
 * Description:支付通知消息接口实现
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:36
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class PayNotifyFacadeImpl extends AbstractFacade implements PayNotifyFacade {

	@Override
	public BaseResponse pushPayMsg2APP(PayNotifyRequest request) {
        //TODO:会员号和商户号 PayNotifyRequest应该回传memberNo
        AccountOpenEntity entity = openAccountBiz.findByMerchantNo(request.getMerchantNo());
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setMemberNo(entity.getMemberNo());
        //今日交易数据
		QueryTodayResponse response = queryPageBiz.queryToday(queryOrderRequest);
		request.setTodayTotalAmount(response.getTotalAmount());
        request.setTodayTotalCount(response.getTotalCount());
		request.setAccountBalance(response.getAccountBalance());
		return appNotifyBiz.pushPayMsg2APP(request);
	}
}
