package com.yeepay.g3.core.ymf.service.remit;

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.junit.SoaContextAwareTest;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferParamDTO;
import com.yeepay.g3.facade.balance.dto.query.BatchQueryTransferResultDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferParamDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferParamDetailDTO;
import com.yeepay.g3.facade.balance.dto.remitdto.TransferSource;
import com.yeepay.g3.facade.balance.facade.BalanceRemitFacade;
import com.yeepay.g3.facade.balance.facade.BatchQueryTransferFacade;
import com.yeepay.g3.facade.balance.page.PageResult;
import com.yeepay.g3.facade.laike.dto.S0InfoRequest;
import com.yeepay.g3.facade.laike.dto.S0InfoResponse;
import com.yeepay.g3.facade.laike.facade.ProductInfoFacade;
import com.yeepay.g3.facade.ymf.facade.laike.RemitInfoQueryFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dongxulu on 17/4/26.
 */
public class InVokeRemoteRemitServiceTest extends SoaContextAwareTest {



    @Test
    /**
     * 打款操作
     */
    public void doRemit(){
        //初始化打款参数

        TransferParamDTO param = new TransferParamDTO();
        param.setProduct("RJT"); // 默认填写RJT 日结通 WTJS委托结算
        param.setTransferSource(TransferSource.INTERFACE_RJT_BATCH); //
        // 请求来源(如果涉及将来区分计算收入等业务可以申请添加自己业务单独的请求来源)
        // INTERFACE_WTJS_BATCH 代付
        // INTERFACE_RJT_BATCH 日结通
        param.setSecondProduct("LAIKER"); // 二级产品码 需要产品确定下来客的二级产品码LIKER，没有就不填
        param.setRequestTime(new Date());
        param.setCustomerNumber("10040040900"); // 商户编号
        param.setGroupNumber("10040040900"); // 集团商户编号(非集团版本与商户编号填写一致就可以)
        param.setRepay(true); // 是否校验同批次重复出款

        param.setTotalCount(1); // 笔数
        param.setTotalAmount(new BigDecimal("11.00")); // 总金额
        param.setTransferParamDetail(new ArrayList<TransferParamDetailDTO>());
        // 出款明细
        TransferParamDetailDTO detailDTO = new TransferParamDetailDTO();

        detailDTO.setFeeType("SOURCE"); // 手续费模式 SOURCE商户承担 TARGET用户承担
        detailDTO.setUrgency(true); // 是否加急
        detailDTO.setBatchNo("741646248924503"); // 批次号 15位数字 商户唯一
        detailDTO.setOrderID("Ttt23234723d34543423e33"); // 订单号 50位内字母数字 商户唯一

        // 银行名称和编码有一项填写就可以。系统优先使用名称模糊匹配银行
        detailDTO.setBankName("农业银行"); // 银行名称
        detailDTO.setBankCode("ABC"); // 银行编码
        detailDTO.setBankBranch("农业银行"); // 支行名称
        detailDTO.setCityCode(""); // 市编码
        detailDTO.setProvinceCode(""); // 省编码
        detailDTO.setAccountName("王鑫"); // 收款人
        detailDTO.setAccountNumber("21212121212"); // 卡号
        detailDTO.setAmount(new BigDecimal("11.00")); // 金额
        param.getTransferParamDetail().add(detailDTO);
        String url = CfgConstant.getRemitServiceUrl();
        BalanceRemitFacade balanceRemitFacade = (BalanceRemitFacade) RemotingProtocol.HESSIAN.getServieGenerator().
                createService(BalanceRemitFacade.class,
                        "http://10.151.30.20:8066/balance-hessian/hessian/BalanceRemitFacade",null);
        TransferParamDTO resultDto = balanceRemitFacade.transferConfirm(param);
        System.out.println(JSONUtils.toJsonString(resultDto));
    }

    @Test
//    query balance system
    public void queryRemitInfo() throws ParseException {
        BatchQueryTransferParamDTO queryParam = new BatchQueryTransferParamDTO();
//        String url = CfgConstant.getRemitInfoUrl();
        String url = "http://172.17.102.5:18066/balance-hessian/hessian/BatchQueryTransferFacade";
        SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BatchQueryTransferFacade brf = (BatchQueryTransferFacade) RemotingProtocol.HESSIAN.getServieGenerator().
                createService(BatchQueryTransferFacade.class, url, null);
        queryParam.setCustomerId(40041016l);  //商户ID
        queryParam.setProduct("RJT");  //产品类型  RJT 日结通  WTJS 委托结算
        queryParam.setCreateDateStart(sf3.parse("2017-05-11 17:00:00"));
        queryParam.setCreateDateEnd(sf3.parse("2017-05-12 21:41:52"));
        queryParam.setBankTrxStatus("SUCCESS");
        // queryParam.setCreateDateStart(sf3.parse("2016-03-10 00:00:00"));
        // queryParam.setCreateDateEnd(sf3.parse("2016-03-17 00:00:00"));
        // queryParam.setMctCreator("20160325NEWTZT100001"); // 订单号
        // queryParam.setBatchCode("201603251026387"); // 批次号
        queryParam.setPageNo(1); // 页数
        queryParam.setPageSize(40000); // 每页数目
        PageResult<BatchQueryTransferResultDTO> result = brf.batchQueryTransfer(queryParam);
        // 查询明细存储在result.getList()中
        System.out.println(JSONUtils.toJsonString(result));
        System.out.println(result.getList().size());
        System.out.println(JSONUtils.toJsonString(result.getList()));
    }

    @Test
//  query laike
    public void doInvokeLaikeS0InfoTest() throws ParseException {
        SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        S0InfoRequest request = new S0InfoRequest();
        request.setMerchantNo("10040041016");
        String url="http://10.151.32.27:30036/laike-hessian/hessian/ProductInfoFacade";
        ProductInfoFacade productInfoFacade =
                (ProductInfoFacade)RemotingProtocol.HESSIAN.getServieGenerator().
                        createService(ProductInfoFacade.class, url, null);
//      ProductInfoFacade productInfoFacade=getService(ProductInfoFacade.class);
        S0InfoResponse response = productInfoFacade.findS0Info(request);
        System.out.println(JSONUtils.toJsonString(response));
//        http://10.151.32.27:30036/laike-hessian/hessian/ProductInfoFacade]
//        http://10.151.32.27:30036/laike-hessian/hessian/ProductInfoFacade]
//        hessianx://172.22.2.4:8088/laike-hessian/soa/hessian/com.yeepay.g3.facade.laike.facade.ProductInfoFacade
    }
    @Test
    public void queryRemitInfoFromRemote(){

        RemitInfoQueryFacade remitInfoQueryFacade = getService(RemitInfoQueryFacade.class);
        remitInfoQueryFacade.queryRemitInfo(1,1000);
    }



}
