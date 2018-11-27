package com.yeepay.g3.ymf.boss.controller.common;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.service.common.HackService;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/2/27.
 */
@Controller
public class HackController {

    private static final Logger log = LoggerFactory.getLogger(HackController.class);

    @Autowired
    private HackService hackService;

    @RequestMapping("dictionary/toBatchAdd")
    public String route() {
        return "dictionary/hack";
    }

    @RequestMapping("dictionary/batchAdd")
    public @ResponseBody ResponseMessage hack(@RequestParam String sql, @RequestParam String pwd, @RequestParam int count) {
        String password = ConfigureSetting.getValue(Constants.HACK_KEY, String.class);
        if (null == password) {
            return ResponseMessage.error("请别乱点!");
        }
        if (!password.equals(pwd)) {
            return ResponseMessage.error("审核密码不正确");
        }
        if (StringUtils.isBlank(sql)) {
            return ResponseMessage.error("SQL不能为空");
        }
        try {
            sql = URLDecoder.decode(URLDecoder.decode(sql, "utf-8"), "utf-8");
            if (sql.trim().toLowerCase().startsWith("select")) {
                sql = "SELECT * FROM ( SELECT ROW_NUMBER() OVER() AS blablabla_rownum, blablabla.* FROM ( " + sql + " )" +
                        " blablabla) WHERE blablabla_rownum <= " + count;
                return ResponseMessage.data(hackService.query(sql));
            } else {
                hackService.exe(sql);
            }
        } catch (Throwable e) {
            log.error("执行失败", e);
            ResponseMessage resp = ResponseMessage.error("搞错了");
            return resp.setContent(ExceptionUtils.getStackFrames(e));
        }
        return ResponseMessage.ok();
    }
}
