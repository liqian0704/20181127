package Promoter_Testcase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.TotalBillDaemonFacade;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;


public class Account04_totalBillToPayRecordTask {
   static String envFlag=CommPro.env;
   static String envuri=CommPro.getEnvURI(envFlag);
   static TotalBillDaemonFacade totalBillDaemonFacade;
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


       try {
           totalBillDaemonFacade= (TotalBillDaemonFacade) hessianProxyFactory.create(TotalBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/TotalBillDaemonFacade");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }
    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 打款
     */

@Test()
    public void totalBillToPayRecordTask(){
    System.out.println(envuri);
    totalBillDaemonFacade.totalBillToPayRecord(null,null,null,null,null,"1042781335",null);

    System.out.println("totalBillToPayRecordTask执行完成");
}

    @Test()
    public void testTotalBillProcessByBillNoRecord( ) throws IOException, ParseException {
        System.out.println(envuri);
        //获取当前账期的账单号
        String billNo= BusinessUtil.billNo(CommPro.testParentMerchantNo,CommPro.promoterBill);
        //根据账单号查询推广人是否已计费
        totalBillDaemonFacade.totalBillToPayRecord(null,null,100,null,null,null,null);

        System.out.println("totalBillToPayRecordTask执行完成");
    }

}
