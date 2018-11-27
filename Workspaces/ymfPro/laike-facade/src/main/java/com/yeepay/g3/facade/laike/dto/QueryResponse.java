package com.yeepay.g3.facade.laike.dto;

import java.util.List;
import java.util.Map;

/**
 * Description: 单条查询响应
 * Author: jiawen.huang
 * Date: 16/12/20
 * Time: 20:16
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class QueryResponse extends BaseResponse {

	Map<String, String> resultMap;

	List<Object> resultList;

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}
}
