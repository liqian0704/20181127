package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.UploadFileService;
import com.yeepay.g3.facade.laike.enumtype.FileTypeEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.utils.ftp.FtpFileStoreHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Description: 文件上传服务实现
 * Author: jiawen.huang
 * Date: 17/2/17
 * Time: 11:30
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class UploadFileServiceImpl extends AbstractService implements UploadFileService {

	private static Logger LOGGER = LoggerFactory.getLogger(UploadFileServiceImpl.class);

	@Override
	public String upload(InputStream inputStream, FileTypeEnum fileTypeEnum) {
		try {
			FtpFileStoreHelper helper = FtpFileStoreHelper.getHelper((String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_FTP_PROPERTIES_NAME));
			String filePath = helper.upload(inputStream, fileTypeEnum.getValue());
			LOGGER.info("[laike_sys] - [返回] - [UploadFileServiceImpl.upload] - [" + filePath + "]");
			return filePath;
		} catch (LaikeSysException e) {
			throw e;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.UPLOAD_FILE_FTP_EXCEPTION, e);
		}
	}

	@Override
	public String upload(File file, FileTypeEnum fileTypeEnum) {
		try {
			return upload(new FileInputStream(file), fileTypeEnum);
		} catch (FileNotFoundException e) {
			throw new LaikeSysException(ErrorCode.UPLOAD_FILE_NOTFOUND_ERROR, e);
		}
	}
}
