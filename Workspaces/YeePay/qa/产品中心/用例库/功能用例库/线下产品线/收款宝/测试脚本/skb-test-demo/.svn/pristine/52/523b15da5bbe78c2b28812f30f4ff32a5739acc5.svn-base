package com.yeepay.skb.biz;


import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.util.ArrayList;
import java.util.List;

/**
 * 代理商返佣转账接口
 * @author sailfish
 * @create 2017-08-24-上午11:01
 */
public class TransferToCustomerRequester {

    private static String url = Conts.baseRequestUrl + "/transferToCustomer.action";

    private static String customerNumber = Conts.customerNumber;
    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String transAmount = "5";
    private static String requestId = Long.toString(System.currentTimeMillis());
    private static String remark = "";
    private static String hmacKey = Conts.hmacKey;

    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();

        try {
            Part[] parts = new TransferToCustomerPartsBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setCustomerNumber(customerNumber).setRequestId(requestId)
                    .setTransAmount(transAmount).setHmac(hmacSign())
                    .setRemark(remark).generateParams();

            // System.out.println("requestId:" + requestId);

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

    private static String hmacSign() {
        StringBuilder hmacStr = new StringBuilder();
        hmacStr.append(mainCustomerNumber == null ? "" : mainCustomerNumber)
                .append(customerNumber == null ? "" : customerNumber)
                .append(requestId == null ? "" : requestId)
                .append(transAmount == null ? "" : transAmount)
                .append(remark == null ? "" : remark);

        System.out.println("===============");
        System.out.println("hmacStr.toString()=" + hmacStr.toString());
        System.out.println("===============");

        String hmac = Digest.hmacSign(hmacStr.toString(), hmacKey);

        System.out.println("===============");
        System.out.println("hmac=" + hmac);
        System.out.println("===============");

        return hmac;
    }
}

class TransferToCustomerPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(8);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public TransferToCustomerPartsBuilder setMainCustomerNumber(String mainCustomerNumber) {
        this.parts
                .add(new StringPart("mainCustomerNumber",
                        mainCustomerNumber == null ? "" : mainCustomerNumber,
                        "UTF-8"));
        return this;
    }

    public TransferToCustomerPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts
                .add(new StringPart("customerNumber",
                        customerNumber == null ? "" : customerNumber,
                        "UTF-8"));
        return this;
    }

    public TransferToCustomerPartsBuilder setTransAmount(String transAmount) {
        this.parts
                .add(new StringPart("transAmount",
                        transAmount == null ? "" : transAmount,
                        "UTF-8"));
        return this;
    }

    public TransferToCustomerPartsBuilder setRequestId(String requestId) {
        this.parts
                .add(new StringPart("requestId",
                        requestId == null ? "" : requestId,
                        "UTF-8"));
        return this;
    }

    public TransferToCustomerPartsBuilder setRemark(String remark) {
        this.parts
                .add(new StringPart("remark",
                        remark == null ? "" : remark,
                        "UTF-8"));
        return this;
    }

    public TransferToCustomerPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac",
                        hmac == null ? "" : hmac,
                        "UTF-8"));
        return this;
    }


}
