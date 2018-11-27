package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.RoleFunctionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleFunctionRepository {

	void save(RoleFunctionEntity roleFunctionEntity);

    void batchSave(@Param("funcId") Long funcId,
                   @Param("roleIds") List<String> roleIds,
                   @Param("operator") String operator,
                   @Param("description") String description);

	RoleFunctionEntity findById(Long id);

	int update(RoleFunctionEntity record);

	List<String> findByFunId(Long funcId);

	RoleFunctionEntity findByRoleAndFunId(@Param("roleId") String roleId, @Param("funcId") Long funcId);

	int close(RoleFunctionEntity record);

	int closeAll(@Param("funcId") Long funcId,
				 @Param("roleIds") String roleIds,
				 @Param("operator") String operator,
				 @Param("description") String description);

	int toggleAvailable(RoleFunctionEntity record);
}