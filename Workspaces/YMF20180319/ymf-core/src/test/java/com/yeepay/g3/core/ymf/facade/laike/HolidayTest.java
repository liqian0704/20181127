package com.yeepay.g3.core.ymf.facade.laike;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.utils.holiday.HolidayUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by dongxulu on 16/12/30.
 */
public class HolidayTest extends SoaContextAwareTest {
    @Test
    public void isHoliday(){
        Date testDate = DateUtil.getDate(new Date(),5);
        System.out.println(testDate);
        System.out.println(HolidayUtils.isHoliday(testDate));

    }
    public void getAll(){

    }

}
