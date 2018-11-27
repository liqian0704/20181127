package com.yeepay.g3.core.ymf.service.remit;

import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;

import java.util.Date;
import java.util.List;

/**
 * 打款服务接口
 * Created by dongxulu on 17/4/24.
 */
public interface RemittanceService {

    int save(Remittance remittance);
    int update(Remittance remittance);
    Remittance findByID(Long id);

    /**
     * 根据商编与商户订单查询打款记录
     * @param customerNumber
     * @param customerOrderID
     * @return
     */
    Remittance findByCustomerNumberAndCustomerOrderId(String customerNumber,String customerOrderID);

    /**
     * 根据易宝订单号查询
     * @param yeepayOrderId
     * @return
     */
    Remittance findByYeepayOrderId(String yeepayOrderId);

    /**
     * 根据打款状态与时间查询打款记录 分页
     * @param remitStatus
     * @param begin
     * @param end
     * @return
     */
    List<Remittance> findByRemitStatus(RemitStatus remitStatus, Date begin,Date end,int startRow,int endRow);

    /**
     * 汇总数据
     * @param remitStatus
     * @param begin
     * @param end
     * @return
     */
    int findByRemitStatusCount(RemitStatus remitStatus,Date begin,Date end);

}
