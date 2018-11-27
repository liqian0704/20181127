package com.yeepay.g3.core.ymf.dao.gratuity;

import com.yeepay.g3.core.ymf.entity.gratuity.GratuityOrder;
import com.yeepay.g3.core.ymf.entity.gratuity.GratuityOrderParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GratuityOrderMapper {
    int countByFilter(GratuityOrderParam filter);

    int deleteByFilter(GratuityOrderParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(GratuityOrder record);

    int insertSelective(GratuityOrder record);

    List<GratuityOrder> selectByFilter(GratuityOrderParam filter);

    GratuityOrder selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") GratuityOrder record, @Param("example") GratuityOrderParam filter);

    int updateByFilter(@Param("record") GratuityOrder record, @Param("example") GratuityOrderParam filter);

    int updateByPrimaryKeySelective(GratuityOrder record);

    int updateByPrimaryKey(GratuityOrder record);
}