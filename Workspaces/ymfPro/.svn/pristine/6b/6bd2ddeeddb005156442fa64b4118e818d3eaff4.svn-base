package com.yeepay.g3.core.laike.service;


import com.yeepay.g3.facade.laike.enumtype.ControlTypeEnum;

/**
 * Description: 业务权限控制服务层接口
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:21
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface SecurityControlService {

	/**
	 * 检查业务是否冻结了
	 *
	 * @param phoneNumber     用户手机号
	 * @param controlTypeEnum 业务类型
	 * @return false: 未冻结 true：已冻结
	 */
	void checkFreeze(String phoneNumber, ControlTypeEnum controlTypeEnum);

	/**
	 * 增加次数
	 *
	 * @param phoneNumber     用户手机号
	 * @param controlTypeEnum 业务类型
	 */
	void increaseCount(String phoneNumber, ControlTypeEnum controlTypeEnum);
}
