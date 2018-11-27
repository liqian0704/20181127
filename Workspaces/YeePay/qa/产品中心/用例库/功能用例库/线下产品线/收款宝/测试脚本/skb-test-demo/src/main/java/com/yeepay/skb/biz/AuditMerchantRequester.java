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
 * 子商户审核接口
 * @author sailfish
 * @create 2017-08-24-上午10:36
 */
public class AuditMerchantRequester {

    private static String url = Conts.baseRequestUrl + "/auditMerchant.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String customerNumber = Conts.customerNumber;
    private static String status = "SUCCESS";
    private static String reason = "test";
    private static String hmacKey = Conts.hmacKey;

    public static void main(String[] args) {

        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();

        try {
            Part[] parts = new AuditMerchantPartsBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setCustomerNumber(customerNumber)
                    .setStatus(status)
                    .setReason(reason)
                    .setHmac(hmacSign())
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

    private static String hmacSign() {
        StringBuilder hmacStr = new StringBuilder();
        hmacStr.append(customerNumber == null ? "" : customerNumber)
                .append(mainCustomerNumber == null ? "" : mainCustomerNumber)
                .append(status == null ? "" : status)
                .append(reason == null ? "" : reason);
        ;
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

class AuditMerchantPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public AuditMerchantPartsBuilder setStatus(String status) {
        this.parts.add(new StringPart("status", status == null ? "" : status,
                "UTF-8"));
        return this;
    }

    public AuditMerchantPartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public AuditMerchantPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public AuditMerchantPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public AuditMerchantPartsBuilder setReason(String reason) {
        this.parts.add(new StringPart("reason", reason == null ? "" : reason,
                "UTF-8"));
        return this;
    }
}
