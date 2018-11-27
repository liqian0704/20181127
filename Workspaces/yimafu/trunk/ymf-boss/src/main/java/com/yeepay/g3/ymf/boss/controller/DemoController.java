package com.yeepay.g3.ymf.boss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/2.
 */
@Controller
public class DemoController {

    /**
     * index 获取session信息
     * @param session
     * @return
     */
    @RequestMapping("index")
    @ResponseBody
    public Map<String, Object> index(HttpSession session) {
        Enumeration<String> enums = session.getAttributeNames();
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            if (key.contains("Menu")) {
                continue;
            }
            if (key.equals("menuMap")) {
                continue;
            }
            if (key.equals("currentSystem")) {
                continue;
            }
            if (key.equals("menuTree")) {
                continue;
            }
            sessionMap.put(key, session.getAttribute(key));
        }
        return sessionMap;
    }

    @RequestMapping("demo")
    public @ResponseBody String demoMsg() {
        return "hello world!";
    }
}
