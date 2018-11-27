package com.yeepay.g3.core.ymf.dao.cod;

import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfo;
import com.yeepay.g3.core.ymf.entity.cod.CodNotifyInfoParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodNotifyInfoMapper {
    int countByFilter(CodNotifyInfoParam filter);

    int deleteByFilter(CodNotifyInfoParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(CodNotifyInfo record);

    int insertSelective(CodNotifyInfo record);

    List<CodNotifyInfo> selectByFilter(CodNotifyInfoParam filter);

    CodNotifyInfo selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") CodNotifyInfo record, @Param("example") CodNotifyInfoParam filter);

    int updateByFilter(@Param("record") CodNotifyInfo record, @Param("example") CodNotifyInfoParam filter);

    int updateByPrimaryKeySelective(CodNotifyInfo record);

    int updateByPrimaryKey(CodNotifyInfo record);
}