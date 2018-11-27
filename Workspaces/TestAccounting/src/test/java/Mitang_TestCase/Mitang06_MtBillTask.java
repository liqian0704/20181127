package Mitang_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.MtBillDaemonFacade;
import com.miitang.facade.accounting.dto.MtFeeFormulaCreateReqDTO;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/*** @param withinHours
// * @param minutesAgo
// * @param limit
// * @param mode
// * @param slice
// * @param parentMerchantNos
// * @param subMerchantNos
 * Created by yp-tc-2646 on 18/3/5.
 */
public class Mitang06_MtBillTask {
    static String envFlag=CommPro.env;
    static String envuri=CommPro.getEnvURI(envFlag);
    static MtBillDaemonFacade mtBillTaskFacade = null;
    static {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();

        MtFeeFormulaCreateReqDTO reqDTO=new MtFeeFormulaCreateReqDTO();
        try {
            mtBillTaskFacade= (MtBillDaemonFacade)hessianProxyFactory.create(MtBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/MtBillDaemonFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

@Test
    public void mtBillTask(){
    System.out.println(envuri);

        boolean respose=mtBillTaskFacade.mBillProcess(100,1,null,null,null,null,null);
        System.out.println(respose);
}
public void delBillTask(){


}

}
