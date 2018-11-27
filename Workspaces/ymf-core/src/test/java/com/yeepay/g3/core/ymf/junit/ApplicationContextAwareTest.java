package com.yeepay.g3.core.ymf.junit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.junit.common.PrintTest;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class ApplicationContextAwareTest extends PrintTest {

    protected ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("ymf-application-test.xml");
        ConfigurationUtils.init();
        RemoteServiceFactory.init();
    }

    public <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }

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
