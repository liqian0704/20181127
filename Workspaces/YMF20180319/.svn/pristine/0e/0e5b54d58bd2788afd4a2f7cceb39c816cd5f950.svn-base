package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.facade.ProfitTimerFacade;
import com.yeepay.g3.facade.ymf.facade.PushProfitToAgentFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单定时
 * @Author: xiaobin.liu
 * @Date: 17/6/26
 * @Time: 下午4:02
 */
public class ProfitTimerFacadeTest extends AnnotationContextAwareTest {
    private ProfitTimerFacade profitTimerFacade;
    @Autowired
    private PushProfitToAgentFacade pushProfitToAgentFacade ;


    //1.来客存量   ProfitProductTypeEnum.LAKER_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //2.来客增量   ProfitProductTypeEnum.LAKER_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //3.易钱包存量   ProfitProductTypeEnum.E_WALLET_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //4.易钱包增量   ProfitProductTypeEnum.E_WALLET_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //5.日结通存量   ProfitProductTypeEnum.RJT_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //6.日结通增量   ProfitProductTypeEnum.RJT_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //7.收款宝存量   ProfitProductTypeEnum.SKB_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //7.收款宝存量2   ProfitProductTypeEnum.SKB_PROFIT.toString(), CustomerTypeEnum.STOCK2.toString()
    //8.收款宝增量   ProfitProductTypeEnum.SKB_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    /**
     * 【人工】 指定月份同步数据
     */
    @Test
    public void testSysnWithStream() {
        profitTimerFacade = getInnerFacade(ProfitTimerFacade.class);
        logger.info("-----------开始调用[内测]，同步数据。");
        profitTimerFacade.sysnWithStream("20180201","20180228");
        logger.info("-----------搞定");
    }

    //【人工计算】指定月份进行计算
    @Test
    public void testCalculateProfit() {
        profitTimerFacade = getInnerFacade(ProfitTimerFacade.class);
        logger.info("-----------开始调用[内测]，计算毛利。");
        profitTimerFacade.calculateProfit(null,
                null,"201802");
        logger.info("-----------搞定");
    }


    //********【人工】清除指定月份数据
    @Test
    public void testClearDatas() {
        try {
            profitTimerFacade = getInnerFacade(ProfitTimerFacade.class);
            logger.info("-----------开始调用[内测]，清空数据。");
            profitTimerFacade.clearSysDatas("201802");
            logger.info("-----------搞定");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //********【人工推送】
    @Test
    public void testPush() {
        pushProfitToAgentFacade = getInnerFacade(PushProfitToAgentFacade.class);
        logger.info("-----------开始调用[内测]，清楚数据。");
        pushProfitToAgentFacade.pushWithMoth("201802");
        logger.info("--------搞定 ");

    }




}
