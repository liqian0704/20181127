package com.yeepay.g3.core.ymf.biz;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.TwoDimensionCode;
import com.yeepay.g3.facade.nccashier.enumtype.CashierVersionEnum;
import com.yeepay.g3.facade.nctrade.dto.*;
import com.yeepay.g3.facade.nctrade.enumtype.TradeSysNo;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/29.
 */
public class TradeCashierBizServiceTest extends AnnotationContextAwareTest {


    /**
     * {"userIp":"127.0.0.1","ristrictCardInfo":
     * {"cardNo":"null","phone":"null","hmac":"null","owner":"null","idcard":"null"},
     * "tradeSysOrderId":0,"orderExpDate":1440,
     * "tradeSysNo":"NCTRADE",
     * "accessCode":"17",
     * "innerFee":{"hmac":"null","feeBusinType":"YMF "},
     * "currency":"CNY",
     * "merchantAccount":"10040007800",
     * "guaranteeTime":1,
     * "orderAmount":1,
     * "cashierVersion":"WAP",
     * "accessBusinMode":"REAL",
     * "merchantName":"保险交易测试商户",
     * "tradeAdditionInfo":
     *      {"callbackUrl":"",
     *       "feeProductCodeMapping\":
     *          {"20":"WECHAT_C_PAYMENT",
     *           "21":"WECHAT_A_PAYMENT",
     *           "23":"NC_PAYMENT"},
     *           "oldMerchantCode":""}",
     * "calFeeMode":"INNER",
     * "identityType":"WECHAT",
     * "identityId":"o3UDHvw9DqsHUO7JMlFkg8bITbaI",
     * "outerFee":{"hmac":"null"},
     * "clearBusinType":"YMFTRADE ",
     * "orderTime":"2016-08-29 17:40:12",
     * "autoBindCard":true,
     * "productDesc":"",
     * "hmac":"e606bdb3dd35681e16b0a7993c95bad6",
     * "productName":"保险交易测试商户-缴费",
     * "industryCatalog":"60"}
     * @throws Exception
     */
    @Test
    public void purchaseRequest() throws Exception {
        TradeCashierRequestDTO tradeCashierRequestDTO = new TradeCashierRequestDTO();
        tradeCashierRequestDTO.setMerchantName("保险交易测试商户");//商户名称，用于收银台显示
        tradeCashierRequestDTO.setMerchantAccount("10040007800");
        tradeCashierRequestDTO.setIdentityId("o3UDHvw9DqsHUO7JMlFkg8bITbaI");//用户唯一标识

        tradeCashierRequestDTO.setIdentityType("WECHAT");//用户标识类型，IMEI,  MAC,  USER_ID,  EMAIL,  PHONE,  ID_CARD,  AGREEMENT_NO,  YIBAO; WECHAT 微信
        tradeCashierRequestDTO.setAccessBusinMode("REAL");//交易模式，实时交易or担保交易，默认实时，REAL,GUARANTEE
        tradeCashierRequestDTO.setCashierVersion(CashierVersionEnum.WAP.toString());//指定收银台，CashierVersionEnum
        InetAddress inet = InetAddress.getLocalHost();
        tradeCashierRequestDTO.setUserIp(inet.getHostAddress());// 用户IP，不能空，手机端如何填
//		tradeCashierRequestDTO.setUserUA(UA);//UA
        tradeCashierRequestDTO.setOrderTime(new Date());//不能为空
        tradeCashierRequestDTO.setCurrency("CNY");
        Map<String, Object> tradeMap = new HashMap<String, Object>();
        tradeMap.put("callbackUrl", "");//
        tradeMap.put("oldMerchantCode", "");
        Map<String, String> payTypeMap = new HashMap<String, String>();
        /**
         * 20：wap微信H5支付，21：微信公众号支付，22：微信主扫支付(待定)，23：银行卡WAP支付
         */
        payTypeMap.put("20", "WECHAT_C_PAYMENT");
        payTypeMap.put("21", "WECHAT_A_PAYMENT");
        payTypeMap.put("23", "NC_PAYMENT");
        tradeMap.put("feeProductCodeMapping", payTypeMap);
        ObjectMapper om = new ObjectMapper();
        String tradeAdditionInfo = om.writeValueAsString(tradeMap);
        tradeCashierRequestDTO.setTradeAdditionInfo(tradeAdditionInfo);//扩展字段 json
//		tradeCashierRequestDTO.setclearAdditionInfo 没有此属性 可以不传
        tradeCashierRequestDTO.setOrderAmount(1);//金额以分为单位
//		tradeCashierRequestDTO.setSplitAccountInfo(splitAccountInfo);//	分账信息
        tradeCashierRequestDTO.setIndustryCatalog("60");//	商品类别码 mcc码对应一键的映射码  必填
        //TODO 后期需要根据运营后台商品名称设置此属性
//        if(BusinessType.ORDER_PAY.toString().equals(order.getBusinessType())){
        tradeCashierRequestDTO.setProductName("保险交易测试商户-保险缴费");//商品名称
//        }else{
//            tradeCashierRequestDTO.setProductName(orderParam.getCustomerName() + "-缴费");//商品名称
//        }
        tradeCashierRequestDTO.setProductDesc("");//	商品描述
//		tradeCashierRequestDTO.setOrderExpDate("");// 交易订单有效期，单位分钟，默认24小时，最小值是5分钟
        tradeCashierRequestDTO.setCalFeeMode("INNER"); //	计费模式，INNER = 清算中心调用计费子系统计费，OUTER = 外部系统自计费，默认INNER
        tradeCashierRequestDTO.getInnerFee().setFeeBusinType("YMF");
        tradeCashierRequestDTO.setClearBusinType("YMFTRADE");//	清算业务方，如果不传，默认为一键支付 定制去清算中心配置
//        if(BusinessType.ORDER_PAY.toString().equals(order.getBusinessType())){
        tradeCashierRequestDTO.setCustomerOrderId("" + System.nanoTime());//商户订单号，不能空
//        }else if(BusinessType.STANDARD.toString().equals(order.getBusinessType())){
//            tradeCashierRequestDTO.setCustomerOrderId(order.getExternalId());//商户订单号，不能空
//        }
        tradeCashierRequestDTO.setAccessOrderId("" + System.nanoTime());//	订单方订单号  非必传
//		tradeCashierRequestDTO.setTradeSysNo("NCTRADE");//不能空 交易系统编码  默认有值
        tradeCashierRequestDTO.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class));//	订单方编码需要 nctrx分配
        String hmac = tradeCashierRequestDTO.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class));
        tradeCashierRequestDTO.setHmac(hmac); //验签  秘钥如何确认的  nctrx分配
