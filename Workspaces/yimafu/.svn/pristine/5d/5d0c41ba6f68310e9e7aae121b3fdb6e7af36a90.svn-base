package com.yeepay.g3.core.ymf.utils.security;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.security.MessageDigest;
import java.util.*;

/**
 * 交易签名工具类
 * @author xiaobin.liu
 *
 */
public class SpaySignUtil {
	private static final Logger log = LoggerFactory.getLogger(SpaySignUtil.class) ;

	private static final String defaultKey = "3094B6B97C7C482D9DEA035699E7BC24" ;
	
	/**
	 * 使用默认的key进行签名
	 */
	public static String sign(Map<String, String> data) {
		return sign(data, defaultKey) ;
	}
	
	/**
	 * 签名算法
	 */
	public static String sign(Map<String, String> data, String key) {
		String resp = "";
		String unsignString = "";
		List<String> nameList = new ArrayList<String>(data.keySet());
		// 首先按字段名的字典序排列
		Collections.sort(nameList);
		for (String name : nameList) {
			String value = data.get(name);
			if (StringUtils.isNotBlank(value) && !"null".equals(value.trim().toLowerCase())) {
				unsignString += name + "=" + value + "&";
			}
		}
		// 后面加上key然后md5签名
		unsignString += "key=" + key;
		log.info("####unsignString:" + unsignString);
		try {
			resp = md5(unsignString);
		} catch (Exception e) {
			log.error("###md5 Exception", e);
		}
		resp = resp.toUpperCase();
		log.info("###signString:" + resp);
		return resp;
	}
	
	public static String md5(String value) throws Exception {
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(value.getBytes("UTF-8"));
		byte[] arr = mdInst.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	/**
	 * 微信公众号app密钥加密
	 * @param data
	 * @return
     */
	public static String encryptAppSecret(String data) {
		return AES.encryptToBase64(data, Constants.APP_SECRET_AES_KEY);
	}

	/**
	 * 微信公众号app密钥解密
	 * @param data
	 * @return
     */
	public static String decryptAppSecret(String data) {
		return AES.decryptFromBase64(data, Constants.APP_SECRET_AES_KEY);
	}

	/**
	 * http://qa.yeepay.com/ymf-pay/orderPay/index?qrCode=1004000780020160818181427&sign=59A57C705271A2E896376FDF1BEA3F21订单版
	 * http://qa.yeepay.com/ymf-pay/standard/index?qrCode=1004000780020160818181427&sign=59A57C705271A2E896376FDF1BEA3F21标准版
	 */
	public static void main(String[] args) {
		String qr = "45KIDTFE" ;
		String localBaseUrl = "http://172.18.61.30:8080/ymf-pay/orderPay/index?qrCode=" + qr ;
		String qaBaseUrl = "http://qa.yeepay.com/ymf-pay/orderPay/index?qrCode=" + qr ;
		String productBaseUrl = "https://yimafu.yeepay.com/ymf-pay/orderPay/index?qrCode=" + qr ;
		String productInnerTestBaseUrl = "http://106.120.186.94:28080/ymf-pay/orderPay/index?qrCode=" + qr ;

		Map<String,String> data = new HashMap<String,String>() ;
		data.put("qrCode",qr) ;
		String newSign = SpaySignUtil.sign(data) ;

		localBaseUrl += "&sign=" + newSign ;
		qaBaseUrl += "&sign=" + newSign ;
		productBaseUrl += "&sign=" + newSign ;
		productInnerTestBaseUrl += "&sign=" + newSign ;

		System.out.println("本机:");
		System.out.println(localBaseUrl);
		System.out.println("QA:");
		System.out.println(qaBaseUrl);
		System.out.println("生产：");
		System.out.println(productBaseUrl);
		System.out.println("内测：");
		System.out.println(productInnerTestBaseUrl);
	}

}
