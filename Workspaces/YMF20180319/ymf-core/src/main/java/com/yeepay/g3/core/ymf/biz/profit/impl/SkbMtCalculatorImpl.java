package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.service.profit.ProfitService;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description: 收款宝数据
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 上午10:51
 */
@Service("skbMtCalculator")
public class SkbMtCalculatorImpl extends AbstractProfitCalculator {
    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.SKB_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.MT;


    @Autowired
    private ProfitService profitService;
    @Autowired
    private ProfitSummationService profitSummationService;

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
        return MT_TRX_COST_RATE;
    }
}
