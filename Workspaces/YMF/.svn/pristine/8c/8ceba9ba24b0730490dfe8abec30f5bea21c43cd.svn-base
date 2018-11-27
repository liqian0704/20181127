package com.yeepay.g3.core.ymf.dao.shop;

import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.entity.shop.ShopParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    int countByFilter(ShopParam filter);

    int deleteByFilter(ShopParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Shop record);

    int insertSelective(Shop record);

    List<Shop> selectByFilter(ShopParam filter);

    Shop selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Shop record, @Param("example") ShopParam filter);

    int updateByFilter(@Param("record") Shop record, @Param("example") ShopParam filter);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);


}