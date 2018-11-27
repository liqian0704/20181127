package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.opr.OprUrlBiz;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import com.yeepay.g3.core.ymf.ext.OprYopOrderExt;
import com.yeepay.g3.core.ymf.ext.impl.OprYopOrderExtImpl;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.terminalno.TerminalNumberService;
import com.yeepay.g3.core.ymf.service.trade.TradeFactory;
import com.yeepay.g3.core.ymf.utils.Base62;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.TwoDimensionCode;
import com.yeepay.g3.facade.nccashier.dto.APIMerchantScanPayDTO;
import com.yeepay.g3.facade.nccashier.dto.BasicResponseDTO;
import com.yeepay.g3.facade.nccashier.enumtype.PayTypeEnum;
import com.yeepay.g3.facade.nccashier.service.APIMerchantScanPayFacade;
import com.yeepay.g3.facade.nctrade.dto.AlipayRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.AlipayResponseDTO;
import com.yeepay.g3.facade.nctrade.dto.WeChatPayRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.WeChatPayResponseDTO;
import com.yeepay.g3.facade.nctrade.enumtype.ProcessStatusEnum;
import com.yeepay.g3.facade.nctrade.enumtype.TradeSysNo;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;
import com.yeepay.g3.facade.opr.dto.yop.order.YopCreateOrderResDTO;
import com.yeepay.g3.facade.opr.enumtype.UserTypeEnum;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeRequestDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.CustomerFlag;
import com.yeepay.g3.facade.ymf.enumtype.IndustryType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.YMFTradeBizFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.log.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dongxulu on 16/12/3.
 */
@Service
public class YMFTradeBizFacadeImpl extends SoaBaseBiz implements YMFTradeBizFacade {
    private static Logger logger = LoggerFactory.getLogger(YMFTradeBizFacadeImpl.class);
    @Autowired
    TradeFactory tradeFactory;
    @Autowired
    CustomerService customerService;
    @Autowired
    TradeYMFbizService  tradeYMFbizService;
    @Autowired
    OrderService orderService;
    @Autowired
    protected OprYopOrderExt oprYopOrderExt;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private  OprUrlBiz oprUrlBiz;
    @Autowired
    private TerminalNumberService terminalNumberService;

    APIMerchantScanPayFacade passiveScanPayFacade;

