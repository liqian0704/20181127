package Accounting_core_mapper;

import Accounting_entity.PayCompanyBillEntity;
import com.miitang.facade.accounting.enumtype.PayProductEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/26.
 */
public interface PayCompanyBillMapper {//
    List<PayCompanyBillEntity>  selectPayCompayBill(@Param("parent_merchant_no") String parent_merchant_no);
    PayCompanyBillEntity selectPayCompayBillByDate(@Param("parent_merchant_no") String parent_merchant_no, @Param("detail_start_time")
                                                   String bill_start_time,@Param("detail_end_time") String bill_end_time);

    PayCompanyBillEntity selectPayCompayBillByDateAndCostType(@Param("parent_merchant_no") String parent_merchant_no, @Param("cost_type") PayProductEnum cost_type, @Param("detail_start_date")
            String bill_start_date, @Param("detail_end_date") String bill_end_date);


}
