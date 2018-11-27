package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.util.HiddenCodeUtil;

/**
 * @Description:发送短信验证码请求类
 * @Author: zhaoyu.cui
 * @Date: 16/9/5
 * @Time: 下午5:55
 */
public class SendRegisterSmsRequest extends BaseRequest {

    /**
     * 定位
     */
    private String  location;

	/**
	 * 联盟邀请码
	 */
	private String inviteCode;

    /**
     * imei
     */
    private String imei;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SendRegisterSmsRequest{");
        sb.append("requestNo='").append(requestNo).append('\'');
        sb.append("phoneNo='").append(HiddenCodeUtil.hiddenMobile(phoneNo)).append('\'');
        sb.append("versionId='").append(versionId).append('\'');
        sb.append("imei='").append(imei).append('\'');
        sb.append("inviteCode='").append(inviteCode).append('\'');
        sb.append("location='").append(HiddenCodeUtil.hiddenMobile(location)).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
