package Promoter_Testcase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.dto.TotalBillModifyReqDTO;
import com.miitang.facade.accounting.dto.TotalBillModifyResDTO;
import com.miitang.facade.accounting.facade.TotalBillFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class Account05_affirmByOperatorTask {
   static String envFlag=CommPro.env;
   static String envuri=CommPro.getEnvURI(envFlag);
   static TotalBillFacade totalBillFacade;
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


       try {
           totalBillFacade= (TotalBillFacade) hessianProxyFactory.create(TotalBillFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/TotalBillFacade");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }
    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 增加支付公司计费模版
     */

@Test(dataProvider = "dp")
    public void queryPayComOtherFeeRecord(String billNo, String message){
    System.out.println(envuri);
    TotalBillModifyReqDTO reqDTO=new TotalBillModifyReqDTO();
    reqDTO.setOperator("lala");
    reqDTO.setBizSystemNo("mitanglala");
    reqDTO.setParentMerchantNo("1042781335");
    reqDTO.setBillNo(billNo);
    TotalBillModifyResDTO resDTO= totalBillFacade.affirmByOperator(reqDTO);

    System.out.println(JSONUtils.toJsonString(resDTO));}
    @DataProvider(name="dp")
    public Object[][] dp(){
        return  new Object[][] {


               // {"ACC1010010169201804117272","打款确认"}
                {"AV100404082018053100000000005765","打款确认"},
                {"AV100404082018053100000000005767","打款确认"}


    };
    }

}
