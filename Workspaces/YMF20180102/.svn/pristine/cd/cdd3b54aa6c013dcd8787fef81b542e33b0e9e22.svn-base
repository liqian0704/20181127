package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.customer.CustomerBizService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceBizService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.core.ymf.utils.common.AmountUtil;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.email.MonitorNotify;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferParamDTO;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferResultDTO;
import com.yeepay.g3.facade.balance.page.PageResult;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.SettleType;
import com.yeepay.g3.facade.ymf.facade.laike.RemitInfoQueryFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.utils.lock.impl.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dongxulu on 17/4/28.
 */
@Service
public class RemitInfoQueryFacadeImpl implements RemitInfoQueryFacade {
    private static Logger logger = LoggerFactory.getLogger(RemitInfoQueryFacadeImpl.class);
    @Autowired
    private InvokeRemoteRemitService invokeRemoteRemitService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerBizService customerBizService;
    @Autowired
    private CustomerSettleDetailService customerSettleDetailService;
    @Autowired
    private RemittanceService remittanceService;
    @Autowired
    private RemittanceBizService remittanceBizService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    /**
     * 日结通系统查询打款结果定时
     * 1.这个定时每分钟跑一次，存在并发问题。2.对于打款明确失败的未做处理，会跑一整天此数据，浪费资源。（）
     */
    @Override
    public void queryRemitInfo(int pageNo,int count) {
        logger.info("queryRemitInfo begin");

        try {
            Date today = new Date();
            Date firstOfDay = DateUtil.getFirstOfDay(today);
            Date twoMinutesAgo = DateUtil.addMinuteToDate(today, -2);
            int trxCount = remittanceService.findByRemitStatusCount(RemitStatus.PROCESS,
                    firstOfDay,twoMinutesAgo);
            if (trxCount == 0) {
                logger.info("没有数据");
                return;
            }
            int pageSize = CfgConstant.getRemitQueryPageSize();
            int pageCount= trxCount/pageSize;//根据交易总数与每页数量相除,获取页数
            for(int i=0; i < pageCount+1; i++){
                //根据每页获取的打款信息,进行查询打款记录的操作
                List<Remittance> remitList = remittanceService.findByRemitStatus(RemitStatus.PROCESS,
                        firstOfDay,twoMinutesAgo
                        ,i*pageSize,(i+1)*pageSize);
                for(Remittance remittances:remitList){
                    //打款记录如已经成功则不进行查询
                    if(RemitStatus.SUCCESS.equals(remittances.getRemitStatus())) continue;
                    Long customerID = customerBizService.getG2CustomerID(remittances.getCustomerNumber());
                    BatchQueryTransferParamDTO queryParam = new BatchQueryTransferParamDTO();
                    queryParam.setCustomerId(customerID);  //商户ID
                    queryParam.setProduct(CfgConstant.getRemitProductCode());  //产品类型  RJT 日结通  WTJS 委托结算
                    //查询当前时间前2分钟的记录
                    queryParam.setCreateDateStart(firstOfDay);
                    queryParam.setCreateDateEnd(twoMinutesAgo);
                    //queryParam.setBankTrxStatus("SUCCESS");
                    queryParam.setMctCreator(remittances.getYeepayOrderId()); // 订单号
                    if (StringUtils.isNotBlank(remittances.getBatchNo())) {
                        queryParam.setBatchCode(remittances.getBatchNo()); // 批次号
                    }
                    queryParam.setPageNo(pageNo); // 页数
                    queryParam.setPageSize(count); // 每页数目

                    PageResult<BatchQueryTransferResultDTO> result = null;
                    try {
                        result = invokeRemoteRemitService.getRemitInfo(queryParam);
                        if(null == result.getList()){
                            logger.info("### no remittance be found!");
                            return;
                        }
                    } catch (Throwable throwable) {
                        String msg = "调用日结通打款状态查询异常" + ",参数:" + JSONUtils.toJsonString(queryParam);
                        logger.info(msg,throwable);
                        MonitorNotify.notifyEmail(msg,throwable);
                        return;
                    }
                    // 查询明细存储在result.getList()中
                    for(BatchQueryTransferResultDTO resultDTO:result.getList()){
                        //此处前期使用时 全部打出 为了排查问题 后期需去掉,日志量太大
                        logger.info("resultDTO :"+ JSONUtils.toJsonString(resultDTO));
                        //TODO 此处最好用批量插入,频繁操作会导致DB连接数紧张  后期更改
                        String yeepayOrderID = resultDTO.getMctCreator();
                        Remittance remittance = remittanceService.findByYeepayOrderId(yeepayOrderID);
                        if(RemitStatus.SUCCESS.equals(remittances.getRemitStatus()) ||
                                RemitStatus.FAIL.equals(remittances.getRemitStatus())){
                            continue;// 如果打款成功直接跳过
                        }
                        RemitStatus remitStatus = null;
                        String remark = null;
                        if ("SUCCESS".equals(resultDTO.getBankTrxStatus())) {
                            remitStatus = RemitStatus.SUCCESS;
                        } else if ("FAIL".equals(resultDTO.getBankTrxStatus())) {
                            remitStatus = RemitStatus.FAIL;
                            remark = resultDTO.getExtInfo();
                        } else {
                            //除成功和失败，其它不做处理
                            continue;
                        }
                        remittance.setRemitStatus(remitStatus);
                        remittance.setCallbackTime(new Date());
                        remittance.setDescription(remark);//备注信息
                        CustomerSettleDetail settleDetail = customerSettleDetailService.findByYeepayOrderId(yeepayOrderID);
                        if (settleDetail == null) {
                            // 兼容老数据 老数据不存在对应的商户结算记录，需要保存一份
                            settleDetail = new CustomerSettleDetail();
                            this.initSettleDetail(resultDTO, settleDetail, remittance);
                        } else {

                            this.prepareSettleDetail(resultDTO, settleDetail, remittance);
                        }
                        settleDetail.setRemark(remark);
                        settleDetail.setSettleStatus(remitStatus);
                        remittanceBizService.updateRemittanceAndSaveCustomerSettleDatil(remittance, settleDetail);
                    }
                }
            }
        } catch (Throwable throwable) {
            logger.info("[日结通系统查询打款结果定时] 执行异常：",throwable);
            MonitorNotify.notifyEmail("[日结通系统查询打款结果定时] 执行异常：",throwable);
        }
    }

