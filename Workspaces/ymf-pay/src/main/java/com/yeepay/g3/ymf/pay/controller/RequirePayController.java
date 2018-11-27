package com.yeepay.g3.ymf.pay.controller;
/**
 * Created by jiwei.lv on 16/12/27.
 */

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.opr.enumtype.UserTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author jiwei.lv
 * @create 2016-12-16/12/27
 */
@Controller
@RequestMapping("/pay")
public class RequirePayController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(RequirePayController.class);

    private static final String Auth2CallUrl = "/common/auth2Callback";
    private static final String RequirePrefix = "REQUIREPREFIX";
    /**
     * 定制页面
     */
    public static final String REQUIREPAGE = "requirepay/index";
    //标识跳转页面
    public static final String REQUIRE = "REQUIRE";

    /**
     * 定制需求
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("index")
    public String piccIndex(HttpServletRequest request, @RequestParam("code") String code, Map<String, Object> map) {
        try {
            logger.info(log_Line + "Begin 统一微信授权  index");
            String url = handerPiccPublicNumber(request, code, map, Auth2CallUrl);
            logger.info(log_Line + "End 统一微信授权 picc index");
            return url;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg());
        }
    }

    /**
     * 调用Cod查询订单信息。
     */
    @RequestMapping("/queryCodOrder")
    public
    @ResponseBody
    JsonResponse queryCodOrder(@RequestParam("customerRequestId") String customerRequestId, HttpServletRequest request) {
        logger.info(log_Line + "picc/queryCodOrder Query COD Order Begin customerRequestId:" + customerRequestId);
        JsonResponse json = new JsonResponse();
        try {
            if (StringUtils.isEmpty(customerRequestId)) {
                throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
            }
            if(customerRequestId.length()<=4){
                throw new YmfTrxException(TrxCode.T1006,"输入查单信息有误") ;
            }
            String orderPrefix=customerRequestId.substring(0,4);
            Dictionary dictionary=dictionaryService.getDictByTypeAndCode(RequirePrefix,orderPrefix);
            String customerNumber = null;
            if(null!=dictionary){
                customerNumber=dictionary.getValue();
            }else{
                throw new YmfTrxException(TrxCode.T1006,"输入查单信息有误");
            }
            YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,"11111111") ;
            if ("00".equals(sPayQueryOrder.getResCode())) {
                //查单成功
                json.setCode(sPayQueryOrder.getResCode());
                json.setMsg(sPayQueryOrder.getResMsg());
                //对关键信息进行签名
                String orderSign = orderSign(sPayQueryOrder);
                sPayQueryOrder.setOrderSign(orderSign);
                json.setData(sPayQueryOrder);
            } else {
                //显示错误信息
//				json.setCode(sPayQueryOrder.getResCode());
                json.setCode("");
                String msg = sPayQueryOrder.getResMsg();
                if (StringUtils.isBlank(msg)) {
                    msg = "查单失败";
                }
                json.setMsg(msg);
            }
        } catch (Exception e) {
            handleException(e, json);
        }
        logger.info(log_Line + "require/queryCodOrder Query COD Order END");
        return json;
    }

    /**
     * 跳转到订单信息详情页面
     */
    @RequestMapping("orderDetail")
    public String orderDetail(HttpServletRequest request, Map<String, Object> map) {
        try {
            logger.info(log_Line + "orderPay/orderDetail");
            return "requirepay/orderDetail";
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg());
        }
    }

    /**
     * 下单发起支付
     */
    @RequestMapping("doPay")
    public
    @ResponseBody
    JsonResponse doPay(HttpServletRequest request, Map<String, Object> map, YmfOrderParam orderParam) {
        JsonResponse json = new JsonResponse();
        try {
            logger.info(log_Line + "Begin /require/doPay ; params:" + JSONUtils.toJsonString(orderParam));
            //1.校验订单参数
            orderParam.setBusinessType(BusinessType.REQUIRE_PAY);
            checkPayParam(orderParam);
            //2.校验订单状态或创建订单
            Customer customer = customerService.findByCustomerNumber(orderParam.getCustomerNumber());
            Order order = null;
            Payment payment = null;
//          //此处要统一查询订单,不然会出现两笔商户订单号一样的订单,根据查询出来的订单业务类型再进行下一步操作
            Order orgOrder = orderService.findOrderPayOrder(orderParam.getCustomerNumber(), orderParam.getCustomerRequestId());
            if (orgOrder == null) {
                logger.info("Create Order");
                // 创建订单
                order = createPayOrder(orderParam);
                payment = createPayment(customer, order, orderParam);
                tradeYMFbizService.createOrderAndPayment(order, payment);
            } else {
                order = orgOrder;
                payment = paymentService.findByOrderIdAndPayStatusAndTrxType(order.getId(), null, TrxType.PURCHASE);
                if (PaymentStatus.SUCCESS.equals(payment.getPayStatus())) {
                    throw new YmfTrxException(TrxCode.T1008) ;
                } else if (PaymentStatus.SUCCESS.equals(payment.getPayStatus())) {
                    throw new YmfTrxException(TrxCode.T1009) ;
                }
                double compare = DateUtil.compareDate(new Date(), payment.getExpireTime()) ;
                if (compare >= 0) {
                    throw new YmfTrxException(TrxCode.T1009);
                }
                //校验下单是否绑卡,如果有绑卡需要判断请求OpenID
                if (StringUtils.isNotBlank(payment.getOpenId()) && !payment.getOpenId().equals(orderParam.getOpenId())) {
                    throw new YmfTrxException(TrxCode.T1010);
                }
            }
            //判断是否需要调用接口
            if (StringUtils.isEmpty(payment.getPayUrl())) {
                purchase(customer, orderParam, order, request, json, payment);
            } else {
                logger.info("Use local payUrl");
                String cashierCallURL = payment.getPayUrl();
                if (TradeSystemEnum.OPR.equals(order.getTradeSystem())) {
                    cashierCallURL = oprUrlBiz.standardCashier(customer.getCustomerNumber(), cashierCallURL,
                            "", order.getExternalId(), UserTypeEnum.USER_ID.toString());
                    logger.info("ExternalId:{},Opr pay url:{}", order.getExternalId(), cashierCallURL);
                }
                json.setCode("00");
                json.setData(cashierCallURL);
            }
            logger.info(log_Line + "End /requirepay/doPay");
            return json;
        } catch (Exception e) {
            return handleException(e, json);
        }
    }

    /**
     * 广东人保定制需求
     *
     * @param request
     * @param map
     * @param auth2CallUrlSufix
     * @return
     * @throws Exception
     */
    protected String handerPiccPublicNumber(HttpServletRequest request, String code, Map<String, Object> map,
                                            String auth2CallUrlSufix) throws Exception {
        String ua = request.getHeader("user-agent").toLowerCase();
        Dictionary dictionary = dictionaryService.getDictByTypeAndCode(REQUIRETYPE, code);
        if (dictionary == null) {
            throw new YmfTrxException(TrxCode.T1030);
        }
        String customerNumber = dictionary.getValue();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if (customer == null) {
            throw new YmfTrxException(TrxCode.T1003);
        }
        if (!Status.ACTIVE.equals(customer.getStatus())) {
            throw new YmfTrxException(TrxCode.T1003);
        }
        putCustParams(request, map, customer);
        if (ua.contains("micromessenger")) {// 是微信浏览器
            //开关,如果打开则获取OpenId
            if (CfgConstant.isTurnOnPiccOpenId()) {
                logger.info("微信获取OpenId");
                return toWechatAuth(REQUIRE,customerNumber, auth2CallUrlSufix);
            }
            logger.info("不获取微信OpenId");
            //走非报备通道
            return RequirePayController.REQUIREPAGE;
        } else {
            logger.info("Request from Other Browser");
            throw new YmfTrxException(TrxCode.T1029);
        }

    }
}
