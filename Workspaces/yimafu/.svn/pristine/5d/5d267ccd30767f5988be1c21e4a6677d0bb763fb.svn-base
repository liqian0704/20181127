package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.facade.nctrade.dto.*;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;
import com.yeepay.g3.facade.nctrade.facade.AlipayAPIRequestFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.nctrade.facade.WeChatAPIPayRequestFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.TradeCashierBizService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("tradeCashierBizService")
public class TradeCashierBizServiceImpl extends SoaBaseBiz implements TradeCashierBizService {
	private static Logger logger = LoggerFactory.getLogger(TradeCashierBizServiceImpl.class);

	TradeCashierFacade tradeCashierFacade;
	WeChatAPIPayRequestFacade weChatAPIPayRequestFacade;
	AlipayAPIRequestFacade alipayAPIRequestFacade;

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

	@Override
    public WeChatPayResponseDTO scanWXOrderQRCodeBySelf(WeChatPayRequestDTO reqDTO) throws ParameterInvalidException, SystemException, YmfTrxException {
        weChatAPIPayRequestFacade = getService(WeChatAPIPayRequestFacade.class);
        if(null == weChatAPIPayRequestFacade){

			logger.warn("###获取远程服务失败 服务接口:weChatAPIPayRequestFacade");
			throw new YmfTrxException(TrxCode.T1014);
		}
		if(null==reqDTO){
			logger.warn("###scanWXOrderQRCodeBySelf WeChatPayRequestDTO is null!");
			throw new YmfTrxException(TrxCode.T1006,"参数为空");
		}
		WeChatPayResponseDTO response = weChatAPIPayRequestFacade.activeScanRequest(reqDTO);
        return response;
    }

	@Override
	public AlipayResponseDTO scanALPAYOrderQRCodeBySelf(AlipayRequestDTO reqDTO) throws ParameterInvalidException, SystemException, YmfTrxException {
		alipayAPIRequestFacade = getService(AlipayAPIRequestFacade.class);

		if(null == alipayAPIRequestFacade){
			logger.warn("###获取远程服务失败 服务接口:alipayAPIRequestFacade");
			throw new YmfTrxException(TrxCode.T1014);
		}
		if(null==reqDTO){
			logger.warn("###scanALPAYOrderQRCodeBySelf AlipayRequestDTO is null!");
			throw new YmfTrxException(TrxCode.T1006,"参数为空");
		}
		AlipayResponseDTO response = alipayAPIRequestFacade.activeScanRequest(reqDTO);
		return response;
	}


}
