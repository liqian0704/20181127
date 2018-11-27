package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.facade.laike.enumtype.OperateTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.ProductStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 16:54
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class S0RecordServiceTest extends BaseTest {

	@Autowired
	private S0RecordService s0RecordService;

	@Rollback(false)
	@Test
	public void test() {
		S0RecordEntity entity = new S0RecordEntity();
		entity.setMemberNo("212468315180");
		entity.setMerchantNo("10010011010");
		entity.setProductStatus(ProductStatusEnum.INIT);
		entity.setOperateType(OperateTypeEnum.OPEN);
		entity.setSettleBankCode("ICBC");
        entity.setBranchBankName("TEST");
        entity.setId("11111111");
		s0RecordService.createOne(entity);
    }

    @Test
    public void test1() {
        System.err.println(new Gson().toJson(s0RecordService.findByType("212468327991", OperateTypeEnum.OPEN)));
    }
}
