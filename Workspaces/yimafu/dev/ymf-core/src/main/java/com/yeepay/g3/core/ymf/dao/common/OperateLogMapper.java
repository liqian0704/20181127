package com.yeepay.g3.core.ymf.dao.common;

import com.yeepay.g3.core.ymf.entity.common.OperateLog;
import com.yeepay.g3.core.ymf.entity.common.OperateLogParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateLogMapper {
    int countByFilter(OperateLogParam filter);

    int deleteByFilter(OperateLogParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    List<OperateLog> selectByFilter(OperateLogParam filter);

    OperateLog selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") OperateLog record, @Param("example") OperateLogParam filter);

    int updateByFilter(@Param("record") OperateLog record, @Param("example") OperateLogParam filter);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);
}