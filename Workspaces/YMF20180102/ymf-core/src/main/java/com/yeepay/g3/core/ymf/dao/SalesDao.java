package com.yeepay.g3.core.ymf.dao;

import com.yeepay.g3.core.ymf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("salesDao")
public interface SalesDao {

    List<Sales> getSalesPage();
    
    void insert(Sales sales);
    
    void updateBankInfo(@Param("bankNo")String bankNo,@Param("bankName")String bankName,@Param("id")Long id);
    
    void frozenSales(@Param("id")Long id);
    
    Sales getSalesById(@Param("id")Long id);
    
    List<Sales> getSalesByMobile(@Param("mobile")String mobile);
    
    Sales getSalesByQrId(@Param("qrId")String qrId);
}