package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.CustomerSettle;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettleParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSettleMapper {
    int countByFilter(CustomerSettleParam filter);

    int deleteByFilter(CustomerSettleParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerSettle record);

    int insertSelective(CustomerSettle record);

    List<CustomerSettle> selectByFilter(CustomerSettleParam filter);

    CustomerSettle selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") CustomerSettle record, @Param("example") CustomerSettleParam filter);

    int updateByFilter(@Param("record") CustomerSettle record, @Param("example") CustomerSettleParam filter);

    int updateByPrimaryKeySelective(CustomerSettle record);

    int updateByPrimaryKey(CustomerSettle record);
}