    /**
     * redis锁  键
     */
    private static final String doRemitRockKey = "com.yeepay.g3.core.ymf.RemitInfoQueryFacadeImpl.doRemit";


    @Override
    public void doRemit(int recordCount) {
        try {
            long time1 = System.currentTimeMillis();
            logger.info("------开始执行打款定时");
            if (recordCount <= 0) {
                throw new Exception("参数设置有误");
            }
            Date now = new Date();
            int intervalSecond = CfgConstant.getRemitIntervalSecond();
            Date firstOfDay = DateUtil.getFirstOfDay(now);
            Date lastOfDay = DateUtil.addSecondToDate(now, -intervalSecond);//只查当前时间前10秒的记录，
            Date remitRequestTime = DateUtil.addMinuteToDate(now, -CfgConstant.getRemitRetryInterval());//重试间隔时间必须大于
            int remitRetryCount = CfgConstant.getRemitRetryCount();//重试次数
            List<Remittance> remitList = null;

            RedisLock lock = new RedisLock(doRemitRockKey,30);//锁的默认有效时间是30s
            try {
                long time2 = System.currentTimeMillis();
                //加锁
                if (lock.tryLock(2)) {
                    // 查询数据,
                    remitList = remittanceService.findRemiteDatas(Status.INIT, firstOfDay, lastOfDay,
                            remitRequestTime, remitRetryCount, recordCount);
                    if (remitList == null || remitList.size() == 0) {
                        logger.info("------打款定时执行完毕，无可执行数据");
                        return;
                    }
                    logger.info("本次查询数据条数：" + remitList.size());
                    if (recordCount == remitList.size()) {
                        //可以邮件一下
                        logger.warn("recordCount 达到封顶值:" + recordCount);
                        MonitorNotify.notifyEmail("执行打款定时,执行数据达到封顶值：" + recordCount,
                                null);
                    }
                    //根据Id批更新
                    Set<Long> idList = new HashSet<Long>();
                    for (Remittance remittance:remitList) {
                        idList.add(remittance.getId());
                    }
                    remittanceService.updateRemitTime(idList);
                } else {
                    //获取锁失败
                    logger.info("------打款定时执行,获取分布式锁失败.");
                    throw new Exception("打款定时执行,获取分布式锁失败");
                }
                long time3 = System.currentTimeMillis();
                logger.info("数据查询完毕，耗时:" + (time3 - time2) + "ms");
            } finally {
                //释放锁
                lock.unlock();
            }

            //开始批处理打款
            for (Remittance remittance : remitList) {
                try {
                    Payment payment = paymentService.findByYeepayOrderId(remittance.getCustomerNumber(),
                            remittance.getYeepayOrderId());
                    Order order = orderService.findById(payment.getOrderId());
                    invokeRemoteRemitService.requestRemittance(order,payment,remittance);
                } catch (Throwable throwable) {
                    String msg = "打款发起失败：" + remittance.getYeepayOrderId() + ",商户订单号："
                            + remittance.getCustomerOrderId();
                    logger.error(msg , throwable);
                    MonitorNotify.notifyEmail(msg,throwable);
                }
            }
            long time4 = System.currentTimeMillis();
            logger.info("------打款定时执行完毕，耗时：" + (time4 - time1) + "ms");

        } catch (Exception e) {
            logger.error("打款定时本批次执行失败:",e);
            MonitorNotify.notifyEmail("打款定时本批次执行失败",e);
        }

    }

