package com.yeepay.g3.core.ymf.service.profit.impl;

import com.yeepay.g3.core.ymf.dao.profit.ProfitSummationDao;
import com.yeepay.g3.core.ymf.dao.profit.ProfitSummationMapper;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 毛利汇总数据
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:53
 */
@Service
public class ProfitSummationServiceImpl implements ProfitSummationService {
    @Autowired
    private ProfitSummationDao profitSummationDao;
    @Autowired
    private ProfitSummationMapper profitSummationMapper;

    private void roundAmount(ProfitSummation entity) {
        if (entity != null) {
            entity.setTotalTrxAmt(Amount.setScale(entity.getTotalTrxAmt()));
            entity.setTotalProfitAmt(Amount.setScale(entity.getTotalProfitAmt()));
            entity.setMitangTotalTrxamt(Amount.setScale(entity.getMitangTotalTrxamt()));
            entity.setMitangTotalProfitAmt(Amount.setScale(entity.getMitangTotalProfitAmt()));
        }
    }

    @Override
    public int save(ProfitSummation entity) {
        if (entity == null) {
            throw new RuntimeException("entity 不能为空");
        }
        entity.setCreateTime(new Date());
        this.roundAmount(entity);
        return profitSummationMapper.insert(entity);
    }

    @Override
    public int update(ProfitSummation entity) {
        if (entity == null || entity.getId() == null) {
            throw new RuntimeException("entity 不能为空");
        }
        entity.setUpdateTime(new Date());
        this.roundAmount(entity);
        return profitSummationMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(ProfitSummation entity) {
        if (entity == null || entity.getId() == null) {
            throw new RuntimeException("entity 不能为空");
        }
        entity.setUpdateTime(new Date());
        this.roundAmount(entity);
        return profitSummationMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public ProfitSummation findById(Long id) {
        if (id == null) {
            throw new RuntimeException("id 不能为空");
        }
        return profitSummationMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProfitSummation findByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                             String month) {
        if (profitType == null || customerType == null || month == null) {
            throw new RuntimeException("profitType customerType month can't be null");
        }
        ProfitSummation summation = profitSummationDao.findByProductType(profitType,customerType,month);
        if (summation == null) {
            return null;
        }
        //对于为null的值初始化为0
        BigDecimal totalTrxAmt = summation.getTotalTrxAmt();
        if (summation.getTotalTrxAmt() == null) {
            totalTrxAmt = new BigDecimal(0);
            summation.setTotalTrxAmt(totalTrxAmt);
        }
        BigDecimal totalProfitAmt = summation.getTotalProfitAmt();
        if (totalProfitAmt == null) {
            totalProfitAmt = new BigDecimal(0);
            summation.setTotalProfitAmt(totalProfitAmt);
        }
        if (summation.getTotalCount() == null) {
            summation.setTotalCount(0L);
        }
        if (summation.getMitangTotalTrxamt() == null) {
            summation.setMitangTotalTrxamt(new BigDecimal(0));
        }
        if (summation.getMitangTotalProfitAmt() == null) {
            summation.setMitangTotalProfitAmt(new BigDecimal(0));
        }
        if (summation.getTotalTrxCount() == null) {
            summation.setTotalTrxCount(0L);
        }
        if (summation.getTotalDayCount() == null) {
            summation.setTotalDayCount(0L);
        }
        return summation ;
    }

}
