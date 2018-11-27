package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.alibaba.fastjson.JSONReader;
import com.yeepay.g3.core.ymf.biz.profit.ProfitBiz;
import com.yeepay.g3.core.ymf.biz.profit.ProfitCalculator;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.profit.Profit;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.service.profit.ProfitService;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.core.ymf.service.profit.ReportorService;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.email.MonitorNotify;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


/**
 * @description: 毛利同步和计算
 * @author: xiaobin.liu
 * @date: 17/12/13
 * @time: 下午5:39
 */
@Service
public class ProfitBizImpl implements ProfitBiz {

    private Logger logger = LoggerFactory.getLogger(ProfitBizImpl.class);
    /**
     * 非存量商户的标志  同步数据时使用
     */
    private static final String MT_SALE = "蜜堂" ;
    @Autowired
    private ReportorService reportorService;
    @Autowired
    private ProfitService profitService;
    @Autowired
    private ProfitSummationService profitSummationService;

    @Resource(name = "skbStockCalculator")
    private ProfitCalculator skbStockCalculator ;
    @Resource(name = "skbMtCalculator")
    private ProfitCalculator skbMtCalculator ;

    @Resource(name = "likerMtCalculator")
    private ProfitCalculator likerMtCalculator ;
    @Resource(name = "likerStockCalculator")
    private ProfitCalculator likerStockCalculator ;

    @Resource(name = "eWalletMtCalculator")
    private ProfitCalculator eWalletMtCalculator ;
    @Resource(name = "eWalletStockCalculator")
    private ProfitCalculator eWalletStockCalculator ;

    @Resource(name = "rjtMtCalculator")
    private ProfitCalculator rjtMtCalculator ;
    @Resource(name = "rjtStockCalculator")
    private ProfitCalculator rjtStockCalculator ;

    /**
     * 通过流的方式同步。
     * @param beginDate
     * @param endDate
     */
    @Override
    public void sysnWithStream(Date beginDate, Date endDate) {
        try {
            //邮件通知，日志打印
            StringBuilder sb = new StringBuilder();
            sb.append("[同步 MT毛利数据]");
            buildParamStr(sb, null,null, beginDate, endDate, null, null,null);
            logger.info("BEGIN 开始" + sb.toString());
            MonitorNotify.notifyEmail("开始" + sb.toString(),null);

            //http://10.149.200.104:8999/
            String dataUrlPrefix = CfgConstant.MT_PROFIT_DATA_URL();
            String urlSuffix = "doggie-engine/bigdata/v1/datainterface/bigdata/query/mitang-yeepay-share?";
            if (beginDate == null || endDate == null) {
                throw new RuntimeException("beginDate endDate can not be null");
            }
            String startDateStr = DateUtil.formatDate(beginDate, "yyyy-MM-dd");
            String endDateStr = DateUtil.formatDate(endDate, "yyyy-MM-dd");
            String paramString = "TRXDATE=" + startDateStr + "," + endDateStr;

            String startMonth = DateUtil.formatDate(beginDate, "yyyyMM");
            String endMonth = DateUtil.formatDate(endDate, "yyyyMM");
            if (startMonth != null && !startMonth.equals(endMonth)) {
                throw new RuntimeException("必须查询相同月份数据。不可跨月份。");
            }

            int saveCount = readFromUrl(dataUrlPrefix + urlSuffix + paramString, startMonth);

            //邮件通知，日志打印
            logger.info("SUCCESS 结束" + sb.toString());
            MonitorNotify.notifyEmail("SUCCESS 结束" + sb.toString() + ",共保存数据条数：" + saveCount,null);
        } catch (RuntimeException e) {
            //邮件通知，日志打印
            StringBuilder sb = new StringBuilder();
            sb.append("[同步 MT毛利数据]");
            buildParamStr(sb, null,null, beginDate, endDate, null, null,null);
            logger.info("Exception 异常结束" + sb.toString(),e);
            MonitorNotify.notifyEmail("异常结束" + sb.toString(),e);
            throw e;
        }
    }

