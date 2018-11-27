package com.yeepay.g3.core.laike.entity;

import java.util.Date;

/**
 * Description: 持久化领域模型基类
 * Author: jiawen.huang
 * Date: 16/10/30
 * Time: 15:17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class PersistenceEntity extends IdEntity {

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
