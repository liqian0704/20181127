package com.yeepay.skb.biz;


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
 * 垫资额度查询接口
 * @author sailfish
 * @create 2017-08-24-上午11:00
 */
public class QueryRJTBalanceRequester {

    // 本地:50.1.1.28:8057
    private static String url = "http://skb.yeepay.com/skb-app/queryRJTBalance.action";
    private static String mainCustomerNumber = "";//
    private static String key = "";

    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();
        try {
            StringBuffer signature = new StringBuffer();
            signature.append(mainCustomerNumber == null ? ""
                    : mainCustomerNumber);

            String hmac = Digest.hmacSign(signature.toString(), key);

            Part[] parts = new QueryRJTBalanceBuilder()
                    .setMainCustomerNumber(mainCustomerNumber).setHmac(hmac)
                    .generateParams();

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


class QueryRJTBalanceBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public QueryRJTBalanceBuilder setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber", mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public QueryRJTBalanceBuilder setHmac(String hmac) {
        this.parts.add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
}