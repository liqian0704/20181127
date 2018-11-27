package com.yeepay.g3.core.ymf.service.impl.remit;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceBizService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongxulu on 17/5/2.
 */
@Service
public class RemittanceBizServiceImpl implements RemittanceBizService {
    @Autowired
    private RemittanceService remittanceService;
    @Autowired
    private CustomerSettleDetailService customerSettleDetailService;
    @Override
    public void updateRemittanceAndSaveCustomerSettleDatil(Remittance remittance, CustomerSettleDetail customerSettleDetail) {
        remittanceService.update(remittance);
        customerSettleDetailService.createAndUpdate(customerSettleDetail);
    }
}
