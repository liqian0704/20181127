package com.yeepay.g3.ymf.boss.controller.apperrormsg;

import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: app错误信息管理
 * Author: wei.li
 * Date: 17/2/15
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/appErrorMsg")
public class AppErrorMsgController  extends ValidateController {

    @RequestMapping("/query")
    public String msgQuery() {
        return "appErrorMsg/appErrorMsgQuery";
    }
}
