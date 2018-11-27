package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.UserEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.utils.common.UIDGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UserTest extends BaseTest {

	@Autowired
	private UserRepository userRepository;

	//	@Rollback(false)
	@Test
	public void save() {
		UserEntity userEntity = new UserEntity();
		String userNo = UIDGenerator.generateBizUID(userRepository.nextSequence(), BizPrefixEnum.US.getValue());
		userEntity.setId(userNo);
		userEntity.setPhoneNo("1851939782");
		userEntity.setUserStatus(null);
//        new LaikeSysException(ErrorCode.REPEAT_SUBMIT_EXCEPTION);
		userRepository.insert(userEntity);
	}

	//	@Rollback(false)
	@Test
	public void update() {
		List<UserEntity> userEntity = userRepository.findByPhoneNo("15010201001");
//		userEntity.setRole(RoleEnum.WORKER);
//		userRepository.update(userEntity);
//		Assert.assertEquals(userEntity.getId(), "USA16111571895287");
	}

    @Test
    public void findByMemberNo() {
        UserEntity userEntity = userRepository.findByMemberNo("212468327836");
        System.err.println(userEntity);
    }
}
