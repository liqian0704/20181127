package com.yeepay.g3.core.ymf.support.annotations;

import com.yeepay.g3.facade.ymf.enumtype.OperateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 此注解只能写在service层方法上
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 * @see com.yeepay.g3.core.ymf.aop.ServiceLoggerInterceptor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface Logged {

    /**
     * 操作类型, 默认是select
     * 如果方法是查询, 需要记录日志, 则不需要写类型
     * @return
     */
    OperateType type() default OperateType.SELECT;
}
