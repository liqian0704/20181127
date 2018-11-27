package Mitang_TestCase;


import com.caucho.hessian.client.HessianProxyFactory;
import com.miitang.common.enums.FeeTypeEnum;
import com.miitang.facade.accounting.dto.MtFeeFormulaCreateReqDTO;
import com.miitang.facade.accounting.facade.MtFeeFormulaFacade;
import com.yeepay.g3.utils.common.json.JSONUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yp-tc-2646 on 18/3/5.
 */
public class Mitang03_MTFee {
    static String envFlag= CommPro.env;
    static String envuri= CommPro.getEnvURI(envFlag);
    static MtFeeFormulaFacade mtFeeFormulaFacade = null;
    static  MtFeeFormulaCreateReqDTO reqDTO=new MtFeeFormulaCreateReqDTO();
    static {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        try {
            mtFeeFormulaFacade= (MtFeeFormulaFacade) hessianProxyFactory.create(MtFeeFormulaFacade.class, CommPro.getEnvURI(envFlag)+"/mt-accounting-hessian/hessian/MtFeeFormulaFacade");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 增加蜜糖费率模版
     */
    @Test
    public void addMTFee(){
        System.out.println(envuri);
        reqDTO.setBizSystemNo("mitang");
        reqDTO.setParentMerchantNo("1042781335679");
        reqDTO.setEffectiveStartDate(new Date());
        try {
            reqDTO.setEffectiveEndDate(new SimpleDateFormat("yyyyy-MM-dd").parse("2059-02-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reqDTO.setFeeType(FeeTypeEnum.FIXED_RATE);
        reqDTO.setFeeRate(BigDecimal.valueOf(2));
        reqDTO.setMaxAmount(BigDecimal.valueOf(100));
        reqDTO.setMinAmount(BigDecimal.valueOf(1000));
        reqDTO.setFeeAmount(BigDecimal.valueOf(0));
        reqDTO.setPayProduct("CASH");
        reqDTO.setPayCompany("YEEPAY");
        Object respose=mtFeeFormulaFacade.createFeeFormula(reqDTO);
        System.out.println(JSONUtils.toJsonString(respose));
    }

    /**
     * 删除蜜糖费率模版
     */
    public void delMTFee(){
        Boolean result=mtFeeFormulaFacade.deleteMtFeeFormulaByFormulaId(7L);
    }
}
