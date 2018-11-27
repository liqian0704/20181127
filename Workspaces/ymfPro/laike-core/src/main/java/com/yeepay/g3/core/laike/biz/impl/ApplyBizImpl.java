package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.laike.utils.EnvironmemntUtil;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.ApplyBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.*;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.Base64;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description: 业务申请类
 * Author: wei.li
 * Date: 17/5/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class ApplyBizImpl extends AbstractBiz implements ApplyBiz {

    @Override
    public BaseResponse applyDevice(ApplyBizRequest request) {
        saveOperateRecord(request);
        return new BaseResponse();
    }

    @Override
    public ApplyCreditCardResponse applyCreditCard(ApplyBizRequest request) {
        request.setBizType(BizTypeEnum.CREDIT_CARD_APPLY);
        saveOperateRecord(request);
        ApplyCreditCardResponse response = new ApplyCreditCardResponse();
        response.setApplyUrl(((HashMap<String, String>) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CREDIT_CARD_URL)).get(request.getDeviceInfo()));
        return response;
    }

    @Override
    public CreditProductResponse getCreditCardProductInfo(BaseRequest request) {
        String productJson = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_CREDIT_CARD_PRODUCT);
        ProductInfo[] productInfos = (ProductInfo[]) JSONUtils.jsonToArray(productJson, ProductInfo.class);
        List<ProductInfo> productInfoList = Arrays.asList(productInfos);
        CreditProductResponse response = new CreditProductResponse();
        response.setProductInfoList(productInfoList);
        return response;
    }

    @Override
    public ShareResponse getShareLink(ShareRequest request) {
        ShareResponse response = new ShareResponse();
        UserEntity entity = userService.findByMemberNo(request.getMemberNo());
        String webHost = EnvironmemntUtil.getWebHost();
        String shareSuffix = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_SHARE_SUFFIX);
        if (null != entity && null != entity.getInviteCode() && entity.getInviteCode().length() > 6) {
            if (null == userService.findByPhoneNo(entity.getPhoneNo(), AppSourceEnum.ALLIANCE)) {
                response.setShareUrl(webHost + shareSuffix + "?status=noreg&phone=" + Base64.encode(entity.getPhoneNo()) + "&invitecode=" + Base64.encode(entity.getInviteCode()));
            } else {
                if (BoolEnum.TRUE.equals(request.getHasAlliance())) {
                    response.setShareUrl(webHost + shareSuffix + "?status=invoke");
                } else {
                    response.setShareUrl(webHost + shareSuffix + "?status=download");
                }
            }
            return response;
        }
        throw new LaikeSysException(ErrorCode.SHARE_PERMISSION_DENY);
    }

    /**
     * 保存申请记录
     */
    private void saveOperateRecord(ApplyBizRequest request) {
        AccountOpenEntity entity = accountOpenService.findByMemberNo(request.getMemberNo());
        OperateRecordEntity operateRecordEntity = new OperateRecordEntity();
        operateRecordEntity.setMemberNo(request.getMemberNo());
        operateRecordEntity.setBizType(request.getBizType());
        operateRecordEntity.setBizParam(request.getDeviceInfo());
        operateRecordEntity.setLinkName(request.getLinkName());
        operateRecordEntity.setCreateTime(new Date());
        operateRecordEntity.setRemark(request.getRemark());
        //不传取默认
        if (null == request.getLinkProvinceName() || null == request.getLinkMerCityName() || null == request.getLinkPhone()) {
            if (null != entity) {
                if (StringUtils.isNotEmpty(entity.getMerProvinceName())) {
                    operateRecordEntity.setProvinceName(entity.getMerProvinceName());
                }
                if (StringUtils.isNotEmpty(entity.getMerCityName())) {
                    operateRecordEntity.setCityName(entity.getMerCityName());
                }
                if (StringUtils.isNotEmpty(entity.getPhoneNo())) {
                    operateRecordEntity.setLinkPhone(entity.getPhoneNo());
                }
            }
        } else {
            operateRecordEntity.setProvinceName(request.getLinkProvinceName());
            operateRecordEntity.setCityName(request.getLinkMerCityName());
            operateRecordEntity.setLinkPhone(request.getLinkPhone());
        }
        operateRecodeService.createAndUpdateByDate(operateRecordEntity);
    }
}
