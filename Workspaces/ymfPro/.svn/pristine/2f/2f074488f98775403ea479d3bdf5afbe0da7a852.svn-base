package com.yeepay.g3.core.laike.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description:序列化领域模型基类
 * Author: jiawen.huang
 * Date: 15/2/4
 * Time: 11:52
 * Version: 1.0
 * Copyright © 2015 YeePay.com All rights reserved.
 */
public abstract class IdEntity implements Serializable {

	protected static final long serialVersionUID = -7277949963127751206L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
