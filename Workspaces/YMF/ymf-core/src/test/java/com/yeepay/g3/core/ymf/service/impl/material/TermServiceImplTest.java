package com.yeepay.g3.core.ymf.service.impl.material;

import com.yeepay.g3.core.ymf.junit.common.BaseAnnotationContextAwareTest;
import com.yeepay.g3.core.ymf.service.material.TermService;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import com.yeepay.g3.facade.ymf.facade.laike.LaikeTermFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/15.
 */
public class TermServiceImplTest extends BaseAnnotationContextAwareTest {

    @Autowired
    private TermService termService;

    @Test
    public void queryBindTerm() throws Exception {
        List<LaikeTermDTO> laikeTermDTOS =  termService.queryBindTerm("20000000007");
        deepPrint(laikeTermDTOS);
    }

    @Test
    public void queryBindTermFacade() {
        List<LaikeTermDTO> laikeTermDTOS = getFacade(LaikeTermFacade.class).queryByCustomer("20000000007");
        deepPrint(laikeTermDTOS);
    }
}