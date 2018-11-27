package com.yeepay.g3.facade.laike.dto.boss;

import com.yeepay.g3.facade.laike.enumtype.LOLOpenStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description: 更新联盟开户单
 * Author: wei.li
 * Date: 17/6/25
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UpdateAllianceAccRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入网单号
     */
    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态
     */
    private LOLOpenStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LOLOpenStatus getStatus() {
        return status;
    }

    public void setStatus(LOLOpenStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
