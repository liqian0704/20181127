package com.yeepay.g3.core.ymf.service.impl.customer;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.app.httpinvoke.online.facade.G2ServiceInterfaceFacade;
import com.yeepay.g3.core.ymf.service.customer.CustomerBizService;
import com.yeepay.g3.facade.ymf.validator.annotations.MemCache;
import com.yeepay.g3.facade.ymf.validator.annotations.YMFCacheKey;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.stereotype.Service;

/**
 * Created by dongxulu on 17/5/1.
 */
@Service
public class CustomerBizServiceImpl implements CustomerBizService {
    private static final Logger log = LoggerFactory.getLogger(CustomerBizServiceImpl.class);
    @Override
    @MemCache
    public Long getG2CustomerID(@YMFCacheKey String customerNumber) {
        G2ServiceInterfaceFacade facade =
                RemoteServiceFactory.getService(RemotingProtocol.HTTPINVOKER, G2ServiceInterfaceFacade.class);
        CustomerBasicInfoDTO basicInfoDTO = facade.getCustomerBasicInfo(customerNumber);
        log.info("getCustomerBasicInfo return :"+ JSONUtils.toJsonString(basicInfoDTO));
        if(null == basicInfoDTO){
            return null;
        }
        return basicInfoDTO.getCustomerId();
    }
}
