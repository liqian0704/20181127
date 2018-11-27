package com.yeepay.skb.biz;


import com.yeepay.skb.util.Digest;
import com.yeepay.skb.util.Conts;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * 分润查询接口
 * @author sailfish
 * @create 2017-08-24-上午10:49
 */
public class AccountTradeQueryApiRequester {

    private static String queryUrl = Conts.baseRequestUrl + "/queryTradeInfo.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;// 大商编
    private static String customerNumber = Conts.customerNumber;// 分润方 10040020284
    private static String orderNo = "";// 定单号
    private static String beginDate = "2016-02-27 00:00:00";// 开始时间
    private static String endDate = "2016-03-03 00:00:00";// 结束时间
    private static String pageNo = "1";// 页码
    private static String splitterType = "TRADE_SPLITTER";// 查询类型
    private static String hmacKey = Conts.hmacKey;

    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(queryUrl);

        HttpClient client = new HttpClient();

        try {

            StringBuffer signature = new StringBuffer();
            signature
                    .append(mainCustomerNumber == null ? ""
                            : mainCustomerNumber)
                    .append(customerNumber == null ? "" : customerNumber)
                    .append(splitterType == null ? "" : splitterType)
                    .append(beginDate == null ? "" : beginDate)
                    .append(endDate == null ? "" : endDate)
                    .append(orderNo == null ? "" : orderNo)
                    .append(pageNo == null ? "" : pageNo);

            System.out.println("source===" + signature.toString());
            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            System.out.println("hmac====" + hmac);

            Part[] parts = new AccountTradeQueryPartsBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setCustomerNumber(customerNumber)
                    .setSplitterType(splitterType).setBeginDate(beginDate)
                    .setEndDate(endDate).setOrderNo(orderNo).setPageNo(pageNo)
                    .setHmac(hmac).generateParams();

            postMethod.setRequestEntity(new MultipartRequestEntity(parts,
                    postMethod.getParams()));

            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                String result = postMethod.getResponseBodyAsString();

                System.out.println("===============");
                System.out.println("result" + result);
                System.out.println("===============");

            } else if (status == HttpStatus.SC_MOVED_PERMANENTLY
                    || status == HttpStatus.SC_MOVED_TEMPORARILY) {
                // 从头中取出转向的地址
                Header locationHeader = postMethod
                        .getResponseHeader("location");
                String location = null;
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    System.out
                            .println("The page was redirected to:" + location);
                } else {
                    System.err.println("Location field value is null.");
                }
            } else {
                System.out.println("fail======" + status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
    }
}

class AccountTradeQueryPartsBuilder {
    private List<Part> parts = new ArrayList<Part>();

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public AccountTradeQueryPartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setOrderNo(String orderNo) {
        this.parts.add(new StringPart("orderNo",
                orderNo == null ? "" : orderNo, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setBeginDate(String beginDate) {
        this.parts.add(new StringPart("beginDate", beginDate == null ? ""
                : beginDate, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setEndDate(String endDate) {
        this.parts.add(new StringPart("endDate",
                endDate == null ? "" : endDate, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setPageNo(String pageNo) {
        this.parts.add(new StringPart("pageNo", pageNo == null ? "" : pageNo,
                "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setSplitterType(String splitterType) {
        this.parts.add(new StringPart("splitterType", splitterType == null ? ""
                : splitterType, "UTF-8"));
        return this;
    }

    public AccountTradeQueryPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
}
