package com.yeepay.g3.facade.ymf.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 枚举类型校验OneOf
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
@ValidatorRule
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneOf {

    /**
     * 属性名
     * @return
     */
    String value() default "";

    /**
     * 枚举类
     * 约定 如果是Enum.class则校验group()
     * 否则校验枚举对应的类
     * @return
     */
    Class<? extends Enum> type();

    /**
     * 枚举值
     * @return
     */
    String[] group() default {};

    /**
     * 校验消息
     * @return format(value() + enums + msg())
     */
    String msg() default "%s必须是%s中的一个";
}
