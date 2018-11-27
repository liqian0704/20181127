package com.yeepay.g3.core.laike.handler;

import java.io.Serializable;

/**
 * Description: 敏感String
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 11:21
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class SensitiveString implements Serializable {

	protected static final long serialVersionUID = -7277949963127751206L;

	private String value;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
