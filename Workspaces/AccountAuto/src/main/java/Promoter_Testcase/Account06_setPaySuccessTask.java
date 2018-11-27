package Promoter_Testcase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.dto.PayRecordModifyReqDTO;
import com.miitang.facade.accounting.dto.PayRecordModifyResDTO;
import com.miitang.facade.accounting.facade.PayRecordFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class Account06_setPaySuccessTask {
   static String envFlag=CommPro.env;
   static String envuri=CommPro.getEnvURI(envFlag);
   static PayRecordFacade payRecordFacade;
    PayRecordModifyReqDTO recordModifyReqDTO;
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();



       try {
           payRecordFacade= (PayRecordFacade) hessianProxyFactory.create(PayRecordFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/PayRecordFacade");
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
    public void setPaySuccessTask(String bizSystemNo, String description, String operator, String uniquePayNo, String verify){
    System.out.println(envuri);
    recordModifyReqDTO=new PayRecordModifyReqDTO();
    recordModifyReqDTO.setBizSystemNo(bizSystemNo);
    recordModifyReqDTO.setDescription(description);
    recordModifyReqDTO.setOperator(operator);
    recordModifyReqDTO.setUniquePayNo(uniquePayNo);
    PayRecordModifyResDTO recordModifyResDTO=payRecordFacade.setPaySuccess(recordModifyReqDTO);

    System.out.println(JSONUtils.toJsonString(recordModifyResDTO));
}
    @DataProvider(name = "dp")
    public Object[][] dp() {
        return new Object[][]{

                {"TRADE","描述","lala","AV100404082018051100000000004956","按照tbl_total_bill的TOTAL_BILL_NO打款"},





                //{"1042781335","TRADE",BillTypeEnum.PROMOTER_BILL,PayCompanyCode.YEEPAY,new Date(118, 4, 17),"按批次号查询"},



        };
    }


}
