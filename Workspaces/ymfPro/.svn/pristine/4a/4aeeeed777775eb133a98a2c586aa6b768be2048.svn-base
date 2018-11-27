package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.cache.annotations.UnCacheable;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.enumtype.LOLOpenStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 开户
 * @Author: zhaoyu.cui
 * @Date: 16/10/25
 * @Time: 下午5:47
 */
@Repository
public interface AccountOpenRepository {

    void save(AccountOpenEntity accountOpenEntity);

    AccountOpenEntity findById(String id);

    @UnCacheable
    long nextSequence();

    AccountOpenEntity findByMemberNo(@Param("memberNo") String memberNo);

    AccountOpenEntity findByMerchantNo(@Param("merchantNo") String merchantNo);

    @UnCacheable
    List<AccountOpenEntity> findByQRCode(@Param("qrCode") String qrCode);

    int update(AccountOpenEntity accountOpenEntity);

    int updateStatus(AccountOpenEntity accountOpenEntity);

    @UnCacheable
    List<AccountOpenEntity> findByLolStatus(LOLOpenStatus lolOpenStatus);

}
