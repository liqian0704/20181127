package com.yeepay.g3.core.ymf.ext.impl;

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.ext.LaikeRiskExt;
import com.yeepay.g3.core.ymf.ext.MerchantExt;
import com.yeepay.g3.core.ymf.ext.OprYopOrderExt;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.service.qrcode.QrRelationService;
import com.yeepay.g3.core.ymf.utils.dateutils.ExpireTimeUtil;
import com.yeepay.g3.facade.laike.dto.RiskQueryRequest;
import com.yeepay.g3.facade.laike.dto.RiskQueryResponse;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.opr.dto.*;
import com.yeepay.g3.facade.opr.dto.order.ext.GoodsParamExtDTO;
import com.yeepay.g3.facade.opr.dto.order.ext.PaymentParamExtDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderReqDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderResDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopQueryOrderReqDTO;
import com.yeepay.g3.facade.opr.dto.yop.order.YopQueryOrderResDTO;
import com.yeepay.g3.facade.opr.enumtype.OrderTypeEnum;
import com.yeepay.g3.facade.opr.enumtype.ProductVersionEnum;
import com.yeepay.g3.facade.opr.facade.yop.YopOrderFacade;
import com.yeepay.g3.facade.opr.facade.yop.YopRefundFacade;
import com.yeepay.g3.facade.ymf.dto.laike.TradeRiskParamDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 大算订单接口
 * @Author: xiaobin.liu
 * @Date: 17/1/4
 * @Time: 下午4:34
 */
@Service("oprYopOrderExt")
public class OprYopOrderExtImpl implements OprYopOrderExt {
    private static final Logger logger = LoggerFactory.getLogger(OprYopOrderExtImpl.class);

    /**
     * 订单处理器业务方
     */
    private static final String bizSystemNo = "LK";
    /**
     * 营销产品码
     */
    private static final String SalesProductCode_DSBZB = "DSBZB";
    private static final String SalesProductCode_DSXTSB = "DSXTSB";

    @Autowired
    private LaikeRiskExt laikeRiskExt;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private QrRelationService qrRelationService;
    @Autowired
    private MerchantExt merchantExt;

    /**
     * 下单接口
     */
    @Override
    public YopCreateOrderResDTO createOrder(Order order, Customer customer, Payment payment) throws YmfTrxException {
        return createOrder(order, customer, payment, "1");
    }

    @Override
    public YopCreateOrderResDTO createOrder(Order order, Customer customer, Payment payment, String selfFlag)
            throws YmfTrxException {
        YopCreateOrderReqDTO reqDto = new YopCreateOrderReqDTO();
        //基础系统信息
        buildSysParam(reqDto,customer.getCustomerNumber());
        // 营销产品码
        if (ProductVersionEnum.DSXTSB.toString().equals(reqDto.getProductVersion())) {
            reqDto.setSalesProductCode(SalesProductCode_DSXTSB);
        } else {
            reqDto.setSalesProductCode(SalesProductCode_DSBZB);
        }

        //产品信息
        buildGoodsInfo(customer, reqDto,order);
        //支付拓展参数 同人时传支付扩展参数,他人不用处理
        if(Constants.ONE_SELF_FLAG.equals(selfFlag)){
            buildPaymentExtInfo(payment,customer, reqDto);
        }

        //商户订单号
        if (order.getBusinessType() == BusinessType.ORDER_PAY
                || order.getBusinessType() == BusinessType.REQUIRE_PAY) {
            reqDto.setOrderId(order.getCustomerOrderId());
        } else {
            reqDto.setOrderId(order.getExternalId());
        }
        reqDto.setTimeoutExpress((int) ExpireTimeUtil.orderExpDate(order.getBusinessType(),customer.getValidityPeriod()));
        reqDto.setAssureType(OrderTypeEnum.REALTIME.toString());
        reqDto.setRequestDate(DateUtils.toString(new Date(), DateUtils.DATE_FORMAT_DATETIME));//yyyy-MM-dd HH:mm:ss

        reqDto.setRedirectUrl(CfgConstant.getOprPageUrl());//前台回调
        reqDto.setNotifyUrl(CfgConstant.getOprNotifyUrl());//后台回调
        reqDto.setCsUrl(CfgConstant.getOprCsUrl());//清算回调

        reqDto.setOrderAmount(String.valueOf(order.getTrxAmt()));

        reqDto.setMemo(order.getCustomerOrderId());//设置对账备注 后期考虑如何使用

        //行业拓展信息 可不填
        reqDto.setIndustryParamExt("");

        //风控信息 可不填
        String riskJson = buildRistParam(customer,selfFlag);
        reqDto.setRiskParamExt(riskJson);//后期考虑如何使用


        YopOrderFacade yopOrderFacade = RemoteServiceFactory.getService(YopOrderFacade.class);
        logger.info("调用 大算 [下单接口] 请求参数:{}", JSONUtils.toJsonString(reqDto));
        YopCreateOrderResDTO resp = yopOrderFacade.createOrder(reqDto);
        logger.info("调用 大算 [下单接口] 响应参数:{}", JSONUtils.toJsonString(resp));
        return resp;
    }

