package com.yeepay.g3.core.ymf.facade.impl.trade;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.BalanceProductResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.CustomerRegistRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.CustomerRegistResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.IndustryType;
import com.yeepay.g3.facade.ymf.enumtype.common.AppType;
import com.yeepay.g3.facade.ymf.enumtype.common.CollectType;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PayType;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.laike.CustomerBizFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;

/**
 * Created by dongxulu on 16/12/16.
 */
public class CustomerBizFacadeTest extends SoaContextAwareTest {

    @Test
    public void registerCustomer(){

        CustomerBizFacade facade = getService(CustomerBizFacade.class);
        CustomerRegistRequestDTO requestDTO = new CustomerRegistRequestDTO();
        requestDTO.setCustomerNumber("10040030661");
        String paytype= PayType.ALIPAY_A_PAYMENT.toString()+","+PayType.ALIPAY_B_PAYMENT.toString();
        requestDTO.setPayType(paytype);
        requestDTO.setIndustryType(IndustryType.INSURANCE);
//        String appType =AppType.NORMAL AppType.INDUSTRY.toString();
        requestDTO.setAppType(AppType.INDUSTRY.toString());
        String collectType = CollectType.AppCollect.toString()+","+CollectType.H5Collect.toString();
        requestDTO.setCollectType(collectType);
        requestDTO.setBusinessCode("TESTCODE");
        requestDTO.setBusinessName("测试注册");
        requestDTO.setDocumentNO("test0001");
        try {
            CustomerRegistResponseDTO responseDTO = facade.doRegist(requestDTO);
            System.out.println(responseDTO.toString());
        } catch (YmfTrxException e) {
            System.out.println("returnCode"+e.getTrxCode().getCode()+"  errorMsg:"+e.getTrxCode().getMsg());
        }

    }
    @Test
    public void NotifyBalanceProduct(){
        BalanceProductRequestDTO balanceProductRequestDTO = new BalanceProductRequestDTO();
        balanceProductRequestDTO.setBalanceProduct(BalanceType.S0);
        balanceProductRequestDTO.setCustomerNumber("10040007800");
        CustomerBizFacade facade = getBean(CustomerBizFacade.class);
        BalanceProductResponseDTO responseDTO = facade.doCustomerBalanceProduct(balanceProductRequestDTO);
        System.out.println(JSONUtils.toJsonString(responseDTO));

    }

}
