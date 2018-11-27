package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.S0RecordEntity;
import com.yeepay.g3.facade.laike.enumtype.OperateTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/4/24
 * Time: 16:54
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class S0RecordTest extends BaseTest {

	@Autowired
	private S0RecordRepository s0RecordRepository;

	@Test
	public void test() {
		S0RecordEntity entity = new S0RecordEntity();
		entity.setMemberNo("11212121");
		entity.setMerchantNo("10010011010");
		entity.setProductStatus(ProductStatusEnum.INIT);
		entity.setOperateType(OperateTypeEnum.OPEN);
		entity.setSettleBankCode("ICBC");
		entity.setId("11111111");
		s0RecordRepository.save(entity);

		S0RecordEntity entity1 = new S0RecordEntity();
		entity1.setMemberNo("11212121");
		entity1.setMerchantNo("10010011010");
		entity1.setProductStatus(ProductStatusEnum.CLOSED);
		entity1.setOperateType(OperateTypeEnum.OPEN);
		entity1.setSettleBankCode("cmbc");
		entity1.setId("111112111");
		s0RecordRepository.save(entity1);

		entity.setProductStatus(ProductStatusEnum.FORBIDDEN);
		s0RecordRepository.update(entity);

		S0RecordEntity entity2 = s0RecordRepository.findByMemberNo("11212121");
		Assert.assertEquals(entity2.getSettleBankCode(), "ICBC");
	}
}
