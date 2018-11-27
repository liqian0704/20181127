package com.yeepay.g3.core.laike.service;

import java.util.List;
import java.util.Map;

/**
 * Description: 银行编码查询
 * Author: jiawen.huang
 * Date: 16/12/19
 * Time: 19:52
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface BankCodeInfoService {

	/**
	 * 省编码查询市
	 *
	 * @param provinceCode
	 * @return
	 */
	Map<String, String> queryCity(String provinceCode);

	/**
	 * 查询支行信息
	 *
	 * @param headBankCode 总行代码
	 * @param provinceCode 省代码
	 * @param cityCode     市代码
	 * @return
	 */
	Map<String, String> queryBranchBank(String headBankCode, String provinceCode, String cityCode);

	/**
	 * @param parentCode
	 * @return
	 */
	List queryAreaInfo(String parentCode);

    /**
     * 查询结算银行列表
     *
     * @return
     */
    List querySettleBank();
}
