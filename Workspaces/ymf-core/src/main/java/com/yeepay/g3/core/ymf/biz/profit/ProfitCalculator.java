package com.yeepay.g3.core.ymf.biz.profit;

//import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;

/**
 * @description: 毛利计算者
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午2:20
 */
public interface ProfitCalculator {

    /**
     * 计算
     */
    public int calculate(String month, boolean reCaculate);

}
