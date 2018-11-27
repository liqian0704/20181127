package com.yeepay.g3.core.ymf.dao.refund;

import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetailParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundOrderDetailMapper {
    int countByFilter(RefundOrderDetailParam filter);

    int deleteByFilter(RefundOrderDetailParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(RefundOrderDetail record);

    int insertSelective(RefundOrderDetail record);

    List<RefundOrderDetail> selectByFilter(RefundOrderDetailParam filter);

    RefundOrderDetail selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") RefundOrderDetail record, @Param("example") RefundOrderDetailParam filter);

    int updateByFilter(@Param("record") RefundOrderDetail record, @Param("example") RefundOrderDetailParam filter);

    int updateByPrimaryKeySelective(RefundOrderDetail record);

    int updateByPrimaryKey(RefundOrderDetail record);
}