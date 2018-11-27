package Accounting_core_mapper;

import Accounting_entity.TotalBillNoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/28.
 */
public interface TotalBillNoMapper {
    List<TotalBillNoEntity> selectTotalBillNoEntity(@Param("parent_merchant_no") String parent_merchant_no);

     String selectTotalBillNoByDate(@Param("parent_merchant_no") String parent_merchant_no, @Param("start_date") String start_date);
}
