package PayCompany_TestCase;


import Accounting_entity.PayCompanyFeeFormulaEntity;
import Accounting_entity.PayCompanyOrderFeeRecordEntity;
import Accounting_entity.TotalBillEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.common.enums.FeeTypeEnum;
import com.miitang.facade.accounting.daemon.OrderInfoDaemonFacade;
import com.miitang.facade.accounting.enumtype.BillStatusEnum;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by yp-tc-2646 on 18/3/5.
 * 计费定时
 */
public class PayCompany05_OrderInfoDaemonTask {
    String envFlag=CommPro.env;
    String envuri=CommPro.getEnvURI(envFlag);
@Test
    public void addPayCompanyFee() throws IOException, ParseException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        OrderInfoDaemonFacade orderInfoDaemonFacade = null;
        System.out.println(envuri);

    //查询是否已生成总账单
    String billStartDate=BusinessUtil.getCDate("1042781335",CommPro.payCompayBill);
   List<TotalBillEntity> totalBillEntityList= BusinessUtil.selectTotalBillByType("1042781335",billStartDate,CommPro.payCompayBill);
    if(totalBillEntityList.size()==0){

        System.out.println("还未生成汇总账单");

        return;

    }else if(totalBillEntityList.get(0).getBillStatus()!= BillStatusEnum.INIT){

        System.out.println("本期账单已计费完成,无需再次计费");

        return;
    }else {
    //根据账单状态判断是否执行计费
        BillStatusEnum billStatusEnum=totalBillEntityList.get(0).getBillStatus();
        if(billStatusEnum.equals(BillStatusEnum.INIT)){
        try {
            try {
                orderInfoDaemonFacade= (OrderInfoDaemonFacade)hessianProxyFactory.create(OrderInfoDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/OrderInfoDaemonFacade");
                //boolean respose1=orderInfoDaemonFacade.queryOrderInfo2MTOrderFeeRecord(null,null,null,null,null,"10427813356",null);
                boolean response2=orderInfoDaemonFacade.queryOrderInfo2PayCompanyOrderFeeRecord(null,null,null,null,null,null,null);
                // boolean response3=orderInfoDaemonFacade.queryOrderInfo2MTOrderCalFee(null,null,null,null,null,"10427813356",null);
                boolean response4=orderInfoDaemonFacade.queryOrderInfo2PayCompanyOrderCalFee(null,null,null,null,null,null,null);
                System.out.println(""+response2+response4);

                //获取账单周期
                int periodDate = BusinessUtil.selectPMPeriodRuleByBillType("1042781335", "PAY_FEE_BILL");
                //算出账单周期开始时间
                String startDate = BusinessUtil.getCDate("1042781335", "PAY_FEE_BILL");

                //算出账单结束时间

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

                String endDate =BusinessUtil.getNDate("1042781335", "PAY_FEE_BILL");
                System.out.println(endDate);

                List<PayCompanyOrderFeeRecordEntity> list=BusinessUtil.selectPayComOrderFeeRecordByDate(startDate,endDate);

                //验证计费
                if(list.size()>0){
                    System.out.println("断言");
                    for(PayCompanyOrderFeeRecordEntity payCompanyOrderFeeRecordEntity:list){

                        if(payCompanyOrderFeeRecordEntity.getPayProduct().equals(PayProductEnum.DIRECT_POINTS)){

                            PayCompanyFeeFormulaEntity PayCompanyFeeFormulaEntity=BusinessUtil.selectPayComOrderFeeFormularByPro("1042781335",PayProductEnum.DIRECT_POINTS);
                            FeeTypeEnum feeType=PayCompanyFeeFormulaEntity.getFeeType();
                            BigDecimal feeRate=PayCompanyFeeFormulaEntity.getFeeRate();
                            BigDecimal feeAmout=PayCompanyFeeFormulaEntity.getFeeAmount();
                            BigDecimal maxAmount=PayCompanyFeeFormulaEntity.getMaxAmount();
                            BigDecimal minAmount=PayCompanyFeeFormulaEntity.getMinAmount();
                            System.out.println("feeType"+feeType+"feeRate"+feeRate);
                            BigDecimal toPaycompanyAmout=payCompanyOrderFeeRecordEntity.getPayAmount().multiply(feeRate).add(feeAmout);
                            System.out.println(toPaycompanyAmout);
                            System.out.println(payCompanyOrderFeeRecordEntity.getToPayCompanyAmount());
                            Assert.assertEquals(toPaycompanyAmout,payCompanyOrderFeeRecordEntity.getToPayCompanyAmount());
                        }else if(payCompanyOrderFeeRecordEntity.getPayProduct().equals(PayProductEnum.RJT)){
                            PayCompanyFeeFormulaEntity PayCompanyFeeFormulaEntity=BusinessUtil.selectPayComOrderFeeFormularByPro("1042781335",PayProductEnum.DIRECT_POINTS);
                            FeeTypeEnum feeType=PayCompanyFeeFormulaEntity.getFeeType();
                            BigDecimal feeRate=PayCompanyFeeFormulaEntity.getFeeRate();
                            BigDecimal feeAmout=PayCompanyFeeFormulaEntity.getFeeAmount();
                            BigDecimal maxAmount=PayCompanyFeeFormulaEntity.getMaxAmount();
                            BigDecimal minAmount=PayCompanyFeeFormulaEntity.getMinAmount();
                            BigDecimal toPaycompanyAmout=payCompanyOrderFeeRecordEntity.getPayAmount().multiply(feeRate).add(feeAmout);
                            System.out.println(toPaycompanyAmout);
                            System.out.println(payCompanyOrderFeeRecordEntity.getToPayCompanyAmount());
                            Assert.assertEquals(toPaycompanyAmout,payCompanyOrderFeeRecordEntity.getToPayCompanyAmount());
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } finally {
            }
        } finally {
        }
        }else {
            System.out.println("本期账单已参与过计费,无需再次执行计费");
        }




             }

        }
}
