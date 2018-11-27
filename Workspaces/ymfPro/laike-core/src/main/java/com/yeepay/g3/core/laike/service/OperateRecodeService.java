package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * Description: 用户操作服务接口
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface OperateRecodeService {

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	OperateRecordEntity findById(Long id);

	/**
	 * 创建记录
	 *
	 * @param entity
	 */
	void save(OperateRecordEntity entity);

	/**
	 * 存固定操作
	 *
	 * @param memberNo
	 * @param bizTypeEnum
	 * @param bizParam
	 */
	void save(String memberNo, BizTypeEnum bizTypeEnum, String bizParam);

	/**
	 * 根据memberNo查询
	 *
	 * @param memberNo
	 * @param bizTypeEnum
	 * @return
	 */
	OperateRecordEntity findByMemberNoAndBiz(String memberNo, BizTypeEnum bizTypeEnum);

	/**
	 * 更新
	 *
	 * @param entity
	 * @return
	 */
	void update(OperateRecordEntity entity);

	/**
	 * 根据操作类型、时间查询
	 *
	 * @param bizTypeEnum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OperateRecordEntity> findByDate(BizTypeEnum bizTypeEnum, Date startDate, Date endDate);

	/**
	 * 按天创建或更新记录，如果当天记录已存在,则更新
	 *
	 * @param entity
	 */
	void createAndUpdateByDate(OperateRecordEntity entity);

	/**
	 * 找到最近的一条记录
	 *
	 * @param memberNo
	 * @param bizTypeEnum
	 * @return
	 */
	OperateRecordEntity findLastRecode(String memberNo, BizTypeEnum bizTypeEnum);
}
