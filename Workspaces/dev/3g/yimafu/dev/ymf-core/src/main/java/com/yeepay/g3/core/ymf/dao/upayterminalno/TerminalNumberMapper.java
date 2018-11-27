package com.yeepay.g3.core.ymf.dao.upayterminalno;

import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumberParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalNumberMapper {
    int countByFilter(TerminalNumberParam filter);

    int deleteByFilter(TerminalNumberParam filter);

    int deleteByPrimaryKey(Long id);

    int insert(TerminalNumber record);

    int insertSelective(TerminalNumber record);

    List<TerminalNumber> selectByFilter(TerminalNumberParam filter);

    TerminalNumber selectByPrimaryKey(Long id);

    int updateByFilterSelective(@Param("record") TerminalNumber record, @Param("example") TerminalNumberParam filter);

    int updateByFilter(@Param("record") TerminalNumber record, @Param("example") TerminalNumberParam filter);

    int updateByPrimaryKeySelective(TerminalNumber record);

    int updateByPrimaryKey(TerminalNumber record);
}