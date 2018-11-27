package com.yeepay.skb.biz;


import com.alibaba.fastjson.JSONObject;
import com.yeepay.skb.util.AESUtil;
import com.yeepay.skb.util.Conts;
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

/**
 * 收款接口
 *
 * @author sailfish
 * @create 2017-08-24-上午10:43
 */
public class ReceiveApiRequester {

    private static String url = Conts.baseRequestUrl + "/receiveApi.action";

    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String amount = "2";
    private static String callBackUrl = "http://172.18.162.240:8080/skb-app/testReceiveApiCallBackAction.action";
    private static String customerNumber = Conts.customerNumber;
    private static String mcc = "4511";
    private static String requestId = "";
    private static String mobileNumber = "";
    private static String smgCallBackUrl = "http://172.18.162.240:8080/skb-app/testReceiveApiCallBackAction.action";
    private static String source = "B";
    private static String webCallBackUrl = "http://172.18.162.240:8080/skb-app/login_forward?requestId=123131";
    private static String payerBankAccountNo = "";
    private static String description = "";
    private static String hmacKey = Conts.hmacKey;

    private static String autoWithdraw = "true";//逐笔结算
    private static String withdrawCardNo = "";//提现卡号
    private static String customTradeFee = ""; //手续费
    private static String withdrawCallBackUrl = "http://app.com";//出款回调地址
    
    private static String productVersion = "PROFESSIONAL"; //产品版本
    


    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(url);
        HttpClient client = new HttpClient();
        try {
            Part[] parts = new ReceviePartsBuiler()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setAmount(amount).setCallBackUrl(callBackUrl)
                    .setCustomerNumber(customerNumber).setHamc(hmacSign())
                    .setMcc(mcc).setMobileNumber(mobileNumber)
                    .setRequestId(requestId).setSmgCallBackUrl(smgCallBackUrl)
                    .setSource(source).setWebCallBackUrl(webCallBackUrl)
                    .setDescription(description)
                    .setPayerBankAccountNo(payerBankAccountNo)
                    .setAutoWithdraw(autoWithdraw)
                    .setWithdrawCardNo(withdrawCardNo)
                    .setCustomTradeFee(customTradeFee)
                    .setProductVersion(productVersion)
                    .setWithdrawCallBackUrl(withdrawCallBackUrl)
                    .generateParams();

            postMethod.setRequestEntity(new MultipartRequestEntity(parts,
                    postMethod.getParams()));

            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                String result = postMethod.getResponseBodyAsString();

                System.out.println("===============");
                System.out.println("result" + result);
                System.out.println("===============");

                if (result != null) {
                    parseResult(result);
                }

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

    /** @param result */
    private static void parseResult(String result) {

        JSONObject jsonResult = JSONObject.parseObject(result);

        String url = jsonResult.getString("url");

        url = AESUtil.decrypt(url, hmacKey);

        System.out.println("===============");
        System.out.println("url = " + url);
        System.out.println("===============");
    }

    private static String hmacSign() {
        StringBuilder hmacStr = new StringBuilder();
        hmacStr.append(source == null ? "" : source)
                .append(mainCustomerNumber == null ? "" : mainCustomerNumber)
                .append(customerNumber == null ? "" : customerNumber)
                .append(amount == null ? "" : amount)
                .append(mcc == null ? "" : mcc)
                .append(requestId == null ? "" : requestId)
                .append(mobileNumber == null ? "" : mobileNumber)
                .append(callBackUrl == null ? "" : callBackUrl)
                .append(webCallBackUrl == null ? "" : webCallBackUrl)
                .append(smgCallBackUrl == null ? "" : smgCallBackUrl)
                .append(payerBankAccountNo == null ? "" : payerBankAccountNo);

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

class ReceviePartsBuiler {

    private List<Part> parts = new ArrayList<Part>(11);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public ReceviePartsBuiler setSource(String source) {
        this.parts.add(new StringPart("source", source == null ? "" : source,
                "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCustomerNumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setRequestId(String requestId) {
        this.parts.add(new StringPart("requestId", requestId == null ? ""
                : requestId, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setAmount(String amout) {
        this.parts.add(new StringPart("amount", amout == null ? "" : amout,
                "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMcc(String mcc) {
        this.parts.add(new StringPart("mcc", mcc == null ? "" : mcc, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setMobileNumber(String mobileNumber) {
        this.parts.add(new StringPart("mobileNumber", mobileNumber == null ? ""
                : mobileNumber, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCallBackUrl(String callBackUrl) {
        this.parts.add(new StringPart("callBackUrl", callBackUrl == null ? ""
                : callBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setWebCallBackUrl(String webCallBackUrl) {
        this.parts.add(new StringPart("webCallBackUrl",
                webCallBackUrl == null ? "" : webCallBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setSmgCallBackUrl(String smgCallBackUrl) {
        this.parts.add(new StringPart("smgCallBackUrl",
                smgCallBackUrl == null ? "" : smgCallBackUrl, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setPayerBankAccountNo(String payerBankAccountNo) {
        this.parts.add(new StringPart("payerBankAccountNo",
                payerBankAccountNo == null ? "" : payerBankAccountNo, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setHamc(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCfca(String cfca) {
        this.parts
                .add(new StringPart("cfca", cfca == null ? "" : cfca, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setDescription(String description) {
        this.parts.add(new StringPart("description", description == null ? ""
                : description, "UTF-8"));
        return this;
    }


    public ReceviePartsBuiler setAutoWithdraw(String autoWithdraw) {
        this.parts.add(new StringPart("autoWithdraw",
                autoWithdraw == null ? "" : autoWithdraw, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setWithdrawCardNo(String withdrawCardNo) {
        this.parts.add(new StringPart("withdrawCardNo",
                withdrawCardNo == null ? "" : withdrawCardNo, "UTF-8"));
        return this;
    }

    public ReceviePartsBuiler setCustomTradeFee(String customTradeFee) {
        this.parts.add(new StringPart("customTradeFee",
                customTradeFee == null ? "" : customTradeFee, "UTF-8"));
        return this;
    }
    
    public ReceviePartsBuiler setProductVersion(String productVersion) {
        this.parts.add(new StringPart("productVersion",
                productVersion == null ? "" : productVersion, "UTF-8"));
        return this;
    }
    
    public ReceviePartsBuiler setWithdrawCallBackUrl(String withdrawCallBackUrl) {
        this.parts.add(new StringPart("withdrawCallBackUrl",
                withdrawCallBackUrl == null ? "" : withdrawCallBackUrl, "UTF-8"));
        return this;
    }


}