//        TradeCashierResponseDTO purchaseRequest = RemoteServiceFactory.getService("http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade",
//                RemotingProtocol.HESSIAN, TradeCashierFacade.class).purchaseRequest(tradeCashierRequestDTO);
//        TradeCashierResponseDTO purchaseRequest = RemoteServiceFactory.getService(TradeCashierFacade.class).purchaseRequest(tradeCashierRequestDTO) ;
        TradeCashierResponseDTO tradeCashierResponseDTO = tradeCashierBizService.purchaseRequest(tradeCashierRequestDTO);
        jsonPrint(tradeCashierResponseDTO);
    }

    @Test
    public void purchaseQuery() throws Exception {

    }

    @Test
    public void purchaseSupply() throws Exception {

    }

    @Autowired
    TradeCashierBizService tradeCashierBizService;

    @Test
    public void scanWxCode() throws YmfTrxException, ParameterInvalidException, SystemException {
        WeChatPayRequestDTO r = new WeChatPayRequestDTO();
        r.setOrderTime(new Date());
        r.setAccessCode("17");
//        r.setMerchantAccount("10012426766");
        r.setMerchantAccount("10040007800");
        r.setMerchantName("商户名称");
        r.setProductName("test");
        r.setProductDesc("asdfsfds");
        r.setAccessOrderId(""+System.nanoTime());
        r.setIndustryCatalog("1");
        r.setUserIp("223.223.193.194");
        r.setCustomerOrderId(UUID.randomUUID().toString());
        r.setOrderAmount(1);
        r.setTradeSysNo(TradeSysNo.NCTRADE.name());
        r.setCalFeeMode("INNER");
        r.getInnerFee().setFeeProductCode("QB_WXH5");
        r.getInnerFee().setFeeBusinType("YDSYT");
        r.getInnerFee().setClearAdditionInfo("{}");
        r.setHmac(r.signMd5("1oC3L9516894J0jX2k5X7Uh505G9ER"));
        System.out.println("请求参数:" + r);
        WeChatPayResponseDTO responseDTO = tradeCashierBizService.scanWXOrderQRCodeBySelf(r);
        TwoDimensionCode handler = new TwoDimensionCode();
        String url = handler.decodeImageHexStr(responseDTO.getUrl());
        System.out.println(url);

    }
    @Test
    public void scanAliPayCode(){
        AlipayRequestDTO r = new AlipayRequestDTO();
        r.setOrderTime(new Date());
        r.setAccessCode("18");
//        r.setMerchantAccount("10012426766");
        r.setMerchantAccount("10040007800");
        r.setMerchantName("商户名称");
        r.setProductName("test");
        r.setProductDesc("asdfsfds");
        String accessOrder = ""+System.nanoTime();
        System.out.println(accessOrder);
        r.setAccessOrderId(accessOrder);
        r.setIndustryCatalog("1");
        r.setUserIp("223.223.193.194");
        String cusotmerOrderId=UUID.randomUUID().toString();
        System.out.println("cusotmerOrderId:"+cusotmerOrderId);
        r.setCustomerOrderId(cusotmerOrderId);
        r.setOrderAmount(1);
        r.setTradeSysNo(TradeSysNo.NCTRADE.name());
        r.setCalFeeMode("INNER");
        r.getInnerFee().setFeeProductCode("QB_ZFB");
        r.getInnerFee().setFeeBusinType("YDSYT");
        r.getInnerFee().setClearAdditionInfo("{}");
        r.setHmac(r.signMd5("1oC3L9516894J0jX2k5X5Uh405G9ER"));
        try {
            AlipayResponseDTO rp = tradeCashierBizService.scanALPAYOrderQRCodeBySelf(r);
            if(null!=rp){
                System.out.println(rp.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]){
        ReferenceConfig<?> referenceConfig = new ReferenceBean<Object>();
        System.out.println(referenceConfig);
    }

}