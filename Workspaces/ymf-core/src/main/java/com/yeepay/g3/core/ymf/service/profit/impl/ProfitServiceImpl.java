package com.yeepay.g3.core.ymf.service.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.impl.ProfitBatchPreparedStatementSetter;
import com.yeepay.g3.core.ymf.dao.profit.ProfitDao;
import com.yeepay.g3.core.ymf.dao.profit.ProfitMapper;
import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.service.profit.ProfitService;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.core.ymf.vo.profit.RowMap;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @description: 毛利记录
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午6:53
 */
@Service
public class ProfitServiceImpl implements ProfitService {
    private static Logger logger = LoggerFactory.getLogger(ProfitServiceImpl.class);
    @Autowired
    private ProfitMapper profitMapper;
    @Autowired
    private ProfitDao profitDao;
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    private void roundAmount(Profit entity) {
        if (entity != null) {
            entity.setMitangTrxamt(Amount.setScale(entity.getMitangTrxamt()));
            entity.setMitangProfitAmt(Amount.setScale(entity.getMitangProfitAmt()));
        }
    }

    @Override
    public int save(Profit entity) {
        if (entity == null) {
            throw new RuntimeException("entity 不能空");
        }
        this.roundAmount(entity);
        return profitMapper.insert(entity);
    }

    @Override
    public int update(Profit entity) {
        if (entity == null || entity.getId() == null) {
            throw new RuntimeException("entity 不能空");
        }
        this.roundAmount(entity);
        return profitMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateSelective(Profit entity) {
        if (entity == null || entity.getId() == null) {
            throw new RuntimeException("entity 不能空");
        }
        this.roundAmount(entity);
        return profitMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Profit findById(Long id) {
        if (id == null) {
            throw new RuntimeException("id 不能空");
        }
        return profitMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量保存
     *
     * @param entitys
     * @return
     */
    @Override
    public int batchSave(List<Profit> entitys) {
        if (entitys == null || entitys.size() < 1) {
            throw new RuntimeException("List<Profit> can't be null");
        }
        //this.roundAmount(entity);批量保存时，不会做计算，所以先不加
        return profitDao.batchSave(entitys);
    }

    /**
     * 使用JDBCUP,Update字样会被ServiceLoggerInterceptor拦截
     * @param entitys
     * @return
     */
    @Override
    public void batchUpdateUseJdbc(List<Profit> entitys) {
        if (entitys == null || entitys.size() < 1) {
            throw new RuntimeException("List<Profit> can't be null");
        }
        String sql = "update YMF_PROFIT" +
                " set " +
                " MITANG_TRXAMT = ?," +
                " MITANG_PROFIT_AMT = ?," +
                " PERCENT = ?," +
                " CALCULATE_STATUS = ?," +
                " SUMMATION_ID = ?," +
                " UPDATE_TIME = ? " +
                " where id = ?";
        int[] effectRows = jdbcTemplate.batchUpdate(sql, new ProfitBatchPreparedStatementSetter(entitys) );
    }

    /**
     * 批量同步数据累加更新。
     * @param entitys   批量更新。
     */
    @Override
    public void batchUpdateProfitUseJdbc(final List<Profit> entitys) {
        if (entitys == null || entitys.size() < 1) {
            throw new RuntimeException("List<Profit> can't be null");
        }
        String sql = "update YMF_PROFIT" +
                " set " +
                " DAY_COUNT = ?," +
                " TOTAL_TRX_COUNT = ?," +
                " TRX_AMT = ?," +
                " PROFIT_AMT = ? " +
                " where id = ?";
        int[] effectRows = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Profit profit = entitys.get(i);
                ps.setLong(1,profit.getDayCount());
                ps.setLong(2,profit.getTotalTrxCount());
                ps.setBigDecimal(3, Amount.setScale(profit.getTrxAmt()));
                ps.setBigDecimal(4, Amount.setScale(profit.getProfitAmt()));
                //条件
                if (profit.getId() == null) {
                    throw new RuntimeException("Id can not be null");
                }
                ps.setLong(5, profit.getId());
            }

            @Override
            public int getBatchSize() {
                return entitys.size();
            }
        } );
    }



    /**
     * 批量查询，根据uniqueId
     *
     * @param set UniqueId的set集合
     * @return
     */
    @Override
    public List<Profit> findByUniqueIdSet(Set<String> set) {
        if (set == null || set.size() < 1) {
            throw new RuntimeException("Set<String> can't be null");
        }
        return profitDao.findByUniqueIdSet(set);
    }
    /**
     * 批量查询，根据uniqueId
     *
     * @param set UniqueId的set集合
     * @return
     */
    @Override
    public Set<String> findUniqueIdByUniqueIdSet(Set<String> set) {
        if (set == null || set.size() < 1) {
            throw new RuntimeException("Set<String> can't be null");
        }
        return profitDao.findUniqueIdByUniqueIdSet(set);
    }

    /**
     * 批量查询，
     *
     * @param profitType
     * @param customerType
     */
    @Override
    public List<Profit> findByProductTypeAndIdPage(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                          String month,Status calculateStatus,
                                          int lowRowNum,int highRowNum) {
        if (profitType == null || customerType == null) {
            throw new RuntimeException("profitType customerType can't be null");
        }
        return profitDao.findByProductTypeAndIdPage(profitType, customerType, month,calculateStatus, lowRowNum, highRowNum);
    }

    /**
     * 根据行号查询ID映射关系
     */
    @Override
    public List<RowMap> findRowMapByProductTypeAndRownum(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                                         String month, Status calculateStatus,
                                                         Set<Long> rownumSet) {
        if (profitType == null || customerType == null) {
            throw new RuntimeException("profitType customerType can't be null");
        }
        if (rownumSet == null || rownumSet.size() < 1) {
            throw new RuntimeException("rownumSet can't be null");
        }
        return profitDao.findRowMapByProductTypeAndRownum(profitType, customerType, month,calculateStatus, rownumSet);
    }

    /**
     * 合计
     *
     * @param profitType
     * @param customerType
     */
    @Override
    public ProfitSummation sumByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                            String month) {
        if (profitType == null || customerType == null) {
            throw new RuntimeException("profitType customerType month can't be null");
        }
        return profitDao.sumByProductType(profitType, customerType, month,null);
    }

    /**
     * 合计
     */
    @Override
    public ProfitSummation sumByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                            String month, Status status) {
        if (profitType == null || customerType == null || month == null) {
            throw new RuntimeException("profitType customerType can't be null");
        }
        return profitDao.sumByProductType(profitType, customerType, month,status);
    }

    /**
     * 统计数量
     */
    @Override
    public int countByProductType(ProfitProductTypeEnum profitType, CustomerTypeEnum customerType,
                                      String month, Status status) {
        if (profitType == null || customerType == null || month == null) {
            throw new RuntimeException("profitType customerType month can't be null");
        }
        Integer count = profitDao.countByProductType(profitType, customerType, month, status);
        return count == null ? 0 : count;
    }

    /**
     * 删除指定月份数据。
     */
    @Override
    public Integer deleteByMonth(@Param("month") String month) {
        if (StringUtils.isBlank(month)) {
            throw new RuntimeException("month can't be null");
        }
        return profitDao.deleteByMonth(month);
    }


}
