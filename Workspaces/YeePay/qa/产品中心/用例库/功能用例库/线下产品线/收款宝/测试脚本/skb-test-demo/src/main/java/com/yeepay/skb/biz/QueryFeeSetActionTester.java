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
 * 商户费率查询接口
 * @author sailfish
 * @create 2017-08-24-上午10:40
 */
public class QueryFeeSetActionTester {

	private static String url = Conts.baseRequestUrl + "/queryFeeSetApi.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String productType = "5";
    private static String customerNumber = Conts.customerNumber;
    private static String hmacKey = Conts.hmacKey; // 商户秘钥

    public static void main(String[] args) {

        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();

        try {
            StringBuffer signature = new StringBuffer();
            signature
                    .append(customerNumber == null ? "" : customerNumber)
                    .append(mainCustomerNumber == null ? ""
                            : mainCustomerNumber)
                    .append(productType == null ? "" : productType);
            System.out.println("source===" + signature.toString());
            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            System.out.println("hmac====" + hmac);

            Part[] parts = new QueryFeeSetPartsBuilder()
                    .setCustomerNumber(customerNumber)
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setProductType(productType).setHmac(hmac).generateParams();

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

class QueryFeeSetPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }


    public QueryFeeSetPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber", customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public QueryFeeSetPartsBuilder setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber", mainCustomerNumber == null ? ""
                : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public QueryFeeSetPartsBuilder setProductType(String productType) {
        this.parts.add(new StringPart("productType", productType == null ? ""
                : productType, "UTF-8"));
        return this;
    }

    public QueryFeeSetPartsBuilder setRate(String rate) {
        this.parts.add(new StringPart("rate",
                rate == null ? "" : rate, "UTF-8"));
        return this;
    }

    public QueryFeeSetPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
}
