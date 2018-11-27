package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.common.laike.utils.RandomUtils;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.SmsCodeEntity;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;
import com.yeepay.g3.utils.common.DateUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SmsCodeTest extends BaseTest {

	@Autowired
	private SmsCodeRepository smsCodeRepository;

	//	@Rollback(false)
	@Test
	public void save() {
		SmsCodeEntity smsCodeEntity = new SmsCodeEntity();
		smsCodeEntity.setPhoneNo("18519397782");
		smsCodeEntity.setSmsType(SmsTypeEnum.REGISTER);
		smsCodeEntity.setEffectTime(DateUtils.addSecond(new Date(),
				(Integer) ConfigUtils.getSysConfigParam(ConfigEnum.SMS_CODE_EFFECT_INTERVAL)));
		smsCodeEntity.setAvailable(false);
		smsCodeEntity.setSmsCode(RandomUtils.randomNumberString(6));
		smsCodeRepository.save(smsCodeEntity);
	}

	//		@Rollback(false)
	@Test
	public void setUnAvaliable() {
		SmsCodeEntity smsCodeEntity = smsCodeRepository.findOne("18519397782", SmsTypeEnum.REGISTER);
		Assert.assertTrue(smsCodeEntity.getAvailable());
		int num = smsCodeRepository.setUnAvaliable(smsCodeEntity.getId());
		Assert.assertEquals(1, num);
	}
}
