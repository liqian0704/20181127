package Accounting_core_mapper;

import Accounting_entity.PromoterPayRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/28.
 */
public interface PromoterPayRecordMapper {
    List<PromoterPayRecordEntity> selectPromoterPayRecord(@Param("parent_merchant_no") String parent_merchant_no);

    List<PromoterPayRecordEntity> selectPromoterPayRecordbyBillNo(@Param("parentMerchantNo") String parent_merchant_no, @Param("bilNo") String total_bill_no);

}
