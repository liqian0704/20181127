package com.yeepay.g3.common.laike.utils;

import com.yeepay.g3.facade.laike.enumtype.FileTypeEnum;
import com.yeepay.g3.utils.report.Report;
import com.yeepay.g3.utils.report.ReportFactory;
import com.yeepay.g3.utils.report.ReportParams;

import java.io.*;
import java.util.List;

/**
 * Description: 报表工具
 * Author: jiawen.huang
 * Date: 17/5/24
 * Time: 18:25
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public class ReportUtil {

	//模板所属目录
	public static String TEMPLATE_BASE_PATH = "template" + File.separator;

	/**
	 * 导出csv，会写文件到默认路径（你在自己机器还要配）下，不建议
	 *
	 * @param templateName
	 * @param targetName
	 * @param data
	 * @return
	 */
	public static String reportCSV(String templateName, String targetName, List data) throws UnsupportedEncodingException {
		Report report = ReportFactory.createDefaultCSVReport();
		ReportParams params = initCSVReportParams(templateName, targetName, data);
		return report.exportAsFile(params);
	}

	/**
	 * 导出csv流
	 *
	 * @param templateName
	 * @param targetName
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static ByteArrayOutputStream reportCSVStream(String templateName, String targetName, List data) throws UnsupportedEncodingException {
		Report report = ReportFactory.createDefaultCSVReport();
		ReportParams params = initCSVReportParams(templateName, targetName, data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		report.exportAsStream(params, outputStream);
		return outputStream;
	}

	private static ReportParams initCSVReportParams(String templateName, String targetName, List data) throws UnsupportedEncodingException {
		ReportParams params = new ReportParams();
		params.setFormatRow(2);
		params.setTitleRow(1);
		params.setData(data);
		String targetFileName = templateName + FileTypeEnum.TXT.getDisplayName();
		String targetFileFullPath = TEMPLATE_BASE_PATH + targetFileName;
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(ResourcesUtils.loadResource(targetFileFullPath), "utf-8"));
		params.setTemplateReader(bufferedReader);
		params.setTargetFileName(targetFileName);
		return params;
	}
}
