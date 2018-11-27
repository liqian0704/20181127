package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.TradeCashierBizService;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("tradeCashierBizService")
public class TradeCashierBizServiceImpl extends SoaBaseBiz implements TradeCashierBizService {
	private static Logger logger = LoggerFactory.getLogger(TradeCashierBizServiceImpl.class);

	TradeCashierFacade tradeCashierFacade;

	@Override
	public TradeCashierResponseDTO purchaseRequest(TradeCashierRequestDTO reqDTO) throws ParameterInvalidException,
			SystemException {
		TradeCashierResponseDTO responseDTO = null;
		if (null == reqDTO) {
			logger.error("###purchaseRequest requestDTO is null");
			return null;
		}
		logger.debug("###TradeCashier purchaseRequest param:" + reqDTO.toString());
		 tradeCashierFacade = getService(TradeCashierFacade.class);
		responseDTO = tradeCashierFacade.purchaseRequest(reqDTO);
		return responseDTO;
	}

	@Override
	public TradeCashierReplyDTO purchaseQuery(TradeCashierBaseDTO reqDTO) throws ParameterInvalidException {
		TradeCashierReplyDTO responseDTO = null;
		if (null == reqDTO) {
			logger.error("###purchaseQuery requestDTO is null");
			return null;
		}
		logger.debug("###TradeCashier purchaseQuery param: " + reqDTO.toString());
		tradeCashierFacade = getService(TradeCashierFacade.class);
		responseDTO = tradeCashierFacade.purchaseQuery(reqDTO);
		return responseDTO;
	}

	@Override
	public TradeCashierReplyDTO purchaseSupply(TradeCashierBaseDTO reqDTO) throws ParameterInvalidException {

		return null;
	}
}
