package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.AttachmentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 附件
 * @Author: zhaoyu.cui
 * @Date: 16/10/26
 * @Time: 上午11:18
 */
@Repository
public interface AttachmentRepository {

    void save(AttachmentEntity record);

    AttachmentEntity findByAccountId(@Param("accountId") String accountId);

    int update(AttachmentEntity record);
}
