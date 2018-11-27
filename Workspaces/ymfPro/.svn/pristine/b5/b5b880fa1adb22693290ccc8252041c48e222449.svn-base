package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.BlackListEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository {


	int save(BlackListEntity record);

	BlackListEntity findById(Long id);

	BlackListEntity findByMemberNo(String memberNo);

	int delete(@Param("id") Long id, @Param("operator") String operator);
}