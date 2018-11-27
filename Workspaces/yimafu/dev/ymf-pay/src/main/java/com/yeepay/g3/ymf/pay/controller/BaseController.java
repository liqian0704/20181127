package com.yeepay.g3.ymf.pay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.pay.param.YmfOrderParam;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.*;
import com.yeepay.g3.core.ymf.service.customer.WechatRelService;
import com.yeepay.g3.core.ymf.service.gratuity.GratuityService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.security.SpaySignUtil;
import com.yeepay.g3.core.ymf.utils.web.HttpUtil;
import com.yeepay.g3.facade.nccashier.enumtype.CashierVersionEnum;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierRequestDTO;
import com.yeepay.g3.facade.nctrade.dto.TradeCashierResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.IndustryType;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.facade.ymf.facade.TradeCashierBizService;
import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.pay.param.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.*;

import static com.caucho.hessian.io.HessianInputFactory.log;

public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class) ;
	protected static final String log_Line = "----------" ;
	/**
	 * 公众号id  统一配置键值
	 */
	protected static final String YMF_AppID = "YMF_AppID";
	/**
	 * 公众号秘钥  统一配置键值
	 */
	protected static final String YMF_WECHATSECRET = "YMF_WECHATSECRET";
	/**
	 * 易码付微信授权  统一配置键值
	 * <p>https://open.weixin.qq.com/connect/oauth2/authorize?appid=AppID&redirect_uri=Auth2CallUrl&response_type=code&scope=snsapi_base&state=customerNumber_SIGN#wechat_redirect </p>
	 */
	protected static final String YMF_BaseAuth2Url = "YMF_BaseAuth2Url";
	/**
	 * 易码付交易系统域名  统一配置键值
	 * <p>http://qa.yeepay.com/ymf-pay
	 */
	protected static final String YMF_PAY_HOST = "YMF_PAY_HOST";
	/**
	 * 认证  统一配置键值
	 * <p>https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppID&secret=SECRET&grant_type=authorization_code&code=CODE 
	 */
	protected static final String YMF_OpenIdUrl  = "YMF_OpenIdUrl";
	/**
	 * 三代计费项目
	 */
	protected static final String YMF  = "YMF";
	/**
	 * 清算中心业务方
	 */
	protected static final String YMFTRADE  = "YMFTRADE";
	
	/**
	 * 行业版订单默认有效期限。
	 */
	protected static final int DEFUALT_ORDER_PAY_EXPECT_TIME = 24 * 10 ;//单位小时 行业版10天
	/**
	 * 标准版订单默认有效期限。
	 */
	protected static final int DEFUALT_STANDARD_EXPECT_TIME = 2 ;//单位小时

	/**
	 * 标志为特殊的页面
	 */
	public static final String PageTag = "COMMON" ;
	
	@Autowired
	protected TradeCashierBizService tradeCashierBizService ;
    @Autowired
    protected OrderService orderService ;
    @Autowired
    protected PaymentService paymentService ;
    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected QrCodeService qrCodeService;
    @Autowired
    protected CodBiz codBiz ;
    @Autowired
    protected CustomerFunctionService customerFunctionService ;
	@Autowired
	protected TradeYMFbizService tradeYMFbizService;
	@Autowired
	protected DictionaryService dictionaryService;
	@Autowired
	protected WechatRelService wechatRelService;
	@Autowired
	private GratuityService gratuityService;

    /**
     * 统一错误页面跳转
     */
    protected String toCommonErrorPage(HttpServletRequest request,String msg) {
    	request.setAttribute("msg", msg);
    	return "common/error" ;
    }

	/**
	 * 根据不同浏览器扫码请求。
	 *
	 * @param request
	 * @param map
	 * @param qrCode
	 * @param data				使用下滑线隔开
	 * @param auth2CallUrlSufix 用户于微信网页授权回调的地址。
	 * @param page              支付页面
	 * @return
	 * @throws Exception
	 */
	protected String handerBrowser(HttpServletRequest request,Map<String, Object> map, String qrCode,
								   String data,String auth2CallUrlSufix,String page) throws Exception {
		QRCode qrCodeEntity = qrCodeService.selectByQrId(qrCode) ;
		if (qrCodeEntity == null) {
			throw new YmfTrxException(TrxCode.T1002) ;
		}
		if (!MaterialStatus.ACTIVE.equals(qrCodeEntity.getStatus())) {
			throw new YmfTrxException(TrxCode.T1002) ;
		}
		String customerNumber = qrCodeEntity.getCustomerNumber() ;
		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.contains("micromessenger")) {// 是微信浏览器
			return toWechatAuth(qrCode, customerNumber,auth2CallUrlSufix);
		} else if(ua.contains("alipay")) {// 支付宝
			logger.info("Request from Alipay Browser");
		} else if(ua.contains("baidu")) {// 百度钱包
			logger.info("Request from Baidu Wallet Browser");
		} else {
			logger.info("Request from Other Browser");
		}
		Customer customer = customerService.findByCustomerNumber(customerNumber) ;
		if (customer == null) {
			throw new YmfTrxException(TrxCode.T1003) ;
		}
		if (!Status.ACTIVE.equals(customer.getStatus())) {
			throw new YmfTrxException(TrxCode.T1003) ;
		}
		//根据应用类型查询对应的二维码businessType,目前维护在数据字典,后期产品需求定了,在进行更改
		String appType = customer.getAppType();
		List<String> businessArray = new ArrayList<String>();
		if(org.apache.commons.lang3.StringUtils.isEmpty(appType)){
			throw new YmfTrxException(TrxCode.T1015) ;
		}else if(appType.split(",").length==1){
			Dictionary dictionary =	dictionaryService.getByDictionaryCode(appType).get(0);
			if(dictionary==null){
				logger.error("getByDictionaryCode is null param:"+appType +" need create Dictionary Type ");
				throw new YmfTrxException(TrxCode.T1015) ;
			}
			for(String business:dictionary.getValue().split(",")){
				businessArray.add(business);
			}
		}else {
			String[] appTypes = appType.split(",");
			for(String code:appTypes){
				Dictionary dictionary =	dictionaryService.getByDictionaryCode(appType).get(0);
				if(dictionary==null){
					logger.error("getByDictionaryCode is null param:"+appType +" need create Dictionary Type ");
					throw new YmfTrxException(TrxCode.T1015) ;
				}
				for(String business:dictionary.getValue().split(",")){
					businessArray.add(business);
				}
			}
		}
		if(!(businessArray.size()>0&&Arrays.asList(businessArray).contains(qrCodeEntity.getBusinessType()))){
			logger.error(TrxCode.T1018.getMsg());
			throw new YmfTrxException(TrxCode.T1018);
		}else if(businessArray.size()<1){
			logger.error("no relationship between customer and Gratuity");
			throw new YmfTrxException(TrxCode.T1015) ;
		}

		putCustParams(request,map,customer);
		map.put("qrCode", qrCode) ;
		if(PageTag.equals(page)) {
			//通过二维码的业务类型路径映射到对应的
			return dealCommonQrPage(qrCodeEntity);
		}
		return page ;
	}

	/**
	 * 判断各个版本交易页面
	 * @param qrCodeEntity
	 * @return
	 * @throws YmfTrxException
     */
	private String dealCommonQrPage(QRCode qrCodeEntity) throws YmfTrxException {
		if (BusinessType.ORDER_PAY.equals(qrCodeEntity.getBusinessType())) {
            return OrderPayController.indexPage;
        } else if (BusinessType.STANDARD.equals(qrCodeEntity.getBusinessType())) {
            return StandardPayController.indexPage;
        } else if (BusinessType.MANUAL_ORDER.equals(qrCodeEntity.getBusinessType())) {
            return ManualOrderPayController.indexPage;
        } else if (BusinessType.GRATUITY_PAY.equals(qrCodeEntity.getBusinessType())) {
            return GratuityPayController.indexPage;
        } else {
            throw new YmfTrxException(TrxCode.T1017);
        }
	}

	/**
	 * 处理微信回调
	 */
	protected String toWechatAuth(String qrCode, String customerNumber,String auth2CallUrlSufix)
			throws UnsupportedEncodingException, YmfTrxException {
		logger.info("Request from Wechat Browser");
		String redrectUrl = wechatAuth2Url(qrCode, customerNumber,auth2CallUrlSufix);
		logger.debug("redrectUrl " + redrectUrl);
		return "redirect:" + redrectUrl ;
	}


	/**
	 * 处理微信授权url地址及参数。
	 *
	 * @param sign		修改为传qrCode
	 * @param customerNumber
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String wechatAuth2Url(String sign, String customerNumber, String auth2CallUrlSufix)
			throws UnsupportedEncodingException, YmfTrxException {
		// 确定是否使用默认公众号。
		String appId;
		WechatRel wechatRel = wechatRelService.findActiveByCustomer(customerNumber);
		if (wechatRel != null) {
			if (StringUtils.isBlank(wechatRel.getAppId()) || StringUtils.isBlank(wechatRel.getAppSecret())) {
				throw new YmfTrxException(TrxCode.T1004);
			}
			appId = wechatRel.getAppId();
			logger.info(customerNumber + ",使用商户定制appId,appId:" + appId);
		} else {
			appId = ConfigureSetting.getValue(YMF_AppID, "");
		}

		//微信授权地址
		String redrectUrl = ConfigureSetting.getValue(YMF_BaseAuth2Url, "");
		String auth2CallUrl = ConfigureSetting.getValue(YMF_PAY_HOST, "") + auth2CallUrlSufix;
		auth2CallUrl = URLEncoder.encode(auth2CallUrl, "UTF-8");
		//固定替换
		redrectUrl = redrectUrl.replace("AppID", appId)
				.replace("Auth2CallUrl", auth2CallUrl)
				.replace("customerNumber", customerNumber);
		if (StringUtils.isNotBlank(sign)) {
			redrectUrl = redrectUrl.replace("SIGN", sign);
		} else {
			redrectUrl = redrectUrl.replace("_SIGN", "");
		}
		logger.info("微信授权请求参数:" + redrectUrl);
		return redrectUrl;
	}

	/**
	 * 处理微信回调
	 * @param request
	 * @param map
	 * @param page		处理完,跳转的支付页面。
	 * @return
	 * @throws IOException
     */
	protected String handleAuth2Callback(HttpServletRequest request, Map<String, Object> map,
										 String auth2CallUrlSufix,String page) throws IOException, YmfTrxException {
		logger.info("微信回调参数:" + request.getQueryString());
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String customerNumber = state ;
		String qrCode = "" ;//qrCode
		if (customerNumber.contains("_")) {
			String arr[] = customerNumber.split("_");
			customerNumber = arr[0] ;
			qrCode = arr[1] ;
		}
		Customer customer = customerService.findByCustomerNumber(customerNumber) ;
		if (!Status.ACTIVE.equals(customer.getStatus())) {
			throw new YmfTrxException(TrxCode.T1003) ;
		}
		putCustParams(request,map, customer);
		map.put("qrCode", qrCode) ;
		String openId = wechatOpenId(customerNumber,code);
		if (StringUtils.isNotBlank(openId)) {
			map.put("openId", openId);
		} else {
			String ua = request.getHeader("user-agent").toLowerCase();
			if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
				return toWechatAuth(qrCode, customerNumber,auth2CallUrlSufix);
			}
		}
		if(PageTag.equals(page)) {
			// 统一授权时,通过二维码业务类型区分
			QRCode qrCodeEntity = qrCodeService.selectByQrId(qrCode) ;
			if (qrCodeEntity == null) {
				throw new YmfTrxException(TrxCode.T1002) ;
			}
			return dealCommonQrPage(qrCodeEntity);
		}
		return page ;
	}

	/**
	 * 调用微信接口，获取openId
	 *
	 * @param code
	 * @return
	 */
	protected String wechatOpenId(String customerNumber,String code) throws IOException, YmfTrxException {
		// 确定是否使用默认公众号。
		String appId;
		String appSecret ;
        WechatRel wechatRel = wechatRelService.findActiveByCustomer(customerNumber);
        if (wechatRel != null ) {
            if (StringUtils.isBlank(wechatRel.getAppId()) || StringUtils.isBlank(wechatRel.getAppSecret())) {
                throw new YmfTrxException(TrxCode.T1004);
            }
            appId = wechatRel.getAppId();
            appSecret = SpaySignUtil.decryptAppSecret(wechatRel.getAppSecret()) ;
            logger.info(customerNumber + ",使用商户定制appId,appId:" + appId);
        } else {
            appId = ConfigureSetting.getValue(YMF_AppID, "");
            appSecret = ConfigureSetting.getValue(YMF_WECHATSECRET, "") ;
        }

		String openIdUrl = ConfigureSetting.getValue(YMF_OpenIdUrl, "");
		openIdUrl = openIdUrl.replace("AppID", appId)
				.replace("SECRET", appSecret)
				.replace("CODE", code);
		String jsonString = HttpUtil.httpGet(openIdUrl, "");
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		String openId = jsonObject.getString("openid");
		logger.info("####openId=" + openId);
		return openId;
	}

	/**
	 * 校验订单参数
	 */
    protected void checkPayParam(YmfOrderParam sPayQueryOrderParam) throws YmfTrxException {
		if (sPayQueryOrderParam == null) {
			throw new YmfTrxException(TrxCode.T1006,"订单信息为空") ;
		}
		if (StringUtils.isEmpty(sPayQueryOrderParam.getTransAmt())) {
			throw new YmfTrxException(TrxCode.T1006,"未获取交易金额") ;
		}
		String transAmt = sPayQueryOrderParam.getTransAmt();
		double doubleAmt = Double.parseDouble(transAmt) ;
		if(BusinessType.ORDER_PAY.equals(sPayQueryOrderParam.getBusinessType())
				||BusinessType.MANUAL_ORDER.equals(sPayQueryOrderParam.getBusinessType())){
			if (StringUtils.isEmpty(sPayQueryOrderParam.getCustomerRequestId())) {
				throw new YmfTrxException(TrxCode.T1006,"订单号为空") ;
			}
			if (sPayQueryOrderParam.getCustomerRequestId().length() > 50) {
				throw new YmfTrxException(TrxCode.T1006,"订单号长度超长") ;
			}
		}
		if (StringUtils.isEmpty(sPayQueryOrderParam.getCustomerNumber())) {
			throw new YmfTrxException(TrxCode.T1006,"商户编号为空") ;
		}

		if (doubleAmt <= 0) {
			throw new YmfTrxException(TrxCode.T1006,"交易金额无效") ;
		}
		if(BusinessType.ORDER_PAY.equals(sPayQueryOrderParam.getBusinessType())){
			if ("0".equals(sPayQueryOrderParam.getPayStatus())) {
				throw new YmfTrxException(TrxCode.T1008) ;
			}
			// 签名验证
			String newSign = orderSign(sPayQueryOrderParam) ;
			if (!newSign.equals(sPayQueryOrderParam.getOrderSign())) {
				logger.error("newOrderSign :" + newSign + ",orderSign:" + sPayQueryOrderParam.getOrderSign());
				throw new YmfTrxException(TrxCode.T1001) ;
			}
		}
	}
    /**
	 * 校验二维码入口签名
	 */
    protected boolean checkCodeSign(String qrCode,String sign) throws Exception {
		//签名校验
		if (StringUtils.isBlank(qrCode) || StringUtils.isBlank(sign)) {
			logger.error("验证签名失败,参数为空");
			throw new YmfTrxException(TrxCode.T1001) ;
		}
		Map<String,String> data = new HashMap<String,String>() ;
		data.put("qrCode",qrCode) ;
		String newSign = SpaySignUtil.sign(data) ;
		if (!sign.equals(newSign)) {
			//如果签名验证失败
			logger.error("验证签名失败");
			throw new YmfTrxException(TrxCode.T1001) ;
		}
		return true ;
	}
    
	/**
	 * 对支付订单的一些关键信息做签名，暂时使用默认key    
	 * 
	 */
    protected String orderSign(YmfOrderParam sPayQueryOrder) {
		Map<String,String> data = new HashMap<String,String>() ;
		data.put("customerNumber",sPayQueryOrder.getCustomerNumber()) ;
		data.put("customerName",sPayQueryOrder.getCustomerName()) ;
		data.put("receiverName",sPayQueryOrder.getReceiverName()) ;
		data.put("transAmt",sPayQueryOrder.getTransAmt()) ;
		data.put("customerRequestId",sPayQueryOrder.getCustomerRequestId()) ;
//		String hmacKey = queryHmacFromG2(sPayQueryOrder.getCustomerNumber()) ;
		String newSign = SpaySignUtil.sign(data);
		return newSign ;
	}
    /**
     * 								
    * @Title: callCashier
    * @Description: 调用收银台
    * @param @param customer
    * @param @param orderParam
    * @param @param order
    * @param @return
    * @param @throws Exception    
    * @return TradeCashierResponseDTO    
    * @throws
     */
	protected TradeCashierResponseDTO callCashier(Customer customer,YmfOrderParam orderParam,Order order,
												  HttpServletRequest request) throws Exception{
		//3.发起订单支付
		TradeCashierRequestDTO tradeCashierRequestDTO = new TradeCashierRequestDTO() ;
		tradeCashierRequestDTO.setMerchantName(customer.getCustomerName());//商户名称，用于收银台显示
		tradeCashierRequestDTO.setMerchantAccount(customer.getCustomerNumber());
//		全部不绑卡
		if(StringUtils.isNotEmpty(orderParam.getOpenId())){
			tradeCashierRequestDTO.setIdentityId(orderParam.getOpenId());//用户唯一标识
			tradeCashierRequestDTO.setIdentityType("WECHAT");//用户标识类型，IMEI,  MAC,  USER_ID,  EMAIL,  PHONE,  ID_CARD,  AGREEMENT_NO,  YIBAO; WECHAT 微信
		} else {
			tradeCashierRequestDTO.setAutoBindCard(false);//	是否绑卡,默认绑卡 不传绑卡信息时需要设置false
		}
		tradeCashierRequestDTO.setAccessBusinMode("REAL");//交易模式，实时交易or担保交易，默认实时，REAL,GUARANTEE
		tradeCashierRequestDTO.setCashierVersion(CashierVersionEnum.WAP.toString());//指定收银台，CashierVersionEnum
//		tradeCashierRequestDTO.setThemeCustomCode("");//收银台版本，即主题自定义编码 可以不传
//		tradeCashierRequestDTO.setGuaranteeTime(1);//	担保时间，以交易时间为起点，单位为天，默认值是1天
//		tradeCashierRequestDTO.setAreaInfo(areaInfo);//	地区码 
//		tradeCashierRequestDTO.setTradeRiskInfo(tradeRiskInfo);//	交易风控参数
//		tradeCashierRequestDTO.setQueryRiskInfo(queryRiskInfo);//	查询限额风控参数
//		tradeCashierRequestDTO.setTerminalInfo("");//	手机信息采集 
//		tradeCashierRequestDTO.setAppInfo(appInfo);//	App信息采集 

		tradeCashierRequestDTO.setUserIp(request.getLocalAddr());// 用户IP，不能空，从请求中获取易码付服务器的ip
//		tradeCashierRequestDTO.setUserUA(UA);//UA
		tradeCashierRequestDTO.setOrderTime(order.getCreateTime());//不能为空
		tradeCashierRequestDTO.setCurrency("CNY");
		Map<String, Object> tradeMap = new HashMap<String, Object>();
//		tradeMap.put("callbackUrl", "");//
//		String frontHost = "http://172.18.162.244:8080/ymf-pay" ;
		//
//		String frontHost = ConfigureSetting.getValue(YMF_PAY_HOST, "") ;
//		if(BusinessType.ORDER_PAY.equals(order.getBusinessType())){
//			tradeMap.put("frontCallbackUrl",  frontHost +
//					"/common/success?customerNumber=" + customer.getCustomerNumber()
//					+ "&customerOrderId=" + order.getCustomerOrderId() + "&memo=保险");//前台回调
//		} else {
//			tradeMap.put("frontCallbackUrl", frontHost + 
//					"/common/success?customerNumber=" + customer.getCustomerNumber()
//					+ "&customerOrderId=" + order.getCustomerOrderId());//前台回调
//		}
		tradeMap.put("frontCallbackUrl","") ;//前台通知页面现在不上
		tradeMap.put("oldMerchantCode", "");
		
		//根据商户编号确定开通的支付权限
		Map<String, String> payTypeMap = new HashMap<String, String>();
        List<Dictionary> funcs = dictionaryService.getCustomerFunctionList(customer.getCustomerNumber());
		if (funcs == null || funcs.size() <= 0) {
			throw new YmfTrxException(TrxCode.T1012) ;
		}
		for (Dictionary func : funcs) {
			if (func != null) {
                payTypeMap.put(func.getValue(), func.getCode());
			}
		}
		if (payTypeMap.size() <= 0) {
			throw new YmfTrxException(TrxCode.T1012) ;
		}
		/**
		 * 20：wap微信H5支付，21：微信公众号支付，22：微信主扫支付(待定)，23：银行卡WAP支付  28:支付宝支付
		 */
		tradeMap.put("feeProductCodeMapping", payTypeMap);
		String tradeAdditionInfo =  JSON.toJSONString(tradeMap, true);
		tradeCashierRequestDTO.setTradeAdditionInfo(tradeAdditionInfo);//扩展字段 json
		
		tradeCashierRequestDTO.setOrderAmount(order.getTrxAmt().multiply(new java.math.BigDecimal("100")).longValue());//金额以分为单位
//		tradeCashierRequestDTO.setSplitAccountInfo(splitAccountInfo);//	分账信息
		String industryCode = IndustryType.getIndustryCode(customer.getIndustryType()) ;
		if (StringUtils.isNotBlank(industryCode)) {
			tradeCashierRequestDTO.setIndustryCatalog(industryCode);//TODO	商品类别码 mcc码对应一键的映射码  必填
		} else {
			throw new YmfTrxException(TrxCode.T1013) ;
		}
		//TODO 后期需要根据运营后台商品名称设置此属性
		if(BusinessType.ORDER_PAY.equals(order.getBusinessType())){
			tradeCashierRequestDTO.setProductName(orderParam.getCustomerName() + "-保险缴费");//商品名称
//			tradeCashierRequestDTO.setOrderExpDate(DEFUALT_ORDER_PAY_EXPECT_TIME * 60);// 交易订单有效期，单位分钟，默认24小时，最小值是5分钟
			tradeCashierRequestDTO.setBizModeCode("ymforder");//	收银台模板终端号，即交易模式编码 必传
		}else{
			tradeCashierRequestDTO.setProductName(orderParam.getCustomerName() + "-缴费");//商品名称
			tradeCashierRequestDTO.setBizModeCode("ymfstandard");//	收银台模板终端号，即交易模式编码 必传
		}
		tradeCashierRequestDTO.setOrderExpDate(orderExpDate(order.getBusinessType(), customer));// 交易订单有效期，单位分钟，默认24小时，最小值是5分钟

		if (BusinessType.ORDER_PAY.equals(order.getBusinessType())) {
			tradeCashierRequestDTO.setCustomerOrderId(order.getCustomerOrderId());//商户订单号，不能空
			tradeCashierRequestDTO.getInnerFee().setClearAdditionInfo("{'bizType':'YMF','historyDesc':'bizType=易码付;requestId=" + order.getCustomerOrderId() + "'}"); // 清算扩展信息
		} else {
			//手工订单版 标准版
			tradeCashierRequestDTO.setCustomerOrderId(order.getExternalId());
			tradeCashierRequestDTO.getInnerFee().setClearAdditionInfo("{'bizType':'YMF','historyDesc':'bizType=易码付;requestId=" + order.getExternalId() + "'}"); // 清算扩展信息
		}
		tradeCashierRequestDTO.setProductDesc("");//	商品描述
		tradeCashierRequestDTO.setCalFeeMode("INNER"); //	计费模式，INNER = 清算中心调用计费子系统计费，OUTER = 外部系统自计费，默认INNER
		tradeCashierRequestDTO.getInnerFee().setFeeBusinType(YMF);
		tradeCashierRequestDTO.setClearBusinType(YMFTRADE);//	清算业务方，如果不传，默认为一键支付 定制去清算中心配置
		tradeCashierRequestDTO.setAccessOrderId(order.getExternalId());//	订单方订单号  非必传,是易码付流水号
//		tradeCashierRequestDTO.setTradeSysNo("NCTRADE");//不能空 交易系统编码  默认有值
		tradeCashierRequestDTO.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class));//	订单方编码需要 nctrx分配
		String hmac = tradeCashierRequestDTO.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class));
		tradeCashierRequestDTO.setHmac(hmac); //验签  秘钥如何确认的  nctrx分配
		long begin = System.currentTimeMillis() ;
		logger.info("----------开始调用收银台下单：" + tradeCashierRequestDTO);
		TradeCashierResponseDTO purchaseRequest = tradeCashierBizService.purchaseRequest(tradeCashierRequestDTO) ;
		long timecost = System.currentTimeMillis() - begin;
		logger.info("----------调用收银台下单结束, 耗时 ：" + timecost + "ms  TradeCashierResponseDTO：" + purchaseRequest);
		return purchaseRequest;
	}
	
	/**
	 * 保存请求订单
	 */
	protected Order createPayOrder(YmfOrderParam ymfOrderParam) throws Exception {
		Order order = new Order() ;
		order.setCustomerNumber(ymfOrderParam.getCustomerNumber());
		order.setCustomerOrderId(ymfOrderParam.getCustomerRequestId());
		order.setCreateTime(new Date());
		order.setOrderStatus(OrderStatus.INIT);
		order.setBusinessType(ymfOrderParam.getBusinessType());
		if(ymfOrderParam.getQrCode() == null || ymfOrderParam.getQrCode().length() > 8) {
			//如果二位码长度错误,不创建订单
			log.info("二维码长度不为8位," + ymfOrderParam);
			throw new YmfTrxException(TrxCode.T1000, " 二维码长度异常");
		}
		order.setSanCode(ymfOrderParam.getQrCode());//保存二位码id
		String transAmt = ymfOrderParam.getTransAmt();
		BigDecimal amt = new BigDecimal(transAmt).setScale(2, RoundingMode.HALF_UP) ;
		order.setTrxAmt(amt);
		order.setReceiverName(ymfOrderParam.getReceiverName());
		order.setReceiverTel(ymfOrderParam.getReceiverTel());
		return order ;
	}
	
	
	/**
	 * 整理需要保存的payment信息
	 */
	protected Payment createPayment(Customer customer, Order order,YmfOrderParam orderParam) {
		Payment payment= new Payment() ;
//		payment.setOrderId(order.getId());
		payment.setCustomerNumber(customer.getCustomerNumber());
		payment.setCustomerOrderId(order.getCustomerOrderId());
		payment.setCreateTime(new Date());
		payment.setPayStatus(PaymentStatus.INIT);
		payment.setSettleStatus(SettleStatus.INIT);
//		payment.setYeepayOrderId(dto.getAccessOrderId());
		payment.setTrxAmt(order.getTrxAmt());
		payment.setTrxType(TrxType.PURCHASE);
		payment.setOpenId(orderParam.getOpenId());
		payment.setExpireTime(expTime(payment.getCreateTime(),orderParam.getBusinessType(), customer));
		return payment;
	}
	
	/**
	 * 订单失效时间。具体的日期时间点
	 */
	protected Date expTime(Date createDate,BusinessType businessType,Customer customer) {
		long time = 0;
		if(BusinessType.ORDER_PAY.equals(businessType)){
			time = orderExpDate(BusinessType.ORDER_PAY, customer) ;
		} else {
			time = orderExpDate(BusinessType.STANDARD, customer) ;
		}
		return DateUtil.addMinuteToDate(createDate, (int) time) ;
	}
	
	/**
	 * 订单失效时间。分钟为单位
	 */
	protected long orderExpDate(BusinessType businessType,Customer customer) {
		Integer validityPeriod = customer.getValidityPeriod();
		if (null != validityPeriod && validityPeriod > 0) {
			return validityPeriod * 60 ;
		}
		if (BusinessType.ORDER_PAY.equals(businessType)) {
			return DEFUALT_ORDER_PAY_EXPECT_TIME * 60 ;
		} else {
			return DEFUALT_STANDARD_EXPECT_TIME * 60 ;
		}
	}
	
	/**
	 * @param map
	 * @param customer
	 */
	protected void putCustParams(HttpServletRequest request,Map<String, Object> map, Customer customer) {
		map.put("customerNumber", customer.getCustomerNumber()) ;
		map.put("customerName", customer.getCustomerName()) ;
		map.put("customerKey", customer.getCustomerNumber()) ;
		map.put("custShotName", customer.getCustomerName()) ;
		if (StringUtils.isBlank(customer.getCustomerLogo())) {
			customer.setCustomerLogo(request.getContextPath() + "/static/images/ymf.png");
		}
		map.put("customerLogo", customer.getCustomerLogo()) ;
	}

	/**
	 * 处理交易异常
	 * @return
     */
	protected TrxCode handleException(Exception e) {
		if (e instanceof  YmfTrxException) {
			logger.error(log_Line + "易码付业务异常:",e);
			return ((YmfTrxException) e).getTrxCode() ;
		} else if (e instanceof YeepayRuntimeException) {
			logger.error(log_Line + "收银台接口调用异常: ",e);
			return TrxCode.T1011 ;
		} else if(e instanceof IOException) {
			logger.error(log_Line + "接口调用异常: ",e);
			return TrxCode.T1005;
		} else {
			logger.error(log_Line + "系统未知异常: ",e);
			return TrxCode.T1000 ;
		}
	}

	/**
	 * 处理返回json数据的交易异常
	 * @param e
	 * @param json
     */
	protected JsonResponse handleException(Exception e,JsonResponse json) {
		TrxCode trxCode = handleException(e);
		if (json == null) {
			json = new JsonResponse();
		}
		json.setMsg(trxCode.toPromptMsg());
		json.setCode(trxCode.getCode());
		return json;
	}


}
