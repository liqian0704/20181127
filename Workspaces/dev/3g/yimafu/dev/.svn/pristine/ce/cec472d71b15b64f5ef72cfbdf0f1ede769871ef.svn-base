package com.yeepay.g3.core.ymf.biz.opr;

import com.yeepay.g3.core.ymf.vo.opr.OprUrlParam;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.DirectPayTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.PassivePayTypeEnum;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;

import java.io.IOException;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/2/23
 * @Time: 下午6:07
 */
public interface OprUrlBiz {
    void sign(OprUrlParam oprUrlParam) throws YmfTrxException;

    String standardCashier(String merchantNo,String token, String cardType, String userNo, String userType) throws YmfTrxException;

    String directPay(String merchantNo,String token, String cardType, String userNo, String userType,
                     DirectPayTypeEnum directPayType) throws YmfTrxException;

    /**
     * 被扫
     * @param customerNumber
     * @param token
     * @param requestDto
     * @param externalId
     * @param payType
     * @return
     */
    String passivePay(String customerNumber, String token, PassivePayRequestDTO requestDto,
                      String externalId, PassivePayTypeEnum payType) throws YmfTrxException;

    /**
     * 被扫请求
     * @param cashierUrl
     * @return
     */
    PassivePayResponseDTO passiveRequestUrl(String cashierUrl,PassivePayResponseDTO responseDto) throws IOException,YmfTrxException;
}
