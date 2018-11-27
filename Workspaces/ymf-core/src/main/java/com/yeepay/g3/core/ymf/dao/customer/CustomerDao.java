package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {
    public List<Customer> findStatusAndBusiType(@Param("status") Status status,
                                                @Param("bizCode") String bizCode,
                                                @Param("rowBegin") int rowBegin,
                                                @Param("rowEnd") int rowEnd);
    public int findStatusAndBusiTypeCount(@Param("status") Status status,
                                                @Param("bizCode") String bizCode);

    public List<Customer> findNOShopCustomers();

    /**
     * 查询所有商户logo的ftp路径
     *
     * @return
     */
    public List<Customer> findOldFtpPrefix(@Param("prefix") String prefix);
}