package com.yeepay.g3.core.ymf.biz;

import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.junit.RmiContextAwareTest;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.facade.nccashier.enumtype.CashierVersionEnum;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.facade.TradeCashierCallBackFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
	@Test
	public void callCashier(){
			YmfOrderParam orderParam = new YmfOrderParam();
			OrderService orderService = null ; 
			orderService = getBean(OrderService.class);
			Order order = new Order();
			order.setCustomerOrderId("lulutest10001");
			order.setCustomerNumber("10040007800");
			order.setCreateTime(new Date());
			order.setTrxAmt(new BigDecimal("0.01"));
			order.setBusinessType(BusinessType.ORDER_PAY);
			try {
//				orderService.save(order);
				order = orderService.findByCustomerAndRequestID(order.getCustomerNumber(), order.getCustomerOrderId());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("####order:"+order);
			//3.发起订单支付
			TradeCashierRequestDTO tradeCashierRequestDTO = new TradeCashierRequestDTO() ;
			tradeCashierRequestDTO.setMerchantName("保险交易测试商户");//商户名称，用于收银台显示
			tradeCashierRequestDTO.setMerchantAccount(order.getCustomerNumber());
			if(StringUtils.isNotEmpty(orderParam.getOpenId())){
				tradeCashierRequestDTO.setIdentityId(orderParam.getOpenId());//用户唯一标识
			}else{
				tradeCashierRequestDTO.setIdentityId("lulutest123qwe");//用户唯一标识
			}
			tradeCashierRequestDTO.setIdentityType("WECHAT");//用户标识类型，IMEI,  MAC,  USER_ID,  EMAIL,  PHONE,  ID_CARD,  AGREEMENT_NO,  YIBAO; WECHAT 微信
			tradeCashierRequestDTO.setAccessBusinMode("REAL");//交易模式，实时交易or担保交易，默认实时，REAL,GUARANTEE
			tradeCashierRequestDTO.setCashierVersion(CashierVersionEnum.WAP.toString());//指定收银台，CashierVersionEnum
//			tradeCashierRequestDTO.setThemeCustomCode("");//收银台版本，即主题自定义编码 可以不传
//			tradeCashierRequestDTO.setGuaranteeTime(1);//	担保时间，以交易时间为起点，单位为天，默认值是1天
//			tradeCashierRequestDTO.setAutoBindCard(true);//	是否绑卡,默认绑卡
//			tradeCashierRequestDTO.setBizModeCode(bizModeCode);//	收银台模板终端号，即交易模式编码 
//			tradeCashierRequestDTO.setAreaInfo(areaInfo);//	地区码 
//			tradeCashierRequestDTO.setTradeRiskInfo(tradeRiskInfo);//	交易风控参数
//			tradeCashierRequestDTO.setQueryRiskInfo(queryRiskInfo);//	查询限额风控参数
//			tradeCashierRequestDTO.setTerminalInfo("");//	手机信息采集 
//			tradeCashierRequestDTO.setAppInfo(appInfo);//	App信息采集 
			try {
				InetAddress inet = InetAddress.getLocalHost();
				tradeCashierRequestDTO.setUserIp(inet.getHostAddress());// 用户IP，不能空，手机端如何填
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			tradeCashierRequestDTO.setUserUA(UA);//UA
			tradeCashierRequestDTO.setOrderTime(order.getCreateTime());//不能为空
			tradeCashierRequestDTO.setCurrency("CNY");
			Map<String, Object> tradeMap = new HashMap<String, Object>();
			tradeMap.put("callbackUrl", "");//
			tradeMap.put("oldMerchantCode", "");
			Map<String, String> payTypeMap = new HashMap<String, String>();
			/**
			 * 20：wap微信H5支付，21：微信公众号支付，22：微信主扫支付(待定)，23：银行卡WAP支付
			 * TODO 此处先写死，后期再运营后台维护
			 */
			payTypeMap.put("20", "WECHAT_C_PAYMENT");
			payTypeMap.put("21", "WECHAT_A_PAYMENT");
			payTypeMap.put("23", "NC_PAYMENT");
			tradeMap.put("feeProductCodeMapping", payTypeMap);
			String tradeAdditionInfo =  JSON.toJSONString(tradeMap, true);
			tradeCashierRequestDTO.setTradeAdditionInfo(tradeAdditionInfo);//扩展字段 json
//			tradeCashierRequestDTO.setclearAdditionInfo 没有此属性 可以不传
			tradeCashierRequestDTO.setOrderAmount(order.getTrxAmt().multiply(new java.math.BigDecimal("100")).longValue());//金额以分为单位
//			tradeCashierRequestDTO.setSplitAccountInfo(splitAccountInfo);//	分账信息
			tradeCashierRequestDTO.setIndustryCatalog("60");//	商品类别码 mcc码对应一键的映射码  必填
			tradeCashierRequestDTO.setProductName(orderParam.getCustomerName() + "缴费");//商品名称
			tradeCashierRequestDTO.setProductDesc("");//	商品描述
//			tradeCashierRequestDTO.setOrderExpDate("");// 交易订单有效期，单位分钟，默认24小时，最小值是5分钟
			tradeCashierRequestDTO.setCalFeeMode("INNER"); //	计费模式，INNER = 清算中心调用计费子系统计费，OUTER = 外部系统自计费，默认INNER
			tradeCashierRequestDTO.getInnerFee().setFeeBusinType("YMF");
			tradeCashierRequestDTO.setClearBusinType("YMFTRADE");//	清算业务方，如果不传，默认为一键支付 定制去清算中心配置
			tradeCashierRequestDTO.setCustomerOrderId(order.getCustomerOrderId());//商户订单号，不能空
//			if(BusinessType.ORDER_PAY.toString().equals(BusinessType.STANDARD.to){
//				tradeCashierRequestDTO.setCustomerOrderId(order.getCustomerOrderId());//商户订单号，不能空
//			}else if(BusinessType.STANDARD.toString().equals(order.getBusinessType())){
//				tradeCashierRequestDTO.setCustomerOrderId(order.getExternalId());//商户订单号，不能空
//			}
//			tradeCashierRequestDTO.setAccessOrderId(order.getExternalId());//	订单方订单号  非必传
//			tradeCashierRequestDTO.setTradeSysNo("NCTRADE");//不能空 交易系统编码  默认有值
			tradeCashierRequestDTO.setAccessCode("17");//	订单方编码需要 nctrx分配
			String hmac = tradeCashierRequestDTO.signMd5("1oC3L9516894J0jX2k5X7Uh505G9ER");
			tradeCashierRequestDTO.setHmac(hmac); //验签  秘钥如何确认的  nctrx分配
			try {
				TradeCashierFacade tradeCashierFacade=getBean(TradeCashierFacade.class);
//				tradeCashierFacade=RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, TradeCashierFacade.class);
				TradeCashierResponseDTO responseDTO = tradeCashierFacade.purchaseRequest(tradeCashierRequestDTO);
				System.out.println(responseDTO.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
