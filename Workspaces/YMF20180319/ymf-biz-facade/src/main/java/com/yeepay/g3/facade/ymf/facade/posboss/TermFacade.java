package com.yeepay.g3.facade.ymf.facade.posboss;

import com.yeepay.g3.facade.ymf.dto.TermDTO;
import com.yeepay.g3.facade.ymf.exception.YmfBizException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Title: 易码付对接POSBOSS库存系统
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public interface TermFacade {

    /**
     * 批量入库
     * @param termDTOHashMap 终端映射集合  key是{@link TermDTO#serial}终端号
     * @throws YmfBizException 业务异常 参数校验
     *
     */
    void batchInTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException;

    /**
     * 批量出库
     * @param serialList 终端号集合
     * @throws YmfBizException 业务异常
     * @return 0 全部失败, 1 全部成功, 2 部分成功
     */
    int batchOutTerm(ArrayList<String> serialList) throws YmfBizException;
}
