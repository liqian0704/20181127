package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.CustomerSettleInfo2gBiz;
import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettle;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.core.ymf.service.CustomerSettleService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.customer.settle.dto.SettleRecordDTO;
import com.yeepay.g3.facade.customer.settle.dto.SettleRecordParamDTO;
import com.yeepay.g3.facade.customer.settle.dto.SettleRecordResultDTO;
import com.yeepay.g3.facade.customer.settle.enums.RemitStatus;
import com.yeepay.g3.facade.customer.settle.facade.SettleQueryFacade;
import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 同步二代的清算信息
 * Created by yp-tc-m-2762 on 16/10/27.
 */
@Service
public class CustomerSettleInfo2gBizImpl extends SoaBaseBiz implements CustomerSettleInfo2gBiz {

	private static final Logger log = LoggerFactory.getLogger(CustomerSettleInfo2gBizImpl.class);

	private final ThreadLocal<DateFormat> local = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		}
	};

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerSettleService customerSettleService;

	@Autowired
	private CustomerSettleDetailService customerSettleDetailService;

	@Override
	public void customerSettleInfo(Date trxDate) {
		List<Customer> customerList = customerService.getAllCustomerByStatus(Status.ACTIVE);
		if (customerList != null && customerList.size() > 0) {
			log.info("active customer size:" + customerList.size());
			for (Customer customer : customerList) {
				String customerNumber = customer.getCustomerNumber();
				log.info(customerNumber + "settleinfo");
				customerSettleInfo(customerNumber, trxDate);
			}
		}
	}

	@Override
	public void customerSettleInfo(String customerNumber, Date trxDate) {
		Customer customer = customerService.findByCustomerNumber(customerNumber);
		if (null == customer) {
			log.info("商户不存在,编号" + customerNumber);
			throw new IllegalArgumentException("商户不存在,编号" + customerNumber);
		}
		try {
			SettleQueryFacade settleQueryFacade = getService(RemotingProtocol.HESSIAN, SettleQueryFacade.class);
			SettleRecordParamDTO settleRecordParamDTO = new SettleRecordParamDTO();
			settleRecordParamDTO.setCustomerNo(customerNumber);
			settleRecordParamDTO.setStartSettleDate(DateUtil.getMinTime(trxDate));
			settleRecordParamDTO.setEndSettleDate(DateUtil.getMaxTime(trxDate));
			SettleRecordResultDTO settleRecordResultDTO = settleQueryFacade.querySettlementRecord(settleRecordParamDTO);
			if (null != settleRecordResultDTO) {
				CustomerSettle customerSettle = customerSettleService.findByParam(customerNumber, trxDate);
				if (null == customerSettle) {
					customerSettle = new CustomerSettle();
					customerSettle.setCustomerNumber(customerNumber);
					customerSettle.setCreateTime(new Date());
					customerSettle.setTrxDate(trxDate);
					customerSettle.setSettleTimes(0);
					customerSettle.setRealAmount(BigDecimal.ZERO);
					customerSettle.setTrxFee(BigDecimal.ZERO);
					customerSettle.setSettleFee(BigDecimal.ZERO);
				}
				if (settleRecordResultDTO.getStatus().equals("SUCCESS")) {
					List<SettleRecordDTO> recordDTOList = settleRecordResultDTO.getSettleRecordList();
					if (null != recordDTOList && recordDTOList.size() > 0) {
						BigDecimal realAmount = BigDecimal.ZERO;
						BigDecimal trxFee = BigDecimal.ZERO;
						BigDecimal settleFee = BigDecimal.ZERO;
						for (SettleRecordDTO recordDTO : recordDTOList) {
							if (recordDTO.getRemitStatus().equals(RemitStatus.SUCCESS)) {
								realAmount = realAmount.add(recordDTO.getSumNetAmount());
								trxFee = trxFee.add(recordDTO.getSumTrxFee());
								settleFee = settleFee.add(recordDTO.getRemitFee());
							}
						}
						customerSettle.setSettleTimes(recordDTOList.size());
						customerSettle.setRealAmount(realAmount);
						customerSettle.setSettleFee(settleFee);
						customerSettle.setTrxFee(trxFee);
					}
				} else if (settleRecordResultDTO.getStatus().equals("FAIL")) {
					throw new YmfException(ResponseMessage.error("同步商户清算信息失败,商编:" + customerNumber
							+ " message:" + settleRecordResultDTO.getMessage()));
				}
				if (customerSettle.getId() == null) {
					customerSettleService.saveCustomerSettle(customerSettle);
				} else {
					customerSettle.setUpdateTime(new Date());
					customerSettleService.update(customerSettle);
				}
				log.info("同步商户T1清算信息完成,商编" + customerNumber + ",时间" + local.get().format(trxDate));
			}
		} catch (YmfException e) {
			log.error(e.getResp().getMsg(), e);
		} catch (Throwable e) {
			log.error("同步商户清算信息失败, 商编" + customerNumber, e);
		}
	}
}
