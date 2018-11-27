package com.yeepay.g3.facade.laike.exception;

import com.yeepay.g3.utils.common.exception.YeepayBizException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * Description: 子系统异常
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 18:25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class LaikeSysException extends YeepayBizException {

	private static Logger LOGGER = LoggerFactory.getLogger(LaikeSysException.class);

	public LaikeSysException(String defineCode) {
		super(defineCode);
		this.message = SystemErrorCodeTranslator.getInstance().getMessage(defineCode);
	}

	public LaikeSysException(String defineCode, Throwable throwable) {
		super(defineCode);
		LOGGER.error("LaikeSysException info:",throwable);
		this.message = SystemErrorCodeTranslator.getInstance().getMessage(defineCode);
	}

	/**
	 * 透传
	 *
	 * @param defineCode
	 * @param message
	 */
	public LaikeSysException(String defineCode, String message) {
		super(defineCode);
		this.message = message;
	}
}
