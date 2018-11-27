package com.yeepay.g3.core.laike.service.impl;


import com.yeepay.g3.core.laike.entity.SecurityControlEntity;
import com.yeepay.g3.core.laike.repository.SecurityControlRepository;
import com.yeepay.g3.core.laike.service.SecurityControlService;
import com.yeepay.g3.facade.laike.enumtype.ControlTypeEnum;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description: 业务权限控制服务层实现
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class SecurityControlServiceImpl implements SecurityControlService {

	@Autowired
	private SecurityControlRepository securityControlRepository;

	@Override
	public void checkFreeze(String phoneNumber, ControlTypeEnum controlTypeEnum) {
        SecurityControlEntity securityControlEntity = securityControlRepository.findByPhoneNumber(phoneNumber, controlTypeEnum);
        if (null != securityControlEntity) {
			if (this.checkFreezeInterval(securityControlEntity, controlTypeEnum)) {
				//超过了冻结时间，清空错误记录
				securityControlEntity.setMistakeTimes(0);
				securityControlEntity.setFirstMistakeTime(null);
				securityControlEntity.setLastMistakeTime(null);
				securityControlEntity.setFreezed(false);
				securityControlRepository.update(securityControlEntity);
			} else {
				//查看是否到了最大错误次数
				if (securityControlEntity.getMistakeTimes() >= securityControlEntity.getControlTypeEnum().getTimes()) {
					securityControlEntity.setFreezed(true);
					securityControlRepository.update(securityControlEntity);
					throw new LaikeSysException(controlTypeEnum.getErrorCode());
				}
			}
		}
	}

	@Override
	public void increaseCount(String phoneNumber, ControlTypeEnum controlTypeEnum) {
        SecurityControlEntity securityControlEntity = securityControlRepository.findByPhoneNumber(phoneNumber, controlTypeEnum);
        Date date = new Date();
		if (null == securityControlEntity) {
			securityControlEntity = new SecurityControlEntity();
            securityControlEntity.setPhoneNumber(phoneNumber);
            securityControlEntity.setControlTypeEnum(controlTypeEnum);
			securityControlEntity.setFirstMistakeTime(date);
			securityControlEntity.setMistakeTimes(1);
			securityControlEntity.setLastMistakeTime(date);
			securityControlEntity.setFreezed(Boolean.FALSE);
			securityControlRepository.save(securityControlEntity);
		} else {
			int count = securityControlEntity.getMistakeTimes();
			if (count == 0) {
				securityControlEntity.setFirstMistakeTime(date);
			}
			count++;
			securityControlEntity.setMistakeTimes(count);
			securityControlEntity.setLastMistakeTime(date);
			securityControlEntity.setFreezed(Boolean.FALSE);
			securityControlRepository.update(securityControlEntity);
		}
	}

	public Boolean checkFreezeInterval(SecurityControlEntity securityControlEntity, ControlTypeEnum controlTypeEnum) {
		Long freezeInterval = controlTypeEnum.getInterval();
		Date now = new Date();
		Long intervalTime = ((now == null ? 0 : now.getTime())
				- (securityControlEntity.getLastMistakeTime() == null ? 0 : securityControlEntity.getLastMistakeTime().getTime())) / 1000;
		return (intervalTime >= freezeInterval);
	}
}
