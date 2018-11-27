package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.order.PaymentDao;
import com.yeepay.g3.core.ymf.dao.order.PaymentMapper;
import com.yeepay.g3.core.ymf.dao.order.YmfBillMapper;
import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.order.PaymentParam;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private PaymentMapper paymentMapper;
	@Autowired
	private YmfBillMapper ymfBillMapper;

	@Override
	@Transactional(rollbackFor = Exception.class,timeout=30)
	public int save(Payment payment) throws YmfException {
		int result = 0;
		try {
			paymentMapper.insert(payment);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			throw new YmfException(e,ResponseMessage.error("PaymentService.insert exception"));
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class,timeout=30)
	public int update(Payment payment) throws YmfException {
		int result = 0;
		try {
			result = paymentMapper.updateByPrimaryKey(payment);
		} catch (Throwable e) {
			
			throw new YmfException(e,ResponseMessage.error("PaymentService.update exception"));
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int updateById(Payment payment) {
		return paymentMapper.updateByPrimaryKey(payment);
	}

	@Override
	public Payment findById(Long id) throws YmfException {
		Payment payment = null;
		try {
			payment = paymentMapper.selectByPrimaryKey(id);
		} catch (Throwable e) {
			throw new YmfException(e,ResponseMessage.error("PaymentService.selectByPrimaryKey exception"));
		}
		
		return payment;
	}

	@Override
	public Payment findByOrderIdAndPayStatus(Long id, String payStatus)
			throws YmfException {
		Payment payment=null;
		try {
			payment = paymentDao.findByOrderIdAndPayStatus(id, payStatus);
		} catch (Throwable e) {
			throw new YmfException(e, ResponseMessage.error("PaymentService.findByOrderIdAndPayStatus exception"));
		}
		return payment;
	}
	
	@Override
	public Payment findByOrderIdAndPayStatusAndTrxType(Long id, PaymentStatus paymentStatus,TrxType trxType)
			throws YmfException {
		Payment payment=null;
		try {
			String paymentState = null ;
			if (paymentStatus != null) {
				paymentState = paymentStatus.toString() ;
			}
			String trxTypeStr = null ;
			if (trxType != null) {
				trxTypeStr = trxType.toString() ;
			}
			payment = paymentDao.findByOrderIdAndPayStatusAndTrxType(id, paymentState,trxTypeStr);
		} catch (Throwable e) {
			throw new YmfException(e, ResponseMessage.error("PaymentService.findByOrderIdAndPayStatus exception"));
		}
		return payment;
	}

	@Override
	public Payment findByYeepayOrderId(String customerNumber, String yeepayOrderId) {
		PaymentParam param = new PaymentParam();
		param.createCriteria().andYeepayOrderIdEqualTo(yeepayOrderId).andCustomerNumberEqualTo(customerNumber);
		List<Payment> paymentList = paymentMapper.selectByFilter(param);
		if (null != paymentList && paymentList.size() > 0) {
			return paymentList.get(0);
		}
		return null;
	}

	@Override
	public List<Payment> queryPayments(String customerNumber, Long orderId, TrxType trxType) {
		PaymentParam param = new PaymentParam();
		param.createCriteria().andCustomerNumberEqualTo(customerNumber)
				.andOrderIdEqualTo(orderId)
				.andTrxTypeEqualTo(trxType);
		return paymentMapper.selectByFilter(param);
	}

	@Override
	public List<Payment> queryPayments(String customerNumber, Long orderId, String yeepayOrderId, TrxType trxType) {
		PaymentParam param = new PaymentParam();
		param.createCriteria().andCustomerNumberEqualTo(customerNumber)
				.andOrderIdEqualTo(orderId)
				.andYeepayOrderIdEqualTo(yeepayOrderId)
				.andTrxTypeEqualTo(trxType);
		return paymentMapper.selectByFilter(param);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void updatePayUrlById(Payment payment) {
		paymentDao.updatePayUrlById(payment);
	}

	/**
	 * 查询符合条件的payment订单
	 *
	 * @param begin       创建时间，开始
	 * @param end         创建时间，结束
	 * @param tradeSystem 订单下单系统
	 * @param retryCount  重试次数
	 * @param rowCount    记录最大笔数
	 * @return
	 */
	@Override
	public List<Payment> findExpriePayments(Date begin, Date end, TradeSystemEnum tradeSystem,
											Integer retryCount, int rowCount) {
		if (begin == null || end == null) {
			throw new RuntimeException("时间必填");
		}
		return paymentDao.findExpriePayments(begin, end, tradeSystem, retryCount, rowCount);
	}

	@Override
	public List<QueryResultParamDTO> billInfoQuery(HashMap<String, Object> map) {
		return ymfBillMapper.getYmfBillInfoByMap(map);
	}

	@Override
	public BigDecimal totalAmountQuery(HashMap<String, Object> map) {
		return ymfBillMapper.getTotalAmountByMap(map);
	}

	@Override
	public int totalCountQuery(HashMap<String, Object> map) {
		return ymfBillMapper.getTotalCountByMap(map);
	}
}
