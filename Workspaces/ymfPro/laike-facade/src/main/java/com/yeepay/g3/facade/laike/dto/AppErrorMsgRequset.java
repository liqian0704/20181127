package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.AppLogLevelEnum;

/**
 * Description:
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppErrorMsgRequset extends BaseRequest{

    /**
     * 手机厂商
     */
    private String phoneManufacturer;

    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * 请求接口
     */
    private String reqInterface;

    /**
     * 错误日志
     */
    private String logContent;

    /**
     * 手机系统版本
     */
    private String sdk;

    /**
     * 错误级别
     */
    private AppLogLevelEnum logLevel;

    /**
     * 当前页面
     */
    private String currentActivity;


    public String getPhoneManufacturer() {
        return phoneManufacturer;
    }

    public void setPhoneManufacturer(String phoneManufacturer) {
        this.phoneManufacturer = phoneManufacturer;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getReqInterface() {
        return reqInterface;
    }

    public void setReqInterface(String reqInterface) {
        this.reqInterface = reqInterface;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public AppLogLevelEnum getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(AppLogLevelEnum logLevel) {
        this.logLevel = logLevel;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AppErrorMsgRequset{");
        sb.append("memberNo='").append(memberNo).append('\'');
        sb.append("versionId='").append(versionId).append('\'');
        sb.append("phoneManufacturer='").append(phoneManufacturer).append('\'');
        sb.append("phoneModel='").append(phoneModel).append('\'');
        sb.append("reqInterface='").append(reqInterface).append('\'');
        sb.append("logContent='").append(logContent).append('\'');
        sb.append("sdk='").append(sdk).append('\'');
        sb.append("logLevel='").append(logLevel).append('\'');
        sb.append("currentActivity='").append(currentActivity).append('\'');
        sb.append("imei='").append(imei).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
