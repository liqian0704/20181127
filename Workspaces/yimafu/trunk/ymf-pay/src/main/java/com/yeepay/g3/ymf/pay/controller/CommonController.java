package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class) ;
	/**
	 * 统一的授权回调地址
	 * http://qa.yeepay.com/ymf-pay/common/index?qrCode=46Y4I88N&sign=EC3ACC1382B6B8739C40DCF7BB785AE4
     */
	private static final String Auth2CallUrl = "/common/auth2Callback" ;
	/**
	 * 交易成功结果页面
	 * @return
	 */
	@RequestMapping("success")
	public String success(@RequestParam("externalId") String externalId,
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam(value="memo",required=false) String memo,
			HttpServletRequest request) {
		try {
			logger.info(log_Line + "Begin /common/success");
			Customer customer = customerService.findByCustomerNumber(customerNumber) ;
			if (customer != null && StringUtils.isEmpty(customer.getCustomerLogo())) {
				customer.setCustomerLogo(request.getContextPath() + "/static/images/ymf.png");
			}
			//TODO 这里需要修改传入的参数为externalId
			Order order = orderService.findByCustomerAndExternalId(customerNumber, externalId) ;
			request.setAttribute("order", order);
			request.setAttribute("customer", customer);
			request.setAttribute("memo", memo);
			if (order != null && order.getCreateTime() != null) {
				request.setAttribute("createTime", DateUtil.getFmtDate(order.getCreateTime(), DateUtil.ORA_DATE_TIMES_FORMAT));
			}
			logger.info(log_Line + "End /common/success");
			return "common/success" ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}

	/**
	 * 统一微信授权
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam("qrCode") String qrCode,
						@RequestParam(value="sign",required=false) String sign, Map<String, Object> map) {
		try {
			logger.info(log_Line + "Begin 统一微信授权 common index");
			checkCodeSign(qrCode,sign) ;
			String url = handerBrowser(request, map, qrCode, sign, Auth2CallUrl,PageTag) ;
			logger.info(log_Line + "End 统一微信授权 common index");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}

	/**
	 * 统一回调,获取openId ；
	 * 接收微信系统回调。
	 */
	@RequestMapping("auth2Callback")
	public String auth2Callback(HttpServletRequest request, Map<String, Object> map,HttpSession session) {
		try {
			logger.info(log_Line + "Begin 统一微信授权 common auth2Callback");
			String url = handleAuth2Callback(request, map,Auth2CallUrl,PageTag);
			logger.info(log_Line + "End 统一微信授权 common  auth2Callback");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
}
