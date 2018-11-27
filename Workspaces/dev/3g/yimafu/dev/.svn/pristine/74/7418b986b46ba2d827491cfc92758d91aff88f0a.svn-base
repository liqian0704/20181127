package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
				String redrectUrl = toWechatAuth("", "11111",Auth2CallUrl_Test);
				logger.debug("redrectUrl " + redrectUrl);
				return redrectUrl ;
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
