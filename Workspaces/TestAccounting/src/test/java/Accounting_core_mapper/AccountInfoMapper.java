package Accounting_core_mapper;

import Accounting_entity.AccountInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 17/10/13.
 */
//
public interface AccountInfoMapper {
    List<AccountInfoEntity> accountInfo(@Param("customerNo") String customerNo);
}
