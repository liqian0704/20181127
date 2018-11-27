package com.yeepay.g3.core.ymf.dao.gratuity;

import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;
import com.yeepay.g3.core.ymf.entity.gratuity.GratuityParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GratuityMapper {
    int countByFilter(GratuityParam filter);

    int deleteByFilter(GratuityParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Gratuity record);

    int insertSelective(Gratuity record);

    List<Gratuity> selectByFilter(GratuityParam filter);

    Gratuity selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Gratuity record, @Param("example") GratuityParam filter);

    int updateByFilter(@Param("record") Gratuity record, @Param("example") GratuityParam filter);

    int updateByPrimaryKeySelective(Gratuity record);

    int updateByPrimaryKey(Gratuity record);



}