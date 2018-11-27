package com.yeepay.g3.core.ymf.dao.upayterminalno;

import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jiwei.lv on 17/5/8.
 */
@Repository
public interface TerminalNumberDao {
    /**
     * 根据商户编号和终端号获取银联终端号
     * @param customerNumber
     * @param terminalNumber
     * @return
     */
    TerminalNumber findByCusNOAndTerNO(@Param("customerNumber")String customerNumber, @Param("terminalNumber")String terminalNumber);

    /**
     * 根据商户的状态和商户号获得终端号
     * @param customerNumber
     * @param active
     * @return
     */
    TerminalNumber selectByCustomerNumber(@Param("customerNumber")String customerNumber, @Param("status")String status);
}
