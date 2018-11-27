package com.yeepay.g3.core.ymf.service.remit;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by dongxulu on 17/5/2.
 */
public interface RemittanceBizService {

    /**
     * 更新打款记录并记录商户结算信息
     * @param remittance
     * @param customerSettleDetail
     */
    public void updateRemittanceAndSaveCustomerSettleDatil(Remittance remittance, CustomerSettleDetail customerSettleDetail);

    /**
     * 保存打款记录同时保存商户结算信息
     * @param remittance
     * @param customerSettleDetail
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
    void saveRemittanceAndCustomerSettleDatil(Remittance remittance, CustomerSettleDetail customerSettleDetail);
}
