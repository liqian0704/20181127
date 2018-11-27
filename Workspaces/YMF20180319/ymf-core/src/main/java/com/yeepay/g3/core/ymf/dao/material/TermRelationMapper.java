package com.yeepay.g3.core.ymf.dao.material;

import com.yeepay.g3.core.ymf.entity.material.TermRelation;
import com.yeepay.g3.core.ymf.entity.material.TermRelationParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRelationMapper {
    int countByFilter(TermRelationParam filter);

    int deleteByFilter(TermRelationParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(TermRelation record);

    int insertSelective(TermRelation record);

    List<TermRelation> selectByFilter(TermRelationParam filter);

    TermRelation selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") TermRelation record, @Param("example") TermRelationParam filter);

    int updateByFilter(@Param("record") TermRelation record, @Param("example") TermRelationParam filter);

    int updateByPrimaryKeySelective(TermRelation record);

    int updateByPrimaryKey(TermRelation record);
}