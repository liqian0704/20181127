package com.yeepay.g3.ymf.boss.support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: 数据字典批量参数
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2016/8/30.
 *
 * @see com.yeepay.g3.ymf.boss.support.method.DictBatchArgsResolver
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictBatchArgs {
}