    @Override
    public ScanQrCodeResponseDTO scanQrCodeDoPay(ScanQrCodeRequestDTO requestDto) throws YmfTrxException,YmfException {
        ScanQrCodeResponseDTO response = new ScanQrCodeResponseDTO();

        if(null == requestDto){
            logger.warn(" invoke scanQrCodeDoPay param is null!");
            response.setReturnCode(TrxCode.T1006.getCode());
            response.setReturnMsg(TrxCode.T1006.getMsg());
            return response;
        }
        logger.info(" begin invoke scanQrCodeDoPay param: "+ JSONUtils.toJsonString(requestDto));
        Order order = initOrder(requestDto);
        Payment payment = initPayment(requestDto);
        //统一配置，大算下单开关，默认调用收银台下单
        Customer customer = customerService.findByCustomerNumber(requestDto.getCustomerNumber());
        if(null == customer){
            logger.warn(" findByCustomerNumber return null! customerNumber :  "+requestDto.getCustomerNumber());
            response.setReturnCode(TrxCode.T1023.getCode());
            response.setReturnMsg(TrxCode.T1023.getMsg());
            return response;
        }
        if(BalanceType.S0.equals(customer.getBalanceProduct())){
            order.setBalanceType(BalanceType.S0);
        }
        tradeYMFbizService.createOrderAndPayment(order,payment);
        if (CustomerFlag.OPR_AFTER.equals(customer.getCustomerFlag())
                || TradeSystemEnum.OPR.equals(customer.getTradeSystem()))  {
            return tradeFromOPR(customer,order,payment,requestDto);
        }
        if(PaySource.ALIPAY.equals(requestDto.getPaySource())){
            AlipayRequestDTO dto = initAliPayRequestParam(requestDto,order);
            try {
                    AlipayResponseDTO responseDto = tradeFactory.getTradeCashierService().scanALPAYOrderQRCodeBySelf(dto);
                    ProcessStatusEnum resultStatus = responseDto.getProcessStatusEnum();
                if(ProcessStatusEnum.SUCCESS.equals(resultStatus)){
                    response = transferAliParam(responseDto);
                }else{
                    response.setReturnCode(responseDto.getReturnCode());
                    response.setReturnMsg(responseDto.getReturnMsg());
                    return response;
                }
            } catch (ParameterInvalidException e) {
                logger.error("scanALPAYOrderQRCodeBySelf ParameterInvalidException",e);
                response.setReturnCode(TrxCode.T1011.getCode());
                response.setReturnMsg(TrxCode.T1011.getMsg());
                return response;
            } catch (SystemException e) {
                logger.error("scanALPAYOrderQRCodeBySelf SystemException",e);
                response.setReturnCode(TrxCode.T1000.getCode());
                response.setReturnMsg(TrxCode.T1000.getMsg());
                return response;
            }
        }else if(PaySource.WECHAT.equals(requestDto.getPaySource())){
            try {
                WeChatPayRequestDTO dto = initWeChatRequestParam(requestDto,order);
                WeChatPayResponseDTO responseDTO = tradeFactory.getTradeCashierService().scanWXOrderQRCodeBySelf(dto);
                ProcessStatusEnum resultStatus = responseDTO.getProcessStatusEnum();
                if(ProcessStatusEnum.SUCCESS.equals(resultStatus)){
                    response = transferWeChatParam(responseDTO);
                }else{
                    response.setReturnCode(responseDTO.getReturnCode());
                    response.setReturnMsg(responseDTO.getReturnMsg());
                    return response;
                }
            } catch (ParameterInvalidException e) {
                logger.error("scanWXOrderQRCodeBySelf ParameterInvalidException",e);
                response.setReturnCode(TrxCode.T1011.getCode());
                response.setReturnMsg(TrxCode.T1011.getMsg());
                return response;
            } catch (SystemException e) {
                logger.error("scanWXOrderQRCodeBySelf SystemException",e);
                response.setReturnCode(TrxCode.T1000.getCode());
                response.setReturnMsg(TrxCode.T1000.getMsg());
                return response;
            }
        }else{
            logger.error("requestDto.getPaySource() error ! paySource:"+requestDto.getPaySource());
            response.setReturnCode(TrxCode.T1016.getCode());
            response.setReturnMsg(TrxCode.T1016.getMsg());
            return response;
        }
        return response;
    }

    @Override
    public PassivePayResponseDTO passiveDoPay(PassivePayRequestDTO requestDto) throws YmfTrxException, YmfException {
        PassivePayResponseDTO responseDto=new PassivePayResponseDTO();
        if(null == requestDto){
            logger.warn(" invoke passiveDoPay param is null!");
            responseDto.setReturnCode(TrxCode.T1006.getCode());
            responseDto.setReturnMsg(TrxCode.T1006.getMsg());
            return responseDto;
        }
        logger.warn(" begin invoke passiveDoPay param: "+requestDto.toString());
        Order order = initPassiveOrder(requestDto);
        Payment payment = initPassivePayment(requestDto);
        tradeYMFbizService.createOrderAndPayment(order,payment);
        return tradePassiveOPR(order,payment,requestDto);
    }

    private PassivePayResponseDTO tradePassiveOPR(Order order, Payment payment, PassivePayRequestDTO requestDto)
            throws YmfTrxException {
        PassivePayResponseDTO responseDto = new PassivePayResponseDTO();
        responseDto.setExternalId(order.getExternalId());
        responseDto.setCustomerNumber(order.getCustomerNumber());
        responseDto.setRequestID(order.getCustomerOrderId());
        Customer customer = customerService.findByCustomerNumber(order.getCustomerNumber());
        YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment);

