package com.yeepay.g3.facade.ymf.facade;

/**
 * @description: 蜜糖毛利核算定时
 * @author: xiaobin.liu
 * @date: 17/12/13
 * @time: 下午5:34
 */
public interface ProfitTimerFacade {
    /**
     * 定时调用，计算当月，
     */
    void timerCalculateProfit(String productTypeStr,
                              String customerTypeStr) ;

    /**
     * 蜜糖毛利分润计算
     */
    void calculateProfit(String productTypeStr,
                              String customerTypeStr,
                              String month);

    /**
     * 手工触发支持重算。重算慎用。。
     */
    void calculateProfitWithRecaculate(String productTypeStr,
                         String customerTypeStr,
                         String month, Boolean reCaculate);

    /**
     * 定时同步，当前月份数据
     */
    void timerSysnWithStream();

    /**
     * 同步指定月份数据
     * @param beginDate         yyyyMMdd
     * @param endDate           yyyyMMdd
     */
    void sysnWithStream(String beginDate, String endDate);

    void clearSysDatas(String month);
}
