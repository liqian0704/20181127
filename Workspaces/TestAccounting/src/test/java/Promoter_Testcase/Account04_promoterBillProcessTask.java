package Promoter_Testcase;

import Accounting_entity.ParentMerchantPeriodRuleEntity;
import Accounting_entity.PromoterPayRecordEntity;
import Accounting_entity.TotalBillEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.PromoterBillDaemonFacade;
import com.miitang.facade.accounting.enumtype.BillStatusEnum;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;


public class Account04_promoterBillProcessTask {
    static String envFlag = CommPro.env;
    static String envuri = CommPro.getEnvURI(envFlag);
    static PromoterBillDaemonFacade promoterBillDaemonFacade;

    static {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


        try {
            promoterBillDaemonFacade = (PromoterBillDaemonFacade) hessianProxyFactory.create(PromoterBillDaemonFacade.class, CommPro.getEnvURI(envFlag) + "/mt-accounting-hessian/hessian/PromoterBillDaemonFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 生成推广人账单
     */

    @Test()
    public void promoterBillProcess() throws IOException, ParseException {
        String billStartDate = BusinessUtil.getCDate("1042781335", CommPro.promoterBill);
        String billNo=BusinessUtil.selectTotalBillNoByDate("1042781335",billStartDate);
        if(billNo==null){
            System.out.println("无交易");
            return;
        }

        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRuleEntityList = BusinessUtil.selectPMRuleByBillType("1042781335", CommPro.promoterBill);

        int automatic = parentMerchantPeriodRuleEntityList.get(0).getAutomatic();

        promoterBillDaemonFacade.promoterBillProcess(null, null, 100, null, null, null, null);



        List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, "PROMOTER_BILL");
        if (totalBillEntities.size() > 0) {

            BillStatusEnum billStatus = totalBillEntities.get(0).getBillStatus();

            BigDecimal billAmount = totalBillEntities.get(0).getBillAmount();

            List<PromoterPayRecordEntity> promoterPayRecordEntityList = BusinessUtil.selectPromoterPayRecordEntityByBilNo("1042781335", billNo);

            BigDecimal sumFee = BigDecimal.valueOf(0);

            BigDecimal sumProfitAmount = BigDecimal.valueOf(0);

            BigDecimal expectSumProfitAmount = BigDecimal.valueOf(0);
            for (PromoterPayRecordEntity promoterPayRecordEntity : promoterPayRecordEntityList) {



                BigDecimal profitFee = promoterPayRecordEntity.getProfitFee();

                BigDecimal profitAmount = promoterPayRecordEntity.getProfitAmount();

                sumFee = sumFee.add(profitFee);

                sumProfitAmount = sumProfitAmount.add(profitAmount);

            }
            System.out.println(sumFee);
            System.out.println(sumProfitAmount);
            System.out.println(billAmount);

            expectSumProfitAmount = sumProfitAmount.subtract(sumFee);
            System.out.println(expectSumProfitAmount);
            Assert.assertTrue(billAmount.stripTrailingZeros().equals(expectSumProfitAmount));


            if (automatic == 1) {
                Assert.assertTrue(BillStatusEnum.CONFIRMED.equals(billStatus));

            } else {
                Assert.assertTrue(BillStatusEnum.GENERATED.equals(billStatus));
            }
            System.out.println("promoterBillProcess执行完成");

        }
    }


}
