package com.yeepay.g3.core.ymf.junit.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/4.
 */
public class SpringPrintTest extends AbstractJUnit4SpringContextTests {

    /**
     * deep print
     * @param obj
     */
    protected void deepPrint(Object obj) {
        if (obj instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) obj;
            for (Object o : collection) {
                deepPrint(o);
            }
        } else {
            jsonPrint(obj);
        }
    }

    /**
     *  json print
     * @param obj
     */
    protected void jsonPrint(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssssss"));
            System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
