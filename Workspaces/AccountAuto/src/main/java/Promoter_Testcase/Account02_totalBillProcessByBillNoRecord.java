package Promoter_Testcase;

import Accounting_entity.TotalBillEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.TotalBillDaemonFacade;
import com.miitang.facade.accounting.enumtype.BillStatusEnum;
import com.miitang.facade.accounting.enumtype.FeePeriodEnum;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Account02_totalBillProcessByBillNoRecord {
    static String envFlag = CommPro.env;
    static String envuri = CommPro.getEnvURI(envFlag);
    static TotalBillDaemonFacade totalBillDaemonFacade;

    static {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


        try {
            totalBillDaemonFacade = (TotalBillDaemonFacade) hessianProxyFactory.create(TotalBillDaemonFacade.class, CommPro.getEnvURI(envFlag) + "/mt-accounting-hessian/hessian/TotalBillDaemonFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 生成推广人总账单
     */

//@Test()
//    public void totalBillProcessTask(){
//    System.out.println(envuri);
//    totalBillDaemonFacade.totalBillProcess(null,null,null,null,null,"10427813356",null);
//
//    System.out.println("totalBillToPayRecordTask执行完成");
//}
    @Test()
    public void testTotalBillProcessByBillNoRecord() throws IOException, ParseException {
        //输出当前接口地址
        System.out.println(envuri);

        //获取账单周期
        int periodDate = BusinessUtil.selectPMPeriodRuleByBillType("1042781335", "PROMOTER_BILL");
        //算出账单周期开始时间
        String billStartDate = BusinessUtil.getBillStartDate("1042781335", "PROMOTER_BILL");

        //定义账单号结束时间
        Date billNoEndDate = null;

        System.out.println("输出账单开始时间"+billStartDate);
        //查询是否已生成过账单
        Date startD = null;

        TotalBillEntity totalBill = null;

        String startDate = null;

        //本期账单号

        String billNo=BusinessUtil.selectTotalBillNoByDate("1042781335",billStartDate);

//        try {
//            List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, "PROMOTER_BILL");
//
//            System.out.println("推广人账单list大小" + totalBillEntities.size());
//
//            if (totalBillEntities.size() > 0) {
//
//                totalBill = totalBillEntities.get(0);
//
//                startD = totalBill.getStartDate();
//
//                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//
//                startDate = f.format(startD);
//
//                System.out.println("startDate" + startDate);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//        }


        if (startDate == null) {
            //获取当前运营商配置推广人账单类型的账单周期
            FeePeriodEnum feePeriodEnum = BusinessUtil.getFeePeriod("1042781335", "PROMOTER_BILL");

            //根据账单号截止时间和当前时间比较,判断是否满足生成汇总账单
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            System.out.println("输出账单截至时间" + BusinessUtil.getBillEndDate("1042781335", "PROMOTER_BILL"));
            billNoEndDate = sdf.parse(BusinessUtil.getBillEndDate("1042781335", "PROMOTER_BILL"));
            if (date.getTime() < billNoEndDate.getTime()) {
                List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, "PROMOTER_BILL");
                Assert.assertTrue(totalBillEntities.size() == 0);
                System.out.println("未到时间,不可生成汇总账单");

            } else if(billNo!=null){

                List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, "PROMOTER_BILL");

                if(totalBillEntities.size()==0){


                System.out.println("分之一执行生成账单定时");

                totalBillDaemonFacade.totalBillProcessByBillNoRecord(100, 1, 100, null, null, null, null);
                    List<TotalBillEntity> totalBillEntityList=BusinessUtil.selectTotalBillByType("1042781335",billStartDate,"PROMOTER_BILL");

                    if(totalBillEntityList.size()>0){
                        BillStatusEnum billStatus=totalBillEntityList.get(0).getBillStatus();
                        Assert.assertTrue(billStatus.equals(BillStatusEnum.INIT));
                    }

            }else {

                System.out.println("本期内推广人无交易");

                Assert.assertTrue(billNo==null);

            }
        } else if (startDate.equals(billStartDate)) {

            System.out.println("本月账单已存在,无需再次生成");

        }


    }


}}


