package com.yeepay.g3.core.ymf.dao.qrcode;

import com.yeepay.g3.core.ymf.entity.qrcode.QrRelation;
import com.yeepay.g3.core.ymf.entity.qrcode.QrRelationParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QrRelationMapper {
    int countByFilter(QrRelationParam filter);

    int deleteByFilter(QrRelationParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(QrRelation record);

    int insertSelective(QrRelation record);

    List<QrRelation> selectByFilter(QrRelationParam filter);

    QrRelation selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") QrRelation record, @Param("example") QrRelationParam filter);

    int updateByFilter(@Param("record") QrRelation record, @Param("example") QrRelationParam filter);

    int updateByPrimaryKeySelective(QrRelation record);

    int updateByPrimaryKey(QrRelation record);
}