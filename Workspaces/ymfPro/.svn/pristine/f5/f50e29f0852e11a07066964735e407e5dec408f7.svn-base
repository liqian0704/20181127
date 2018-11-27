package com.yeepay.g3.core.laike.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.SettleS0Service;
import com.yeepay.g3.core.laike.utils.RemoteFacadeFactory;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:S0相关服务
 * Author: wei.li
 * Date: 17/4/26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class SettleS0ServiceImpl extends AbstractService implements SettleS0Service {

	@Override
	public void openS0(String merchantNo) {
		Map<String, String> s0Parm = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.S0_OPEN_PARM);
		s0Parm.put("customerNumber", merchantNo);
		try {
			String response = RemoteFacadeFactory.getHttpPostService(ExternalSystem.RJT_S0, s0Parm, null, null);
			JSONObject jsonObject = JSON.parseObject(response);
			if (!ExternalReturnCode.S0_SUCCESS_CODE.equals(jsonObject.get("returnCode"))) {
				throw new LaikeSysException(ErrorCode.RJT_OPEN_EXCEPTION, jsonObject.get("returnCode") + ":" + jsonObject.get("msg").toString());
			}
		} catch (LaikeSysException e) {
			throw e;
		} catch (JSONException e) {
            throw new LaikeSysException(ErrorCode.RJT_OPEN_EXCEPTION, e);
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.RJT_UNKNOW_EXCEPTION, e);
        }
	}

    @Override
    public void modifyRJTfee(String merchantNo) {
        String baseFeeId = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_RJT_BASE_FEE);
        try {
            if (!transferEntrustSettleFacade.modifyRJTFee(merchantNo, baseFeeId, null, null)) {
                throw new LaikeSysException(ErrorCode.MODIFY_RJT_FEE_DENY);
            }
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.RJT_UNKNOW_EXCEPTION, e);
        }
    }

	@Override
	public boolean notifyYMF(String merchantNo) {
        try {
            BalanceProductRequestDTO request = new BalanceProductRequestDTO();
            request.setCustomerNumber(merchantNo);
            request.setBalanceProduct(BalanceType.S0);
            BalanceProductResponseDTO response = customerBizFacade.doCustomerBalanceProduct(request);
            if (response.getReturnCode().equals(ExternalReturnCode.YMF_SUCCESS_CODE)) {
                return true;
            } else {
                throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.YMF,
                        response.getReturnCode(), response.getReturnMsg()));
            }
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.YMF_UNKNOW_EXCEPTION, e);
        }
	}
}
