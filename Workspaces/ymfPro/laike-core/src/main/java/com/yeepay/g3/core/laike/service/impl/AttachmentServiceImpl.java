package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.AttachmentService;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Description: 开户状态表
 * Author: jiawen.huang
 * Date: 16/12/13
 * Time: 18:19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class AttachmentServiceImpl extends AbstractService implements AttachmentService {

	private AttachmentEntity findByAccountId(String accoutId) {
		return attachmentRepository.findByAccountId(accoutId);
	}

	@Override
	public AttachmentEntity findByAccount(String accoutId) {
		AttachmentEntity attachmentEntity = this.findByAccountId(accoutId);
		if (null == attachmentEntity) {
			attachmentEntity = new AttachmentEntity();
		}
		return attachmentEntity;
	}

	private void save(AttachmentEntity record) {
		try {
			attachmentRepository.save(record);
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.ATTACH_REPEAT, e);
		}
	}

	@Override
	public void createAndUpdate(AttachmentEntity record) {
		if (null == record.getId()) {
			this.save(record);
		} else {
			Integer num = attachmentRepository.update(record);
			if (0 == num) {
				throw new LaikeSysException(ErrorCode.ATTACH_NOT_EXIST);
			}
		}
	}
}
