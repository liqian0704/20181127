package Accounting_core_mapper;

import Accounting_entity.PayCompanyOrderFeeRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/27.
 */
public interface PayCompanyOrderFeeRecordMapper {

    List<PayCompanyOrderFeeRecordEntity> selectPayComOrderFeeRecord(@Param("unique_order_no") String unique_order_no);

    List<PayCompanyOrderFeeRecordEntity> selectPayComOrderFeeRecordByDate(@Param("bill_start_time") String bill_start_date,@Param("bill_end_time") String bill_end_date);
}
