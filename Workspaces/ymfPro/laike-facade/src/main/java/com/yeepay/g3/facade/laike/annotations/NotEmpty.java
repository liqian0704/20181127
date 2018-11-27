package com.yeepay.g3.facade.laike.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 非Empty String.class
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
@ValidatorRule
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    /**
     * 属性名称
     *
     * @return 中文
     */
    String value() default "";

    /**
     * 消息提示
     *
     * @return value() + msg()
     */
    String msg() default "不能为空或者空字符串";

}
