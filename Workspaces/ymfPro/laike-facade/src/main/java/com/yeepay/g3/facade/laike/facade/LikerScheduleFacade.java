package com.yeepay.g3.facade.laike.facade;

/**
 * Description: 来客app后段这边的定时
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 14:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface LikerScheduleFacade {

	/**
	 * 硬件申请每日汇总和邮件（凌晨发起昨天的）
	 */
	void gatherDailyDeviceApply();

    /**
     * 将展业Submit状态开户单,提交入网
     */
    void gatherAlliancetoMer();
}
