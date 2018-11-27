package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;

/**
 * Created by dongxulu on 17/4/26.
 */
public class BalanceProductRequestDTO extends BaseDTO {
    private static final long serialVersionUID = -4883613070129110029L;
    /**
     * 商编号
     */
    String customerNumber;
    /**
     * 结算产品 默认T1
     */
    BalanceType balanceProduct=BalanceType.T1;


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BalanceType getBalanceProduct() {
        return balanceProduct;
    }

    public void setBalanceProduct(BalanceType balanceProduct) {
        this.balanceProduct = balanceProduct;
    }
}
