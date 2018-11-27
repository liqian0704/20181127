package com.yeepay.g3.core.ymf.service.impl.terminalno;/**
 * Created by jiwei.lv on 17/5/8.
 */

import com.yeepay.g3.core.ymf.dao.order.OrderMapper;
import com.yeepay.g3.core.ymf.dao.upayterminalno.TerminalNumberDao;
import com.yeepay.g3.core.ymf.dao.upayterminalno.TerminalNumberMapper;
import com.yeepay.g3.core.ymf.entity.upayterminalno.TerminalNumber;
import com.yeepay.g3.core.ymf.service.terminalno.TerminalNumberService;
import com.yeepay.g3.facade.ymf.enumtype.common.CommonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2017-05-17/5/8
 */
@Service
public class TerminalNumberServiceImpl implements TerminalNumberService {
    @Autowired
    private TerminalNumberDao terminalNumberDao;
    @Autowired
    private TerminalNumberMapper terminalNumberMapper;
    @Override
    public boolean checkTerminalNumber(String customerNumber, String terminalNumber) {
        TerminalNumber terminal=terminalNumberDao.findByCusNOAndTerNO(customerNumber,terminalNumber);
        if(terminal==null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public int saveTerNO(String customerNumber, String terminalNumber, String user) {
        TerminalNumber terminalNO=new TerminalNumber();
        terminalNO.setCustomerNumber(customerNumber);
        terminalNO.setTerminalNumber(terminalNumber);
        terminalNO.setCreateTime(new Date());
        terminalNO.setStatus(CommonStatus.ACTIVE);
        terminalNO.setOperatorName(user);
        return terminalNumberMapper.insert(terminalNO);
    }

    @Override
    public TerminalNumber findByTerminalNO(Long id) {
        return terminalNumberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateTerminalNO(TerminalNumber terminalNumber) {
        return terminalNumberMapper.updateByPrimaryKey(terminalNumber);
    }

    @Override
    public TerminalNumber findTerminalNOByCustomerNumber(String customerNumber) {
        return terminalNumberDao.selectByCustomerNumber(customerNumber,"ACTIVE");
    }
}
