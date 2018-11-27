package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 来客存量商户计算
 * @author: xiaobin.liu
 * @date: 17/12/21
 * @time: 下午4:02
 */
@Service("rjtStockCalculator")
public class RjtStockCalculatorImpl extends AbstractProfitCalculator implements ProfitCalculator {

    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.RJT_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.STOCK;

    /**
     * 存量商户，蜜糖毛利分配比例：50%
     */
    protected static BigDecimal RJT_STOCK_PROFIT_PERCENT = new BigDecimal(0.5);


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
        return RJT_STOCK_PROFIT_PERCENT;
    }

}
