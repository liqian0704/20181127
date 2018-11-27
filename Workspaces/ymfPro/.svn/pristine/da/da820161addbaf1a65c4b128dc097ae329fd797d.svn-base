package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 文件类型
 * Author: jiawen.huang
 * Date: 16/11/30
 * Time: 16:44
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public enum FileTypeEnum {

	IPA("ipa", ".ipa"),
	APK("apk", ".apk"),
	PLIST("plist", ".plist"),
	TXT("txt", ".txt"),
	CSV("csv", ".csv"),
	JPG("jpg", ".jpg"),
    PNG("png", ".png"),
    JPEG("jpeg", ".jpeg");

	private static final Map<String, FileTypeEnum> VALUE_MAP = new HashMap<String, FileTypeEnum>();

	private String value;
	private String displayName;

	static {
		for (FileTypeEnum item : FileTypeEnum.values()) {
			VALUE_MAP.put(item.value, item);
		}
	}

	FileTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static FileTypeEnum parse(String value) {
		return VALUE_MAP.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, FileTypeEnum> getValueMap() {
		return VALUE_MAP;
	}
}
