package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.facade.laike.enumtype.S0LevelEnum;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/5/4
 * Time: 16:55
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;


	/**
	 * 业务简化后模拟错误的代码
	 */
	@Test
	public void testCache() {
		UserEntity userEntity1 = userService.findByMemberNo("212468315525");
		userEntity1.setLocation("test");
		userEntity1.setS0Level(S0LevelEnum.LEVEL1);
		System.out.println(userEntity1.toString());

//		userService.updateS0Level(userEntity1);
		UserEntity userEntityCurrent = userService.findByMemberNo("212468315525");
		System.out.println(userEntityCurrent.toString());

//		UserEntity userEntity2 = userService.findById("USA17011971895805");
//		System.out.println(userEntity2.toString());
	}

	@Test
	public void testCache2() {
		UserEntity userEntity1 = userService.findByMemberNo("212468315525");
		userEntity1.setLocation("test");
		userEntity1.setS0Level(S0LevelEnum.LEVEL1);
		System.out.println(userEntity1.toString());
	}


	public static void main(String[] a) {
		//查
		UserEntity resultObject = new UserEntity();
		resultObject.setMerchantNo("123456");
		resultObject.setS0Level(S0LevelEnum.LEVEL1);
		resultObject.setImei("111");

		//取和存cache
		Map<Object, Object> cache = new HashMap();
		cache.put("entity", resultObject);
		UserEntity firstQuery = resultObject;
		System.out.println(ReflectionToStringBuilder.toString(firstQuery));

		firstQuery.setAccountId("hahahaha");

		//缓存读
		UserEntity cacheObject = (UserEntity) cache.get("entity");
		System.out.println(ReflectionToStringBuilder.toString(cacheObject));
		System.out.println("他们是同一块内存吗？" + String.valueOf(firstQuery == cacheObject));
	}

    @Test
    public void userTest() {
        List<UserEntity> userEntity = userService.findByMerchantNo("10040042070");
        System.err.println(new Gson().toJson(userEntity.size()));
    }
}





















