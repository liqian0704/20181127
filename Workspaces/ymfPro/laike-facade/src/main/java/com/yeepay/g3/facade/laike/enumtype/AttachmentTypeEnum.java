package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 附件类型
 * Author: jiawen.huang
 * Date: 2017/6/15
 * Time: 15:42
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public enum AttachmentTypeEnum {

	ID_CARD_FRONT("ID_CARD_FRONT", "身份证正面", "身份证信息有误"),
	ID_CARD_BACK("ID_CARD_BACK", "身份证反面", ""),
    SETTLE_CARD("SETTLE_CARD", "结算卡", "结算卡信息有误"),
    HARD_ID_CARD("HARD_ID_CARD", "手持身份证", ""),
    SIGNATURE("SIGNATURE", "协议签名", "");

	private static final Map<String, AttachmentTypeEnum> VALUE_MAP = new HashMap<String, AttachmentTypeEnum>();

	private String value;
	private String displayName;
    private String errorMsg;

	static {
		for (AttachmentTypeEnum item : AttachmentTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

    AttachmentTypeEnum(String value, String displayName, String errorMsg) {
        this.value = value;
        this.displayName = displayName;
        this.errorMsg = errorMsg;
    }

	public static AttachmentTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

    public String getErrorMsg() {
        return errorMsg;
    }

	public static Map<String, AttachmentTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
