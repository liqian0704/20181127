package com.yeepay.g3.facade.ymf.facade.laike;

import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodeInfoResponseDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesRequestDTO;
import com.yeepay.g3.facade.ymf.dto.qrCode.QrCodePurchasesResponseDTO;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 *  采购二维码服务接口
 *
 *
 */
public interface PurchaseQrCodeFacade {
    /**
     * 二维码采购订单
     * @param requestDTO
     * @return
     */
     QrCodePurchasesResponseDTO doPurchase(QrCodePurchasesRequestDTO requestDTO);

     QrCodeInfoResponseDTO getQrCodeInfo(QrCodeInfoRequestDTO requestDTO);

     QrCodeInfoResponseDTO bindCustomerInfo(QrCodeInfoRequestDTO requestDTO);

     QrCodeInfoResponseDTO getQrCodeByCustomer(QrCodeInfoRequestDTO requestDTO) throws YmfTrxException;


}