        if (OprYopOrderExtImpl.opr_ok.equals(resp.getCode())) {
            try {
                payment.setYeepayOrderId(resp.getUniqueOrderNo());
                String token = resp.getToken();
                payment.setPayUrl(token);//存token
                paymentService.update(payment);
//                PassivePayTypeEnum payType = null;
//                if(PaySource.WECHAT.equals(requestDto.getPaySource())){
//                    payType=PassivePayTypeEnum.WX;
//                }else if(PaySource.ALIPAY.equals(requestDto.getPaySource())){
//                    payType = PassivePayTypeEnum.ZFB;
//                }else if(PaySource.UPOP.equals(requestDto.getPaySource())){
//                    payType = PassivePayTypeEnum.UPOP;
//                }
                //支付链接，收银台订单号回写

                if(StringUtils.isEmpty(token) || StringUtils.isEmpty(requestDto.getCode())
                        || StringUtils.isEmpty(requestDto.getDeviceSn()) ||
                        StringUtils.isEmpty(order.getExternalId()) || StringUtils.isEmpty(requestDto.getCustomerNumber())
                        || StringUtils.isEmpty(String.valueOf(requestDto.getPaySource())) || StringUtils.isEmpty(requestDto.getUserIp())){
                    responseDto.setReturnCode(TrxCode.T1000.getCode());
                    responseDto.setReturnMsg("参数不完整!");
                    return responseDto;
                }
                passiveScanPayFacade = getService(APIMerchantScanPayFacade.class);
                APIMerchantScanPayDTO passiveScanPayDTO=new APIMerchantScanPayDTO();
                passiveScanPayDTO.setCode(requestDto.getCode());
                if(PaySource.UPOP.equals(requestDto.getPaySource())){
                    TerminalNumber terminalNumber=terminalNumberService.findTerminalNOByCustomerNumber(customer.getCustomerNumber());
                    if(null == terminalNumber){
                        responseDto.setReturnCode(TrxCode.T1038.getCode());
                        responseDto.setReturnCode(TrxCode.T1038.getMsg());
                    }
                    passiveScanPayDTO.setDeviceSn(terminalNumber.getTerminalNumber());
                }else{
                    passiveScanPayDTO.setDeviceSn(requestDto.getDeviceSn());
                }
                passiveScanPayDTO.setStoreCode(order.getCustomerNumber());
                passiveScanPayDTO.setMerchantNo(requestDto.getCustomerNumber());
                passiveScanPayDTO.setToken(token);
                passiveScanPayDTO.setUserIp(requestDto.getUserIp());
                passiveScanPayDTO.setBizType("LAIKE");
                if(PaySource.WECHAT.equals(requestDto.getPaySource())){
                    passiveScanPayDTO.setCodeType(PayTypeEnum.WECHAT_SCAN.toString());
                }else if(PaySource.ALIPAY.equals(requestDto.getPaySource())){
                    passiveScanPayDTO.setCodeType(PayTypeEnum.ALIPAY_SCAN.toString());
                }else if(PaySource.UPOP.equals(requestDto.getPaySource())){
                    passiveScanPayDTO.setCodeType(PayTypeEnum.UPOP_PASSIVE_SCAN.toString());
                }

                BasicResponseDTO basicResponseDTO=passiveScanPayFacade.pay(passiveScanPayDTO);
                String returnCode =basicResponseDTO.getReturnCode();
                String returnMsg  =basicResponseDTO.getReturnMsg();
                String status = String.valueOf(basicResponseDTO.getProcessStatusEnum());
                logger.info("returnCode:"+returnCode+"returnMsg:"+returnMsg+"status:"+status);
                if("SUCCESS".equals(status)){
                    payment.setPayStatus(PaymentStatus.PROCESS);
                    payment.setPayRequestTime(new Date());
                    order.setOrderStatus(OrderStatus.PROCESS);
                    tradeYMFbizService.updatePayUrlAndYeePayOrderId(order, payment);
                    responseDto.setReturnCode(TrxCode.T00.getCode());
                }else{
                    responseDto.setReturnCode(returnCode);
                }
                responseDto.setReturnMsg(returnMsg);

//                String str = oprUrlBiz.passivePay(customer.getCustomerNumber(),token, requestDto, order.getExternalId(),payType);
//                responseDto=oprUrlBiz.passiveRequestUrl(str,responseDto);
//                if("00".equals(responseDto.getReturnCode())){
//                    payment.setPayStatus(PaymentStatus.PROCESS);
//                    payment.setPayRequestTime(new Date());
//                    order.setOrderStatus(OrderStatus.PROCESS);
//
//                    tradeYMFbizService.updatePayUrlAndYeePayOrderId(order, payment);
//                }

            } catch (YmfException e) {
                logger.error("updatePayUrlAndYeePayOrderId SystemException requestID:"+order.getCustomerOrderId(),e);
                responseDto.setReturnCode(TrxCode.T1000.getCode());
                responseDto.setReturnMsg(TrxCode.T1000.getMsg());
                return responseDto;
            } catch (Exception e) {
                logger.error("request passive error",e);
                responseDto.setReturnCode(TrxCode.T1000.getCode());
                responseDto.setReturnMsg(TrxCode.T1000.getMsg());
                return responseDto;
            }

        }else{
            logger.error("OPREXCEPTION "+resp.getMessage()+" code:"+ resp.getCode());
            responseDto.setReturnCode(TrxCode.T1031.getCode());
            responseDto.setReturnMsg(resp.getMessage());
            return responseDto;
        }
        return responseDto;
    }

    private Payment initPassivePayment(PassivePayRequestDTO requestDto) {
        Payment payment= new Payment() ;
        payment.setCustomerNumber(requestDto.getCustomerNumber());
        payment.setCustomerOrderId(requestDto.getRequestID());
        payment.setCreateTime(new Date());
        payment.setPayStatus(PaymentStatus.INIT);
        payment.setPaySource(requestDto.getPaySource());
        payment.setSettleStatus(SettleStatus.INIT);
        payment.setTrxAmt(requestDto.getOrderAmount().getValue());
        payment.setTrxType(TrxType.PURCHASE);
        payment.setPaySource(requestDto.getPaySource());
        return payment;
    }

    private Order initPassiveOrder(PassivePayRequestDTO requestDto) {
        Order order = new Order();
        order.setCustomerNumber(requestDto.getCustomerNumber());
        order.setCustomerOrderId(requestDto.getRequestID());
        order.setSanCode(requestDto.getDeviceSn());
        order.setCreateTime(new Date());
        order.setOrderStatus(OrderStatus.INIT);
        order.setTradeSystem(TradeSystemEnum.OPR);
        if(PaySource.WECHAT.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.PASSIVEWECHAT_PAY);
        }else if(PaySource.ALIPAY.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.PASSIVEAL_PAY);
        }else if(PaySource.UPOP.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.PASSIVELY_PAY);
        }
        BigDecimal amt = requestDto.getOrderAmount().getValue();
        order.setTrxAmt(amt);
        return order;
    }

    private ScanQrCodeResponseDTO tradeFromOPR(Customer customer,Order order,Payment payment,ScanQrCodeRequestDTO requestDTO)
            throws YmfTrxException {
        logger.info(" begin  opr trade processing!  ");
        ScanQrCodeResponseDTO responseDTO = new ScanQrCodeResponseDTO();
//        0:本人  1:他人  此处做区分
        String selfFlag =  requestDTO.getSelfFlag();
        YopCreateOrderResDTO resp = oprYopOrderExt.createOrder(order, customer, payment,selfFlag);
        String cashierUrl = null;
        String token = null;
        if (OprYopOrderExtImpl.opr_ok.equals(resp.getCode())) {
            token = resp.getToken();
            DirectPayTypeEnum payType = null;
            if(PaySource.WECHAT.equals(requestDTO.getPaySource())){
                payType=DirectPayTypeEnum.WECHAT;
            }else if(PaySource.ALIPAY.equals(requestDTO.getPaySource())){
                payType = DirectPayTypeEnum.ALIPAY;
            }else if(PaySource.NCPAY.equals(requestDTO.getPaySource())){
                payType = DirectPayTypeEnum.YJZF;
            }else if(PaySource.CFL.equals(requestDTO.getPaySource())){
                payType = DirectPayTypeEnum.CFL;
            }
            //支付链接，收银台订单号回写
            try {
                //@TODO 如果没有用户表示传过来,直接使用订单参考号,后期绑卡id非必填时,可去掉
                String userId =  requestDTO.getUserID();
                if(org.apache.commons.lang3.StringUtils.isEmpty(userId)){
                    userId = order.getExternalId();
                }
//                如果没有传具体支付方式,直接跳转标准收银台
                if(null == requestDTO.getPaySource()){
                    if (CfgConstant.isOpenShortLink()) {
                        //标准收银台，使用短链跳转
                        cashierUrl = CfgConstant.gePayHostUrl() + "/link/" + Base62._10_62(payment.getId());
                    } else {
                        //跳转标准收银台
                        cashierUrl = oprUrlBiz.standardCashier(payment.getCustomerNumber(),token,
                                "", "", "");
                    }
                }else{
                    cashierUrl = oprUrlBiz.directPay(customer.getCustomerNumber(),token,
                            "",userId , UserTypeEnum.USER_ID.toString(),payType);
                }
                responseDTO.setPayUrl(cashierUrl);
                payment.setPayUrl(token);//存token
                payment.setPayStatus(PaymentStatus.PROCESS);
                payment.setYeepayOrderId(resp.getUniqueOrderNo());
                payment.setPayRequestTime(new Date());
                order.setOrderStatus(OrderStatus.PROCESS);
                order.setTradeSystem(TradeSystemEnum.OPR);
                tradeYMFbizService.updateOrderAndPayment(order, payment);
                responseDTO.setReturnCode(TrxCode.T00.getCode());
                responseDTO.setTrxCode(TrxCode.T00);
                responseDTO.setExternalID(order.getExternalId());
                responseDTO.setCustomerNumber(order.getCustomerNumber());
                responseDTO.setRequestID(order.getCustomerOrderId());
            } catch (YmfException e) {
                logger.error("updatePayUrlAndYeePayOrderId YmfException requestID:"+order.getCustomerOrderId(),e);
                responseDTO.setReturnCode(TrxCode.T1000.getCode());
                responseDTO.setReturnMsg(TrxCode.T1000.getMsg());
                return responseDTO;
            } catch (YmfTrxException e) {
                logger.error(" YmfTrxException requestID:"+order.getCustomerOrderId(),e);
                responseDTO.setReturnCode(e.getTrxCode().getCode());
                responseDTO.setReturnMsg(e.getTrxCode().getMsg());
                return responseDTO;
            }
        }else{
            logger.error("oprYopOrderExt.createOrder failed requestID:"+order.getCustomerOrderId());
            responseDTO.setReturnCode(TrxCode.T1031.getCode());
            responseDTO.setReturnMsg(TrxCode.T1031.getMsg());
            return responseDTO;
        }

        return responseDTO;
    }

    private ScanQrCodeResponseDTO transferAliParam(AlipayResponseDTO response){
        ScanQrCodeResponseDTO scanResponse = new ScanQrCodeResponseDTO();
        String payUrl =response.getUrl();
        scanResponse.setPayUrl(payUrl);
        scanResponse.setReturnCode(TrxCode.T00.getCode());
        scanResponse.setExternalID(response.getAccessOrderId());
        scanResponse.setCustomerNumber(response.getMerchantAccount());
        scanResponse.setRequestID(response.getCustomerOrderId());
        return scanResponse;
    }

    private ScanQrCodeResponseDTO transferWeChatParam(WeChatPayResponseDTO response){
        ScanQrCodeResponseDTO scanResponse = new ScanQrCodeResponseDTO();
        TwoDimensionCode handler = new TwoDimensionCode();
        String payUrl = handler.decodeImageHexStr(response.getUrl());
        scanResponse.setPayUrl(payUrl);
        scanResponse.setReturnCode(TrxCode.T00.getCode());
        scanResponse.setExternalID(response.getAccessOrderId());
        scanResponse.setCustomerNumber(response.getMerchantAccount());
        scanResponse.setRequestID(response.getCustomerOrderId());
        return scanResponse;
    }


    private Order initOrder(ScanQrCodeRequestDTO requestDto){
        Order order = new Order();
        order.setCustomerNumber(requestDto.getCustomerNumber());
        order.setCustomerOrderId(requestDto.getRequestID());
        order.setCreateTime(new Date());
        if(PaySource.ALIPAY.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.SCANALI_QRCODE_PAY);
        }else if(PaySource.WECHAT.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.SCANWECHAT_QRCODE_PAY);
        }else if(PaySource.NCPAY.equals(requestDto.getPaySource())){
            order.setBusinessType(BusinessType.SCANWECHAT_QRCODE_PAY);
        }else{
//            如果不传任何支付渠道,默认使用标准版
            order.setBusinessType(BusinessType.STANDARD);
        }
        order.setOrderStatus(OrderStatus.INIT);
        BigDecimal amt = requestDto.getOrderAmount().getValue();
        order.setTrxAmt(amt);
        return order;
    }

    private Payment initPayment(ScanQrCodeRequestDTO requestDto){
        Payment payment= new Payment() ;
        payment.setCustomerNumber(requestDto.getCustomerNumber());
        payment.setCustomerOrderId(requestDto.getRequestID());
        payment.setCreateTime(new Date());
        payment.setPayStatus(PaymentStatus.INIT);
        payment.setPaySource(requestDto.getPaySource());
        payment.setSettleStatus(SettleStatus.INIT);
        payment.setTrxAmt(requestDto.getOrderAmount().getValue());
        payment.setTrxType(TrxType.PURCHASE);
        payment.setPaySource(requestDto.getPaySource());
        return payment;
    }

    private WeChatPayRequestDTO initWeChatRequestParam(ScanQrCodeRequestDTO requestDto,Order order) throws YmfTrxException{

        String customerNumber = requestDto.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null==customer|| Status.INACTIVE.equals(customer.getStatus())){
            throw new YmfTrxException(TrxCode.T1012);

        }
        WeChatPayRequestDTO requestDTO = new WeChatPayRequestDTO();
        requestDTO.setOrderTime(new Date());
        requestDTO.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class));//	订单方编码需要 nctrx分配
        requestDTO.setMerchantAccount(customerNumber);
        requestDTO.setMerchantName(customer.getCustomerName());
        requestDTO.setProductName(requestDto.getProductName());
        requestDTO.setProductDesc(requestDto.getProductDesc());
        //使用系统参考号,ymf唯一
        requestDTO.setAccessOrderId(order.getExternalId());
        String industryCode = IndustryType.getIndustryCode(customer.getIndustryType()) ;
        if (StringUtils.isNotBlank(industryCode)) {
            requestDTO.setIndustryCatalog(industryCode);//TODO	商品类别码 mcc码对应一键的映射码  必填
        } else {
            throw new YmfTrxException(TrxCode.T1013) ;
        }
        requestDTO.setUserIp(requestDto.getUserIp());
        if(StringUtil.isEmpty(requestDto.getRequestID())){
            requestDTO.setCustomerOrderId(order.getExternalId());
        }else{
            requestDTO.setCustomerOrderId(requestDto.getRequestID());
        }
        requestDTO.setOrderAmount(requestDto.getOrderAmount().getValue().multiply(new java.math.BigDecimal("100")).longValue());
        requestDTO.setTradeSysNo(TradeSysNo.NCTRADE.name());
        //此处默认内部计费,全部使用三代计费
        requestDTO.setCalFeeMode("INNER");
        //计费产品   先使用公众号支付计费
