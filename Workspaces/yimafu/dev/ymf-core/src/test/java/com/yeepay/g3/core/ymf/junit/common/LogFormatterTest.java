package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.utils.common.FileManageUtil;
import com.yeepay.g3.utils.common.log.LogFormattingTuple;
import com.yeepay.g3.utils.common.log.LogMessageFormatter;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/9/12.
 */
public class LogFormatterTest {

    @Test
    public void testFormmatter() {
        String format = "{}, {}, {}, o:{}";
        LogFormattingTuple ft = LogMessageFormatter.format(format, "123", new String[]{"456", "678"}, new Order());
        System.out.println(ft.getMessage());

    }

    @Test
    public void testFile() {
        System.out.println(FileManageUtil.mergePath("apps/data/", "/tomcat/", "/nohup.out"));
    }
}
