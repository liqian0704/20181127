
package com.yeepay.g3.core.laike.service;

import java.util.Map;

/**
 * Description: 账户管理
 * Author: jiawen.huang
 * Date: 16/12/20
 * Time: 17:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AccountManageService {

	/**
	 * 查询余额
	 *
	 * @param merchantNo
	 * @return
	 */
	Map<String, String> getBalance(String merchantNo);
}
