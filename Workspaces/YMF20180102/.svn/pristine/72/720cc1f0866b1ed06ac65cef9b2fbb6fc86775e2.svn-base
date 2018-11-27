package com.yeepay.g3.core.ymf.facade.impl.term;

import com.yeepay.g3.core.ymf.biz.material.TermBiz;
import com.yeepay.g3.facade.ymf.dto.BizErrorCode;
import com.yeepay.g3.facade.ymf.dto.TermDTO;
import com.yeepay.g3.facade.ymf.exception.YmfBizException;
import com.yeepay.g3.facade.ymf.facade.posboss.TermFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Title: 来客对接POSBOSS库存系统
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
@Service
public class TermFacadeImpl implements TermFacade {

    private static final Logger log = LoggerFactory.getLogger(TermFacadeImpl.class);

    @Autowired
    private TermBiz termBiz;

    @Override
    public void batchInTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException {
        if (null == termDTOHashMap || 0 == termDTOHashMap.size()) {
            throw new YmfBizException(BizErrorCode.LACK_OF_PARAM, "入库终端不能为空");
        }
        termBiz.batchInTerm(termDTOHashMap);
    }

    @Override
    public int batchOutTerm(ArrayList<String> serialList) throws YmfBizException {
        return 0;
    }

}
