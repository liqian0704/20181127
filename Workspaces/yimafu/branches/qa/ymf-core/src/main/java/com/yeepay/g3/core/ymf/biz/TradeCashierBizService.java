package com.yeepay.g3.core.ymf.biz;

import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException;
import com.yeepay.g3.facade.nctrade.exception.SystemException;

/**
 * 
* @Title: TradeCashierBizService.java
* @Package com.yeepay.g3.core.ymf.biz
* @Description: 统一收银台业务服务入口
* @author dongxu.lu 
* @date 2016年8月18日 上午10:49:45
* @version
 */
public interface TradeCashierBizService {
	/**
	 * 
	* @Title: purchaseRequest
	* @Description: 统一收银台消费请求
	* @param @param reqDTO
	* @param @return
	* @param @throws ParameterInvalidException
	* @param @throws SystemException    
	* @return TradeCashierResponseDTO    
	* @throws
	 */
	public TradeCashierResponseDTO purchaseRequest(TradeCashierRequestDTO reqDTO) throws ParameterInvalidException, SystemException;
	/**
	 * 支付查询接口
	 * @param reqDTO
	 * @return
	 * @throws ParameterInvalidException
	 */
	public TradeCashierReplyDTO purchaseQuery(TradeCashierBaseDTO reqDTO) throws ParameterInvalidException;

	/**
	 * 补单接口
	 * @param reqDTO
	 * @return
	 * @throws ParameterInvalidException
	 */
	public TradeCashierReplyDTO purchaseSupply(TradeCashierBaseDTO reqDTO) throws ParameterInvalidException;
}
