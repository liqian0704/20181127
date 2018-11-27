package com.yeepay.g3.core.ymf.biz.remit.impl;

import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.core.ymf.utils.common.ThreadPoolUtil;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferParamDTO;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferResultDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferParamDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferParamDetailDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferSource;
import com.yeepay.g3.facade.balance.facade.BalanceRemitFacade;
import com.yeepay.g3.facade.balance.facade.BatchQueryTransferFacade;
import com.yeepay.g3.facade.balance.page.PageResult;
import com.yeepay.g3.facade.laike.dto.ResponseStatus;
import com.yeepay.g3.facade.laike.dto.S0InfoRequest;
import com.yeepay.g3.facade.laike.dto.S0InfoResponse;
import com.yeepay.g3.facade.laike.facade.ProductInfoFacade;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.OrderStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.RemitStatus;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.log.utils.StringUtil;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yeepay.g3.core.ymf.constants.Constants.bankCodeTransferMap;
import static com.yeepay.g3.utils.rmi.RemoteServiceFactory.getService;

/**
 * Created by dongxulu on 17/4/25.
 */
@Service
public class InvokeRemoteRemitServiceImpl implements InvokeRemoteRemitService {
    private static final Logger log = LoggerFactory.getLogger(InvokeRemoteRemitServiceImpl.class);
    @Autowired
    private RemittanceService remittanceService;
    @Autowired
    private CustomerService customerService;
    @Override
    public void doRemittance(final Order order,final Payment payment) {
        ThreadPoolUtil.getInstance().excutorTask(new Runnable() {
            @Override
            public void run() {

                Remittance remittance = remittanceService.findByYeepayOrderId(payment.getYeepayOrderId());
                log.info("### begin remit order.customerOrderID:"+order.getCustomerOrderId());
                if(null ==remittance){
                    remittance = new Remittance();
                }else{
                    if(RemitStatus.SUCCESS.equals(remittance.getRemitStatus())) {
                        log.info("### remittance already remit success! order.customerOrderId:" + order.getCustomerOrderId());
                        return;
                    }
//                  只要生成了打款记录,就不在进行下一步的逻辑
                    log.info("remittance already exsist!");
                    return ;
                }
                TransferParamDTO param = new TransferParamDTO();
                log.info("### begin remit order.customerOrderID:"+order.getCustomerOrderId());
                try {
                   if(!OrderStatus.SUCCESS.equals(order.getOrderStatus())){
                       log.error("order not success  customerOrderID:"+order.getCustomerOrderId());
                       return;
                    }
                    Customer customer = customerService.findByCustomerNumber(order.getCustomerNumber());
                    //@TODO 打款调用 限制条件金额大于10 时间8:00只21:00  商户开通秒到
                    remitCheck(order,customer);
                    initRemittance(order,payment,remittance,param);
                } catch (YmfTrxException e) {
                    log.error("remit YmfTrxException customerOrderId: "+order.getCustomerOrderId(),e.getCode(),e.getMessage());
                    return;
                } catch (Throwable throwable) {
                    log.error("remit YmfTrxException customerOrderId: "+order.getCustomerOrderId(),throwable);
                    return;
                }
                TransferParamDTO resultDto ;
                try {
                    resultDto =  transferConfirm(param);
                    if(StringUtil.isEmpty(resultDto.getErrorCode())){
                        List<TransferParamDetailDTO> list = resultDto.getTransferParamDetail();
                        log.info(" ## remit remote service transferConfirm return data count:"+list.size());
//                           使用秒到,每笔交易为一个打款批次,因此打款明细只有一条,若后期改为批量打款,逻辑需重新设置
                        for(TransferParamDetailDTO detailDTO:list){
//                           此处因打款时 每批次只有一个 因此可以打印明细,若日后修改为批量打款,此处需谨慎
                            log.info("## transferConfirm return TransferParamDetailDTO:"+JSONUtils.toJsonString(detailDTO));
                            //打款返回信息,根据ErrorCode 是否有值判断打款状态 需要沟通以后是否修改
                            remittance.setFee(detailDTO.getFeeAmount());
                            remittance.setRemitStatus(RemitStatus.PROCESS);
                            remittance.setStatus(Status.SUCCESS);
                            }
                        remittanceService.save(remittance);
                    }else{
//                      即使打款失败 也进行打款记录的存储,后期可能会加入人工补单,所以需要有记录
                        remittanceService.save(remittance);
                        log.warn("###transferConfirm error !error msg :"+resultDto.getErrorMsg());
                        return;
                    }
                } catch (Throwable throwable) {
                    log.error("");
                    return;
                }

            }
        });
    }

