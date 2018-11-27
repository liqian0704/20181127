package com.yeepay.g3.ymf.boss.controller.customer;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.entity.customer.WechatRelArgs;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.customer.WechatRelService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.core.ymf.utils.security.SpaySignUtil;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.support.annotations.CommonArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/10/12.
 */
@Controller
@RequestMapping("wechatRel")
public class WechatRelController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(WechatRelController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private WechatRelService wechatRelService;

    @RequestMapping("query")
    public String index() {
        return "customer/wechatRel/query";
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "customer/wechatRel/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseMessage add(@CommonArgs WechatRelArgs relArgs, HttpSession session) {
        ResponseMessage resp = validate(relArgs);
        if (!resp.isOk()) {
            return resp;
        }
        String customerNumber = relArgs.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if (null == customer) {
            return ResponseMessage.error("商户编号不存在");
        }
        // 判断关系是否已存在
        if (wechatRelService.hasRel(customerNumber, relArgs.getAppId())) {
            return ResponseMessage.error("商户已经存在此公众号关系");
        }
        if (Status.ACTIVE == relArgs.getStatus() && null != wechatRelService.findActiveByCustomer(customerNumber)) {
            return ResponseMessage.error("商户已经存在生效的公众号关系");
        }
        WechatRel rel = new WechatRel();
        copyProperties(relArgs, rel);
        encrypt(rel);
        try {
            wechatRelService.save(new OperateEntity<WechatRel>(getUser(session), rel, OperateType.ADD, customerNumber));
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("新增微信公众号关系失败", e);
            return ResponseMessage.error("新增微信公众号关系失败, 异常消息:" + e.getMessage());
        }
    }

    @RequestMapping("toModify")
    public String toModify(@RequestParam("id") Long id, Model model) {
        WechatRel rel = wechatRelService.findById(id);
        if (null == rel) {
            throw new IllegalArgumentException("微信公众号关系不存在");
        }
        decrypt(rel);
        model.addAttribute("wechatRel", rel);
        return "customer/wechatRel/modify";
    }

    @RequestMapping("modify")
    @ResponseBody
    public ResponseMessage modify(@CommonArgs WechatRelArgs relArgs, HttpSession session) {
        ResponseMessage resp = validate(relArgs);
        if (!resp.isOk()) {
            return resp;
        }
        String customerNumber = relArgs.getCustomerNumber();
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if (null == customer) {
            return ResponseMessage.error("商户编号不存在");
        }
        WechatRel rel = wechatRelService.findById(relArgs.getId());
        copyProperties(relArgs, rel);
        encrypt(rel);
        // 判断状态是否变化
        WechatRel activeRel = wechatRelService.findActiveByCustomer(customerNumber);
        if (null != activeRel) {
            if (Status.ACTIVE == rel.getStatus()) {
                // 如果修改不是有效的那个,则判断
                if (!rel.getId().equals(activeRel.getId())) {
                    if (!rel.getAppId().equals(activeRel.getAppId())) {
                        return ResponseMessage.error("商户已存在生效的公众号关系");
                    }
                }
            }
        }
        try {
            wechatRelService.update(new OperateEntity<WechatRel>(getUser(session), rel, OperateType.UPDATE, customerNumber));
            return ResponseMessage.ok();
        } catch (Exception e) {
            log.error("修改微信公众号关系失败", e);
            return ResponseMessage.error("修改微信公众号关系失败, 异常消息:" + e.getMessage());
        }
    }

    /**
     * APP密钥加密
     * @param rel
     */
    private void encrypt(WechatRel rel) {
        String secret = rel.getAppSecret();
        rel.setAppSecret(SpaySignUtil.encryptAppSecret(secret));
    }

    /**
     * APP密钥加密
     * @param rel
     */
    private void decrypt(WechatRel rel) {
        String secret = rel.getAppSecret();
        rel.setAppSecret(SpaySignUtil.decryptAppSecret(secret));
    }
}
