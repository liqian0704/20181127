package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.core.ymf.junit.AnnotationContextAwareTest;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.facade.ymf.facade.ProfitTimerFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.junit.Test;

/**
 * @Description: 订单定时
 * @Author: xiaobin.liu
 * @Date: 17/6/26
 * @Time: 下午4:02
 */
public class ProfitTimerFacadeTest extends AnnotationContextAwareTest {
    private ProfitTimerFacade profitTimerFacade;


    //1.来客存量   ProfitProductTypeEnum.LAKER_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //2.来客增量   ProfitProductTypeEnum.LAKER_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //3.易钱包存量   ProfitProductTypeEnum.E_WALLET_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //4.易钱包增量   ProfitProductTypeEnum.E_WALLET_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //5.日结通存量   ProfitProductTypeEnum.RJT_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //6.日结通增量   ProfitProductTypeEnum.RJT_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //7.收款宝存量   ProfitProductTypeEnum.SKB_PROFIT.toString(), CustomerTypeEnum.STOCK.toString()
    //8.收款宝增量   ProfitProductTypeEnum.SKB_PROFIT.toString(), CustomerTypeEnum.MT.toString()

    //只跑上月数据
    @Test
    public void testTimerSysnWithStream() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.18.162.140:8080/ymf-hessian/hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.timerSysnWithStream();
    }

    @Test   //http://106.120.186.94:8080/内测  //http://172.18.162.140:8080/ymf-hessian/hessian/小斌本地
    public void testSysnWithStream() {
        profitTimerFacade = RemoteServiceFactory.getService("http://172.19.111.176:8080/ymf-hessian/hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        //腊月
        profitTimerFacade.sysnWithStream("20171201","20171231");
    }

    /**
     * 只跑上月数据
     */
    @Test
    public void testTimerCalculateProfit() {
        profitTimerFacade = RemoteServiceFactory.getService("http://106.120.186.94:8080/ymf-hessian/hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.timerCalculateProfit(ProfitProductTypeEnum.SKB_PROFIT.toString(),
                CustomerTypeEnum.MT.toString());
    }

    @Test//收款宝  //http://10.151.30.152:8083/ymf-hessian/hessian/ qa
    public void testCalculateProfit() {
        profitTimerFacade = RemoteServiceFactory.getService("http://106.120.186.94:8080/ymf-hessian/hessian/",
                RemotingProtocol.HESSIAN,ProfitTimerFacade.class);
        profitTimerFacade.calculateProfit(ProfitProductTypeEnum.LAKER_PROFIT.toString(),
                CustomerTypeEnum.STOCK.toString(),"201712");
    }


}
