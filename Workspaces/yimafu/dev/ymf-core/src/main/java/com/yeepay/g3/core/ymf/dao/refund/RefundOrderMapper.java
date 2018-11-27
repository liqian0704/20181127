package com.yeepay.g3.core.ymf.dao.refund;

import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundOrderMapper {
    int countByFilter(RefundOrderParam filter);

    int deleteByFilter(RefundOrderParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    List<RefundOrder> selectByFilter(RefundOrderParam filter);

    RefundOrder selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") RefundOrder record, @Param("example") RefundOrderParam filter);

    int updateByFilter(@Param("record") RefundOrder record, @Param("example") RefundOrderParam filter);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
}