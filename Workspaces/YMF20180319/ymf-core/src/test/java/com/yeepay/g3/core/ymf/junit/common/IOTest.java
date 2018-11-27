package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/28.
 */
public class IOTest {

    @Test
    public void testObject() {
        try {
            CountResponse cr = new CountResponse();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(cr);
            byte[] buffer = baos.toByteArray();
            for (int i : buffer) {
                System.out.printf("%02X ", i & 0xFF);
            }
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
