package com.yeepay.g3.core.ymf.dao.customer;

import com.yeepay.g3.core.ymf.entity.customer.WechatRel;
import com.yeepay.g3.core.ymf.entity.customer.WechatRelParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WechatRelMapper {
    int countByFilter(WechatRelParam filter);

    int deleteByFilter(WechatRelParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(WechatRel record);

    int insertSelective(WechatRel record);

    List<WechatRel> selectByFilter(WechatRelParam filter);

    WechatRel selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") WechatRel record, @Param("example") WechatRelParam filter);

    int updateByFilter(@Param("record") WechatRel record, @Param("example") WechatRelParam filter);

    int updateByPrimaryKeySelective(WechatRel record);

    int updateByPrimaryKey(WechatRel record);
}