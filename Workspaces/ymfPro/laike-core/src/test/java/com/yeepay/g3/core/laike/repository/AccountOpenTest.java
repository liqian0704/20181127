package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.enumtype.OpenStatusEnum;
import com.yeepay.g3.utils.common.UIDGenerator;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AccountOpenTest extends BaseTest {

	@Autowired
	private AccountOpenRepository accountOpenRepository;

	//	@Rollback(false)
	@Test
	public void save() {
		AccountOpenEntity accountOpenEntity = new AccountOpenEntity();
		accountOpenEntity.setMemberNo("10040011560");
		accountOpenEntity.setOpenStatus(OpenStatusEnum.INIT);
		accountOpenEntity.setMerchantName("测试入网商户");
        accountOpenEntity.setInviteType(InviteType.DIRECT_SALE);
        accountOpenEntity.setInviteCode("1234");
        accountOpenEntity.setPhoneNo("1111");
        String id = UIDGenerator.generateBizUID(accountOpenRepository.nextSequence(), BizPrefixEnum.AC.getValue());
        accountOpenEntity.setId(id);
		accountOpenEntity.setCreateTime(new Date());
		accountOpenRepository.save(accountOpenEntity);
	}

	@Test
	public void update() {
		AccountOpenEntity accountOpenEntity = accountOpenRepository.findByMemberNo("10040011560");
		accountOpenEntity.setOpenStatus(OpenStatusEnum.INIT);
		accountOpenRepository.update(accountOpenEntity);
		Assert.assertEquals(accountOpenEntity.getId(), "CFA16111618952369");
	}

	@Test
	public void check() {
		List<AccountOpenEntity> list = accountOpenRepository.findByQRCode("E1R00MY");
		Assert.assertTrue(list.size() > 0);
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a.toString());
    }
}
