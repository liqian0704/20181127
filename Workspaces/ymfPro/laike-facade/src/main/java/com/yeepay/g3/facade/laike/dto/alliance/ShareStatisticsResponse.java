package com.yeepay.g3.facade.laike.dto.alliance;

import com.yeepay.g3.facade.laike.dto.BaseResponse;

import java.math.BigDecimal;

/**
 * Description: 分润统计
 * Author: wei.li
 * Date: 17/8/1
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ShareStatisticsResponse extends BaseResponse {

    /**
     * 拓展奖
     */
    private BigDecimal expandAward = new BigDecimal(0);

    /**
     * 推广奖
     */
    private BigDecimal spreadAward = new BigDecimal(0);

    /**
     * 鼓励奖
     */
    private BigDecimal encourageAward = new BigDecimal(0);

    /**
     * 良师奖
     */
    private BigDecimal teacherAward = new BigDecimal(0);

    /**
     * 益友奖
     */
    private BigDecimal friendAward = new BigDecimal(0);

    /**
     * 本月收益
     */
    private BigDecimal currentMonth = new BigDecimal(0);

    /**
     * 累积收益
     */
    private BigDecimal totalShare = new BigDecimal(0);

    /**
     * 上月收益
     */
    private BigDecimal preMonth = new BigDecimal(0);

    public BigDecimal getExpandAward() {
        return expandAward;
    }

    public void setExpandAward(BigDecimal expandAward) {
        this.expandAward = expandAward;
    }

    public BigDecimal getSpreadAward() {
        return spreadAward;
    }

    public void setSpreadAward(BigDecimal spreadAward) {
        this.spreadAward = spreadAward;
    }

    public BigDecimal getEncourageAward() {
        return encourageAward;
    }

    public void setEncourageAward(BigDecimal encourageAward) {
        this.encourageAward = encourageAward;
    }

    public BigDecimal getTeacherAward() {
        return teacherAward;
    }

    public void setTeacherAward(BigDecimal teacherAward) {
        this.teacherAward = teacherAward;
    }

    public BigDecimal getFriendAward() {
        return friendAward;
    }

    public void setFriendAward(BigDecimal friendAward) {
        this.friendAward = friendAward;
    }

    public BigDecimal getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(BigDecimal currentMonth) {
        this.currentMonth = currentMonth;
    }

    public BigDecimal getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(BigDecimal totalShare) {
        this.totalShare = totalShare;
    }

    public BigDecimal getPreMonth() {
        return preMonth;
    }

    public void setPreMonth(BigDecimal preMonth) {
        this.preMonth = preMonth;
    }
}
