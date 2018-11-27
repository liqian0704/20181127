package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.CreditService;
import com.yeepay.g3.core.laike.utils.ConstantUtil;
import com.yeepay.g3.facade.consumerloan.dto.CashLoanLinkBuildRequest;
import com.yeepay.g3.facade.consumerloan.dto.CashLoanLinkBuildResponse;
import com.yeepay.g3.facade.consumerloan.enumtype.IdentityTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.ErrorCodeSource;
import com.yeepay.g3.facade.laike.exception.ExternalReturnCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.springframework.stereotype.Service;

/**
 * Description: 贷款服务Service
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class CreditServiceImpl extends AbstractService implements CreditService {

    @Override
    public String getCreditLink(String ip, String imei, String channelId, AccountOpenEntity accountOpenEntity) {
        try {
            CashLoanLinkBuildRequest request = convert2Req(ip, imei, channelId, accountOpenEntity);
            CashLoanLinkBuildResponse response = cashLoanFacade.buildCashLoanLink(request);
            if (!ExternalReturnCode.CFL_SUCCESS_CODE.equals(response.getStatus().toString())) {
                throw new LaikeSysException(errorCodeTranslator.translateCode(ErrorCodeSource.CFL,
                        response.getResponseCode(), response.getResponseMsg()));
            }
            return response.getLoanUrl();
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.CFL_UNKNOW_EXCEPTION, e);
        }
    }

    private CashLoanLinkBuildRequest convert2Req(String ip, String imei, String channelId, AccountOpenEntity accountOpenEntity) {
        CashLoanLinkBuildRequest request = new CashLoanLinkBuildRequest();
        request.setBiz(ConstantUtil.LIKER);
        request.setRequestNo(UIDGenerator.generateSysUID(BizPrefixEnum.CL.getValue()));
        request.setMerchantAccount(accountOpenEntity.getMerchantNo());
        request.setIdentityId(accountOpenEntity.getPhoneNo());
        request.setIdentityType(IdentityTypeEnum.PHONE);
        request.setName(accountOpenEntity.getLegalName());
        request.setCardNo(accountOpenEntity.getSettleCardNo());
        request.setIdCardNo(accountOpenEntity.getLegalIdCard());
        request.setPhone(accountOpenEntity.getPhoneNo());
        request.setAddress(accountOpenEntity.getMerAddress());
        request.setIp(ip);
        request.setImei(imei);
        request.setChannelId(channelId);
        return request;
    }
}
