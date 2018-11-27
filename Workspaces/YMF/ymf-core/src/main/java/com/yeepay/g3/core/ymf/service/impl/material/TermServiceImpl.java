package com.yeepay.g3.core.ymf.service.impl.material;

import com.yeepay.g3.core.ymf.dao.material.TermDao;
import com.yeepay.g3.core.ymf.dao.material.TermMapper;
import com.yeepay.g3.core.ymf.dao.material.TermRelationMapper;
import com.yeepay.g3.core.ymf.entity.material.Term;
import com.yeepay.g3.core.ymf.entity.material.TermParam;
import com.yeepay.g3.core.ymf.entity.material.TermRelation;
import com.yeepay.g3.core.ymf.entity.material.TermRelationParam;
import com.yeepay.g3.core.ymf.service.material.TermService;
import com.yeepay.g3.facade.ymf.dto.laike.LaikeTermDTO;
import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Title: 终端及终端关系管理
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/10.
 */
@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TermDao termDao;

    @Autowired
    private TermRelationMapper termRelationMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(Term term) {
        termMapper.insert(term);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void batchSave(List<Term> termList) {
        for (Term term : termList) {
            termMapper.insert(term);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void batchUpdate(List<Term> termList) {
        for (Term term : termList) {
            termMapper.updateByPrimaryKey(term);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(TermRelation termRelation) {
        termRelationMapper.insert(termRelation);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void bind(Term term, TermRelation relation) {
        termMapper.updateByPrimaryKey(term);
        termRelationMapper.insert(relation);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void unBind(Term term, TermRelation relation) {
        termMapper.updateByPrimaryKey(term);
        termRelationMapper.updateByPrimaryKey(relation);
    }

    @Override
    public Term queryBySerial(String snSerial) {
        TermParam param = new TermParam();
        param.createCriteria().andSnSerialEqualTo(snSerial);
        List<Term> termList = termMapper.selectByFilter(param);
        if (null != termList && termList.size() > 0) {
            return termList.get(0);
        }
        return null;
    }

    @Override
    public TermRelation queryRelBySerial(String snSerial, String customerNumber) {
        TermRelationParam param = new TermRelationParam();
        param.createCriteria().andSnSerialEqualTo(snSerial).andCustomerNumberEqualTo(customerNumber)
            .andStatusEqualTo(CommonStatus.ACTIVE);
        List<TermRelation> termList = termRelationMapper.selectByFilter(param);
        if (null != termList && termList.size() > 0) {
            return termList.get(0);
        }
        return null;
    }

    @Override
    public List<Term> queryBySerials(Set<String> serials) {
        return termDao.queryBySerials(serials);
    }

    @Override
    public List<String> queryExistSerials(Set<String> serials) {
        return termDao.querySerialsBySerials(serials);
    }

    @Override
    public List<Term> queryInTerm(Set<String> serials) {
        return termDao.queryInTerm(serials);
    }

    @Override
    public List<LaikeTermDTO> queryBindTerm(String customerNumber) {
        return termDao.queryBindTerm(customerNumber);
    }

    @Override
    public boolean validateBindTerm(String customerNumber, String snSerial) {
        return termDao.validateBindTerm(customerNumber, snSerial);
    }
}
