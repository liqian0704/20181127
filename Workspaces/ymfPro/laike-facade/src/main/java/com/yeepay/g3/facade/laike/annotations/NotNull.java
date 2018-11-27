package com.yeepay.g3.facade.laike.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 非空校验 Object.class
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
@ValidatorRule
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

    /**
     * 属性名称
     *
     * @return 中文
     */
    String value() default "";

    /**
     * 返回小时
     *
     * @return value() + msg()
     */
    String msg() default "不能为空";

}
