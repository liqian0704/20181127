package com.yeepay.g3.core.ymf.service.profit.impl;

import com.yeepay.g3.core.ymf.dao.profit.ReportorDao;
import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.service.profit.ReportorService;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/15
 * @time: 下午5:37
 */
@Service
public class ReportorServiceImpl implements ReportorService {
    @Autowired
    private ReportorDao reportorDao;
    /**
     * 查询所有数据
     *
     * @param profitProductType 毛利产品类型
     * @param beginDate         日期
     * @param endDate           日期
     * @return
     */
    @Override
    public List<Profit> queryProfits(ProfitProductTypeEnum profitProductType, Date beginDate,
                                     Date endDate,int lowNum,int highNum) {
        if (profitProductType == null || beginDate == null || endDate == null) {
            throw new RuntimeException("profitProductType,beginDate,endDate 不能为空");
        }
        return reportorDao.selectByType(profitProductType,beginDate,endDate,lowNum,highNum);
    }

    /**
     * 查询所有数据
     *
     * @param profitProductType 毛利产品类型
     * @param beginDate         日期
     * @param endDate           日期
     * @return
     */
    @Override
    public int countProfits(ProfitProductTypeEnum profitProductType, Date beginDate, Date endDate) {
        if (profitProductType == null || beginDate == null || endDate == null) {
            throw new RuntimeException("profitProductType,beginDate,endDate 不能为空");
        }
        return reportorDao.countByType(profitProductType,beginDate,endDate);
    }
}
