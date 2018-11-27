/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.facade.ymf.facade.laike;

import java.io.Serializable;

/**
 * 类名称: QrCodeInfo <br>
 * 类描述: 二维码信息DTO<br>
 *
 * @author: xxxx.xxx
 * @since: 17/8/30 下午4:07
 * @version: 1.0.0
 */

public class QrCodeInfoDTO  implements Serializable {
    private static final long serialVersionUID = -3790167611743251897L;
    /**
     * 二维码内容,用此信息生成二维码
     */
    private String qrCodeUrl;

    private String qrID;
    /**
     * 网点名称
     */
    private String shopName;
    /**
     * 网点号
     */
    private String shopNumber;

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getQrID() {
        return qrID;
    }

    public void setQrID(String qrID) {
        this.qrID = qrID;
    }
}