    @Override
    public PageResult<BatchQueryTransferResultDTO> getRemitInfo(BatchQueryTransferParamDTO queryTransferParamDTO) throws Throwable{
        String url = CfgConstant.getRemitInfoUrl();
        BatchQueryTransferFacade batchQueryTransferFacade = (BatchQueryTransferFacade)RemotingProtocol.HESSIAN.getServieGenerator().
                createService(BatchQueryTransferFacade.class,url,null);
        log.info("invoke batchQueryTransfer request param:"+ JSONUtils.toJsonString(queryTransferParamDTO));
        PageResult<BatchQueryTransferResultDTO> result = batchQueryTransferFacade.batchQueryTransfer(queryTransferParamDTO);
        log.info("invoke batchQueryTransfer response param:"+ JSONUtils.toJsonString(queryTransferParamDTO));
        return result;
    }

    @Override
    public TransferParamDTO transferConfirm(TransferParamDTO param) throws Throwable{
        //打款已做防重复处理,自己处理可使用redis锁 锁订单
        log.info("request Remote RemitService Param:"+JSONUtils.toJsonString(param));
        String url = CfgConstant.getRemitServiceUrl();
        log.info("request Remote RemitService url:"+url);
        BalanceRemitFacade balanceRemitFacade =
                (BalanceRemitFacade)RemotingProtocol.HESSIAN.getServieGenerator().
                        createService(BalanceRemitFacade.class,url,null);
        TransferParamDTO resultDto = balanceRemitFacade.transferConfirm(param);
        log.info("###balanceRemitFacade.transferConfirm result: "+JSONUtils.toJsonString(resultDto));
        return resultDto;
    }

