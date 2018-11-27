package Promoter_Testcase;

import Accounting_entity.ParentMerchantPeriodRuleEntity;
import Accounting_entity.PromoterPayRecordEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.PromoterBillDaemonFacade;
import com.miitang.facade.accounting.enumtype.OrderFeeStatusEnum;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

public class Account03_promoterPayRecordCallFeeTask {
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

    public Account03_promoterPayRecordCallFeeTask() throws IOException, ParseException {
    }

    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 推广人计费
     */

    @Test()
    public void promoterPayRecordCallFeeTask() throws IOException, ParseException {

        //获取本期账单号
        String billNo = BusinessUtil.billNo("1042781335", CommPro.promoterBill);
        if (billNo == null) {

            System.out.println("还没有生成账单号,无需计费");
            return;
        }
        //查询本期是否已计费
        List<PromoterPayRecordEntity> promoterPayRecordEntityList = BusinessUtil.selectPromoterPayRecordEntityByBilNo("1042781335", billNo);

        if (promoterPayRecordEntityList.size() > 0) {

            System.out.println("本期推广人订单已参与计费,无需重新计费");

        } else {

            boolean boolen = promoterBillDaemonFacade.promoterPayRecordCallFee(null, null, 100, null, null, null, null);

            List<ParentMerchantPeriodRuleEntity> list = BusinessUtil.selectPMRuleByBillType("1042781335", CommPro.payCompayBill);
            // 查询税费费率
             BigDecimal feeRate=list.get(1).getFeeRate();
            //查询出计费记录
            List<PromoterPayRecordEntity> promoterPayRecordEntityListNew = BusinessUtil.selectPromoterPayRecordEntityByBilNo("1042781335", billNo);

            for(int i=0;i<promoterPayRecordEntityListNew.size();i++){

                BigDecimal profitFee = promoterPayRecordEntityList.get(i).getProfitFee();

                BigDecimal profitAmount = promoterPayRecordEntityList.get(i).getProfitAmount();

                OrderFeeStatusEnum orderFeeStatusEnum= promoterPayRecordEntityList.get(i).getFeeStatus();

                BigDecimal expectProfitFee=profitAmount.multiply(feeRate);

                Assert.assertTrue(expectProfitFee.equals(profitFee));

                Assert.assertTrue(orderFeeStatusEnum.equals(OrderFeeStatusEnum.SUCCESS));
            }

            System.out.println("promoterPayRecordCallFee执行完成");
        }
    }}



