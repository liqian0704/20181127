package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * @author xiaobin.liu
 *
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class) ;
	/**
	 * 测试获取openID地址
	 */
	protected static final String Auth2CallUrl_Test = "/test/auth2Callback" ;
	private static final String indexPage = "index" ;
	
	@RequestMapping("/index")
	public String test() {
		return "index" ;
	}
	
	/**
	 * 获取授权
	 */
	@RequestMapping("/test/openId")
	public String index(HttpServletRequest request) {
		try {
			logger.info(log_Line + "Begin /test/openId");
			String ua = request.getHeader("user-agent").toLowerCase();
			if (ua.contains("micromessenger")) {// 是微信浏览器
				logger.info("Request from Wechat Browser");
				String redrectUrl = wechatAuth2Url("", "11111",Auth2CallUrl_Test);
				logger.debug("redrectUrl " + redrectUrl);
				return "redirect:" + redrectUrl ;
			} else if(ua.contains("alipay")) {// 支付宝
				logger.info("Request from Alipay Browser");
			} else if(ua.contains("baidu")) {// 百度钱包
				logger.info("Request from Baidu Wallet Browser");
			} else {
				logger.info("Request from Other Browser");
			}
			logger.info(log_Line + "End /test/openId");
			return indexPage ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	/**
	 * 获取openId ；
	 * 接收微信系统回调。
	 */
	@RequestMapping("/test/auth2Callback")
	public String auth2Callback(HttpServletRequest request, Map<String, Object> map) {
		try {
			logger.info(log_Line + "Begin /test/auth2Callback");
			String code = request.getParameter("code");
//			String state = request.getParameter("state");
			
			String openId = wechatOpenId("12344",code);
			logger.info("####openId=" + openId);
			map.put("openId", openId);
			logger.info(log_Line + "End /test/auth2Callback");
			return "index" ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	/**
	 * 内测交易服务器IP
	 */
	public static final String innerIP = "http://106.120.186.94:28080/ymf-pay" ;
	
	/**
	 * 获取OpenId跳转到内测
	 */
	@RequestMapping("innerTest")
	public String innerTest(HttpServletRequest request,@RequestParam(value="type",required=false) String type) {
//		https://yimafu.yeepay.com/ymf-pay/orderPay/index?qrCode=45KIDTF7&sign=A7E0237FAC70B27B7FD5675B38DA3AC2
//		https://yimafu.yeepay.com/ymf-pay/standard/index?qrCode=45KIDTF7&sign=A7E0237FAC70B27B7FD5675B38DA3AC2
		try {
			logger.info(log_Line + "Begin innerTest");
			String customerNumber = "10011240071" ;
			String ua = request.getHeader("user-agent").toLowerCase();
			if (ua.contains("micromessenger")) {// 是微信浏览器
				logger.info("Request from Wechat Browser");
				String callUrl = "/inner/auth2Callback" ;
				String redrectUrl = wechatAuth2Url(type, customerNumber,callUrl);
				logger.debug("redrectUrl " + redrectUrl);
				return "redirect:" + redrectUrl ;
			} else if(ua.contains("alipay")) {// 支付宝
				logger.info("Request from Alipay Browser");
			} else if(ua.contains("baidu")) {// 百度钱包
				logger.info("Request from Baidu Wallet Browser");
			} else {
				logger.info("Request from Other Browser");
			}
			String redrectUrl;
			if ("1".equals(type)) {
				redrectUrl = innerIP + "/orderPay/index?qrCode=45KIDTF7&sign=A7E0237FAC70B27B7FD5675B38DA3AC2" ;
			} else {
				redrectUrl = innerIP + "/standard/index?qrCode=45KIDTF7&sign=A7E0237FAC70B27B7FD5675B38DA3AC2" ;
			}
			logger.info(log_Line + "End innerTest");
			return "redirect:" + redrectUrl ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	/**
	 * 获取openId ；跳转到内测地址
	 */
	@RequestMapping("inner/auth2Callback")
	public String auth2Callback(HttpServletRequest request) {
		try {
			logger.info(log_Line + "Begin inner Callback");
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String customerNumber = state ;
			String sign = "" ;
			if (customerNumber.contains("_")) {
				String arr[] = customerNumber.split("_");
				customerNumber = arr[0] ;
				sign = arr[1] ;
			}
			String openId = wechatOpenId(customerNumber,code);
			if (StringUtils.isNotBlank(openId)) {
			} else {
				String ua = request.getHeader("user-agent").toLowerCase();
				if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
					logger.info("Request from Wechat Browser");
					String callUrl = "/inner/auth2Callback" ;
					String redrectUrl = wechatAuth2Url(sign, customerNumber,callUrl);
					logger.debug("redrectUrl " + redrectUrl);
					return "redirect:" + redrectUrl ;
				}
			}
			logger.info(log_Line + "End inner Callback");
			return "redirect:" + innerIP + "/inner/fromQA?openId=" + openId + "&type=" + sign ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	/**
	 * 内测用，用于接收测试环境的OpenId并跳转到此路径
	 */
	@RequestMapping("inner/fromQA")
	public String fromQA(HttpServletRequest request, Map<String, Object> map,@RequestParam(value="openId",required=true) String openId,
			@RequestParam(value="type",required=false) String type) {
		try {
			logger.info(log_Line + "Begin fromQA");
			String customerNumber = "10011240071" ;
			Customer customer = customerService.findByCustomerNumber(customerNumber) ;
			putCustParams(request,map, customer);
			map.put("openId", openId);
			String url = "standard/pay" ;
			if ("1".equals(type)) {
				//行业版本
				url = "orderPay/index" ;
			}
			logger.info(log_Line + "End fromQA");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}

	@RequestMapping(value = "{name}", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String mpFileRequest(@PathVariable String name) {
		if (StringUtils.isNotBlank(name)) {
			if (!name.endsWith(".txt")) {
				name = name + ".txt";
			}
			File file = new File("/apps/mp/" + name);
			if (file.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					StringBuilder sb = new StringBuilder();
					String line;
					while (null != (line = br.readLine())) {
						sb.append(line);
					}
					return sb.toString();
				} catch (Exception e) {
					logger.error("读取微信公众号授权文件失败", e);
				}

			}
		}
		return "param error";
	}
	

}
