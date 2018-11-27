package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.SmsCodeEntity;
import com.yeepay.g3.facade.laike.enumtype.SmsTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description: dao
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 14:04
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Repository
public interface SmsCodeRepository {

	void save(SmsCodeEntity smsCodeEntity);

	int setUnAvaliable(Long id);

	SmsCodeEntity findOne(@Param("phoneNo") String phoneNo, @Param("smsType") SmsTypeEnum smsType);
}
