package com.yeepay.skb.biz;

import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 交易查询接口
 * @author sailfish
 * @create 2017-08-24-上午10:47
 */
public class TradeReciveQueryApiRequester {


    // 访问地址
    private static final String url = Conts.baseRequestUrl + "/tradeReviceQuery.action";

    private static final String hmacKey = Conts.hmacKey;

    private static NameValuePair[] param = {
            // 大商户编号
            new NameValuePair("mainCustomerNumber", Conts.maincustomerNumber),
            // 小商户编号
            new NameValuePair("customerNumber", Conts.customerNumber),
            // 交易订单号
            new NameValuePair("requestId", ""),
            // 请求时间起始时间
            new NameValuePair("createTimeBegin", "2018-01-24 00:00:00"),
            // 请求时间结束时间
            new NameValuePair("createTimeEnd", "2018-01-25 00:00:00"),
            // 支付时间起始时间
            new NameValuePair("payTimeBegin", ""),
            // 支付时间结束时间
            new NameValuePair("payTimeEnd", ""),

            // 更新时间起始时间
            new NameValuePair("lastUpdateTimeBegin", ""),
            // 更新时间结束时间
            new NameValuePair("lastUpdateTimeEnd", ""),
            // 状态（不区分大小写） FAIL，SUCCESS，FROZEN，THAWED
            new NameValuePair("status", ""),
            // 业务类型（不区分大小写） COMMON ， ASSURE
            new NameValuePair("busiType", ""),
            // 分页-页码
            new NameValuePair("pageNo", "1"),
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
