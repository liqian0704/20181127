package Accounting_core_mapper;

import Accounting_entity.MtFeeFormulaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 17/10/13.
 */
//@Repository
public interface MtFeeFormulaMapper {
    List<MtFeeFormulaEntity> selectMtFee(@Param("parent_merchant_no") String parent_merchant_no);
}
