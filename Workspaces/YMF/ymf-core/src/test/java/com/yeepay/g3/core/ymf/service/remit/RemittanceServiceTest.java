package com.yeepay.g3.core.ymf.service.remit;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.junit.ApplicationContextAwareTest;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongxulu on 17/4/24.
 */
public class RemittanceServiceTest extends ApplicationContextAwareTest {


    @Test
    public void save(){
        RemittanceService remittanceService = getBean(RemittanceService.class);
        Remittance remittance = new Remittance();
        String requestID = String.valueOf(System.currentTimeMillis());
        System.out.println(requestID);
        remittance.setYeepayOrderId(requestID);
        remittance.setAmount(new BigDecimal(0.01));
        remittance.setBatchNo("123456789012345");
        remittance.setCustomerNumber("10040011560");
        remittance.setRemitStatus(RemitStatus.INIT);
        remittance.setCreateTime(new Date());
        remittance.setStatus(Status.INIT);
        remittanceService.save(remittance);
    }
    @Test
    public void getByYeepayOrderID(){
        RemittanceService remittanceService = getBean(RemittanceService.class);
        Remittance remittance = remittanceService.findByYeepayOrderId("1493040065177");
        System.out.println(JSONUtils.toJsonString(remittance));
    }
    @Test
    public void findByYeepayOrderId(){
        RemittanceService remittanceService = getBean(RemittanceService.class);
        Remittance remittance = remittanceService.
                findByCustomerNumberAndCustomerOrderId("10040011560","1493040065177");
        System.out.println(JSONUtils.toJsonString(remittance));
    }

