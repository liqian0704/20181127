package com.yeepay.g3.ymf.boss.controller.common;

import com.yeepay.g3.core.ymf.support.bean.LocalSimpleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: 日志
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
@Lazy
@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private LocalSimpleCache localSimpleCache;

    @RequestMapping("query")
    public String query(Model model) {
        model.addAttribute("entry", localSimpleCache.getEntrySet());
        return "common/logQuery";
    }

}
