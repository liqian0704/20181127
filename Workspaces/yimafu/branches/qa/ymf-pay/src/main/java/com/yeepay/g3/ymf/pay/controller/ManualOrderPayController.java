package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 标准版支付
 * @author xiaobin.liu
 *
 */
@Controller
@RequestMapping("/manualOrder")
public class ManualOrderPayController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(ManualOrderPayController.class) ;
	/**
	 * 标准版，回调地址
	 */
	private static final String Auth2CallUrl = "/manualOrder/auth2Callback" ;
	/**
	 * 支付首页面
     */
	public static final String indexPage = "manualOrder/pay" ;
	
	/**
	 * 获取授权
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam("qrCode") String qrCode,
			@RequestParam(value="sign",required=false) String sign, Map<String, Object> map) {
		try {
			logger.info(log_Line + "Begin 手工订单支付 manualOrder index qrCode:" + qrCode + "sign:" + sign);
			checkCodeSign(qrCode,sign) ;
			String url = handerBrowser(request, map, qrCode, sign, Auth2CallUrl,indexPage) ;
			logger.info(log_Line + "End 手工订单支付 manualOrder index");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}

	/**
	 * 获取openId ；
	 * 接收微信系统回调。
	 */
	@RequestMapping("auth2Callback")
	public String auth2Callback(HttpServletRequest request, Map<String, Object> map,HttpSession session) {
		try {
			logger.info(log_Line + "Begin 手工订单支付 manualOrder auth2Callback");
			String url = handleAuth2Callback(request, map,Auth2CallUrl,indexPage);
			logger.info(log_Line + "End 手工订单支付 manualOrder  auth2Callback");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	public static void main(String[] args) {

//		String openIdUrl ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppID&secret=SECRET&grant_type=authorization_code&code=CODE";
//		openIdUrl.replace("AppID", "wx26f6a9008c72e75b" );
//		openIdUrl.replace("SECRET", "a7fec9e9d7666bbef92264ec518bbcc7");
//		openIdUrl.replace("CODE", "32232");
//		logger.info("openIdUrl:" + openIdUrl);
	
	}
	/**
	 * 下单发起支付
	 */
	@RequestMapping("doPay")
	public @ResponseBody JsonResponse doPay(HttpServletRequest request,YmfOrderParam orderParam) {
		JsonResponse json = new JsonResponse() ;
		try {
			logger.info(log_Line + "Begin 手工订单支付 manualOrder doPay 参数:" + orderParam);
			orderParam.setBusinessType(BusinessType.MANUAL_ORDER);
			//1.校验订单参数
			checkPayParam(orderParam);
			//2.校验订单状态或创建订单
			Customer customer = customerService.findByCustomerNumber(orderParam.getCustomerNumber()) ;
			// 创建订单
			Order order = createPayOrder(orderParam) ;
			Payment payment = createPayment(customer, order, orderParam) ;
			tradeYMFbizService.createOrderAndPayment(order, payment);
			logger.info(log_Line + "Create Order Success ExtenalId=" + order.getExternalId()
			+ " 商户订单号:" + order.getCustomerOrderId());
			//3.发起订单支付
			TradeCashierResponseDTO purchaseResp = callCashier(customer, orderParam, order,request) ;
			//4.处理结果  更新订单对应的支付链接，响应支付链接到前端，通过js调整到收银台链接
			int stat = purchaseResp.getOrderState();
			String cashierCallURL =  purchaseResp.getCashierCallURL();
			logger.info(log_Line+"callCashier return stat:"+stat);
			if(StringUtils.isNotEmpty(cashierCallURL)){
				json.setCode("00");
				json.setData(purchaseResp.getCashierCallURL());
				
				payment.setPayUrl(cashierCallURL);
				payment.setPayStatus(PaymentStatus.PROCESS);
				payment.setPayRequestTime(new Date());
				order.setOrderStatus(OrderStatus.PROCESS);
				//支付链接，收银台订单号回写
				tradeYMFbizService.updatePayUrlAndYeePayOrderId(order, payment);
			} else {
//				json.setCode(purchaseResp.getCashierErrorCode());
//				json.setMsg(purchaseResp.getCashierErrorMsg());
				//如果未返回支付链接直接提示下单异常，请稍后重试
				json.setCode("80");
				json.setMsg("下单异常，请稍后重试");
			}
			logger.info(log_Line + "End 手工订单支付 manualOrder doPay");
			return json ;
		} catch (Exception e) {
			return handleException(e,json) ;
		}
	}

}
