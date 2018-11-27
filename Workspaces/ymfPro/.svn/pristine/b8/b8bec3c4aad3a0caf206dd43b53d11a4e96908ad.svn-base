package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.RandomUtils;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.SmsCodeEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.SmsCodeService;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:验证码service
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:29
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Component
public class SmsCodeServiceImpl extends AbstractService implements SmsCodeService {

	@Override
	public SmsCodeEntity getAvaliable(String phoneNo, SmsTypeEnum smsType) {
        SmsCodeEntity smsCodeEntity = smsCodeRepository.findOne(phoneNo, smsType);
        if (null != smsCodeEntity && smsCodeEntity.getEffectTime().after(new Date())) {//未过期
			return smsCodeEntity;
		}
		return createOne(phoneNo, smsType);
	}

	@Override
	public void verify(String phoneNo, SmsTypeEnum smsType, String smsCode) {
		SmsCodeEntity smsCodeEntity = findOne(phoneNo, smsType);
		if (smsCode.equals(smsCodeEntity.getSmsCode())) {
			smsCodeRepository.setUnAvaliable(smsCodeEntity.getId());
		} else {
			throw new LaikeSysException(ErrorCode.SMS_VERIFY_ERROR);
		}
	}

	/**
	 * 查询未过期且avaliable有效的
	 *
	 * @param phoneNo
	 * @param smsType
	 * @return
	 */
	private SmsCodeEntity findOne(String phoneNo, SmsTypeEnum smsType) {
        SmsCodeEntity smsCodeEntity = smsCodeRepository.findOne(phoneNo, smsType);
        if (null == smsCodeEntity) {
			throw new LaikeSysException(ErrorCode.SMS_EXPIRED_OR_UNFIND);
		}
		return smsCodeEntity;
	}

	private SmsCodeEntity createOne(String phoneNo, SmsTypeEnum smsType) {
		SmsCodeEntity smsCodeEntity = new SmsCodeEntity();
        smsCodeEntity.setPhoneNo(phoneNo);
        smsCodeEntity.setSmsType(smsType);
		smsCodeEntity.setCreateTime(new Date());
		smsCodeEntity.setEffectTime(DateUtils.addSecond(new Date(), Integer.valueOf((String) ConfigUtils.getSysConfigParam(ConfigEnum.SMS_CODE_EFFECT_INTERVAL))));
		smsCodeEntity.setAvailable(false);
		smsCodeEntity.setSmsCode(RandomUtils.randomNumberString(6));
		smsCodeRepository.save(smsCodeEntity);
		return smsCodeEntity;
	}
}
