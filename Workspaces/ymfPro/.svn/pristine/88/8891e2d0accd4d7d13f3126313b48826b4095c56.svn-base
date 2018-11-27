package com.yeepay.g3.facade.laike.facade.app;

import com.yeepay.g3.facade.laike.dto.AttachmentsRequest;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;

/**
 * Description: 入网facade
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 16:48
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AppOpenAccountFacade {

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
     * 提交/修改开户基本信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewBaseInfo(OpenAccountRequest request);

    /**
     * 提交/修改结算信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewSettleInfo(OpenAccountRequest request);

    /**
     * 提交/修改附件信息(来客1.1.1小微商户入网使用)
     *
     * @param request
     * @return
     */
    OpenAccountResponse gatherNewAttachments(AttachmentsRequest request);
}
