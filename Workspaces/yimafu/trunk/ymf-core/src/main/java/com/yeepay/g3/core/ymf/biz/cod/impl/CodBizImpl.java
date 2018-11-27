package com.yeepay.g3.core.ymf.biz.cod.impl;

import com.yeepay.cod.hessian.beans.OrderPayParam;
import com.yeepay.cod.hessian.beans.OrderResultParam;
import com.yeepay.cod.hessian.beans.QueryOrderParam;
import com.yeepay.cod.hessian.service.CodPOSPIndRemoteService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfoParam;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.cod.CodNotifyInfoService;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.serialize.JsonMarshaller;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.sp.NotifyType;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * COD接口
 * @author xiaobin.liu
 *
 */
@Lazy
@Service
public class CodBizImpl implements CodBiz {
	private static final Logger log = LoggerFactory.getLogger(CodBizImpl.class) ;
	/**
	 * 扫码支付，虚拟posSN,12位
	 */
	private static final String spayPosSn = "999999999999" ;//周灏要求  999999999999
	/**
	 * 扫码支付，虚拟pos机编号
	 */
	private static final String spayPosCati = "11111111" ;
	/**
	 * Pos Menu :订单收款标准版
	 */
	public static final int POS_MENU_ORDER = 3;
	/**
	 * Pos Menu:无订单收款
     */
	public static final int POS_MENU_NO_ORDER = 1;
	/**
	 * 统一配置键值: 终端号映射表 键值
     */
	private static final String CONFIG_YMF_CATI = "YMF_CATI" ;
	/**
	 * 统一配置键值: 终端号映射开关
     */
	private static final String CONFIG_YMF_CATI_SWITCH = "YMF_CATI_SWITCH" ;
	/**
	 * Pos Menu:手工订单收款
     */
	public static final int POS_MENU_MANUAL_ORDER = 1;
	public static final String COD_INSURANCE_QUERY= "207";							//保险-保单查询
	public static final String RESPONSE_CODE_SUCCESS = "00";
	@Autowired(required = false)
	private CodPOSPIndRemoteService codPOSPIndRemoteService ;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CodNotifyInfoService codNotifyInfoService;

	/**
	 * 将qrid转换成功配置的值,作为终端号
	 * @param qrId		二维码id
	 * @return			统一配置中的值
     */
	private String swithQrId(String qrId) {
		String isUse = ConfigureSetting.getValue(CONFIG_YMF_CATI_SWITCH, "");
		if ("true".equals(isUse)) {
			HashMap<String, String> posCatiMap = ConfigureSetting.getValue(CONFIG_YMF_CATI, new HashMap<String, String>());
			if (posCatiMap != null && !posCatiMap.isEmpty() && posCatiMap.containsKey(qrId)) {
				log.info("QrId:" + qrId + "切换成:" + posCatiMap.get(qrId));
				return posCatiMap.get(qrId) ;
			}
		}
		return qrId ;
	}

