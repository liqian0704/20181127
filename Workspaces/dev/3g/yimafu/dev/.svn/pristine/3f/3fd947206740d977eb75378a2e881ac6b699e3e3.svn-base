package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    int countByFilter(CustomerParam filter);

    int deleteByFilter(CustomerParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByFilter(CustomerParam filter);

    Customer selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Customer record, @Param("example") CustomerParam filter);

    int updateByFilter(@Param("record") Customer record, @Param("example") CustomerParam filter);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}