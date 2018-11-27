package com.yeepay.g3.facade.ymf.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 长度校验 String.class
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/17.
 */
@ValidatorRule
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LengthValidator {

    /**
     * 提示属性名称
     * @return 中文名称
     */
    String value() default "";

    /**
     * 最大长度 必填
     * @return 包含
     */
    int length();

    /**
     * 提示信息
     * @return 返回的校验结果是value() + format(msg(), length())
     */
    String msg() default "超过长度限制%s";
}
