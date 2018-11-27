package com.yeepay.g3.core.ymf.biz.trade;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.ymf.junit.common.BaseAnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.YMFTradeBizFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by dongxulu on 16/12/3.
 */
public class YMFTradeBizFacadeTest extends BaseAnnotationContextAwareTest {


    @Autowired
    private YMFTradeBizFacade facade;
    @Test
    public void scanQrcodeTest(){
//        YMFTradeBizFacade facade =
////                getService(YMFTradeBizFacade.class);
//                context.getBean(YMFTradeBizFacade.class);
        ScanQrCodeRequestDTO requestDTO = new ScanQrCodeRequestDTO();
        String accessOrder = ""+System.nanoTime();
        System.out.println("###request_order "+accessOrder);
        requestDTO.setRequestID(accessOrder);
        requestDTO.setCustomerNumber("10040007800");
        requestDTO.setPaySource(PaySource.OPEN_UPOP);
        requestDTO.setProductName("商户名称展示--单元测试");
        requestDTO.setProductDesc("商品描述");
        requestDTO.setUserIp("223.223.193.194");
        requestDTO.setUserID("15040419981");
        requestDTO.setOrderAmount(new Amount(new BigDecimal(0.01)));
        System.out.println(requestDTO.getOrderAmount().getValue().multiply(new java.math.BigDecimal("10000")).longValue());
        try {
            ScanQrCodeResponseDTO responseDTO = facade.scanQrCodeDoPay(requestDTO);
            System.out.println("##### responseDTO : "+JSONUtils.toJsonString(responseDTO));
            System.out.println("##### pay url : "+responseDTO.getPayUrl());
        } catch (YmfTrxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void passivePayTest(){
//        YMFTradeBizFacade facade = context.getBean(YMFTradeBizFacade.class);
        PassivePayRequestDTO requestDTO = new PassivePayRequestDTO();
        String accessOrder = ""+System.nanoTime();
        System.out.println("###request_order "+accessOrder);
        requestDTO.setRequestID(accessOrder);
        requestDTO.setCustomerNumber("10040007800");
        requestDTO.setPaySource(PaySource.ALIPAY);
        requestDTO.setProductName("商户名称");
        requestDTO.setProductDesc("商品描述");
        requestDTO.setDeviceSn("122342434");
        requestDTO.setCode("280926700376825793");
        requestDTO.setUserIp("223.223.193.194");
        requestDTO.setOrderAmount(new Amount(new BigDecimal(0.01)));
        System.out.println(requestDTO.getOrderAmount().getValue().multiply(new java.math.BigDecimal("100")).longValue());

        try {
            PassivePayResponseDTO responseDTO = facade.passiveDoPay(requestDTO);
            System.out.println(responseDTO.getReturnMsg());
            System.out.println(responseDTO.getReturnCode());
            System.out.println(responseDTO.getExternalId());
            System.out.println(responseDTO.getCustomerNumber());
            System.out.println(responseDTO.getRequestID());
        } catch (YmfTrxException e) {
            e.printStackTrace();
        } catch (YmfException e) {
            e.printStackTrace();
        }
    }
}
