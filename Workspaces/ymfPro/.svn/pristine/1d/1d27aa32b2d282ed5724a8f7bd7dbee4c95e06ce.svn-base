package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.laike.enumtype.FileTypeEnum;

import java.io.File;
import java.io.InputStream;

/**
 * Description: 文件上传服务
 * Author: jiawen.huang
 * Date: 17/2/17
 * Time: 11:28
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface UploadFileService {

	/**
	 * 上传
	 *
	 * @param inputStream
	 * @param fileTypeEnum 上传后缀
	 * @return 文件下载路径
	 */
	String upload(InputStream inputStream, FileTypeEnum fileTypeEnum);

	/**
	 * 上传
	 *
	 * @param file
	 * @param fileTypeEnum 上传后缀
	 * @return 文件下载路径
	 */
	String upload(File file, FileTypeEnum fileTypeEnum);
}
