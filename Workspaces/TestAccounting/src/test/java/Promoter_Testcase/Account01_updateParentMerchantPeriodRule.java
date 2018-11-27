package Promoter_Testcase;

import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.dto.ParentMerchantPeriodRuleModifyReqDTO;
import com.miitang.facade.accounting.dto.ParentMerchantPeriodRuleModifyResDTO;
import com.miitang.facade.accounting.enumtype.StatusEnum;
import com.miitang.facade.accounting.facade.ParentMerchantPeriodRuleFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;


public class Account01_updateParentMerchantPeriodRule {
    static String envFlag = CommPro.env;
    static String envuri = CommPro.getEnvURI(envFlag);
    static ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;

    static {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();


        try {
            parentMerchantPeriodRuleFacade = (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class, CommPro.getEnvURI(envFlag) + "/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
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
    public void updateParentMerchantPeriodRule(String bizSystemNo, Boolean automatic, BigDecimal feeRate, StatusEnum status, String ruleId) {
        System.out.println(envuri);
        ParentMerchantPeriodRuleModifyReqDTO parentMerchantPeriodRuleModifyReqDTO = new ParentMerchantPeriodRuleModifyReqDTO();
        parentMerchantPeriodRuleModifyReqDTO.setBizSystemNo(bizSystemNo);
        parentMerchantPeriodRuleModifyReqDTO.setAutomatic(automatic);
        parentMerchantPeriodRuleModifyReqDTO.setFeeRate(feeRate);
        parentMerchantPeriodRuleModifyReqDTO.setStatus(status);
        parentMerchantPeriodRuleModifyReqDTO.setRuleId(ruleId);
        ParentMerchantPeriodRuleModifyResDTO resDTO = parentMerchantPeriodRuleFacade.updateParentMerchantPeriodRule(parentMerchantPeriodRuleModifyReqDTO);
        System.out.println(JSONUtils.toJsonString(resDTO));
    }

    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return new Object[][]{
                //new Object[]{"mtbiz",false,BigDecimal.valueOf(0),StatusEnum.INIT,"5"},
                //  new Object[]{"mtbiz",true,BigDecimal.valueOf(0),StatusEnum.INIT,"5"},

                //new Object[]{"mtbiz",true,BigDecimal.valueOf(3),StatusEnum.INIT,"5"},
                // new Object[]{null,null,BigDecimal.valueOf(1),StatusEnum.INIT,"0"},
//                new Object[]{"mtbiz",true,null,StatusEnum.INIT,"5"},
//                new Object[]{"mtbiz",true,BigDecimal.valueOf(0),null,"5"},
                // new Object[]{"mtbiz",true,null,StatusEnum.EFFECTIVE,"16"},
                new Object[]{"mtbiz", false, BigDecimal.valueOf(0), StatusEnum.EFFECTIVE, "20"},

        };
    }

}
