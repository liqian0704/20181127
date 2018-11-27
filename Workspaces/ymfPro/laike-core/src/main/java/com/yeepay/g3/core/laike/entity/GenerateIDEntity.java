package com.yeepay.g3.core.laike.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 自定义id实体基类
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:14
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class GenerateIDEntity implements Serializable {

	protected static final long serialVersionUID = -7277949963127751206L;

	private String id;

	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
