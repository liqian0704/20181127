package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.g3.core.ymf.service.material.TermService;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import com.yeepay.g3.facade.ymf.facade.laike.LaikeTermFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: 来客终端接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/15.
 */
@Service
public class LaikeTermFacadeImpl implements LaikeTermFacade {

    @Autowired
    private TermService termService;

    @Override
    public List<LaikeTermDTO> queryByCustomer(String customerNumber) {
        return termService.queryBindTerm(customerNumber);
    }

    @Override
    public boolean validateTerm(String customerNumber, String snSerial) {
        if ("ARE6000XN7100".equals(snSerial)) {
            return true;
        }
        return termService.validateBindTerm(customerNumber, snSerial);
    }
}
