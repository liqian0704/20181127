package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

/**
 * Created by dongxulu on 17/2/20.
 */
public class TradeRiskParamDTO extends BaseDTO {
    /**
     * 交易硬件终端类型 IMEI、MAC、UUID、OTHER
     */
    private String terminalType;
    /**
     * 交易硬件终端标识码
     */
    private String terminalId;
    /**
     * 交易经度
     */
    private String longitude;
    /**
     * 交易纬度
     */
    private String latitude;
    /**
     * 交易IP
     */
    private String userIp;
    /**
     * 操作系统 IOS、Android、Windows、OTHER
     */
    private String os;
    /**
     * 系统版本
     */
    private String osVersion;
    /**
     * 交易手机类型 Iphone 、三星、华为、小米、魅族、Oppo、Vivo、中兴、乐视、努比亚、联想、索尼、其他
     */
    private String transPhoneType;
    /**
     * 是否同人交易 0：同人交易 / 1：非同人交易
     */
    private String isTradeOneself;
    /**
     * 商户类型 自然人、个体、企业
     */
    private String merchantType;

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getTransPhoneType() {
        return transPhoneType;
    }

    public void setTransPhoneType(String transPhoneType) {
        this.transPhoneType = transPhoneType;
    }

    public String getIsTradeOneself() {
        return isTradeOneself;
    }

    public void setIsTradeOneself(String isTradeOneself) {
        this.isTradeOneself = isTradeOneself;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
