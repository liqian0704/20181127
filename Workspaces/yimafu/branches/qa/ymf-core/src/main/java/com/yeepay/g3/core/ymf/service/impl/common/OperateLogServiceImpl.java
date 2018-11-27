package com.yeepay.g3.core.ymf.service.impl.common;

import com.yeepay.g3.core.ymf.dao.common.OperateLogMapper;
import com.yeepay.g3.core.ymf.entity.common.OperateLog;
import com.yeepay.g3.core.ymf.service.common.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/25.
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int save(OperateLog en) {
        return operateLogMapper.insert(en);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(OperateLog en) {
        return operateLogMapper.updateByPrimaryKey(en);
    }
}
