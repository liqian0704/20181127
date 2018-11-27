package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.facade.ymf.facade.ProfitTimerFacade;
import com.yeepay.g3.facade.ymf.facade.PushProfitToAgentFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单定时
 * @Author: xiaobin.liu
 * @Date: 17/6/26
 * @Time: 下午4:02
 */
public class QaProfitTimerFacadeTest extends AnnotationContextAwareTest {
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

    //【定时】只同步上月数据
    @Test
    public void testTimerSysnWithStream() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.19.111.176:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.timerSysnWithStream();
    }

    /**
     * 【人工】 指定月份同步数据
     */
    @Test
    public void testSysnWithStream() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.19.111.176:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        //腊月
        profitTimerFacade.sysnWithStream("20180201","20180228");
    }

    /**
     * 【定时计算】只计算上月数据
     */
    @Test
    public void testTimerCalculateProfit() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.19.111.176:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.timerCalculateProfit(ProfitProductTypeEnum.LAKER_PROFIT.toString(),
                CustomerTypeEnum.STOCK.toString());
    }

    //"http://10.151.30.152:8083/ymf-hessian/"

    //【人工计算】指定月份进行计算
    @Test
    public void testCalculateProfit() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.19.111.176:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.calculateProfit(null,
                null,"201801");
    }


    //********【人工】清除指定月份数据
    @Test
    public void testClearDatas() {
        profitTimerFacade = RemoteServiceFactory.getService("http://localhost:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        try {
            profitTimerFacade.clearSysDatas("201802");
            logger.info("11111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //********【人工推送】
    @Test
    public void testPush() {
        //pushProfitToAgentFacade = RemoteServiceFactory.getService(PushProfitToAgentFacade.class);
        pushProfitToAgentFacade = RemoteServiceFactory.getService("http://localhost:8080/ymf-hessian/",
                RemotingProtocol.HESSIAN,PushProfitToAgentFacade.class);
        pushProfitToAgentFacade.pushWithMoth("201801");
        logger.info("--------end");

    }




}
