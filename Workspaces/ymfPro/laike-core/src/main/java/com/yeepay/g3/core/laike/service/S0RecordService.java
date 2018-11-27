package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.facade.laike.enumtype.OperateTypeEnum;

/**
 * Description: S0服务接口
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 10:52
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface S0RecordService {

	/**
	 * 创建记录
	 *
	 * @param entity
	 */
	void createOne(S0RecordEntity entity);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	S0RecordEntity findOne(String id);

	/**
	 * 根据会员号查询最新记录
	 *
	 * @param memberNo
	 * @return
	 */
	S0RecordEntity findByMemberNo(String memberNo);

	/**
	 * 根据操作类型查询最近的
	 *
	 * @param memberNo
	 * @param operateTypeEnum
	 * @return
	 */
	S0RecordEntity findByType(String memberNo, OperateTypeEnum operateTypeEnum);

	/**
	 * 更新
	 *
	 * @param entity
	 */
	void update(S0RecordEntity entity);
}