    @Override
	public YmfOrderParam queryOrder(String customerNumber,String orderNo,String qrId) throws Exception {
		log.info("######## YMF call COD ,execute CodPOSPIndRemoteService.queryOrder ########");
		//TODO 后期如果有多个行业时，先查询商户所在行业
		QueryOrderParam queryOrderParam = new QueryOrderParam();
		//防止cod报错，固定写123456
		queryOrderParam.setEmployeeId("123456");
		queryOrderParam.setPosId(spayPosSn);
		// TODO 映射终端号
		queryOrderParam.setPosTerminal(swithQrId(qrId));
		Customer customer = customerService.findByCustomerNumber(customerNumber) ;

		if (customer == null) {
			throw new YmfTrxException(TrxCode.T1003) ;
		}
		queryOrderParam.setCustomerName(customer.getCustomerName());
		// #######payMeune更改
		// POS菜单
		queryOrderParam.setPosMenue(POS_MENU_ORDER);

		//商户编号
		queryOrderParam.setCustomerCode(customerNumber);
		//订单号
		queryOrderParam.setOrderNo(orderNo);

		log.info("YMF request param:" + queryOrderParam.toString());
		List<OrderResultParam> orderResults = new ArrayList<OrderResultParam>();
		long begin = System.currentTimeMillis();
		try {
			orderResults = codPOSPIndRemoteService.queryOrder(queryOrderParam);
		} catch (Exception e) {
			log.error("接口耗时:" + (System.currentTimeMillis() - begin) + ",Cod Query Exception：",e);
			throw new YmfTrxException(TrxCode.T1007,"请稍后重试") ;
		}
		log.info("COD response param: orderResults.size=" + orderResults.size() + ",接口耗时:" + (System.currentTimeMillis() - begin));
		if (orderResults.size() > 0) {
			log.info("COD返回数据:" + orderResults.get(0));
		}
		YmfOrderParam spayOrder = new YmfOrderParam() ;
		spayOrder.setCustomerNumber(customerNumber);
		spayOrder.setCustomerName(customer.getCustomerName());
		spayOrder.setCustomerKey(customer.getCustomerName());
		spayOrder.setCustShotName(customer.getCustomerName());
		return orders2SpayOrder(orderResults, orderNo,spayOrder,COD_INSURANCE_QUERY) ;
	}

