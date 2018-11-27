package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.dto.*;

import java.util.Map;

/**
 * Description: 账户业务接口
 * Author: jiawen.huang
 * Date: 16/11/30
 * Time: 16:55
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface OpenAccountBiz {

	/**
	 * 查询开户状态
	 *
	 * @param request
	 * @return
	 */
	OpenAccountResponse findOpenAccount(BaseRequest request);

	/**
	 * 入网
	 *
	 * @param request
	 * @return
	 */
	OpenAccountResponse checkInviteType(OpenAccountRequest request);

	/**
	 * 提交/修改开户基本信息
	 *
	 * @param request
	 * @return
	 */
	OpenAccountResponse gatherBaseInfo(OpenAccountRequest request);

	/**
	 * 提交/修改结算信息
	 *
	 * @param request
	 * @return
	 */
	OpenAccountResponse gatherSettleInfo(OpenAccountRequest request);

	/**
	 * 提交/修改附件信息
	 *
	 * @param request
	 * @return
	 */
	OpenAccountResponse gatherAttachments(AttachmentsRequest request);

	/**
	 * 回调
	 *
	 * @param request
	 * @return
	 */
	BaseResponse callbackResult(Map<String, String> request);

	/**
	 * 入网查询产品信息
	 *
	 * @param request
	 * @return
	 */
	NewProductResponse findProductInfo(NewProductRequest request);

    /**
     * 通过商编查开户信息
     *
     * @param merchantNo
     * @return
     */
    AccountOpenEntity findByMerchantNo(String merchantNo);
}
