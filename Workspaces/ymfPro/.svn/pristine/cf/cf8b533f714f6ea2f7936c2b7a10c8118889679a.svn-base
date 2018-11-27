package com.yeepay.g3.facade.laike.dto;

/**
 * Description: 基础查询类
 * Author: jiawen.huang
 * Date: 16/11/24
 * Time: 20:38
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryBaseRequest extends BaseRequest {

	protected int pageIndex = 1;

	protected int pageSize = 10;

	protected String startDate;

	protected String endDate;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("QueryOrderRequest{");
		sb.append("memberNo='").append(memberNo).append('\'');
		sb.append("imei='").append(imei).append('\'');
		sb.append("versionId='").append(versionId).append('\'');
		sb.append("location='").append(location).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
