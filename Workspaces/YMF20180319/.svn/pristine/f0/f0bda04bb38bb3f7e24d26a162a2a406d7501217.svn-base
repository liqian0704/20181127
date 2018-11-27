package com.yeepay.g3.ymf.boss.controller.likerapp;

import com.google.common.collect.Maps;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.OpenAccountResponse;
import com.yeepay.g3.facade.laike.dto.boss.UpdateAllianceAccRequest;
import com.yeepay.g3.facade.laike.enumtype.AttachmentTypeEnum;
import com.yeepay.g3.facade.laike.enumtype.LOLOpenStatus;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.laike.facade.AllianceBossFacade;
import com.yeepay.g3.facade.laike.facade.app.AppOpenAccountFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 来赚宝controller
 * Author: wei.li
 * Date: 17/6/19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/appAudit")
public class AllianceAuditController extends ValidateController{

    private static final Logger log = LoggerFactory.getLogger(AllianceAuditController.class);

    @RequestMapping("/query")
    public String auditQuery() {
		return "likerapp/alliance/allianceAuditQuery";
	}

    @RequestMapping("/toModify")
    public String toModify(@RequestParam("memberNo") String memberNo, @RequestParam("id") String id, @RequestParam("isModify") String isModify, Model model) {
        AppOpenAccountFacade appOpenAccountFacade = RemoteServiceFactory.getService(AppOpenAccountFacade.class);
        BaseRequest request = new BaseRequest();
        request.setMemberNo(memberNo);
        OpenAccountResponse response = appOpenAccountFacade.findOpenAccount(request);
        if (LOLOpenStatus.OCR_AUDIT.equals(response.getLolOpenStatus()) || (LOLOpenStatus.RETURN.equals(response.getLolOpenStatus()) && response.getRemark() != null)
                || LOLOpenStatus.SUCCESS.equals(response.getLolOpenStatus())){
            String remark = "";
            if (response.getRemark() != null){
                HashMap map = (HashMap) JSONUtils.jsonToMap(response.getRemark(), String.class, String.class);
                if (map.size() > 1){
                    remark = "身份证和结算卡信息有误";
                }else if (map.containsKey(AttachmentTypeEnum.ID_CARD_FRONT.name())){
                    remark = AttachmentTypeEnum.ID_CARD_FRONT.getErrorMsg();
                }else if (map.containsKey(AttachmentTypeEnum.SETTLE_CARD.name()))
                    remark = AttachmentTypeEnum.SETTLE_CARD.getErrorMsg();
                if (remark.length() > 0){
                    model.addAttribute("remark", remark);
                }
            }
            model.addAttribute("response", response);
            model.addAttribute("id", id);
            model.addAttribute("isModify", isModify);
			return "likerapp/alliance/allianceAuditModify";
		} else {
			return "likerapp/alliance/allianceAuditQuery";
		}

    }

    @RequestMapping("/modify")
    @ResponseBody
    public ResponseMessage auditModify(String id, String result, HttpSession session) {
        try {
            String operator = getUser(session);
            log.info("id:" + id + " result:" + result + " operator:" + operator);
            AllianceBossFacade facade = RemoteServiceFactory.getService(AllianceBossFacade.class);
            UpdateAllianceAccRequest request = new UpdateAllianceAccRequest();
            request.setId(id);
            if (result.trim().equals("SUCCESS")) {
                request.setStatus(LOLOpenStatus.SUCCESS);
            } else {
                Map<String, String> map = Maps.newHashMap();
                if (result.trim().equals("idCardError")) {
                    map.put("ID_CARD_FRONT", AttachmentTypeEnum.ID_CARD_FRONT.getErrorMsg());
                } else if (result.trim().equals("bankCardError")) {
                    map.put("SETTLE_CARD", AttachmentTypeEnum.SETTLE_CARD.getErrorMsg());
                } else if (result.trim().equals("bothError")) {
                    map.put("ID_CARD_FRONT", AttachmentTypeEnum.ID_CARD_FRONT.getErrorMsg());
                    map.put("SETTLE_CARD", AttachmentTypeEnum.SETTLE_CARD.getErrorMsg());
                }
                if (map.size() == 0){
                    throw new LaikeSysException(ErrorCode.REMARK_IS_ILLEGAL);
                }
                request.setRemark(JSONUtils.toJsonString(map));
                request.setStatus(LOLOpenStatus.RETURN);
            }
            BaseResponse response = facade.updateAllianceAccount(request);
            String errorcode = response.getErrCode();
            if (errorcode != null) {
                return ResponseMessage.error(response.getErrMsg());
            }
            return ResponseMessage.ok();
        } catch (LaikeSysException e) {
            log.error("审核异常", e);
            return ResponseMessage.error(e.getDefineCode() + " " + e.getMessage());
        } catch (Throwable e){
            log.error("审核异常", e);
            return ResponseMessage.error(e.toString());
        }
    }

}
