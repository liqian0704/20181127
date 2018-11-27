package com.yeepay.g3.core.ymf.service.profit;

//import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;

import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:49
 */
public interface ProfitSummationService {
    int save(ProfitSummation entity);

    int update(ProfitSummation entity);

    int updateSelective(ProfitSummation entity);

    ProfitSummation findById(Long id);

    ProfitSummation findByProductType(ProfitProductTypeEnum profitType,
                      CustomerTypeEnum customerType, String month);

    List<ProfitSummation> findByMonth(String month,Status status);

    Integer deleteByMonth(@Param("month") String month);
}
