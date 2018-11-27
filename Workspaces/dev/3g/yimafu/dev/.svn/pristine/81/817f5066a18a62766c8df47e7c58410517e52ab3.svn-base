package com.yeepay.g3.core.ymf.biz.opr.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.biz.opr.OprUrlBiz;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.ext.DigitalSecurityExt;
import com.yeepay.g3.core.ymf.utils.web.HttpUtil;
import com.yeepay.g3.core.ymf.vo.opr.OprUrlParam;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.DirectPayTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.PassivePayTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: 订单处理器根据Toten生成支付url工具类
 * @Author: xiaobin.liu
 * @Date: 17/2/23
 * @Time: 下午6:08
 */
@Service
public class OprUrlBizImpl implements OprUrlBiz {
    private static final Logger logger = LoggerFactory.getLogger(OprUrlBizImpl.class);


    @Autowired
    private DigitalSecurityExt digitalSecurityExt;

    /**
     * 标准版转换成签名串,并签名
     * @return
     */
    @Override
    public void sign(OprUrlParam oprUrlParam) throws YmfTrxException {
        StringBuilder sb = new StringBuilder();
        sb.append("merchantNo=").append(StringUtils.trimToEmpty(oprUrlParam.getMerchantNo()));
        sb.append("&token=").append(StringUtils.trimToEmpty(oprUrlParam.getToken()));
        sb.append("&timestamp=").append(StringUtils.trimToEmpty(oprUrlParam.getTimestamp()));
        sb.append("&directPayType=").append(StringUtils.trimToEmpty(oprUrlParam.getDirectPayType()));
        sb.append("&cardType=").append(StringUtils.trimToEmpty(oprUrlParam.getCardType()));
        sb.append("&userNo=").append(StringUtils.trimToEmpty(oprUrlParam.getUserNo()));
        sb.append("&userType=").append(StringUtils.trimToEmpty(oprUrlParam.getUserType()));
        sb.append("&bizType=").append(StringUtils.trimToEmpty(oprUrlParam.getBizType()));
        String sign = digitalSecurityExt.sign(sb.toString());
        oprUrlParam.setSign(sign);
    }

    /**
     * 标准收银台模式
     * @return  支付url
     */
    @Override
    public String standardCashier(String merchantNo,String token,String cardType,String userNo,String userType) throws YmfTrxException {
        OprUrlParam oprUrlParam = new OprUrlParam();
        oprUrlParam.setMerchantNo(merchantNo);
        oprUrlParam.setToken(token);
        oprUrlParam.setCardType(cardType);
        oprUrlParam.setUserNo(userNo);
        oprUrlParam.setUserType(userType);
        oprUrlParam.setTimestamp(timeStamp());
        sign(oprUrlParam);
        String oprStdCashierUrl = CfgConstant.getOprStdCashierUrl();
        if (StringUtils.isBlank(oprStdCashierUrl)) {
            throw YmfTrxException.SYSTEM_ERROR;
        }
        return oprStdCashierUrl + oprUrlParam.toHttpParamString();
    }

    /**
     * 直联模式
     * @return  支付url
     */
    @Override
    public String directPay(String merchantNo,String token,String cardType,String userNo,String userType,
                            DirectPayTypeEnum directPayType) throws YmfTrxException {
        OprUrlParam oprUrlParam = new OprUrlParam();
        oprUrlParam.setMerchantNo(merchantNo);
        oprUrlParam.setToken(token);
        oprUrlParam.setCardType(cardType);
        oprUrlParam.setUserNo(userNo);
        oprUrlParam.setUserType(userType);
        oprUrlParam.setTimestamp(timeStamp());
        oprUrlParam.setDirectPayType(directPayType.toString());
        sign(oprUrlParam);
        String oprStdCashierUrl = CfgConstant.getOprStdCashierUrl();
        if (StringUtils.isBlank(oprStdCashierUrl)) {
            throw YmfTrxException.SYSTEM_ERROR;
        }
        return oprStdCashierUrl + oprUrlParam.toHttpParamString();
    }

