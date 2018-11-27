package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.g3.core.ymf.dao.dictionary.DictionaryDao;
import com.yeepay.g3.core.ymf.dao.dictionary.DictionaryMapper;
import com.yeepay.g3.core.ymf.entity.dictionary.Dictionary;
import com.yeepay.g3.core.ymf.entity.dictionary.DictionaryParam;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典管理
 * Created by yp-tc-m-2762 on 16/8/12.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper  dictionaryMapper;
    @Autowired
    private DictionaryDao dictionaryDao;
    @Override
    public List<Dictionary> getDictionariesByType(String type) {
        DictionaryParam param = new DictionaryParam();
        param.createCriteria()
                .andStatusEqualTo(Status.ACTIVE)
                .andTypeEqualTo(type);
        return dictionaryMapper.selectByFilter(param);
    }

    @Override
    public Dictionary getDictionaryByCode(String code) {
        DictionaryParam param = new DictionaryParam();
        param.createCriteria().andCodeEqualTo(code);
        List<Dictionary> list  = dictionaryMapper.selectByFilter(param);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Dictionary getDictionaryByName(String name) {
        DictionaryParam param = new DictionaryParam();
        param.createCriteria().andNameEqualTo(name);
        List<Dictionary> list = dictionaryMapper.selectByFilter(param);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Dictionary getDictByTypeAndCode(String type, String code) {
        DictionaryParam param = new DictionaryParam();
        param.createCriteria().andTypeEqualTo(type)
                .andCodeEqualTo(code);
        List<Dictionary> list = dictionaryMapper.selectByFilter(param);
        if(list != null && list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Dictionary> getAllDictionary() {
        return dictionaryDao.getAll();
    }

    @Override
    public Dictionary getById(Long id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int batchSave(List<OperateEntity<Dictionary>> wrapperList) {
        int count = 0;
        for (OperateEntity<Dictionary> wrapper : wrapperList) {
            count += dictionaryMapper.insert(wrapper.getEntity());
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int save(OperateEntity<Dictionary> wrapper) {
        return dictionaryMapper.insert(wrapper.getEntity());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(OperateEntity<Dictionary> wrapper) {
        return dictionaryMapper.updateByPrimaryKeySelective(wrapper.getEntity());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int delete(OperateEntity<Dictionary> wrapper) {
        return dictionaryMapper.deleteByPrimaryKey(wrapper.getEntity().getId());
    }

    @Override
    public List<Dictionary> getCustomerFunctionList(String customerNumber) {
        return dictionaryDao.getCustomerFunctionList(customerNumber) ;
    }
}
