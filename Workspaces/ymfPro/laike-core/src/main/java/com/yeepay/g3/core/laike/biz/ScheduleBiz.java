package com.yeepay.g3.core.laike.biz;

/**
 * Description: 定时业务Biz接口
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 15:38
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface ScheduleBiz {

	/**
	 * 硬件申请每日汇总和邮件(凌晨发起昨天的)
	 */
	void gatherDailyDeviceApply();
}
