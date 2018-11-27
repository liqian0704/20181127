package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.BlackListEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: BlackListTest
 * Author: jiawen.huang
 * Date: 16/11/17
 * Time: 10:20
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BlackListTest extends BaseTest {

	@Autowired
	private BlackListRepository blackListRepository;

	@Test
	public void test() {
		BlackListEntity blackListEntity = new BlackListEntity();
		blackListEntity.setMemberNo("8952369");
		blackListEntity.setOperator("jiawen");
		blackListEntity.setFunctionId(1l);
		blackListRepository.save(blackListEntity);
		BlackListEntity entity = blackListRepository.findByMemberNo("8952369");
		int num = blackListRepository.delete(entity.getId(), "jiwei");
		Assert.assertEquals(num, 1);
		Assert.assertTrue(entity.getAvailable() == false);
	}
}
