package com.yeepay.g3.facade.ymf.facade;

import java.util.Map;

import com.yeepay.g3.facade.nctrade.dto.TradeCashierAccountReplyDTO;

/**
 * 
* @Title: CallbackFromCsprocessService.java
* @Package com.yeepay.g3.facade.ymf.facade
* @Description: 清算中心业务方
* @author dongxu.lu 
* @date 2016年8月25日 下午7:43:06
* @version
 */
public interface CallbackFromCsprocessService {
	
	/**
	 * @Description: 清算中心订单入账回调
	 * @param @param mapParam    
	 * @return void    
	 * @throws
	 */
	public void purchaseCallback(TradeCashierAccountReplyDTO accountDto) throws Exception;
	/**
	 * 
	* @Title: refund
	* @Description: 清算中心退款
	* @param @param mapParam    
	* @return void    
	* @throws
	 */
	public String refundCallback(Map<String, Object> mapParam);

}