	/**
	 * Cod查询结果转换为YmfOrderParam
	 */
	private YmfOrderParam orders2SpayOrder(List<OrderResultParam> orderResultParams,String orderNo,
			YmfOrderParam spayOrder,String industry) {

		// 如果订单未查到返回80编码错误
		if (orderResultParams == null || orderResultParams.size() == 0) {// 查询订单不存在
			spayOrder.setResCode("80");
			spayOrder.setResMsg("没有查询到订单");
			return spayOrder;
		} else if ("24".equals(orderResultParams.get(0).getOrderStatus())) {// 异常订单
			spayOrder.setResCode("24");
			spayOrder.setResMsg("异常单："
					+ orderResultParams.get(0).getOrderStatusMsg());
			return spayOrder;
		} else if ("20".equals(orderResultParams.get(0).getOrderStatus())) {// 异常订单
			spayOrder.setResCode("20");
			spayOrder.setResMsg("该订单已支付");
			return spayOrder;
		} else if ("000001".equals(orderResultParams.get(0).getOrderStatus())) {// 不是已核投保单
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000002".equals(orderResultParams.get(0).getOrderStatus())) {// 正在扣款状态、扣款成功、扣款状态错误
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000003".equals(orderResultParams.get(0).getOrderStatus())) {// 生效日期不对，投保单不允许倒签单!
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000004".equals(orderResultParams.get(0).getOrderStatus())) {// 该单不是人民币业务!
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000005".equals(orderResultParams.get(0).getOrderStatus())) {// 该单金额必须大于0
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000006".equals(orderResultParams.get(0).getOrderStatus())) {// 同保投保单不存在或已注销
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000007".equals(orderResultParams.get(0).getOrderStatus())) {// 30关联投保单不存在或已注销
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000008".equals(orderResultParams.get(0).getOrderStatus())) {// 车+意业务关联投保单不存在或已注销
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000010".equals(orderResultParams.get(0).getOrderStatus())) {// 已过有效期，投保单不能进行付款!
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		} else if ("000015".equals(orderResultParams.get(0).getOrderStatus())) {// 已过有效期，投保单不能进行付款!
			spayOrder.setResCode("71");
			spayOrder.setResMsg(orderResultParams.get(0).getExceptionMsg());
			return spayOrder;
		}

		OrderResultParam orderResultParam = orderResultParams.get(0);
		// log.info(orderResultParam.toString());
		StringBuilder orderNos = new StringBuilder();
		for (OrderResultParam oparam : orderResultParams) {
			orderNos.append("|");
			orderNos.append(oparam.getOrderNo());
		}
		if (orderResultParam.getExceptionCode() != null
				&& orderResultParam.getExceptionCode() == 81) {
			spayOrder.setResCode("81");
			spayOrder.setResMsg(orderResultParam.getExceptionMsg());
			return spayOrder;
		}
		// // 根据不同的行业组装报文
		if (COD_INSURANCE_QUERY.equals(COD_INSURANCE_QUERY)) {
			//先写死，只传保险
			spayOrder.setReceiverName(orderResultParam.getReceiverName());
			spayOrder.setRemark(orderResultParam.getRemark());
			spayOrder.setCodOrderNo(orderResultParam.getOrderNo());
		} else {
			spayOrder.setReceiverName(orderResultParam.getReceiverName());
			spayOrder.setCodOrderNo(orderResultParam.getCodExternalId());
		}
		spayOrder.setReceiverTel(orderResultParam.getReceiverTel());
		spayOrder.setReceiverAddr(orderResultParam.getReceiverAddr());
		spayOrder.setPayMode(orderResultParam.getPayMode());
		spayOrder.setSignStatus(orderResultParam.getReceiverState());
		spayOrder.setPayStatus(orderResultParam.getPayState());
		if (orderResultParam.getPayAmount() != null) {
			String paidAmountStr = formatAmountToString(orderResultParam.getPayAmount());
			spayOrder.setPayAmt(paidAmountStr);
		}
		if (orderResultParam.getPayChannel() != null) {
			spayOrder.setPayChannel(String.valueOf(orderResultParam.getPayChannel())); // 支付通道
		}
		spayOrder.setPayDetail(orderResultParam.getPayDetail()); // 支付明细

		if (orderResultParam.getReceiverDate() != null) {
			spayOrder.setSignInDate(DateUtil.formatDate(orderResultParam.getReceiverDate()));
		}
		if (orderResultParam.getPayDate() != null) {
			spayOrder.setPayDate(DateUtil.formatDate(orderResultParam.getPayDate()));
		}
		spayOrder.setSendNotice(orderResultParam.getReceiverTerm());
		spayOrder.setSendCompanyBarCode(orderResultParam.getCustomerBarCode());
		spayOrder.setCustomerRequestId(orderNo);
		spayOrder.setTransAmt(format1(orderResultParam.getAmount())); // 订单金额
		spayOrder.setResCode(RESPONSE_CODE_SUCCESS);
		// 退货信息
		if (orderResultParam.getRefundAmount() != null) {
			String refundAmount = formatAmountToString(orderResultParam
					.getRefundAmount());
			spayOrder.setRefundAmt(refundAmount);
		}
		log.info("Cod Response params :" + spayOrder);
		return spayOrder;
	}

	/**
	 * 金额转换为String
	 */
	private String formatAmountToString(double amount) {
		BigDecimal paidAmount = new BigDecimal(Amount.mul(amount, 100));
		String paidAmountStr = paidAmount.toPlainString();
		int index = paidAmountStr.indexOf(".");
		if (index > 0) {
			paidAmountStr = paidAmountStr.substring(0, index);
		}
		return paidAmountStr;
	}

	/**
	 * 使用BigDecimal，保留小数点后两位
	 */
	private static String format1(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.toString();
	}


