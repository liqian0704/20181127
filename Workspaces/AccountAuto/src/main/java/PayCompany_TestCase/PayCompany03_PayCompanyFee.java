package PayCompany_TestCase;

import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.common.enums.FeeTypeEnum;
import com.miitang.facade.accounting.dto.PayCompanyFeeFormulaCreateReqDTO;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import com.miitang.facade.accounting.facade.PayCompanyFeeFormulaFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PayCompany03_PayCompanyFee {
   static String envFlag= CommPro.env;
   static String envuri= CommPro.getEnvURI(envFlag);
   static PayCompanyFeeFormulaFacade payCompanyFeeFormulaFacade;
    static PayCompanyFeeFormulaCreateReqDTO reqDTO=new PayCompanyFeeFormulaCreateReqDTO();
   static {
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


       try {
           payCompanyFeeFormulaFacade= (PayCompanyFeeFormulaFacade) hessianProxyFactory.create(PayCompanyFeeFormulaFacade.class, CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/PayCompanyFeeFormulaFacade");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }
    /**
     * Created by yp-tc-2646 on 18/3/5.
     * 增加支付公司计费模版
     */

@Test
    public void addPayCompanyFee(){
    System.out.println(envuri);
    reqDTO.setBizSystemNo("MERt5wqVK0z");
    reqDTO.setParentMerchantNo("1032881315");
    reqDTO.setEffectiveStartDate(new Date());
    try {
        reqDTO.setEffectiveEndDate(new SimpleDateFormat("yyyyy-MM-dd").parse("2059-02-01"));
    } catch (ParseException e) {
        e.printStackTrace();
    }
    reqDTO.setFeeType(FeeTypeEnum.FIXED_RATE);
    reqDTO.setFeeRate(BigDecimal.valueOf(0.0036));
    //reqDTO.setMaxAmount(BigDecimal.valueOf(100));
    //reqDTO.setMinAmount(BigDecimal.valueOf(0.01));
    reqDTO.setPayCompany("YEEPAY");
    reqDTO.setPayProduct(PayProductEnum.WTJS.toString());//RJT//DIRECT_POINTS//DIRECT_NO_POINTS
    Object respose=payCompanyFeeFormulaFacade.createFeeFormula(reqDTO);
    System.out.println(JSONUtils.toJsonString(respose));
}

    /**
     * 删除支付公司费率模版
     */
    @Test
    public void delPayCompanyFee(){
        boolean result=payCompanyFeeFormulaFacade.deleteFeeFormula(3L);
        System.out.println(result);
    }
}
