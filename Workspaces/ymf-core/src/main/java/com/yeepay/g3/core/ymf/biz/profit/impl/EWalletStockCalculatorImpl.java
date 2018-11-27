package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 易钱包存量商户
 * @author: xiaobin.liu
 * @date: 17/12/21
 * @time: 下午4:02
 */
@Service("eWalletStockCalculator")
public class EWalletStockCalculatorImpl extends AbstractProfitCalculator implements ProfitCalculator {

    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.E_WALLET_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.STOCK;
    /**
     * 存量商户，蜜糖毛利分配比例：20%
     */
    protected static BigDecimal EWALLET_STOCK_PROFIT_PERCENT = new BigDecimal(0.2).setScale(4, BigDecimal.ROUND_HALF_UP);


    @Override
    protected ProfitProductTypeEnum getProductType() {
        return PRODUCT_TYPE;
    }

    @Override
    protected CustomerTypeEnum getCustomerType() {
        return CUSTOMER_TYPE;
    }
    @Override
    protected BigDecimal getPercent() {
        return EWALLET_STOCK_PROFIT_PERCENT;
    }

}
