package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.laike.SettleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSettleDao {

    /**
     * 统计清算
     * @param args
     * @return
     */
    CountResponse countSettle(SettleArgs args);

    /**
     * 查询清算信息
     * @param args
     * @return
     */
    List<SettleDTO> querySettle(SettleArgs args);
}