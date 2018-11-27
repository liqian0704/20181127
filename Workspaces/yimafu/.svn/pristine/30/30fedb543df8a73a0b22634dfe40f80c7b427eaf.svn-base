package com.yeepay.g3.core.ymf.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeepay.g3.core.ymf.biz.trade.TradeBiz;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.support.ServletSupport;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.order.OrderArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderDownloadArgs;
import com.yeepay.g3.facade.ymf.dto.order.OrderQueryArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderDownloadArgs;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;
import sun.nio.cs.ext.GBK;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Title: 2代商户后台对接易码付servlet
 * Description: 订单查询
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class G2Servlet extends JsonResponseServlet {

    private static final Logger log = LoggerFactory.getLogger(G2Servlet.class);
    private static final long serialVersionUID = -5850235227329927485L;
    private TradeBiz tradeBiz;

    /**
     * url: g2Servlet?g2_apicode=&hmac=&g2_param={jsonstring}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String remoteIp = req.getRemoteAddr();
        String checkIp = ConfigureSetting.getValue(Constants.YMF_HEART_BEAT_IP, "10.149.254.225");
        if (checkIp.equals(remoteIp)) { // 心跳ip直接ok
            resp.getWriter().write("ok");
            return;
        }
        log.info("接收到2代商户接口请求, 请求ip:" + remoteIp + ", user:" + req.getRemoteUser() + ", host:" + req.getRemoteHost() + ", port:" + req.getRemotePort());
        // 根据APICODE路由
        String apiCode = req.getParameter(Constants.G2SERVLET_API);
        // 获取不到apicode
        if (StringUtils.isBlank(apiCode)) {
            String error = Constants.G2SERVLET_API + "参数不能为空";
            log.info(error);
            response(resp, ResponseMessage.error(error));
            return;
        }
        // 校验私钥
        String hamc = req.getParameter("hmac");
        if (StringUtils.isBlank(hamc)) {
            String error = "私钥参数不能为空";
            log.info(error);
            response(resp, ResponseMessage.error(error));
            return;
        }
        // 比较密钥
        if (!Constants.G2SERVLET_API_HMAC.equals(hamc)) {
            String error = "私钥参数有误, 你发错接口了吧!";
            log.info(error);
            response(resp, ResponseMessage.error(error));
            return;
        }
        // 2代商户后台是GBK编码 urlEncode了
        req.setCharacterEncoding("UTF-8");
        String orderParamGBKJson = req.getParameter(Constants.G2SERVLET_API_PARAM);
        if (StringUtils.isBlank(orderParamGBKJson)) {
            String error = Constants.G2SERVLET_API_PARAM + "参数不能为空";
            log.info(error);
            response(resp, ResponseMessage.error(error));
            return;
        }
        log.info("接收到2代商户请求 apiCode:" + apiCode);
        // g2_param
        try {
            log.info("***********"+orderParamGBKJson);


            String result=null;
            if(Constants.G2SERVLET_API_ORDER_QUERY.equals(apiCode) ){
               String  codeResult = URLDecoder.decode(orderParamGBKJson, "UTF-8");
                log.info(codeResult);
                result=new String(codeResult.getBytes("GBK"), "UTF-8");
                log.info(result);

            }else if( Constants.G2SERVLET_API_ORDER_DOWNLOAD.equals(apiCode)){
                String codeResult = URLDecoder.decode(orderParamGBKJson, "UTF-8");
                log.info(codeResult);
                 result=new String(codeResult.getBytes("GBK"), "UTF-8");
                log.info(result);
            }else{
                result = URLDecoder.decode(orderParamGBKJson, "UTF-8");
            }
            log.info("******"+result);
//            String orderParamJson = URLDecoder.decode(orderParamGBKJson, "UTF-8");
//            log.info("*******"+orderParamJson);
//            String resultParam=URLDecoder.decode(orderParamJson,"GBK");
//            log.info(resultParam);
//            orderParamJson=resultParam;
            String orderParamJson=result;
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> paramMap = om.readValue(orderParamJson, Map.class);
            long start = System.currentTimeMillis();
            // 根据apiCode判断执行逻辑
//            log.info(paramMap.get("shopName").toString());
            apiRoute(apiCode, paramMap, resp);
            log.info("2代商户接口请求处理完毕, 请求ip:" + req.getRemoteAddr() +
                    ", 处理耗时:" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (Exception e) {
            log.error("2代商户接口请求处理发生异常, 请求ip:" + req.getRemoteAddr(), e);
            response(resp, ResponseMessage.error("请求异常", new YmfException(e)));
        }
    }

    /**
     * api路由
     * @param apiCode
     * @param paramMap
     * @param resp
     * @throws IOException
     */
    private void apiRoute(String apiCode, Map<String, Object> paramMap, HttpServletResponse resp) throws IOException {
        if (Constants.G2SERVLET_API_ORDER_QUERY.equals(apiCode)) { // 订单查询
            OrderArgs orderArgs = ServletSupport.buildOrderArgs(paramMap);
            response(resp, tradeBiz.queryOrder(orderArgs));
        } else if(Constants.G2SERVLET_API_ORDER_DOWNLOAD.equals(apiCode)){
            OrderArgs orderArgs = ServletSupport.buildOrderArgs(paramMap);
            response(resp, tradeBiz.queryOrder(orderArgs));
        }else if (Constants.G2SERVLET_API_REFUND_QUERY.equals(apiCode)) { // 退款查询
            RefundOrderArgs refundArgs = ServletSupport.buildRefundArgs(paramMap);
            response(resp, tradeBiz.queryRefund(refundArgs));
        } else if (Constants.G2SERVLET_API_QUERY_ORDER.equals(apiCode)) { // 根据商户编号的易宝流水号查询订单
            OrderQueryArgs queryArgs = ServletSupport.buildQueryOrderArgs(paramMap);
            response(resp, tradeBiz.querySingleOrder(queryArgs));
        } else if (Constants.G2SERVLET_API_REFUND_ORDER.equals(apiCode)) { // 发起退款
            boolean refundFunction = ConfigureSetting.getValue(Constants.YMF_G2_REFUND_FUNCTION, false);
            if (refundFunction) {
                RefundRequestDTO requestArgs = ServletSupport.buildRefundRequest(paramMap);
                response(resp, tradeBiz.refundOrder(requestArgs));
            } else {
                response(resp, ResponseMessage.error("退款功能维护中..."));
            }
        } else {
            log.error("请求的APICODE不正确, apicode:" + apiCode);
            response(resp, ResponseMessage.error("请求的APICODE不正确, apicode:" + apiCode));
        }
    }

    @Override
    public void init() throws ServletException {
        tradeBiz = ApplicationContextHelper.getApplicationContext().getBean(TradeBiz.class);
    }
}