	/**
	 * 支付通知
	 */
	@Override
	public void orderPayNotify(Payment payment,Customer customer,Order order) {
		// 根据商编，商户订单号，交易类型，查询是否已经存在通知记录。不存在就保存一条记录。
		CodNotifyInfoParam codNotifyInfoParam = new CodNotifyInfoParam() ;
		codNotifyInfoParam.createCriteria().andExternalIdEqualTo(order.getExternalId())
		.andTrxTypeEqualTo(TrxType.PURCHASE.name());
		List<CodNotifyInfo> codAsynInfos = codNotifyInfoService.selectByFilter(codNotifyInfoParam) ;
		CodNotifyInfo codAsynInfo = null ;
		if (codAsynInfos != null && codAsynInfos.size() > 0) {
			codAsynInfo = codAsynInfos.get(0) ;
		}
		OrderPayParam orderPayParam = null;
		if (null == codAsynInfo) {
			orderPayParam = createPurchasePayParam(payment, customer,order);
			codAsynInfo = saveCodAsynNotifyInfo(orderPayParam,order);
			log.info("###externalId = " + order.getExternalId() + ",商户单号:" + order.getCustomerOrderId()
					+ " 成功创建通知表##########");
		} else {
			if (Status.SUCCESS.equals(codAsynInfo.getStatus())) {
				log.info("###externalId = " + order.getExternalId() + ",商户单号:" + order.getCustomerOrderId()
						+ " 回执已成功发送##########");
			}
			if (StringUtils.isBlank(codAsynInfo.getExtendInfo())) {
				orderPayParam = createPurchasePayParam(payment, customer,order);
			} else {
				orderPayParam = JsonMarshaller.getMarshaller().jsonUnMarshaller(codAsynInfo.getExtendInfo(),
						OrderPayParam.class);
			}
			orderPayParam.setPospTime(System.currentTimeMillis());
		}
		log.info("-----通知COD报文:" + orderPayParam);
		boolean response = codPOSPIndRemoteService.orderPay(orderPayParam);
		long timecost = System.currentTimeMillis() - orderPayParam.getPospTime();
		log.info("########## YMF call cod cost " + timecost + "ms#########,系统流水号:" + order.getExternalId()
				+ ",商户订单号:" + order.getCustomerOrderId());

		if (response) {
			log.info("NOTIFY COD SUCCESS,系统流水号:" + order.getExternalId()
					+ ",商户订单号:" + order.getCustomerOrderId());
			codAsynInfo.setStatus(Status.SUCCESS);
		}
		codNotifyInfoService.updateStateById(codAsynInfo);

		if (!response) {
			log.info("NOTIFY COD FAIL,系统流水号:" + order.getExternalId()
					+ ",商户订单号:" + order.getCustomerOrderId());
		}

	}

