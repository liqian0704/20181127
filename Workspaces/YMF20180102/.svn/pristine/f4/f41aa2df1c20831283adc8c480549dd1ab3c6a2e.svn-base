package com.yeepay.g3.core.ymf.dao.material;

import com.yeepay.g3.core.ymf.entity.material.Term;
import com.yeepay.g3.core.ymf.entity.material.TermParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TermMapper {
    int countByFilter(TermParam filter);

    int deleteByFilter(TermParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(Term record);

    int insertSelective(Term record);

    List<Term> selectByFilter(TermParam filter);

    Term selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") Term record, @Param("example") TermParam filter);

    int updateByFilter(@Param("record") Term record, @Param("example") TermParam filter);

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);
}