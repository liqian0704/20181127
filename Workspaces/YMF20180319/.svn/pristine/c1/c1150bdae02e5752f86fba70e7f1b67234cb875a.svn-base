package com.yeepay.g3.core.ymf.biz.qrcode;

import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesResponseDTO;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.laike.PurchaseQrCodeFacade;
import org.junit.Test;

/**
 * Created by dongxulu on 16/12/9.
 */
public class PurchaseQrCodeFacadeTest extends SoaContextAwareTest {

    @Test
    public void doPurchase(){
        PurchaseQrCodeFacade facade = (PurchaseQrCodeFacade)getBean("purchaseQrCodeFacadeClinet");
        QrCodePurchasesRequestDTO request = new QrCodePurchasesRequestDTO();
        request.setAgentNumber("10040011560");
        request.setCount(10);
        String requestID = ""+System.nanoTime();
        System.out.println(requestID);
        request.setRequestID(requestID);
        QrCodePurchasesResponseDTO responseDTO = facade.doPurchase(request);
        System.out.println(responseDTO.toString());
    }
    @Test
    public void getQrCodeInfo(){
        PurchaseQrCodeFacade facade = getService(PurchaseQrCodeFacade.class);
//      PurchaseQrCodeFacade facade = (PurchaseQrCodeFacade)getBean("purchaseQrCodeFacade");
        QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
        requestDTO.setQrCodeID("7XJT535G");
        QrCodeInfoResponseDTO responseDTO = facade.getQrCodeInfo(requestDTO);
        System.out.println(responseDTO.toString());
    }
    @Test
    public void bindQrCodeInfo(){
//        PurchaseQrCodeFacade facade = getService(PurchaseQrCodeFacade.class);
        PurchaseQrCodeFacade facade = (PurchaseQrCodeFacade)getBean("purchaseQrCodeFacade");
        QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
        requestDTO.setQrCodeID("80RKRDPX");
        requestDTO.setCustomerNumber("10040011560");

        QrCodeInfoResponseDTO responseDTO =  facade.bindCustomerInfo(requestDTO);
        System.out.println(responseDTO.toString());
    }
    @Test
    public void getQrCodeByCustomer(){

        PurchaseQrCodeFacade facade = (PurchaseQrCodeFacade)getBean("purchaseQrCodeFacade");
        QrCodeInfoRequestDTO requestDTO = new QrCodeInfoRequestDTO();
        requestDTO.setCustomerNumber("10040011560");
        try {
            QrCodeInfoResponseDTO responseDTO =  facade.getQrCodeByCustomer(requestDTO);
            System.out.println(responseDTO.toString());
        } catch (YmfTrxException e) {
            e.printStackTrace();
        }
    }

}
