package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @description: 批量更新实现类
 * @author: xiaobin.liu
 * @date: 17/12/29
 * @time: 下午6:34
 */
public class ProfitBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    private final List<Profit> list;

    public ProfitBatchPreparedStatementSetter(List<Profit> list) {
        this.list = list;
    }
    /**
     * Set parameter values on the given PreparedStatement.
     *
     * @param ps the PreparedStatement to invoke setter methods on
     * @param i  index of the statement we're issuing in the batch, starting from 0
     * @throws SQLException if a SQLException is encountered
     *                      (i.e. there is no need to catch SQLException)
     */
    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Profit profit = list.get(i);
        //logger.info("Will be save :" + JSONUtils.toJsonString(profit));
        ps.setBigDecimal(1, Amount.setScale(profit.getMitangTrxamt()));
        ps.setBigDecimal(2, Amount.setScale(profit.getMitangProfitAmt()));
        ps.setBigDecimal(3, profit.getPercent() == null ? new BigDecimal(0L) : profit.getPercent());
        ps.setString(4, profit.getCalculateStatus().name());
        ps.setLong(5, profit.getSummationId());

        java.sql.Date updateTime;
        if (profit.getUpdateTime() == null) {
            updateTime = new java.sql.Date(new Date().getTime());
        } else {
            updateTime = new java.sql.Date(profit.getUpdateTime().getTime());
        }
        ps.setDate(6, updateTime);

        //条件
        if (profit.getId() == null) {
            throw new RuntimeException("Id can not be null");
        }
        ps.setLong(7, profit.getId());
    }

    /**
     * Return the size of the batch.
     *
     * @return the number of statements in the batch
     */
    @Override
    public int getBatchSize() {
        return list.size();
    }
}
