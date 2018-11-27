package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.support.OperateEntity;

import java.util.List;

/**
 *
 * Created by yp-tc-m-2762 on 16/8/11.
 */
public interface BusinessService {
    /**
     * 获得所有状态可用的业务方
     * add by fei.lu
     * @return
     */
    public List<Business> findAllBusiness();

    /**
     * 根据业务方标识获得业务方
     *  add by fei.lu
     * @param code
     * @return
     */
    public Business getBusinessByCode(String code);

    /**
     * 根据id查询业务方
     *  add by fei.lu
     * @param id
     * @return
     */
    public Business getBusinessById(Long id);

    /**
     * 保存业务方
     *  add by fei.lu
     * @param business
     * @return
     */
    public int saveBusiness(Business business);

    /**
     * 修改业务方
     *  add by fei.lu
     * @param business
     * @return
     */
    public int updateBusiness(Business business);

    /**
     * 保存业务方加日志
     * @param en
     * @return
     */
    public int saveBusiness(OperateEntity<Business> en);

    /**
     * 修改业务饭加日志
     * @param en
     * @return
     */
    public int updateBusiness(OperateEntity<Business> en);
}
