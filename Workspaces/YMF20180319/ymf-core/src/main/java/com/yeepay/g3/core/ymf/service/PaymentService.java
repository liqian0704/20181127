package com.yeepay.g3.core.ymf.service;

import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 
* @Title: PaymentService.java
* @Package com.yeepay.g3.core.ymf.service
* @Description: paymentService
* @author dongxu.lu 
* @date 2016年8月25日 上午11:16:00
* @version
 */
@Service
public interface PaymentService {
	/**
     * 新增
     * @param payment
     */
    int save(Payment payment) throws YmfException ;
    /**
     *更新
     * @param payment
     */
    int update(Payment payment)  throws YmfException;

    /**
     *更新
     * @param payment
     */
    int updateById(Payment payment);

    Payment findById(Long id)  throws YmfException;
    
    Payment findByOrderIdAndPayStatus(Long orderId,String payStatus)  throws YmfException;

    /**
     * 查找payment
     * @param customerNumber 商户编号
     * @param yeepayOrderId 交易流水号
     * @return
     */
    Payment findByYeepayOrderId(String customerNumber, String yeepayOrderId);

    /**
     * 根据商户编号和商户订单号查询 PROCESS状态 Payment
     *
     * @param customerNumber 商户编号
     * @param orderId 订单主键
     * @param trxType 交易类型
     * @return List
     */
    List<Payment> queryPayments(String customerNumber, Long orderId, TrxType trxType);

    /**
     * 根据商户编号\商户订单号\交易流水号查询 PROCESS状态 Payment
     *
     * @param customerNumber 商户编号
     * @param orderId 订单主键
     * @param yeepayOrderId 交易流水号
     * @param trxType 交易类型
     * @return List
     */
    List<Payment> queryPayments(String customerNumber, Long orderId, String yeepayOrderId, TrxType trxType);

    /**
     * 根据订单id和支付类型查询支付记录
     * @param id 订单id
     * @param paymentStatus 支付状态 可为null
     * @param trxType 支付类型
     * @return
     * @throws YmfException
     */
	Payment findByOrderIdAndPayStatusAndTrxType(Long id, PaymentStatus paymentStatus, TrxType trxType) throws YmfException;

    /**
	 * 更新支付链接,支付状态，下单请求时间。
	 * @param payment
	 */
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
    List<Payment> findExpriePayments(Date begin, Date end,
                                     TradeSystemEnum tradeSystem,
                                     Integer retryCount,
                                     int rowCount);

    /**
     * 新增查询都邦对账数据方法
     * @param map 查询参数map
     * @return
     */
    List<QueryResultParamDTO> billInfoQuery(HashMap<String, Object> map);

    /**
     * 新增查询时间内交易总金额
     * @param map
     * @return
     */
    BigDecimal totalAmountQuery(HashMap<String, Object> map);

    /**
     * 新增查询时间内交易总笔数
     * @param map
     * @return
     */
    int totalCountQuery(HashMap<String, Object> map);

}
