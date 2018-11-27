package PayCompany_TestCase;

import Accounting_entity.OrderInfoEntity;
import Accounting_entity.PayComOtherFeeRecordEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.PayCompanyBillDaemonFacade;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class PayCompany07_payCompanyBillProcessTask {
   static String envFlag=CommPro.env;
   static String envuri=CommPro.getEnvURI(envFlag);
   static PayCompanyBillDaemonFacade promoterBillDaemonFacade;
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


       try {
           promoterBillDaemonFacade= (PayCompanyBillDaemonFacade) hessianProxyFactory.create(PayCompanyBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/PayCompanyBillDaemonFacade");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }
    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 生成支付公司账单
     */

@Test()
    public void promoterBillProcessTask() throws IOException, ParseException {
    System.out.println(envuri);
    promoterBillDaemonFacade.payCompanyBillProcess(null,null,100,null,null,null,null);

    System.out.println("promoterBillProcess执行完成");

    //获取账单周期
    int periodDate = BusinessUtil.selectPMPeriodRuleByBillType("1042781335", "PAY_FEE_BILL");
    //算出账单周期开始时间
    String startDate = BusinessUtil.getCDate("1042781335", "PAY_FEE_BILL");

    //算出账单结束时间

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    String endDate =BusinessUtil.getNDate("1042781335", "PAY_FEE_BILL");

    System.out.println(endDate);

    BigDecimal toPayCompanyDIRECT_POINTSFee=BigDecimal.valueOf(0);

    BigDecimal toPayCompanyRJTFee=BigDecimal.valueOf(0);

    BigDecimal toPayCompanyWTJSFee=BigDecimal.valueOf(0);

    BigDecimal directPointFeeRate=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.DIRECT_POINTS).getFeeRate();
    BigDecimal feeDirectPointFeeAmount=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.DIRECT_POINTS).getFeeAmount();

    BigDecimal rjtFeeRate=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.RJT).getFeeRate();

    BigDecimal rjtFeeAmout=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.RJT).getFeeAmount();

    BigDecimal WTJSFeeRate=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.WTJS).getFeeRate();

    BigDecimal WTJSFeeAmount=BusinessUtil.selectPayComOrderFeeForByPro("1042781335", PayProductEnum.WTJS).getFeeAmount();

    List<OrderInfoEntity> list= BusinessUtil.selectOrderByPMNoandPeriod("1042781335",startDate,endDate);

    List<PayComOtherFeeRecordEntity> paycompanyOtherfeeList=BusinessUtil.selectPayComOtherFeeRecordByDate("1042781335",startDate,endDate);



    if(list.size()>0){

        for(OrderInfoEntity orderInfoEntity:list){
            if(orderInfoEntity.getProductCode().equals("PAY_STANDARD")){

                BigDecimal feeDirectPointAmount=orderInfoEntity.getOrderAmount().multiply(directPointFeeRate).add(feeDirectPointFeeAmount);

                toPayCompanyDIRECT_POINTSFee=toPayCompanyDIRECT_POINTSFee.add(feeDirectPointAmount);

            }else if (orderInfoEntity.getProductCode().equals("QUICK_PAY_STANDARD")){

                BigDecimal feeDirectPointAmount=orderInfoEntity.getOrderAmount().multiply(directPointFeeRate).add(feeDirectPointFeeAmount);

                BigDecimal feeRJTAmount=orderInfoEntity.getOrderAmount().multiply(rjtFeeRate).add(rjtFeeAmout);

                toPayCompanyRJTFee=toPayCompanyRJTFee.add(feeRJTAmount).add(feeRJTAmount);

                toPayCompanyDIRECT_POINTSFee=toPayCompanyDIRECT_POINTSFee.add(feeDirectPointAmount);


            }
        }
    }
    if(paycompanyOtherfeeList.size()>0){

        for(PayComOtherFeeRecordEntity payComOtherFeeRecordEntity:paycompanyOtherfeeList){

            if(payComOtherFeeRecordEntity.getProductCode().equals("WTJS")){

                BigDecimal FeeWTJSAmount=payComOtherFeeRecordEntity.getOrderAmount().multiply(WTJSFeeRate).add(WTJSFeeAmount);

                toPayCompanyWTJSFee=toPayCompanyWTJSFee.add(FeeWTJSAmount);

            }
        }
    }

   // BusinessUtil.selectPayCompayBillByDateAndCostType()





}



}
