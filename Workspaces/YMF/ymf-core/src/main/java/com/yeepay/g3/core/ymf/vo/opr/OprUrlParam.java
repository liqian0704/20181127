package com.yeepay.g3.core.ymf.vo.opr;

import com.yeepay.g3.utils.common.json.JSONUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @Description: 订单处理器，支付url参数
 * @Author: xiaobin.liu
 * @Date: 17/2/17
 * @Time: 下午4:18
 */
public class OprUrlParam {
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 订单token
     */
    private String token;
    /**
     * 订单时间戳,1361324896， 精确到秒
     */
    private String timestamp;
    /**
     * 设置该参数后，直接调用支付工具，不显示易宝移动收银台页面。
     * 枚举值：
     * WX： WX支付（暂不支持）
     * ZFB： ZFB支付
     * NC： 易宝一键支付
     * CFL:  分期支付
     * 银行通道编码表中的编码：指定的企业或个人网银
     */
    private String directPayType;
    /**
     * 卡种
     */
    private String cardType;
    /**
     * 用户标识
     */
    private String userNo;
    /**
     * 用户表示类型
     */
    private String userType;
    /**
     * 业务类型，必须是LK
     */
    private String bizType = "LK";
    /**
     * 签名
     */
    private String sign;

    /**
     * 扫码类型
     * 枚举值：
     * WX： WX支付（暂不支持）
     * ZFB：ZFB支付
     * YL： 银联支付
     */
    private String codeType;

    /**
     * 授权码
     */
    private String code;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 设备号
     */
    private String deviceSn;
    /**
     * 扩展字段，{"appId":"wx9e13bd68a8f1921e","openId":"zml_wechat”,"clientId":"*****"}
     */
    private String ext;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirectPayType() {
        return directPayType;
    }

    public void setDirectPayType(String directPayType) {
        this.directPayType = directPayType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return JSONUtils.toJsonString(this);
    }

    /**
     * 转换成http参数串
     * @return
     */
    public String toHttpParamString() {

        String paramStr = "?merchantNo=" + merchantNo + "&token=" + token;
        if (StringUtils.isNotBlank(timestamp)) {
            paramStr += "&timestamp=" + timestamp;
        }
        if (StringUtils.isNotBlank(directPayType)) {
            paramStr += "&directPayType=" + directPayType;
        }
        if (StringUtils.isNotBlank(cardType)) {
            paramStr += "&cardType=" + cardType;
        }
        if (StringUtils.isNotBlank(userNo)) {
            paramStr += "&userNo=" + userNo;
        }
        if (StringUtils.isNotBlank(userType)) {
            paramStr += "&userType=" + userType;
        }
        if (StringUtils.isNotBlank(sign)) {
            paramStr += "&sign=" + sign;
        }
        if (StringUtils.isNotBlank(codeType)) {
            paramStr += "&codeType=" + codeType;
        }
        if (StringUtils.isNotBlank(code)) {
            paramStr += "&code=" + code;
        }
        if (StringUtils.isNotBlank(storeCode)) {
            paramStr += "&storeCode=" + storeCode;
        }
        if (StringUtils.isNotBlank(deviceSn)) {
            paramStr += "&deviceSn=" + deviceSn;
        }
        paramStr += "&bizType=" + bizType;
        if (StringUtils.isNotBlank(ext)) {
            paramStr += "&ext=" + ext;
        }
        return paramStr;
    }
}
