package com.yeepay.g3.ymf.hessian.controller;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.biz.liker.LikerBizService;
import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.business.Business;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.laike.dto.CustomerSourceResponse;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.facade.AllianceBossFacade;
import com.yeepay.g3.facade.ymf.dto.opr.OprPayCallbackRequestDTO;
import com.yeepay.g3.facade.ymf.dto.opr.OprSettleCallbackRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.common.AppType;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.log.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

import static com.yeepay.g3.core.ymf.utils.dateutils.DateUtil.getStrToDate;

/**
 * 订单处理器公网回调处理器
 * Created by dongxulu on 17/1/19.
 */
@Controller
@RequestMapping("/opr")
public class OprCallbackController  extends SoaBaseBiz{
    private static Logger logger = LoggerFactory.getLogger(OprCallbackController.class);
    private static final String FALSE = "FALSE";
    private static final String SUCCESS = "SUCCESS";
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TradeYMFbizService tradeYMFbizService;
    @Autowired
    private LikerBizService likerBizService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CodBiz codBiz;
    @Autowired
    private RemittanceService remittanceService;
    @Autowired
    private InvokeRemoteRemitService invokeRemoteRemitService;

    /**
     * 支付回调
     *
     * @param requestDTO 回调请求
     * @return
     */
    @RequestMapping("/payCallback")
    @ResponseBody
    public String oprPayCallback(OprPayCallbackRequestDTO requestDTO) {
        if (null == requestDTO) {
            logger.error("oprPayCallback requestParam is null!");
            return FALSE;
        }
        logger.info("####oprPayCallback requestParm:" + JSONUtils.toJsonString(requestDTO));
        String status = requestDTO.getStatus();
        if ("SUCCESS".equals(status)) {
            String customerNo = requestDTO.getMerchantNo();
            Customer customer = customerService.findByCustomerNumber(customerNo);
            String yeepayOrderId = requestDTO.getUniqueOrderNo();
            Payment payment;
            try {
                payment = paymentService.findByYeepayOrderId(customerNo, yeepayOrderId);
            } catch (Exception e) {
                logger.error(" findByYeepayOrderId exception yeepayOrderId:" + yeepayOrderId, e);
                return FALSE;
            }
            if (null == payment) {
                logger.error(" no payment yeepayOrderId:" + yeepayOrderId);
                return FALSE;
            }
            Order order = orderService.findById(payment.getOrderId());
            if(null == order){
                logger.error(" no result exception yeepayOrderId:" + yeepayOrderId);
                return FALSE;
            }
            if(OrderStatus.SUCCESS.equals(order.getOrderStatus())){
                logger.info(" order has been SUCESS! no more notify again:" + yeepayOrderId);
                return SUCCESS;
            }
            //查询此商户归属,是标准代理还是联盟系统
            AllianceBossFacade allianceBossFacade = getService(AllianceBossFacade.class);
            CustomerSourceResponse response = allianceBossFacade.queryCustomerSource(customerNo);
            if(ResponseStatus.SUCCESS.equals(response.getStatus())){
                AppSourceEnum appType = response.getAppSourceEnum();
                if(AppSourceEnum.LIKER.equals(appType)){
                    order.setPayConfirm(AppType.AGENT.toString());
                }else if(AppSourceEnum.ALLIANCE.equals(appType)){
                    order.setPayConfirm(AppType.ALLIANCE.toString());
                }
            }
            order.setCompleteTime(new Date());
            order.setOrderStatus(OrderStatus.SUCCESS);
            BigDecimal realAmount;
            if (null != requestDTO.getPayAmount()) {
                realAmount = BigDecimal.valueOf(Double.valueOf(requestDTO.getPayAmount()));
                order.setRealAmt(realAmount);
                payment.setRealAmt(realAmount);
            }
            payment.setBankOrderId(requestDTO.getBankOrderId());
            //获取支付成功时间 写入payment
            if (!StringUtil.isEmpty(requestDTO.getPaySuccessDate())) {
                Date payTime = getStrToDate(requestDTO.getPaySuccessDate(), Constants.OPR_DATE_TEMPLATE);
                payment.setPayTime(payTime);
                payment.setChannelPayTime(payTime);
            } else {
                payment.setPayTime(new Date());
                payment.setChannelPayTime(new Date());
            }
            payment.setPayStatus(PaymentStatus.SUCCESS);
            //微信WECHAT,支付宝ALIPAY,分期支付CFL_BT
            String cardType = requestDTO.getCardType();
            try {
                payment.setPaySource(PaySource.valueOf(requestDTO.getPlatformType()));
            } catch (IllegalArgumentException e) {
                logger.error("### IllegalArgumentException requestDTO.getPlatformType:"+requestDTO.getPlatformType(),e);
            }
            payment.setOutOrderId(requestDTO.getBankTrxId());
            payment.setBankType(requestDTO.getBankId());
            if (StringUtils.isNotEmpty(cardType)) {
                try {
                    payment.setCardType(CardType.getCardType(cardType));
                } catch (Exception e) {
//                    此处抓一下异常 打印日志 不做处理 可能会因为类型不一样导致异常
                    logger.error("setCardType exception:cardType--->:" + cardType);
                }
            }
            try {
                tradeYMFbizService.updateOrderAndPayment(order, payment);
//                查询商户业务方  如果是laike 通知给app服务端,非来客 直接通知行业应用
                Business business = businessService.getBusinessById(customer.getBusinessId());
                if (Status.ACTIVE.equals(business.getStatus()) && Constants.LAIKE.equals(business.getBizCode())) {
                    //通知来客支付结果
                    likerBizService.doNotify(payment, customer, order);
                } else {
                    // 行业版需要通知,改为全部交易都通知COD
                    codBiz.orderPayNotify(payment, customer, order);
                    logger.info("------ Cod通知成功,externalId:" + order.getExternalId() + ",customerOrderId:"
                            + order.getCustomerOrderId());
                }
                logger.info("oprPayCallback success!");
                return SUCCESS;
            } catch (YmfException e) {
                logger.error("completeOrderAndPayment exception yeepayOrderId :" + yeepayOrderId, e);
            } catch (Throwable throwable) {
                logger.error(" Throwable yeepayOrderId :" + yeepayOrderId, throwable);
            }
        }
        return FALSE;
    }

