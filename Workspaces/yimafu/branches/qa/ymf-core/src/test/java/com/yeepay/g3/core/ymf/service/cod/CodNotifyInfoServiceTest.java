package com.yeepay.g3.core.ymf.service.cod;

import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfoParam;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceGenerator;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.sp.NotifyType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/29.
 */
public class CodNotifyInfoServiceTest extends ApplicationContextAwareTest {
    private CodNotifyInfoService service;

    @Test
    public void save() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyInfo notifyInfo = new CodNotifyInfo();
        notifyInfo.setExternalId(SequenceGenerator.getInstance().generate().toString());
        notifyInfo.setCodInterface("CodPOSPIndRemoteService.remote");
        notifyInfo.setCreateDate(new Date());
        notifyInfo.setNotifyType(NotifyType.AUTO);
        notifyInfo.setSendNum(1);
        notifyInfo.setStatus(Status.INIT);
        notifyInfo.setTrxType(TrxType.PURCHASE.name());
        int result = service.save(notifyInfo);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateStateById() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyInfo notifyInfo = new CodNotifyInfo();
        notifyInfo.setStatus(Status.FAIL);
        notifyInfo.setId(7L);
        int result = service.updateStateById(notifyInfo);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateById() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyInfo notifyInfo = new CodNotifyInfo();
        notifyInfo.setStatus(Status.SUCCESS);
        notifyInfo.setId(7L);
        int result = service.updateById(notifyInfo);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updatePartial() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyInfo notifyInfo = new CodNotifyInfo();
        notifyInfo.setStatus(Status.SUCCESS);
        notifyInfo.setId(7L);
        notifyInfo.setOrderNo(SequenceUtils.createOrderSequence(7L));
        int result = service.updatePartial(notifyInfo);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByFilter() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyInfoParam param = new CodNotifyInfoParam();
        param.createCriteria().andExternalIdEqualTo("464306704960212992");
        List<CodNotifyInfo> list = service.selectByFilter(param);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("464306704960212992", list.get(0).getExternalId());
    }

    @Test
    public void queryDTOByArgs() throws Exception {
        service = getBean(CodNotifyInfoService.class);
        CodNotifyArgs args = new CodNotifyArgs();
        Date from = DateUtil.getDate(new Date(), -1);
        args.setStartDate(from);
        args.setEndDate(new Date());
        args.setStatus(Status.SUCCESS.name());
        args.setExternalId("464306704960212992");
        List<CodNotifyDTO> list = service.queryDTOByArgs(args);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void queryByArgs() throws Exception {

    }

}