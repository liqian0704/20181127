package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.FunctionEntity;
import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class FuncTest extends BaseTest {

	@Autowired
	private FunctionRepository functionRepository;

	@Autowired
	private RoleFunctionRepository roleFunctionRepository;

	@Test
	public void func() {
		FunctionEntity functionEntity = new FunctionEntity();
		functionEntity.setFunctionCode("/push/msg");
		functionEntity.setFunctionName("推送");
		functionEntity.setOperator("jiawen");
		functionRepository.save(functionEntity);
		FunctionEntity entity = functionRepository.findById(functionEntity.getId());
        int num = functionRepository.update(entity);
        Assert.assertEquals(num, 1);

		RoleFunctionEntity roleFunctionEntity = new RoleFunctionEntity();
		roleFunctionEntity.setOperator("jiawen");
		roleFunctionEntity.setFunctionId(entity.getId());
		roleFunctionRepository.save(roleFunctionEntity);

		RoleFunctionEntity entity1 = roleFunctionRepository.findById(roleFunctionEntity.getId());
		entity1.setAvailable(true);
		int num2 = roleFunctionRepository.update(entity1);
		Assert.assertEquals(num2, 1);
	}
}
