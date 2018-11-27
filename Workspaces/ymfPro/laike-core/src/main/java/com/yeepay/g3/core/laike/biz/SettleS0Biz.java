package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.facade.laike.dto.*;

import java.util.List;

/**
 * Description: 秒到结算业务接口
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 14:48
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface SettleS0Biz {

	/**
	 * 查询用户秒到详情-YMF用
	 *
	 * @param request
	 * @return
	 */
	S0InfoResponse findS0Info(S0InfoRequest request);

    /**
     * 查询服务开通信息
     *
     * @param request
     * @return
     */
    List<ProductionResponse> findAppServiceInfo(BaseRequest request);

	/**
	 * 开通S0服务
	 *
	 * @param request
	 * @return
	 */
    ProductionResponse open(OpenProductionRequest request);

}
