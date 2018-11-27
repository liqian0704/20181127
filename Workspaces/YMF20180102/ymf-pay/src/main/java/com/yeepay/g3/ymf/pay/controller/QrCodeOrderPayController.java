package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.utils.security.SpaySignUtil;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private static final String indexPage = "qrPay/orderDetail" ;
    private static final int orderExpireMin = 60 ;//默认订单超时时间

//    https://yimafu.yeepay.com/ymf-pay/qrPay/index?qr=45KIDTF7&id=12345678901234567890&ct=161011152420&sg=A7E0237FAC70B27B7FD5675B38DA3AC2

    /**
     * 获取授权
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, @RequestParam("qr") String qrCode,
                        @RequestParam("id") String customerRequestId,
                        @RequestParam(value="ct",required=false) String createTime,
                        @RequestParam(value="sg",required=false) String sign, Map<String, Object> map) {
        logger.info(log_Line + "开始 [二维码支付] 请求参数：requestId={},qrCode={},sign={}",
                customerRequestId,qrCode,sign);
        try {
            if (StringUtils.isEmpty(customerRequestId)) {
                throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
            }
            if (StringUtils.isEmpty(qrCode)) {
                throw YmfTrxException.QRCODE_INVALID_ERROR;
            }
            QRCode qrCodeEntity = qrCodeService.selectByQrId(qrCode) ;
            if (qrCodeEntity == null) {
                throw new YmfTrxException(TrxCode.T1002) ;
            }
            if (!MaterialStatus.ACTIVE.equals(qrCodeEntity.getStatus())) {
                throw new YmfTrxException(TrxCode.T1002) ;
            }
            Map<String, String> data = new HashMap<String, String>();
            data.put("qr", qrCode);
            data.put("id", customerRequestId);
            data.put("ct", createTime);

            checkSign(data, sign, qrCodeEntity.getCustomerNumber());
            //校验通过，开始调用COD验证订单，然后生成链接
            Customer customer = customerService.findByCustomerNumber(qrCodeEntity.getCustomerNumber());
            if (customer == null) {
                throw YmfTrxException.CUSTOMER_INVALID_ERROR;
            }
            String customerNumber = qrCodeEntity.getCustomerNumber() ;
            if (!Status.ACTIVE.equals(customer.getStatus())) {
                throw new YmfTrxException(TrxCode.T1003) ;
            }

            //开始判断获取OpenId
            String ua = request.getHeader("user-agent").toLowerCase();
            if (ua.contains("micromessenger")) {// 是微信浏览器
                //同时把 商户订单号 拼接到参数里
                return toWechatAuth(qrCode + "_" + customerRequestId, customerNumber,Auth2CallUrl_QrCodeOrderPay);
            } else if(ua.contains("alipay")) {// 支付宝
                logger.info("Request from Alipay Browser");
            } else if(ua.contains("baidu")) {// 百度钱包
                logger.info("Request from Baidu Wallet Browser");
            } else {
                logger.info("Request from Other Browser");
            }

            //开始查询COD订单
            YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,qrCode) ;
            if ("00".equals(sPayQueryOrder.getResCode())) {
                //对关键信息进行签名
                String orderSign = orderSign(sPayQueryOrder);
                sPayQueryOrder.setOrderSign(orderSign);
                sPayQueryOrder.setQrCode(qrCode);
                if (StringUtils.isBlank(customer.getCustomerLogo())) {
                    customer.setCustomerLogo(request.getContextPath() + "/static/images/ymf.png");
                }
                sPayQueryOrder.setCustomerLogo(customer.getCustomerLogo());
                sPayQueryOrder.setOpenId("");//获取不到openId
            } else {
                //显示错误信息
                String msg = sPayQueryOrder.getResMsg();
                return toCommonErrorPage(request, "查询保单失败：" + msg);
            }
            map.put("order", sPayQueryOrder) ;
            logger.info(log_Line + "结束 [二维码支付] 响应参数：{}" ,sPayQueryOrder);
            return "qrPay/orderDetail" ;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
        }
    }


    /**
     * 获取openId ；
     * 接收微信系统回调。
     */
    @RequestMapping("auth2Callback")
    public String auth2Callback(HttpServletRequest request, Map<String, Object> map) {
        try {
            logger.info(log_Line + "Begin /orderPay/auth2Callback 参数:" + request.getQueryString());
            String url = handleAuth2Callback2(request, map,Auth2CallUrl_QrCodeOrderPay,indexPage);
            logger.info(log_Line + "End /orderPay/auth2Callback");
            return url ;
        } catch (Exception e) {
            return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
        }
    }

    /**
     * 处理微信回调
     * @param request
     * @param map
     * @param page		处理完,跳转的支付页面。
     * @return
     * @throws IOException
     */
    private String handleAuth2Callback2(HttpServletRequest request, Map<String, Object> map,
                                         String auth2CallUrlSufix,String page) throws Exception {
        logger.info("微信回调参数:" + request.getQueryString());
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String customerNumber = state ;
        String qrCode = "" ;//qrCode
        String customerRequestId = "";//customerRequestId
        if (customerNumber.contains("_")) {
            String arr[] = customerNumber.split("_");
            customerNumber = arr[0] ;
            qrCode = arr[1] ;
            customerRequestId = arr[2];
        }
        Customer customer = customerService.findByCustomerNumber(customerNumber) ;
        if (!Status.ACTIVE.equals(customer.getStatus())) {
            throw new YmfTrxException(TrxCode.T1003) ;
        }
        putCustParams(request,map, customer);
        map.put("qrCode", qrCode) ;
        String openId = wechatOpenId(customerNumber,code);
        if (StringUtils.isNotBlank(openId)) {
            map.put("openId", openId);
        } else {
            String ua = request.getHeader("user-agent").toLowerCase();
            if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
                return toWechatAuth(qrCode + "_" + customerRequestId, customerNumber,auth2CallUrlSufix);
            }
        }

        //开始查询COD订单
        YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,qrCode) ;
        if ("00".equals(sPayQueryOrder.getResCode())) {
            //对关键信息进行签名
            String orderSign = orderSign(sPayQueryOrder);
            sPayQueryOrder.setOrderSign(orderSign);
            sPayQueryOrder.setQrCode(qrCode);
            if (StringUtils.isBlank(customer.getCustomerLogo())) {
                customer.setCustomerLogo(request.getContextPath() + "/static/images/ymf.png");
            }
            sPayQueryOrder.setCustomerLogo(customer.getCustomerLogo());
            sPayQueryOrder.setOpenId(openId);//获取不到openId
        } else {
            //显示错误信息
            String msg = sPayQueryOrder.getResMsg();
            return toCommonErrorPage(request, "查询保单失败：" + msg);
        }
        map.put("order", sPayQueryOrder) ;

        return page ;
    }

    // qrType = qrdata img qrlink
    /**
     * 调用获取订单码。订单码由本系统生成,通过json直接返回订单码。
     * @param customerRequestId   requestId 商户订单号
     * @param customerNumber  customerNumber 商户编号
     * @param sign  签名
     * @return
     */
    @RequestMapping("/createQrCode")
    public @ResponseBody
    JsonResponse createQrCode(@RequestParam("requestId") String customerRequestId,
                               @RequestParam("qr") String customerNumber,
                              @RequestParam(value="sign",required=false) String sign,
                              @RequestParam(value = "qrType",required = true) String qrType) {
        logger.info(log_Line + "开始 [获取二维码] 请求参数：requestId={},customerNumber={},sign={}",
                customerRequestId,customerNumber,sign);
        JsonResponse json = new JsonResponse() ;
        try {
            //对参数进行签名验证
            if (StringUtils.isEmpty(customerRequestId)) {
                throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
            }
            if (StringUtils.isEmpty(customerNumber)) {
                throw new YmfTrxException(TrxCode.T1006,"未获取商户编号") ;
            }
            Map<String, String> data = new HashMap<String, String>();
            data.put("requestId", customerRequestId);
            data.put("qr", customerNumber);
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            if (customer == null) {
                throw YmfTrxException.CUSTOMER_INVALID_ERROR;
            }
            checkSign(data, sign, customerNumber);
            //校验通过，开始调用COD验证订单，然后生成链接


            json.setCode(TrxCode.T00.getCode());
            json.setMsg(TrxCode.T00.getMsg());
        } catch (Exception e) {
            handleException(e, json);
        }
        logger.info(log_Line + "结束 [获取二维码] 响应参数：{}",json);
        return json ;
    }

    @RequestMapping("/mockCreteQrCode")
    public @ResponseBody JsonResponse testCreteQrCode(@RequestParam("requestId") String requestId,
                                                      @RequestParam("qr") String qrCode,
                                                      @RequestParam("pass") String pass,
                                                      @RequestParam(value = "env",required = false) String env) {
        logger.info(log_Line + "开始 [获取测试二维码] 请求参数：requestId={},qr={},env={}",
                requestId,qrCode,env);
        JsonResponse json = new JsonResponse() ;
        try {
            if (!"ymf123456".equals(pass)) {
                throw new YmfTrxException(TrxCode.T1000, "密码错误");
            }
            QRCode qrCode1 = qrCodeService.selectByQrId(qrCode);
            if (qrCode1 == null) {
                throw new YmfTrxException(TrxCode.T1002);
            }
            Customer customer = customerService.findByCustomerNumber(qrCode1.getCustomerNumber());
            if (customer == null) {
                throw YmfTrxException.CUSTOMER_INVALID_ERROR;
            }
            //测试白名单
//            Set<String> set = new HashSet < String >();
//            set.add("10040007800");
//            set.add("10040007800");
//            set.add("10040007800");
//            if (!set.contains(customer.getCustomerNumber())) {
//                throw new YmfTrxException(TrxCode.T1000, "只支持以下测试商户：" + JSONUtils.toJsonString(set));
//            }
            String hmacKey = g2ServiceExt.queryCustomerHmacKey(customer.getCustomerNumber());
            json.setData(SpaySignUtil.createOrderQrUrl(qrCode, requestId, hmacKey, env));
            json.setCode(TrxCode.T00.getCode());
            json.setMsg(TrxCode.T00.getMsg());
        } catch (Exception e) {
            handleException(e, json);
        }
        logger.info(log_Line + "结束 [获取测试二维码] 响应参数：{}",json);
        return json ;
    }

    @RequestMapping("/toCreateQr")
    public String toCreateQr() {
        return "qrPay/createQr";
    }

    /**
     * 校验二维码入口签名
     */
    private boolean checkSign(Map<String,String> data,String sign,String customerNumer) throws Exception {
        String hmacKey = g2ServiceExt.queryCustomerHmacKey(customerNumer);
        if (StringUtils.isBlank(hmacKey)) {
            logger.info("获取二代商户hmckey失败,customerNumer:{}" + customerNumer);
            throw YmfTrxException.SYSTEM_ERROR;
        }
        //签名校验
        if (StringUtils.isBlank(sign)) {
            logger.error("验证签名失败,参数为空");
            throw new YmfTrxException(TrxCode.T1001) ;
        }
        String newSign = SpaySignUtil.sign(data,hmacKey) ;
        if (!sign.equals(newSign)) {
            //如果签名验证失败
            logger.error("验证签名失败");
            throw new YmfTrxException(TrxCode.T1001) ;
        }
        return true ;
    }

    public static void main(String[] args) {
        logger.info("加密后：{}" , AES.encryptToBase64("1234567890", Constants.APP_SECRET_AES_KEY));
    }





}
