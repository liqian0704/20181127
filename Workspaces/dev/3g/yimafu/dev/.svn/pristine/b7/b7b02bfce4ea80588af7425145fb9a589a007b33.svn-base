package com.yeepay.g3.facade.ymf.facade.laike;

import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;

import java.util.List;

/**
 * Title: 来客终端接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/15.
 */
public interface LaikeTermFacade {

    /**
     * 根据商编查询已绑定的来客终端
     * @param customerNumber 商户编号
     * @return
     */
    List<LaikeTermDTO> queryByCustomer(String customerNumber);

    /**
     * 判断商编与终端是否有绑定状态
     * @param customerNumber 商户编号
     * @param snSerial 终端SN号
     * @return
     */
    boolean validateTerm(String customerNumber, String snSerial);
}
