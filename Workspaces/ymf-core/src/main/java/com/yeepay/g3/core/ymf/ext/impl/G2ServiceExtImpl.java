package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.app.httpinvoke.online.dto.CustomerHmacInfoDTO;
import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.ext.G2ServiceExt;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description: 2代接口调用
 * @Author: xiaobin.liu
 * @Date: 17/1/17
 * @Time: 下午4:08
 */
@Service("g2ServiceExt")
public class G2ServiceExtImpl implements G2ServiceExt {
    private static final Logger logger = LoggerFactory.getLogger(G2ServiceExtImpl.class);

    @Override
    public CustomerBasicInfoDTO queryCustomerBasicInfo(String customerNumber) {
        logger.info("开始调用 [2代商户信息查询接口] 请求参数：{}",customerNumber);
        if (StringUtils.isBlank(customerNumber)) {
            throw new IllegalArgumentException("商户编号不能为空");
        }
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        CustomerBasicInfoDTO basicInfoDTO = facade.getCustomerBasicInfo(customerNumber);
        logger.info("开始调用 [2代商户信息查询接口] 响应参数：{}", JSONUtils.toJsonString(basicInfoDTO));
        return basicInfoDTO;
    }

    @Override
    public String queryCustomerHmacKey(String customerNumber) {
        logger.info("开始调用 [2代商户hmac查询接口] 请求参数：{}",customerNumber);
        if (StringUtils.isBlank(customerNumber)) {
            throw new IllegalArgumentException("商户编号不能为空");
        }
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        CustomerHmacInfoDTO customerHmacInfo = facade.getCustomerHmacInfo(customerNumber, null);
        String hmacKey = customerHmacInfo.getHmacKey();
        customerHmacInfo.setHmacKey("*******");//秘钥掩码打印
        logger.info("开始调用 [2代商户hmac查询接口] 响应参数：{}", JSONUtils.toJsonString(customerHmacInfo));
        return hmacKey;
    }

    public static void main(String[] args) {
        CustomerBasicInfoDTO basicInfoDTO = new CustomerBasicInfoDTO();
        basicInfoDTO.setCustomernumber("123455");
        System.out.print("print:" + JSONUtils.toJsonString(basicInfoDTO));
    }
}
