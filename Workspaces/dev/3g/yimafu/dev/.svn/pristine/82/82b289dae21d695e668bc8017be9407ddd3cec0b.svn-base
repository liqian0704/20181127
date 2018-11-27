package com.yeepay.g3.core.ymf.junit;

import com.yeepay.bridge.pos.AccHisDTO;
import com.yeepay.bridge.pos.RemoteAccountFacade;
import com.yeepay.g3.core.ymf.biz.CustomerSettleInfo2gBiz;
import com.yeepay.g3.core.ymf.dao.customer.CustomerSettleDao;
import com.yeepay.g3.core.ymf.ext.G2ServiceExt;
import com.yeepay.g3.core.ymf.junit.common.SpringPrintTest;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.laike.SettleDTO;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/10/27.
 */
@DirtiesContext
@ContextConfiguration({"/ymf-application-test.xml"}) //加载配置文件
public class AnnotationContextAwareTest extends SpringPrintTest {

    protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected DateFormat df_m = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() {
        RemoteServiceFactory.init();
        ConfigurationUtils.init();
    }
    @Autowired
    G2ServiceExt g2ServiceExt ;

    @Autowired
    RemoteAccountFacade remoteAccountFacade;

    @Autowired
    CustomerSettleInfo2gBiz customerSettleInfo2gBiz;

    @Autowired
    CustomerSettleDao customerSettleDao;

    @Test
    public void testG2ServiceExt() {
        g2ServiceExt.queryCustomerHmacKey("c");
    }

    @Test
    public void test() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = df.parse("2016-04-09 00:00:00");

            Date start = df.parse("2016-04-25 00:00:00");
            Date end = df.parse("2016-04-26 00:00:00");
            Date from = DateUtil.getFirstOfDay(time);
            Date to = DateUtil.getLastOfDay(time);
            Long customerId = Long.valueOf("10040011865");
            String[] pers = remoteAccountFacade.getPermissions(customerId);
            System.out.println(Arrays.toString(pers));

            System.out.println("---");
            // 10040011865 10040007800
            List<AccHisDTO> result = remoteAccountFacade.getAccHisByType(start, end, "MERCHANT_SETTLE", "10040007800");
            System.out.println(result.size());
            for(int i=0;i<result.size();i++){
                System.out.print(result.get(i).toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSync() {
        Date time = null;
        try {
            time = df.parse("2016-04-27 00:00:00");
            customerSettleInfo2gBiz.customerSettleInfo("10040007800", time);

            time = df.parse("2016-04-25 00:00:00");
            customerSettleInfo2gBiz.customerSettleInfo("10040007800", time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCustomerSettleDao() {
        SettleArgs args = new SettleArgs();
        args.setCount(true);
        args.setCustomerNumber("10040007800");
        args.setStart(0);
        args.setPage(20);
        try {
            args.setStartDate(df_m.parse("2016-04-27"));
            args.setEndDate(df_m.parse("2016-04-27"));
            List<SettleDTO> settleDTOList = customerSettleDao.querySettle(args);
            deepPrint(settleDTOList);

            CountResponse count = customerSettleDao.countSettle(args);
            deepPrint(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
