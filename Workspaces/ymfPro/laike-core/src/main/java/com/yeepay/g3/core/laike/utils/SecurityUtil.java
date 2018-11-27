package com.yeepay.g3.core.laike.utils;

import com.apay.util.encrypt.AES;
import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: 加解密安全工具
 * Author: jiawen.huang
 * Date: 16/12/16
 * Time: 17:42
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class SecurityUtil {

	public static String DEFAULT_ENCODE = "UTF-8";

	private static String AES_KEY = (String) ConfigUtils.getSysConfigParam(ConfigEnum.AES_KEY);

	private static String AES_KEY_SUFFIX = (String) ConfigUtils.getSysConfigParam(ConfigEnum.AES_KEY_SUFFIX);

	/**
	 * 加密一级信息（一般敏感信息,联系电话等）
	 *
	 * @param data
	 * @return
	 */
	public static String encryptL1Info(String data) {
		return AES.encryptToBase64(data, AES.decryptFromBase64(ConstantUtil.AES_KEY1 + AES_KEY_SUFFIX, AES_KEY));
	}

	/**
	 * 解密一级信息
	 *
	 * @param data
	 * @return
	 */
	public static String decryptL1Info(String data) {
		return AES.decryptFromBase64(data, AES.decryptFromBase64(ConstantUtil.AES_KEY1 + AES_KEY_SUFFIX, AES_KEY));
	}

	/**
	 * 加密二级信息（身份信息等）
	 *
	 * @param data
	 * @return
	 */
	public static String encryptL2Info(String data) {
		return AES.encryptToBase64(data, AES.decryptFromBase64(ConstantUtil.AES_KEY2 + AES_KEY_SUFFIX, AES_KEY));
	}

	/**
	 * 解密二级信息
	 *
	 * @param data
	 * @return
	 */
	public static String decryptL2Info(String data) {
		return AES.decryptFromBase64(data, AES.decryptFromBase64(ConstantUtil.AES_KEY2 + AES_KEY_SUFFIX, AES_KEY));
	}


	/**
	 * SHA签名（客户中心加密需求）
	 *
	 * @param data
	 * @param encoding
	 * @return
	 */
	public static String digest2G(String data, String encoding) {
		data = data.trim();
		byte value[];
		try {
			value = data.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = data.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return toHex(md.digest(value));
	}


	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}
}