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

    @Test
    public void testString() {
        String str = "123123123213";
        str = str.replaceAll("3", "");
        byte[] bytes = str.getBytes();
        byte[] newBytes = new byte[str.length()];

        for (int i = 0, j = bytes.length - 1; i < j; i ++) {
            byte b = bytes[i];
            if (b == "3".getBytes()[0]) {

            } else {
                newBytes[i] = b;
            }
        }
        newBytes.toString();
    }
}
