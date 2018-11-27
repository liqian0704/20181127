package com.yeepay.skb.biz;


import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * 出款
 * @author sailfish
 * @create 2017-08-24-上午10:55
 */
public class WithdrawRequester {

    // 访问地址
    private static final String url = Conts.baseRequestUrl + "/withDrawApi.action";

    private static final String hmacKey = Conts.hmacKey;

    /**
     * 请求参数 验签在数组最后
     *
     */
    private static NameValuePair[] param = {
            // 出款金额
            new NameValuePair("amount", "3.92"),
            // 小商户编号
            new NameValuePair("customerNumber", Conts.customerNumber),
            // 出款订单号
            new NameValuePair("externalNo", Long.toString(System.currentTimeMillis())),
            // 大商户编号
            new NameValuePair("mainCustomerNumber", Conts.maincustomerNumber),

            // 出款方式
            new NameValuePair("transferWay", "2"),//1-日结通，2-提现

            new NameValuePair("callBackUrl","http://211.151.82.148:8081/skb-app/testReceiveApiCallBackAction.action"),

            // 签名串
            new NameValuePair("hmac", Conts.hmacKey),

    };

    public static void main(String[] args) {

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
