package Mitang_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.facade.accounting.dto.ParentMerchantPeriodRuleCreateReqDTO;
import com.miitang.facade.accounting.enumtype.BeneficiaryEnum;
import com.miitang.facade.accounting.enumtype.BillTypeEnum;
import com.miitang.facade.accounting.enumtype.FeePeriodEnum;
import com.miitang.facade.accounting.facade.ParentMerchantPeriodRuleFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by yp-tc-2646 on 17/12/18.
 * 运营商结算周期设置
 */
public class Mitang02_ParentMerchantPeriodRuleCreate {


        String envFlag=CommPro.env;
        String envuri=CommPro.getEnvURI(envFlag);


   @Test()
    public void createRulePayCompany(){//qa 10427813356
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;
       ParentMerchantPeriodRuleCreateReqDTO reqDTO=new ParentMerchantPeriodRuleCreateReqDTO();
       DateFormat dateFormat;
        try {
            System.out.println(envuri);
            parentMerchantPeriodRuleFacade= (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
            reqDTO.setBeneficiary(BeneficiaryEnum.PAY_COMPANY);
            reqDTO.setBillType(BillTypeEnum.PAY_FEE_BILL);
            reqDTO.setBizSystemNo("test");
             dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                reqDTO.setEffectiveEndDate(dateFormat.parse("2099-06-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
            }
            reqDTO.setEffectiveStartDate(dateFormat.parse("2018-01-01"));
            reqDTO.setParentMerchantNo("1013243273");
            reqDTO.setFeePeriod(FeePeriodEnum.MONTH);
            reqDTO.setPeriodTime("14");
            Object respose=parentMerchantPeriodRuleFacade.createParentMerchantPeriodRule(reqDTO);
            System.out.println(JSONUtils.toJsonString(respose));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


   }
   @Test
   public void delRulePayCompany(){
       HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
       ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;
       System.out.println(envuri);
       try {
           parentMerchantPeriodRuleFacade= (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
           parentMerchantPeriodRuleFacade.deleteParentMerchantPeriodRule("20");
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
       }
   }

    /**
     * 创建蜜糖计费周期规则
     */
    @Test
    public void createRuleMitang(){
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;
        ParentMerchantPeriodRuleCreateReqDTO reqDTO=new ParentMerchantPeriodRuleCreateReqDTO();
        try {
            System.out.println(envuri);
            parentMerchantPeriodRuleFacade= (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
            reqDTO.setBeneficiary(BeneficiaryEnum.MT);
            reqDTO.setBizSystemNo("mitang");
            reqDTO.setBillType(BillTypeEnum.SALES_FEE_BILL);
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                reqDTO.setEffectiveEndDate(dateFormat.parse("2099-06-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
            }
            reqDTO.setPeriodTime("14");
            try {
                reqDTO.setEffectiveStartDate(dateFormat.parse("2018-01-01"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            reqDTO.setParentMerchantNo("1013243273");
            reqDTO.setFeePeriod(FeePeriodEnum.MONTH);
            Object respose=parentMerchantPeriodRuleFacade.createParentMerchantPeriodRule(reqDTO);
            System.out.println(JSONUtils.toJsonString(respose));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }}
        /**
         *
         */
        @Test
        public void createRulePromoter(){
            HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
            ParentMerchantPeriodRuleFacade parentMerchantPeriodRuleFacade;
            ParentMerchantPeriodRuleCreateReqDTO reqDTO=new ParentMerchantPeriodRuleCreateReqDTO();
            try {
                System.out.println(envuri);
                parentMerchantPeriodRuleFacade= (ParentMerchantPeriodRuleFacade) hessianProxyFactory.create(ParentMerchantPeriodRuleFacade.class,CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/ParentMerchantPeriodRuleFacade");
                reqDTO.setBeneficiary(BeneficiaryEnum.PROMOTER);
                reqDTO.setBizSystemNo("mitang");
                reqDTO.setBillType(BillTypeEnum.PROMOTER_BILL);

                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    reqDTO.setEffectiveEndDate(dateFormat.parse("2099-06-01"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                }
                reqDTO.setPeriodTime("8");
                try {
                    reqDTO.setEffectiveStartDate(dateFormat.parse("2018-01-01"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                reqDTO.setParentMerchantNo("1042781335");
                reqDTO.setFeePeriod(FeePeriodEnum.MONTH);
                reqDTO.setFeeRate(new BigDecimal("0.01"));
                reqDTO.setAutomatic(true);
                Object respose=parentMerchantPeriodRuleFacade.createParentMerchantPeriodRule(reqDTO);
                System.out.println(JSONUtils.toJsonString(respose));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }



        }

}
