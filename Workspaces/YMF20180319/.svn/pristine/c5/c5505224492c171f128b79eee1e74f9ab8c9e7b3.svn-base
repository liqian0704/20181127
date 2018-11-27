package com.yeepay.g3.core.ymf.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yeepay.g3.core.ymf.support.JacksonBigDecimalSerializer;
import com.yeepay.g3.core.ymf.support.JacksonNullSerializer;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 用于servlet回复json类型返回值
 * Created by chen.liu on 2016/8/29.
 */
public abstract class JsonResponseServlet extends HttpServlet {

    private static final long serialVersionUID = -6847940697691213078L;

    /**
     * json消息回复
     * @param resp servlet response
     * @param msg 消息实体
     * @throws IOException
     */
    protected void response(HttpServletResponse resp, ResponseMessage msg) throws IOException {
//        ObjectMapper om = new ObjectMapper();
//        String respJsonUTF8 = om.writeValueAsString(msg);
//        String respJson = URLEncoder.encode(respJsonUTF8, "UTF-8");
////        resp.setContentType("charset=UTF-8");
//        resp.getWriter().write(respJson);
        responseJson(resp, msg);
    }

    /**
     * json消息回复
     * @param resp servlet response
     * @param msg 消息实体
     * @throws IOException
     */
    protected void responseJson(HttpServletResponse resp, ResponseMessage msg) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // null show null string
        om.getSerializerProvider().setNullValueSerializer(JacksonNullSerializer.instance);
        // BigDecimal show plain string
        SimpleModule module = new SimpleModule();
        module.addSerializer(JacksonBigDecimalSerializer.instance);
        om.registerModule(module);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(om.writeValueAsString(msg));
    }

}
