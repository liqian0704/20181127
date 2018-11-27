package repository;

import Accounting_entity.AccountInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yp-tc-2646 on 17/10/13.
 */
//@Repository
public interface AccountInfoMapper {
    List<AccountInfoEntity> accountInfo(@Param("customerNo") String customerNo);
}
