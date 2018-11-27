package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * Description: 商家扫码请求
 * Author: jiawen.huang
 * Date: 17/3/16
 * Time: 18:02
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class PassivePayRequest extends BaseRequest {

	/**
	 * 订单金额
	 */
	private Amount orderAmount;

	/**
	 * 打赏
	 */
	private Amount gratuityAmount;

	/**
	 * 收款码
	 */
	private String userPayCode;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 设备号
	 */
	private String deviceSn;

	public Amount getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Amount orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Amount getGratuityAmount() {
		return gratuityAmount;
	}

	public void setGratuityAmount(Amount gratuityAmount) {
		this.gratuityAmount = gratuityAmount;
	}

	public String getUserPayCode() {
		return userPayCode;
	}

	public void setUserPayCode(String userPayCode) {
		this.userPayCode = userPayCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

    public String toString() {
        StringBuffer sb = new StringBuffer("PassivePayRequest{");
        sb.append("memberNo='").append(memberNo).append('\'');
        sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
        sb.append("orderAmount='").append(orderAmount).append('\'');
        sb.append("gratuityAmount='").append(gratuityAmount).append('\'');
        sb.append("userPayCode='").append(userPayCode).append('\'');
        sb.append("ip='").append(ip).append('\'');
        sb.append("deviceSn='").append(deviceSn).append('\'');
        sb.append("imei='").append(imei).append('\'');
        sb.append("versionId='").append(versionId).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
