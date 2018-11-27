package com.yeepay.g3.core.ymf.dao.remit;

import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dongxulu on 17/4/24.
 */
@Repository
public interface RemittanceDao {


    Remittance findByCustomerNumberAndCustomerOrderId(@Param("customerNumber") String customerNumber,
                                                      @Param("customerOrderID") String customerOrderID);
    Remittance findByYeepayOrderId(@Param("yeepayOrderId") String yeepayOrderId);

}
