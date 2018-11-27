package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.SecurityControlEntity;
import com.yeepay.g3.facade.laike.enumtype.ControlTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/9/21
 * Time: 16:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Repository
public interface SecurityControlRepository {

	void save(SecurityControlEntity securityControlEntity);

	int update(SecurityControlEntity securityControlEntity);

	SecurityControlEntity findByPhoneNumber(@Param("phoneNumber") String phoneNumber, @Param("controlType") ControlTypeEnum controlTypeEnum);
}
