package com.yeepay.g3.core.laike.entity;

import com.yeepay.g3.facade.laike.enumtype.AppLogLevelEnum;

/**
 * Description: app错误信息实体
 * Author: wei.li
 * Date: 17/2/9
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class AppErrorMsgEntity extends GenerateIDEntity{


    /**
     * 易宝会员号
     */
    private String memberNo;

    /**
     * 应用版本
     */
    private String appVersionId;

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

    /**
     * 手机标识
     */
    private String imei;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
