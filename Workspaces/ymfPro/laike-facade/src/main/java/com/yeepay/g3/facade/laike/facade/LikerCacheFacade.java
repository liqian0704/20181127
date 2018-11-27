package com.yeepay.g3.facade.laike.facade;

/**
 * Description: 清空专用
 * Author: jiawen.huang
 * Date: 2017/8/7
 * Time: 12:28
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface LikerCacheFacade {

	/**
	 * 清除所有laike-hessian前缀的缓存
	 */
	void cleanAllCache();
}
