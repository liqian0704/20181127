package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.OperateRecodeService;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.DateUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description: 用户操作服务实现类
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class OperateRecodeServiceImpl extends AbstractService implements OperateRecodeService {

    @Override
    public OperateRecordEntity findById(Long id) {
        OperateRecordEntity entity = operateRecodeRepository.findById(id);
        if (null == entity) {
            throw new LaikeSysException(ErrorCode.OPERATE_RECORD_NOT_EXIST);
        }
		return entity;
	}

    @Override
    public void save(OperateRecordEntity entity) {
        try {
            operateRecodeRepository.save(entity);
        } catch (DuplicateKeyException e) {
            throw new LaikeSysException(ErrorCode.OPERATE_RECORD_EXIST, e);
        }
    }

	@Override
	public void save(String memberNo, BizTypeEnum bizTypeEnum, String bizParam) {
		try {
			OperateRecordEntity entity = new OperateRecordEntity();
			entity.setMemberNo(memberNo);
			entity.setBizType(bizTypeEnum);
			entity.setBizParam(bizParam);
			operateRecodeRepository.save(entity);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.OPERATE_RECORD_EXIST, e);
		}
	}

	@Override
	public OperateRecordEntity findByMemberNoAndBiz(String memberNo, BizTypeEnum bizTypeEnum) {
		return operateRecodeRepository.findByMemberNoAndBiz(memberNo, bizTypeEnum.getValue());
	}

    @Override
    public void update(OperateRecordEntity entity) {
        Integer num = operateRecodeRepository.update(entity);
        if (0 == num) {
            throw new LaikeSysException(ErrorCode.OPERATE_RECORD_NOT_EXIST);
        }
	}

	@Override
	public List<OperateRecordEntity> findByDate(BizTypeEnum bizTypeEnum, Date startDate, Date endDate) {
		return operateRecodeRepository.findByDate(bizTypeEnum.getValue(), startDate, endDate);
	}

    @Override
    public void createAndUpdateByDate(OperateRecordEntity entity) {
		OperateRecordEntity operateRecordEntity = operateRecodeRepository
                .findByLinkPhoneAndDate(entity.getLinkPhone(), entity.getBizParam(),
                        DateUtils.getDayStart(entity.getCreateTime()),
						DateUtils.getDayEnd(entity.getCreateTime()));
		if (operateRecordEntity == null) {
			try {
                operateRecodeRepository.save(entity);
            } catch (DuplicateKeyException e) {
                throw new LaikeSysException(ErrorCode.OPERATE_RECORD_EXIST, e);
            }
        } else {
            operateRecordEntity.addCount();
            operateRecordEntity.setCityName(entity.getCityName());
            operateRecordEntity.setProvinceName(entity.getProvinceName());
            Integer num = operateRecodeRepository.update(operateRecordEntity);
            if (0 == num) {
                throw new LaikeSysException(ErrorCode.OPERATE_RECORD_NOT_EXIST);
            }
        }
    }

    @Override
    public OperateRecordEntity findLastRecode(String memberNo, BizTypeEnum bizTypeEnum) {
        return operateRecodeRepository.findLastRecode(memberNo, bizTypeEnum.getValue());
    }
}
