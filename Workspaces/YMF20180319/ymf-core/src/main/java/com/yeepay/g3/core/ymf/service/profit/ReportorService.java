package com.yeepay.g3.core.ymf.service.profit;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * @description: 从report查询数据
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 下午5:31
 */
public interface ReportorService {

    /**
     * 查询所有数据
     *
     * @param profitProductType 毛利产品类型
     * @param beginDate         日期
     * @param endDate           日期
     * @return
     */
    List<Profit> queryProfits(ProfitProductTypeEnum profitProductType, Date beginDate,
                              Date endDate,int lowNum,int highNum);
    /**
     * 查询所有数据
     *
     * @param profitProductType 毛利产品类型
     * @param beginDate         日期
     * @param endDate           日期
     * @return
     */
    int countProfits(ProfitProductTypeEnum profitProductType, Date beginDate, Date endDate);
}
