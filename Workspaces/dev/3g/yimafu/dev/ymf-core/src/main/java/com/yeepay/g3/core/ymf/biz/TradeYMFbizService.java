package com.yeepay.g3.core.ymf.biz;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.facade.ymf.exception.YmfException;

/**
 * 
* @Title: TradeYMFbizService.java
* @Package com.yeepay.g3.core.ymf.biz
* @Description: 易码付交易业务服务接口
* @author dongxu.lu 
* @date 2016年8月25日 上午10:49:30
* @version
 */
public interface TradeYMFbizService {
	/**
	 * 更新Order，新增Payment
	* @Title: completeOrderAndPayment
	* @Description: 支付完成后统一修改订单与payment
	* @param @throws YmfException    
	* @return void    
	* @throws
	 */
	public void completeOrderAndPayment(Order order,Payment payment) throws YmfException;
	/**
	 * 
	* @Title: CreateOrderAndPayment
	* @Description: 支付过程payment与order需要统一创建，保存支付过程信息。
	* @param @throws YmfException    
	* @return void    
	* @throws
	 */
	public void createOrderAndPayment(Order order,Payment payment) throws YmfException;
	/**
	 * 更新Order和Payment
	 * @param order
	 * @param payment
	 * @throws YmfException
	 */
	void updateOrderAndPayment(Order order, Payment payment) throws YmfException;
	
	/**
	 * 更新支付地址
	 */
	void updatePayUrlAndYeePayOrderId(Order order, Payment payment) throws YmfException;
	
	
	
}
