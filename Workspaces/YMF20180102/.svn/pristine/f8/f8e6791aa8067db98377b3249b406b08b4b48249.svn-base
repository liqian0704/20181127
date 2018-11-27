package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.agent.SyncOrderParam;
import com.yeepay.g3.facade.ymf.dto.agent.SyncS0OrderDTO;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDTO;
import com.yeepay.g3.facade.ymf.enumtype.common.AppType;
import com.yeepay.g3.facade.ymf.facade.agent.NewSyncOrderFacade;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/10/26.
 */
public class NewSyncOrderDaoTest extends ApplicationContextAwareTest {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void queryS0OrderDTOByArgs() throws Exception {
        Date from = format.parse("2017-08-01");
        Date to = format.parse("2017-08-28");
        SyncOrderParam param = new SyncOrderParam();
        param.setAppType(AppType.ALLIANCE);
        param.setFrom(from);
        param.setTo(to);
        param.setStart(1);
        List<SyncS0OrderDTO> orderDTOList = getBean(NewSyncOrderFacade.class).queryS0Remit(param);
        deepPrint(orderDTOList);
    }

    @Test
    public void countS0OrderByArgs() throws Exception {
        Date from = format.parse("2017-08-01");
        Date to = format.parse("2017-08-28");
        SyncOrderParam param = new SyncOrderParam();
        param.setAppType(AppType.ALLIANCE);
        param.setFrom(from);
        param.setTo(to);
        CountResponse response = getBean(NewSyncOrderFacade.class).countS0Remit(param);
        deepPrint(response);
    }
}
