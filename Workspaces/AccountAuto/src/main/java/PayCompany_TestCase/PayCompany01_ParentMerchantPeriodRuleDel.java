package PayCompany_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.facade.ParentMerchantPeriodRuleFacade;
import org.testng.annotations.Test;

/**
 * Created by yp-tc-2646 on 17/12/18.
 * 运营商结算周期设置
 */
public class PayCompany01_ParentMerchantPeriodRuleDel {


        String envFlag=CommPro.env;
        String envuri=CommPro.getEnvURI(envFlag);


   @Test()
    public void testdeleteParentMerchantPeriodRule(){//qa 10427813356
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;
        try {
            System.out.println(envuri);
            parentMerchantPeriodRuleFacade= (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
            boolean result1 = parentMerchantPeriodRuleFacade.deleteParentMerchantPeriodRule("17");
            boolean result2 = parentMerchantPeriodRuleFacade.deleteParentMerchantPeriodRule("18");
            System.out.println(result1 + "|" + result2);
        } catch (Throwable e) {
            e.printStackTrace();
        }

   }
}
