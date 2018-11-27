package com.yeepay.g3.core.ymf.junit;

import com.yeepay.g3.core.ymf.junit.common.PrintTest;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
public class RmiContextAwareTest extends PrintTest {

    protected ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("ymf-rmi-test.xml","ymf-application-test.xml");

    }

    public <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
