package Accounting_core_mapper;

import Accounting_entity.PayRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/28.
 */
public interface PayRecordMapper {
    List<PayRecordEntity> selectPayRecord(@Param("parent_merchant_no") String parent_merchant_no);
}
