package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 16/10/17
 * @Time: 上午10:04
 */
public interface WechatRelDao {

    /**
     * 根据商户编号和状态查询WechatRel
     * @param customerNumber        商户编号,必填
     * @param status            状态,可为null
     * @return                  查询结果列表
     */
    public List<WechatRel> findByCustomerNumber(@Param("customerNumber") String customerNumber,
                                                @Param("status") Status status) ;

}