    /**
     * 查单接口,
     *
     * @param customerNumber  商户编号
     * @param customerOrderId 商户请求号
     * @param uniqueOrderNo   易宝统一订单号
     */
    @Override
    public YopQueryOrderResDTO queryOrder( String customerNumber, String customerOrderId,
                                          String uniqueOrderNo) throws YmfTrxException {
        YopOrderFacade yopOrderFacade = RemoteServiceFactory.getService(YopOrderFacade.class);
        YopQueryOrderReqDTO reqDto = new YopQueryOrderReqDTO();
        buildSysParam(reqDto,customerNumber);

        reqDto.setOrderId(customerOrderId);//商户请求号
        reqDto.setUniqueOrderNo(uniqueOrderNo);//易宝统一订单号
        //大算是用商户订单号和商户编号查询的
        logger.info("调用 大算 [查单接口] 请求参数:{}", JSONUtils.toJsonString(reqDto));
        YopQueryOrderResDTO resp = yopOrderFacade.queryOrder(reqDto);
        logger.info("调用 大算 [查单接口] 响应参数:{}", JSONUtils.toJsonString(resp));
        return resp;
    }

    @Override
    public ResponseRefundDTO oprRefund(Order order) throws YmfTrxException {
        if(null == order){
            logger.error("");
            throw new YmfTrxException(TrxCode.T1009);
        }
        ResponseRefundDTO responseDTO;
        YopRefundFacade yopRefundFacade  = RemoteServiceFactory.getService(YopRefundFacade.class);
        RequestRefundDTO request = new RequestRefundDTO();
        buildSysParam(request,order.getCustomerNumber());
        request.setRefundRequestId(order.getExternalId());
        request.setParentMerchantNo(order.getCustomerNumber());
        request.setOrderId(order.getCustomerOrderId());
        request.setMerchantNo(order.getCustomerNumber());
        request.setRefundAmount(order.getTrxAmt().toString());
        logger.info(" invoke YopRefundFacade.yopRefundFacade request param :"+JSONUtils.toJsonString(request));
        responseDTO = yopRefundFacade.refund(request);
        logger.info(" invoke YopRefundFacade.yopRefundFacade response param :"+JSONUtils.toJsonString(responseDTO));
        return responseDTO;
    }

    @Override
    public QueryRefundResponseDTO oprRefundQuery(Order order) throws YmfTrxException {
        if(null == order){
            logger.error("");
            throw new YmfTrxException(TrxCode.T1009);
        }
        QueryRefundResponseDTO responseDTO ;
        QueryRefundRequestDTO requestDTO = new QueryRefundRequestDTO();
        requestDTO.setOrderId(order.getCustomerOrderId());
        requestDTO.setRefundRequestId(order.getExternalId());
        YopRefundFacade yopRefundFacade  = RemoteServiceFactory.getService(YopRefundFacade.class);
        logger.info(" invoke YopRefundFacade.queryRefund param :"+JSONUtils.toJsonString(requestDTO));
        responseDTO = yopRefundFacade.queryRefund(requestDTO);
        logger.info(" invoke YopRefundFacade.queryRefund response param :"+JSONUtils.toJsonString(responseDTO));
        return responseDTO;
    }

