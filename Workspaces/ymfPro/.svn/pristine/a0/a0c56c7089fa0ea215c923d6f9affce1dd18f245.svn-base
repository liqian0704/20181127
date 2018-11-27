package com.yeepay.g3.common.laike.utils;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.facade.laike.enumtype.EnvironmentType;
import com.yeepay.g3.utils.soa.context.DubboConfigUtils;

/**
 * Description: 机器环境
 * Author: jiawen.huang
 * Date: 2017/7/17
 * Time: 11:39
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class EnvironmemntUtil {

	/**
	 * 当前环境
	 *
	 * @return
	 */
	public static EnvironmentType getCurrentEnvironment() {
		String hessianURL = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_HESSIAN_URL);
		if (ConfigEnum.LIKER_HESSIAN_URL.getDefaultValue().equals(hessianURL)) {
			return EnvironmentType.QA;
		} else {
			String environment = DubboConfigUtils.getEnvironment();//默认product，内测test
			return "product".equals(environment) ? EnvironmentType.PRO : EnvironmentType.NC;
		}
	}

	/**
	 * Web当前host
	 *
	 * @return
	 */
	public static String getWebHost() {
		String webURL = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_WEB_URL);
		if (webURL.equals(ConfigEnum.LIKER_WEB_URL.getDefaultValue())) {
			return webURL;
		} else {
			String environment = DubboConfigUtils.getEnvironment();
			return "product".equals(environment) ? Constants.LIKER_WEB_URL_PRO_HOST
					: Constants.LIKER_WEB_URL_NC_HOST;
		}

	}
}
