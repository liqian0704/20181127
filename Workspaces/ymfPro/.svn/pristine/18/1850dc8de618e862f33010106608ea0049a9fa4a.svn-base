package com.yeepay.g3.core.laike.service;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Description:
 * Author: wei.li
 * Date: 17/6/19
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class OCRServiceTest extends BaseTest {

    @Autowired
    private OCRService ocrService;

    @Test
    public void idCard() throws IOException {
        File file = new File("/Users/yp-tc-m-7116/Desktop/下载二维码.jpeg");//
        BufferedImage bis = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bis, "jpeg", baos);
        byte[] bankCardByte = baos.toByteArray();
        String base64 = Base64.encodeBase64String(bankCardByte);
        System.err.println(new Gson().toJson(ocrService.getIdCardInfo(base64, "test1111")));
    }

    @Test
    public void bankCard() throws IOException {
        File file = new File("/Users/yp-tc-m-7116/Desktop/WechatIMG364.jpeg");
        BufferedImage bis = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bis, "jpeg", baos);
        byte[] bankCardByte = baos.toByteArray();
        String base64 = Base64.encodeBase64String(bankCardByte);
        System.err.println(new Gson().toJson(ocrService.getBankCardInfo(base64, "test1111")));
    }
}
