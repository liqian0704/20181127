package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: AttachmentTest
 * Author: jiawen.huang
 * Date: 16/11/15
 * Time: 18:09
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AttachmentTest extends BaseTest {

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Test
	public void test() {
		AttachmentEntity attachmentEntity = new AttachmentEntity();
		attachmentEntity.setAccountId("CFA16111618952369");
		attachmentEntity.setBizImg("hahahahah");
		attachmentEntity.setBankcardImg("http://wiki.yeepay.com/notpermitted.action?version=1&modificationDate=1404106848000&api=v2");
		attachmentRepository.save(attachmentEntity);
		AttachmentEntity entity = attachmentRepository.findByAccountId("CFA16111618952369");
		Assert.assertEquals(entity.getBizImg(), "hahahahah");
		entity.setBizImg("hhhhhh");
		int num = attachmentRepository.update(entity);
		Assert.assertEquals(num, 1);
	}
}
