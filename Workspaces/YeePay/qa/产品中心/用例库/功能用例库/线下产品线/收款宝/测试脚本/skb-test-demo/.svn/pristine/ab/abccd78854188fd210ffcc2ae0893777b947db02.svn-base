package com.yeepay.skb.biz;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import com.alibaba.fastjson.JSONObject;
import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

/**
 * 商户费率设置接口
 * @author sailfish
 * @create 2017-08-24-上午10:39
 */
public class FeeSetActionTester {

	private static String url = Conts.baseRequestUrl + "/feeSetApi.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String productType = "3";
    private static String customerNumber = Conts.customerNumber;
    private static String rate = "0";
    private static String hmacKey = Conts.hmacKey;

    public static void main(String[] args) {

        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();

        try {
            StringBuffer signature = new StringBuffer();
            signature
                    .append(customerNumber == null ? "" : customerNumber)
                    .append(mainCustomerNumber == null ? ""
                            : mainCustomerNumber)
                    .append(productType == null ? "" : productType)
                    .append(rate == null ? "" : rate);
            System.out.println("source===" + signature.toString());

            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            Part[] parts = new FeeSetPartsBuilder()
                    .setCustomerNumber(customerNumber)
                    .setGroupCustomerNumber(mainCustomerNumber)
                    .setProductType(productType)
                    .setHmac(hmac).setRate(rate).generateParams();

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


    private static void parseResult(String result) {

        JSONObject jsonResult = JSONObject.parseObject(result);
        String url = jsonResult.getString("url");
    }

}

class FeeSetPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }


    public FeeSetPartsBuilder setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber", customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setGroupCustomerNumber(String groupCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber", groupCustomerNumber == null ? ""
                : groupCustomerNumber, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setProductType(String productType) {
        this.parts.add(new StringPart("productType", productType == null ? ""
                : productType, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setRate(String rate) {
        this.parts.add(new StringPart("rate",
                rate == null ? "" : rate, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setCfca(String cfca) {
        this.parts
                .add(new StringPart("cfca", cfca == null ? "" : cfca, "UTF-8"));
        return this;
    }

    public FeeSetPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
}
