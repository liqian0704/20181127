package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeYMFbizServiceImpl implements TradeYMFbizService {
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
	public void completeOrderAndPayment(Order order, Payment payment) throws YmfException {
		if (null == order || null == payment) {
			throw new YmfException(ResponseMessage.error("completeOrderAndPayment param is null"));
		}
		orderService.update(order);
//		paymentService.update(payment);
		paymentService.save(payment) ;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
	public void createOrderAndPayment(Order order, Payment payment) throws YmfException {
		if (null == order || null == payment) {
			throw new YmfException(ResponseMessage.error("createOrderAndPayment param is null"));
		}
		orderService.save(order) ;
		payment.setOrderId(order.getId());
		if (StringUtils.isBlank(payment.getCustomerOrderId())) {
			payment.setCustomerOrderId(order.getCustomerOrderId());
		}
		paymentService.save(payment) ;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
	public void updateOrderAndPayment(Order order, Payment payment) throws YmfException {
		if (null == order || null == payment) {
			throw new YmfException(ResponseMessage.error("updateOrderAndPayment param is null"));
		}
		orderService.update(order);
		paymentService.update(payment);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
	public void updatePayUrlAndYeePayOrderId(Order order, Payment payment) throws YmfException {
		if (null == order || null == payment) {
			throw new YmfException(ResponseMessage.error("updateOrderAndPayment param is null"));
		}
		paymentService.updatePayUrlById(payment);
		//订单状态改为process
		orderService.updateOrderStatusById(order) ;
	}

}