    /**
     * 请求接口，同步数据、
     * @param url       地址
     * @param month     月份
     */
    private int readFromUrl(String url,String month) {
        //http://10.148.170.103:8099/doggie-engine/bigdata/v1/datainterface/bigdata/query/mitang-yeepay-share?TRXDATE=2017-12-01,2017-12-01
        //String url = "http://10.148.170.103:8099/doggie-engine/bigdata/v1/datainterface/bigdata/query/mitang-yeepay-share?TRXDATE=2017-12-01,2017-12-01";
        try {
            logger.info("请求地址：" + url);
            URL destURL = new URL(url);

            HttpURLConnection urlConn = (HttpURLConnection) destURL.openConnection();
            urlConn.setRequestProperty("Content-Type",
                    "text/x-www-form-urlencoded; charset=utf-8");
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setConnectTimeout(CfgConstant.MT_PROFIT_SYS_CONNECT_TIMEOUT());//300秒连接时间  可以放到统一配置
            urlConn.setReadTimeout(CfgConstant.MT_PROFIT_SYS_READ_TIMEOUT());//3000秒读取时间 可以放到统一配置
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("GET");

            int responseCode = urlConn.getResponseCode();
            if (responseCode != 200) {
                logger.error("请求失败，responseCode：" + responseCode);
                throw new RuntimeException("请求失败，responseCode：" + responseCode);
            }
            //开始解析数据流
            BufferedInputStream is = new BufferedInputStream(urlConn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            JSONReader jsonReader = new JSONReader(br);
            //解析并保存
            logger.info("开始解析返回数据");
            int saveCount = parseJsonAndSave(jsonReader, month);
            is.close();
            br.close();
            jsonReader.close();
            return saveCount;
        } catch (Exception e) {
            logger.error("请求数据同步接口失败:",e);
            throw new RuntimeException("请求数据同步接口失败",e);
        }
    }

    /**
     * 解析数据，并保存。
     * @param jsonReader
     */
    private int parseJsonAndSave(JSONReader jsonReader,String month) {
        jsonReader.startObject();//result
        int recordCount = 0 ;
        int saveCount = 0 ;
        while (jsonReader.hasNext()) {
            String result = jsonReader.readString();
            if ("result".equals(result)) {
                jsonReader.startArray();
                List<Profit> rsList = new ArrayList<Profit>();
                Set<String> uniqueIdSet = new HashSet<String>();
                while (jsonReader.hasNext()) {
                    jsonReader.startObject();//这边反序列化也是极速
                    Profit profit = new Profit();
                    while (jsonReader.hasNext()) {
                        String itemKey = jsonReader.readString();
                        Object o = jsonReader.readObject();
                        String itemValue = null;
                        if (o != null) {
                            itemValue = String.valueOf(o);
                        }
                        //填充指定属性
                        fitObejectProperty(itemKey, itemValue, profit);
                    }
                    //填充其他属性
                    completeObjectExtProperty(profit, month);

                    jsonReader.endObject();
                    //增加一个对象
                    uniqueIdSet.add(profit.getUniqueId());
                    recordCount++ ;
                    rsList.add(profit);
                    if (rsList.size() == CfgConstant.MT_PROFIT_BATCH_SAVE_COUNT()) {
                        //300条一个批次，保存
                        saveCount += batchSaveProfit(uniqueIdSet, rsList);
                    }

                }
                //重要 最后再保存一次
                if (rsList.size() > 0) {
                    saveCount += batchSaveProfit(uniqueIdSet,rsList);
                }
                jsonReader.endArray();
                logger.info("本次共同步数据总数:" + recordCount + "，保存总数：" + saveCount);
            } else if ("status".equals(result)) {
                //"status":"success","message":"操作成功","queryTime":9992,"version":"bigdata v1.0.0"}
                String statusVal = jsonReader.readString();
                logger.info("status:" + statusVal);
            } else {
                jsonReader.readString();
            }
        }
        jsonReader.endObject();

        //同步完所有数据，插入一条汇总。计算前，进行校验。
        ProfitSummation summation = new ProfitSummation();
        summation.setProfitProductType(ProfitProductTypeEnum.ALL);
        summation.setCustomerType(CustomerTypeEnum.ALL);
        summation.setMonth(month);
        summation.setTotalCount(Long.valueOf(recordCount));//统计需要同步保存的总记录数
        summation.setTotalTrxCount(0L);
        summation.setTotalDayCount(0L);
        summation.setTotalTrxAmt(new BigDecimal(0));
        summation.setTotalProfitAmt(new BigDecimal(0));
        summation.setMitangTotalTrxamt(new BigDecimal(0));
        summation.setMitangTotalProfitAmt(new BigDecimal(0));
        summation.setCalculateStatus(Status.SUCCESS);
        summation.setCreateTime(new Date());
        summation.setPercent(new BigDecimal(0));
        profitSummationService.save(summation);

        return saveCount;
    }

    /**
     * 填充其他属性
     */
    private void completeObjectExtProperty(Profit profit,String month) {
        String uniqueId = profit.getCustomerNumber() + month
                + profit.getSalesProductCode() + profit.getProductCode();
        //uniqueIdSet.add(uniqueId);
        profit.setUniqueId(uniqueId);
        //Month
        profit.setMonth(month);

        if (MT_SALE.equals(profit.getSale())) {
            profit.setCustomerType(CustomerTypeEnum.MT);
        } else {
            profit.setCustomerType(CustomerTypeEnum.STOCK);
        }
        profit.setProfitProductType(transferProfitProduct(profit.getSalesProductCode(),profit.getProductCode()));
        profit.setCalculateStatus(Status.INIT);
        profit.setCreateTime(new Date());
    }

    /**
     * 适配属性
     */
    private void fitObejectProperty(String itemKey, String itemValue, Profit profit) {
        //{"SALES_PRODUCT_CODE":"3011004001004A","PROFIT":-1.1400,"DAYCOUNT":1,
        // "SALESNAME":"陈德鲍","TRXAMOUNT":10129.0600,"PRODUCT_CODE":"3011001003001",
        // "TRXCOUNT":4.0000,"CUSTOMERNUMBER":"10001674445","AGENTCODE":"10001139003"}
        if ("SALES_PRODUCT_CODE".equals(itemKey)) {
            profit.setSalesProductCode(itemValue);
        } else if ("PROFIT".equals(itemKey)) {
            profit.setProfitAmt(new BigDecimal(itemValue));
        } else if ("DAYCOUNT".equals(itemKey)) {
            profit.setDayCount(Long.valueOf(itemValue));
        } else if ("SALESNAME".equals(itemKey)) {
            profit.setSale(itemValue);
        } else if ("TRXAMOUNT".equals(itemKey)) {
            profit.setTrxAmt(new BigDecimal(itemValue));
        } else if ("PRODUCT_CODE".equals(itemKey)) {
            profit.setProductCode(itemValue);
        } else if ("TRXCOUNT".equals(itemKey)) {
            profit.setTotalTrxCount(new BigDecimal(itemValue).longValue());
        } else if ("CUSTOMERNUMBER".equals(itemKey)) {
            profit.setCustomerNumber(itemValue);
        } else if ("AGENTCODE".equals(itemKey)) {
            profit.setAgentNumber(itemValue);
        }
    }

    /**
     * 转换匹配对应的产品类型
     * @return
     */
    private static ProfitProductTypeEnum transferProfitProduct(String saleCode,String productCode) {
        if ("3011004001004A".equals(saleCode)) {
            if ("3011101001001".equals(productCode) || "3011004003002".equals(productCode)
                    || "3011004003001".equals(productCode)) {
                return ProfitProductTypeEnum.RJT_PROFIT;
            } else {
                return ProfitProductTypeEnum.SKB_PROFIT;
            }
        } else if ("3011001001003A".equals(saleCode)) {
            return ProfitProductTypeEnum.LAKER_PROFIT;
        } else if ("3011001001001F".equals(saleCode)) {
            return ProfitProductTypeEnum.E_WALLET_PROFIT;
        }
        return null ;
    }


    public static void main(String[] args) {
//        String url = "http://10.148.170.103:8099/doggie-engine/bigdata/v1/datainterface/bigdata/query/" +
//                "mitang-yeepay-share?TRXDATE=2017-12-01,2017-12-01";
//        ProfitBizImpl profitBiz = new ProfitBizImpl();
//        profitBiz.readFromUrl(url,"201711");
        Date lastMonthFirstDay = DateUtil.getLastMonthFirstDay(null);
        Date lastMonthLastDay = DateUtil.getLastMonthLastDay(null);
        System.out.println(DateUtil.formatDate(lastMonthFirstDay,"yyyyMMdd"));
        System.out.println(DateUtil.formatDate(lastMonthLastDay,"yyyyMMdd"));
    }


    /**
     * 参数日志的拼接
     */
    private StringBuilder buildParamStr(StringBuilder sb,ProfitProductTypeEnum profitType,CustomerTypeEnum customerType,
                                        Date beginDate, Date endDate,
                                        String month,Integer lowNum, Integer highNum) {
        if (sb == null) {
            return null;
        }
        if (profitType != null) {
            sb.append(" profitType:").append(profitType);
        }
        if (customerType != null) {
            sb.append(" customerType:").append(customerType);
        }
        if (beginDate != null) {
            sb.append("beginDate:").append(DateUtil.formatDate(beginDate, DateUtil.TIME_PATTERN_SESSION));
        }
        if (endDate != null) {
            sb.append(",endDate:").append(DateUtil.formatDate(endDate, DateUtil.TIME_PATTERN_SESSION));
        }
        if (month != null) {
            sb.append(",month:").append(month);
        }
        if (lowNum != null) {
            sb.append(",lowNum:").append(lowNum);
        }
        if (highNum != null) {
            sb.append(",highNum:").append(highNum);
        }
        return sb;
    }

    /**
     * 批量保存同步数据
     * @param uniqueIdSet   唯一键
     * @param profits       待保存数据
     */
    private int batchSaveProfit(Set<String> uniqueIdSet, List<Profit> profits) {
        int saveCount = 0 ;
        //通过所有的uniqueId查询本地库是否已经存在记录,已经存在不保存
        List<Profit> needSave = new ArrayList<Profit>();
        Set<String> localSet = profitService.findUniqueIdByUniqueIdSet(uniqueIdSet);
        if (localSet != null && localSet.size() >= 0) {
            for (Profit profit:profits) {
                if (!localSet.contains(profit.getUniqueId())) {
                    needSave.add(profit);
                }
            }
        }
        //批量保存
        if (needSave.size() > 0) {
            saveCount = needSave.size();
            //logger.info("本次需要保存：" + needSave.size());
            profitService.batchSave(needSave);
        }
        //保存成功，清空数据容器
        uniqueIdSet.clear();
        profits.clear();
        return saveCount ;
    }

    /**
     * 计算毛利 指定计算周期
     *
     * @param profitType
     */
    @Override
    public void calculate(ProfitProductTypeEnum profitType,CustomerTypeEnum customerType, String month
            ,boolean reCaculate) {
        if (profitType == null || customerType == null) {
            throw new RuntimeException("profitType,customerTypeEnum can't be null" );
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[计算MT毛利数据]");
        buildParamStr(sb, profitType, customerType,null, null, month, null,null);

        //计算
        try {
            logger.info("BEGIN 开始" + sb.toString());
            MonitorNotify.notifyEmail("BEGIN 开始" + sb.toString(),null);

            //计算前，校验数据是否已经同步完成
            ProfitSummation sysData = profitSummationService.findByProductType(ProfitProductTypeEnum.ALL, CustomerTypeEnum.ALL, month);
            if (sysData == null) {
                throw new RuntimeException("数据未同步完成，需要先同步数据。");
            }

            ProfitCalculator calculatetor = getObeject(profitType,customerType);
            int calculateCount = calculatetor.calculate(month, reCaculate);

            //设置为计算完毕
            ProfitSummation summation = profitSummationService.findByProductType(profitType, customerType, month);

            sb.append("\n汇总记录\nSummation：").append(JSONUtils.toJsonString(summation));
            //汇总明细表的数据
            ProfitSummation summation1 = profitService.sumByProductType(profitType, customerType, month,Status.SUCCESS);
            sb.append("\n从明细表汇总的数据为:\nSummation：").append(JSONUtils.toJsonString(summation1));

            BigDecimal mitangTotalTrxamt = summation.getMitangTotalTrxamt();
            BigDecimal mitangTotalProfitAmt = summation.getMitangTotalProfitAmt();
            BigDecimal mitangTotalTrxamt1 = null;
            BigDecimal mitangTotalProfitAmt1 = null;
            Long totalCount1 = 0L;
            if (summation1 != null) {
                mitangTotalTrxamt1 = summation1.getMitangTotalTrxamt();
                mitangTotalProfitAmt1 = summation1.getMitangTotalProfitAmt();
                totalCount1 = summation1.getTotalCount();
            }

            if (mitangTotalTrxamt1 == null) {
                mitangTotalTrxamt1 = new BigDecimal(0);
            }
            if (mitangTotalProfitAmt1 == null) {
                mitangTotalProfitAmt1 = new BigDecimal(0);
            }

            if (mitangTotalTrxamt.compareTo(mitangTotalTrxamt1) != 0
                    || mitangTotalProfitAmt.compareTo(mitangTotalProfitAmt1) != 0) {
                sb.append("\n【注意：】金额核对结果貌似有些不一致！请人工确认。\n");
            }

            if (summation.getTotalCount().longValue() == totalCount1.longValue()) {
                summation.setCalculateStatus(Status.SUCCESS);
                profitSummationService.update(summation);
                sb.append("\n明细笔数核对一致！已经更新计算状态");
            } else {
                sb.append("\n【注意：】明细笔数核对结果貌似有些不一致！请人工确认。计算状态未更新。\n");
            }

            logger.info("SUCCESS 结束" + sb.toString() + "，本次共处理：" + calculateCount);
            //汇总计算结果，并通知。
            MonitorNotify.notifyEmail("SUCCESS 结束" + sb.toString() + "，本次共处理：" + calculateCount,
                    null);
        } catch (Exception e) {
            logger.error("Exception 异常结束：" + sb.toString(),e);
            MonitorNotify.notifyEmail("异常结束" + sb.toString(),e);
        }
    }

    /**
     * 获取计算类
     */
    public ProfitCalculator getObeject(ProfitProductTypeEnum productType,CustomerTypeEnum customerType) {
        //计算开始
        switch (productType) {
            case SKB_PROFIT:
                if (customerType == CustomerTypeEnum.STOCK) {
                    return skbStockCalculator;
                }
                return skbMtCalculator;
            case LAKER_PROFIT:
                if (customerType == CustomerTypeEnum.STOCK) {
                    return likerStockCalculator;
                }
                return likerMtCalculator;
            case RJT_PROFIT:
                if (customerType == CustomerTypeEnum.STOCK) {
                    return rjtStockCalculator;
                }
                return rjtMtCalculator;
            case E_WALLET_PROFIT:
                if (customerType == CustomerTypeEnum.STOCK) {
                    return eWalletStockCalculator;
                }
                return eWalletMtCalculator;
            default:return null;
        }
    }
}
