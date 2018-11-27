package com.yeepay.g3.facade.ymf.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 正则校验 实现toString()的Object
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/17.
 */
@ValidatorRule
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegValidator {

    /**
     * 属性名称
     * @return 页面提示
     */
    String value() default "";

    /**
     * 校验结果消息 必填
     * @return value() + msg()
     */
    String msg();

    /**
     * 正则表达式 必填
     * @return reg
     */
    String reg();
}
