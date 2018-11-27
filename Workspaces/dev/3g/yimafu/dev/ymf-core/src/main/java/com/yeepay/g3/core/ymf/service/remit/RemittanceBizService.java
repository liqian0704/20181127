package com.yeepay.g3.core.ymf.service.remit;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;

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

}
