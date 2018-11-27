package com.yeepay.g3.core.ymf.biz.remit.impl;

import com.yeepay.g3.core.ymf.biz.remit.InvokeRemoteRemitService;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleDetail;
import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.remit.Remittance;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.CustomerSettleDetailService;
import com.yeepay.g3.core.ymf.service.order.OrderService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceBizService;
import com.yeepay.g3.core.ymf.service.remit.RemittanceService;
import com.yeepay.g3.core.ymf.utils.common.AmountUtil;
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
import com.yeepay.g3.facade.ymf.enumtype.trade.*;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.log.utils.StringUtil;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
    @Autowired
    private OrderService orderService;
    @Autowired
    private RemittanceBizService remittanceBizService;
    @Autowired
    private CustomerSettleDetailService customerSettleDetailService;

    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 调用远程系统，请求打款
     * @param order     订单
     * @param payment   支付信息
     * @param remittance    打款记录
     */
    @Override
    public void requestRemittance(Order order, Payment payment, Remittance remittance) throws Exception {
        try {
            TransferParamDTO param = initS0INFO(order, payment);
            TransferParamDTO resultDto = transferConfirm(param);
            //商户结算信息表
            CustomerSettleDetail settleDetail = customerSettleDetailService.findByYeepayOrderId(
                    remittance.getYeepayOrderId());
            if (StringUtil.isEmpty(resultDto.getErrorCode())) {
                List<TransferParamDetailDTO> list = resultDto.getTransferParamDetail();
                //使用秒到,每笔交易为一个打款批次,因此打款明细只有一条,若后期改为批量打款,逻辑需重新设置
                TransferParamDetailDTO detailDTO = list.get(0);

                remittance.setRemitStatus(RemitStatus.PROCESS);
                remittance.setStatus(Status.SUCCESS);
                remittance.setBatchNo(detailDTO.getBatchNo());

                if (settleDetail != null) {
                    //兼容老数据
                    settleDetail.setSettleStatus(RemitStatus.PROCESS);
                    settleDetail.setBatchId(detailDTO.getBatchNo());
                }
                remittanceBizService.updateRemittanceAndSaveCustomerSettleDatil(remittance,settleDetail);

                /** 更新为S0  目前由客户端传
                Order newOrder = orderService.findById(order.getId());
                newOrder.setBalanceType(BalanceType.S0);
                orderService.update(newOrder);
                 */
            } else {
                log.warn("###transferConfirm error !error msg :" + resultDto.getErrorMsg());

                String failMsg = "";
                List<TransferParamDetailDTO> list = resultDto.getTransferParamDetail();
                TransferParamDetailDTO detailDTO = list.get(0);
                failMsg += "[" + detailDTO.getErrorCode() + "]" + detailDTO.getErrorMsg();

                //[0035]订单号不合法或重复;当第一次打款连接超时，第二次重新跑打款会报订单号重复。
                if ("0035".equals(detailDTO.getErrorCode())) {
                    remittance.setRemitStatus(RemitStatus.PROCESS);
                    if (settleDetail != null) {
                        settleDetail.setSettleStatus(RemitStatus.PROCESS);
                    }
                }

                if (settleDetail != null) {
                    //此判断为兼容老数据
                    settleDetail.setBatchId(detailDTO.getBatchNo());
                    settleDetail.setRemark(failMsg);//失败原因
                }

                remittance.setStatus(Status.FAIL);
                remittance.setBatchNo(detailDTO.getBatchNo());
                remittance.setDescription(failMsg);

                remittanceBizService.updateRemittanceAndSaveCustomerSettleDatil(remittance,settleDetail);
                return;
            }
        } catch (Throwable throwable) {
            //处理打款时异常，后期可考虑补上邮件通知
            log.error("doRemittance throwable :", throwable);
            throw new Exception("处理请求打款失败:",throwable);
        }
    }

    @Override
    public void doRemittance(final Order order, final Payment payment) {
        ThreadPoolUtil.getInstance().excutorTask(new Runnable() {
            @Override
            public void run() {
                Remittance remittance = remittanceService.findByYeepayOrderId(payment.getYeepayOrderId());
                log.info("### begin remit order.customerOrderID:" + order.getCustomerOrderId());
                if (null == remittance) {
                    remittance = new Remittance();
                } else {
//                  只要生成了打款记录,就不在进行下一步的逻辑
                    log.info("remittance already exsist!");
                    return;
                }
                log.info("### begin remit order.customerOrderID:" + order.getCustomerOrderId());
                try {
                    if (!OrderStatus.SUCCESS.equals(order.getOrderStatus())) {
                        log.error("order not success  customerOrderID:" + order.getCustomerOrderId());
                        return;
                    }
                    Customer customer = customerService.findByCustomerNumber(order.getCustomerNumber());
                    if (reentrantLock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                        //@TODO 打款调用 限制条件金额大于10 时间8:00只21:00  商户开通秒到
                        remitCheck(order, customer);
                        initRemittance(order, payment, remittance);
                        CustomerSettleDetail settleDetail = new CustomerSettleDetail();
                        initSettleDetail(settleDetail, remittance, order);
                        //同时保存打款记录表和商户结算信息表
                        remittanceBizService.saveRemittanceAndCustomerSettleDatil(remittance,settleDetail);
                    }
                } catch (YmfTrxException e) {
                    log.error("remit YmfTrxException customerOrderId: " + order.getCustomerOrderId(), e.getCode(), e.getMessage());
                    return;
                } catch (Throwable throwable) {
                    log.error("remit YmfTrxException customerOrderId: " + order.getCustomerOrderId(), throwable);
                    return;
                } finally {
                    log.info("release lock ");
                    reentrantLock.unlock();
                }
            }
        });
    }

    /**
     * 查询 日结通 打款信息
     */
    @Override
    public PageResult<BatchQueryTransferResultDTO> getRemitInfo(BatchQueryTransferParamDTO queryTransferParamDTO) throws Throwable{
        String url = CfgConstant.getRemitInfoUrl();
        BatchQueryTransferFacade batchQueryTransferFacade = (BatchQueryTransferFacade)RemotingProtocol.HESSIAN.getServieGenerator().
                createService(BatchQueryTransferFacade.class,url,null);
        log.info("invoke batchQueryTransfer request param:"+ JSONUtils.toJsonString(queryTransferParamDTO));
        PageResult<BatchQueryTransferResultDTO> result = batchQueryTransferFacade.batchQueryTransfer(queryTransferParamDTO);
        log.info("invoke batchQueryTransfer response param:"+ JSONUtils.toJsonString(result));
        return result;
    }

    /**
     * 请求打款系统
     */
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

    /**
     * 初始化打款记录
     */
    private void initRemittance(Order order,Payment payment,Remittance remittance) throws Throwable {
        String customerNumber = order.getCustomerNumber();
        remittance.setCreateTime(new Date());
        remittance.setRemitStatus(RemitStatus.INIT);
        remittance.setCustomerNumber(customerNumber);
        remittance.setStatus(Status.INIT);//
        remittance.setAmount(AmountUtil.getSubstractFeeOrderAmount(order.getTrxAmt(),order.getFee()));
        remittance.setFee(order.getFee());
        remittance.setCustomerOrderId(order.getCustomerOrderId());
        // 区分记录是联盟还是代理商
        remittance.setRemark(order.getPayConfirm());
        remittance.setYeepayOrderId(payment.getYeepayOrderId());
    }

    /**
     * 初始化商户打款信息表
     * @param customerSettleDetail
     * @param remittances
     */
    private void initSettleDetail(CustomerSettleDetail customerSettleDetail,Remittance remittances,Order order){
        customerSettleDetail.setCustomerNumber(remittances.getCustomerNumber());
        //此处 交易手续费计入结算表
        customerSettleDetail.setTradeFeeAmount(remittances.getFee());
        customerSettleDetail.setSettleAmount(remittances.getAmount());
        customerSettleDetail.setCreateTime(new Date());
        customerSettleDetail.setSettleType(SettleType.S0);
        //获取商户订单号 下单orderid传的yeepayrderid
        customerSettleDetail.setYeepayOrderId(remittances.getYeepayOrderId());
        customerSettleDetail.setExternalId(order.getExternalId());
        customerSettleDetail.setSettleStatus(RemitStatus.INIT);
    }

    /**
     * 初始化 日结通 请求参数
     */
    private TransferParamDTO initS0INFO(Order order,Payment payment ) throws YmfTrxException,Throwable {
        TransferParamDTO param = new TransferParamDTO();
        S0InfoRequest request = new S0InfoRequest();
        request.setMerchantNo(order.getCustomerNumber());
        ProductInfoFacade  productInfoFacade=getService(RemotingProtocol.HESSIAN,ProductInfoFacade.class);
        log.info("###invoke productInfoFacade.findS0Info param:"+JSONUtils.toJsonString(request));
        S0InfoResponse response = productInfoFacade.findS0Info(request);
        if(!ResponseStatus.SUCCESS.equals(response.getStatus()) ){
            log.error("productInfoFacade.findS0Info error: response"+(null==response?"null":response.toString()));
            return null ;
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
        param.setCustomerNumber(order.getCustomerNumber()); // 商户编号
        param.setGroupNumber(order.getCustomerNumber()); // 集团商户编号(非集团版本与商户编号填写一致就可以) 统一传子商户号,日结通会去客户中心验证
        param.setRepay(true); // 是否校验同批次重复出款
        param.setTotalCount(1); // 笔数
        param.setTotalAmount(AmountUtil.getSubstractFeeOrderAmount(order.getTrxAmt(),order.getFee())); // 总金额 订单金额减去手续费
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
        detailDTO.setAmount(AmountUtil.getSubstractFeeOrderAmount(order.getTrxAmt(),order.getFee())); // 金额
        param.getTransferParamDetail().add(detailDTO);
        return param;
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
