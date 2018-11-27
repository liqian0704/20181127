package com.yeepay.skb.biz;


import com.yeepay.skb.util.Digest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
/**
 * 结算手续费查询接口
 * @author sailfish
 * @create 2017-08-24-上午10:58
 */
public class LendTargetFeeQueryRequester {

    private static String url = "http://skb.yeepay.com/skb-app/lendTargetFeeQuery.action";
    private static String mainCustomerNumber = "10040018515";// 10040018515
    private static String customerNumber = "10040026643";// 10040026643
    private static String transType = "1";// 1.提现 2.日结通
    private static String transAmount = "100";// 10040018515
    private static String hmacKey = "9393F8Q58Ja90994hdlc2Rk4Te2IrV289y6XV90Qg90arAg9s7kYn7r8587B";

    public static void main(String[] args) {

        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();

        try {
            // hmac和cfca是签名方法计算出来的
            Part[] parts = new LendTargetFeeQueryBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setCustomerNumber(customerNumber)
                    .setTransAmount(transAmount).setTransType(transType)
                    .setHmac(hmacSign()).generateParams();

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
                .append(transType == null ? "" : transType)
                .append(transAmount == null ? "" : transAmount);

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

class LendTargetFeeQueryBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public LendTargetFeeQueryBuilder setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber", mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public LendTargetFeeQueryBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber", customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public LendTargetFeeQueryBuilder setHmac(String hmac) {
        this.parts.add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public LendTargetFeeQueryBuilder setTransAmount(String transAmount) {
        this.parts.add(new StringPart("transAmount", transAmount == null ? "" : transAmount, "UTF-8"));
        return this;
    }
    public LendTargetFeeQueryBuilder setTransType(String transType) {
        this.parts.add(new StringPart("transType", transType == null ? "" : transType, "UTF-8"));
        return this;
    }

}
