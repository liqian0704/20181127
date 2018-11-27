package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;

import java.util.List;

/**
 * Description: 设备服务类
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 17:38
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface DeviceService {

	/**
	 * 根据商编查
	 *
	 * @param merchantNo
	 * @return
	 */
	List<LaikeTermDTO> queryByMerchantNo(String merchantNo);

	/**
	 * 根据商编和机具号查绑定关系
	 *
	 * @param merchantNo
	 * @return
	 */
	void queryByMerchantNoAndSnNo(String merchantNo, String snNo);
}
