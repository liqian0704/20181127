package com.yeepay.g3.core.ymf.service.impl.common;

import com.yeepay.g3.core.ymf.dao.common.HolidayInfoDao;
import com.yeepay.g3.core.ymf.dao.common.HolidayInfoMapper;
import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.core.ymf.entity.common.HolidayInfoParam;
import com.yeepay.g3.core.ymf.service.common.HolidayInfoService;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 17/6/27.
 */
@Service
public class HolidayInfoServiceImpl implements HolidayInfoService {
    @Autowired
    public HolidayInfoMapper holidayInfoMapper;
    @Autowired
    public HolidayInfoDao holidayInfoDao;
    @Override
    public List<HolidayInfo> findByDate(Date begin, Date end) {
        HolidayInfoParam param = new HolidayInfoParam();
        param.createCriteria()
                .andCreateTimeBetween(begin, end);
        return holidayInfoMapper.selectByFilter(param);
    }

    @Override
    public void save(HolidayInfo holidayInfo) throws Exception {
        holidayInfoMapper.insertSelective(holidayInfo);
    }

    @Override
    public void update(HolidayInfo holidayInfo) throws Exception {
        holidayInfoMapper.updateByPrimaryKey(holidayInfo);
    }

    @Override
    public HolidayInfo findByID(Long id) throws Exception {
        return holidayInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<HolidayInfo> findAllByStatus(Status status) throws Exception {
        return holidayInfoDao.findAllByStatus(status);
    }

    @Override
    public HolidayInfo findByHolidayDate(Date holiday) throws Exception {
        return holidayInfoDao.findByHolidayDate(holiday);
    }
}
