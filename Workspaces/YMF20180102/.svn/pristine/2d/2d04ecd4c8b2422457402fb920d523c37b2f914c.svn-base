package com.yeepay.g3.core.ymf.service.base;

import com.yeepay.g3.core.ymf.support.OperateEntity;

/**
 * Title: Service层基础类
 * Description: 暂时BOSS系统使用, 请求类接口不需要继承
 * Copyright: Copyright (c)2016
 * Company: YeePay
 *
 * @author chen.liu on 16/8/18.
 *
 * @see OperateEntity
 * @see com.yeepay.g3.core.ymf.aop.ServiceLoggerInterceptor
 */
public interface GenericService<T> {

    /**
     * 增加
     * @param wrapper wrapper
     * @return
     */
    int save(OperateEntity<T> wrapper);

    /**
     * 更新
     * @param wrapper wrapper
     * @return
     */
    int update(OperateEntity<T> wrapper);

    /**
     * 删除
     * 删除慎用 并且参数必须是实体(因为要记录日志)
     * @param wrapper wrapper
     * @return
     */
    int delete(OperateEntity<T> wrapper);

}
