package com.yeepay.g3.core.ymf.servlet;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.facade.TradeFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.soa.context.ApplicationContextHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 交易退款补单servlet接口  降级用
 * Created by chen.liu on 2016/8/29.
 */
public class SupplyServlet extends JsonResponseServlet  {

    private static final long serialVersionUID = 1958292517989222815L;

    private static final Logger log = LoggerFactory.getLogger(SupplyServlet.class);
    private TradeFacade tradeFacade;

    /**
     * url: supplyServlet?supply_method=&hmac=
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("接收到定时补单接口请求, 请求用户:" + req.getRemoteAddr());
        // 根据methodName路由
        String methodName = req.getParameter(Constants.SUPPLY_SERVLET_METHOD);
        // 获取不到methodName
        if (StringUtils.isBlank(methodName)) {
            String error = Constants.SUPPLY_SERVLET_METHOD + "参数不能为空";
            log.info(error);
            responseJson(resp, ResponseMessage.error(error));
            return;
        }
        // 校验私钥
        String hmac = req.getParameter("hmac");
        if (StringUtils.isBlank(hmac)) {
            String error = "私钥参数不能为空";
            log.info(error);
            responseJson(resp, ResponseMessage.error(error));
            return;
        }
        // 校验秘钥
        if (Constants.SUPPLY_SERVLET_HMAC.equals(hmac)) {
            String error = "私钥参数有误, 你发错接口了吧!";
            log.info(error);
            responseJson(resp, ResponseMessage.error(error));
            return;
        }
        // 根据方法名反射
        try {
            Method method = TradeFacade.class.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(tradeFacade);
            responseJson(resp, ResponseMessage.ok());
        } catch (Exception e) {
            String error = methodName + "方法调用异常";
            log.error(error, e);
            responseJson(resp, ResponseMessage.error(error));
        }
    }

    @Override
    public void init() throws ServletException {
        tradeFacade = ApplicationContextHelper.getApplicationContext().getBean(TradeFacade.class);
    }
}
