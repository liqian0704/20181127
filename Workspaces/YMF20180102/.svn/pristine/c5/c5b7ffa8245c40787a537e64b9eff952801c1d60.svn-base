package com.yeepay.g3.core.ymf.service.order;

import com.yeepay.g3.core.ymf.entity.common.HolidayInfo;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.service.common.HolidayInfoService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/18.
 */
public class HolidayInfoTest extends ApplicationContextAwareTest {
    public HolidayInfoService holidayInfoService;
    @Before
    public void init(){
        holidayInfoService = getBean(HolidayInfoService.class);
    }
    @Test
    public void save() throws Exception {

        HolidayInfo holidayInfo = new HolidayInfo();
        holidayInfo.setHolidayDate(new Date());
        holidayInfo.setStatus(Status.INIT);
        holidayInfo.setRemark("单元测试");
        holidayInfoService.save(holidayInfo);

    }

    @Test
    public void findByID() throws Exception {
        HolidayInfo holidayInfo = holidayInfoService.findByID(2L);
        System.out.println(holidayInfo.toString());
        holidayInfo.setRemark("单元测试修改");
        holidayInfoService.update(holidayInfo);
        System.out.println(holidayInfo.toString());
    }
    @Test
    public void getAll() throws Exception {
        List<HolidayInfo> holidayInfos = holidayInfoService.findAllByStatus(Status.INIT);
        System.out.println(holidayInfos.toString());
    }
    @Test
    public void getByHoliday() throws Exception {
        HolidayInfo holidayInfo = holidayInfoService.findByHolidayDate(DateUtil.getDate(new Date(),-1));
        System.out.println(holidayInfo.toString());
    }


}