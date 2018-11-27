package com.yeepay.g3.ymf.boss.controller.msgpush;

import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/17
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/appMsgManage")
public class MsgPushController extends ValidateController {

    @RequestMapping("/query")
    public String msgQuery() {
        return "msgmanage/pushMsgQuery";
    }
}
