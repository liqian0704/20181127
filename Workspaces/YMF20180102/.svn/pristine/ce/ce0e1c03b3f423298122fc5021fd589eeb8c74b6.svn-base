package com.yeepay.g3.core.ymf.dao.profit;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.vo.profit.RowMap;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:38
 */
@Repository
public interface ProfitDao {

    /**
     * 批量保存
     *
     * @param list
     */
    public int batchSave(@Param("list") List<Profit> list) ;

    /**
     * 批量更新
     */
    public int batchUpdate(@Param("list") List<Profit> list) ;

    List<Profit> findByUniqueIdSet(@Param("set")Set<String> set);

    /**
     * 批量查询，根据uniqueId
     * @param set   UniqueId的set集合
     */
    Set<String> findUniqueIdByUniqueIdSet(@Param("set") Set<String> set);

    /**
     * 根据ID分页查询数据。
     */
    public List<Profit> findByProductTypeAndIdPage(@Param("profitType") ProfitProductTypeEnum profitType,
                                          @Param("customerType") CustomerTypeEnum customerType,
                                          @Param("month") String month,
                                          @Param("calculateStatus") Status status,
                                          @Param("lowRowNum") int lowId,
                                          @Param("highRowNum") int highId);

    /**
     * 查询Rownum对应的ID
     * @param profitType
     * @param customerType
     * @param month
     * @param status
     * @param set       rownum的集合
     */
    public List<RowMap> findRowMapByProductTypeAndRownum(@Param("profitType") ProfitProductTypeEnum profitType,
                                                         @Param("customerType") CustomerTypeEnum customerType,
                                                         @Param("month") String month,
                                                         @Param("calculateStatus") Status status,
                                                         @Param("set") Set<Long> set);

    public ProfitSummation sumByProductType(@Param("profitType") ProfitProductTypeEnum profitType,
                                            @Param("customerType") CustomerTypeEnum customerType,
                                            @Param("month") String month,
                                            @Param("calculateStatus") Status status);

    /**
     * 统计条数
     */
    public Integer countByProductType(@Param("profitType") ProfitProductTypeEnum profitType,
                                            @Param("customerType") CustomerTypeEnum customerType,
                                            @Param("month") String month,
                                            @Param("calculateStatus") Status status);
}
