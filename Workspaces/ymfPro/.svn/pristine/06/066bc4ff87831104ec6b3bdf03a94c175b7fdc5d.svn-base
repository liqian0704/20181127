package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.BankCodeInfoService;
import com.yeepay.g3.facade.laike.dto.BankInfo;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.mer.dto.response.out.MerAreaRespDto;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 银行编码查询
 * Author: jiawen.huang
 * Date: 16/12/19
 * Time: 20:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class BankCodeInfoServiceImpl extends AbstractService implements BankCodeInfoService {

	@Override
	public Map<String, String> queryCity(String provinceCode) {
		return bankInfoQueryFacade.queryCity(provinceCode);
	}

	@Override
	public Map<String, String> queryBranchBank(String headBankCode, String provinceCode, String cityCode) {
		return bankInfoQueryFacade.queryBranchBank(headBankCode, provinceCode, cityCode);
	}

	@Override
	public List queryAreaInfo(String parentCode) {
		try {
			HashMap<String, String> map = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CS_MERCHANT_ERROR_KEY);
			MerAreaRespDto responseDTO = merOutInvokeFacade.queryAreaInfo(parentCode);
			if (responseDTO.getReturnCode().equals(map.get(ExternalReturnCode.CS_MERCHANT_SUCCESS_CODE))) {
				return responseDTO.getAreaDtoList();
			} else {
				throw new LaikeSysException(responseDTO.getReturnCode());
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.MERCHANT_UNKNOW_EXCEPTION, e);
		}
	}

    @Override
    public List querySettleBank() {
        List<String> list = (ArrayList<String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_SETTLE_BANK);
        List<BankInfo> bankInfoList = Lists.newArrayList();
        for (String s : list) {
            bankInfoList.add(JSONUtils.jsonToBean(s, BankInfo.class));
        }
        return bankInfoList;
    }

}
