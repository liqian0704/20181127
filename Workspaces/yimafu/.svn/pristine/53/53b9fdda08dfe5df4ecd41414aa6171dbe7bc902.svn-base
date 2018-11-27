package com.yeepay.g3.core.ymf.dao.dictionary;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.dictionary.DictionaryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryMapper {
    int countByFilter(DictionaryParam filter);

    int deleteByFilter(DictionaryParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    List<Dictionary> selectByFilter(DictionaryParam filter);

    Dictionary selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Dictionary record, @Param("example") DictionaryParam filter);

    int updateByFilter(@Param("record") Dictionary record, @Param("example") DictionaryParam filter);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}