    /**
     * 准备更新商户打款记录
     */
    private void prepareSettleDetail(BatchQueryTransferResultDTO resultDTO,CustomerSettleDetail
            customerSettleDetail,Remittance remittances){
        BigDecimal settleAmount = null;
        if(resultDTO.getFeeType().equals(Constants.SOURCE_FEETYPE)){
            settleAmount = AmountUtil.formatDoubleAmout(resultDTO.getSrcAmount());
            remittances.setSrcFee(AmountUtil.formatDoubleAmout(resultDTO.getSrcFee()));
        }else if(resultDTO.getFeeType().equals(Constants.TARGET_FEETYPE)){
            remittances.setTargetFee(AmountUtil.formatDoubleAmout(resultDTO.getTargetFee()));
            settleAmount = AmountUtil.formatDoubleAmout(resultDTO.getTargetAmount()).add(AmountUtil.formatDoubleAmout(resultDTO.getFee())).abs();
        }
        customerSettleDetail.setSettleRealAmount(settleAmount);

        customerSettleDetail.setBatchId(resultDTO.getBatchCode());

        customerSettleDetail.setSettleFeeAmount(AmountUtil.formatDoubleAmout(resultDTO.getFee()));
        customerSettleDetail.setSettleTime(resultDTO.getFinishDate());//获取打款完成时间
        //customerSettleDetail.setSettleStatus(RemitStatus.SUCCESS);
    }

    /**
     * 兼容老数据
     */
    private void initSettleDetail(BatchQueryTransferResultDTO resultDTO,CustomerSettleDetail
            customerSettleDetail,Remittance remittances){
        BigDecimal settleAmount = null;
        if(resultDTO.getFeeType().equals(Constants.SOURCE_FEETYPE)){
            settleAmount = AmountUtil.formatDoubleAmout(resultDTO.getSrcAmount());
            remittances.setSrcFee(AmountUtil.formatDoubleAmout(resultDTO.getSrcFee()));
        }else if(resultDTO.getFeeType().equals(Constants.TARGET_FEETYPE)){
            remittances.setTargetFee(AmountUtil.formatDoubleAmout(resultDTO.getTargetFee()));
            settleAmount = AmountUtil.formatDoubleAmout(resultDTO.getTargetAmount()).add(AmountUtil.formatDoubleAmout(resultDTO.getFee())).abs();
        }
        customerSettleDetail.setCustomerNumber(remittances.getCustomerNumber());
        customerSettleDetail.setBatchId(resultDTO.getBatchCode());
        //此处 交易手续费计入结算表
        customerSettleDetail.setTradeFeeAmount(remittances.getFee());
        customerSettleDetail.setSettleAmount(remittances.getAmount());
        customerSettleDetail.setCreateTime(new Date());
        customerSettleDetail.setSettleRealAmount(settleAmount);
        customerSettleDetail.setSettleFeeAmount(AmountUtil.formatDoubleAmout(resultDTO.getFee()));
        customerSettleDetail.setSettleTime(resultDTO.getFinishDate());//获取打款完成时间
        customerSettleDetail.setSettleType(SettleType.S0);
        customerSettleDetail.setYeepayOrderId(resultDTO.getMctCreator());//获取商户订单号 下单orderid传的yeepayrderid
        //customerSettleDetail.setSettleStatus(RemitStatus.SUCCESS);
    }
}
