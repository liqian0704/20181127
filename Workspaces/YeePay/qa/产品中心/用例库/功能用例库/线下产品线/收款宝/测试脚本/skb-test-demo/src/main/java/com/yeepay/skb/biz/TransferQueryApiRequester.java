package com.yeepay.skb.biz;


import com.yeepay.skb.util.Digest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 结算记录查询
 * @author sailfish
 * @create 2017-08-24-上午10:50
 */
public class TransferQueryApiRequester {

    // 访问地址
    private static final String url = "http://skb.yeepay.com/skb-app/transferQuery.action";

    private static final String hmacKey = "";

    /**
     * 请求参数 验签在数组最后
     *
     *
     */

    private static NameValuePair[] param = {
            // 小商户编号
            new NameValuePair("customerNumber", ""),

            new NameValuePair("externalNo", ""),
            // 大商户编号
            new NameValuePair("mainCustomerNumber", ""),
            // 分页-页码
            new NameValuePair("pageNo", ""),

            // 请求时间起始时间
            new NameValuePair("requestDateSectionBegin", ""),
            // 请求时间结束时间
            new NameValuePair("requestDateSectionEnd", ""),
            new NameValuePair("serialNo",
                    ""),
            // 出款状态 /** * 已接收 */ RECEIVED, /** * 处理中 */ PROCESSING, /** * 打款成功
            // */ SUCCESSED, /** * 打款失败 */ FAILED, /** * 已退款 */ REFUNED;
            new NameValuePair("transferStatus", ""),
            // 出款方式 TRANSFERINDAY("1","日结通"),
            // TRANSFER("2","委托结算"),SETTLEMENT("3","商家结算");
            new NameValuePair("transferWay", ""),

            // 签名串
            new NameValuePair("hmac", ""),

    };

    public static void main(String[] args) {

        // System.out.println(new BigDecimal("1.00").divide(new
        // BigDecimal("0")));

        PostMethod postMethod = new PostMethod(url);

        HttpClient client = new HttpClient();
        postMethod.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");

        try {

            param[param.length - 1].setValue(hmacSign());

            postMethod.setRequestBody(param);

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

    /**
     * 签名
     *
     * @return
     */
    private static String hmacSign() {
        StringBuilder hmacStr = new StringBuilder();
        for (NameValuePair nameValuePair : param) {
            if (nameValuePair.getName().equals("hmac")) {
                continue;
            }
            hmacStr.append(nameValuePair.getValue() == null ? ""
                    : nameValuePair.getValue());

        }

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
