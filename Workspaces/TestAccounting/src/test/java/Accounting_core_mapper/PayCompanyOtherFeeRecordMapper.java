package Accounting_core_mapper;


import Accounting_entity.PayComOtherFeeRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/28.
 */
public interface PayCompanyOtherFeeRecordMapper {//
    List<PayComOtherFeeRecordEntity> selectPayComOtherFeeRecord(@Param("parent_merchant_no") String parent_merchant_no);

    List<PayComOtherFeeRecordEntity> selectPayComOtherFeeRecordByDate(@Param("parent_merchant_no") String parent_merchant_no,@Param("bill_start_time") String billStartTime,@Param("bill_end_time") String billEndTime);

}
