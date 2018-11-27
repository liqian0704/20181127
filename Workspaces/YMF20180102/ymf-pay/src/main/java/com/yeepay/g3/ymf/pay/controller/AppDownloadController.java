package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.facade.laike.dto.AppVersionResponse;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;
import com.yeepay.g3.facade.laike.facade.AppVersionFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 下载控制器
 * Author: wei.li,jiawen.huang
 * Date: 17/4/6
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Controller
@RequestMapping("/download")
public class AppDownloadController extends BaseController{

    /**
     * 代理版下载
     *
     * @param model
     * @return
     */
    @RequestMapping("agent.html")
    public String agent(Model model) {
        AppVersionFacade appVersionFacade = RemoteServiceFactory.getService(AppVersionFacade.class);
        AppVersionResponse android = appVersionFacade.findNewByRoleAndPlatform(VersionPlatform.ANDROID, AppRoleEnum.WORKER);
        AppVersionResponse ios = appVersionFacade.findNewByRoleAndPlatform(VersionPlatform.IOS, AppRoleEnum.WORKER);
        model.addAttribute("androidUrl", android.getFileUrl());
        model.addAttribute("iosUrl", ios.getFileUrl());
        return "app/agent_download";
    }
}
