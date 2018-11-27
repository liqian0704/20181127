package com.yeepay.g3.core.ymf.service.material;

import com.yeepay.g3.core.ymf.entity.material.Term;
import com.yeepay.g3.core.ymf.entity.material.TermRelation;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;

import java.util.List;
import java.util.Set;

/**
 * Title: 终端与终端关系管理
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public interface TermService {

    void save(Term term);

    /**
     * 批量入库
     * @param termList 入库
     */
    void batchSave(List<Term> termList);

    /**
     * 批量出库
     * @param termList 出库
     */
    void batchUpdate(List<Term> termList);

    void save(TermRelation termRelation);

    /**
     * 绑定终端
     * @param term 终端
     * @param relation 终端关系
     */
    void bind(Term term, TermRelation relation);

    /**
     * 绑定终端
     * @param term 终端
     * @param relation 终端关系
     */
    void unBind(Term term, TermRelation relation);

    /**
     * 根据终端号查询终端
     * @param snSerial
     * @return
     */
    Term queryBySerial(String snSerial);

    /**
     * 根据终端号和商户编号查询绑定关系
     * @param snSerial 终端号
     * @param customerNumber 商户编号
     * @return
     */
    TermRelation queryRelBySerial(String snSerial, String customerNumber);

    /**
     * 根据终端号查询终端
     * @param serials 终端号
     * @return
     */
    List<Term> queryBySerials(Set<String> serials);

    /**
     * 查询是否已经入库
     * @param serials 终端号
     * @return
     */
    List<String> queryExistSerials(Set<String> serials);

    /**
     * 查询入库的终端
     * @param serials
     * @return
     */
    List<Term> queryInTerm(Set<String> serials);

    /**
     * 查询商户绑定的终端
     * @param customerNumber 商户编号
     * @return
     */
    List<LaikeTermDTO> queryBindTerm(String customerNumber);

    /**
     * 查询商户和终端是否有绑定关系
     * @param customerNumber 商户编号
     * @param snSerial 终端SN号
     * @return
     */
    boolean validateBindTerm(String customerNumber, String snSerial);
}