	/**
	 * 创建COD支付通知接口参数
	 */
	private OrderPayParam createPurchasePayParam(Payment payment,Customer customer,Order order){
		OrderPayParam orderPayParam = new OrderPayParam();

		orderPayParam.setPosId(spayPosSn);
		orderPayParam.setPospTime(System.currentTimeMillis());
		if (StringUtils.isBlank(order.getSanCode())) {
			//非台签版本,有可能QrID为空
			orderPayParam.setPosCati(spayPosCati);
		} else {
			// 英大定制 传8个1
			orderPayParam.setPosCati(swithQrId(order.getSanCode()));
		}
		if (StringUtils.isNotBlank(payment.getOpenId())) {
			orderPayParam.setEmployeeId(payment.getOpenId());//使用openId作为操作员
		} else {
			orderPayParam.setEmployeeId("123456");//使用openId作为操作员
		}
		orderPayParam.setEmployeeName("易码付");
		orderPayParam.setCustomerCode(customer.getCustomerNumber());

		//COD数据库查询
		orderPayParam.setCustomerName(customer.getCustomerName());
		orderPayParam.setOrderNo(payment.getCustomerOrderId());//商户订单号
		if (StringUtils.isNotBlank(payment.getCardNo())) {
			orderPayParam.setCardNo(AES.decryptFromBase64(payment.getCardNo(), Constants.CARD_NO_AES_KEY));
		}
		orderPayParam.setAmount(payment.getRealAmt().doubleValue());
		orderPayParam.setBankOrderId("");
		orderPayParam.setPospExternalId(order.getExternalId());//微信支付填微信订单号，epos填易宝订单号
		orderPayParam.setCorePlatExternalId(payment.getYeepayOrderId());//填易宝订单号
		orderPayParam.setCustomerBarCode("");//发件商条码
		orderPayParam.setCodExternalId("");//物流流水号
		orderPayParam.setPayTime(payment.getChannelPayTime());
		orderPayParam.setPosManufacturer("YMF");//pos厂商
		orderPayParam.setReceiveOrderCustomerName(customer.getCustomerName());//公司名称
		orderPayParam.setReceiveOrderCustomerNo(customer.getCustomerNumber());//
		orderPayParam.setReceiveMoneyCustomerName("");//0051收款方名称
		orderPayParam.setReceiveMoneyCustomerNo("");//0052收款方编号
//		默认为23，对应feeProductCodeMapping的值，表示用户最终的支付方式，
//		支付方式的值有19：api微信H5支付，20：wap微信H5支付，21：微信公众号支付，
//		22：微信主扫支付，23：银行卡WAP支付
		if (PaySource.WECHAT.equals(payment.getPaySource())) {
			// 微信支付
			orderPayParam.setCardType(3);// 1银行卡2储值卡 3微信 4 支付宝
			//根据微信还是epos判断  CARDPAY  刷卡支付   WXPAY 微信支付    ANDPAY 和付支付
			orderPayParam.setOrderPayMentChannel("WXPAY");
		} else if (PaySource.NCPAY.equals(payment.getPaySource())){
			orderPayParam.setCardType(1);
			orderPayParam.setOrderPayMentChannel("CARDPAY");
		} else if (PaySource.ALIPAY.equals(payment.getPaySource())) {
			orderPayParam.setCardType(4);
			orderPayParam.setOrderPayMentChannel("ALIPAY");
		}
		orderPayParam.setPosBatch("");    		// POS批次号
		orderPayParam.setPosRequestId("");	// 流水号

		orderPayParam.setPayChannel(1);//0058支付通道 1整单 2拆单 默认整单支付

		if (BusinessType.ORDER_PAY.equals(order.getBusinessType())) {
			orderPayParam.setPosMenue(POS_MENU_ORDER);
		} else if(BusinessType.MANUAL_ORDER.equals(order.getBusinessType())) {
			orderPayParam.setPosMenue(POS_MENU_MANUAL_ORDER);
		} else {
			orderPayParam.setPosMenue(POS_MENU_NO_ORDER);
		}
		orderPayParam.setFrpCode(payment.getBankType());//银行代码

		orderPayParam.setUserId(payment.getCustomerOrderId());


		long sendTimeStamp = System.currentTimeMillis();
		orderPayParam.setPospTime(sendTimeStamp);

		return orderPayParam;
	}

	public static final int NOTIFY_MANUAL_EXP_DELAY = 30;                   				//回执通知失效时间(分钟)
	/**
	 * 保存CodAsynNotifyInfo
	 */
	private CodNotifyInfo saveCodAsynNotifyInfo(OrderPayParam orderPayParam ,Order order){
		CodNotifyInfo info = new CodNotifyInfo();
		info.setExternalId(order.getExternalId());
		info.setOrderNo(order.getCustomerOrderId());
		String codInterface = "";
		codInterface = "codPOSPIndRemoteService";
		info.setNotifyType(NotifyType.MANUAL);
		//失效时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, NOTIFY_MANUAL_EXP_DELAY);
		info.setExpDate(calendar.getTime());
		info.setCreateDate(new Date());
		info.setCodInterface(codInterface);
		info.setStatus(Status.FAIL);
		String extendInfo = JsonMarshaller.getMarshaller().jsonMarshaller(orderPayParam);
		log.info("###extendInfo Length = "+extendInfo.length());
		info.setExtendInfo(extendInfo);
		info.setTrxType(TrxType.PURCHASE.toString());
		codNotifyInfoService.save(info);

		return info;
	}

	@Override
	public void testInterface() {
		if (null == codPOSPIndRemoteService) {
			log.info("codPOSPIndRemoteService is null");
		} else {
			boolean a =codPOSPIndRemoteService.orderPay(new OrderPayParam());
			log.info("invoke:" + a);
		}

	}
}