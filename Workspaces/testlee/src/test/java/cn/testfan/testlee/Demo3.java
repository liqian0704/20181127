package cn.testfan.testlee;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示登录
 * @author xiyang
 *
 */
public class Demo3 {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpClient httpclient = new DefaultHttpClient();

        //设置登录参数
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("username", "sdlgxxy"));
        formparams.add(new BasicNameValuePair("[pwd_label]", "*****"));
        UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "UTF-8");

        //新建Http  post请求
        HttpPost httppost = new HttpPost("https://it.yeepay.com/cas/login?service=https%3A%2F%2Fit.yeepay.com%2Fportal%2F");
        httppost.setEntity(entity1);

        //处理请求，得到响应
        HttpResponse response = httpclient.execute(httppost);

        String set_cookie = response.getFirstHeader("Set-Cookie").getValue();

        //打印Cookie值
        System.out.println(set_cookie.substring(0,set_cookie.indexOf(";")));

        //打印返回的结果
        HttpEntity entity = response.getEntity();

        StringBuilder result = new StringBuilder();
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(instream));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                String str = new String(temp.getBytes(), "utf-8");
                result.append(str);
            }
        }
        System.out.println(result);
    }
}