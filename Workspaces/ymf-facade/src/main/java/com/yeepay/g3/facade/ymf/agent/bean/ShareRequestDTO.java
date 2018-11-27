package com.yeepay.g3.facade.ymf.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by guangzong.yan on 18/1/9.
 */
public class ShareRequestDTO implements Serializable {
    /**
     *代理商编号
     */
    private String agentCode;

    /**
     * 代理商该月份润金额 汇总
     */
    private BigDecimal shareProfitAmount;


    /**
     * 分润月份 ，代表这是几月的分润
     */
    private Date shareTime;



    /**
     * 分润类型
     */
    private ShareProfitType shareProfitType;


    /**
     * 2016-12-28 18:25:50
     * 业务类型
     */
    private BizType bizType;


    /**
     * 备注
     */
    private String remark;

    /**
     * 结算方式 （DAY 日结  MONTH  月结）
     *
     */
    private String SettleMode;


    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public BigDecimal getShareProfitAmount() {
        return shareProfitAmount;
    }

    public void setShareProfitAmount(BigDecimal shareProfitAmount) {
        this.shareProfitAmount = shareProfitAmount;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public ShareProfitType getShareProfitType() {
        return shareProfitType;
    }

    public void setShareProfitType(ShareProfitType shareProfitType) {
        this.shareProfitType = shareProfitType;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSettleMode() {
        return SettleMode;
    }

    public void setSettleMode(String settleMode) {
        SettleMode = settleMode;
    }

    @Override
    public String toString() {
        return "ShareRequestDTO{" +
                "agentCode='" + agentCode + '\'' +
                ", shareProfitAmount=" + shareProfitAmount +
                ", shareTime=" + shareTime +
                ", shareProfitType=" + shareProfitType +
                ", bizType=" + bizType +
                ", remark='" + remark + '\'' +
                ", SettleMode='" + SettleMode + '\'' +
                '}';
    }
}
