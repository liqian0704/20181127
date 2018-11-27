package com.yeepay.g3.ymf.boss.controller.common;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.support.bean.LocalSimpleCache;
import com.yeepay.g3.facade.ymf.dto.common.ElectImageDto;
import com.yeepay.g3.utils.common.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Title: 日志
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author fei.lu on 16/8/25.
 */
@Lazy
@Controller
@RequestMapping("download")
public class DownLoadPicController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/downloadPic")
    public void downloadFile(@RequestParam(value="ftpUrl") String ftpUrl, HttpServletResponse response) throws IOException {
        if (!StringUtils.isEmpty(ftpUrl)) {
            String name = ftpUrl.substring(ftpUrl.lastIndexOf("/")+1,ftpUrl.length());
            response.setHeader("Content-disposition", "attachment; filename="+ new String(name.getBytes("utf-8"), "ISO8859-1"));
            URL url = new URL (ftpUrl);
            //打开网络输入流
            DataInputStream dis = new DataInputStream(url.openStream());
            BufferedOutputStream fos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = dis.read(buff, 0, buff.length))) {
                fos.write(buff, 0, bytesRead);
            }
            dis.close();
            fos.close();
        }

    }

    @RequestMapping(value = "/downloadProof")
    public ResponseEntity<byte[]> downloadProof(String orderId, HttpSession session) throws Exception{
        if(!StringUtils.isEmpty(orderId)){
            String path=System.getProperty("java.io.tmpdir");
//            String image=this.getClass().getClassLoader().getResource("/").getPath()+"image/bgSign.jpg";
            String image=null;
            String fileName=orderService.createProofPic(orderId,image);
            File file = new File(path+"/"+fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(file.length());
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
                    HttpStatus.OK);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(MediaType.TEXT_HTML_VALUE));
            return new ResponseEntity<byte[]>("文件名称获取失败!".getBytes(),headers, HttpStatus.OK);
        }
    }
}