//        String feeProductCode ;
//        try {
//            feeProductCode =  ConfigureSetting.getValue(Constants.WECHAT_ZS_FEECODE, String.class);
//        } catch (Exception e) {
//            logger.error("统一配置异常 健:WECHAT_ZS_FEECODE 未获取到值",e);
//            throw new YmfTrxException(TrxCode.T1014) ;
//        }
        requestDTO.getInnerFee().setFeeProductCode("WECHAT_D_PAYMENT");
        //计费业务方
        requestDTO.getInnerFee().setFeeBusinType(Constants.YMF);
//        requestDTO.getInnerFee().setClearAdditionInfo("{}");
        requestDTO.setHmac(requestDTO.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class)));

        return requestDTO;
    }

    private AlipayRequestDTO  initAliPayRequestParam(ScanQrCodeRequestDTO requestDto, Order order) throws YmfTrxException{
        String customerNumber = requestDto.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if(null==customer|| Status.INACTIVE.equals(customer.getStatus())){
            throw new YmfTrxException(TrxCode.T1012);
        }
        AlipayRequestDTO requestDTO = new AlipayRequestDTO();
        requestDTO.setOrderTime(new Date());
        requestDTO.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class));//	订单方编码需要 nctrx分配
        requestDTO.setMerchantAccount(customerNumber);
        requestDTO.setMerchantName(customer.getCustomerName());
        requestDTO.setProductName(requestDto.getProductName());
        requestDTO.setProductDesc(requestDto.getProductDesc());
        //使用系统参考号,ymf唯一
        requestDTO.setAccessOrderId(order.getExternalId());
		      
        String industryCode = IndustryType.getIndustryCode(customer.getIndustryType()) ;
        if (StringUtils.isNotBlank(industryCode)) {
            requestDTO.setIndustryCatalog(industryCode);//TODO	商品类别码 mcc码对应一键的映射码  必填
        } else {
            throw new YmfTrxException(TrxCode.T1013) ;
        }
        requestDTO.setUserIp(requestDto.getUserIp());
        if(StringUtil.isEmpty(requestDto.getRequestID())){
            requestDTO.setCustomerOrderId(order.getExternalId());
        }else{
            requestDTO.setCustomerOrderId(requestDto.getRequestID());
        }
        requestDTO.setOrderAmount(requestDto.getOrderAmount().getValue().multiply(new java.math.BigDecimal("100")).longValue());
        requestDTO.setTradeSysNo(TradeSysNo.NCTRADE.name());
        //此处默认内部计费,全部使用三代计费
        requestDTO.setCalFeeMode("INNER");
        //计费产品   支付宝计费
        String feeProductCode;
//        try {
//            feeProductCode =  ConfigureSetting.getValue(Constants.ALIPAY_ZS_FEECODE, String.class);
//        } catch (Exception e) {
//            logger.error("统一配置异常 健:ALIPAY_ZS_FEECODE 未获取到值",e);
//            throw new YmfTrxException(TrxCode.T1014) ;
//        }
        requestDTO.getInnerFee().setFeeProductCode("ALIPAY_B_PAYMENT");
        //计费业务方
        requestDTO.getInnerFee().setFeeBusinType(Constants.YMF);
//        requestDTO.getInnerFee().setClearAdditionInfo("{}");
        requestDTO.setHmac(requestDTO.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class)));

        return requestDTO;
    }

}
