package com.yeepay.g3.core.ymf.biz.impl.material;

import com.yeepay.g3.core.ymf.biz.material.TermBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.material.Term;
import com.yeepay.g3.core.ymf.entity.material.TermRelation;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.material.TermService;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.vo.material.BatchTermRequest;
import com.yeepay.g3.core.ymf.vo.material.BatchTermResponse;
import com.yeepay.g3.facade.ymf.dto.BizErrorCode;
import com.yeepay.g3.facade.ymf.dto.TermDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import com.yeepay.g3.facade.ymf.enumtype.term.StockStatus;
import com.yeepay.g3.facade.ymf.enumtype.term.TermStatus;
import com.yeepay.g3.facade.ymf.exception.YmfBizException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Title: 终端与终端关系
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
@Service
public class TermBizImpl implements TermBiz {

    private static final Logger log = LoggerFactory.getLogger(TermBizImpl.class);

    @Autowired
    private TermService termService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShopService shopService;

    @Override
    public void batchInTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException {
        Set<String> keySet = new HashSet<String>(termDTOHashMap.keySet());
        List<String> existTermList = termService.queryExistSerials(keySet);
        // 移除已经入库的
        if (null != existTermList && existTermList.size() > 0) {
            keySet.removeAll(existTermList);
        }
        if (0 == keySet.size()) {
            throw new YmfBizException(BizErrorCode.TERM_ERROR_IMPORT, "没有需要入库的终端");
        }
        List<Term> termList = new ArrayList<Term>();
        for (String serial : keySet) {
            TermDTO termDTO = termDTOHashMap.get(serial);
            termList.add(importTerm(termDTO));
        }
        try {
            termService.batchSave(termList);
            log.info("批量入库终端数:" + termList.size());
        } catch (Exception e) {
            log.error("批量入库终端失败", e);
            throw new YmfBizException(BizErrorCode.TERM_ERROR_IMPORT, "批量终端入库失败");
        }
    }

    @Override
    public void batchExportTerm(ArrayList<String> serialList) {

    }

    @Override
    public void batchSyncTerm(HashMap<String, TermDTO> termDTOHashMap) throws YmfBizException {
        Set<String> keySet = new HashSet<String>(termDTOHashMap.keySet());
        if (0 == keySet.size()) {
            throw new YmfBizException(BizErrorCode.TERM_ERROR_IMPORT, "没有需要入库的终端");
        }
        List<Term> inTermList = termService.queryInTerm(keySet);
        List<Term> termList = new ArrayList<Term>();
        try {
            // 如果有入库的, 则表示是出库操作
            if (null != inTermList && inTermList.size() > 0) {
                for (Term term : inTermList) {
                    TermDTO termDTO = termDTOHashMap.get(term.getSnSerial());
                    term.setOutTime(termDTO.getCreateTime()); // 出库时间
                    term.setStockStatus(StockStatus.OUT);
                    termList.add(term);
                }
                termService.batchUpdate(termList);
                log.info("批量出库终端数:" + termList.size());
            } else { // 否则表示入库操作
                batchInTerm(termDTOHashMap);
            }
        } catch (Exception e) {
            log.error("批量同步终端失败", e);
            throw new YmfBizException(BizErrorCode.TERM_ERROR_IMPORT, "批量同步终端失败");
        }
    }

