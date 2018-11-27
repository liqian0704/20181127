package com.yeepay.g3.core.ymf.service.gratuity;

import com.yeepay.g3.core.ymf.entity.gratuity.Gratuity;

import java.util.List;

/**
 * 名称: GratuityService <br>
 * 描述: <br>
 *
 * @author: dongxu.lu
 * @since: 16/11/17 上午9:55
 * @version: 1.0.0
 */
public interface GratuityService {

    int save(Gratuity gratuity);

    int update(Gratuity gratuity);

    Gratuity getById(Long id);

    List<Gratuity> getByCustomerNumber(String customerNumber);

}
