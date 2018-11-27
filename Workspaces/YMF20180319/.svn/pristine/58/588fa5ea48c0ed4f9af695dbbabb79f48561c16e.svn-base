package com.yeepay.g3.core.ymf.biz.profit;

import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;

import java.util.Date;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/13
 * @time: 下午5:38
 */
public interface ProfitBiz {

    /**
     * 通过流的方式同步。
     * @param beginDate
     * @param endDate
     */
    void sysnWithStream(Date beginDate,Date endDate) ;

    /**
     * 计算毛利
     */
    void calculate(ProfitProductTypeEnum productType, CustomerTypeEnum customerTypeEnum,
                   String month,boolean reCaculate);

    /**
     * 清除指定月份的数据。
     * @param month        月份。
     */
    void clearSysDatas(String month);
}
