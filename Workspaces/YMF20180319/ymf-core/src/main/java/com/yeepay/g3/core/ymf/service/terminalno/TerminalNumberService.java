package com.yeepay.g3.core.ymf.service.terminalno;/**
 * Created by jiwei.lv on 17/5/8.
 */

import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;

/**
 * @author jiwei.lv
 * @create 2017-05-17/5/8
 */
public interface TerminalNumberService {
    /**
     * 校验商户银联终端号是否唯一
     * @param customerNumber
     * @param terminalNumber
     * @return
     */
    boolean checkTerminalNumber(String customerNumber, String terminalNumber);

    /**
     * 添加商户银联终端号
     * @param customerNumber
     * @param customerNumber1
     * @param user
     * @return
     */
    int saveTerNO(String customerNumber, String customerNumber1, String user);

    /**
     * 根据Id获得商户银联终端号
     * @param id
     * @return
     */
    TerminalNumber findByTerminalNO(Long id);

    /**
     * 修改
     * @param terminalNumber
     * @return
     */
    int updateTerminalNO(TerminalNumber terminalNumber);

    /**
     * 根据商户号得到银联终端号
     * @param customerNumber
     * @return
     */
    TerminalNumber findTerminalNOByCustomerNumber(String customerNumber);
}