    @Test
    public void findByRemitStatus(){
        RemittanceService remittanceService = getBean(RemittanceService.class);
        List<Remittance> remittanceList = remittanceService.
                findByRemitStatus(RemitStatus.INIT, DateUtil.getStrToDate("2017-03-01 00:00:00","yyyy-MM-dd HH:mm:ss")
                ,DateUtil.getStrToDate("2017-05-01 00:00:00","yyyy-MM-dd HH:mm:ss"),1,10);
        System.out.println("@@@@@@总笔数:"+remittanceService.findByRemitStatusCount(RemitStatus.INIT, DateUtil.getStrToDate("2017-03-01 00:00:00","yyyy-MM-dd HH:mm:ss")
                ,DateUtil.getStrToDate("2017-05-01 00:00:00","yyyy-MM-dd HH:mm:ss")));
        System.out.println(remittanceList.toString());
    }
    @Test
    public void pageSizeTest() {
        int pageSize = 1000;
        int pageCount = (5100 / 1000);
        System.out.println(pageCount);

        for (int i = 0; i < pageCount + 1; i++) {
            System.out.println(" statRow:" + i * pageSize);
            System.out.println(" endRow:" + (i + 1) * pageSize);
        }
    }
        @Test
        public void updateRemit(){
            RemittanceService remittanceService = getBean(RemittanceService.class);
            Remittance remittance = remittanceService.findByYeepayOrderId("1493040065177");
            remittance.setCallbackTime(new Date());
            remittanceService.update(remittance);
            System.out.println(JSONUtils.toJsonString(remittance));
        }
    @Test
    public void settlecallBackOpr(){

        String url = "http://localhost:8080/ymf-hessian/httpService/opr/settleCallback";
//            String url = "http://10.151.30.161:8081/ymf-hessian/httpService/opr/settleCallback";
        HttpPost postMethod = new HttpPost(url);
        try {
            //建立HttpPost对象
            List<NameValuePair> params=new ArrayList<NameValuePair>();
//建立一个NameValuePair数组，用于存储欲传送的参数
//          获取payment的yeepayOrderId
            params.add(new BasicNameValuePair("uniqueOrderNo","1001201705100000000000023799"));
//          回调时间按当前时间
            params.add(new BasicNameValuePair("successDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
//          orderId 为order内的customerOrderId
            params.add(new BasicNameValuePair("orderId","PY1494402468359"));
            params.add(new BasicNameValuePair("successDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
            params.add(new BasicNameValuePair("paySuccessDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
            params.add(new BasicNameValuePair("csSuccessDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
            params.add(new BasicNameValuePair("payAmount","1"));
            params.add(new BasicNameValuePair("status","SUCCESS"));
            params.add(new BasicNameValuePair("orderAmount","1"));
            params.add(new BasicNameValuePair("merchantFee","0.01"));
//          此为商户号
            params.add(new BasicNameValuePair("merchantNo","10040041016"));
            params.add(new BasicNameValuePair("bankId","lulutest123"));
//添加参数
            postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//发送Post,并返回一个HttpResponse对象
            HttpClient client = new DefaultHttpClient();
            HttpResponse responseBody = client.execute(postMethod);
            System.out.println(" return code:"+responseBody.getStatusLine().getStatusCode());
            if(responseBody.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
                String results=EntityUtils.toString(responseBody.getEntity());
                System.out.println("##callback result:"+results);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @Test
        public void callBackOpr(){

//            String url = "http://localhost:8080/ymf-hessian/httpService/opr/payCallback";
            String url = "http://10.151.30.161:8081/ymf-hessian/httpService/opr/payCallback";
            HttpPost postMethod = new HttpPost(url);
            try {
            //建立HttpPost对象
            List<NameValuePair> params=new ArrayList<NameValuePair>();
//建立一个NameValuePair数组，用于存储欲传送的参数
            params.add(new BasicNameValuePair("uniqueOrderNo","1001201707190000000000029407"));
            params.add(new BasicNameValuePair("successDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
            params.add(new BasicNameValuePair("orderId","PY1500431943556"));
            params.add(new BasicNameValuePair("paySuccessDate",DateUtil.getFmtDate(new Date(), Constants.OPR_DATE_TEMPLATE)));
            params.add(new BasicNameValuePair("payAmount","0.01"));
            params.add(new BasicNameValuePair("status","SUCCESS"));
            params.add(new BasicNameValuePair("orderAmount","0.01"));
            params.add(new BasicNameValuePair("merchantNo","10040041774"));
            params.add(new BasicNameValuePair("bankId","lulutest123"));
            params.add(new BasicNameValuePair("cardType",""));
            params.add(new BasicNameValuePair("platformType","WECHAT"));
//添加参数
            postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//发送Post,并返回一个HttpResponse对象
               HttpClient client = new DefaultHttpClient();
                HttpResponse responseBody = client.execute(postMethod);
                System.out.println(" return code:"+responseBody.getStatusLine().getStatusCode());
                if(responseBody.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
                    String results=EntityUtils.toString(responseBody.getEntity());
                    System.out.println("##callback result:"+results);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    @Test
    public void settleBackOpr(){

//            String url = "http://localhost:8080/ymf-hessian/httpService/opr/payCallback";
        String url = "http://10.151.30.161:8081/ymf-hessian/httpService/opr/settleCallback";
        HttpPost postMethod = new HttpPost(url);
        try {
            //建立HttpPost对象
            List<NameValuePair> params=new ArrayList<NameValuePair>();
//建立一个NameValuePair数组，用于存储欲传送的参数
            params.add(new BasicNameValuePair("payAmount","15"));
            params.add(new BasicNameValuePair("status","SUCCESS"));
            params.add(new BasicNameValuePair("csSuccessDate","2017-07-24 19:34:00"));
            params.add(new BasicNameValuePair("cashierType","SY_H5"));
            params.add(new BasicNameValuePair("paymentProduct","NCPAY"));
            params.add(new BasicNameValuePair("orderStatus","SUCCESS"));
            params.add(new BasicNameValuePair("uniqueOrderNo","1001201707240000000000030010"));
            params.add(new BasicNameValuePair("bankId","ICBC"));
            params.add(new BasicNameValuePair("bankOrderId","112233"));
            params.add(new BasicNameValuePair("bizSystemNo","LK"));
            params.add(new BasicNameValuePair("paySuccessDate","2017-07-24 19:30:00"));

            params.add(new BasicNameValuePair("orderAmount","15.00"));
            params.add(new BasicNameValuePair("customerFee","0.00"));
            params.add(new BasicNameValuePair("parentMerchantNo","10040011542"));

            params.add(new BasicNameValuePair("merchantNo","10040041016"));
            params.add(new BasicNameValuePair("requestDate","2017-07-24 19:30:00"));
            params.add(new BasicNameValuePair("merchantFee","3:00"));
            params.add(new BasicNameValuePair("orderId","764480767539"));

//添加参数
            postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//发送Post,并返回一个HttpResponse对象
            HttpClient client = new DefaultHttpClient();
            HttpResponse responseBody = client.execute(postMethod);
            System.out.println(" return code:"+responseBody.getStatusLine().getStatusCode());
            if(responseBody.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
                String results=EntityUtils.toString(responseBody.getEntity());
                System.out.println("##callback result:"+results);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
