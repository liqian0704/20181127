package com.yeepay.g3.core.ymf.dao.order;

import com.yeepay.g3.core.ymf.entity.billDto.QueryResultParamDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public interface YmfBillMapper {

    List<QueryResultParamDTO>

    getYmfBillInfoByMap(HashMap<String, Object> map);

    BigDecimal getTotalAmountByMap(HashMap<String, Object> map);

    int getTotalCountByMap(HashMap<String, Object> map);

}
