package com.yeepay.g3.core.laike.repository;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.OperateRecordEntity;
import com.yeepay.g3.core.laike.utils.SecurityUtil;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import com.yeepay.g3.utils.common.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OperateRecodeTest extends BaseTest {

	@Autowired
	private OperateRecodeRepository operateRecodeRepository;

	@Test
	@Rollback(false)
	public void save() {
		OperateRecordEntity entity = new OperateRecordEntity();
		//entity.setId("1");
		entity.setMemberNo("191111163513");
		entity.setBizType(BizTypeEnum.DEVICE_APPLY);
		entity.setBizParam("网商户");
		entity.setCount(2);
		entity.setLinkName("liker");
		entity.setCreateTime(new Date());
		entity.setLinkPhone("18519397761");
		operateRecodeRepository.save(entity);
		OperateRecordEntity entit = operateRecodeRepository.findByMemberNo("10040011560");
		List<OperateRecordEntity> list = Lists.newArrayList();
		list.add(entit);
		list.add(entity);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		List<Map<String, Object>> list2 = gson.fromJson(json, List.class);
		System.out.println(list2.toString());
	}

	@Test
	public void list() {

		List<OperateRecordEntity> list = operateRecodeRepository.findByDate(BizTypeEnum.DEVICE_APPLY.getValue(),
				DateUtils.addDay(new Date(), -2), new Date());
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println("json===" + json);
		List<Map<String, Object>> list2 = gson.fromJson(json, List.class);
		System.out.println("list===" + list2.toString());
	}

	@Test
//    @Rollback(false)
	public void update() {
		System.err.println(DateUtils.getDayStart(new Date()) + "   " + DateUtils.getDayEnd(new Date()));
		OperateRecordEntity entity = operateRecodeRepository.findByMemberNo("10040011560");
		System.err.println(new Gson().toJson(operateRecodeRepository.
				findByLinkPhoneAndDate(entity.getMemberNo(), "",
						DateUtils.getDayStart(entity.getCreateTime()),
						DateUtils.getDayEnd(entity.getCreateTime()))));
		entity.setCount(3);
		operateRecodeRepository.update(entity);
	}

    @Test
    public void test() {
        System.err.println(new Gson().toJson(operateRecodeRepository.findByLinkPhoneAndDate("13126647238", "新大陆SP60", DateUtils.getDayStart(new Date()), DateUtils.getDayEnd(new Date()))));
    }

    @Test
    public void findLastRecode() {
        System.err.println(new Gson().toJson(operateRecodeRepository.findLastRecode("212468327836", "LOGIN")));
    }

	public static void main(String[] a) {
        System.out.println(SecurityUtil.decryptL1Info("mmsxUjh4vRJRI2P0R6nznw=="));
    }
}
