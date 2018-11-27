package com.yeepay.g3.core.ymf.dao.common;

import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.core.ymf.entity.common.HolidayInfoParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HolidayInfoMapper {
    int countByFilter(HolidayInfoParam filter);

    int deleteByFilter(HolidayInfoParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(HolidayInfo record);

    int insertSelective(HolidayInfo record);

    List<HolidayInfo> selectByFilter(HolidayInfoParam filter);

    HolidayInfo selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") HolidayInfo record, @Param("example") HolidayInfoParam filter);

    int updateByFilter(@Param("record") HolidayInfo record, @Param("example") HolidayInfoParam filter);

    int updateByPrimaryKeySelective(HolidayInfo record);

    int updateByPrimaryKey(HolidayInfo record);
}