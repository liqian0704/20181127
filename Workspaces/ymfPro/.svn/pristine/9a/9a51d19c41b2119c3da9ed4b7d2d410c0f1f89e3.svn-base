package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.cache.annotations.UnCacheable;
import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository {

	int save(AppVersionEntity record);

	AppVersionEntity findById(String id);

	@UnCacheable
	AppVersionEntity findNewByRoleAndPlatform(@Param("platform") VersionPlatform platformEnum,
											  @Param("roleType") AppRoleEnum roleEnum);

	int update(AppVersionEntity record);

	@UnCacheable
	long nextSequence();
}