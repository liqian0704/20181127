package com.yeepay.g3.facade.laike.facade;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description: 产品费率等查询接口
 * Author: jiawen.huang
 * Date: 16/12/8
 * Time: 15:28
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface ProductInfoFacade {

	/**
     * 获取开通产品接口(客户中心用)
     *
	 * @return
	 */
	@Deprecated
	ProductResponse findProductInfo();

	/**
	 * 新入网产品接口
	 *
	 * @param request
	 * @return
	 */
	NewProductResponse findProductInfo(NewProductRequest request);

	/**
	 * 商户S0信息查询
	 *
	 * @param request
	 * @return
	 */
	S0InfoResponse findS0Info(S0InfoRequest request);

}
