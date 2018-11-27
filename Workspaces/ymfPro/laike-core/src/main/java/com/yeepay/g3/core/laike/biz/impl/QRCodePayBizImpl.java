package com.yeepay.g3.core.laike.biz.impl;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.biz.AbstractBiz;
import com.yeepay.g3.core.laike.biz.QRCodePayBiz;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.dto.PassivePayRequest;
import com.yeepay.g3.facade.laike.dto.PassivePayResponse;
import com.yeepay.g3.facade.laike.dto.PayCodeRequest;
import com.yeepay.g3.facade.laike.dto.PayCodeResponse;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import org.springframework.stereotype.Component;

/**
 * Description: 二维码支付业务实现层
 * Author: jiawen.huang
 * Date: 16/11/27
 * Time: 17:38
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class QRCodePayBizImpl extends AbstractBiz implements QRCodePayBiz {

	@Override
	public PayCodeResponse generatePayCode(PayCodeRequest request) {
        String upopLimit = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_UPOP_LIMIT_SWITCH);
        if (upopLimit.equals("1")) {
            //银联主扫限额1000
            if (PaySource.OPEN_UPOP.equals(request.getPaySource()) && request.getOrderAmount().isGreaterThan(new Amount(1000))) {
                throw new LaikeSysException(ErrorCode.OVER_ORDER_LIMIT);
            }
        }
        //查找商编
        UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
        AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
        //银联主扫无法自动报备,PAY_SUCCESS不能使用
        if (PaySource.OPEN_UPOP.equals(request.getPaySource()) && accountOpenEntity.getOpenStatus().getStep() == 6) {
            throw new LaikeSysException(ErrorCode.ACCOUNT_STATUS_DENY);
        }
		return doGeneratePayCode(request, accountOpenEntity);
	}

	@Override
	public PassivePayResponse passivePay(PassivePayRequest request) {
		PassivePayResponse response = new PassivePayResponse();
		UserEntity userEntity = userService.findRegisterByMember(request.getMemberNo());
		AccountOpenEntity accountOpenEntity = accountOpenService.findPayableById(userEntity.getAccountId());
        deviceService.queryByMerchantNoAndSnNo(accountOpenEntity.getMerchantNo(), request.getDeviceSn());
        PassivePayResponseDTO responseDTO = qrPayService.passivePay(request.getOrderAmount(), request.getGratuityAmount(),
				request.getUserPayCode(), accountOpenEntity.getMerchantNo(),
				accountOpenEntity.getMerchantName(), request.getIp(), request.getDeviceSn(), accountOpenEntity.getCompanyType());
		response.setExternalId(responseDTO.getExternalId());
		return response;
	}

	private PayCodeResponse doGeneratePayCode(PayCodeRequest request, AccountOpenEntity accountOpenEntity) {
		//获取支付二维码
		ScanQrCodeResponseDTO responseDTO = qrPayService.generateUnionPayQR(request.getMemberNo(),
                request.getOrderAmount(), request.getPaySource(), request.getGratuityAmount(), accountOpenEntity.getMerchantNo(),
				accountOpenEntity.getMerchantName(), request.getNotSelf(), request.getIp(), accountOpenEntity.getCompanyType(),
				request.getS0Balance());
		PayCodeResponse response = new PayCodeResponse();
		response.setPayUrl(responseDTO.getPayUrl());
		response.setExternalId(responseDTO.getExternalID());
		return response;
	}
}
