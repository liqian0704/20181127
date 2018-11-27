package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.PushMsgEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushMsgRepository {

	void save(PushMsgEntity record);

	PushMsgEntity findById(@Param("id") Long id);

	PushMsgEntity findByMessageNo(@Param("messageNo") String messageNo);

	int updateAfterSend(PushMsgEntity record);

	int updateBeforeSend(PushMsgEntity record);

	int delete(PushMsgEntity entity);

	long nextSequence();
}