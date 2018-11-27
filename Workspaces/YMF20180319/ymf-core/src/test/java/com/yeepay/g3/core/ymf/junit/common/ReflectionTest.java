package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.core.ymf.utils.reflect.ReflectionUtils;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
public class ReflectionTest {

    @Test
    public void superField() {
        try {
            OrderArgs args = new OrderArgs();
            Field field = ReflectionUtils.getField(OrderArgs.class, "start");
            field.setAccessible(true);
            field.set(args, 22);
            System.out.println(args.getStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
