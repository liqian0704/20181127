package com.yeepay.g3.core.ymf.biz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.nccashier.enumtype.CashierVersionEnum;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/29.
 */
public class TradeCashierBizServiceTest extends SoaContextAwareTest {


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
        TradeCashierRequestDTO tradeCashierRequestDTO = new TradeCashierRequestDTO() ;
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
        String tradeAdditionInfo =  om.writeValueAsString(tradeMap);
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
            tradeCashierRequestDTO.setCustomerOrderId("327580769331");//商户订单号，不能空
//        }else if(BusinessType.STANDARD.toString().equals(order.getBusinessType())){
//            tradeCashierRequestDTO.setCustomerOrderId(order.getExternalId());//商户订单号，不能空
//        }
//		tradeCashierRequestDTO.setAccessOrderId(order.getExternalId());//	订单方订单号  非必传
//		tradeCashierRequestDTO.setTradeSysNo("NCTRADE");//不能空 交易系统编码  默认有值
        tradeCashierRequestDTO.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class));//	订单方编码需要 nctrx分配
        String hmac = tradeCashierRequestDTO.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class));
        tradeCashierRequestDTO.setHmac(hmac); //验签  秘钥如何确认的  nctrx分配
        TradeCashierResponseDTO purchaseRequest = RemoteServiceFactory.getService("http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade",
                RemotingProtocol.HESSIAN, TradeCashierFacade.class).purchaseRequest(tradeCashierRequestDTO);
//        TradeCashierResponseDTO purchaseRequest = RemoteServiceFactory.getService(TradeCashierFacade.class).purchaseRequest(tradeCashierRequestDTO) ;
        jsonPrint(purchaseRequest);
    }

    @Test
    public void purchaseQuery() throws Exception {

    }

    @Test
    public void purchaseSupply() throws Exception {

    }

}