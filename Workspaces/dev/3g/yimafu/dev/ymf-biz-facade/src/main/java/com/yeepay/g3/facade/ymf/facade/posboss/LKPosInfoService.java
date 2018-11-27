package com.yeepay.g3.facade.ymf.facade.posboss;

import com.yeepay.g3.facade.ymf.dto.TermDTO;

import java.util.List;

/**
 * Title: POSBOSS提供来客反查终端接口
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author fei.lu on 17/3/14.
 */
public interface LKPosInfoService {

    /**
     *
     * @param inandoutStockNumber 出入库批次号
     * @return
     */
    List<TermDTO> getLKPosInfoByInAndOutNumber(String inandoutStockNumber);
}
