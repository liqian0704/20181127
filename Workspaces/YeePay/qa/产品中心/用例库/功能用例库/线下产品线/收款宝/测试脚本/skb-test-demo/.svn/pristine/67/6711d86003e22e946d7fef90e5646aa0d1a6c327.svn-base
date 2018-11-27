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
 * 子商户设置限额接口
 * @author sailfish
 * @create 2017-08-24-上午10:41
 */
public class LimitAmountSetApiRequester {

    private static String setUrl = Conts.baseRequestUrl + "/tradeLimitSet.action";
    private static String tradeLimitConfigKey = "2";// 自定义key 1店主 2非店主 3代理商户
    private static String singleAmount = "100";// 单笔限额
    private static String dayAmount = "200";// 日限额
    private static String monthAmount = "300";// 月限额
    private static String dayCount = "3";// 日累计次数限制
    private static String monthCount = "33";// 月累计次数限制
    private static String customerNumber = Conts.customerNumber;// 商户编号
    private static String mainCustomerNumber = Conts.maincustomerNumber;// 代理商编
    private static String bankCardType = "CREDIT";// 银行卡类型 DEBIT CREDIT
    private static String bankCardNo = "";// 银行卡卡号
    private static String hmacKey = Conts.hmacKey; // 商户秘钥

    public static void main(String[] args) {
        PostMethod postMethod = new PostMethod(setUrl);

        HttpClient client = new HttpClient();

        try {
            StringBuffer signature = new StringBuffer();
            signature
                    .append(customerNumber == null ? "" : customerNumber)
                    .append(mainCustomerNumber == null ? ""
                            : mainCustomerNumber)
                    .append(bankCardType == null ? "" : bankCardType)
                    .append(bankCardNo == null ? "" : bankCardNo)
                    .append(tradeLimitConfigKey == null ? ""
                            : tradeLimitConfigKey)
                    .append(singleAmount == null ? "" : singleAmount)
                    .append(dayAmount == null ? "" : dayAmount)
                    .append(monthAmount == null ? "" : monthAmount)
                    .append(dayCount == null ? "" : dayCount)
                    .append(monthCount == null ? "" : monthCount);
            System.out.println("source===" + signature.toString());
            String hmac = Digest.hmacSign(signature.toString(), hmacKey);
            System.out.println("hmac====" + hmac);

            Part[] parts = new LimitAmountSetPartsBuilder()
                    .setTradeLimitConfigKey(tradeLimitConfigKey)
                    .setCustomernumber(customerNumber)
                    .setMainCustomerNumber(mainCustomerNumber)
                    .setBankCardType(bankCardType).setBankCardNo(bankCardNo)
                    .setSingleAmount(singleAmount).setDayAmount(dayAmount)
                    .setMonthAmount(monthAmount).setDayCount(dayCount)
                    .setMonthCount(monthCount).setHmac(hmac).generateParams();

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

class LimitAmountSetPartsBuilder {

    private List<Part> parts = new ArrayList<Part>();

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    // [start] jun.lin 2015-03-20 这里是普通入参
    /**
     * @param mainCustomerNumber
     *            the mainCustomerNumber to set
     */
    public LimitAmountSetPartsBuilder setTradeLimitConfigKey(
            String tradeLimitConfigKey) {
        this.parts
                .add(new StringPart("tradeLimitConfigKey",
                        tradeLimitConfigKey == null ? "" : tradeLimitConfigKey,
                        "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setSingleAmount(String singleAmount) {
        this.parts.add(new StringPart("singleAmount", singleAmount == null ? ""
                : singleAmount, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setDayAmount(String dayAmount) {
        this.parts.add(new StringPart("dayAmount", dayAmount == null ? ""
                : dayAmount, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setMonthAmount(String monthAmount) {
        this.parts.add(new StringPart("monthAmount", monthAmount == null ? ""
                : monthAmount, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setDayCount(String dayCount) {
        this.parts.add(new StringPart("dayCount", dayCount == null ? ""
                : dayCount, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setMonthCount(String monthCount) {
        this.parts.add(new StringPart("monthCount", monthCount == null ? ""
                : monthCount, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setCustomernumber(String customerNumber) {
        this.parts.add(new StringPart("customerNumber",
                customerNumber == null ? "" : customerNumber, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setMainCustomerNumber(
            String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setBankCardType(String bankCardType) {
        this.parts.add(new StringPart("bankCardType", bankCardType == null ? ""
                : bankCardType, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setBankCardNo(String bankCardNo) {
        this.parts.add(new StringPart("bankCardNo", bankCardNo == null ? ""
                : bankCardNo, "UTF-8"));
        return this;
    }

    public LimitAmountSetPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }
}
