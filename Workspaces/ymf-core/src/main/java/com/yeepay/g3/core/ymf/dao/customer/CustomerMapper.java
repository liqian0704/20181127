package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
//    =====非自动生成
    List<Customer> getCustomersByGroupId(@Param("groupId") Long groupId);

    int unBindGroup(@Param("id") Long id);

    List<Customer> findCustomerByName(@Param("customerName")String customerName);

    int bindGroup(@Param("id") Long id,@Param("groupId") Long groupId);
}