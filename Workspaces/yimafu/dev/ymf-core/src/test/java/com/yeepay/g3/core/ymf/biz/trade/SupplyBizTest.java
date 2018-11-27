package com.yeepay.g3.core.ymf.biz.trade;

import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Created by chen.liu on 2016/9/7.
 */
public class SupplyBizTest extends ApplicationContextAwareTest {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void supplyOrder() throws Exception {
        SupplyBiz biz = getBean(SupplyBiz.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = df.parse("2016-09-07 16:00:00");
        Date to = df.parse("2016-09-08 00:00:00");
        biz.supplyOrder(from, to);
    }

    @Test
    public void supplyRefund() throws Exception {
        SupplyBiz biz = getBean(SupplyBiz.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = df.parse("2016-09-07 00:00:00");
        Date to = df.parse("2016-09-07 01:00:00");
        biz.supplyRefund(from, to);
    }

    @Test
    public void expireOrder() throws Exception {
        SupplyBiz biz = getBean(SupplyBiz.class);
        Date from = df.parse("2016-09-01 00:00:00");
        biz.expireOrder(from, new Date());
    }

}