    @Override
    public String passivePay(String customerNumber, String token, PassivePayRequestDTO requestDto,
                             String externalId, PassivePayTypeEnum payType) throws YmfTrxException{
        OprUrlParam oprUrlParam = new OprUrlParam();
        oprUrlParam.setMerchantNo(customerNumber);
        oprUrlParam.setToken(token);
        oprUrlParam.setCode(requestDto.getCode());
        oprUrlParam.setCodeType(payType.toString());
        oprUrlParam.setDeviceSn(requestDto.getDeviceSn());
        oprUrlParam.setTimestamp(timeStamp());
        oprUrlParam.setStoreCode(externalId);//TODO 用户在商户的唯一标识。传值待定
        passivePaySign(oprUrlParam);
        String oprPassiveCashierUrl = CfgConstant.getOprPassiveCashierUrl();
        if (StringUtils.isBlank(oprPassiveCashierUrl)) {
            throw YmfTrxException.SYSTEM_ERROR;
        }
        return oprPassiveCashierUrl + oprUrlParam.toHttpParamString();
    }

    @Override
    public PassivePayResponseDTO passiveRequestUrl(String url,PassivePayResponseDTO responseDto) throws IOException, YmfTrxException {
        long begin = System.currentTimeMillis() ;
        String jsonString = HttpUtil.httpRequest(url,"",HttpUtil.HTTP_METHOD_GET,HttpUtil.UTF8);
        long timecost = System.currentTimeMillis() - begin;
        logger.info("调用被扫 耗时:{}ms 响应参数:{}",timecost,url);
        if(StringUtils.isEmpty(jsonString)){
            responseDto.setReturnCode(TrxCode.T1014.getCode());
            responseDto.setReturnMsg(TrxCode.T1014.getMsg());
            return responseDto;
        }
        ObjectMapper om = new ObjectMapper();
        Map<String,String> jsonObject = om.readValue(jsonString, Map.class);
        String code = jsonObject.get("code");
        String message=jsonObject.get("message");
        String sign= jsonObject.get("sign");

        logger.info("passive return param code:{} message:{} sign:{}",code,message,sign);
        if(StringUtils.isEmpty(sign)){
            responseDto.setReturnCode(TrxCode.T1032.getCode());
            responseDto.setReturnMsg(TrxCode.T1032.getMsg());
            return responseDto;
        }
        String rebacksign=rebackSign(code,message);
        if(sign.equals(rebacksign)){
            if("0000".equals(code)){
                responseDto.setReturnCode(TrxCode.T00.getCode());
                responseDto.setReturnMsg(message);
                return responseDto;
            }
        }else{
            responseDto.setReturnCode(TrxCode.T1034.getCode());
            responseDto.setReturnMsg(TrxCode.T1034.getMsg());
            return responseDto;
        }
        responseDto.setReturnCode(code);
        responseDto.setReturnMsg(message);
        return responseDto;
    }

    private String rebackSign(String code, String message) throws YmfTrxException {
        StringBuilder sb = new StringBuilder();
        sb.append("code=").append(code);
        if(StringUtils.isEmpty(message)){
            sb.append("&message=");
        }else{
            sb.append("&message=").append(message);
        }
        String sign = digitalSecurityExt.sign(sb.toString());
        logger.info(sign);
        return sign;
    }

    private void passivePaySign(OprUrlParam oprUrlParam) throws YmfTrxException{
        StringBuilder sb = new StringBuilder();
        sb.append("merchantNo=").append(StringUtils.trimToEmpty(oprUrlParam.getMerchantNo()));
        sb.append("&token=").append(StringUtils.trimToEmpty(oprUrlParam.getToken()));
        sb.append("&timestamp=").append(StringUtils.trimToEmpty(oprUrlParam.getTimestamp()));
        sb.append("&codeType=").append(StringUtils.trimToEmpty(oprUrlParam.getCodeType()));
        sb.append("&code=").append(StringUtils.trimToEmpty(oprUrlParam.getCode()));
        sb.append("&storeCode=").append(StringUtils.trimToEmpty(oprUrlParam.getStoreCode()));
        sb.append("&deviceSn=").append(StringUtils.trimToEmpty(oprUrlParam.getDeviceSn()));
        sb.append("&bizType=").append(StringUtils.trimToEmpty(oprUrlParam.getBizType()));
        String sign = digitalSecurityExt.sign(sb.toString());
        oprUrlParam.setSign(sign);
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return   当前时间戳（精确到秒） 1417792627
     */
    private String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String aa  = "     " ;
        sb.append("1234=").append(StringUtils.trimToEmpty(aa));
        System.out.println("currentTimeMillis:" + sb.toString() + "|");
    }
}
