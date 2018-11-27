package com.yeepay.g3.core.ymf.service.impl;


import com.yeepay.g3.core.ymf.dao.customer.CustomerSettleDao;
import com.yeepay.g3.core.ymf.dao.customer.CustomerSettleMapper;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettle;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleParam;
import com.yeepay.g3.core.ymf.service.CustomerSettleService;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.laike.SettleArgs;
import com.yeepay.g3.facade.ymf.dto.laike.SettleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商户清算信息查询
 * Created by yp-tc-m-2762 on 16/10/28.
 */
@Service
public class CustomerSettleServiceImpl implements CustomerSettleService{

    @Autowired
    private CustomerSettleMapper customerSettleMapper;
    @Autowired
    private CustomerSettleDao customerSettleDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomerSettle(CustomerSettle customerSettle) {
        return customerSettleMapper.insert(customerSettle);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(CustomerSettle customerSettle) {
        return customerSettleMapper.updateByPrimaryKey(customerSettle);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public CustomerSettle findByParam(String customerNumber, Date trxDate) {
        CustomerSettleParam param = new CustomerSettleParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber)
                .andTrxDateEqualTo(trxDate);
        List<CustomerSettle> settleList = customerSettleMapper.selectByFilter(param);
        if (null != settleList && settleList.size() > 0) {
            return settleList.get(0);
        }
        return null;
    }

    @Override
    public CountResponse countByArgs(SettleArgs args) {
        return customerSettleDao.countSettle(args);
    }

    @Override
    public List<SettleDTO> queryByArgs(SettleArgs args) {
        return customerSettleDao.querySettle(args);
    }
}
