package com.yeepay.g3.core.ymf.facade.spi;

import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.facade.spi.CodAsyncNotifyFacade;
import org.junit.Test;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class CodAsyncNotifyFacadeTest extends ApplicationContextAwareTest {

    @Test
    public void findCodAsynNotify() throws YmfException {
        CodAsyncNotifyFacade facade = getBean(CodAsyncNotifyFacade.class);
        CodNotifyArgs args = new CodNotifyArgs();
        List<CodNotifyDTO> list = facade.findCodAsynNotify(args);
        deepPrint(list);
    }

    @Test
    public void updateCodAsynNotify() throws YmfException {
        CodAsyncNotifyFacade facade = getBean(CodAsyncNotifyFacade.class);
        CodNotifyArgs args = new CodNotifyArgs();
        args.setStatus("FAIL");
        args.setExternalId("1111111");
        facade.updateCodAsynNotify(args);
    }
}
