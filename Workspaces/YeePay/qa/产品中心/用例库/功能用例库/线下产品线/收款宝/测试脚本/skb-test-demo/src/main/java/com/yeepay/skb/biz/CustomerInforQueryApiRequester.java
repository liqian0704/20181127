package com.yeepay.skb.biz;


import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

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
 * 子商户信息查询
 * @author sailfish
 * @create 2017-08-24-上午10:34
 */
public class CustomerInforQueryApiRequester {

    private static String queryUrl = Conts.baseRequestUrl + "/customerInforQuery.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;// 商户编号
    private static String mobilePhone = "13522666106";// 手机号
    private static String customerNumber = Conts.customerNumber;
    private static String customerType = "";
    private static String hmacKey = Conts.hmacKey; // 商户秘钥

    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(queryUrl);

        HttpClient client = new HttpClient();

        try {

            StringBuffer signature = new StringBuffer();
            signature
                    .append(mainCustomerNumber == null ? ""
                            : mainCustomerNumber)
                    .append(mobilePhone == null ? "" : mobilePhone)
                    .append(customerNumber == null ? "" : customerNumber)
                    .append(customerType == null ? "" : customerType);

            System.out.println("source===" + signature.toString());
            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            System.out.println("hmac====" + hmac);

            Part[] parts = new CustomerInforQueryPartsBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setMobilePhone(mobilePhone).setHmac(hmac)
                    .setCustomerType(customerType)
                    .setCustomerNumber(customerNumber).generateParams();

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

            postMethod.releaseConnection();
        }
    }
}

class CustomerInforQueryPartsBuilder {

    private List<Part> parts = new ArrayList<Part>();

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public CustomerInforQueryPartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforQueryPartsBuilder setMobilePhone(String mobilePhone) {
        this.parts.add(new StringPart("mobilePhone", mobilePhone == null ? ""
                : mobilePhone, "UTF-8"));
        return this;
    }

    public CustomerInforQueryPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public CustomerInforQueryPartsBuilder setCustomerNumber(
            String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforQueryPartsBuilder setCustomerType(String customerType) {
        this.parts.add(new StringPart("customerType", customerType == null ? ""
                : customerType, "UTF-8"));
        return this;
    }
}
