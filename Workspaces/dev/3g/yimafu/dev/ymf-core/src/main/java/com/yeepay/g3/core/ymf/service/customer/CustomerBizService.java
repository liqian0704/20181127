package com.yeepay.g3.core.ymf.service.customer;

/**
 * 提供商户业务服务
 */
public interface CustomerBizService {
    /**
     * 二代获取customeriD
     * @param customerNumber
     * @return
     */
    Long getG2CustomerID( String customerNumber);
}
