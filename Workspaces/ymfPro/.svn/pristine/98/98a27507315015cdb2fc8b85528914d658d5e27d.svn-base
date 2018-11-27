package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.facade.laike.dto.AttachmentsRequest;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountRequest;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;
import com.yeepay.g3.facade.laike.facade.OpenAccountFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 入网facade实现
 * Author: jiawen.huang
 * Date: 16/12/14
 * Time: 11:39
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class OpenAccountFacadeImpl extends AbstractFacade implements OpenAccountFacade {

	@Override
	public OpenAccountResponse findOpenAccount(BaseRequest request) {
		return openAccountBiz.findOpenAccount(request);
	}

	@Override
	public OpenAccountResponse checkInviteType(OpenAccountRequest request) {
		return openAccountBiz.checkInviteType(request);
	}

	@Override
	public OpenAccountResponse gatherBaseInfo(OpenAccountRequest request) {
		return openAccountBiz.gatherBaseInfo(request);
	}

	@Override
	public OpenAccountResponse gatherSettleInfo(OpenAccountRequest request) {
		return openAccountBiz.gatherSettleInfo(request);
	}

	@Override
	public OpenAccountResponse gatherAttachments(AttachmentsRequest request) {
		return openAccountBiz.gatherAttachments(request);
	}
}
