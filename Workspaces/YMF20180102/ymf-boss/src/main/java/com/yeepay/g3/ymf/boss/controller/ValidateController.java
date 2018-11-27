package com.yeepay.g3.ymf.boss.controller;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.utils.BeanValidator;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.BeanUtils;

import javax.servlet.http.HttpSession;


/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/13.
 */
public abstract class ValidateController {

    /**
     * 校验
     * @param o
     * @return
     */
    protected ResponseMessage validate(Object o) {
        return BeanValidator.validate(o);
    }

    /**
     * 校验
     * @param o
     * @return
     */
    protected ResponseMessage validateExclude(Object o, String...args) {
        return BeanValidator.validateExclude(o, args);
    }

    /**
     * 校验
     * @param o
     * @return
     */
    protected ResponseMessage validateInclude(Object o, String...args) {
        return BeanValidator.validateInclude(o, args);
    }

    /**
     * 获取三代session用户名
     * @param session
     * @return
     */
    protected String getUser(HttpSession session) {
        return (String) session.getAttribute(Constants.SSO_SESSION_USER_ID);
    }

    /**
     * 复制同名属性
     * @param src
     * @param dest
     */
    protected void copyProperties(Object src, Object dest) {
        BeanUtils.copyProperties(src, dest);
    }

    public static void main(String[] args) {
        WechatRel rel1 = new WechatRel();
        rel1.setId(1L);
        rel1.setCustomerNumber("123");

        WechatRel rel2 = new WechatRel();
        rel2.setCustomerNumber("456");

        ValidateController controller = new ValidateController() {};
        controller.copyProperties(rel2, rel1);
        System.out.println(rel1);
    }
}
