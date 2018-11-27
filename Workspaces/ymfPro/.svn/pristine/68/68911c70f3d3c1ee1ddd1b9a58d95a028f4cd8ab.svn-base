package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AccountManageService;
import com.yeepay.g3.facade.account.manage.dto.AccBalAndStatusQueryResultDTO;
import com.yeepay.g3.facade.account.manage.enums.AccountGtypeEnum;
import com.yeepay.g3.facade.account.manage.params.BalanceQueryParams;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 账户管理impl
 * Author: jiawen.huang
 * Date: 16/12/20
 * Time: 17:05
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class AccountManageServiceImpl extends AbstractService implements AccountManageService {

	@Override
	public Map<String, String> getBalance(String merchantNo) {
		try {
			BalanceQueryParams requestDTO = new BalanceQueryParams();
			requestDTO.setRequestor(ExternalSystem.LIKER.getKey());
			requestDTO.setAccGennerationType(AccountGtypeEnum.G2_ACCOUNT);
			requestDTO.setCustomerNo(merchantNo);
			List<AccBalAndStatusQueryResultDTO> responseDTOList = accountBalanceAndStatusQueryFacade.
					queryAccountBalAndStatusByCustomerNo(requestDTO);
			AccBalAndStatusQueryResultDTO responseDTO = responseDTOList.get(0);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("balance", new Amount(responseDTO.getBalance()).toString());
			resultMap.put("availableBalance", new Amount(responseDTO.getAvaibleBalance()).toString());
			resultMap.put("frozenFund", new Amount(responseDTO.getFrozenFund()).toString());
			return resultMap;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.ACCOUNT_BALANCE_EXCEPTION, e);
		}
	}
}
