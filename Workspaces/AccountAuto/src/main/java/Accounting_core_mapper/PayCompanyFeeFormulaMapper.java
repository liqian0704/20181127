package Accounting_core_mapper;

import Accounting_entity.PayCompanyFeeFormulaEntity;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/27.
 */
public interface PayCompanyFeeFormulaMapper {

    List<PayCompanyFeeFormulaEntity> selectPayComFeeFormular(@Param("parent_merchant_no") String parent_merchant_no);

    PayCompanyFeeFormulaEntity selectPayComOrderFeeForByPro(@Param("parent_merchant_no") String parent_merchant_no, @Param("pay_product") PayProductEnum pay_product);

}
