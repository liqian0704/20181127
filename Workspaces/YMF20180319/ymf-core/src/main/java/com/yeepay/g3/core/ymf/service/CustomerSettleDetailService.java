package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 出款详情服务层接口
 * Author: jiawen.huang
 * Date: 17/4/26
 * Time: 20:12
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface CustomerSettleDetailService {

	/**
	 * 存在就更新，不存在创建一条出款记录
	 *
	 * @param settleDetail
	 * @return
	 */
	int createAndUpdate(CustomerSettleDetail settleDetail);

	/**
	 * 批量创建
	 *
	 * @param settleDetailList
	 * @return
	 */
	int create(List<CustomerSettleDetail> settleDetailList);

	/**
	 * 根据易宝订单号查询
	 *
	 * @param yeepayOrderId
	 * @return
	 */
	CustomerSettleDetail findByYeepayOrderId(String yeepayOrderId);

	/**
	 * 查询秒到汇总
	 *
	 * @param customerNumber
	 * @param startSettleDate
	 * @param endSettleDate
	 * @return
	 */
	Map<String, Object> sumS0BySettleTime(String customerNumber, Date startSettleDate, Date endSettleDate);

	/**
	 * 根据商编和出款时间查询
	 *
	 * @param customerNumber
	 * @param startDate
	 * @param endDate
	 * @return mapkey:customerNumber,realAmount,settleFee,trxFee,count
	 */
	List<CustomerSettleDetail> listBySettleTime(String customerNumber, Date startDate, Date endDate);
}
