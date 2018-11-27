package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.order.Order;
import com.yeepay.g3.core.ymf.entity.order.OrderParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int countByFilter(OrderParam filter);

    int deleteByFilter(OrderParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByFilter(OrderParam filter);

    Order selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Order record, @Param("example") OrderParam filter);

    int updateByFilter(@Param("record") Order record, @Param("example") OrderParam filter);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}