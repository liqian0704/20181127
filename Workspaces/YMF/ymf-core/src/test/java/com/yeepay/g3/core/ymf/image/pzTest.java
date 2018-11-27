package com.yeepay.g3.core.ymf.image;/**
 * Created by jiwei.lv on 16/10/12.
 */

import com.yeepay.g3.core.ymf.utils.common.ElectronicVoucherUtil;
import com.yeepay.g3.facade.ymf.dto.common.ElectImageDto;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/12
 */
public class pzTest {
    @Test
    public void pzimageTest(){

      String path=  "/Users/yp-tc-m-2803/work/mq/apache-tomcat-7.0.62/temp/";
//        String image="/Users/yp-tc-m-2803/work/bgSign.jpg";
//        File image=new File("/Users/yp-tc-m-2803/work/bgSign.jpg");

        try {
            InputStream imageIO= new FileInputStream("/Users/yp-tc-m-2803/work/bgSign.jpg");
//        System.out.println(path);
        ElectImageDto  dto=new ElectImageDto();
        dto.setCustomerName("测试商户测试商");
        dto.setExternalId("12932093923");
        dto.setCustomerNumber("10040023923");
        dto.setCustomerOrderId("130910348134824");
        dto.setProductName("测试产品名称很");
        dto.setTrxDate("2016-10-02 00:09:23");
        dto.setTradeTime("2016-10-02 00:88:90");
        dto.setTrxAmount("1000.00");
        dto.setStatus("成功");
        dto.setUserName("测试商户你纳斯");
        ElectronicVoucherUtil electronicVoucherUtil= ElectronicVoucherUtil.getInstance();
            electronicVoucherUtil.voucherGeneration(dto, imageIO, path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
