package com.yeepay.g3.ymf.pay.controller;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 查单支付
 * @author xiaobin.liu
 *
 */
@Controller
@RequestMapping("/orderPay")
public class OrderPayController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(OrderPayController.class) ;
	/**
	 * 订单支付，回调地址
	 */
	private static final String Auth2CallUrl_OrderPay = "/orderPay/auth2Callback" ;
	public static final String indexPage = "orderPay/index" ;
	
	/**
	 * 获取授权
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam("qrCode") String qrCode,
			@RequestParam(value="sign",required=false) String sign, Map<String, Object> map) {
		try {
			logger.info(log_Line + "Begin /orderPay/index 参数 qrCode:" + qrCode + "sign:" + sign);
			checkCodeSign(qrCode,sign) ;
			String url = handerBrowser(request, map, qrCode, sign, Auth2CallUrl_OrderPay,indexPage) ;
			logger.info(log_Line + "End /orderPay/index");
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
	public String auth2Callback(HttpServletRequest request, Map<String, Object> map) {
		try {
			logger.info(log_Line + "Begin /orderPay/auth2Callback 参数:" + request.getQueryString());
			String url = handleAuth2Callback(request, map,Auth2CallUrl_OrderPay,indexPage);
			logger.info(log_Line + "End /orderPay/auth2Callback");
			return url ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}

	/**
	 * 调用Cod查询订单信息。
	 */
	@RequestMapping("/queryCodOrder")
	public @ResponseBody
	JsonResponse queryCodOrder(@RequestParam("customerRequestId") String customerRequestId,
							   @RequestParam("customerNumber") String customerNumber,
							   @RequestParam("qrCode") String qrCode,
							   HttpServletRequest request) {
		logger.info(log_Line + "orderPay/queryCodOrder Query COD Order Begin customerRequestId:" + customerRequestId
		+ " customerNumber :" + customerNumber + " qrCode:" + qrCode);
		JsonResponse json = new JsonResponse() ;
		try {
			if (StringUtils.isEmpty(customerRequestId)) {
				throw new YmfTrxException(TrxCode.T1006,"请输入查单信息") ;
			}
			if (StringUtils.isEmpty(customerNumber)) {
				throw new YmfTrxException(TrxCode.T1006,"商户编号不能为空") ;
			}
			YmfOrderParam sPayQueryOrder = codBiz.queryOrder(customerNumber, customerRequestId,qrCode) ;
//			logger.info("cod code : " + sPayQueryOrder.getResCode());
//			logger.info("cod msg : " + sPayQueryOrder.getResMsg());
			if ("00".equals(sPayQueryOrder.getResCode())) {
				//查单成功
				json.setCode(sPayQueryOrder.getResCode());
				json.setMsg(sPayQueryOrder.getResMsg());
				//对关键信息进行签名
				String orderSign = orderSign(sPayQueryOrder);
				sPayQueryOrder.setOrderSign(orderSign);
				json.setData(sPayQueryOrder);
			} else {
				//显示错误信息
//				json.setCode(sPayQueryOrder.getResCode());
				json.setCode("");
				String msg = sPayQueryOrder.getResMsg() ;
				if (StringUtils.isBlank(msg)) {
					msg = "查单失败" ;
				}
				json.setMsg(msg);
			}
		} catch (Exception e) {
            handleException(e, json);
        }
		logger.info(log_Line + "orderPay/queryCodOrder Query COD Order END");
		return json ;
	}
	
	/**
	 * 跳转到订单信息详情页面
	 */
	@RequestMapping("orderDetail")
	public String orderDetail(HttpServletRequest request, Map<String, Object> map) {
		try {
			logger.info(log_Line + "orderPay/orderDetail");
			return "orderPay/orderDetail" ;
		} catch (Exception e) {
			return toCommonErrorPage(request, handleException(e).toPromptMsg()) ;
		}
	}
	
	/**
	 * 下单发起支付
	 */
	@RequestMapping("doPay")
	public @ResponseBody JsonResponse doPay(HttpServletRequest request, Map<String, Object> map,YmfOrderParam orderParam) {
		JsonResponse json = new JsonResponse() ;
		try {
			logger.info(log_Line + "Begin /orderPay/doPay 参数orderParam:" + orderParam);
			//1.校验订单参数
			orderParam.setBusinessType(BusinessType.ORDER_PAY);
			checkPayParam(orderParam);
			//2.校验订单状态或创建订单
			Customer customer = customerService.findByCustomerNumber(orderParam.getCustomerNumber()) ;
			Order order = null ;
			Payment payment = null ;
			//根据商编和商户订单号确定同一笔订单 需要指定交易类型为订单统一标准版
			Order orgOrder = orderService.findOrderPayOrder(orderParam.getCustomerNumber(),
					orderParam.getCustomerRequestId());

			if (orgOrder == null ) {
				logger.info("Create Order");
				// 创建订单
				order = createPayOrder(orderParam) ;
				payment = createPayment(customer, order, orderParam) ;
				tradeYMFbizService.createOrderAndPayment(order, payment);
			} else {
				order = orgOrder ;
				payment = paymentService.findByOrderIdAndPayStatusAndTrxType(order.getId(), null, TrxType.PURCHASE) ;
				if (PaymentStatus.SUCCESS.equals(payment.getPayStatus())) {
					throw new YmfTrxException(TrxCode.T1008) ;
				} else if (PaymentStatus.SUCCESS.equals(payment.getPayStatus())) {
					throw new YmfTrxException(TrxCode.T1009) ;
				}
				int compare = DateUtil.compareDate(new Date(), payment.getExpireTime()) ;
				if (compare >= 0) {
					throw new YmfTrxException(TrxCode.T1009) ;
				}
				//校验下单是否绑卡,如果有绑卡需要判断请求OpenID
				if (StringUtils.isNotBlank(payment.getOpenId()) && !payment.getOpenId().equals(orderParam.getOpenId())) {
					throw new YmfTrxException(TrxCode.T1010) ;
				}
			}
			//判断是否需要调用接口
			String cashierCallURL ;//支付链接
			if (StringUtils.isEmpty(payment.getPayUrl())) {
				TradeCashierResponseDTO purchaseResp = callCashier(customer, orderParam, order,request);
				//4.处理结果  更新订单对应的支付链接，响应支付链接到前端，通过js调整到收银台链接
				int stat = purchaseResp.getOrderState();
				cashierCallURL =  purchaseResp.getCashierCallURL();
				logger.info(log_Line+"callCashier return stat:"+stat);
				
				if(!StringUtils.isEmpty(cashierCallURL)){
					json.setCode("00");
					json.setData(cashierCallURL);
					payment.setPayUrl(cashierCallURL);
					payment.setPayStatus(PaymentStatus.PROCESS);
					payment.setPayRequestTime(new Date());
					order.setOrderStatus(OrderStatus.PROCESS);
					//支付链接，收银台订单号回写
					tradeYMFbizService.updatePayUrlAndYeePayOrderId(order, payment);
				}else{
//					json.setCode(purchaseResp.getCashierErrorCode());
//					json.setMsg(purchaseResp.getCashierErrorMsg());
					//如果未返回支付链接直接提示下单异常，请稍后重试
					json.setCode(TrxCode.T1011.getCode());
					json.setMsg(TrxCode.T1011.toPromptMsg());
				}
			} else {
				logger.info("Use local payUrl");
				cashierCallURL = payment.getPayUrl() ;
				json.setCode("00");
				json.setData(cashierCallURL);
			}
			logger.info(log_Line + "End /orderPay/doPay");
			return json ;
		} catch (Exception e) {
            return handleException(e,json);
		}
	}

	
	public static void main(String[] args) {
		Date date = new Date() ;
		Date date2 = DateUtil.addMinuteToDate(date, 0) ;
		int compare = DateUtil.compareDate(date, date2) ;
		logger.info("结果：" + compare);
	}

}
