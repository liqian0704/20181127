package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import com.yeepay.g3.core.laike.enumtype.BizPrefixEnum;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.PushMsgService;
import com.yeepay.g3.facade.laike.enumtype.PushStatus;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.UIDGenerator;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/10/26
 * @Time: 下午2:36
 */
@Service
public class PushMsgServiceImpl extends AbstractService implements PushMsgService {

	private static Logger LOGGER = LoggerFactory.getLogger(PushMsgServiceImpl.class);

	@Override
	public PushMsgEntity save(PushMsgEntity entity) {
		try {
			String messageNo = UIDGenerator.generateBizUID(pushMsgRepository.nextSequence(), BizPrefixEnum.MS.getValue());//生成编号
			entity.setPushStatus(PushStatus.NOT_SEND);
			entity.setMessageNo(messageNo);//可以不判blank，这是唯一索引
			pushMsgRepository.save(entity);
			return entity;
		} catch (DuplicateKeyException e) {
			throw new LaikeSysException(ErrorCode.MSG_CREATE_REPEAT, e);
		}
	}

	@Override
	public void updateBeforeSend(PushMsgEntity entity) {
		Integer num = pushMsgRepository.updateBeforeSend(entity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public void updateAfterSend(PushMsgEntity entity) {
		entity.setPushStatus(PushStatus.SENDED);
		Integer num = pushMsgRepository.updateAfterSend(entity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public void delete(PushMsgEntity entity) {
		Integer num = pushMsgRepository.delete(entity);
		if (0 == num) {
			throw new LaikeSysException(ErrorCode.MSG_UPDATE_STATUS_ERROR);
		}
	}

	@Override
	public PushMsgEntity findByMessageNo(String messageNo) {
		PushMsgEntity msgEntity = pushMsgRepository.findByMessageNo(messageNo);
		if (null == msgEntity) {
			throw new LaikeSysException(ErrorCode.MSG_NOT_EXIST);
		}
		return msgEntity;
	}

}
