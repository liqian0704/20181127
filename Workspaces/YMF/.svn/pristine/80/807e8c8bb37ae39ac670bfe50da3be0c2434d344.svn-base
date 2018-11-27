package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.customer.CustomerSettltDetailMapper;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 出款详情服务层
 * Author: jiawen.huang
 * Date: 17/4/27
 * Time: 10:37
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class CustomerSettleDetailServiceImpl implements CustomerSettleDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSettleDetailServiceImpl.class);

	@Autowired
	private CustomerSettltDetailMapper customerSettltDetailMapper;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int createAndUpdate(CustomerSettleDetail settleDetail) {
		CheckUtils.notEmpty(settleDetail.getYeepayOrderId(), "yeepayOrderId");
		CheckUtils.notEmpty(settleDetail.getSettleType(), "settleType");
		CustomerSettleDetail existEntity = customerSettltDetailMapper.findByYeepayOrderId(settleDetail.getYeepayOrderId());
		if (null == existEntity) {
			LOGGER.info("创建秒到出款记录:" + settleDetail.toString());
			return customerSettltDetailMapper.save(settleDetail);
		} else {
			LOGGER.info("更新秒到出款记录:" + settleDetail.toString());
			return customerSettltDetailMapper.update(settleDetail);
		}
	}

	@Override
	public int create(List<CustomerSettleDetail> settleDetailList) {
		for (CustomerSettleDetail settleDetail : settleDetailList) {
			CheckUtils.notEmpty(settleDetail.getYeepayOrderId(), "yeepayOrderId");
			CheckUtils.notEmpty(settleDetail.getSettleType(), "settleType");
		}
		return customerSettltDetailMapper.batchSave(settleDetailList);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public CustomerSettleDetail findByYeepayOrderId(String yeepayOrderId) {
		return customerSettltDetailMapper.findByYeepayOrderId(yeepayOrderId);
	}

	@Override
	public Map<String, Object> sumS0BySettleTime(String customerNumber, Date startSettleDate, Date endSettleDate) {
		return customerSettltDetailMapper.sumS0BySettleTime(customerNumber, startSettleDate, endSettleDate);
	}

	@Override
	public List<CustomerSettleDetail> listBySettleTime(String customerNumber, Date startDate, Date endDate) {
		return customerSettltDetailMapper.listBySettleTime(customerNumber, startDate, endDate);
	}
}