    /**
     * 结算回调
     *
     * @param requestDTO 回调请求
     * @return
     */
    @RequestMapping("/settleCallback")
    @ResponseBody
    public String oprSettleCallback(OprSettleCallbackRequestDTO requestDTO) {
        if (null == requestDTO) {
            logger.error("oprSettleCallback requestParam is null!");
            return FALSE;
        }
        Order order = null;
        logger.info("####oprSettleCallback requestParm:" + JSONUtils.toJsonString(requestDTO));
        String status = requestDTO.getStatus();
        logger.info("###oprSettleCallback status: "+status);
        if ("SUCCESS".equals(status)) {
            String customerNo = requestDTO.getMerchantNo();
            String yeepayOrderId = requestDTO.getUniqueOrderNo();
            Payment payment;
            String merchantFee = requestDTO.getMerchantFee();
            try {
                payment = paymentService.findByYeepayOrderId(customerNo, yeepayOrderId);
            } catch (Exception e) {
                logger.error(" oprSettleCallback findByYeepayOrderId exception yeepayOrderId: " + yeepayOrderId, e);
                return FALSE;
            }
            if (null == payment) {
                logger.error(" no payment yeepayOrderId:" + yeepayOrderId);
                return FALSE;
            }
            try {
                order = orderService.findById(payment.getOrderId());
                if(null == order){
                    logger.error(" no order exsist yeepayOrderId:" + yeepayOrderId);
                    return FALSE;
                }
                //是否允许S0 条件限制 : S0订单 && ( (业务类型为 SCAN_PAY && PaySource 非 NCPAY) || 业务类型为 NCPAY_SELF )
                Boolean isS0 =  BalanceType.S0.equals(order.getBalanceType())
                        && ( (BusinessType.SCAN_PAY.equals(order.getBusinessType()) && !PaySource.NCPAY.equals(payment.getPaySource()) )
                        || BusinessType.NCPAY_SELF.equals(order.getBusinessType()));
                if (SettleStatus.SETTLED.equals(payment.getSettleStatus())) {
                    logger.info("### order already SETTLED yeepayOrderId:" + yeepayOrderId);
//                如果已经结算成功,则不再进行结算状态更新,但是若没有进行打款,则需要进行订单打款
                    if (isS0) {
                        Remittance remittance = remittanceService.findByYeepayOrderId(yeepayOrderId);
                        if (null == remittance) {
                            logger.info("### order dont  remit,try agin! ");
                            invokeRemoteRemitService.doRemittance(order, payment);
                        } else {
                            logger.info("### order already remitted ");
                        }
                    }
                    return SUCCESS;
                }

                BigDecimal fee = BigDecimal.valueOf(Double.valueOf(merchantFee));
                order.setFee(fee);
                payment.setFee(fee);
                payment.setSettleStatus(SettleStatus.SETTLED);
                if (StringUtil.isNotEmpty(requestDTO.getSuccessDate())) {
                    Date setlleTime = DateUtil.getStrToDate(requestDTO.getSuccessDate(), Constants.OPR_DATE_TEMPLATE);
                    payment.setSettleTime(setlleTime);
                } else {
                    payment.setSettleTime(new Date());
                }
                tradeYMFbizService.updateOrderAndPayment(order, payment);
//              此处限制打款订单,他人卡一键支付不允许使用S0  关闭回调时打款  只允许S0订单打款
                if(isS0){
                    invokeRemoteRemitService.doRemittance(order, payment);
                }
                logger.info("oprSettleCallback success! yeepayOrderId:" + yeepayOrderId);
                return SUCCESS;
            } catch (YmfException e) {
                logger.error("completeOrderAndPayment exception yeepayOrderId :" + yeepayOrderId, e);
            } catch (YmfTrxException e) {
                logger.error(" YmfTrxException yeepayOrderId :" + yeepayOrderId + " customerOrderID:" + yeepayOrderId, e);
            } catch (Throwable throwable) {
                logger.error("completeOrderAndPayment exception yeepayOrderId :" + yeepayOrderId, throwable);
            }
        }

        return FALSE;
    }

}
