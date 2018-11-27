package com.yeepay.g3.core.ymf.service.material;

import com.yeepay.g3.core.ymf.junit.common.BaseAnnotationContextAwareTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
public class TermServiceTest extends BaseAnnotationContextAwareTest {

    @Autowired
    private TermService termService;

    @Test
    public void queryBySerials() throws Exception {
        Set<String> serials = new HashSet<String>();
        serials.add("123qwe123");
        serials.add("123qwe124");
        serials.add("123qwe125");
        serials.add("123qwe126");
        deepPrint(termService.queryBySerials(serials));
    }

    @Test
    public void queryExistSerials() throws Exception {
        Set<String> serials = new HashSet<String>();
        serials.add("123qwe123");
        serials.add("123qwe124");
        serials.add("123qwe125");
        serials.add("123qwe126");
        deepPrint(termService.queryExistSerials(serials));
    }

}