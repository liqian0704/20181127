package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/4/25
 * Time: 15:58
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Repository
public interface CustomerSettltDetailMapper {

	int save(CustomerSettleDetail entity);

	int batchSave(List<CustomerSettleDetail> settleDetailList);

	CustomerSettleDetail findById(Long id);

	CustomerSettleDetail findByYeepayOrderId(@Param("yeepayOrderId") String yeepayOrderId);

	int update(CustomerSettleDetail entity);

	List<CustomerSettleDetail> listBySettleTime(@Param("customerNumber") String customerNumber,
												@Param("startDate") Date startDate,
												@Param("endDate") Date endDate);

	Map<String, Object> sumS0BySettleTime(@Param("customerNumber") String customerNumber,
										  @Param("startDate") Date startDate,
										  @Param("endDate") Date endDate);
}
