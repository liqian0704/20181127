package com.yeepay.g3.core.ymf.service.profit;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.vo.profit.RowMap;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:50
 */
public interface ProfitService {
    int save(Profit entity);

    int update(Profit entity);

    int updateSelective(Profit entity);

    Profit findById(Long id);

    /**
     * 批量保存
     * @param entitys
     * @return
     */
    int batchSave(List<Profit> entitys);

    /**
     * 批量更新
     */
    //int batchUpdate(List<Profit> entitys);

    /**
     * 使用jdbc批量更新
     * @param entitys
     */
    void batchUpdateUseJdbc(List<Profit> entitys);

    /**
     * 批量查询，根据uniqueId
     * @param set   UniqueId的set集合
     * @return
     */
    List<Profit> findByUniqueIdSet(Set<String> set);

    /**
     * 批量查询，根据uniqueId
     * @param set   UniqueId的set集合
     * @return
     */
    Set<String> findUniqueIdByUniqueIdSet(Set<String> set);

    /**
     * 批量查询，
     */
    public List<Profit> findByProductTypeAndIdPage(ProfitProductTypeEnum profitType,
                                          CustomerTypeEnum customerType, String month,Status calculateStatus,
                                          int lowRowNum,int highRowNum);

    List<RowMap> findRowMapByProductTypeAndRownum(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                                  String month, Status calculateStatus,
                                                  Set<Long> rownumSet);

    /**
     * 批量统计
     */
    public ProfitSummation sumByProductType(ProfitProductTypeEnum profitType,
                                              CustomerTypeEnum customerType, String month);


    ProfitSummation sumByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                     String month, Status status);

    int countByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                           String month, Status status);
}
