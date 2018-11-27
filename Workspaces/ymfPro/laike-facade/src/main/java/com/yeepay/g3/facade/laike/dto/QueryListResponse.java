package com.yeepay.g3.facade.laike.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description: 消息查询响应
 * @Author: zhaoyu.cui
 * @Date: 16/11/10
 * @Time: 下午2:50
 */
public class QueryListResponse extends BaseResponse {

	private long count;

	private BigDecimal sum;

	private List<Map<String, Object>> list;

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
}
