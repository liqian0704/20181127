package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 收款宝数据
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 上午10:51
 */
@Service("skbStockCalculator")
public class SkbStockCalculatorImpl extends AbstractProfitCalculator {
    /**
     * 存量商户毛利计算交易金额临界值:9.6亿
     */
    private static final BigDecimal STOCK_CRITICAL_AMT = new BigDecimal(960000000);
    /**
     * 蜜糖低于9.6分配占比
     */
    private static final BigDecimal MT_BELOWPART_RATE = new BigDecimal(0.2).setScale(4, BigDecimal.ROUND_HALF_UP);
    /**
     * 蜜糖高于9.6占比   2018.03.16 调整为：4:6
     */
    private static final BigDecimal MT_ABOVEPART_RATE = new BigDecimal(0.4).setScale(4, BigDecimal.ROUND_HALF_UP);
    /**
     * 产品类型
     */
    private static final ProfitProductTypeEnum PRODUCT_TYPE = ProfitProductTypeEnum.SKB_PROFIT;
    /**
     * 商户类型
     */
    private static final CustomerTypeEnum CUSTOMER_TYPE = CustomerTypeEnum.STOCK;


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
        return new BigDecimal(0);
    }
    /**
     * 计算收款宝，存量商户的毛利的 平均分配比例；
     * </br>
     * 存量商户，9.6亿金额以内按照0.2算；
     * 9.6亿金额以外按照0.5算。最后计算一个平均值。
     */
    private BigDecimal calculateStockSkbProfitPercent(ProfitSummation summation){

        //1. 存量汇总
        BigDecimal skbStockTotalTrxAmt = summation.getTotalTrxAmt();
        BigDecimal skbStockTotalProfitAmt = summation.getTotalProfitAmt();

        if (skbStockTotalProfitAmt.doubleValue() <= 0 || skbStockTotalTrxAmt.doubleValue() <= 0) {
            return new BigDecimal(0);
        }

        //4.计算占比,平均比例
        //蜜糖存量商户最终分润金额
        BigDecimal mitangTotalProfitAmt = Amount.getZeroAmount();
        BigDecimal mitangTotalTrxAmt = Amount.getZeroAmount();
        //蜜糖低于9.6的分润金额
        BigDecimal mittangBelowPartProfitAmt = Amount.getZeroAmount();
        //蜜糖高于9.6的分润金额
        BigDecimal mittangAbovePartProfitAmt = Amount.getZeroAmount();

        BigDecimal belowPartTrxAmt = Amount.getZeroAmount();
        BigDecimal abovePartTrxAmt = Amount.getZeroAmount();
        //零的校验
        if (skbStockTotalTrxAmt.compareTo(STOCK_CRITICAL_AMT) > 0) {
            //大于9.6
            belowPartTrxAmt = STOCK_CRITICAL_AMT;
            abovePartTrxAmt = skbStockTotalTrxAmt.subtract(STOCK_CRITICAL_AMT);
        } else {
            belowPartTrxAmt = skbStockTotalTrxAmt;
        }
        //占比
        BigDecimal belowPartPercent = belowPartTrxAmt.divide(skbStockTotalTrxAmt,6, BigDecimal.ROUND_HALF_UP);
        mittangBelowPartProfitAmt = skbStockTotalProfitAmt.multiply(belowPartPercent).multiply(MT_BELOWPART_RATE);

        //占比
        BigDecimal abovePartPercent = abovePartTrxAmt.divide(skbStockTotalTrxAmt,6, BigDecimal.ROUND_HALF_UP);
        mittangAbovePartProfitAmt = skbStockTotalProfitAmt.multiply(abovePartPercent).multiply(MT_ABOVEPART_RATE);

        mitangTotalProfitAmt = mittangBelowPartProfitAmt.add(mittangAbovePartProfitAmt);
        mitangTotalTrxAmt = belowPartTrxAmt.multiply(MT_BELOWPART_RATE).add(abovePartTrxAmt.multiply(MT_ABOVEPART_RATE));

        //计算蜜糖平均分配比例
        BigDecimal mitangAveragePercent = mitangTotalProfitAmt.divide(skbStockTotalProfitAmt,6, BigDecimal.ROUND_HALF_UP);

        summation.setPercent(mitangAveragePercent);
        summation.setMitangTotalTrxamt(mitangTotalTrxAmt);
        summation.setMitangTotalProfitAmt(mitangTotalProfitAmt);

        //接下来,存量部分全部按照平均分配比例
        return mitangAveragePercent;
    }

    /**
     * 汇总数据当前数据。
     *
     */
    @Override
    protected ProfitSummation querySummation(String month,boolean reCalculate) {
        //先查询是否已经汇总过
        ProfitSummation localSummation = profitSummationService.findByProductType(PRODUCT_TYPE,
                CUSTOMER_TYPE, month);
        if (localSummation != null && !reCalculate) {
            return localSummation ;
        }

        ProfitSummation summation = profitService.sumByProductType(PRODUCT_TYPE, CUSTOMER_TYPE, month);
        if (summation == null) {
            //没有需要计算的数据,直接记录一条交易金额为0的
            summation = super.initEmptySummation(PRODUCT_TYPE,CUSTOMER_TYPE,month);
        }

        //和其他存量产品相比，收款宝交易的分配比例为：平均比例。
        calculateStockSkbProfitPercent(summation);
        summation.setCalculateStatus(Status.INIT);

        if (localSummation != null && reCalculate) {
            updateLocalSummation(localSummation,summation);
            return localSummation;
        }
        profitSummationService.save(summation);
        return summation;
    }

    /**
     * 计算一条费用
     *
     * @param summation
     * @param profit
     * @return
     */
    @Override
    protected void calculateOne(ProfitSummation summation, Profit profit) {
        BigDecimal trxAmt = profit.getTrxAmt();
        BigDecimal profitAmt = profit.getProfitAmt();

        //平均的分配比例。
        BigDecimal averagePercent = summation.getPercent();

        profit.setMitangTrxamt(trxAmt.multiply(averagePercent));
        profit.setMitangProfitAmt(profitAmt.multiply(averagePercent));
        profit.setPercent(averagePercent);
        profit.setSummationId(summation.getId());
        profit.setUpdateTime(new Date());
        profit.setCalculateStatus(Status.SUCCESS);
    }
}
