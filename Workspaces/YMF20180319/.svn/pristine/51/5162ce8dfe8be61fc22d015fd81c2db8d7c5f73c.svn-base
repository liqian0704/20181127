package com.yeepay.g3.core.ymf.dao.profit;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitMapper {
    int countByFilter(ProfitParam filter);

    int deleteByFilter(ProfitParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Profit record);

    int insertSelective(Profit record);

    List<Profit> selectByFilter(ProfitParam filter);

    Profit selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Profit record, @Param("example") ProfitParam filter);

    int updateByFilter(@Param("record") Profit record, @Param("example") ProfitParam filter);

    int updateByPrimaryKeySelective(Profit record);

    int updateByPrimaryKey(Profit record);
}