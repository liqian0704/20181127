package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.order.Payment;
import com.yeepay.g3.core.ymf.entity.order.PaymentParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMapper {
    int countByFilter(PaymentParam filter);

    int deleteByFilter(PaymentParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    int insertSelective(Payment record);

    List<Payment> selectByFilter(PaymentParam filter);

    Payment selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Payment record, @Param("example") PaymentParam filter);

    int updateByFilter(@Param("record") Payment record, @Param("example") PaymentParam filter);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}