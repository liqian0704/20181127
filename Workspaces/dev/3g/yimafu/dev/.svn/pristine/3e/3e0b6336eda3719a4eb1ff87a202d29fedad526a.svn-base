package com.yeepay.g3.core.ymf.utils.security;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
		System.out.println("####unsignString:" + unsignString);
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
	 * 加密并urlencode短链的uri
	 * @param uri  需要加密的uri
	 * @return	参数为空返回null，其他返回AES加密后并urlencode的字符串
	 */
	public static String encodeUri(String uri) throws YmfException {
		try {
			if (org.apache.commons.lang.StringUtils.isBlank(uri)) {
                return null;
            }
			log.info("encodeUri 加密前：{}",uri);
			String appSecret = encryptAppSecret(uri);
			String encode = URLEncoder.encode(appSecret, "UTF-8");
			log.info("encodeUri,加密结果：{}",encode);
			return encode;
		} catch (UnsupportedEncodingException e) {
			throw new YmfException(e);
		}
	}

	/**
	 * 解密并urldecode短连接
	 * @param decodeUri		需要解密的uri
	 * @return		返回解密后的明文
	 */
	public static String decodeUri(String decodeUri) throws YmfException {
		String decode = null;
		try {
			log.info("decodeUri 解密前：{}",decodeUri);
			decode = URLDecoder.decode(decodeUri, "UTF-8");
			decode = decryptAppSecret(decode);
			log.info("decodeUri 解密后：{}",decode);
		} catch (UnsupportedEncodingException e) {
			throw new YmfException(e);
		}
		return decode;
	}


	private static final String localBaseUrl = "http://172.18.61.30:8080/ymf-pay/qrPay/index";
	private static final String qaBaseUrl = "http://qa.yeepay.com/ymf-pay/qrPay/index" ;
	private static final String productBaseUrl = "https://yimafu.yeepay.com/ymf-pay/qrPay/index" ;
	private static final String productInnerTestBaseUrl = "http://106.120.186.94:28080/ymf-pay/qrPay/index" ;
	/**
	 * 生成订单二维码地址
	 * @param qr
	 * @param requestId
	 * @param key
	 * @param env          local  qa product  inner
	 * @return
	 */
	public static String createOrderQrUrl(String qr,String requestId,String key,String env) {
		Map<String, String> data = new HashMap<String, String>();
		String ct = DateUtil.formatDate(new Date(), "yyMMddHHmmss");
		data.put("qr", qr);
		data.put("ct", ct);
		data.put("id", requestId);
		String sign = SpaySignUtil.sign(data, key);
		String baseUrl = "";
		if ("local".equals(env)) {
			baseUrl = localBaseUrl;
		} else if ("qa".equals(env)) {
			baseUrl = qaBaseUrl;
		} else if ("product".equals(env)) {
			baseUrl = productBaseUrl;
		} else if ("inner".equals(env)) {
			baseUrl = productInnerTestBaseUrl;
		}
		baseUrl += "?qr=" + qr + "&id=" + requestId + "&ct=" + ct + "&sg=" + sign;
		return baseUrl;
	}

	/**
	 * http://qa.yeepay.com/ymf-pay/orderPay/index?qrCode=1004000780020160818181427&sign=59A57C705271A2E896376FDF1BEA3F21订单版
	 * http://qa.yeepay.com/ymf-pay/standard/index?qrCode=1004000780020160818181427&sign=59A57C705271A2E896376FDF1BEA3F21标准版
	 */
	public static void main(String[] args) {
//		String qr = "45KIDTFE" ;
//		String localBaseUrl = "http://172.18.61.30:8080/ymf-pay/orderPay/index?qrCode=" + qr ;
//		String qaBaseUrl = "http://qa.yeepay.com/ymf-pay/orderPay/index?qrCode=" + qr ;
//		String productBaseUrl = "https://yimafu.yeepay.com/ymf-pay/orderPay/index?qrCode=" + qr ;
//		String productInnerTestBaseUrl = "http://106.120.186.94:28080/ymf-pay/orderPay/index?qrCode=" + qr ;
//
//		Map<String,String> data = new HashMap<String,String>() ;
//		data.put("qrCode",qr) ;
//		String newSign = SpaySignUtil.sign(data) ;
//
//		localBaseUrl += "&sign=" + newSign ;
//		qaBaseUrl += "&sign=" + newSign ;
//		productBaseUrl += "&sign=" + newSign ;
//		productInnerTestBaseUrl += "&sign=" + newSign ;
//
//		System.out.println("本机:");
//		System.out.println(localBaseUrl);
//		System.out.println("QA:");
//		System.out.println(qaBaseUrl);
//		System.out.println("生产：");
//		System.out.println(productBaseUrl);
//		System.out.println("内测：");
//		System.out.println(productInnerTestBaseUrl);
//		Map<String, String> data = new HashMap<String, String>();

//		data.put("qr", "45KIDTFE");
//		data.put("id", "123456");
//		data.put("id", "170107181512");
//		String hmacKey = "3094B6B97C7C482D9DEA035699E7BC24";
//		String newSign = SpaySignUtil.sign(data,hmacKey) ;

//		System.out.println("加密后的文件为：" +
//				createOrderQrUrl("3232","3232","32333232","local"));
		try {
			String appSecret = encryptAppSecret("170107181512");
			System.out.println("encode：" + appSecret);
			String encode = URLEncoder.encode(appSecret, "UTF-8");
			System.out.println("encode：" + encode);
			String decode = URLDecoder.decode(encode, "UTF-8");
			System.out.println("decode：" + decode);
			System.out.println(appSecret.equals(decode));
			System.out.println(decryptAppSecret(decode));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
