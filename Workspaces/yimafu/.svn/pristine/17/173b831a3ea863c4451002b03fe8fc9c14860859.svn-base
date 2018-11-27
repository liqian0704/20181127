package com.yeepay.g3.core.ymf.support;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.core.ymf.utils.common.AmountUtil;
import com.yeepay.g3.facade.nctrade.dto.*;
import com.yeepay.g3.facade.ymf.dto.refund.RefundRequestDTO;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.utils.common.ArrayUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Title: 统一收银台 接口帮助类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 * @see com.yeepay.g3.facade.nctrade.facade
 */
public class NCApiSupport {

    /**
     * 微信类交易的payType值
     */
	private static final int[] payCode = { 19, 20, 21, 22 };

    /**
     * 构建补交易单请求
     * @param order 订单
     * @param payment 支付
     * @return TradeCashierBaseDTO
     */
    public static TradeCashierBaseDTO buildSupplyRequest(Order order, Payment payment) {
        TradeCashierBaseDTO request = new TradeCashierBaseDTO();
        request.setCustomerOrderId(order.getCustomerOrderId()); // 商户订单号
        request.setMerchantAccount(order.getCustomerNumber()); // 商户账户编号
        request.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class)); // 订单方编码
        request.setAccessOrderId(order.getExternalId());
        hmac(request); // 签名
        return request;
    }

    /**
     * 构建补退款单请求
     * @param refundOrder 退款订单
     * @param orderDetail 退款明细
     * @return TradeRefundQueryRequestDTO
     */
    public static TradeRefundQueryRequestDTO buildSupplyRequest(RefundOrder refundOrder, RefundOrderDetail orderDetail) {
        TradeRefundQueryRequestDTO request = new TradeRefundQueryRequestDTO();
        request.setMerchantAccount(refundOrder.getCustomerNumber()); // 商户编号
        request.setBizRefundNo(orderDetail.getRefundRequestId()); // 统一收银台退款编号
        request.setMerchantRefundOrderid(orderDetail.getRefundOrderId()); // 易码付退款流水号
        request.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class)); // 订单方编码
        hmac(request); // 签名
        return request;
    }

    /**
     * 构建退款请求
     * 请求参数：
     * 参数名称           字段名称                    字段类型        是否必填        业务描述
     * 商户账户编号        merchantAccount            String
     * 商户退货订单号      merchantRefundOrderid      String         √
     * 原交易系统流水号    tradeSysOrderId            Long           √
     * 退款金额           amount                     Long           √             最小值为1
     * 交易币种           currency                   String         √             CNY人民币
     * 退款缘由           cause                      String
     * 订单方编码         accessCode                 String         √
     * 签名              hmac                       String          √
     *
     * @param refundPayment 退款请求
     * @param refundOrderDetail 退款明细
     * @return 退款请求DTO
     */
    public static TradeRefundRequestDTO buildRefundRequest(Payment refundPayment, RefundOrderDetail refundOrderDetail) {
        TradeRefundRequestDTO request = new TradeRefundRequestDTO();
        request.setMerchantAccount(refundPayment.getCustomerNumber());
        request.setMerchantRefundOrderid(refundOrderDetail.getRefundOrderId());
        request.setAmount(handleAmount(refundOrderDetail.getRefundAmount()));
        request.setCurrency(ConfigureSetting.getValue(Constants.NC_API_CURRENCY_YMF, "CNY")); // 币种
        request.setCause(refundOrderDetail.getCause()); // 退款原因
        request.setAccessCode(ConfigureSetting.getValue(Constants.YMF_NC_API_ACCESS_CODE, String.class)); // 订单方编码
        request.setTradeSysOrderId(refundPayment.getYeepayOrderId()); // 交易流水号
        hmac(request); // 签名
        return request;
    }

    /**
     * 根据交易payment构建退款请求
     * @param payment
     * @return
     */
    public static RefundRequestDTO buildRefundRequest(Payment payment) {
        RefundRequestDTO request = new RefundRequestDTO();
        request.setCustomerNumber(payment.getCustomerNumber());
        request.setCustomerOrderId(payment.getCustomerOrderId());
        request.setYeepayOrderId(payment.getYeepayOrderId());
        request.setRefundAmount(payment.getTrxAmt());
        request.setRemark("易码付运营后台测试退款");
        return request;
    }

    /**
     * 统一收银台用的手续费和金额都是Long类型
     * 而易码付用的是BigDecimal, 需要转换一哈
     * @param bigDecimal 易码付金额
     * @return 统一收银台金额
     */
    public static Long handleAmount(BigDecimal bigDecimal) {
        if (null == bigDecimal) {
            return null;
        }
        return bigDecimal.multiply(new BigDecimal(100)).longValue();
    }

    /**
     *
     * @param l 统一收银台金额
     * @return 易码付金额
     */
    public static BigDecimal handleAmount(Long l) {
        if (null == l) {
            return null;
        }
        return new BigDecimal(l).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 统一收银台接口签名
     * @param dto
     */
    public static void hmac(BaseDTO dto) {
        String hmac = dto.signMd5(ConfigureSetting.getValue(Constants.YMF_NC_API_HMAC, String.class));
        dto.setHmac(hmac);
    }

    /**
     * 根据统一收银台返回状态转化
     *
     * 待支付  UNPAY(0)
     * 支付成功 PAY_SUCCESS(1)
     * 订单已撤消 CANCEL(2)
     * 阻断交易 STOP(3)
     * 支付失败 PAY_FAIL(4)
     * 处理中 PROCESSING(5)
     * @param orderState
     * @return
     */
    public static PaymentStatus transferStatus(String orderState) {
        if ("0".equals(orderState) || "5".equals(orderState)) {
            return PaymentStatus.PROCESS;
        } else if ("2".equals(orderState) || "3".equals(orderState) || "4".equals(orderState)) {
            return PaymentStatus.FAIL;
        } else if ("1".equals(orderState)) {
            return PaymentStatus.SUCCESS;
        } else {
            return PaymentStatus.PROCESS;
        }
    }

    /**
     * 根据统一收银台返回退款状态转换
     *
     * 退款失败 4
     * 退款成功 5
     * @param refundStatus
     * @return
     */
    public static RefundStatus transferRefundStatus(String refundStatus) {
        if ("4".equals(refundStatus)) {
            return RefundStatus.PROCESS;
        } else if ("5".equals(refundStatus)) {
            return RefundStatus.SUCCESS;
        } else {
            return RefundStatus.PROCESS;
        }
    }

    /**
     * 根据统一收银台返回退款状态转换
     * @param refundStatus
     * @return
     */
    public static PaymentStatus transferRefundPaymentStatus(String refundStatus) {
        if ("4".equals(refundStatus)) {
            return PaymentStatus.PROCESS;
        } else if ("5".equals(refundStatus)) {
            return PaymentStatus.SUCCESS;
        } else {
            return PaymentStatus.PROCESS;
        }
    }

    /**
     * 支付方式的值有(含预留): -1表示未支付或未知
     *        {api微信H5支付(19), wap微信H5支付(20), 微信公众号支付(21), 微信主扫支付(22)
     *          ，无卡收银台首次支付(24)，无卡收银台绑卡支付(25),  移动收银台首次支付(26),
     *           移动收银台绑卡支付(27) ,wap支付宝主扫支付(28),api支付宝主扫支付(29) }
     * @param payType  收银台返回的payType
     * @return  PaySource
     */
    private static PaySource payTypeToPaySource(int payType) {
        if (ArrayUtils.contains(payCode, payType)) {
            return PaySource.WECHAT;
        } else if (23 == payType || 26 == payType || 27 == payType) {
            return PaySource.NCPAY ;
        } else if (28 == payType || 29 == payType) {
            return PaySource.ALIPAY ;
        }
        return null ;
    }
    
	/**
	 * 整理需要保存的payment信息
	 */
	public static Payment preparePayment(Payment payment,TradeCashierReplyDTO dto) {
        payment.setPaySource(payTypeToPaySource(dto.getPayType()));
		//收银台技术： payTime是微信支付完成时间，closeTIme是nctrade交易完成时间，也就是我们改状态的时间
		payment.setPayTime(dto.getCloseTime());//支付完成时间
		payment.setChannelPayTime(dto.getPayTime());//交易完成时间
		BigDecimal realAmt = AmountUtil.formatLongAmout(dto.getOrderAmount()) ;
		//手续费和payment的入账时回调
//		order.setFee("");
		payment.setPayUrl(dto.getCashierCallUrl());
		payment.setPayStatus(PaymentStatus.SUCCESS);
//		payment.setBankOrderId();//未传
		payment.setYeepayOrderId(String.valueOf(dto.getTradeSysOrderId()));//易宝流水号，收银台生成的流水
//		payment.setTrxAmt(bd);
		payment.setRealAmt(realAmt);
//		payment.setTrxType(TrxType.PURCHASE);
		payment.setOpenId(dto.getIdentityId());
		if (StringUtils.isNotBlank(dto.getCardNo())) {
			payment.setCardNo(AES.encryptToBase64(dto.getCardNo(),Constants.CARD_NO_AES_KEY));
		}
		//根据NCPAY  的cardType确定本地的枚举值
        if (StringUtils.isNotBlank(dto.getCardType()) && payment.getPaySource() != PaySource.ALIPAY) {
            //支付宝不返回卡类型,故而不需要填写
            payment.setCardType(CardType.getCardType(dto.getCardType()));
        }
		payment.setBankType(dto.getBankCode());
		payment.setBankName(dto.getBankName());
		
		return payment;
	}

    /**
     * 失败的payment
     * 整理需要保存的payment信息
     */
    public static void prepareFailPayment(Payment payment,TradeCashierReplyDTO dto) {
        payment.setPaySource(payTypeToPaySource(dto.getPayType()));
        //收银台技术： payTime是微信支付完成时间，closeTIme是nctrade交易完成时间，也就是我们改状态的时间
        payment.setPayTime(dto.getCloseTime());//支付完成时间
        payment.setChannelPayTime(dto.getCloseTime());//交易完成时间
        //手续费和payment的入账时回调
//        payment.setPayUrl(dto.getCashierCallUrl());
        payment.setPayStatus(PaymentStatus.FAIL);
        payment.setYeepayOrderId(String.valueOf(dto.getTradeSysOrderId()));//易宝流水号，收银台生成的流水
        payment.setOpenId(dto.getIdentityId());
        payment.setCardNo(dto.getCardNo());
        if (StringUtils.isNotBlank(dto.getCardType())) {
            payment.setCardType(CardType.getCardType(dto.getCardType()));
        }
        payment.setBankType(dto.getBankCode());
        payment.setBankName(dto.getBankName());
    }
	
	/**
	 * 整理订单成功的需要补充数据
	 * @param dto
	 * @param order
	 */
	public static void prepareOrder(TradeCashierReplyDTO dto, Order order) {
		BigDecimal realAmt = AmountUtil.formatLongAmout(dto.getOrderAmount()) ;
		int than = realAmt.compareTo(order.getTrxAmt()) ;
		if (than < 0) {
			//拆单支付时存在
//				order.setOrderStatus(OrderStatus.PROCESS);
			order.setRealAmt(realAmt.add(order.getRealAmt()));//和之前的已支付叠加
		} else if (than == 0) {
			order.setOrderStatus(OrderStatus.SUCCESS);
			order.setRealAmt(realAmt);
		} else {
//			logger.error("##### 实际支付金额大于订单金额");
		}
		order.setCompleteTime(new Date());
	}
}
