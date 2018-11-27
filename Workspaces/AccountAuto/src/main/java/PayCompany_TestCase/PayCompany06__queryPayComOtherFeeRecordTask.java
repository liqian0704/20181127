package PayCompany_TestCase;

import Accounting_entity.PayComOtherFeeRecordEntity;
import Accounting_entity.PayCompanyFeeFormulaEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.common.enums.FeeTypeEnum;
import com.miitang.facade.accounting.daemon.PayComOtherFeeDaemonFacade;
import com.miitang.facade.accounting.enumtype.OrderFeeStatusEnum;
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


public class PayCompany06__queryPayComOtherFeeRecordTask {
   static String envFlag=CommPro.env;
   static String envuri=CommPro.getEnvURI(envFlag);
   static PayComOtherFeeDaemonFacade facade;
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();



       try {
           facade= (PayComOtherFeeDaemonFacade) hessianProxyFactory.create(PayComOtherFeeDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/PayComOtherFeeDaemonFacade");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }
    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 增加支付公司计费模版
     */

@Test()
    public void queryPayComOtherFeeRecord() throws IOException, ParseException {

    //获取账单周期
    int periodDate = BusinessUtil.selectPMPeriodRuleByBillType("1042781335", "PAY_FEE_BILL");
    //算出账单周期开始时间
    String startDate = BusinessUtil.getBillStartDate("1042781335", "PAY_FEE_BILL");

    //算出账单结束时间

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    String endDate = BusinessUtil.getBillEndDate("1042781335", "PAY_FEE_BILL");

    System.out.println(endDate);

    List<PayComOtherFeeRecordEntity> list = BusinessUtil.selectPayComOtherFeeRecordByDate("1042781335", startDate, endDate);

    //执行定时

    System.out.println(envuri);
    facade.queryPayComOtherFeeRecord(null, null, 100, null, null, null, null);
    System.out.println("totalBillToPayRecordTask执行完成");


    if (list.size() > 0) {

        for (PayComOtherFeeRecordEntity payComOtherFeeRecordEntity : list) {

            if (payComOtherFeeRecordEntity.getProductCode().equals("WTJS")) {
                PayCompanyFeeFormulaEntity PayCompanyFeeFormulaEntity = BusinessUtil.selectPayComOrderFeeFormularByPro("1042781335", PayProductEnum.WTJS);
                FeeTypeEnum feeType = PayCompanyFeeFormulaEntity.getFeeType();
                BigDecimal feeRate = PayCompanyFeeFormulaEntity.getFeeRate();
                BigDecimal feeAmout = PayCompanyFeeFormulaEntity.getFeeAmount();
                BigDecimal maxAmount = PayCompanyFeeFormulaEntity.getMaxAmount();
                BigDecimal minAmount = PayCompanyFeeFormulaEntity.getMinAmount();

                BigDecimal orderAmount = payComOtherFeeRecordEntity.getOrderAmount();
                BigDecimal Amount = payComOtherFeeRecordEntity.getFeeAmount();
                BigDecimal respectFeeAmount = orderAmount.multiply(feeRate).add(feeAmout);
                System.out.println(respectFeeAmount);
                Assert.assertEquals(Amount, respectFeeAmount);
                OrderFeeStatusEnum orderFeeStatusEnum = payComOtherFeeRecordEntity.getFeeStatus();
                Assert.assertEquals(OrderFeeStatusEnum.SUCCESS, orderFeeStatusEnum);
            }else if (payComOtherFeeRecordEntity.getProductCode().equals("WALLET")){
                PayCompanyFeeFormulaEntity PayCompanyFeeFormulaEntity = BusinessUtil.selectPayComOrderFeeFormularByPro("1042781335", PayProductEnum.WALLET);
                FeeTypeEnum feeType = PayCompanyFeeFormulaEntity.getFeeType();
                BigDecimal feeRate = PayCompanyFeeFormulaEntity.getFeeRate() == null?new BigDecimal(0):PayCompanyFeeFormulaEntity.getFeeRate();
                BigDecimal feeAmout = PayCompanyFeeFormulaEntity.getFeeAmount();
                BigDecimal maxAmount = PayCompanyFeeFormulaEntity.getMaxAmount();
                BigDecimal minAmount = PayCompanyFeeFormulaEntity.getMinAmount();
                BigDecimal orderAmount = payComOtherFeeRecordEntity.getOrderAmount();
                BigDecimal Amount = payComOtherFeeRecordEntity.getFeeAmount();
               // (feeRate==null)?0:feeRate;
                BigDecimal respectFeeAmount = orderAmount.multiply(feeRate).add(feeAmout).setScale(2);
                OrderFeeStatusEnum orderFeeStatusEnum = payComOtherFeeRecordEntity.getFeeStatus();
                System.out.println(respectFeeAmount);
                Assert.assertEquals(Amount, respectFeeAmount);



                Assert.assertEquals(OrderFeeStatusEnum.SUCCESS, orderFeeStatusEnum);

            }
        }

    }

}}
