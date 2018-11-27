package com.yeepay.g3.ymf.boss.utils.common;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Title: 下载工具类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/14.
 */
public class DownloadUtils {

    private static final Logger log = LoggerFactory.getLogger(DownloadUtils.class);

    /**
     * 下载文件
     * @param response http
     * @param in 输入流
     * @param fileName 文件名称
     * @param mediaType 文件类型
     */
    public static void download(HttpServletResponse response, InputStream in, String fileName, MediaType mediaType) {
        try {
            response.setContentType(mediaType.getType());
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("下载文件失败");
            response.setContentType("text/plain");
            try {
                response.getWriter().write("下载文件失败:" + fileName + ", " + e.getMessage());
            } catch (IOException e1) {

            }
        }
    }

    /**
     * 下载文件
     * @param response http
     * @param baos 二进制输出流
     * @param fileName 文件名称
     * @param mediaType 文件类型
     */
    public static void download(HttpServletResponse response, ByteArrayOutputStream baos, String fileName, MediaType mediaType) {
        try {
            response.setContentType(mediaType.getType());
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            IOUtils.write(baos.toByteArray(), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("下载文件失败");
            response.setContentType("text/plain");
            try {
                response.getWriter().write("下载文件失败:" + fileName + ", " + e.getMessage());
            } catch (IOException e1) {

            }
        }
    }

}
