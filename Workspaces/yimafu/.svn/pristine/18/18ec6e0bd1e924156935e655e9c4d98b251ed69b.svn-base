package com.yeepay.g3.core.ymf.dao.order;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.ymf.entity.order.Payment;

@Repository
public interface PaymentDao {
	
	Payment findByOrderIdAndPayStatus(@Param("orderId")Long orderId,@Param("payStatus")String payStatus);
	Payment findByOrderIdAndPayStatusAndTrxType(@Param("orderId")Long orderId,@Param("payStatus")String payStatus,@Param("trxType")String trxType);
	void updatePayUrlById(Payment payment) ;

}