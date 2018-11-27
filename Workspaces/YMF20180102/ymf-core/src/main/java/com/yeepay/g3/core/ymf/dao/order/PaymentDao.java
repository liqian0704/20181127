package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDao {
	
	Payment findByOrderIdAndPayStatus(@Param("orderId")Long orderId,@Param("payStatus")String payStatus);
	Payment findByOrderIdAndPayStatusAndTrxType(@Param("orderId")Long orderId,@Param("payStatus")String payStatus,@Param("trxType")String trxType);
	void updatePayUrlById(Payment payment) ;

	/**
	 * 查询符合条件的payment订单
	 * @param begin		创建时间，开始
	 * @param end		创建时间，结束
	 * @param tradeSystem	订单下单系统
	 * @param retryCount	重试次数
	 * @param rowCount		记录最大笔数
	 * @return
	 */
	List<Payment> findExpriePayments(@Param("begin")Date begin, @Param("end")Date end,
									 @Param("tradeSystem")TradeSystemEnum tradeSystem,
									 @Param("retryCount")Integer retryCount,
									 @Param("rowCount")int rowCount);

}