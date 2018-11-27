package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.facade.laike.enumtype.OperateTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description: DAO
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 11:02
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Repository
public interface S0RecordRepository {

	void save(S0RecordEntity entity);

	S0RecordEntity findById(String id);

	long nextSequence();

	S0RecordEntity findByMemberNo(@Param("memberNo") String memberNo);

	S0RecordEntity findByType(@Param("memberNo") String memberNo,
							  @Param("operateType") OperateTypeEnum operateTypeEnum);

	int update(S0RecordEntity s0RecordEntity);
}
