package com.yeepay.g3.core.ymf.service.impl.gratuity;

import com.yeepay.g3.core.ymf.dao.gratuity.GratuityDao;
import com.yeepay.g3.core.ymf.dao.gratuity.GratuityMapper;
import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;
import com.yeepay.g3.core.ymf.service.gratuity.GratuityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 名称: GratuityService <br>
 * 描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/11/17 上午9:55
 * @version: 1.0.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class,timeout = 5)
public class GratuityServiceImpl implements GratuityService {
    @Autowired
    public GratuityMapper gratuityMapper;
    @Autowired
    public GratuityDao gratuityDao;
    @Override
    public int save(Gratuity gratuity) {

        return gratuityMapper.insert(gratuity);
    }

    @Override
    public int update(Gratuity gratuity) {
        return gratuityMapper.updateByPrimaryKey(gratuity);
    }

    @Override
    public Gratuity getById(Long id) {
        return gratuityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Gratuity> getByCustomerNumber(String customerNumber) {
        return gratuityDao.findByCustomerNumber(customerNumber);
    }
}
