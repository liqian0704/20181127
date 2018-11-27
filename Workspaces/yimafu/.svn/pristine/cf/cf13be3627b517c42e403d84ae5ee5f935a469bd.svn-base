package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 *
 * Created by dongxulu on 16/11/4.
 */
@Controller
@RequestMapping("/gratuity")
public class GratuityPayController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(StandardPayController.class) ;
    /**
     * 订单支付，回调地址
     */
    private static final String Auth2CallUrl_OrderPay = "/gratuity/auth2Callback" ;
    public static final String indexPage = "gratuity/pay" ;


    /**
     * 下单发起支付
     */
    @RequestMapping("doPay")
    public @ResponseBody
    JsonResponse standardPay(HttpServletRequest request, Map<String, Object> map, YmfOrderParam orderParam) {
        JsonResponse json = new JsonResponse() ;
        try {
            logger.info(log_Line + "Begin /gratuity/doPay");
            orderParam.setBusinessType(BusinessType.GRATUITY_PAY);
            logger.info("Pay param :" + orderParam);
            /* 1.校验订单参数 */
            checkPayParam(orderParam);
            //2.校验订单状态或创建订单
            Customer customer = customerService.findByCustomerNumber(orderParam.getCustomerNumber()) ;
            // 创建订单
            Order order = createPayOrder(orderParam) ;
            Payment payment = createPayment(customer, order, orderParam) ;
            tradeYMFbizService.createOrderAndPayment(order, payment);
            logger.info(log_Line + "Create Order Success ExtenalId=" + order.getExternalId());
            //3.发起订单支付
            TradeCashierResponseDTO purchaseResp = callCashier(customer, orderParam, order,request) ;
            //4.处理结果  更新订单对应的支付链接，响应支付链接到前端，通过js调整到收银台链接
            int stat = purchaseResp.getOrderState();
            String cashierCallURL =  purchaseResp.getCashierCallURL();
            logger.info(log_Line+"callCashier return stat:"+stat);
            if(StringUtils.isNotEmpty(cashierCallURL)){
                json.setCode("00");
                json.setData(purchaseResp.getCashierCallURL());
                payment.setPayUrl(cashierCallURL);
                payment.setPayStatus(PaymentStatus.PROCESS);
                payment.setPayRequestTime(new Date());
                order.setOrderStatus(OrderStatus.PROCESS);
                //支付链接，收银台订单号回写
                tradeYMFbizService.updatePayUrlAndYeePayOrderId(order, payment);
            } else {
//				json.setCode(purchaseResp.getCashierErrorCode());
//				json.setMsg(purchaseResp.getCashierErrorMsg());
                //如果未返回支付链接直接提示下单异常，请稍后重试
                json.setCode("80");
                json.setMsg("下单异常，请稍后重试");
            }
            logger.info(log_Line + "End /standard/doPay");
            return json ;
        } catch (Exception e) {
            return handleException(e,json) ;
        }
    }

}
