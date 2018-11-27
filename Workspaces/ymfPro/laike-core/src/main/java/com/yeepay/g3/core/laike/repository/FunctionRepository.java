package com.yeepay.g3.core.laike.repository;

import com.yeepay.g3.core.laike.entity.FunctionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository {

    void save(FunctionEntity record);

    FunctionEntity findById(Long id);

    FunctionEntity findByCode(String functionCode);

    int update(FunctionEntity record);

    int toggleAvailable(FunctionEntity record);
}