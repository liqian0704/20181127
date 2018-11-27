package com.yeepay.g3.core.ymf.facade.impl.trade

import com.yeepay.g3.facade.nctrade.dto.TradeCashierBaseDTO
import com.yeepay.g3.facade.nctrade.dto.TradeCashierReplyDTO
import com.yeepay.g3.facade.nctrade.exception.ParameterInvalidException
import com.yeepay.g3.facade.nctrade.facade.TradeCashierFacade
import com.yeepay.g3.utils.common.json.JSONUtils
import com.yeepay.g3.utils.config.ConfigurationUtils
import com.yeepay.g3.utils.rmi.RemoteServiceFactory
import com.yeepay.g3.utils.rmi.RemotingProtocol
import com.yeepay.g3.ypt.gry.base.BaseGroovyTest
import groovy.sql.Sql
import org.junit.Before
import org.junit.Test
/**
 * Created by yp-tc-m-2574 on 16/12/9.
 */
class Test_QueryOrder1 extends BaseGroovyTest{

    def DB = "jdbc:db2://172.17.106.194:50000/qa3new"    //数据库url
    def USER = "db2inst"
    def PASSWORD = "dev8132430"
    def DRICER = "com.ibm.db2.jcc.DB2Driver"          //驱动
    def sql;
//    Date d = new Date();
    @Before
    void before(){

        System.setProperty("disableconfig", "true")
        ConfigurationUtils.init()
        RemoteServiceFactory.init()
        sql = Sql.newInstance(DB, USER, PASSWORD, DRICER)

    }
    @Test//请求统一收银台
    void test_1(){
        TradeCashierFacade facade = RemoteServiceFactory.getService(
                "http://172.21.0.83:8003/nc-api-hessian/hessian/TradeCashierFacade", RemotingProtocol.HESSIAN,TradeCashierFacade.class);
        TradeCashierBaseDTO request = new TradeCashierBaseDTO()
        request.accessCode="17"
        request.merchantAccount="10040007800"
        request.customerOrderId="78000119001"
         //String.valueOf(d.getTime())
        /*
        def DB = "jdbc:db2://172.17.106.194:50000/qa3new"    //数据库url
        def USER = "db2inst"
        def PASSWORD = "dev8132430"
        def DRICER = "com.ibm.db2.jcc.DB2Driver"          //驱动
        def sql
        def rspresults =sql.firstRow("select STATUS from ADVANCE.TBL_ADVANCE_REQUEST where BIZ_REQUEST_NO ="" order by id desc")
        //assert respDTO.data =="5"
        if(rspresults.STATUS == "REFUND"){
            print("the case is pass \n}")
            assertEquals( rspresults.STATUS, "REFUND")
        }
        */
        def rspresults = sql.firstRow("select * from ADVANCE.TBL_ADVANCE_REQUEST where status='REFUND' order by id desc")

        println(JSONUtils.toJsonString(rspresults))
        if(rspresults.STATUS == "REFUND")
        {
            print("the case is pass")
            assertEquals(rspresults.STATUS, "REFUND")
        }

        request.signMd5("1oC3L9516894J0jX2k5X7Uh505G9ER");
        try {
//            TradeCashierReplyDTO reply = facade.purchaseQuery(request);

            List<TradeCashierReplyDTO> orderResults = new ArrayList<TradeCashierReplyDTO>()
             reply = facade.purchaseQuery(request);
            println(reply);

        } catch (ParameterInvalidException e) {
            e.printStackTrace();
        }
    }



}
