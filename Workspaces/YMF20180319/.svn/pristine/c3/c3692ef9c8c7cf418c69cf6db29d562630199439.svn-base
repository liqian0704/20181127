package com.yeepay.g3.core.ymf.biz;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.nctrade.facade.TradeBaseFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * Title: SOA/RMI基础biz类
 * Description: 默认的服务协议是HESSIAN
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public abstract class SoaBaseBiz {

    /**
     * 根据接口名称获取soa
     * 默认协议顺序 httpinvoke, hessian
     * @param clazz 接口类
     * @param <T> 泛型
     * @return soa
     */
    protected <T> T getService(Class<T> clazz) {
        String mode = ConfigureSetting.getValue(Constants.YMF_NC_API_MODE, Constants.YMF_API_MODE_ONLINE); // 默认是生产
        if (Constants.YMF_API_MODE_QA.equals(mode) &&
                (clazz == TradeBaseFacade.class || clazz == TradeCashierFacade.class)) { // 测试用配置QA
            return getRmiService(clazz);
        }
        return RemoteServiceFactory.getService(clazz);
    }

    // 打桩方法
    private <T> T getRmiService(Class<T> clazz) {
        // NC-API
        return getService(
                ConfigureSetting.getValue(Constants.YMF_NC_API_QA_URL,
                        "http://172.21.0.83:8003/nc-api-hessian/hessian/")
                 + clazz.getSimpleName(),
                RemotingProtocol.HESSIAN, clazz);
    }

    /**
     * 根据协议和接口获取soa
     * @param protocol 协议
     * @param clazz 接口类
     * @param <T> 泛型
     * @return soa
     */
    protected <T> T getService(RemotingProtocol protocol, Class<T> clazz) {
        return RemoteServiceFactory.getService(protocol, clazz);
    }

    /**
     * 根据url地址获取rmi
     * @param serviceUrl 指定服务url
     * @param protocol 协议类型
     * @param clazz 接口类
     * @param <T> 泛型
     * @return rmi
     */
    protected <T> T getService(String serviceUrl, RemotingProtocol protocol, Class<T> clazz) {
        return RemoteServiceFactory.getService(serviceUrl, protocol, clazz);
    }

}
