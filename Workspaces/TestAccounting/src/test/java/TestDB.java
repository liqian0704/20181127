import Accounting_entity.*;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/24.
 */
public class TestDB {
    @Test
    public void test01() throws IOException {
        List<AccountInfoEntity> accountInfo = (List<AccountInfoEntity>) BusinessUtil.SelectByCustomerNumber("1024327420");
        for (int i = 0; i < accountInfo.size(); i++) {
            System.out.println(accountInfo.get(i).getCustomerName());
        }

    }

    @Test
    public void test02() throws IOException {
        MtBillEntity mtBill = BusinessUtil.SelectByBillNo("ACC1010010169201804132046");
        System.out.println(JSONUtils.toJsonString(mtBill));
    }

    @Test
    public void test03() throws IOException {
        List<MtFeeFormulaEntity> mtFeeFormulaEntity = BusinessUtil.selectMtFee("1042781335");
        for (int i = 0; i < mtFeeFormulaEntity.size(); i++) {

            System.out.println(JSONUtils.toJsonString(mtFeeFormulaEntity));
        }
    }

    @Test
    public void test04() throws IOException {
        List<OrderInfoEntity> orderList = BusinessUtil.selectOrderByPMNo("1042781335");
        for (OrderInfoEntity orderInfo : orderList) {
            System.out.println(JSONUtils.toJsonString(orderInfo));
        }
    }

    @Test
    public void test05() throws IOException {
        int i = BusinessUtil.updateOrderStatus("1042781335");
        System.out.println(i);

    }

    @Test
    public void test06() throws IOException {
        List<ParentMerchantPeriodRuleEntity> parentRule = BusinessUtil.selectPMPeriodRule("1042781335");
        System.out.println(JSONUtils.toJsonString(parentRule));

    }

    @Test
    public void test07() throws IOException {
        List<PayCompanyBillEntity> payCompanyBill = BusinessUtil.selectPayCompayBill("1042781335");
        System.out.println(JSONUtils.toJsonString(payCompanyBill));
    }

    @Test
    public void test08() throws IOException {
        List<PayCompanyFeeFormulaEntity> payCompanyFeeFormula = BusinessUtil.selectPayComFeeFormular("1042781335");
        System.out.println(JSONUtils.toJsonString(payCompanyFeeFormula));
    }

    @Test
    public void test09() throws IOException {
        List<PayCompanyOrderFeeRecordEntity> payCompanyOrderFeeRecord = BusinessUtil.selectpayCompanyOrderFeeRecord("1");
        System.out.println(JSONUtils.toJsonString(payCompanyOrderFeeRecord));
    }

    @Test
    public void test10() throws IOException {
        List<PayComOtherFeeRecordEntity> PayComOtherFeeRecord = BusinessUtil.selectPayComOtherFeeRecord("1042781335");
        System.out.println(JSONUtils.toJsonString(PayComOtherFeeRecord));
    }

    @Test
    public void test11() throws IOException {
        List<PayRecordEntity> payRecordEntities = BusinessUtil.selectPayRecord("1042781335");
        System.out.println(JSONUtils.toJsonString(payRecordEntities));
    }

    @Test
    public void test12() throws IOException {
        List<PromoterPayRecordEntity> promoterPayRecordEntities = BusinessUtil.selectPromoterPayRecordEntity("1042781335");
        System.out.println(JSONUtils.toJsonString(promoterPayRecordEntities));
    }

    @Test
    public void test13() throws IOException {
        List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillEntity("1042781335");
        System.out.println(JSONUtils.toJsonString(totalBillEntities));
    }

    @Test
    public void test14() throws IOException {
        List<TotalBillNoEntity> totalBillNoEntities = BusinessUtil.selectTotalBillNoEntity("1042781335");
        System.out.println(JSONUtils.toJsonString(totalBillNoEntities));
    }

    @Test
    public void test15() throws IOException {
        List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", "2018-06-01 00:00:00", "SALES_FEE_BILL");
        System.out.println(totalBillEntities.size());
    }

    @Test
    public void test16() throws IOException {
        int i = BusinessUtil.selectPMPeriodRuleByBillType("1042781335", "SALES_FEE_BILL");
        System.out.println(i);
    }
    @Test
    public void test17() throws IOException, ParseException {
       String billNo= BusinessUtil.billNo("1042781335","PROMOTER_BILL");

        System.out.println(billNo);
    }
    @Test
    public void test18() throws IOException {

       List<OrderInfoEntity> orderInfoEntities =BusinessUtil.selectOrderByPMNoandPeriod("1042781335","2018-06-01 00:00:00","2018-09-01 00:00:00");

        System.out.println(orderInfoEntities.size());
      //  http://gitlab.yeepay.com/mt/mt-autotest.git

    }
    @Test
    public void test19() throws IOException {
        List<PayCompanyOrderFeeRecordEntity> payCompanyOrderFeeRecordEntityList=BusinessUtil.selectPayComOrderFeeRecordByDate("2018-07-24 00:00:00","2018-07-25 00:00:00");
        System.out.println(payCompanyOrderFeeRecordEntityList.size());
    }
    @Test
    public void test20() throws IOException {

        PayCompanyFeeFormulaEntity payCompanyFeeFormulaEntity= BusinessUtil.selectPayComOrderFeeFormularByPro("1042781335", PayProductEnum.DIRECT_POINTS);
        System.out.println(payCompanyFeeFormulaEntity.getFeeRate());
    }
    @Test
    public void test21() throws IOException {
        List<PayComOtherFeeRecordEntity> list =BusinessUtil.selectPayComOtherFeeRecordByDate("1042781335","2018-07-24 00:00:00","2018-08-25 00:00:00");
        System.out.println(list.size());
    }
    @Test
    public void test22() throws IOException {
        PayCompanyBillEntity payCompanyBillEntity= BusinessUtil.selectPayCompayBillByDate("1042781335","2018-07-24 00:00:00","2018-08-25 00:00:00");

        if(payCompanyBillEntity!=null){
            int tradeNum=payCompanyBillEntity.getTradeNum()==null?0:payCompanyBillEntity.getTradeNum();
            System.out.println(tradeNum);
        }
    }
    @Test
    public  void test23() throws IOException {
        PayCompanyBillEntity payCompanyBillEntity=BusinessUtil.selectPayCompayBillByDateAndCostType("1042781335",PayProductEnum.DIRECT_POINTS,"2018-08-01 00:00:00","2018-09-01 00:00:00");

        if(payCompanyBillEntity!=null){
            System.out.println(payCompanyBillEntity.getTradeNum());
        }

    }
    @Test
    public void test(){
        System.out.println(Integer.toString(1));
    }
    public void test00(){

    }

}