package com.yeepay.g3.facade.ymf.facade;

import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

/**
 *
 * 收银台业务服务
 *
 */
public interface YMFTradeBizFacade {

    /**
     * 扫码交易方法
     * @param requestDto
     * @return
     */
    ScanQrCodeResponseDTO scanQrCodeDoPay(ScanQrCodeRequestDTO requestDto) throws Exception;


    /**
     * 被扫交易方法
     * @param requestDto
     * @return
     * @throws YmfTrxException
     * @throws YmfException
     */
    PassivePayResponseDTO passiveDoPay(PassivePayRequestDTO requestDto) throws YmfTrxException,YmfException;
}
