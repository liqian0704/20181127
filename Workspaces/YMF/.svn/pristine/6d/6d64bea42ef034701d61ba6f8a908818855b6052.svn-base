package com.yeepay.g3.core.ymf.dao.common;

import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HolidayInfoDao {

    List<HolidayInfo> findAllByStatus(@Param("status") Status status) ;
    HolidayInfo findByHolidayDate(@Param("holiday") Date holiday);
}