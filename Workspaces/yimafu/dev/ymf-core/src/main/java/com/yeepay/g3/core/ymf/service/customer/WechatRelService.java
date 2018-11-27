package com.yeepay.g3.core.ymf.service.customer;

import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.service.base.GenericService;

import java.util.List;

/**
 * Title: 微信公众号关系
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/10/12.
 */
public interface WechatRelService extends GenericService<WechatRel> {

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    WechatRel findById(Long id);

    /**
     * 根据商编获取微信公众号关系
     * @param customerNumber 商编
     * @return
     */
    List<WechatRel> findByCustomer(String customerNumber);

    /**
     * 根据商编获取生效的微信公众号关系
     * @param customerNumber 商编
     * @return
     */
    WechatRel findActiveByCustomer(String customerNumber);

    /**
     * 商编是否有微信公众号关系
     * TODO 路由后,需要修改
     * @param customerNumber 商编
     * @param appId appId
     * @return true 有; false 没有
     */
    boolean hasRel(String customerNumber, String appId);
}