    /**
     * 基本请求参数信息,系统相关，公共方法
     */
    private void buildSysParam(BaseRequestDTO reqDto,String customerNumber) throws YmfTrxException {
        String parentMerchentNo = merchantExt.queryParentMerchentNo(customerNumber);
        if (!customerNumber.equals(parentMerchentNo)) {
            //如果返回的父商户不是该商户，则为子商户，对接系统商版本
            reqDto.setProductVersion(ProductVersionEnum.DSXTSB.toString());
        } else {
            reqDto.setProductVersion(ProductVersionEnum.DSBZB.toString());
        }
        reqDto.setParentMerchantNo(parentMerchentNo);
        reqDto.setMerchantNo(customerNumber);
        //基本信息
        reqDto.setBizSystemNo(bizSystemNo);
    }

    /**
     * 填充产品信息
     */
    private void buildGoodsInfo(Customer customer, YopCreateOrderReqDTO reqDto,Order order) {
        GoodsParamExtDTO goodsInfo = new GoodsParamExtDTO();
        goodsInfo.setGoodsDesc("商户单号：" + order.getCustomerOrderId());
        goodsInfo.setGoodsName(customer.getCustomerName() + "-收款");
        //商品拓展信息 json类型
        reqDto.setGoodsParamExt(JSONUtils.toJsonString(goodsInfo));
    }

    /**
     * 支付拓展参数
     */
    private void buildPaymentExtInfo(Payment payment, Customer customer, YopCreateOrderReqDTO reqDto) {
        //易码付 目前没有这些信息
        PaymentParamExtDTO paymentParamExtDto = new PaymentParamExtDTO();
        RiskQueryRequest request = new RiskQueryRequest();
        request.setMerchantNo(customer.getCustomerNumber());
        request.setExternalSystem(ExternalSystem.YMF);
        RiskQueryResponse response = laikeRiskExt.getRiskParam(request);
//            paymentParamExtDto.setCardName("");
//            paymentParamExtDto.setBankCardNo("");
        paymentParamExtDto.setIdCardNo(response.getLegalIdCard());
        paymentParamExtDto.setCardName(response.getLegalName());
        reqDto.setPaymentParamExt(JSONUtils.toJsonString(paymentParamExtDto));
    }

    /**
     * 风控信息
     * @param selfFlag  是否本人
     * @return
     */
    private String buildRistParam(Customer customer, String selfFlag) {
        RiskQueryRequest request = new RiskQueryRequest();
        TradeRiskParamDTO tradeRiskParamDTO = new TradeRiskParamDTO();
        request.setMerchantNo(customer.getCustomerNumber());
        request.setExternalSystem(ExternalSystem.YMF);
        RiskQueryResponse response = laikeRiskExt.getRiskParam(request);
        logger.info("laikeRiskExt return param:>>>>>"+JSONUtils.toJsonString(response));
//        风控企业类型传中文  我也是醉了   本人/他人 默认都是他人
        if (Constants.ONE_SELF_FLAG.equals(selfFlag)) {
            tradeRiskParamDTO.setIsTradeOneself(Constants.ONE_SELF_FLAG);
        } else {
            tradeRiskParamDTO.setIsTradeOneself("1");
        }
        if (CompanyTypeEnum.ENTER_UNION.equals(response.getCompanyType())
                || CompanyTypeEnum.ENTERPRISE.equals(response.getCompanyType())) {
            tradeRiskParamDTO.setMerchantType("企业");
        } else if (CompanyTypeEnum.INDIVIDUAL.equals(response.getCompanyType())) {
            tradeRiskParamDTO.setMerchantType("个体");
        } else if (CompanyTypeEnum.MICRO.equals(response.getCompanyType())) {
            tradeRiskParamDTO.setMerchantType("自然人");
        }
        return JSONUtils.toJsonString(tradeRiskParamDTO);
    }


}
