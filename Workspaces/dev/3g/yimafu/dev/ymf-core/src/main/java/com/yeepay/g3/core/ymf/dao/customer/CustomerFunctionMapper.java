package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.core.ymf.entity.customer.CustomerFunctionParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFunctionMapper {
    int countByFilter(CustomerFunctionParam filter);

    int deleteByFilter(CustomerFunctionParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerFunction record);

    int insertSelective(CustomerFunction record);

    List<CustomerFunction> selectByFilter(CustomerFunctionParam filter);

    CustomerFunction selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") CustomerFunction record, @Param("example") CustomerFunctionParam filter);

    int updateByFilter(@Param("record") CustomerFunction record, @Param("example") CustomerFunctionParam filter);

    int updateByPrimaryKeySelective(CustomerFunction record);

    int updateByPrimaryKey(CustomerFunction record);
}