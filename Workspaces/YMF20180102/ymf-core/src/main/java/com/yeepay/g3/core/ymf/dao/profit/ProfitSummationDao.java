package com.yeepay.g3.core.ymf.dao.profit;

import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:39
 */
@Repository
public interface ProfitSummationDao {

    /**
     * 查询某个月，指定产品，指定商户类型数据。
     */
    ProfitSummation findByProductType(@Param("profitType") ProfitProductTypeEnum profitType,
                                      @Param("customerType") CustomerTypeEnum customerType,
                                      @Param("month") String month);

}
