package com.yeepay.g3.core.ymf.dao.profit;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @description: 从Reporter查询
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 下午5:41
 */
@Repository
public interface ReportorDao {

    List<Profit> selectByType(@Param("profitProductType") ProfitProductTypeEnum profitProductType,
                              @Param("beginDate") Date beginDate,
                              @Param("endDate") Date endDate,
                              @Param("lowNum") int lowNum,
                              @Param("highNum") int highNum);

    int countByType(@Param("profitProductType") ProfitProductTypeEnum profitProductType,
                    @Param("beginDate") Date beginDate,
                    @Param("endDate") Date endDate);
}
