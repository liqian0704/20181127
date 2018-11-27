package com.yeepay.g3.core.ymf.dao.material;

import com.yeepay.g3.core.ymf.entity.material.Term;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
@Repository
public interface TermDao {

    /**
     * 根据SN号查询终端
     * @param serials SN号
     * @return
     */
    List<Term> queryBySerials(@Param("list") Set<String> serials);

    /**
     * 根据SN号查询SN号
     * @param serials SN号
     * @return
     */
    List<String> querySerialsBySerials(@Param("list") Set<String> serials);

    /**
     * 根据SN号查询入库的终端
     * @param serials SN号
     * @return
     */
    List<Term> queryInTerm(@Param("list") Set<String> serials);

    /**
     * 查询商户绑定的终端
     * @param customerNumber
     * @return
     */
    List<LaikeTermDTO> queryBindTerm(String customerNumber);

    /**
     * 查询商户绑定的终端
     * @param customerNumber 商户编号
     * @param snSerial SN号
     * @return
     */
    boolean validateBindTerm(@Param("customerNumber") String customerNumber, @Param("snSerial") String snSerial);

}
