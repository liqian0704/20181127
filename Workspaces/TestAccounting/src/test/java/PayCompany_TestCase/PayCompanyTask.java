package PayCompany_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.daemon.PayCompanyBillDaemonFacade;
import com.miitang.facade.accounting.dto.MtFeeFormulaCreateReqDTO;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by yp-tc-2646 on 18/3/5.
 * 支付公司账单定时
 *
 */
public class PayCompanyTask {
    String envFlag=CommPro.env;
    String envuri=CommPro.getEnvURI(envFlag);
@Test
    public void addPayCompanyFee(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        PayCompanyBillDaemonFacade payCompanyBillTaskFacade = null;
        MtFeeFormulaCreateReqDTO reqDTO=new MtFeeFormulaCreateReqDTO();
        System.out.println(envuri);
        try {
            payCompanyBillTaskFacade= (PayCompanyBillDaemonFacade) hessianProxyFactory.create(PayCompanyBillDaemonFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/PayCompanyBillDaemonFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }

        Object respose=payCompanyBillTaskFacade.payCompanyBillProcess(null,null,null,null,null,"10427813356",null);
        System.out.println(respose);
    }}
