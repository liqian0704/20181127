package com.yeepay.g3.core.ymf.biz.cod;

import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;

/**
 * Cod系统业务接口
 * @author xiaobin.liu
 */
public interface CodBiz {

	/**
	 * Cod订单查询
	 * @param customerNumber
	 * @param orderNo
	 */
	YmfOrderParam queryOrder(String customerNumber, String orderNo,String qrId) throws Exception;

	/**
	 * Cod订单支付成功通知
	 * @param payment
	 * @param customer
	 * @param order
	 */
	void orderPayNotify(Payment payment, Customer customer, Order order);
	

	void testInterface();

}
