package com.yeepay.g3.core.ymf.service.impl.notify;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.PaymentService;
import com.yeepay.g3.core.ymf.service.notify.NotifyBizService;
import com.yeepay.g3.core.ymf.service.notify.NotifyRecordeService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.PayNotifyFacade;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxType;
import com.yeepay.g3.facade.ymf.exception.YmfException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dongxulu on 17/1/4.
 */
@Service
public class NotifyBizServiceImpl extends SoaBaseBiz implements NotifyBizService {
    private static Logger logger = LoggerFactory.getLogger(NotifyBizServiceImpl.class);
    private PayNotifyFacade payNotifyFacade;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private NotifyRecordeService notifyRecordeService;

    @Override
    public Status asynNotify(NotifyRecord notifyRecord) {
        Payment payment = null;
        String customerNumber = notifyRecord.getCustomerNumber();
        String externalID = notifyRecord.getExternalId();
        BaseResponse response;
        Order order = orderService.findByCustomerAndExternalId(customerNumber,externalID);
        if(null == order){
            logger.error("order externalID : "+order.getExternalId()+" no purchase order!!");
            return Status.FAIL;
        }
        try {
            payment =  paymentService.findByOrderIdAndPayStatusAndTrxType(order.getId(), PaymentStatus.SUCCESS, TrxType.PURCHASE);

            if(null == payment){
                logger.error("order externalID : "+order.getExternalId()+" no purchase payment!!");
                return Status.FAIL;
            }
            PayNotifyRequest payNotifyRequest = new PayNotifyRequest();
            payNotifyRequest.setExternalSystem(ExternalSystem.YMF);
            payNotifyRequest.setMerchantNo(order.getCustomerNumber());
            payNotifyRequest.setOrderAmount(new Amount(order.getTrxAmt()));
            payNotifyRequest.setOrderNo(order.getCustomerOrderId());
            payNotifyRequest.setPayReceipt(order.getExternalId());
            payNotifyRequest.setPayTime(payment.getPayTime());
            payNotifyRequest.setPaySource(payment.getPaySource());
            payNotifyRequest.setTotalAmount(new Amount(order.getTrxAmt()));
            payNotifyRequest.setRealPayAmount(new Amount(payment.getRealAmt()));
            payNotifyFacade = getService(RemotingProtocol.HESSIAN,PayNotifyFacade.class);
            logger.info("推送来客APP消息参数 payNotifyRequest:"+payNotifyRequest.toString());
            response  = payNotifyFacade.pushPayMsg2APP(payNotifyRequest);
        } catch (YmfException e) {
           logger.error("asynNotify failed",e);
           return Status.FAIL;
        }catch (Exception e1){
            logger.error("Exception asynNotify  failed  ",e1);
            return Status.FAIL;
        }
        notifyRecord.setLastNotifyTime(new Date());
        notifyRecord.setNotifyRecode((short)(notifyRecord.getNotifyRecode()+1));
        if(null == response){
            logger.error("推送来客APP响应参数为空 response is null externalID:"+order.getExternalId());
            notifyRecord.setRemark("");
            notifyRecordeService.update(notifyRecord);
            return Status.FAIL;
        }
        if(ResponseStatus.SUCCESS.equals(response.getStatus())){
            logger.info("Notify Success! order ExternalId = "+order.getExternalId());
            notifyRecord.setStatus(Status.SUCCESS);
            notifyRecord.setRemark("");
            notifyRecordeService.update(notifyRecord);
            return Status.SUCCESS;
        }else{
            notifyRecord.setRemark(response.getErrMsg());
            logger.warn("Notify Fail! order ExternalId = "+order.getExternalId() +" Fail message : "+response.getErrMsg());
        }
        notifyRecordeService.update(notifyRecord);
        return Status.FAIL;
    }
}