    private void initRemittance(Order order,Payment payment,Remittance remittance,TransferParamDTO param ) throws Throwable {
        String customerNumber = order.getCustomerNumber();
        remittance.setCreateTime(new Date());
        remittance.setRemitStatus(RemitStatus.INIT);
        remittance.setCustomerNumber(customerNumber);
        remittance.setStatus(Status.INIT);
        remittance.setAmount(order.getSubstractFeeOrderAmount());
        remittance.setCustomerOrderId(order.getCustomerOrderId());
        remittance.setYeepayOrderId(payment.getYeepayOrderId());
        initS0INFO(customerNumber,param,order,payment);

    }
    private void initS0INFO(String customerNumber,TransferParamDTO param,Order order,Payment payment ) throws YmfTrxException,Throwable {

        S0InfoRequest request = new S0InfoRequest();
        request.setMerchantNo(customerNumber);
        ProductInfoFacade  productInfoFacade=getService(RemotingProtocol.HESSIAN,ProductInfoFacade.class);
        log.info("###invoke productInfoFacade.findS0Info param:"+JSONUtils.toJsonString(request));
        S0InfoResponse response = productInfoFacade.findS0Info(request);
        if(!ResponseStatus.SUCCESS.equals(response.getStatus()) ){
            log.error("productInfoFacade.findS0Info error: response"+JSONUtils.toJsonString(response));
            return ;
         }
        log.info("###invoke productInfoFacade.findS0Info return param:"+JSONUtils.toJsonString(response));
        //初始化打款参数
        param.setProduct(CfgConstant.getRemitProductCode()); // 默认填写RJT 日结通 WTJS委托结算
        param.setTransferSource(TransferSource.INTERFACE_RJT_BATCH); //
        // 请求来源(如果涉及将来区分计算收入等业务可以申请添加自己业务单独的请求来源)
        // INTERFACE_WTJS_BATCH 代付
        // INTERFACE_RJT_BATCH 日结通
        param.setSecondProduct(CfgConstant.getRemitSecondProductCode()); // 二级产品码 需要产品确定下来客的二级产品码LIKER，没有就不填
        param.setRequestTime(new Date());
        param.setCustomerNumber(customerNumber); // 商户编号
        String agentNo = response.getAgentNo();
        if(StringUtils.isEmpty(agentNo)){
            log.info("no agentNo customerOrderID:"+order.getCustomerOrderId());
            throw new YmfTrxException(TrxCode.T1018);
        }
        param.setGroupNumber(agentNo); // 集团商户编号(非集团版本与商户编号填写一致就可以)
        param.setRepay(true); // 是否校验同批次重复出款
        param.setTotalCount(1); // 笔数
        param.setTotalAmount(order.getSubstractFeeOrderAmount()); // 总金额 订单金额减去手续费
        param.setTransferParamDetail(new ArrayList<TransferParamDetailDTO>());
        // 出款明细  在来客app后台查询代理关系以及结算银行信息
        TransferParamDetailDTO detailDTO = new TransferParamDetailDTO();
        detailDTO.setFeeType(CfgConstant.getRemitFeeType()); // 手续费模式 SOURCE商户承担 TARGET用户承担
        detailDTO.setUrgency(true); // 是否加急
        //打款批次使用 订单参考号左补零,满15位
        detailDTO.setBatchNo("000"+order.getExternalId()); // 批次号 15位数字 商户唯一
        detailDTO.setOrderID(payment.getYeepayOrderId()); // 订单号 50位内字母数字 商户唯一
        // 银行名称和编码有一项填写就可以。系统优先使用名称模糊匹配银行
        detailDTO.setBankName(response.getSettleBankName()); // 银行名称
        String bankCode = response.getSettleBankCode();
        bankCode=bankCodeTransferMap.containsKey(response.getSettleBankCode())?bankCodeTransferMap.get(bankCode):bankCode;
        detailDTO.setBankCode(bankCode); // 银行编码
        detailDTO.setBankBranch(response.getBranchBankName()); // 支行名称
        detailDTO.setCityCode(""); // 市编码
        detailDTO.setProvinceCode(""); // 省编码
        detailDTO.setAccountName(response.getLegalName()); // 收款人 TODO 接口返回
        detailDTO.setAccountNumber(response.getSettleCardNo()); // 卡号
        detailDTO.setAmount(order.getSubstractFeeOrderAmount()); // 金额
        param.getTransferParamDetail().add(detailDTO);
    }


    void remitCheck(Order order,Customer customer) throws YmfTrxException{
        log.info("###begin remitCheck customerOrderID:"+order.getCustomerOrderId());
        BigDecimal trxAmount = order.getTrxAmt();
        //商户是否开通秒到
        if(!BalanceType.S0.equals(customer.getBalanceProduct())){
            throw new YmfTrxException(TrxCode.T1035);
        }
        String amountLimit=null;
        amountLimit = CfgConstant.getRemitAmountLimit();
        //秒到打款周期限制
        if(DateUtil.remitTimeLimit(order.getCreateTime())){
            log.info("order create_time:"+order.getCreateTime());
            throw new YmfTrxException(TrxCode.T1037);
        }
        BigDecimal limit = new BigDecimal(amountLimit);
        //起结金额判断
        if(1 == limit.compareTo(trxAmount)){
            log.info("order trxAmount Limit:"+order.getTrxAmt());
            throw new YmfTrxException(TrxCode.T1036);
        }
    }

}
