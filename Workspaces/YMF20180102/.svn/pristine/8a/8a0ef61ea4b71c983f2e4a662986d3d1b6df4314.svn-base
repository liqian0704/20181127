package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.TradeYMFbizService;
import com.yeepay.g3.core.ymf.biz.cod.CodBiz;
import com.yeepay.g3.core.ymf.biz.liker.LikerBizService;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.ext.OprYopOrderExt;
import com.yeepay.g3.core.ymf.service.BusinessService;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.support.NCApiSupport;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.opr.dto.yop.order.YopQueryOrderResDTO;
import com.yeepay.g3.facade.opr.enumtype.CardTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.common.CardType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.facade.ymf.facade.OrderTimerFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 订单相关定时
 * @Author: xiaobin.liu
 * @Date: 17/6/23
 * @Time: 上午10:16
 */
@Service
public class OrderTimerFacadeImpl implements OrderTimerFacade {

    private static final Logger logger = LoggerFactory.getLogger(OrderTimerFacadeImpl.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CodBiz codBiz;
    @Autowired
    private TradeYMFbizService tradeYMFbizService;
    @Autowired
    private OprYopOrderExt oprYopOrderExt;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private LikerBizService likerBizService;

    /**
     * 超时订单关闭.对从订单处理器下单，已经超时订单发起支付结果查询，根据支付结果进行补单及关闭订单。
     * @param beforeDay         超时时间范围，与今天间隔。如：1  为跑昨日数据，如 0 为跑今日数据
     * @param retryCount        当前数据重跑次数,0:则只跑未跑过数据，1：则相当于第二次跑
     * @param recordCount       单次跑最大数据总数
     */
    @Override
    public void clossOrder(int beforeDay, int retryCount, int recordCount) {

        logger.info("-----开始执行 [clossOrder] 定时");
        List<Payment> expriePayments = null;
        try {
            expriePayments = getExpriePayments(beforeDay, retryCount, recordCount);

            if (expriePayments == null || expriePayments.size() <= 0) {
                logger.info("-----结束 [clossOrder] 定时，无可执行数据");
                return;
            }

            logger.info("[clossOrder] 定时数据条数：{}",expriePayments.size());

            // 执行订单更新,queryCount自动+1
            Set<Long> orderIds = new HashSet<Long>();
            for (Payment payment : expriePayments) {
                orderIds.add(payment.getOrderId());
            }
            orderService.updateQueryCountByIds(orderIds);
            logger.info("updateQueryCountByIds SUCCESS");
        } catch (Exception e) {
            logger.info("关闭订单定时 [clossOrder] 异常:{}", e);
            throw new RuntimeException(e);
        }

        //调用接口查询订单支付状态，根据状态进行关闭订单，或者完成订单补发cod通知
        for (Payment payment : expriePayments) {
            try {
                Order order = orderService.findById(payment.getOrderId());
                //对于init状态订单，直接关闭订单
                if (payment.getPayStatus() == PaymentStatus.INIT) {
                    updateFailOrder(payment, order);
                    continue;
                }

                String orderId = order.getExternalId();
                if (NCApiSupport.useCustomerOrderId(order.getBusinessType())) {
                    orderId = order.getCustomerOrderId();
                }

                //接口
                YopQueryOrderResDTO orderResDTO = oprYopOrderExt.queryOrder(
                        payment.getCustomerNumber(), orderId, payment.getYeepayOrderId());
                if (!OprYopOrderExt.opr_ok.equals(orderResDTO.getCode())) {
                    //不成功的情况，确定下具体错误码，有些错误码可以直接关闭订单 OPR11001：订单不存在
                    continue ;
                }
                String status = orderResDTO.getStatus();
                if ("SUCCESS".equals(status)) {
                    completeOrder(payment, order, orderResDTO);
//                    notifyCodOrLiker(payment, customer, order);//超时订单暂时不做补发通知
                } else if ("CANCEL".equals(status) || "CLOSE".equals(status) || "REJECT".equals(status)
                        || "TIME_OUT".equals(status)){
                    updateFailOrder(payment, order);
                    continue;
                }

            } catch (Exception e) {
                logger.error("关闭订单 [clossOrder] 定时异常。",e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 完成订单状态
     * @param payment
     * @param order
     * @param orderResDTO
     * @throws YmfException
     */
    private void completeOrder(Payment payment, Order order, YopQueryOrderResDTO orderResDTO) throws YmfException {
        order.setCompleteTime(new Date());
        order.setOrderStatus(OrderStatus.SUCCESS);
        BigDecimal realAmount = orderResDTO.getPayAmount();
        order.setRealAmt(realAmount);

        payment.setRealAmt(realAmount);
        //payment.setBankOrderId(orderResDTO);//查单时不返回
        Date payTime = null;
        if (StringUtils.isNotBlank(orderResDTO.getPaySuccessDate())) {
            payTime = DateUtil.getStrToDate(orderResDTO.getPaySuccessDate(), Constants.OPR_DATE_TEMPLATE);
        } else {
            payTime = new Date();
        }
        payment.setPayTime(payTime);
        payment.setChannelPayTime(payTime);
        payment.setPayStatus(PaymentStatus.SUCCESS);
        payment.setPaySource(PaySource.valueOf(orderResDTO.getPlatformType()));
        CardTypeEnum cardType = orderResDTO.getCardType();
        if (cardType != null) {
            try {
                payment.setCardType(CardType.getCardType(cardType.toString()));
            } catch (Exception e) {
                //此处抓一下异常 打印日志 不做处理 可能会因为类型不一样导致异常
                logger.error("setCardType exception:cardType--->:" + cardType);
            }
        }
        payment.setBankType(orderResDTO.getBankId());

        // 行业版需要通知 成功时, 通知cod
        tradeYMFbizService.updateOrderAndPayment(order, payment);
        logger.info("------ Update Order And Payment Success,Order is Success:externalId={}",
                order.getExternalId());
    }

    /**
     * 完成失败订单
     * @param payment
     * @param order
     * @throws YmfException
     */
    private void updateFailOrder(Payment payment, Order order) throws YmfException {
        // update payment and order （completeTime status）
        payment.setPayStatus(PaymentStatus.FAIL);
        order.setOrderStatus(OrderStatus.FAIL);
        order.setCompleteTime(new Date());
        tradeYMFbizService.updateOrderAndPayment(order, payment);
        logger.error("------ Update Order And Payment Success ,Order is Fail:externalId={}",order.getExternalId());
    }

    /**
     * 查询获取符合条件的payment数据
     * @param beforeDay
     * @param retryCount
     * @param recordCount
     * @return
     */
    private List<Payment> getExpriePayments(int beforeDay, int retryCount, int recordCount) {
        //由于来客订单超时时间没存，所以，当天先用创建时间延迟2小时作为临界时间
        Date today = new Date();
        Date dataDate = today;//数据查询时间
        Date beginDate = null;
        Date endDate = null;
        if (beforeDay > 0) {
            //往期订单
            dataDate = DateUtil.getDate(today, -beforeDay);
            beginDate = DateUtil.getFirstOfDay(dataDate);//数据查询时间日期的0时分
            endDate = DateUtil.getLastOfDay(dataDate);
            // 只跑订单处理器数据,往期订单重跑不考虑重跑次数
            return paymentService.findExpriePayments(beginDate, endDate,
                    TradeSystemEnum.OPR, null, recordCount);
        } else {
            //当天订单
            //由于来客订单超时时间没存，所以，当天先用创建时间延迟2小时作为临界时间
            beginDate = DateUtil.getFirstOfDay(dataDate);//数据查询时间日期的0时分
            int internal = 130;
            internal = internal + (retryCount * 30);//考虑同一天两次重跑间隔大于30分钟
            endDate = DateUtil.addMinuteToDate(dataDate, -internal);
            // 只跑订单处理器数据
            return paymentService.findExpriePayments(beginDate, endDate,
                    TradeSystemEnum.OPR, retryCount, recordCount);
        }
    }

    /**
     * 补单
     */
    @Override
    public void completeOrder() {
        //根据条件查询订单


    }
}
