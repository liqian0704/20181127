package com.yeepay.g3.common.laike.utils;

import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.resource.ResourceLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 文件提取工具
 * Author: jiawen.huang
 * Date: 15/4/23
 * Time: 12:14
 * Version: 1.0
 * Copyright © 2015 YeePay.com All rights reserved.
 */
public class ResourcesUtils extends ResourceLoader {

	/**
	 * 根据名称获取文件绝对路径
	 *
	 * @param resourceName
	 * @return
	 */
	public static String getResourcePath(String resourceName) {
		return Thread.currentThread().getContextClassLoader().getResource(resourceName).getPath();
	}

	/**
	 * 根据全路径拿到文件
	 *
	 * @param filePath
	 * @return
	 */
	public static InputStream getResource(String filePath) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
	}


	/**
	 * 加载资源文件内容（根据内容大小分配内存，不可用于大文件读取）
	 *
	 * @param basePath
	 * @param resourceName
	 * @param suffix
	 * @return
	 */
	public static String readResource(String basePath, String resourceName, String suffix) {
		StringBuffer sb = new StringBuffer();
		if ("".equals(basePath)) {
			sb.append(resourceName + suffix);
		} else {
			sb.append(basePath + File.separator + resourceName + suffix);
		}
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(sb.toString());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[inputStream.available()];
			while (inputStream.read(buffer) != -1) {
				outputStream.write(buffer);
			}
			return outputStream.toString("UTF-8");
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.RESOURCE_NOT_FOUND_EXCEPTION, e);
		}
	}

	/**
	 * 替换文件内容中的变量并且输出内容
	 *
	 * @param baseResourcePath 要替换的文件
	 * @param data             替换内容
	 * @return 替换后的文件内容全文
	 */
	public static String replaceResource(String baseResourcePath, Map<String, String> data) {
		StringBuffer stringBuffer = new StringBuffer();
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = pattern.matcher(baseResourcePath);
		while (matcher.find()) {
			String name = matcher.group(1);
			String value = String.valueOf(data.get(name));
			if (value == null) {
				value = "";
			}
			value = value.replaceAll("\\$", "\\\\\\$");
			matcher.appendReplacement(stringBuffer, value);
		}
		matcher.appendTail(stringBuffer);
		return stringBuffer.toString();
	}
}
