package com.yeepay.g3.core.ymf.utils.security;

import com.yeepay.g3.utils.common.StringUtils;

/**
 * 
 * 字符串掩码处理工具类
 * 
 * @author meng.wang-2@yeepay.com
 *
 */
public class HideCodeUtil {

	/**
	 * 字符串掩码处理
	 * <p/>
	 * 留前startBits位后endBits 替换中间
	 * 
	 * @param str
	 *            待处理字符串
	 * @param startBits
	 *            前startBits不处理
	 * @param endBits
	 *            后endBits不处理
	 * @param maskChar
	 *            掩码字符
	 * @return
	 */
	public static String encryptStayAround(String str, int startBits,
			int endBits, char maskChar) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		String regEx = "(.{" + startBits + "})(.+)(.{" + endBits + "})";
		int maskBits = str.length() - startBits - endBits;
		StringBuilder maskStringBuilder = new StringBuilder();
		for (int i = 0; i < maskBits; i++) {
			maskStringBuilder.append(maskChar);
		}
		return str
				.replaceAll(regEx, "$1" + maskStringBuilder.toString() + "$3");

	}

	/**
	 * 字符串掩码处理
	 * <p/>
	 * 处理前startBits位后endBits 保留中间
	 * 
	 * @param str
	 *            待处理字符串
	 * @param startBits
	 *            前startBits处理
	 * @param endBits
	 *            后endBits处理
	 * @param maskChar
	 *            掩码字符
	 * @return
	 */
	public static String encryptAround(String str, int startBits, int endBits,
			char maskChar) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		String regEx = "(.{" + startBits + "})(.+)(.{" + endBits + "})";

		StringBuilder startMaskStringBuilder = new StringBuilder();
		for (int i = 0; i < startBits; i++) {
			startMaskStringBuilder.append(maskChar);
		}

		StringBuilder endMaskStringBuilder = new StringBuilder();
		for (int i = 0; i < endBits; i++) {
			endMaskStringBuilder.append(maskChar);
		}
		return str.replaceAll(regEx, startMaskStringBuilder.toString() + "$2"
				+ endMaskStringBuilder.toString());
	}

	/**
	 * 字符串掩码处理
	 * <p/>
	 * 替换前startBits位后的middleBits位
	 * 
	 * @param str
	 *            待替换字符串
	 * @param startBits
	 *            前startBits不处理
	 * @param middleBits
	 *            处理中间middleBits位
	 * @param maskChar
	 * @return
	 */
	public static String encrypt(String str, int startBits, int middleBits,
			char maskChar) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		String regEx = "(.{" + startBits + "})(.{" + middleBits + "})";
		int maskBits = middleBits;
		StringBuilder maskStringBuilder = new StringBuilder();
		for (int i = 0; i < maskBits; i++) {
			maskStringBuilder.append(maskChar);
		}
		return str.replaceAll(regEx, "$1" + maskStringBuilder.toString());

	}

	/**
	 * str除了第startSeq、endSeq位，其他替换成maskChar
	 * 
	 * @param str
	 * @param startSeq
	 * @param endSeq
	 * @param maskChar
	 * @return
	 */
	public static String encryptSeq(String str, int startSeq, int endSeq,
			char maskChar) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (i == startSeq || i == endSeq) {
				sBuilder.append(str.charAt(i));
			} else {
				sBuilder.append(maskChar);
			}

		}

		return sBuilder.toString();

	}

	public static void main(String[] args) {
		System.out.println(HideCodeUtil.encrypt("", 3, 2, '*'));
		System.out.println(HideCodeUtil.encryptAround("12", 3, 3, '*'));
		System.out.println(HideCodeUtil.encryptStayAround("12", 3, 3, '*'));
		System.out.println(HideCodeUtil.encryptSeq("as赵", 0, 2, '*'));

	}
}
