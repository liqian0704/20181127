package com.yeepay.g3.core.ymf.dao.dictionary;

import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryDao {
    public List<Dictionary> getAll();

    public List<Dictionary> getCustomerFunctionList(@Param("customerNumber") String customerNumber) ;

}