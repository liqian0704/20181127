package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.service.profit.ProfitService;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.core.ymf.utils.page.Pager;
import com.yeepay.g3.core.ymf.vo.profit.RowMap;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description: 抽象类
 * @author: xiaobin.liu
 * @date: 17/12/14
 * @time: 下午2:59
 */
public abstract class AbstractProfitCalculator implements ProfitCalculator {
    private Logger logger = LoggerFactory.getLogger(AbstractProfitCalculator.class);
    @Autowired
    protected ProfitService profitService;
    @Autowired
    protected ProfitSummationService profitSummationService;

    /**
     * 蜜糖增量商户，支付给易宝0.1%
     */
    protected static BigDecimal MT_TRX_COST_RATE = new BigDecimal(0.00010).setScale(5, BigDecimal.ROUND_HALF_UP);
    /**
     * 蜜糖增量商户,每笔结算收取的费用
     */
    protected static BigDecimal MT_SETTLE_COST = new BigDecimal(0.1).setScale(2, BigDecimal.ROUND_HALF_UP);

    /**
     * 存量商户
     */
    @Override
    public int calculate(String month, boolean reCaculate) {
        ProfitSummation summation = querySummation(month,reCaculate);

        //如果已经是成功，直接不计算
        if (Status.SUCCESS == summation.getCalculateStatus()) {
            throw new RuntimeException("已经计算成功，无需再计算。");
        }

        int totalCount = 0 ;
        Status status = null;
        if (!reCaculate) {
            status = Status.INIT;
            totalCount = profitService.countByProductType(getProductType(), getCustomerType(), month, status);
        } else {
            //重算
            totalCount = summation.getTotalCount().intValue();
        }

        if (totalCount <= 0) {
            logger.info("没有需要计算的数据，全部已经计算完毕。");
            return 0;
        }

        //根据Id分页，先获取ID值
        Pager pager1 = new Pager(CfgConstant.MT_PROFIT_PAGE_COUNT(), totalCount);
        Set<Long> rownumSet = new HashSet<Long>();
        rownumSet.add(1L);//临界值
        rownumSet.add(Long.valueOf(pager1.getLowRowNum()));
        for (int loopFlag = pager1.getCurrentPage();loopFlag <= pager1.getPageTotal() ;pager1.pageDown(),loopFlag++) {
            rownumSet.add(Long.valueOf(pager1.getHighRowNum()));
        }
        Status calculateStatus = null;
        if (!reCaculate) {
            //如果不是重新计算，只查询未计算的记录。这里的重新计算是指，放弃之前已经计算过的结果。
            calculateStatus = Status.INIT;
        }
        List<RowMap> rownums = profitService.findRowMapByProductTypeAndRownum(getProductType(), getCustomerType(), month,
                calculateStatus, rownumSet);
        Map<Long, Long> rownumMap = new HashMap<Long, Long>();
        if (rownums != null && rownums.size() > 0) {
            for (RowMap rowMap : rownums) {
                if (rowMap.getRownum().longValue() == 1) {
                    //临界值问题
                    Long id = rowMap.getId();
                    if (id > 0) {
                        id = id - 1;
                    }
                    rownumMap.put(0L, id);
                } else {
                    rownumMap.put(rowMap.getRownum(), rowMap.getId());
                }
            }
        }

        //开始分页计算
        Pager pager = new Pager(CfgConstant.MT_PROFIT_PAGE_COUNT(), totalCount);
        try {
            for (int loopFlag = pager.getCurrentPage();loopFlag <= pager.getPageTotal() ;pager.pageDown(),loopFlag++) {
                if (rownumMap.get(Long.valueOf(pager.getLowRowNum())) == null) {
                    throw new RuntimeException("糟糕页码不存在。lowId");
                }
                if (rownumMap.get(Long.valueOf(pager.getHighRowNum())) == null) {
                    throw new RuntimeException("糟糕页码不存在。hignId");
                }
                int lowId = rownumMap.get(Long.valueOf(pager.getLowRowNum())).intValue();
                int hignId = rownumMap.get(Long.valueOf(pager.getHighRowNum())).intValue();
                //处理一页数据
                List<Profit> profits = queryProfit(month,reCaculate,lowId,hignId);
                if (profits != null && profits.size() > 0) {
                    int i = 0 ;
                    List<Profit> batchSaveList = new ArrayList<Profit>();
                    for (Profit profit : profits) {
                        i++ ;
                        //处理单条数据
                        if (!reCaculate && Status.SUCCESS == profit.getCalculateStatus()) {
                            //已经算过的不再算
                            continue;
                        }
                        calculateOne(summation,profit);
                        batchSaveList.add(profit);
                        //很操蛋，易宝的DalDataSource批更新有bug。所以只能一条条插入
                        if (batchSaveList.size() == CfgConstant.MT_PROFIT_BATCH_SAVE_COUNT()
                                || (i == profits.size() && batchSaveList.size() > 0)) {
                            //300条保存一次
                            profitService.batchUpdateUseJdbc(batchSaveList);
                            batchSaveList.clear();
                        }
                        //单条更新效率低
                        //profitService.update(profit);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("计算第" + pager.getCurrentPage() + "页时，异常",e);
        }
        return totalCount;
    }

    protected abstract ProfitProductTypeEnum getProductType() ;
    protected abstract CustomerTypeEnum getCustomerType() ;
    /**
     * 存量商户：分配比例
     * 增量商户：成本费率
     */
    protected abstract BigDecimal getPercent() ;

    /**
     * 计算一条费用
     * @param profit
     * @return
     */
    protected void calculateOne(ProfitSummation summation, Profit profit) {
        if (getCustomerType() == CustomerTypeEnum.MT) {
            calculateMtOne(summation,profit,getPercent());
        } else if (getCustomerType() == CustomerTypeEnum.STOCK
                || getCustomerType() == CustomerTypeEnum.STOCK2) {
            //蜜糖
            calculateStockOne(summation,profit,getPercent());
        }
    }

    /**
     * 按页查询毛利数据。
     * 注意:lowRowNum,highRowNum为ID值
     */
    protected List<Profit> queryProfit(String month, boolean reCaculate,int lowRowNum, int highRowNum) {
        Status calculateStatus = null;
        if (!reCaculate) {
            //如果不是重新计算，只查询未计算的记录。这里的重新计算是指，放弃之前已经计算过的结果。
            calculateStatus = Status.INIT;
        }
        List<Profit> byProductType = profitService.findByProductTypeAndIdPage(getProductType(),
                getCustomerType(), month,calculateStatus,lowRowNum,highRowNum);
        return byProductType;
    }

    /**
     *  查询汇总数据
     */
    protected ProfitSummation querySummation(String month,boolean reCaculate) {
        //先查询是否已经汇总过
        ProfitSummation localSummation = profitSummationService.findByProductType(getProductType(),
                getCustomerType(), month);
        if (localSummation != null && !reCaculate) {
            return localSummation ;
        }
        ProfitSummation summation = profitService.sumByProductType(getProductType(),
                getCustomerType(), month);

        if (summation == null) {
            //没有需要计算的数据,直接记录一条交易金额为0的
            summation = initEmptySummation(getProductType(),getCustomerType(),month);
        }
        BigDecimal totalTrxAmt = summation.getTotalTrxAmt();
        BigDecimal totalProfitAmt = summation.getTotalProfitAmt();

        if (CustomerTypeEnum.STOCK == getCustomerType()
                || CustomerTypeEnum.STOCK2 == getCustomerType()) {
            //存量商户按比例分配
            BigDecimal mtTotalTrxAmt = totalTrxAmt.multiply(getPercent());
            BigDecimal mtTotalProfitAmt = totalProfitAmt.multiply(getPercent());

            summation.setMitangTotalTrxamt(mtTotalTrxAmt);
            summation.setMitangTotalProfitAmt(mtTotalProfitAmt);
        } else if (CustomerTypeEnum.MT == getCustomerType()) {
            //蜜糖增量商户扣去成本
            BigDecimal mtTotalProfit = calculateMtProfit(totalTrxAmt, totalProfitAmt,
                    summation.getTotalTrxCount(),getPercent(),summation.getTotalDayCount());

            summation.setMitangTotalTrxamt(totalTrxAmt);
            summation.setMitangTotalProfitAmt(mtTotalProfit);
        }

        summation.setCalculateStatus(Status.INIT);

        //如果已经算过，并且需要重新计算，更新记录。
        if (localSummation != null && reCaculate) {
            updateLocalSummation(localSummation, summation);
            return localSummation;
        }
        profitSummationService.save(summation);
        return summation;
    }

    /**
     * [存量商户] 计算单个商户毛利和交易金额
     * @param summation
     * @param profit
     */
    protected void calculateStockOne(ProfitSummation summation, Profit profit,BigDecimal percent) {
        BigDecimal trxAmt = profit.getTrxAmt();
        BigDecimal profitAmt = profit.getProfitAmt();

        if (trxAmt == null) {
            trxAmt = Amount.getZeroAmount();
        }
        if (profitAmt == null) {
            profitAmt = Amount.getZeroAmount() ;
        }
        profit.setMitangTrxamt(trxAmt.multiply(percent));
        profit.setMitangProfitAmt(profitAmt.multiply(percent));

        profit.setSummationId(summation.getId());
        profit.setUpdateTime(new Date());
        profit.setCalculateStatus(Status.SUCCESS);
    }


    /**
     * 如果是已经算过，需要重新计算的，更新记录。
     */
    protected void updateLocalSummation(ProfitSummation localSummation, ProfitSummation summation) {
        localSummation.setTotalCount(summation.getTotalCount());
        localSummation.setTotalTrxCount(summation.getTotalTrxCount());
        localSummation.setTotalTrxAmt(summation.getTotalTrxAmt());
        localSummation.setTotalProfitAmt(summation.getTotalProfitAmt());
        localSummation.setMitangTotalTrxamt(summation.getMitangTotalTrxamt());
        localSummation.setMitangTotalProfitAmt(summation.getMitangTotalProfitAmt());
        localSummation.setCalculateStatus(Status.INIT);
        localSummation.setUpdateTime(new Date());
        localSummation.setPercent(summation.getPercent());
        profitSummationService.update(localSummation);
    }

    /**
     * 保存一个空的汇总信息。当某类产品没有交易数据时产生。
     */
    protected ProfitSummation initEmptySummation(ProfitProductTypeEnum profitType,
                                                 CustomerTypeEnum customerType,
                                                 String month) {
        ProfitSummation summation;
        summation = new ProfitSummation();
        summation.setProfitProductType(profitType);
        summation.setCustomerType(customerType);
        summation.setMonth(month);
        summation.setTotalCount(0L);
        summation.setTotalTrxCount(0L);
        summation.setTotalDayCount(0L);
        summation.setTotalTrxAmt(new BigDecimal(0));
        summation.setTotalProfitAmt(new BigDecimal(0));
        summation.setMitangTotalTrxamt(new BigDecimal(0));
        summation.setMitangTotalProfitAmt(new BigDecimal(0));
        summation.setCalculateStatus(Status.INIT);
        summation.setCreateTime(new Date());
        summation.setPercent(new BigDecimal(0));
        return summation;
    }

    /**
     * [增量商户] 计算蜜糖发展商户，一条数据的毛利计算。除了日结通外，其余三个产品可共用
     */
    protected void calculateMtOne(ProfitSummation summation, Profit profit, BigDecimal trxRate) {
        BigDecimal trxAmt = profit.getTrxAmt();
        BigDecimal profitAmt = profit.getProfitAmt();
        //单笔蜜糖交易金额
        profit.setMitangTrxamt(trxAmt);

        //如果是日结通，实际笔数计算，如果是其他按照商户的交易天数计算。
        BigDecimal mtProfit = calculateMtProfit(trxAmt, profitAmt, profit.getTotalTrxCount(),trxRate,profit.getDayCount());
        //单笔蜜糖毛利金额
        profit.setMitangProfitAmt(mtProfit);

        profit.setSummationId(summation.getId());
        profit.setUpdateTime(new Date());
        profit.setCalculateStatus(Status.SUCCESS);
    }

    /**
     *  [增量商户] 计算蜜糖发展的商户,给蜜糖的毛利：所有毛利 - 交易成本 - 结算成本
     * @param totalTrxAmt       总的交易金额
     * @param totalProfitAmt    未减去易宝成本前的毛利
     * @param totalTrxCount     实际交易笔数
     * @param trxRate           交易费率
     * @param dayCouont         实际交易天数
     * @return
     */
    protected BigDecimal calculateMtProfit(BigDecimal totalTrxAmt, BigDecimal totalProfitAmt,
                                           Long totalTrxCount,BigDecimal trxRate,Long dayCouont) {
        if (totalProfitAmt == null) {
            totalProfitAmt = new BigDecimal(0);
        }
        if (totalTrxAmt == null) {
            totalTrxAmt = new BigDecimal(0);
        }
        //所有毛利 - 交易成本 - 结算成本
        BigDecimal mitangTotalProfitAmt = null;
        // 交易成本
        BigDecimal trxCost = totalTrxAmt.multiply(trxRate);

        // 结算成本
        BigDecimal settleCost = null;
        if (getProductType() == ProfitProductTypeEnum.RJT_PROFIT && CustomerTypeEnum.MT == getCustomerType()) {
            //日结通的数据需要使用实际交易笔数 * MT_SETTLE_COST
            BigDecimal totalTrxCount1 = new BigDecimal(totalTrxCount);
            settleCost = totalTrxCount1.multiply(MT_SETTLE_COST);
        } else if (getProductType() == ProfitProductTypeEnum.AUTH_PROFIT) {
            //如果是认证信息 增量商户，不扣除成本
            settleCost = Amount.getZeroAmount();
        }  else {
            BigDecimal dayCount1 = new BigDecimal(dayCouont);
            settleCost = dayCount1.multiply(MT_SETTLE_COST);
        }

        mitangTotalProfitAmt = totalProfitAmt.subtract(trxCost).subtract(settleCost);
        return mitangTotalProfitAmt;
    }


    public static void main(String[] args) {

        //计算
        double num = 111231.5545;
        BigDecimal b = new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimal = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        //保留2位小数
        double result = b.doubleValue();
        System.out.println(result);  //111231.56
    }
}
