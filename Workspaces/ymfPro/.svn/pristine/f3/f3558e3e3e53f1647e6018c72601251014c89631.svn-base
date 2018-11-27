package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.*;

/**
 * Description:来客增值业务接口
 * Author: jiawen.huang
 * Date: 17/4/18
 * Time: 16:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface LikerProductionFacade {

	/**
	 * 罗列用户所有可以开通和已经开通的产品
	 *
	 * @param request
	 * @return
	 */
	ProductionListResponse list(BaseRequest request);

	/**
	 * 开通服务
	 *
	 * @param request
	 * @return
	 */
	ProductionResponse open(OpenProductionRequest request);

    /**
     * 业务申请接口
     *
     * @param request
     * @return
     */
    BaseResponse applyDevice(ApplyBizRequest request);

	/**
	 * 获取信用卡产品信息
	 *
	 * @param request
	 * @return
	 */
	CreditProductResponse getCreditCardProductInfo(BaseRequest request);

	/**
	 * 申请信用卡
	 *
	 * @param request
	 * @return
	 */
	ApplyCreditCardResponse applyCreditCard(ApplyBizRequest request);

	/**
	 * 获取分享链接(未注册推客用户返回分享链接)
	 *
	 * @param request
	 * @return
	 */
	ShareResponse getShareLink(ShareRequest request);
}
