package com.yeepay.g3.facade.ymf.facade.spi;

import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyDTO;
import com.yeepay.g3.facade.ymf.exception.YmfException;

import java.util.List;

/**
 * Title: COD异步调用易码付通知接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public interface CodAsyncNotifyFacade {

    /**
     * 根据CodAsynInfo查询COD异步通知信息
     * @param args 参数
     * @return
     */
    List<CodNotifyDTO> findCodAsynNotify(CodNotifyArgs args) throws YmfException;

    /**
     * 更新异步信息表
     *
     * @param args 参数
     */
    void updateCodAsynNotify(CodNotifyArgs args) throws YmfException;
}
