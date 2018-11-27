package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettle;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.laike.SettleDTO;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by yp-tc-m-2762 on 16/10/28.
 */
public interface CustomerSettleService {
    /**
     * 新建商户结算信息
     * @param customerSettle
     * @return
     */
    int saveCustomerSettle(CustomerSettle customerSettle);

    /**
     * 更新
     * @param customerSettle
     * @return
     */
    int update(CustomerSettle customerSettle);

    /**
     * 根据商编和日期获取清算信息
     * @param customerNumber 商户编号
     * @param trxDate 日期
     * @return
     */
    CustomerSettle findByParam(String customerNumber, Date trxDate);

    /**
     * 根据条件汇总
     * @param args 条件
     * @return count and amount
     */
    CountResponse countByArgs(SettleArgs args);

    /**
     * 根据条件查询
     * @param args 条件
     * @return list
     */
    List<SettleDTO> queryByArgs(SettleArgs args);
}
