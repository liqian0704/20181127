package Accounting_core_mapper;

import Accounting_entity.TotalBillEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/28.
 */
public interface TotalBillMapper {

    List<TotalBillEntity> selectTotalBill(@Param("parent_merchant_no") String parent_merchant_no);

    List<TotalBillEntity> selectTotalBillByType(@Param("parent_merchant_no") String parent_merchant_no,@Param("start_date") String start_date,@Param("bill_type") String BillType );
}
