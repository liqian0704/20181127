package com.yeepay.g3.core.ymf.junit;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class AmountTest {

    @Test
    public void testAmount() {
        Long l1 = 100L;
        System.out.println(new BigDecimal(l1));
        BigDecimal bigDecimal = new BigDecimal("22.22");
        System.out.println(bigDecimal.longValue());
        System.out.println(bigDecimal.longValueExact());
    }
}
