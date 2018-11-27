package com.yeepay.skb.util;

import java.security.PrivateKey;

/**
 * @author sailfish
 * @create 2017-08-24-上午11:19
 */
public interface Conts {
	/**
	 * 生产环境
	 */
	/*String baseRequestUrl = "https://skb.yeepay.com/skb-app";  //基础请求路径
    String maincustomerNumber = "10000420859";  //测试商编
    String hmacKey = "8GG6V170r5mo767tMg55oZ2F28NsUB3V5i44v48bfmIkDaz6xj8mPb8BY6P6";  //商编秘钥
    String customerNumber = "10016150025"; //测试子商户商编10015872275张岩，10015872266东旭，10015875834我
*/   
    /**
	 * 测试环境
	 */
    /*String baseRequestUrl = "http://10.151.30.4:8057/skb-app";  //基础请求路径
    String maincustomerNumber = "10040018515";  //测试代理商商编
    String hmacKey = "9393F8Q58Ja90994hdlc2Rk4Te2IrV289y6XV90Qg90arAg9s7kYn7r8587B";  //商编秘钥
   // String maincustomerNumber = "10040030887";  //测试代理商商编
    //String hmacKey = "Va555zp7PAoMmMtAkTIW91518Y55659b172CCoH32KLXAo6383832K6V80qt";  //商编秘钥
    String customerNumber = "10040051980"; //测试子商户商编10040041589
*/    
    /**
	 * 内测环境
	 */
	
    String baseRequestUrl = "http://211.151.82.148:8081/skb-app";  //基础请求路径
    String maincustomerNumber = "10000420859";  //测试代理商商编
    String hmacKey = "8GG6V170r5mo767tMg55oZ2F28NsUB3V5i44v48bfmIkDaz6xj8mPb8BY6P6";  //商编秘钥
    String customerNumber = "10018513350";//测试子商户商编，张岩10016150421，何其月10017731840、10017731827，张萌10017045353,东旭10015872266
    //郑志龙 10014965447，10017364614，10018345591（859）
    
    /*
	 * 开发本机测试环境
	 */
    /*String baseRequestUrl = "http://172.18.162.172:9001/skb-app";  //基础请求路径
    String maincustomerNumber = "10040018515";  //测试代理商商编
    String hmacKey = "9393F8Q58Ja90994hdlc2Rk4Te2IrV289y6XV90Qg90arAg9s7kYn7r8587B";  //商编秘钥
    String customerNumber = "10040048474"; //测试子商户商编
*/
    }
