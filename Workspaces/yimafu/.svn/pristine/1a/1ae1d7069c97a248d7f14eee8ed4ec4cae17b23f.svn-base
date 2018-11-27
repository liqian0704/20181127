package com.yeepay.g3.core.ymf.biz;

import com.yeepay.g3.core.ymf.junit.RmiContextAwareTest;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.ymf.facade.TradeCashierCallBackFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {""})
public class TradeCashierCallBackFacadeTest extends RmiContextAwareTest {
	
	public void callBackTest() {
		TradeCashierCallBackFacade service = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, TradeCashierCallBackFacade.class);
		TradeCashierReplyDTO  params = new  TradeCashierReplyDTO();
	    params.setMerchantAccount("10040012312");
	    try {
			service.purchaseCallBack(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
