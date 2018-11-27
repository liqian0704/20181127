package com.yeepay.g3.core.ymf.utils;

import com.yeepay.g3.core.ymf.junit.common.PrintTest;
import com.yeepay.g3.facade.ymf.dto.spi.CodNotifyArgs;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class BeanValidatorTest extends PrintTest {

    @Test
    public void testOneOf() {
        CodNotifyArgs args = new CodNotifyArgs();
        jsonPrint(BeanValidator.validateInclude(args, "status"));
        args.setStatus("123");
        jsonPrint(BeanValidator.validateInclude(args, "status"));
        args.setStatus(Status.SUCCESS.name());
        jsonPrint(BeanValidator.validateInclude(args, "status"));
    }
}
