package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 日结通蜜糖发展商户计算
 * @author: xiaobin.liu
 * @date: 17/12/21
 * @time: 下午4:02
 */
@Service("rjtMtCalculator")
public class RjtMtCalculatorImpl extends AbstractProfitCalculator implements ProfitCalculator {

    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.RJT_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.MT;
    /**
     * 蜜糖增量商户，日结通交易，支付给易宝0.012%
     */
    protected static BigDecimal MT_RJT_TRX_COST_RATE = new BigDecimal(0.00012).setScale(5, BigDecimal.ROUND_HALF_UP);


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
        return MT_RJT_TRX_COST_RATE;
    }

}
