package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.support.NCApiSupport;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.facade.TradeCashierCallBackFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeCashierCallBackFacadeImpl implements TradeCashierCallBackFacade {
	private static Logger logger = LoggerFactory.getLogger(TradeCashierCallBackFacadeImpl.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CodBiz codBiz;
	@Autowired
	private TradeYMFbizService tradeYMFbizService;

	@Override
	public void purchaseCallBack(TradeCashierReplyDTO dto) throws Exception {
		logger.info("----------收到交易通知回调 purchaseCallBack begin");
		try {
			if (null == dto) {
				logger.info("###TradeCashierReplyDTO is null!");
				throw new Exception("TradeCashierReplyDTO is null!");
			}
			logger.info("----------收到交易通知参数 TradeCashierReplyDTO:" + dto);
			// 验证签名
			String hmac = dto.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class));
			if (StringUtils.isEmpty(hmac)) {
				logger.error("### signHmac error hmac: " + hmac);
				throw new Exception("hmac is null");
			} else if (!hmac.equals(dto.getHmac())) {
				logger.error("### check hmac error: src hmac:" + dto.getHmac() + "  new hmac:" + hmac);
				throw new Exception("check hmac error");
			}
//			String requestId = dto.getCustomerOrderId();
			String externalId = dto.getAccessOrderId() ;
			String customerNumber = dto.getMerchantAccount();
			logger.info("### query order param:" + externalId);
			// 查出原订单 对标准版查询不到
			Order order = orderService.findByCustomerAndExternalId(customerNumber,externalId);
			if (null == order) {
				logger.error("订单不存在,customerNumber:" + customerNumber + ",requestID:" + dto.getCustomerOrderId()
				+ "externalId:" + externalId);
				return;
			}
			// 如果订单已经成功直接返回不做处理
			if (OrderStatus.SUCCESS.equals(order.getOrderStatus())) {
				logger.info("重复通知,订单已经支付成功。 ,externalId:" + order.getExternalId() + ",customerOrderId:"
						+ order.getCustomerOrderId());
				return ;
				// 如果失败或者取消
			} else if (OrderStatus.FAIL.equals(order.getOrderStatus())) {
				logger.info("订单已经超时失败 ,externalId:" + order.getExternalId() + ",customerOrderId:"
						+ order.getCustomerOrderId());
				return ;
			}
			NCApiSupport.prepareOrder(dto, order);

			Customer customer = customerService.findByCustomerNumber(customerNumber);
			Payment payment = paymentService.findByOrderIdAndPayStatusAndTrxType(order.getId(),null,TrxType.PURCHASE);
//			Payment payment = paymentService.findByOrderIdAndPayStatus(order.getId(), PaymentStatus.SUCCESS.toString());
			if (payment == null) {
				//是在交易的时候创建，为空肯定不可能
				logger.info("Payment不存在 ,externalId:" + order.getExternalId() + ",customerOrderId:"
						+ order.getCustomerOrderId());
				return ;
			} else {
				//payment 已经存在，暂时不考虑拆单问题.
//				logger.info("payment is exists");
				//如果订单为不成功，payment也不可能为成功，所以先不判断payment状态
				payment = NCApiSupport.preparePayment(payment,dto) ;
			}
			
			tradeYMFbizService.updateOrderAndPayment(order, payment);
			logger.info("------ 更新订单成功,externalId:" + order.getExternalId() + ",customerOrderId:"
					+ order.getCustomerOrderId());
			// 行业版需要通知,改为全部交易都通知COD
			codBiz.orderPayNotify(payment,customer, order);
			logger.info("------ Cod通知成功,externalId:" + order.getExternalId() + ",customerOrderId:"
					+ order.getCustomerOrderId());
		} catch (Exception e) {
			logger.error("error :",e);
			throw e ;
		}
	}


	
//	TradeCashierReplyDTO:{"phone":"null","orderState":"1","identityType":"WECHAT","identityId":"o3UDHvw9DqsHUO7JMlFkg8bITbaI","bindId":"0","tradeSysOrderId":411608305764059041,"idcard":"null","bankName":"","tradeSysNo":"NCTRADE","accessCode":"17","payTime":"2016-08-30 16:04:25","cardType":"CFT","customerOrderId":"78000830005","merchantAccount":"10040007800","cardNo":"null","closeTime":"2016-08-30 16:14:33","hmac":"8d931d0dc05c997ebcb2382454c15c35","accessOrderId":"411608305764059041","orderAmount":1,"cashierCallUrl":"http://172.21.0.83:8008/nc-cashier-wap/wap/request/10040007800/zY33*zVWg05yivjxzocTJA%3D%3D","owner":"null","payType":21}
	
}
