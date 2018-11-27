package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.customer.WechatRelMapper;
import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.entity.customer.WechatRelParam;
import com.yeepay.g3.core.ymf.service.customer.WechatRelService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: 商户与微信appid报备关系
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/10/12.
 */
@Service
public class WechatRelServiceImpl implements WechatRelService {

    @Autowired
    private WechatRelMapper wechatRelMapper;

    @Override
    public WechatRel findById(Long id) {
        return wechatRelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WechatRel> findByCustomer(String customerNumber) {
        WechatRelParam param = new WechatRelParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber);
        return wechatRelMapper.selectByFilter(param);
    }

    @Override
    public WechatRel findActiveByCustomer(String customerNumber) {
        WechatRelParam param = new WechatRelParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber)
                .andStatusEqualTo(Status.ACTIVE);
        List<WechatRel> relList = wechatRelMapper.selectByFilter(param);
        if (null != relList && relList.size() > 0) {
            return relList.get(0);
        }
        return null;
    }

    @Override
    public boolean hasRel(String customerNumber, String appId) {
        WechatRelParam param = new WechatRelParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber)
                .andAppIdEqualTo(appId);
        List<WechatRel> relList = wechatRelMapper.selectByFilter(param);
        return null != relList && relList.size() > 0;
    }

    @Override
    public int save(OperateEntity<WechatRel> wrapper) {
        wrapper.getEntity().setCreateTime(new Date());
        return wechatRelMapper.insert(wrapper.getEntity());
    }

    @Override
    public int update(OperateEntity<WechatRel> wrapper) {
        wrapper.getEntity().setUpdateTime(new Date());
        return wechatRelMapper.updateByPrimaryKey(wrapper.getEntity());
    }

    @Override
    public int delete(OperateEntity<WechatRel> wrapper) {
        return wechatRelMapper.deleteByPrimaryKey(wrapper.getEntity().getId());
    }
}
