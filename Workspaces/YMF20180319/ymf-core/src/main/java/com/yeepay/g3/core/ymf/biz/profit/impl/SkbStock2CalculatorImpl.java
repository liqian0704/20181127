package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 收款宝存量2数据
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 上午10:51
 */
@Service("skbStock2Calculator")
public class SkbStock2CalculatorImpl extends AbstractProfitCalculator {
    /**
     * 存量2商户，全部按照5:5计算  2018.03.16 调整为：4:6
     */
    private static final BigDecimal MT_STOCK2_RATE = new BigDecimal(0.4).setScale(4, BigDecimal.ROUND_HALF_UP);
    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.SKB_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.STOCK2;


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
        return MT_STOCK2_RATE;
    }
}
