package com.yeepay.g3.core.ymf.service.base;

import com.yeepay.g3.core.ymf.support.OperateEntity;

import java.util.List;

/**
 * Title: Service层基础批量类
 * Description: 暂时BOSS系统使用, 请求类接口不需要继承
 * Copyright: Copyright (c)2016
 * Company: YeePay
 *
 * @author chen.liu on 16/8/18.
 *
 * @see OperateEntity
 * @see com.yeepay.g3.core.ymf.aop.ServiceLoggerInterceptor
 */
public interface GenericBatchService<T> extends GenericService<T> {

    /**
     * 新增字典
     * @param wrapperList 批量处合集
     * @return
     */
    int batchSave(List<OperateEntity<T>> wrapperList);
}
