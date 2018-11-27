package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.TradeLimitService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.laike.dto.CalFeeInfo;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.riskcontrol.facade.v2.TradelimitDataQueryRequestDto;
import com.yeepay.riskcontrol.facade.v2.TradelimitDataQueryResponseDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 限额相关服务
 * Author: wei.li
 * Date: 17/8/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class TradeLimitServiceImpl extends AbstractService implements TradeLimitService {

    @Override
    public CalFeeInfo limitQuery(String merchantNo, String productionId, CompanyTypeEnum companyType) {
        Map<String, String> enterpriseLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_ENTERPRISE_LIMIT);
        Map<String, String> microLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_MICRO_LIMIT);
        Map<String, String> individualLimitMap = (HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_INDIVIDUAL_LIMIT);

        try {
            TradelimitDataQueryRequestDto request = new TradelimitDataQueryRequestDto();
            CalFeeInfo calFeeInfo = new CalFeeInfo();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("MERCHANTNO", merchantNo);
            request.setInterceptValueMap(map);
            request.setProductId(productionId);
            request.setProduction(ConstantUtil.LK);
            TradelimitDataQueryResponseDto responseDto = tradeLimitConfigQueryFacade.queryTradelimitInfoByTradeinfo(request);
            if (ExternalReturnCode.RISK_CONTROL_SUCCESS_CODE.equals(responseDto.getRetCode())) {
                calFeeInfo.setSingleAmount(responseDto.getSingleAmount());
                calFeeInfo.setDayAmount(responseDto.getDayAmount());
                calFeeInfo.setMonthAmount(responseDto.getMonthAmount());
            } else {
                if ("FE".equals(productionId)) {
                    if (CompanyTypeEnum.MICRO.equals(companyType)) {
                        calFeeInfo.setSingleAmount(microLimitMap.get("OTHER_ORDER"));
                        calFeeInfo.setDayAmount(microLimitMap.get("OTHER_DAY"));
                        calFeeInfo.setMonthAmount(microLimitMap.get("OTHER_MONTH"));
                    } else if (CompanyTypeEnum.INDIVIDUAL.equals(companyType)) {
                        calFeeInfo.setSingleAmount(individualLimitMap.get("OTHER_ORDER"));
                        calFeeInfo.setDayAmount(individualLimitMap.get("OTHER_DAY"));
                        calFeeInfo.setMonthAmount(individualLimitMap.get("OTHER_MONTH"));
                    } else if (CompanyTypeEnum.ENTER_UNION.equals(companyType) || CompanyTypeEnum.ENTERPRISE.equals(companyType)) {
                        calFeeInfo.setSingleAmount(enterpriseLimitMap.get("OTHER_ORDER"));
                        calFeeInfo.setDayAmount(enterpriseLimitMap.get("OTHER_DAY"));
                        calFeeInfo.setMonthAmount(enterpriseLimitMap.get("OTHER_MONTH"));
                    } else {
                        throw new LaikeSysException(ErrorCode.COMMIT_ATTACH_ILLEGAL);
                    }
                } else if ("NOCARDPAY".equals(productionId)) {
                    if (CompanyTypeEnum.MICRO.equals(companyType)) {
                        calFeeInfo.setSingleAmount(microLimitMap.get("ONE_KEY_PAY_ORDER"));
                        calFeeInfo.setDayAmount(microLimitMap.get("ONE_KEY_PAY_DAY"));
                        calFeeInfo.setMonthAmount(microLimitMap.get("ONE_KEY_PAY_MONTH"));
                    } else if (CompanyTypeEnum.INDIVIDUAL.equals(companyType)) {
                        calFeeInfo.setSingleAmount(individualLimitMap.get("ONE_KEY_PAY_ORDER"));
                        calFeeInfo.setDayAmount(individualLimitMap.get("ONE_KEY_PAY_DAY"));
                        calFeeInfo.setMonthAmount(individualLimitMap.get("ONE_KEY_PAY_MONTH"));
                    } else if (CompanyTypeEnum.ENTER_UNION.equals(companyType) || CompanyTypeEnum.ENTERPRISE.equals(companyType)) {
                        calFeeInfo.setSingleAmount(enterpriseLimitMap.get("ONE_KEY_PAY_ORDER"));
                        calFeeInfo.setDayAmount(enterpriseLimitMap.get("ONE_KEY_PAY_DAY"));
                        calFeeInfo.setMonthAmount(enterpriseLimitMap.get("ONE_KEY_PAY_MONTH"));
                    } else {
                        throw new LaikeSysException(ErrorCode.COMMIT_ATTACH_ILLEGAL);
                    }
                }
            }
            return calFeeInfo;
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.RISK_CONTROL_UNKNOW_EXCEPTION, e);
        }
    }
}
