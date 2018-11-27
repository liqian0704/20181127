package Accounting_core_mapper;

import Accounting_entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 18/4/26.
 */
public interface OrderInfoMapper {
    List<OrderInfoEntity> selectOrderByPMNo(@Param("parent_merchant_no") String parent_merchant_no);
    int updateOrderStatus(@Param("parent_merchant_no") String parent_merchant_no);
    List<OrderInfoEntity> selectOrderByPMNoandPeriod (@Param("parent_merchant_no") String parent_merchant_no, @Param("bill_start_time") String bill_start_time, @Param("bill_end_time") String bill_end_time);
}
