package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.business.BusinessMapper;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.business.BusinessParam;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by yp-tc-m-2762 on 16/8/11.
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> findAllBusiness() {
        BusinessParam param = new BusinessParam();
        param.createCriteria().andStatusEqualTo(Status.ACTIVE);
        return businessMapper.selectByFilter(param);
    }

    @Override
    public Business getBusinessByCode(String code) {
        BusinessParam param = new BusinessParam();
        param.createCriteria().andBizCodeEqualTo(code);
        List<Business> list = businessMapper.selectByFilter(param);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Business getBusinessById(Long id) {
        return businessMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveBusiness(Business business) {
        business.setOptimisitc(0L);
        business.setCreateTime(new Date());
        return businessMapper.insert(business);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateBusiness(Business business) {
        business .setUpdateTime(new Date());
        return businessMapper.updateByPrimaryKeySelective(business);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveBusiness(OperateEntity<Business> en) {
        en.getEntity().setOptimisitc(0L);
        en.getEntity().setCreateTime(new Date());
        return businessMapper.insert(en.getEntity());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateBusiness(OperateEntity<Business> en) {
        en.getEntity().setUpdateTime(new Date());
        return businessMapper.updateByPrimaryKeySelective(en.getEntity());
    }
}
