package com.yeepay.g3.core.ymf.dao.notify;

import com.yeepay.g3.core.ymf.entity.notify.NotifyRecord;
import com.yeepay.g3.core.ymf.entity.notify.NotifyRecordParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotifyRecordMapper {
    int countByFilter(NotifyRecordParam filter);

    int deleteByFilter(NotifyRecordParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(NotifyRecord record);

    int insertSelective(NotifyRecord record);

    List<NotifyRecord> selectByFilter(NotifyRecordParam filter);

    NotifyRecord selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") NotifyRecord record, @Param("example") NotifyRecordParam filter);

    int updateByFilter(@Param("record") NotifyRecord record, @Param("example") NotifyRecordParam filter);

    int updateByPrimaryKeySelective(NotifyRecord record);

    int updateByPrimaryKey(NotifyRecord record);
}