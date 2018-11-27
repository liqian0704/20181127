package com.yeepay.g3.core.ymf.dao.gratuity;

import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 名称: GratuityDao <br>
 * 描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/11/17 上午10:40
 * @version: 1.0.0
 */
@Repository
public interface GratuityDao {

    List<Gratuity> findByCustomerNumber (@Param("customerNumber") String customerNumber);

}
