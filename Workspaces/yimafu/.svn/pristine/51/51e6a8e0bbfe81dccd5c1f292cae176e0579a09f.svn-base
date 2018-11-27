package com.yeepay.g3.core.ymf.service;

import java.util.List;

import com.yeepay.g3.core.ymf.entity.customer.CustomerFunction;
import com.yeepay.g3.facade.ymf.enumtype.Status;

/**
 * Created by yp-tc-m-2762 on 16/8/16.
 */
public interface CustomerFunctionService {
    /**
     * 保存商户关系
     *  add by fei.lu
     * @param function
     * @return
     */
    public int saveCustomerFunction(CustomerFunction function);

    /**
     * 修改
     *  add by fei.lu
     * @param function
     * @return
     */
    public int updateCustomerFunction(CustomerFunction function);

    /**
     * 根据商编查支付方式
     *  add by fei.lu
     * @param customerNumber
     * @return
     */
    public List<CustomerFunction> getCustomerFunctionByCustomerNumber(String customerNumber);
    public CustomerFunction getFunctionByCustomerNumberAndType(String customerNumber,String typeName);

	/**
	 * 根据商编和状态查询商户支付方式
	 * @param customerNumber
	 * @param status
	 * @return
	 */
	List<CustomerFunction> getCustomerFunctionByCustomerNumberAndStatus(String customerNumber, Status status);
}
