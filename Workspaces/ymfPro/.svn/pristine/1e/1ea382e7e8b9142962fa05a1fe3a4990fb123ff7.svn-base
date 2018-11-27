package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.CreditBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.utils.ValidateParamUtil;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * Description: 贷款相关服务Biz
 * Author: wei.li
 * Date: 17/3/29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class CreditBizImpl extends AbstractBiz implements CreditBiz {

    @Override
    public CreditResponse getCreditLink(CreditRequset requset) {
        AccountOpenEntity accountOpenEntity = checkRequset(requset.getMemberNo());
        String ip = ValidateParamUtil.isIp(requset.getIp()) ? requset.getIp() : (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_IP_ADDRESS);
        String loanUrl = creditService.getCreditLink(ip, requset.getImei(), requset.getChannelId(), accountOpenEntity);
        CreditResponse response = new CreditResponse();
        response.setLoanUrl(loanUrl);
        return response;
    }

    @Override
    public CreditProductResponse getCreditProductInfo(BaseRequest request) {
        checkRequset(request.getMemberNo());
        String productJson = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CREDIT_PRODUCT);
        ProductInfo[] productInfos = (ProductInfo[]) JSONUtils.jsonToArray(productJson, ProductInfo.class);
        List<ProductInfo> productInfoList = Arrays.asList(productInfos);
        CreditProductResponse response = new CreditProductResponse();
        response.setProductInfoList(productInfoList);
        return response;
    }

    private AccountOpenEntity checkRequset(String memberNo) {
        AccountOpenEntity accountOpenEntity = accountOpenService.findByMemberNo(memberNo);
        if (null == accountOpenEntity) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_NOT_EXIST);
        }
        //非大商户入网，是小微、个体工商户、且审核通过
        if (!InviteType.BIG_MERCHANT.equals(accountOpenEntity.getInviteType())) {
            if (CompanyTypeEnum.INDIVIDUAL.equals(accountOpenEntity.getCompanyType()) || CompanyTypeEnum.MICRO.equals(accountOpenEntity.getCompanyType())) {
                if (!(OpenStatusEnum.SUCCESS.equals(accountOpenEntity.getOpenStatus()) || OpenStatusEnum.PAY_SUCCESS.equals(accountOpenEntity.getOpenStatus()))) {
                    throw new LaikeSysException(ErrorCode.COMMIT_CREDIT_REQ_DENY);
                }
            } else {
                throw new LaikeSysException(ErrorCode.COMMIT_CREDIT_REQ_DENY);
            }
        } else {
            throw new LaikeSysException(ErrorCode.COMMIT_CREDIT_REQ_DENY);
        }
        return accountOpenEntity;
    }

}
