package com.yeepay.g3.core.ymf.dao.remit;

import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by dongxulu on 17/4/24.
 */
@Repository
public interface RemittanceDao {


    Remittance findByCustomerNumberAndCustomerOrderId(@Param("customerNumber") String customerNumber,
                                                      @Param("customerOrderID") String customerOrderID);
    Remittance findByYeepayOrderId(@Param("yeepayOrderId") String yeepayOrderId);

    /**
     * 查询需要发起出款的数据
     */
    List<Remittance> findRemiteDatas(@Param("status") Status status,
                                     @Param("begin") Date begin, @Param("end") Date end,
                                     @Param("begin") Date remitRequestTime,
                                     @Param("remitCount") int remitCount,
                                     @Param("rows") int rows);

    /**
     * 批量更新打款请求时间和次数
     * @param ids   主键
     */
    void updateRemitTime(@Param("ids") Set<Long> ids,@Param("curTime") Date curTime);
}
