package com.yeepay.g3.core.laike.entity;

import com.google.gson.annotations.SerializedName;
import com.yeepay.g3.facade.laike.enumtype.BizTypeEnum;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Description: 用户操作记录
 * Author: wei.li
 * Date: 17/5/23
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OperateRecordEntity extends PersistenceEntity {

    /**
     * 会员号
     */
    private String memberNo;

    /**
     * 业务类型
     */
    private BizTypeEnum bizType;

    /**
     * 业务参数
     */
    @SerializedName("biz_param")
    private String bizParam;

    /**
     * 次数
     */
    private int count = 1;

    /**
     * 联系人
     */
    @SerializedName("link_name")
    private String linkName;

    /**
     * 联系电话
     */
    @SerializedName("link_phone")
    private String linkPhone;

    /**
     * 省
     */
    @SerializedName("province_name")
    private String provinceName;

    /**
     * 市
     */
    @SerializedName("city_name")
    private String cityName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 增加次数
     */
    public void addCount() {
        count++;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public BizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(BizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public String getBizParam() {
        return bizParam;
    }

    public void setBizParam(String bizParam) {
        this.bizParam = bizParam;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

