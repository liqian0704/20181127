package com.yeepay.g3.facade.ymf.agent.bean;

/**
 * Created by guangzong.yan on 18/1/9.
 */
public enum BizType {
    POS("POS业务"),
    NET("在线支付"),
    QCB("Q财宝"),
    AIR("航旅业务"),
    NBCARD("非银行卡"),
    SETTLE("委托结算"),
    LK("来客"),
    S0("来客秒到"),
    ALC("联盟"),
    WECHAT("扫码支付");

    private String displayName;

    BizType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Enum[] types() {
        return new Enum[]{ POS, WECHAT, LK,S0};
    }

    /**
     * 根据业务方获取分润类型
     * @return
     */
    public ShareProfitType toShareType() {
        switch (this) {
            case POS: return ShareProfitType.TRX;
            case WECHAT: return ShareProfitType.WECHAT;
            case LK: return ShareProfitType.LIKER;
            case S0: return ShareProfitType.S0;
            default:
                return ShareProfitType.TRX;
        }
    }
}
