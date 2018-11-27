package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Repository
public interface OperateRecodeRepository {

	void save(OperateRecordEntity entity);

	OperateRecordEntity findById(Long id);

	OperateRecordEntity findByMemberNo(String memberNo);

	OperateRecordEntity findByMemberNoAndBiz(@Param("memberNo") String memberNo,
											 @Param("bizType") String bizTypeEnum);

	List<OperateRecordEntity> findByDate(@Param("bizType") String bizTypeEnum,
										 @Param("startDate") Date startDate,
										 @Param("endDate") Date endDate);

	int update(OperateRecordEntity entity);

	OperateRecordEntity findByLinkPhoneAndDate(@Param("linkPhone") String linkPhone,
											   @Param("bizParam") String bizParam,
											   @Param("startDate") Date startDate,
											   @Param("endDate") Date endDate);

	OperateRecordEntity findLastRecode(@Param("memberNo") String memberNo,
									   @Param("bizType") String bizTypeEnum);
}
