package com.yeepay.g3.ymf.boss.controller.common;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryResult;
import com.yeepay.g3.utils.query.QueryService;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yeepay.g3.ymf.boss.utils.FileManageUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 下载基础控制器
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/14.
 */
public abstract class DownloadController extends ValidateController {

    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    protected QueryService ymfDownloadQueryService;

    /**
     * 下载文件
     * @param request 请求
     * @param session session
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    protected ResponseEntity<byte[]> downdload(HttpServletRequest request, HttpSession session) throws IOException {
        QueryParam queryParam = prepareQueryParam(request, session);

        String queryKey = request.getParameter("queryId");
        QueryResult result = ymfDownloadQueryService.query(queryKey, queryParam);
        if(null!= result){
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getData();
            if (null!=list && list.size()>0){
                String fileName = (String) list.get(0).get("fileName");
                log.info(fileName);
                if(null!=fileName && "error".equals(fileName)){
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
                    return new ResponseEntity<byte[]>("文件名称获取失败!".getBytes(),headers, HttpStatus.OK);
                }else if(null!=fileName && !"error".equals(fileName)){
                    String filepath= FileManageUtils.getFilePath();
                    File file = new File(filepath+fileName);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                    String attachementName = fileName;
                    String fileName1 = (String) request.getAttribute("fileName1");
                    if (StringUtils.isNotBlank(fileName1)) {
                        //自定义名称
                        attachementName = new String(fileName1.getBytes("utf-8"),
                                "ISO8859-1");
                        int i = fileName.lastIndexOf(".");
                        attachementName += fileName.substring(i);
                    }
                    headers.setContentDispositionFormData("attachment", attachementName);
                    headers.setContentLength(file.length());
                    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
                            HttpStatus.OK);
                }else{
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
                    return new ResponseEntity<byte[]>("文件名称获取失败!".getBytes(),headers,HttpStatus.OK);
                }
            } else{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
                return new ResponseEntity<byte[]>("文件生成失败".getBytes(),headers,HttpStatus.OK);
            }
        } else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
            return new ResponseEntity<byte[]>("文件生成失败".getBytes(),headers,HttpStatus.OK);
        }
    }

    /**
     * 下载zip文件
     *
     * @param request 请求
     * @param session session
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    protected ResponseEntity<byte[]> downdloadZip(HttpServletRequest request, HttpSession session)
            throws IOException {
        try {
            QueryParam queryParam = prepareQueryParam(request, session);
            String fileName1 = (String) request.getAttribute("fileName1");
            String queryKey = request.getParameter("queryId");
            QueryResult result = ymfDownloadQueryService.query(queryKey, queryParam);

            if (null == result ) {
                throw new IllegalArgumentException();
            }
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getData();
            if (null != list && list.size() > 0) {
                String fileName = (String) list.get(0).get("fileName");
                log.info(fileName);
                if (null != fileName && !"error".equals(fileName)) {
                    String filepath = FileManageUtils.getFilePath();
                    File file = new File(filepath + fileName);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                    String attachementName = fileName;
                    if (StringUtils.isNotBlank(fileName1)) {
                        //自定义名称
                        attachementName = new String(fileName1.getBytes("utf-8"),
                                "ISO8859-1");
                        int i = fileName.lastIndexOf(".");
                        attachementName += fileName.substring(i);
                    }
                    headers.setContentDispositionFormData("attachment", attachementName);
                    headers.setContentLength(file.length());
                    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
                            HttpStatus.OK);
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
            return new ResponseEntity<byte[]>("文件生成失败".getBytes(), headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("生成文件异常：",e);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
            return new ResponseEntity<byte[]>("文件生成异常".getBytes(), headers, HttpStatus.OK);
        }
    }

    /**
     * 获取请求参数
     * @param request
     * @param session
     */
    private QueryParam prepareQueryParam(HttpServletRequest request, HttpSession session) {
        QueryParam queryParam = new QueryParam();
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String[]> param = request.getParameterMap();//拼装参数
        for (Map.Entry<String, String[]> entry : param.entrySet()) {
            String key = entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof Object[]) {
                map.put(key, ((Object[]) obj)[0].toString());
            }
        }
        map.put("operator", getUser(session));

        queryParam.setParams(map);
        return queryParam;
    }
}
