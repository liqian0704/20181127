package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 认证费用蜜糖发展商户计算
 * @author: xiaobin.liu
 * @date: 17/12/21
 * @time: 下午4:02
 */
@Service("authMtCalculator")
public class AuthMtCalculatorImpl extends AbstractProfitCalculator implements ProfitCalculator {

    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.AUTH_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.MT;
    /**
     * 蜜糖增量商户，认证费用收入  全部给密堂
     */
    protected static BigDecimal MT_AUTH_TRX_COST_RATE = new BigDecimal(0.00000).setScale(5, BigDecimal.ROUND_HALF_UP);


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
        return MT_AUTH_TRX_COST_RATE;
    }

}
