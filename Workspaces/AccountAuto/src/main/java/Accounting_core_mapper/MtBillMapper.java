package Accounting_core_mapper;

import Accounting_entity.MtBillEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yp-tc-2646 on 17/10/13.
 */
//
public interface MtBillMapper {
    MtBillEntity selectMtBill(@Param("mt_bill_no") String mt_bill_no);
}
