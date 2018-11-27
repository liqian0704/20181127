package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerFunctionDao {

   List<CustomerFunction> getByCustomerAndDicType(@Param("customerNumber")String customerNumber,@Param("dicType") String dicType);

   int batchCreate(List<CustomerFunction> list);
}