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
 * 子商户信息修改接口
 * @author sailfish
 * @create 2017-08-24-上午10:38
 */
public class CustomerInforUpdateApiRequester {

    private static String queryUrl = Conts.baseRequestUrl + "/customerInforUpdate.action";
    private static String mainCustomerNumber = Conts.maincustomerNumber;
    private static String customerNumber = Conts.customerNumber;
    private static String whiteList = "";
    private static String freezeDays = "";
    private static String modifyType = "3";
    private static String bankCardNumber = "";
    private static String bankName = "";
    private static String riskReserveDay = "0";
    private static String manualSettle = "Y";
    private static String splitter = "";
    private static String splitterProfitFee = "";
    private static String business = "";
    private static String bindMobile = "";// 绑定手机号
    private static String mailStr = "";// 邮箱
    private static String hmacKey = Conts.hmacKey; // 商户秘钥

    public static void main(String[] args) {

        PostMethod postMethod = new PostMethod(queryUrl);

        HttpClient client = new HttpClient();

        try {

            StringBuffer signature = new StringBuffer();
            if ("1".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(whiteList == null ? "" : whiteList)
                        .append(freezeDays == null ? "" : freezeDays);
            } else if ("2".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(bankCardNumber == null ? "" : bankCardNumber)
                        .append(bankName == null ? "" : bankName);
            } else if ("3".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(riskReserveDay == null ? "" : riskReserveDay)
                        .append(manualSettle == null ? "" : manualSettle);
            } else if ("4".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(splitter == null ? "" : splitter)
                        .append(splitterProfitFee == null ? ""
                                : splitterProfitFee);
            } else if ("5".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(business == null ? "" : business);
            }else if ("6".equals(modifyType)) {
                signature
                        .append(mainCustomerNumber == null ? ""
                                : mainCustomerNumber)
                        .append(customerNumber == null ? "" : customerNumber)
                        .append(bindMobile == null ? "" : bindMobile)
                        .append(mailStr == null ? "" : mailStr);
            }
            System.out.println("source===" + signature.toString());
            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            System.out.println("hmac====" + hmac);

            Part[] parts = new CustomerInforUpdatePartsBuilder()
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setCustomerNumber(customerNumber).setWhiteList(whiteList)
                    .setFreezeDays(freezeDays).setModifyType(modifyType)
                    .setBankCardNumber(bankCardNumber).setBankName(bankName)
                    .setRiskReserveDay(riskReserveDay)
                    .setManualSettle(manualSettle).setSplitter(splitter)
                    .setSplitterProfitFee(splitterProfitFee)
                    .setBusiness(business).setHmac(hmac).generateParams();

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

class CustomerInforUpdatePartsBuilder {

    private List<Part> parts = new ArrayList<Part>();

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    public CustomerInforUpdatePartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setCustomerNumber(
            String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setWhiteList(String whiteList) {
        this.parts.add(new StringPart("whiteList", whiteList == null ? ""
                : whiteList, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setFreezeDays(String freezeDays) {
        this.parts.add(new StringPart("freezeDays", freezeDays == null ? ""
                : freezeDays, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setModifyType(String modifyType) {
        this.parts.add(new StringPart("modifyType", modifyType == null ? ""
                : modifyType, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBankCardNumber(
            String bankCardNumber) {
        this.parts.add(new StringPart("bankCardNumber",
                bankCardNumber == null ? "" : bankCardNumber, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBankName(String bankName) {
        this.parts.add(new StringPart("bankName", bankName == null ? ""
                : bankName, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setRiskReserveDay(
            String riskReserveDay) {
        this.parts.add(new StringPart("riskReserveDay",
                riskReserveDay == null ? "" : riskReserveDay, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setManualSettle(String manualSettle) {
        this.parts.add(new StringPart("manualSettle", manualSettle == null ? ""
                : manualSettle, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setSplitter(String splitter) {
        this.parts.add(new StringPart("splitter", splitter == null ? ""
                : splitter, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setSplitterProfitFee(
            String splitterProfitFee) {
        this.parts.add(new StringPart("splitterProfitFee",
                splitterProfitFee == null ? "" : splitterProfitFee, "UTF-8"));
        return this;
    }

    public CustomerInforUpdatePartsBuilder setBusiness(String business) {
        this.parts.add(new StringPart("business", business == null ? ""
                : business, "UTF-8"));
        return this;
    }
}
