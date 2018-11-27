package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AllianceService;
import com.yeepay.g3.facade.alliance.dto.common.ResponseMessage;
import com.yeepay.g3.facade.alliance.dto.member.InviteCodeReq;
import com.yeepay.g3.facade.alliance.dto.member.InviteCodeResp;
import com.yeepay.g3.facade.alliance.dto.member.RegisterReq;
import com.yeepay.g3.facade.alliance.dto.member.RegisterResp;
import com.yeepay.g3.facade.alliance.enums.member.MerType;
import com.yeepay.g3.facade.alliance.enums.member.RegisterSource;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Description: 联盟服务
 * Author: jiawen.huang
 * Date: 2017/6/21
 * Time: 19:23
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class AllianceServiceImpl extends AbstractService implements AllianceService {

	@Override
	public String validateInviteCode(String inviteCode) {
		return validateInvite(inviteCode).getCustomerNumber();
	}

	@Override
	public String joinAlliance(String inviteCode, String merchantNo, String merchantName
			, String sales, Date createTime, MerType merType) {
		try {

			RegisterReq request = RegisterReq.RegisterReqBuilder.builder()
					.inviteCode(inviteCode).customerName(merchantName).customerNumber(merchantNo)
					.merType(merType).sales(sales).source(merType.equals(MerType.CHILD) ? RegisterSource.LK : RegisterSource.ZY).registerTime(createTime).build();
			ResponseMessage responseMessage = registerFacade.registerMember(request);
			if (!responseMessage.isSuccess()) {
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.ALC,
						responseMessage.getCode(), responseMessage.getMsg()));
			} else {
				return ((RegisterResp) responseMessage.getContent()).getInviteCode();
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.ALLIANCE_UNKNOW_EXCEPTION, e);
		}
	}

	@Override
	public String getLord(String inviteCode) {
		return validateInvite(inviteCode).getLordCode();
	}

	@Override
	public boolean isOfficialLord(String inviteCode) {
		HashMap<String, String> officialLords = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.ALLIANCE_OFFICIAL_MERCHANT_NO);
		if (!officialLords.containsKey(inviteCode)) {
			return officialLords.containsValue(this.getLord(inviteCode));
		}
		return true;
	}

	private InviteCodeResp validateInvite(String inviteCode) {
		try {
			InviteCodeReq request = InviteCodeReq.InviteCodeReqBuilder.builder().inviteCode(inviteCode).build();
			ResponseMessage responseMessage = registerFacade.validateInviteCode(request);
			if (responseMessage.isSuccess()) {
				InviteCodeResp response = (InviteCodeResp) responseMessage.getContent();
				if ("ACTIVE".equals(response.getInviteStatus())) {
					return response;
				} else {
					throw new LaikeSysException(ErrorCode.ALLIANCE_INVITE_CODE_INACTIVE);
				}
			} else {
				throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.ALC,
						responseMessage.getCode(), responseMessage.getMsg()));
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.ALLIANCE_UNKNOW_EXCEPTION, e);
		}
	}

}
