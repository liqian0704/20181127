package utility;

import Accounting_core_mapper.*;
import Accounting_entity.*;
import com.miitang.facade.accounting.enumtype.FeePeriodEnum;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 返回注册手机号验证码
 * Created by yp-tc-2646 on 17/11/7.
 */
public class BusinessUtil {

    public static List<AccountInfoEntity> SelectByCustomerNumber(String customerNo) throws IOException {
//        ConfigurationUtils.init();
        SqlSession session = MybatisUtils.openSession();
        AccountInfoMapper accountInfoDao = session.getMapper(AccountInfoMapper.class);
        List<AccountInfoEntity> list = accountInfoDao.accountInfo(customerNo);
        return list;

    }

    public static MtBillEntity SelectByBillNo(String billNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        MtBillMapper mtBillMapper = session.getMapper(MtBillMapper.class);
        MtBillEntity mtBill = (MtBillEntity) mtBillMapper.selectMtBill(billNo);
        return mtBill;

    }

    public static String selectTotalBillNoByDate(String parentMerchantNo, String billStartDate) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        TotalBillNoMapper totalBillNoMapper = session.getMapper(TotalBillNoMapper.class);
        String billNo = totalBillNoMapper.selectTotalBillNoByDate(parentMerchantNo, billStartDate);
        return billNo;

    }

    public static List<MtFeeFormulaEntity> selectMtFee(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        MtFeeFormulaMapper mtFeeMapper = session.getMapper(MtFeeFormulaMapper.class);
        List<MtFeeFormulaEntity> mtFeeFormular = mtFeeMapper.selectMtFee(parentMerchantNo);
        return mtFeeFormular;

    }

    public static List<OrderInfoEntity> selectOrderByPMNo(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        OrderInfoMapper orderInfoMapper = session.getMapper(OrderInfoMapper.class);
        List<OrderInfoEntity> orderInfoList = orderInfoMapper.selectOrderByPMNo(parentMerchantNo);
        return orderInfoList;
    }

    public static int updateOrderStatus(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        OrderInfoMapper orderInfoMapper = session.getMapper(OrderInfoMapper.class);
        int i = orderInfoMapper.updateOrderStatus(parentMerchantNo);
        return i;
    }

    public static List<ParentMerchantPeriodRuleEntity> selectPMPeriodRule(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        ParentMerchantPeriodMapper parentMerchantPeriodMapper = session.getMapper(ParentMerchantPeriodMapper.class);
        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRule = parentMerchantPeriodMapper.selectPMPeriodRule(parentMerchantNo);
        return parentMerchantPeriodRule;

    }

    public static int selectPMPeriodRuleByBillType(String parentMerchantNo, String bilType) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        ParentMerchantPeriodMapper parentMerchantPeriodMapper = session.getMapper(ParentMerchantPeriodMapper.class);
        int period = parentMerchantPeriodMapper.selectPMPeriodRuleByBillType(parentMerchantNo, bilType);
        return period;

    }

    public static List<ParentMerchantPeriodRuleEntity> selectPMRuleByBillType(String parentMerchantNo, String billType) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        ParentMerchantPeriodMapper parentMerchantPeriodMapper = session.getMapper(ParentMerchantPeriodMapper.class);
        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRule = parentMerchantPeriodMapper.selectPMRuleByBillType(parentMerchantNo, billType);
        return parentMerchantPeriodRule;

    }

    public static List<PayCompanyBillEntity> selectPayCompayBill(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayCompanyBillMapper payCompanyBillMapper = session.getMapper(PayCompanyBillMapper.class);
        List<PayCompanyBillEntity> payCompayBill = payCompanyBillMapper.selectPayCompayBill(parentMerchantNo);
        return payCompayBill;

    }

    public static List<PayCompanyFeeFormulaEntity> selectPayComFeeFormular(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayCompanyFeeFormulaMapper payCompanyFeeFormulaMapper = session.getMapper(PayCompanyFeeFormulaMapper.class);
        List<PayCompanyFeeFormulaEntity> payCompanyFeeFormula = payCompanyFeeFormulaMapper.selectPayComFeeFormular(parentMerchantNo);
        return payCompanyFeeFormula;

    }

    public static List<PayCompanyOrderFeeRecordEntity> selectpayCompanyOrderFeeRecord(String unique_order_no) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayCompanyOrderFeeRecordMapper payCompanyOrderFeeRecordMapper = session.getMapper(PayCompanyOrderFeeRecordMapper.class);
        List<PayCompanyOrderFeeRecordEntity> payCompanyOrderFeeRecord = payCompanyOrderFeeRecordMapper.selectPayComOrderFeeRecord(unique_order_no);

        return payCompanyOrderFeeRecord;
    }

    public static List<PayComOtherFeeRecordEntity> selectPayComOtherFeeRecord(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayCompanyOtherFeeRecordMapper payCompanyOtherFeeRecordMapper = session.getMapper(PayCompanyOtherFeeRecordMapper.class);
        List<PayComOtherFeeRecordEntity> payComOtherFeeRecord = payCompanyOtherFeeRecordMapper.selectPayComOtherFeeRecord(parentMerchantNo);

        return payComOtherFeeRecord;

    }


    public static List<PayRecordEntity> selectPayRecord(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayRecordMapper payRecordMapper = session.getMapper(PayRecordMapper.class);
        List<PayRecordEntity> payComOtherFeeRecord = payRecordMapper.selectPayRecord(parentMerchantNo);

        return payComOtherFeeRecord;
    }

    public static List<PromoterPayRecordEntity> selectPromoterPayRecordEntity(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PromoterPayRecordMapper promoterPayRecordMapper = session.getMapper(PromoterPayRecordMapper.class);
        List<PromoterPayRecordEntity> promoterPayRecord = promoterPayRecordMapper.selectPromoterPayRecord(parentMerchantNo);

        return promoterPayRecord;
    }

    public static List<PromoterPayRecordEntity> selectPromoterPayRecordEntityByBilNo(String parentMerchantNo, String bilNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PromoterPayRecordMapper promoterPayRecordMapper = session.getMapper(PromoterPayRecordMapper.class);
        List<PromoterPayRecordEntity> promoterPayRecord = promoterPayRecordMapper.selectPromoterPayRecordbyBillNo(parentMerchantNo, bilNo);

        return promoterPayRecord;
    }

    public static List<TotalBillEntity> selectTotalBillEntity(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        TotalBillMapper totalBillMapper = session.getMapper(TotalBillMapper.class);
        List<TotalBillEntity> totalBillEntities = totalBillMapper.selectTotalBill(parentMerchantNo);

        return totalBillEntities;
    }

    public static List<TotalBillEntity> selectTotalBillByType(String parentMerchantNo, String startDate, String billType) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        TotalBillMapper totalBillMapper = session.getMapper(TotalBillMapper.class);
        List<TotalBillEntity> totalBillEntities = totalBillMapper.selectTotalBillByType(parentMerchantNo, startDate, billType);
        return totalBillEntities;


    }

    public static List<TotalBillNoEntity> selectTotalBillNoEntity(String parentMerchantNo) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        TotalBillNoMapper totalBillNoMapper = session.getMapper(TotalBillNoMapper.class);
        List<TotalBillNoEntity> totalBillNoEntities = totalBillNoMapper.selectTotalBillNoEntity(parentMerchantNo);

        return totalBillNoEntities;
    }

    public static FeePeriodEnum getFeePeriod(String parentMerchantNo, String billType) throws IOException {
        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRuleEntityList = BusinessUtil.selectPMPeriodRule(parentMerchantNo);
        for (int i = 0; i < parentMerchantPeriodRuleEntityList.size(); i++) {
            if (parentMerchantPeriodRuleEntityList.get(i).getBillType().equals(billType)) {
                return parentMerchantPeriodRuleEntityList.get(i).getFeePeriod();
            }
        }


        return null;
    }

    public static List<OrderInfoEntity> selectOrderByPMNoandPeriod(String parentMerchantNo, String billStartTime, String biilEndTime) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        OrderInfoMapper orderInfoMapper = session.getMapper(OrderInfoMapper.class);
        List<OrderInfoEntity> orderInfoEntityList = orderInfoMapper.selectOrderByPMNoandPeriod(parentMerchantNo, billStartTime, biilEndTime);
        return orderInfoEntityList;

    }

    public static List<PayCompanyOrderFeeRecordEntity> selectPayComOrderFeeRecordByDate(String billStartTime, String billEndTime) throws IOException {
        SqlSession session = MybatisUtils.openSession();

        PayCompanyOrderFeeRecordMapper payCompanyOrderFeeRecordMapper = session.getMapper(PayCompanyOrderFeeRecordMapper.class);

        List<PayCompanyOrderFeeRecordEntity> payCompanyOrderFeeRecordEntityList = payCompanyOrderFeeRecordMapper.selectPayComOrderFeeRecordByDate(billStartTime, billEndTime);

        return payCompanyOrderFeeRecordEntityList;

    }

    public static PayCompanyFeeFormulaEntity selectPayComOrderFeeFormularByPro(String parentMerchantNo, PayProductEnum payProduct) throws IOException {

        SqlSession session = MybatisUtils.openSession();

        PayCompanyFeeFormulaMapper payCompanyFeeFormulaMapper = session.getMapper(PayCompanyFeeFormulaMapper.class);

        PayCompanyFeeFormulaEntity payCompanyFeeFormulaEntity = payCompanyFeeFormulaMapper.selectPayComOrderFeeForByPro(parentMerchantNo, payProduct);

        return payCompanyFeeFormulaEntity;

    }

    public static List<PayComOtherFeeRecordEntity> selectPayComOtherFeeRecordByDate(String parentMerchantNo, String billStartTime, String billEndTIime) throws IOException {

        SqlSession session = MybatisUtils.openSession();

        PayCompanyOtherFeeRecordMapper payCompanyOtherFeeRecordMapper = session.getMapper(PayCompanyOtherFeeRecordMapper.class);

        List<PayComOtherFeeRecordEntity> payComOtherFeeRecordEntityList = payCompanyOtherFeeRecordMapper.selectPayComOtherFeeRecordByDate(parentMerchantNo, billStartTime, billEndTIime);

        return payComOtherFeeRecordEntityList;


    }

    public static PayCompanyBillEntity selectPayCompayBillByDate(String parentMerchantNo, String billStartTime, String billEndTime) throws IOException {
        SqlSession session = MybatisUtils.openSession();
        PayCompanyBillMapper payCompanyBillMapper = session.getMapper(PayCompanyBillMapper.class);
        PayCompanyBillEntity payCompanyBillEntity = payCompanyBillMapper.selectPayCompayBillByDate(parentMerchantNo, billStartTime, billEndTime);
        return payCompanyBillEntity;


    }

    public static PayCompanyFeeFormulaEntity selectPayComOrderFeeForByPro(String parentMerchantNo, PayProductEnum payProductEnum) throws IOException {
        SqlSession session = MybatisUtils.openSession();

        PayCompanyFeeFormulaMapper payCompanyFeeFormulaMapper = session.getMapper(PayCompanyFeeFormulaMapper.class);

        PayCompanyFeeFormulaEntity payCompanyFeeFormulaEntity = payCompanyFeeFormulaMapper.selectPayComOrderFeeForByPro(parentMerchantNo, payProductEnum);

        return payCompanyFeeFormulaEntity;

    }

    public static PayCompanyBillEntity selectPayCompayBillByDateAndCostType(String parentMerchantNo, PayProductEnum costType, String billStartTime, String billEndTime) throws IOException {

        SqlSession session = MybatisUtils.openSession();

        PayCompanyBillMapper payCompanyBillMapper = session.getMapper(PayCompanyBillMapper.class);

        PayCompanyBillEntity payCompanyBillEntity = payCompanyBillMapper.selectPayCompayBillByDateAndCostType(parentMerchantNo, costType, billStartTime, billEndTime);

        return payCompanyBillEntity;
    }

    public static String getDate() throws IOException {
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        //System.out.println(sdf.format(date));
        return sdf.format(date);

    }

    /**
     * 日期比较函数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(String date1, String date2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = sdf.parse(date1);
            Date dt2 = sdf.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("date1在 date2之前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("date1在date2之后");
                return -1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 存储账单类型及账单日
     */

    public static HashMap storeBill() {
        HashMap<String, String> billMap = new HashMap();
        //查询当前运营商的账单周期规则
        try {
            List<ParentMerchantPeriodRuleEntity> list = BusinessUtil.selectPMPeriodRule("1042781335");

            for (int i = 0; i < list.size(); i++) {

                billMap.put(list.get(i).getBillType().toString(), list.get(i).getPeriodTime());
                //System.out.println("账单类型为:"+list.get(i).getBillType()+"--"+"账单周期:"+list.get(i).getFeePeriod()+"--"+"账单日:"+list.get(i).getPeriodTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行当前接口
        // totalBillDaemonFacade.totalBillProcessByBillNoRecord(null,null,100,null,null,null,null);
        //输出保存的账单类型结果
        //System.out.println(billMap);

        return billMap;
    }

    public static int veryIfCreateMTBill() {
        int date = Calendar.DATE;

        Map billMap = BusinessUtil.storeBill();

        int mtBilldate = Integer.parseInt(BusinessUtil.storeBill().get("SALES_FEE_BILL").toString());

        if (date > mtBilldate) {

            return 1;

        } else {

            return 0;
        }
    }

    public static void printDATE() {
        Calendar cal = Calendar.getInstance();

        int year, month, date, hour, minute, second;
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DATE);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
        System.out.println("YEAR=" + year + "---" + "MONTH=" + month + "---" + "DATE=" + date);
    }

    public static String getCDate(String parentMerchantNo, String billType) throws IOException, ParseException {

        int period = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);

        // System.out.println(period);

        if (period < 10) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-0" + period + " 00:00:00");
            Calendar currentDate = Calendar.getInstance();

            currentDate.add(Calendar.MONTH, -1);
            return sdf.format(currentDate.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-" + period + " 00:00:00");
            Calendar currentDate = Calendar.getInstance();

            currentDate.add(Calendar.MONTH, -1);
            return sdf.format(currentDate.getTime());
        }
    }

    public static String getNDate(String parentMerchantNo, String billType) throws IOException, ParseException {

        int period = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);
        String date = null;

        // System.out.println(period);

        if (period < 10) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-0" + period + " 00:00:00");
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(currentDate.MONTH, 0);

            date = sdf.format(currentDate.getTime());

            return date;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-" + period + " 00:00:00");
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(currentDate.MONTH, 0);
            date = sdf.format(currentDate.getTime());

            return date;
        }
    }

    /**
     * 获取账单结算周期类型,并根据周期类型返回对应的账单开始时间
     */
    public static String getBillStartDate(String parentMerchantNo, String billType) throws IOException {

        int period = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);

        String billStartTime = "aaa";

        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRuleList = BusinessUtil.selectPMRuleByBillType(parentMerchantNo, billType);
        int periodtime = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);
        if (parentMerchantPeriodRuleList.size() > 0) {
            FeePeriodEnum feePeriodEnum = parentMerchantPeriodRuleList.get(0).getFeePeriod();
            Calendar currentDate = Calendar.getInstance();
            if (feePeriodEnum.equals(FeePeriodEnum.MONTH)) {


                if (periodtime < 10) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-0" + periodtime + " 00:00:00");

                    currentDate.add(currentDate.MONTH, -1);

                    billStartTime = sdf.format(currentDate.getTime());


                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-" + periodtime + " 00:00:00");

                    currentDate.add(currentDate.MONTH, -1);

                    billStartTime = sdf.format(currentDate.getTime());

                }
            } else if (feePeriodEnum.equals(FeePeriodEnum.DAY)) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

                currentDate.add(currentDate.DATE, -1);

                billStartTime = sdf.format(currentDate.getTime());


            }
        }
        return billStartTime;

    }

    public static String getBillEndDate(String parentMerchantNo, String billType) throws IOException {

        int period = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);

        String billEndTime = null;

        List<ParentMerchantPeriodRuleEntity> parentMerchantPeriodRuleList = BusinessUtil.selectPMRuleByBillType(parentMerchantNo, billType);
        int periodtime = BusinessUtil.selectPMPeriodRuleByBillType(parentMerchantNo, billType);
        if (parentMerchantPeriodRuleList.size() > 0) {
            FeePeriodEnum feePeriodEnum = parentMerchantPeriodRuleList.get(0).getFeePeriod();
            Calendar currentDate = Calendar.getInstance();
            if (feePeriodEnum.equals(FeePeriodEnum.MONTH)) {

                String date = null;

                if (periodtime < 10) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-0" + periodtime + " 00:00:00");

                    currentDate.add(currentDate.MONTH, 0);

                    billEndTime = sdf.format(currentDate.getTime());

                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-" + periodtime + " 00:00:00");

                    currentDate.add(currentDate.MONTH, 0);
                    billEndTime = sdf.format(currentDate.getTime());

                }
            } else if (feePeriodEnum.equals(FeePeriodEnum.DAY)) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

                currentDate.add(currentDate.DATE, 0);

                billEndTime = sdf.format(currentDate.getTime());

            }
        }
        return billEndTime;

    }

    /**
     * 查询是否已生成账单
     */
    public static boolean hasBill(String parentMerchantNo, String billType, String currentDate) throws IOException, ParseException {
        //算出账单周期开始时间
        String billStartDate = BusinessUtil.getCDate(parentMerchantNo, billType);

        List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType(parentMerchantNo, billStartDate, billType);

        if (totalBillEntities.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static String billNo(String parentMerchantNo, String billType) throws IOException, ParseException {
        String totalBillNo = null;
        //算出账单周期开始时间
        String billStartDate = BusinessUtil.getCDate(parentMerchantNo, billType);

        List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType(parentMerchantNo, billStartDate, billType);

        if (totalBillEntities.size() > 0) {

            totalBillNo = totalBillEntities.get(0).getTotalBillNo();

        }

        return totalBillNo;


    }
}
