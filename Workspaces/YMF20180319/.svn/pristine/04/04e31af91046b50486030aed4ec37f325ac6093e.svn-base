package com.yeepay.g3.core.ymf.service.common;

import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.facade.ymf.enumtype.Status;

import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 17/6/27.
 * 节假日信息查询
 *
 */
public interface HolidayInfoService {
    /**
     * 根据创建日期 查询节假日信息
     * @param begin
     * @param end
     * @return
     */
    public List<HolidayInfo> findByDate(Date begin, Date end) throws Exception;

    public void save(HolidayInfo holidayInfo) throws Exception;
    public void update(HolidayInfo holidayInfo) throws Exception;
    public HolidayInfo findByID(Long id) throws Exception;

    /**
     * 根据状态查询节假日信息
     * @param status
     * @return
     * @throws Exception
     */
    public List<HolidayInfo> findAllByStatus(Status status) throws Exception;

    /**
     * 根据节假日查询信息
     * @param holiday
     * @return
     * @throws Exception
     */
    public HolidayInfo findByHolidayDate(Date holiday) throws Exception;
}