    @Override
    public List<BatchTermResponse> batchBindTerm(List<BatchTermRequest> requestList, String opCode, String operator) {
        List<BatchTermResponse> responseList = new LinkedList<BatchTermResponse>();
        for (BatchTermRequest request : requestList) {
            String customerNumber = request.getCustomerNumber();
            if (StringUtils.isBlank(customerNumber)) {
                responseList.add(BatchTermResponse.build(request, "商户编号不能为空"));
                continue;
            }
            String shopNumber = request.getShopNumber();
            if (StringUtils.isBlank(shopNumber)) {
                responseList.add(BatchTermResponse.build(request, "网点编号不能为空"));
                continue;
            }
            String snSerial = request.getSnSerial();
            if (StringUtils.isBlank(snSerial)) {
                responseList.add(BatchTermResponse.build(request, "终端编号不能为空"));
                continue;
            }
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            if (null == customer) {
                responseList.add(BatchTermResponse.build(request, "商户编号不存在"));
                continue;
            }
            Shop shop = shopService.queryShopByShopNumber(shopNumber, null);
            if (null == customer) {
                responseList.add(BatchTermResponse.build(request, "商户编号不存在"));
                continue;
            }
            if (null == shop) {
                responseList.add(BatchTermResponse.build(request, "网点编号不存在"));
                continue;
            }
            if (Status.ACTIVE != customer.getStatus()) {
                responseList.add(BatchTermResponse.build(request, "商户状态不可用"));
                continue;
            }
            Term term = termService.queryBySerial(snSerial);
            if (null == term) {
                responseList.add(BatchTermResponse.build(request, "终端不存在"));
                continue;
            }
            if (TermStatus.BIND == term.getTermStatus()) {
                responseList.add(BatchTermResponse.build(request, "终端已绑定"));
                continue;
            }
//            if (StockStatus.IN == term.getStockStatus()) {
//                responseList.add(BatchTermResponse.build(request, "终端未出库"));
//                continue;
//            }
            bind(snSerial, customerNumber, operator, term,shop.getId());
        }
        return responseList;
    }

    @Override
    public void bindTerm(String snSerial, String customerNumber, String operator,Long shopId) throws YmfBizException {
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if (null == customer) {
            throw new YmfBizException(BizErrorCode.CUSTOMER_NOT_EXIST, "商编" + customerNumber);
        }
        if (Status.ACTIVE != customer.getStatus()) {
            throw new YmfBizException(BizErrorCode.CUSTOMER_NOT_ACTIVE, "商编" + customerNumber);
        }
        Term term = termService.queryBySerial(snSerial);
        if (null == term) {
            throw new YmfBizException(BizErrorCode.TERM_NOT_EXIST, "SN号" + snSerial);
        }
        if (TermStatus.BIND == term.getTermStatus()) {
            throw new YmfBizException(BizErrorCode.TERM_EXIST_RELATION, "SN号" + snSerial);
        }
        TermRelation relation = termService.queryRelBySerial(snSerial, customerNumber);
        if (null != relation) {
            throw new YmfBizException(BizErrorCode.TERM_EXIST_RELATION, "SN号" + snSerial + ",商编" + customerNumber);
        }
        bind(snSerial, customerNumber, operator, term,shopId);
    }

    @Override
    public void undBindTerm(String snSerial, String customerNumber, String operator) throws YmfBizException {
        Term term = termService.queryBySerial(snSerial);
        if (null == term) {
            throw new YmfBizException(BizErrorCode.TERM_NOT_EXIST, "SN号" + snSerial);
        }
        TermRelation relation = termService.queryRelBySerial(snSerial, customerNumber);
        if (null == relation) {
            throw new YmfBizException(BizErrorCode.TERM_NO_RELATION, "SN号" + snSerial + ",商编" + customerNumber);
        }
        term.setTermStatus(TermStatus.UNBIND);
        relation.setStatus(CommonStatus.INACTIVE);
        relation.setUnbindTime(new Date());
        relation.setUnbindOperator(operator);
        termService.unBind(term, relation);
    }

    /**
     * 入库终端
     * @param termDTO 终端DTO
     * @return 终端实体
     */
    private Term importTerm(TermDTO termDTO) {
        Term term = new Term();
        term.setSnSerial(termDTO.getSerial());
        term.setInTime(termDTO.getCreateTime());
        term.setManufact(termDTO.getManufacture());
        term.setTermType(termDTO.getTermType());
        term.setStockStatus(StockStatus.IN);
        term.setTermStatus(TermStatus.UNBIND);
        return term;
    }

    /**
     * 绑定终端
     * @param snSerial SN号
     * @param customerNumber 商户编号
     * @param operator 操作员
     * @param term 终端
     */
    private void bind(String snSerial, String customerNumber, String operator, Term term,Long shopId) {
        term.setTermStatus(TermStatus.BIND);
        TermRelation tr = new TermRelation();
        tr.setSnSerial(snSerial);
        tr.setCustomerNumber(customerNumber);
        tr.setBindOperator(operator);
        tr.setBindTime(new Date());
        tr.setStatus(CommonStatus.ACTIVE);
        tr.setTermId(term.getId());
        tr.setShopId(shopId);
        termService.bind(term, tr);
    }
}
