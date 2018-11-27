package com.yeepay.g3.core.ymf.facade.impl.spi;

import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.service.cod.CodNotifyInfoService;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.facade.spi.CodAsyncNotifyFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: COD异步通知易码付接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
@Service
public class CodAsyncNotifyFacadeImpl implements CodAsyncNotifyFacade {

    private static final Logger log = LoggerFactory.getLogger(CodAsyncNotifyFacadeImpl.class);

    @Autowired
    private CodNotifyInfoService codNotifyInfoService;

    @Override
    public List<CodNotifyDTO> findCodAsynNotify(CodNotifyArgs args) throws YmfException {
        log.info("#YMF-COD findCodAsynNotify BEGIN#");
        ResponseMessage resp = BeanValidator.validateExclude(args, "externalId");
        if (!resp.isOk()) { // 参数校验ok
            throw new YmfException(resp);
        }
        return codNotifyInfoService.queryDTOByArgs(args);
    }

    @Override
    public void updateCodAsynNotify(CodNotifyArgs args) throws YmfException {
        log.info("#YMF-COD updateCodAsynNotify BEGIN#");
        ResponseMessage resp = BeanValidator.validateExclude(args, "startDate", "endDate");
        if (!resp.isOk()) {
            throw new YmfException(resp);
        }
        // 业务校验
        CodNotifyInfo codNotifyInfo = codNotifyInfoService.queryByArgs(args);
        if (null == codNotifyInfo) {
            throw new YmfException(ResponseMessage.error("根据条件未查询到COD通知信息 args:" + args));
        }
        // 更新状态 更新时间
        codNotifyInfo.setStatus(Status.SUCCESS);
        codNotifyInfo.setUpdateTime(new Date());
        try {
            int result = codNotifyInfoService.updateById(codNotifyInfo);
            if (1 != result) {
                throw new YmfException(ResponseMessage.error("更新COD通知信息状态失败"));
            }
        } catch (Exception e) {
            log.error("更新COD异步通知消息失败", e);
            throw new YmfException(ResponseMessage.error("更新COD通知信息状态异常", e));
        }

    }
}
