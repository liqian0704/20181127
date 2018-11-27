package Mitang_TestCase;

import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.TotalBillDaemonFacade;
import com.miitang.facade.accounting.dto.PayCompanyFeeFormulaCreateReqDTO;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by yp-tc-2646 on 18/3/5.
 */
public class Mitang04_TotalBillTask {
    String envFlag=CommPro.env;
    String envuri=CommPro.getEnvURI(envFlag);
@Test
    public void TotalBill(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        TotalBillDaemonFacade totalBillDaemonFacade;
        PayCompanyFeeFormulaCreateReqDTO reqDTO=new PayCompanyFeeFormulaCreateReqDTO();
        try {
            System.out.println(envuri);
            totalBillDaemonFacade= (TotalBillDaemonFacade) hessianProxyFactory.create(TotalBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/TotalBillDaemonFacade");


            boolean result=totalBillDaemonFacade.totalBillProcess(null, null, 100, 1, 0, null, null);
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
}}
