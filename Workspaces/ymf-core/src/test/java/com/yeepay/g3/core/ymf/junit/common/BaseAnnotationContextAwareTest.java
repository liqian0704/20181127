package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.junit.Before;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/10/27.
 */
@DirtiesContext
@ContextConfiguration({"/ymf-application-test.xml"}) //加载配置文件
public class BaseAnnotationContextAwareTest extends SpringPrintTest {

    protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected DateFormat df_m = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() {
        RemoteServiceFactory.init();
        ConfigurationUtils.init();
    }

    protected <T> T getFacade(Class<T> clazz) {
        return RemoteServiceFactory.getService(clazz);
    }
}
