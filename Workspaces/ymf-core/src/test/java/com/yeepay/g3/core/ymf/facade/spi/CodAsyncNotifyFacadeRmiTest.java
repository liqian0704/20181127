package com.yeepay.g3.core.ymf.facade.spi;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.facade.spi.CodAsyncNotifyFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Title: COD异步接口单元测试
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class CodAsyncNotifyFacadeRmiTest extends SoaContextAwareTest {

    @Test
    public void findCodAsynNotify() throws YmfException {
        CodAsyncNotifyFacade facade = RemoteServiceFactory.getService(CodAsyncNotifyFacade.class);
        CodNotifyArgs args = new CodNotifyArgs();
        Date from = DateUtil.getDate(new Date(), -1);
        args.setStartDate(from);
        args.setEndDate(new Date());
//        args.setStatus(Status.SUCCESS.name());
        args.setExternalId("464306704960212992");
        List<CodNotifyDTO> list = facade.findCodAsynNotify(args);
        deepPrint(list);
    }

    @Test
    public void updateCodAsynNotify() throws YmfException {
        CodAsyncNotifyFacade facade = RemoteServiceFactory.getService(CodAsyncNotifyFacade.class);
        CodNotifyArgs args = new CodNotifyArgs();
        args.setStatus("FAIL");
        args.setExternalId("464306704960212993");
        facade.updateCodAsynNotify(args);
    }
}
