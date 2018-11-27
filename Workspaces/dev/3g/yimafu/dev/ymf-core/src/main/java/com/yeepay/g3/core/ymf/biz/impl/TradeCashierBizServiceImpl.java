package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.biz.TradeCashierBizService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.facade.nctrade.dto.*;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;
import com.yeepay.g3.facade.nctrade.facade.AlipayAPIRequestFacade;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.nctrade.facade.WeChatAPIPayRequestFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tradeCashierBizService")
public class TradeCashierBizServiceImpl extends SoaBaseBiz implements TradeCashierBizService {
	private static Logger logger = LoggerFactory.getLogger(TradeCashierBizServiceImpl.class);

	TradeCashierFacade tradeCashierFacade;
	WeChatAPIPayRequestFacade weChatAPIPayRequestFacade;
	AlipayAPIRequestFacade alipayAPIRequestFacade;
	@Autowired
	CustomerService  customerService;

	@Override
	public TradeCashierResponseDTO purchaseRequest(TradeCashierRequestDTO reqDTO) throws ParameterInvalidException,
			SystemException {
		TradeCashierResponseDTO responseDTO = null;
		if (null == reqDTO) {
			logger.error("###purchaseRequest requestDTO is null");
			return null;
		}
		logger.info("调用 [收银台下单接口] 请求参数 purchaseRequest param:" + reqDTO.toString());
		long begin = System.currentTimeMillis() ;
		tradeCashierFacade = getService(TradeCashierFacade.class);
		responseDTO = tradeCashierFacade.purchaseRequest(reqDTO);
		long timecost = System.currentTimeMillis() - begin;
		logger.info("调用 [收银台下单接口] 耗时:{}ms 响应参数:{}",timecost,responseDTO);
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
		logger.info("调用 [收银台查单接口] 请求参数:{}",reqDTO);
		responseDTO = tradeCashierFacade.purchaseQuery(reqDTO);
		logger.info("调用 [收银台查单接口] 响应参数:{}",responseDTO);
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
		String customerNumber = reqDTO.getMerchantAccount();
		if(StringUtils.isEmpty(customerNumber)){
			logger.warn("###customerNumber  is Empty!");
			throw new YmfTrxException(TrxCode.T1006,"参数为空");
		}
		logger.info("调用 [收银台微信主扫接口] 请求参数:{}",reqDTO);
		WeChatPayResponseDTO response = weChatAPIPayRequestFacade.activeScanRequest(reqDTO);
		logger.info("调用 [收银台微信主扫接口] 响应参数:{}",response);
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
		logger.info("调用 [收银台支付宝主扫接口] 请求参数:{}",reqDTO);
		AlipayResponseDTO response = alipayAPIRequestFacade.activeScanRequest(reqDTO);
		logger.info("调用 [收银台支付宝主扫接口] 请求参数:{}",response);
		return response;
	}


}
