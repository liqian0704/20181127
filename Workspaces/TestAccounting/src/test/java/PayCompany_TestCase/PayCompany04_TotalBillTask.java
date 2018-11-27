package PayCompany_TestCase;

import Accounting_entity.TotalBillEntity;
import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.TotalBillDaemonFacade;
import com.miitang.facade.accounting.dto.PayCompanyFeeFormulaCreateReqDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BusinessUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yp-tc-2646 on 18/3/5.
 */
public class PayCompany04_TotalBillTask {
    String envFlag=CommPro.env;
    String envuri=CommPro.getEnvURI(envFlag);
@Test
    public void TotalBill() throws IOException, ParseException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        TotalBillDaemonFacade totalBillDaemonFacade;
        PayCompanyFeeFormulaCreateReqDTO reqDTO=new PayCompanyFeeFormulaCreateReqDTO();
    String billStartDate=BusinessUtil.getCDate("1042781335",CommPro.payCompayBill);
    //定义账单号结束时间
    Date billNoEndDate = null;

    //查询当前时间是否可以生成账单
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    System.out.println("输出账单截至时间" + BusinessUtil.getNDate("1042781335", CommPro.payCompayBill));
    billNoEndDate = sdf.parse(BusinessUtil.getNDate("1042781335", CommPro.payCompayBill));
    List<TotalBillEntity> totalBillEntities = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, CommPro.payCompayBill);
    if(date.getTime()<billNoEndDate.getTime()){

        System.out.println("未到账单日不可以生成账单");
        Assert.assertTrue(totalBillEntities.size() == 0);
    }else if(totalBillEntities.size()>0){
        //查询是否已经存在本期账单
        System.out.println("本期支付手续费账单已经存在,无需再次生成");

    }else {

        try {
            System.out.println(envuri);
            totalBillDaemonFacade= (TotalBillDaemonFacade) hessianProxyFactory.create(TotalBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/TotalBillDaemonFacade");
            boolean result=totalBillDaemonFacade.totalBillProcess(null, null, 100, 1, 0, null, null);
            List<TotalBillEntity> totalBillEntitiesnew = BusinessUtil.selectTotalBillByType("1042781335", billStartDate, CommPro.payCompayBill);
            Assert.assertTrue(totalBillEntitiesnew.size()==1);
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
}}}
