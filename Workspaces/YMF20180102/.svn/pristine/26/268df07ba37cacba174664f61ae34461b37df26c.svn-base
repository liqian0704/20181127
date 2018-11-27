package com.yeepay.g3.core.ymf.dao.business;

import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.business.BusinessParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessMapper {
    int countByFilter(BusinessParam filter);

    int deleteByFilter(BusinessParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    int insertSelective(Business record);

    List<Business> selectByFilter(BusinessParam filter);

    Business selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Business record, @Param("example") BusinessParam filter);

    int updateByFilter(@Param("record") Business record, @Param("example") BusinessParam filter);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}