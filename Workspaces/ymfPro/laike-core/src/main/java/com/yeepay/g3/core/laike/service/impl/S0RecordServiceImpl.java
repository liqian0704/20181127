package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.S0RecordService;
import com.yeepay.g3.facade.laike.enumtype.OperateTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.ProductStatusEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Description: S0服务接口实现
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 11:01
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class S0RecordServiceImpl extends AbstractService implements S0RecordService {

	@Override
	public void createOne(S0RecordEntity entity) {
		try {
			String id = UIDGenerator.generateBizUID(s0RecordRepository.nextSequence(), BizPrefixEnum.S0.getValue());
			entity.setId(StringUtils.isBlank(entity.getId()) ? id : entity.getId());
			entity.setProductStatus(ProductStatusEnum.INIT);
			s0RecordRepository.save(entity);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.S0_RECORD_EXIST, e);
		}
	}

	@Override
	public S0RecordEntity findOne(String id) {
		S0RecordEntity entity = s0RecordRepository.findById(id);
		if (null == entity) {
			throw new LaikeSysException(ErrorCode.S0_RECORD_NOT_EXIST);
		}
		return entity;
	}

	@Override
	public S0RecordEntity findByMemberNo(String memberNo) {
		S0RecordEntity entity = s0RecordRepository.findByMemberNo(memberNo);
		if (null == entity) {
			throw new LaikeSysException(ErrorCode.S0_RECORD_NOT_EXIST);
		}
		return entity;
	}

	@Override
	public S0RecordEntity findByType(String memberNo, OperateTypeEnum operateTypeEnum) {
		S0RecordEntity entity = s0RecordRepository.findByType(memberNo, operateTypeEnum);
		if (null == entity) {
			throw new LaikeSysException(ErrorCode.S0_RECORD_NOT_EXIST);
		}
		return entity;
	}

	@Override
	public void update(S0RecordEntity entity) {
		Integer num = s0RecordRepository.update(entity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.S0_RECORD_NOT_EXIST);
		}
	}
}
