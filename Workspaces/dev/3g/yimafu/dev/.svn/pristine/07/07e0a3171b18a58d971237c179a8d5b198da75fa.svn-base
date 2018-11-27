package com.yeepay.g3.core.ymf.facade.impl.laike;

import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.core.ymf.service.customer.CustomerBizService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceBizService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferParamDTO;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferResultDTO;
import com.yeepay.g3.facade.balance.page.PageResult;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.SettleType;
import com.yeepay.g3.facade.ymf.facade.laike.RemitInfoQueryFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @Override
    public void queryRemitInfo(int pageNo,int count) {
        logger.info("queryRemitInfo begin");
        //TODO  此处需要在以后进行分页操作,目前商户少可以全部查出,赶工期没办法
        int trxCount = remittanceService.findByRemitStatusCount(RemitStatus.PROCESS,
                DateUtil.getFirstOfDay(new Date()),DateUtil.addMinuteToDate(new Date(),-2));
        int pageSize = CfgConstant.getRemitQueryPageSize();
        int pageCount= trxCount/pageSize;//根据交易总数与每页数量相除,获取页数
        for(int i=0;i<pageCount+1;i++){
            List<Remittance> remitList = remittanceService.findByRemitStatus(RemitStatus.PROCESS,
                    DateUtil.getFirstOfDay(new Date()),DateUtil.addMinuteToDate(new Date(),-2)
                    ,i*pageSize,(i+1)*pageSize); //根据每页获取的打款信息,进行查询打款记录的操作
            for(Remittance remittances:remitList){
//              打款记录如已经成功则不进行查询
                if(RemitStatus.SUCCESS.equals(remittances.getRemitStatus())) continue;
                Long customerID = customerBizService.getG2CustomerID(remittances.getCustomerNumber());
                BatchQueryTransferParamDTO queryParam = new BatchQueryTransferParamDTO();
                queryParam.setCustomerId(customerID);  //商户ID
                queryParam.setProduct(CfgConstant.getRemitProductCode());  //产品类型  RJT 日结通  WTJS 委托结算
                //查询当前时间前2分钟的记录
                queryParam.setCreateDateStart(DateUtil.getFirstOfDay(new Date()));
                queryParam.setCreateDateEnd(DateUtil.addMinuteToDate(new Date(),-2));
                queryParam.setBankTrxStatus("SUCCESS");
                // queryParam.setCreateDateStart(sf3.parse("2016-03-10 00:00:00"));
                // queryParam.setCreateDateEnd(sf3.parse("2016-03-17 00:00:00"));
                // queryParam.setMctCreator("20160325NEWTZT100001"); // 订单号
                // queryParam.setBatchCode("201603251026387"); // 批次号
                queryParam.setPageNo(pageNo); // 页数
                queryParam.setPageSize(count); // 每页数目
                PageResult<BatchQueryTransferResultDTO> result = null;
                try {
                    result = invokeRemoteRemitService.getRemitInfo(queryParam);
                    if(null == result.getList()){
                        logger.info("### no remittance be found!");
                        return;
                    }
                    logger.info("### result count:"+result.getTotalCount());
                    logger.info("### result totalPageSize:"+result.getTotalPageSize());
                } catch (Throwable throwable) {
                    logger.info("",throwable);
                    return;
                }
                // 查询明细存储在result.getList()中
                for(BatchQueryTransferResultDTO resultDTO:result.getList()){
                    logger.info("getRemitInfo param--->BatchQueryTransferResultDTO :"+ JSONUtils.toJsonString(resultDTO));
//                  TODO 此处最好用批量插入,频繁操作会导致DB连接数紧张  后期更改
                    String yeepayOrderID = resultDTO.getMctCreator();
                    Remittance remittance = remittanceService.findByYeepayOrderId(yeepayOrderID);
                    remittance.setRemitStatus(RemitStatus.SUCCESS);
                    remittance.setCallbackTime(new Date());
                    remittance.setFee(remittance.getFee().add(new BigDecimal(resultDTO.getFee())));
                    CustomerSettleDetail customerSettleDetail = new CustomerSettleDetail();
                    this.initSettleDetail(resultDTO,customerSettleDetail,remittances);
                    remittanceBizService.updateRemittanceAndSaveCustomerSettleDatil(remittance,customerSettleDetail);
                }
            }

        }
    }

    @Override
    public void doRemit() {



    }

    private void initSettleDetail(BatchQueryTransferResultDTO resultDTO,CustomerSettleDetail
            customerSettleDetail,Remittance remittances){
        BigDecimal settleAmount = new BigDecimal(resultDTO.getSrcAmount()).subtract(new BigDecimal(resultDTO.getFee()));
        customerSettleDetail.setCustomerNumber(remittances.getCustomerNumber());
        customerSettleDetail.setBatchId(resultDTO.getBatchCode());
        customerSettleDetail.setCreateTime(new Date());
        customerSettleDetail.setSettleAmount(settleAmount);
        customerSettleDetail.setSettleRealAmount(settleAmount);
        customerSettleDetail.setSettleFeeAmount(remittances.getFee());
        customerSettleDetail.setSettleTime(resultDTO.getFinishDate());//获取打款完成时间
        customerSettleDetail.setSettleType(SettleType.S0);
        customerSettleDetail.setYeepayOrderId(resultDTO.getMctCreator());//获取商户订单号 下单orderid传的yeepayrderid
    }
}
