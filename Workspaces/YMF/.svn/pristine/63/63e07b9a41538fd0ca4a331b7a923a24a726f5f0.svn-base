package com.yeepay.g3.core.ymf.biz.liker.impl;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.ymf.biz.liker.LikerBizService;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.service.notify.NotifyRecordeService;
import com.yeepay.g3.core.ymf.utils.common.ThreadPoolUtil;
import com.yeepay.g3.facade.laike.dto.BaseResponse;
import com.yeepay.g3.facade.laike.dto.PayNotifyRequest;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.PayNotifyFacade;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.sp.NotifyType;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.yeepay.g3.utils.rmi.RemoteServiceFactory.getService;

/**
 * Created by dongxulu on 17/3/21.
 */
@Service
public class LikerBizServiceImpl implements LikerBizService {
    private static Logger logger = LoggerFactory.getLogger(LikerBizServiceImpl.class);

    private PayNotifyFacade payNotifyFacade;
    @Autowired
    private NotifyRecordeService notifyRecordeService;


    @Override
    public void doNotify(final Payment payment, final Customer customer, final Order order) {
        ThreadPoolUtil.getInstance().excutorTask(new Runnable(){
            @Override
            public void run() {
                logger.info(" begin to notify ! customerNumber = "+customer.getCustomerNumber()+"  order externalID = "+order.getExternalId());
                PayNotifyRequest request = initLaikeNotifyParam(order,payment);
                BaseResponse response = null;
                try {
                    payNotifyFacade=getService(RemotingProtocol.HESSIAN,PayNotifyFacade.class);
                    response = payNotifyFacade.pushPayMsg2APP(request);
                } catch (Exception e) {
                    logger.error(" payNotifyFacade.pushPayMsg2APP exception try again! order externalID = "+order.getExternalId(),e);
                    int i = 0;
                    while (i<3){
                        i++;
                        response = payNotifyFacade.pushPayMsg2APP(request);
                        if(null!=response && ResponseStatus.SUCCESS.equals(response.getStatus())){
                            logger.info(i +" Try Notify again Success! order ExternalId = "+order.getExternalId());
                            break;
                        }else{
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                logger.error("Thread.sleep Exception",e1);
                                continue;
                            }
                        }
//						TODO 需要更改为配置,并且将异常通知记录,进行补发
                        if(i==3){
                            saveRecord(customer,order,e.getMessage());
                        }
                    }
                }
                if(null==response){
                    logger.error(" externalID: "+order.getExternalId()+ " notify result is null!");
                    saveRecord(customer,order,"notify result is null!");
                }else{
                    logger.info("### notify result:"+response.toString());
                    if(ResponseStatus.SUCCESS.equals(response.getStatus())){
                        logger.info("Notify Success! order ExternalId = "+order.getExternalId());
                    }else{
                        saveRecord(customer,order,response.getErrCode()+"--"+response.getErrMsg());
                        logger.warn("Notify Fail! order ExternalId = "+order.getExternalId() +" Fail message : "+response.getErrMsg());
                    }
                }

            }
        });
    }

    private void saveRecord(Customer customer,Order order,String errMsg){
        NotifyRecord record = new NotifyRecord();
        record.setCustomerNumber(customer.getCustomerNumber());
        record.setExternalId(order.getExternalId());
        record.setStatus(Status.FAIL);
        Date date = new Date();
        record.setCreateTime(date);
        record.setLastNotifyTime(date);
        record.setNotifyRecode((short) 1);
        record.setRemark(errMsg);
        record.setNotifyType(NotifyType.AUTO);
        notifyRecordeService.save(record);
    }

    PayNotifyRequest initLaikeNotifyParam(final Order order,final Payment payment){
        PayNotifyRequest request = new PayNotifyRequest();
        request.setExternalSystem(ExternalSystem.YMF);
        request.setMerchantNo(order.getCustomerNumber());
        request.setOrderAmount(new Amount(order.getTrxAmt()));
        request.setOrderNo(order.getCustomerOrderId());
        request.setPayReceipt(order.getExternalId());
        request.setPayTime(payment.getPayTime());
        PaySource paySource = payment.getPaySource();
        request.setPaySource(paySource);
        request.setTotalAmount(new Amount(order.getTrxAmt()));
        request.setRealPayAmount(new Amount(payment.getRealAmt()));
        return request;
    }




}
