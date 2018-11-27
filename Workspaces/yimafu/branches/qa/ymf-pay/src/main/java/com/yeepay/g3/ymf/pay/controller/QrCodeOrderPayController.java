package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode.T1003;

/**
 * @Description: 行业订单二维码支付
 * @Author: xiaobin.liu
 * @Date: 16/10/11
 * @Time: 下午2:51
 */
@Controller
@RequestMapping("/qrPay")
public class QrCodeOrderPayController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(QrCodeOrderPayController.class) ;
    /**
     * 行业订单二位码支付
     */
    private static final String Auth2CallUrl_QrCodeOrderPay = "/qrPay/auth2Callback" ;
    private static final String indexPage = "qrPay/index" ;
    private static final int orderExpireMin = 60 ;//默认订单超时时间

//    https://yimafu.yeepay.com/ymf-pay/qrPay/index?qr=45KIDTF7&id=123456789012345&ct=161011152420&sg=A7E0237FAC70B27B7FD5675B38DA3AC2
//    https://yimafu.yeepay.com/ymf-pay/qrPay/index?qr=45KIDTF7&id=12345678901234567890&ct=161011152420&sg=A7E0237FAC70B27B7FD5675B38DA3AC2

    /**
     * 获取授权
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, @RequestParam("qr") String qrCode,
                        @RequestParam("id") String customerRequestId,
                        @RequestParam(value="ct",required=false) String createTime,
                        @RequestParam(value="sg",required=false) String sign, Map<String, Object> map) {
        try {
            logger.info(log_Line + "Begin qrCodeOrderPay/index params:");
            //TODO 校验失效时间
            //TODO 验签
//            checkSign(qrCode,sign) ;
            QRCode qrCodeEntity = qrCodeService.selectByQrId(qrCode) ;
            if (qrCodeEntity == null) {
                throw new YmfTrxException(TrxCode.T1002) ;
            }
            if (!MaterialStatus.ACTIVE.equals(qrCodeEntity.getStatus())) {
                throw new YmfTrxException(TrxCode.T1002) ;
            }
            String customerNumber = qrCodeEntity.getCustomerNumber() ;
            String ua = request.getHeader("user-agent").toLowerCase();
            if (ua.contains("micromessenger")) {// 是微信浏览器
                return toWechatAuth(sign, customerNumber);
            } else if(ua.contains("alipay")) {// 支付宝
                logger.info("Request from Alipay Browser");
            } else if(ua.contains("baidu")) {// 百度钱包
                logger.info("Request from Baidu Wallet Browser");
            } else {
                logger.info("Request from Other Browser");
            }
            Customer customer = customerService.findByCustomerNumber(customerNumber) ;
            if (!Status.ACTIVE.equals(customer.getStatus())) {
                throw new YmfTrxException(TrxCode.T1003) ;
            }
            putCustParams(request,map, customer);
            map.put("sign", sign) ;
            logger.info(log_Line + "End /orderPay/index");
            return "qrPay/index" ;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
        }
    }

    /**
     * 处理微信回调
     */
    private String toWechatAuth(@RequestParam(value = "sign", required = false) String sign, String customerNumber)
            throws UnsupportedEncodingException, YmfTrxException {
        logger.info("Request from Wechat Browser");
        String redrectUrl = wechatAuth2Url(sign, customerNumber,Auth2CallUrl_QrCodeOrderPay);
        logger.debug("redrectUrl " + redrectUrl);
        return "redirect:" + redrectUrl ;
    }

    /**
     * 获取openId ；
     * 接收微信系统回调。
     */
    @RequestMapping("auth2Callback")
    public String auth2Callback(HttpServletRequest request, Map<String, Object> map,HttpSession session) {
        try {
            logger.info(log_Line + "Begin /orderPay/auth2Callback");
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String customerNumber = state ;
            String sign = "" ;
            if (customerNumber.contains("_")) {
                String arr[] = customerNumber.split("_");
                customerNumber = arr[0] ;
                sign = arr[1] ;
            }
            Customer customer = customerService.findByCustomerNumber(customerNumber) ;
            if (!Status.ACTIVE.equals(customer.getStatus())) {
                throw new YmfTrxException(T1003) ;
            }
            putCustParams(request,map, customer);
            map.put("sign", sign) ;
            String openId = wechatOpenId(customerNumber,code);
            if (StringUtils.isNotBlank(openId)) {
                session.setAttribute("openId", openId);
                map.put("openId", openId);
            } else {
                String ua = request.getHeader("user-agent").toLowerCase();
                if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
                    return toWechatAuth(sign, customerNumber);
                }
            }
            logger.info(log_Line + "End /orderPay/auth2Callback");
            return "orderPay/index" ;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
        }
    }



    /**
     * 调用Cod查询订单信息。
     */
    @RequestMapping("/queryCodOrder")
    public @ResponseBody
    JsonResponse queryCodOrder(@RequestParam("customerRequestId") String customerRequestId,
                               @RequestParam("customerNumber") String customerNumber,
                               @RequestParam("qrCode") String qrCode,
                               HttpServletRequest request) {
        logger.info(log_Line + "orderPay/queryCodOrder Query COD Order Begin");
        logger.info("customerNumber :" + customerNumber + ",customerRequestId :" + customerRequestId);
        JsonResponse json = new JsonResponse() ;
        try {
            if (StringUtils.isEmpty(customerRequestId)) {
                throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
            }
            if (StringUtils.isEmpty(customerNumber)) {
                throw new YmfTrxException(TrxCode.T1006,"未获取商户编号") ;
            }
            YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,qrCode) ;
            logger.info("cod code : " + sPayQueryOrder.getResCode());
            logger.info("cod msg : " + sPayQueryOrder.getResMsg());
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
                json.setMsg(sPayQueryOrder.getResMsg());
            }
        } catch (Exception e) {
            handleException(e, json);
        }
        logger.info(log_Line + "orderPay/queryCodOrder Query COD Order END");
        return json ;
    }


    /**
     * 调用获取订单码。订单码由本系统生成,通过json直接返回订单码。
     */
    @RequestMapping("/createQrCode")
    public @ResponseBody
    JsonResponse createQrCode(@RequestParam("requestId") String customerRequestId,
                               @RequestParam("qr") String customerNumber,
                              @RequestParam(value="requestTime",required=false) String requestTime,
                              @RequestParam(value="sign",required=false) String sign) {
        logger.info(log_Line + "Begin qrPay/createQrCode");
        logger.info("customerNumber :" + customerNumber + ",customerRequestId :" + customerRequestId);
        JsonResponse json = new JsonResponse() ;
        try {
            if (StringUtils.isEmpty(customerRequestId)) {
                throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
            }
            if (StringUtils.isEmpty(customerNumber)) {
                throw new YmfTrxException(TrxCode.T1006,"未获取商户编号") ;
            }
            YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,customerNumber) ;
            logger.info("cod code : " + sPayQueryOrder.getResCode());
            logger.info("cod msg : " + sPayQueryOrder.getResMsg());
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
                json.setMsg(sPayQueryOrder.getResMsg());
            }
        } catch (Exception e) {
            handleException(e, json);
        }
        logger.info(log_Line + "orderPay/queryCodOrder Query COD Order END");
        return json ;
    }



}
