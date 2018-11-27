package com.yeepay.g3.facade.ymf.facade.laike;

import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.CustomerRegistRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.CustomerRegistResponseDTO;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 * Created by dongxulu on 16/12/16.
 */
public interface CustomerBizFacade {
    /**
     *
     * @param requestDTO
     * @return
     * @throws YmfTrxException
     */

    CustomerRegistResponseDTO doRegist(CustomerRegistRequestDTO requestDTO) throws YmfTrxException;

    /**
     * 结算产品开通接口
     * @param balanceProductRequestDTO
     * @return
     */
    BalanceProductResponseDTO doCustomerBalanceProduct(BalanceProductRequestDTO balanceProductRequestDTO) ;

}
