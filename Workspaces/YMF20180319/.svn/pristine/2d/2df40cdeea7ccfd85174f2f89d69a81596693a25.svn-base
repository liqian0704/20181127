package com.yeepay.g3.core.ymf.biz.material;

import com.yeepay.g3.core.ymf.vo.material.BatchTermRequest;
import com.yeepay.g3.core.ymf.vo.material.BatchTermResponse;
import com.yeepay.g3.facade.ymf.dto.TermDTO;
import com.yeepay.g3.facade.ymf.exception.YmfBizException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Title: 终端管理  入库、绑定
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
public interface TermBiz {

    /**
     * 批量终端入库
     * @param termDTOHashMap 终端DTO
     */
    void batchInTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException;

    /**
     * 批量终端出库
     * @param serialList 终端集合
     */
    void batchExportTerm(ArrayList<String> serialList) throws YmfBizException;

    /**
     * 批量终端入库
     * @param termDTOHashMap 终端DTO
     */
    void batchSyncTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException;

    /**
     * 绑定终端
     * @param snSerial SN号
     * @param customerNumber 商户编号
     * @param operator 操作员
     * @throws YmfBizException
     */
    void bindTerm(String snSerial, String customerNumber, String operator,Long shopId) throws YmfBizException;

    /**
     * 解绑终端
     * @param snSerial SN号
     * @param customerNumber 商户编号
     * @param operator 操作员
     * @throws YmfBizException
     */
    void undBindTerm(String snSerial, String customerNumber, String operator) throws YmfBizException;

    /**
     * 批量绑定终端
     * @param requestList 请求
     * @param opCode op单号
     * @param operator 操作员
     * @return 处理结果
     */
    List<BatchTermResponse> batchBindTerm(List<BatchTermRequest> requestList, String opCode, String operator);
}
