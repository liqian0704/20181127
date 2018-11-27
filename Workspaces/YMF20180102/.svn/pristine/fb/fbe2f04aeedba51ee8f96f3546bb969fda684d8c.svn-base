package com.yeepay.g3.core.ymf.utils.web;

import com.yeepay.g3.core.ymf.vo.material.BatchTermRequest;
import com.yeepay.g3.facade.ymf.dto.TermDTO;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/14.
 */
public class POIHelperTest {

    @Test
    public void read() throws Exception {

    }

    @Test
    public void write() throws Exception {
        List<String> titleList = new LinkedList<String>();
        titleList.add("title1");
        titleList.add("title2");
        titleList.add("title3");
        titleList.add("title4");
        titleList.add("title5");

        List<BatchTermRequest> beanList = new LinkedList<BatchTermRequest>();
        BatchTermRequest request = new BatchTermRequest();
        request.setIndex(1);
        request.setCustomerNumber("123123");
        request.setSnSerial("123123sdfdsf");
        beanList.add(request);

        FileOutputStream fos = new FileOutputStream(new File("test.xlsx"));
        POIHelper.write(titleList, beanList, fos);
    }

    @Test
    public void writeNormal() throws Exception  {
        List<String> titleList = new LinkedList<String>();
        titleList.add("title1");
        titleList.add("title2");
        titleList.add("title3");
        titleList.add("title4");
        titleList.add("title5");

        List<List<Object>> beanList = new LinkedList<List<Object>>();
        List<Object> request = new LinkedList<Object>();
        request.add("123sdfdsf");
        request.add("sdfdsfs");
        request.add("sdfsdf");
        beanList.add(request);

        FileOutputStream fos = new FileOutputStream(new File("test2.xlsx"));
        POIHelper.writeNormal(titleList, beanList, fos);
    }
}