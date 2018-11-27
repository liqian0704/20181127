package com.yeepay.g3.core.laike.biz;

import com.google.gson.Gson;
import com.yeepay.g3.core.laike.BaseTest;
import com.yeepay.g3.facade.laike.dto.UploadImgRequest;
import com.yeepay.g3.facade.laike.enumtype.AttachmentTypeEnum;
import com.yeepay.g3.utils.common.DateUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Description:
 * Author: wei.li
 * Date: 17/6/22
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class UploadImgTest extends BaseTest {

    @Autowired
    private AllianceAccountBiz allianceAccountBiz;

    @Rollback(false)
    @Test
    public void upload() throws IOException, ParseException {
        UploadImgRequest uploadImgRequest = new UploadImgRequest();
        File file = new File("/Users/yp-tc-m-7116/Desktop/下载二维码.jpeg");
        BufferedImage bis = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bis, "jpeg", baos);
        byte[] bankCardByte = baos.toByteArray();
        String base64 = Base64.encodeBase64String(bankCardByte);
        uploadImgRequest.setMemberNo("212468327115");
        uploadImgRequest.setImageBase64(base64);
        uploadImgRequest.setAttachmentTypeEnum(AttachmentTypeEnum.SETTLE_CARD);
        System.err.println(DateUtils.parseDate("2111-11-11", DateUtils.DATE_FORMAT_DATEONLY));
        System.err.println(new Gson().toJson(allianceAccountBiz.uploadImage(uploadImgRequest)));
    }

    @Test
    public void testAddList() {
        ArrayList list = new ArrayList();
        list.add("1");
        ArrayList list1 = new ArrayList();
        list1.addAll(list);
    }
}
