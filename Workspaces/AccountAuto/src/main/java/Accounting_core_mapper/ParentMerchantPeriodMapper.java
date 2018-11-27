package Accounting_core_mapper;

import Accounting_entity.ParentMerchantPeriodRuleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/26.
 */
public interface ParentMerchantPeriodMapper {
    List<ParentMerchantPeriodRuleEntity> selectPMPeriodRule(String parentMerchantNo);

    int selectPMPeriodRuleByBillType(@Param("parent_merchant_no") String parentMerchantNo, @Param("bill_type") String biiType);

    List<ParentMerchantPeriodRuleEntity> selectPMRuleByBillType(@Param("parent_merchant_no") String parentMerchantNo, @Param("bill_type") String biiType);

}
