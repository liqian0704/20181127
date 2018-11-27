package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.core.laike.entity.BlackListEntity;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.BlackListService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/10/28
 * @Time: 上午11:27
 */
@Service
public class BlackListServiceImpl extends AbstractService implements BlackListService{
    @Override
    public void deleteByPrimaryKey(Long id) {

    }

    @Override
    public int insert(BlackListEntity record) {
        return blackListRepository.save(record);
    }

    @Override
    public BlackListEntity selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(BlackListEntity record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BlackListEntity record) {
        return 0;
    }
}
