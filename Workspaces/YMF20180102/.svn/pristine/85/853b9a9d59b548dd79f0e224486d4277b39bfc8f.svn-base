package com.yeepay.g3.core.ymf.dao.profit;

import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummationParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfitSummationMapper {
    int countByFilter(ProfitSummationParam filter);

    int deleteByFilter(ProfitSummationParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(ProfitSummation record);

    int insertSelective(ProfitSummation record);

    List<ProfitSummation> selectByFilter(ProfitSummationParam filter);

    ProfitSummation selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") ProfitSummation record, @Param("example") ProfitSummationParam filter);

    int updateByFilter(@Param("record") ProfitSummation record, @Param("example") ProfitSummationParam filter);

    int updateByPrimaryKeySelective(ProfitSummation record);

    int updateByPrimaryKey(ProfitSummation record);
}