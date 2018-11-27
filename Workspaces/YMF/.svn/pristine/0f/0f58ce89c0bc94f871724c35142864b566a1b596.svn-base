package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.common.AmountUtil;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierAccountReplyDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.SettleStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.facade.CallbackFromCsprocessService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service
public class CallbackFromCsprocessServiceImpl implements CallbackFromCsprocessService {
	private static Logger logger = LoggerFactory.getLogger(CallbackFromCsprocessServiceImpl.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private TradeYMFbizService tradeYMFbizService;
	@Autowired
	private PaymentService paymentService;



//  Dto字段意义备注
//	private String feeType;
//	private long orderAmount; //订单金额
//	private long sellerFee; //商户手续费
//	private long userFee; //用户手续费
//	private long accountTime; //入账时间   秒数
	
	@Override
	public void purchaseCallback(TradeCashierAccountReplyDTO accountDto) throws Exception {
		try {
			logger.info("---------收到清算入账回调 purchaseCallback Begin");
			if (accountDto == null) {
				throw new Exception("TradeCashierAccountReplyDTO is Null") ;
			}
			logger.info("---------TradeCashierAccountReplyDTO :" + accountDto);

			String customerNumber = accountDto.getMerchantAccount() ;
//			String requestid = accountDto.getCustomerOrderId() ;
			String externalId = accountDto.getAccessOrderId() ;
			if (StringUtils.isBlank(customerNumber) || StringUtils.isBlank(externalId)) {
				throw new Exception("MerchantAccount or accessOrderId is Null") ;
			}

			Order order = orderService.findByCustomerAndExternalId(customerNumber, externalId);
			if (null == order) {
				//TODO后期可以实现邮件提醒或者存到错误订单表
				logger.warn("----------订单不存在");
				return;
			}
			Payment payment = paymentService.findByOrderIdAndPayStatusAndTrxType(order.getId(),null,TrxType.PURCHASE);
			if (payment == null) {
//				logger.warn("----------need next notify 交易回调未返回，需等待下次回调。");
//				throw new Exception("交易回调未返回，需等待交易回调成功后入账。") ;
				logger.error("----------Payment is null");
				return ;
			}
			// 如果订单是已清算终态
			if (SettleStatus.SETTLED.equals(payment.getSettleStatus())) {
				logger.info("-----------订单已经是结算状态,重复通知!");
				return;
			} else {
				BigDecimal fee = AmountUtil.formatLongAmout(accountDto.getSellerFee());//商户手续费，目前只存商户手续费。用户手续费不需要
				payment.setSettleStatus(SettleStatus.SETTLED);
				long accountTime = accountDto.getAccountTime() * 1000 ;//1472697605  到秒需乘以1000
				order.setFee(fee);//TODO 如果有拆单支付，需要累计
				payment.setSettleTime(new Date(accountTime));////入账时间为long型
				payment.setFee(fee);
				tradeYMFbizService.updateOrderAndPayment(order, payment);
				logger.info("---------清算入账回调  End :Update Success");
			}
		} catch (Exception e) {
			logger.error("入账回调处理异常", e);
			throw e ;
		}
	}

	@Override
	public String refundCallback(Map<String, Object> mapParam) {
		return null;
	}

}
