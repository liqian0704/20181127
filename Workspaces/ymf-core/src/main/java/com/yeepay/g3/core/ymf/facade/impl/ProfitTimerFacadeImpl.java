package com.yeepay.g3.core.ymf.facade.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitBiz;
import com.yeepay.g3.core.ymf.service.profit.ProfitService;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.facade.ymf.facade.ProfitTimerFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum.MT;
import static com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum.STOCK;
import static com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum.STOCK2;
import static com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum.*;

/**
 * @description:
 * @author: xiaobin.liu
 * @date: 17/12/13
 * @time: 下午5:37
 */
@Service
public class ProfitTimerFacadeImpl implements ProfitTimerFacade {
    private Logger logger = LoggerFactory.getLogger(ProfitTimerFacadeImpl.class);
    @Autowired
    private ProfitBiz profitBiz ;
    @Autowired
    private ProfitSummationService profitSummationService;
    @Autowired
    private ProfitService profitService;

    /**
     * 定时调用
     */
    @Override
    public void timerCalculateProfit(String productTypeStr,
                                     String customerTypeStr) {
        //暂时不开放此方法
        Date lastMonthFirstDay = DateUtil.getLastMonthFirstDay(null);
        String yyyyMM = DateUtil.formatDate(lastMonthFirstDay, "yyyyMM");
        //定时默认不重新计算。
        calculateProfit(productTypeStr, customerTypeStr, yyyyMM);
    }

    /**
     * 定时调用。只支持续算.
     */
    @Override
    public void calculateProfit(String productTypeStr,
                                     String customerTypeStr,
                                     String month) {
        //定时默认不重新计算。
        calculateProfitWithRecaculate(productTypeStr, customerTypeStr, month, false);
    }

    /**
     * 蜜糖毛利分润计算;支持重算。注意：重算之后，后期即使失败，也不能再按续算，因为数据状态已经乱了，必须全部重算。
     * 1.month:YYYYMM
     * @param reCaculate 重新计算，意味着之前计算的数据发生变化。汇总和已经计算过的也会重新计算。
     */
    @Override
    public void calculateProfitWithRecaculate(String productTypeStr,
                                String customerTypeStr,
                                String month, Boolean reCaculate) {
        if (StringUtils.isBlank(month) || "now".equals(month)) {
            month = DateUtil.formatDate(new Date(), "yyyyMM");
        } else {
            Date date = DateUtil.getStrToDate(month, "yyyyMM");
            if (date == null) {
                throw new RuntimeException("The param 'month' is invalid !");
            }
        }
        ProfitProductTypeEnum productType = ProfitProductTypeEnum.safeParse(productTypeStr);
        CustomerTypeEnum customerType = CustomerTypeEnum.safeParse(customerTypeStr);
        if (productType == null || ProfitProductTypeEnum.ALL.equals(productType)) {
            //收款宝存量
            excuteTask(SKB_PROFIT,STOCK, month,reCaculate);
            //收款宝存量2
            excuteTask(SKB_PROFIT,STOCK2, month,reCaculate);
            //收款宝增量
            excuteTask(SKB_PROFIT,MT,month,reCaculate);
            //来客存量
            excuteTask(LAKER_PROFIT,STOCK,month,reCaculate);
            //来客增量
            excuteTask(LAKER_PROFIT,MT,month,reCaculate);
            //易钱包存量
            excuteTask(E_WALLET_PROFIT,STOCK,month,reCaculate);
            //易钱包增量
            excuteTask(E_WALLET_PROFIT,MT,month,reCaculate);
            //日结通存量
            excuteTask(RJT_PROFIT,STOCK,month,reCaculate);
            //日结通增量
            excuteTask(RJT_PROFIT,MT,month,reCaculate);

        } else {
            //执行某个产品的毛利。
            if (customerType == null || CustomerTypeEnum.ALL.equals(customerType)) {
                //存量
                excuteTask(productType,STOCK,month,reCaculate);
                //增量
                excuteTask(productType,MT,month,reCaculate);
            } else {
                //目前只让走这个分支，多个线程一起跑数据量大会出问题
                excuteTask(productType,customerType,month,reCaculate);
            }
        }
    }

    /**
     * 启动线程任务进行计算
     */
    private void excuteTask(final ProfitProductTypeEnum productType,
                            final CustomerTypeEnum customerType,
                            final String month, final boolean reCaculate) {
        singleExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                profitBiz.calculate(productType,customerType, month,reCaculate);
            }
        });
    }

    @Override
    public void timerSysnWithStream() {
        Date date = new Date();
        //默认同步上个月份的
        //获取上个月份月份的最后一天和第一天.
        Date lastMonthFirstDay = DateUtil.getLastMonthFirstDay(null);
        Date lastMonthLastDay = DateUtil.getLastMonthLastDay(null);
        sysnWithStream(DateUtil.formatDate(lastMonthFirstDay,"yyyyMMdd"),
                DateUtil.formatDate(lastMonthLastDay,"yyyyMMdd"));
    }

    @Override
    public void sysnWithStream(String beginDate, String endDate) {
        Date begin = DateUtil.getStrToDate(beginDate, "yyyyMMdd");
        Date end = DateUtil.getStrToDate(endDate, "yyyyMMdd");

        if (Long.valueOf(beginDate) > Long.valueOf(endDate)) {
            throw new RuntimeException("开始时间不能大于结束时间。");
        }
        sysnWithStreamByDate(begin, end);
    }


    /**
     * 单线程池
     */
    private static ExecutorService singleExecutorService;
    static {
        singleExecutorService = Executors.newSingleThreadExecutor();
    }

    /**
     * 启用线程，进行数据同步
     */
    private void sysnWithStreamByDate(final Date beginDate, final Date endDate) {
        singleExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                profitBiz.sysnWithStream(beginDate,endDate);
            }
        });
    }

    /**
     * 删除指定月份的数据，包括已经计算数据。【危险操作，谨慎使用】
     */
    @Override
    public void clearSysDatas(String month) {
        profitBiz.clearSysDatas(month);
    }

}
