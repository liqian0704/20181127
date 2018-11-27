package com.yeepay.g3.facade.laike.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 数字校验
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
@ValidatorRule
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Max {

    /**
     * 边界值
     *
     * @return 包含
     */
    int value();

    /**
     * 属性名
     *
     * @return
     */
    String name();

    /**
     * 提示消息
     *
     * @return
     */
    String msg() default "%s不能大于%s";
}
