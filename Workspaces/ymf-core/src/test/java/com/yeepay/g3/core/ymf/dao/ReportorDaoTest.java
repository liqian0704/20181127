package com.yeepay.g3.core.ymf.dao;

import com.yeepay.g3.core.ymf.dao.profit.ReportorDao;
import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/20
 * @time: 下午4:03
 */
public class ReportorDaoTest extends AnnotationContextAwareTest {
    @Autowired
    private ReportorDao reportorDao;


//    List<Profit> selectByType(@Param("profitProductType") ProfitProductTypeEnum profitProductType,
//                              @Param("beginDate") Date beginDate,
//                              @Param("endDate") Date endDate,
//                              @Param("lowNum") int lowNum,
//                              @Param("highNum") int highNum);


    @Test
    public void testSelectByType() {
        Date beginDate = DateUtil.getStrToDate("2017-11-01", "YYYYMMDD");
        Date endDate = DateUtil.getStrToDate("2017-11-30", "YYYYMMDD");

        List<Profit> profits = reportorDao.selectByType(ProfitProductTypeEnum.SKB_PROFIT,
                beginDate, endDate, 1, 2);
        System.out.println(profits.size());
    }

}
