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
import com.yeepay.g3.core.ymf.vo.profit.SysnResult;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.CustomerTypeEnum;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.utils.common.StringUtils;
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
    private static final String MT_SALE = "蜜堂007" ;
    @Autowired
    private ReportorService reportorService;
    @Autowired
    private ProfitService profitService;
    @Autowired
    private ProfitSummationService profitSummationService;

    @Resource(name = "skbStockCalculator")
    private ProfitCalculator skbStockCalculator ;
    @Resource(name = "skbStock2Calculator")
    private ProfitCalculator skbStock2Calculator ;
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

    @Resource(name = "authMtCalculator")
    private ProfitCalculator authMtCalculator ;
    @Resource(name = "authStockCalculator")
    private ProfitCalculator authStockCalculator ;

    //收款宝存量商户的商户编号
    private static Map<String,String> SKB_STOCK_CUSTOMER = new HashMap<String,String>();
    static {
        SKB_STOCK_CUSTOMER.put("10015705103","厦门万华顺昌信息科技有限公司");
        SKB_STOCK_CUSTOMER.put("10015643960","福建省通汇信息技术有限公司");
        SKB_STOCK_CUSTOMER.put("10015703385","上海莘丽网络信息有限公司");
        SKB_STOCK_CUSTOMER.put("10015642946","波士易客（上海）信息科技有限公司");
        SKB_STOCK_CUSTOMER.put("10015528553","深圳福同享科技有限公司");
        SKB_STOCK_CUSTOMER.put("10014197223","厦门辰亿鑫信息科技有限公司");
        SKB_STOCK_CUSTOMER.put("10013534483","上海秉垚网络信息服务有限公司");
        SKB_STOCK_CUSTOMER.put("10012437746","福州聚信网络技术有限公司");
        SKB_STOCK_CUSTOMER.put("10015570955","融口碑（福建）信息技术服务有限公司");
        SKB_STOCK_CUSTOMER.put("10015542332","上海掌玺网络科技有限公司");
        SKB_STOCK_CUSTOMER.put("10014834191","南京旭钥电子商务有限公司");
        SKB_STOCK_CUSTOMER.put("10015554965","福建银丰金融服务有限公司");
        SKB_STOCK_CUSTOMER.put("10012647313","福建平潭自贸区瑞诚科技有限公司");
        SKB_STOCK_CUSTOMER.put("10015571742","全民微店（厦门）网络科技有限公司");
        SKB_STOCK_CUSTOMER.put("10012478197","上海邑仓电子科技有限公司");
        SKB_STOCK_CUSTOMER.put("10014221185","福建付联信息科技有限公司");
        SKB_STOCK_CUSTOMER.put("10012555213","深圳市天下谷电子商务有限公司");
        SKB_STOCK_CUSTOMER.put("10012526306","重庆兴手付科技发展股份有限公司");
        SKB_STOCK_CUSTOMER.put("10012445933","成都融贝财商文化传播有限公司");
        SKB_STOCK_CUSTOMER.put("10012643649","北京上京电子科技有限公司");
        SKB_STOCK_CUSTOMER.put("10012593531","重庆二码信息技术有限公司");
        SKB_STOCK_CUSTOMER.put("10015688606","北京环城万家超市连锁有限公司");
        SKB_STOCK_CUSTOMER.put("10015660997","上海前赢电子商务有限公司");
        SKB_STOCK_CUSTOMER.put("10015712861","厦门微辰金融技术服务有限公司");
        SKB_STOCK_CUSTOMER.put("10015843908","厦门汇享天成金融技术服务有限公司");
    }

    /**
     * 通过流的方式同步。
     * @param beginDate
     * @param endDate
     */
    @Override
    public void sysnWithStream(Date beginDate, Date endDate) {
        try {
            if (beginDate == null || endDate == null) {
                throw new RuntimeException("beginDate endDate can not be null");
            }
            //邮件通知，日志打印
            StringBuilder sb = new StringBuilder();
            sb.append("[同步 MT毛利数据]");
            buildParamStr(sb, null,null, beginDate, endDate, null, null,null);
            logger.info("BEGIN 开始" + sb.toString());
            MonitorNotify.notifyEmail("开始" + sb.toString(),null);

            String startMonth = DateUtil.formatDate(beginDate, "yyyyMM");
            String endMonth = DateUtil.formatDate(endDate, "yyyyMM");
            if (startMonth != null && !startMonth.equals(endMonth)) {
                throw new RuntimeException("必须查询相同月份数据。不可跨月份。");
            }

            //http://10.149.200.104:8999/
            String dataUrlPrefix = CfgConstant.MT_PROFIT_DATA_URL();
            String urlSuffix = "doggie-engine/bigdata/v1/datainterface/bigdata/query/mitang-yeepay-share?";

            int saveCount = 0;
            int recordCount = 0;


            //分多次进行接口请求
            int start = Integer.valueOf(DateUtil.formatDate(beginDate, "yyyyMMdd"));
            int end = Integer.valueOf(DateUtil.formatDate(endDate, "yyyyMMdd"));

            //days      总涉及天数，一般来说，一个月的days是固定的。
            int days = end - start + 1;
            //每次天数   percent
            int timeDay = CfgConstant.MT_PROFIT_REQUEST_DAYS();
            //次数       根据总天数和每次天数算出来的策略
            int times = requestTimes(days,timeDay);
            logger.info("本次共需同步次数：" + times);
            //当前已跑到的次数。只要days，每天次数任意一个变化，就从零开始。保证数据完整。
            int currTime = 0;
            ProfitSummation summation = profitSummationService.findByProductType(ProfitProductTypeEnum.ALL, CustomerTypeEnum.ALL, startMonth);
            if (summation != null) {
                if (summation.getCalculateStatus() == Status.SUCCESS) {
                    throw new RuntimeException("这个月的数据，都同步完了，不需要再同步。");
                } else {
                    //如果之前已经同步过，并且未同步完，同步之前  清除所有数据
                    clearSysDatas(endMonth);
                }
            }
            summation = null ;
            if (summation == null) {
                //分次请求
                summation = new ProfitSummation();
                summation.setProfitProductType(ProfitProductTypeEnum.ALL);
                summation.setCustomerType(CustomerTypeEnum.ALL);
                summation.setMonth(startMonth);
                summation.setTotalCount(0L);//统计需要同步保存的总记录数
                summation.setTotalTrxCount(Long.valueOf(currTime));//已完成次数
                summation.setTotalDayCount(Long.valueOf(days));//请求次数
                summation.setTotalTrxAmt(new BigDecimal(times));//一共需要的次数
                summation.setTotalProfitAmt(new BigDecimal(0));
                summation.setMitangTotalTrxamt(new BigDecimal(0));
                summation.setMitangTotalProfitAmt(new BigDecimal(0));
                summation.setCalculateStatus(Status.INIT);
                summation.setCreateTime(new Date());
                summation.setTotalProfitAmt(new BigDecimal(timeDay));//每次请求的天数
                profitSummationService.save(summation);
                logger.info("Save summation success");
            } else {
                //不支持 续传，所以，以下代码不让执行。
                int currTime2 = summation.getTotalTrxCount().intValue();
                int days2 = summation.getTotalDayCount().intValue();
                //int times2 = summation.getTotalTrxAmt().intValue();
                int timeDay2 = summation.getTotalProfitAmt().intValue();
                if (days != days2 || timeDay2 != timeDay) {
                    //只要传进来的天数和每天执行数变化，从第一天开始同步。遗留一个问题，假设传入的时间天数一样，但是起始时间不同（暂不考虑此情况）
                    summation.setTotalCount(0L);//统计需要同步保存的总记录数
                    summation.setTotalTrxCount(Long.valueOf(currTime));//已完成次数
                    summation.setTotalDayCount(Long.valueOf(days));//请求次数
                    summation.setTotalTrxAmt(new BigDecimal(times));//一共需要的次数
                    summation.setTotalProfitAmt(new BigDecimal(timeDay));//没次请求的天数
                    profitSummationService.update(summation);
                } else {
                    if (summation.getTotalCount() != null) {
                        saveCount = summation.getTotalCount().intValue();
                    }
                    //从已经同步的位置继续同步
                    currTime = currTime2;
                }
            }

            for (int i = currTime + 1; i <= times ;i++) {

                int startDay = start + (i - 1) * timeDay;
                int endDay = startDay + (timeDay - 1);
                if (endDay > end) {
                    endDay = end;
                }
                Date reqStartDate = DateUtil.getStrToDate("" + startDay, "yyyyMMdd");
                Date reqEndDate = DateUtil.getStrToDate("" + endDay, "yyyyMMdd");
                String startDateStr = DateUtil.formatDate(reqStartDate, "yyyy-MM-dd");
                String endDateStr = DateUtil.formatDate(reqEndDate, "yyyy-MM-dd");
                String paramString = "TRXDATE=" + startDateStr + "," + endDateStr;
                logger.info("开始同步完第" + i + "次，时间范围：" + paramString);

                SysnResult sysnResult = readFromUrl(dataUrlPrefix + urlSuffix + paramString, startMonth);
                saveCount += sysnResult.getSavecount();
                recordCount += sysnResult.getTotalcount();
                summation.setTotalCount(Long.valueOf(saveCount));
                summation.setTotalTrxCount(Long.valueOf(i));
                profitSummationService.update(summation);
                logger.info("已经同步完第" + i + "次，时间范围：" + paramString);
            }
            //同步完所有数据，更新状态。计算前，进行校验。
            summation.setCalculateStatus(Status.SUCCESS);
            profitSummationService.update(summation);

            //邮件通知，日志打印
            logger.info("SUCCESS 结束" + sb.toString());
            MonitorNotify.notifyEmail("SUCCESS 结束" + sb.toString() + ",共保存数据条数：" + saveCount
                    + " ",null);
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
     * 计算一共需要请求几次接口
     */
    private int requestTimes(int days,int timeDay) {
        int j = days % timeDay;
        int i = days / timeDay;
        if (j == 0) {
            return i;
        } else {
            return i + 1;
        }
    }

    /**
     * 请求接口，同步数据、
     * @param url       地址
     * @param month     月份
     */
    private SysnResult readFromUrl(String url, String month) {
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
            SysnResult result = parseJsonAndSave(jsonReader, month);
            is.close();
            br.close();
            jsonReader.close();
            return result;
        } catch (Exception e) {
            logger.error("请求数据同步接口失败:",e);
            throw new RuntimeException("请求数据同步接口失败",e);
        }
    }

    /**
     * 解析数据，并保存。
     * @param jsonReader
     */
    private SysnResult parseJsonAndSave(JSONReader jsonReader,String month) {
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
        SysnResult result = new SysnResult();
        result.setTotalcount(recordCount);
        result.setSavecount(saveCount);
        return result;
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
        profit.setProfitProductType(transferProfitProduct(profit.getSalesProductCode(),profit.getProductCode()));
        if (MT_SALE.equals(profit.getSale())) {
            profit.setCustomerType(CustomerTypeEnum.MT);
        } else {
            if (profit.getProfitProductType() == ProfitProductTypeEnum.SKB_PROFIT) {
                //分为存量和存量2
                if (SKB_STOCK_CUSTOMER.containsKey(profit.getCustomerNumber())) {
                    //如果为标记的25家商户，按照STOCK计算
                    profit.setCustomerType(CustomerTypeEnum.STOCK);
                } else {
                    profit.setCustomerType(CustomerTypeEnum.STOCK2);
                }
            } else {
                profit.setCustomerType(CustomerTypeEnum.STOCK);
            }
        }
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
            if ("3011101001001".equals(productCode)) {
                return ProfitProductTypeEnum.RJT_PROFIT;
            } else if("3011004003002".equals(productCode)
                    || "3011004003001".equals(productCode)) {
                //2018.03.16增加认证信息费用
                return ProfitProductTypeEnum.AUTH_PROFIT;
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
        String start = DateUtil.formatDate(lastMonthFirstDay, "yyyyMMdd");
        System.out.println(start);
        String end = DateUtil.formatDate(lastMonthLastDay, "yyyyMMdd");
        System.out.println(end);

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
            sb.append(" profitType: <").append(profitType.getDisplayName()).append("> ");
        }
        if (customerType != null) {
            sb.append(" customerType: <").append(customerType.getDisplayName()).append("> ");
        }
        if (beginDate != null) {
            sb.append(" beginDate:").append(DateUtil.formatDate(beginDate, "yyyyMMdd"));
        }
        if (endDate != null) {
            sb.append(",endDate:").append(DateUtil.formatDate(endDate, "yyyyMMdd"));
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
        //通过所有的uniqueId查询本地库是否已经存在记录,已经存在进行累计。
        List<Profit> needSave = new ArrayList<Profit>();
        List<Profit> needUpdate = new ArrayList<Profit>();
        List<Profit> localProfitList = profitService.findByUniqueIdSet(uniqueIdSet);
        Map<String, Profit> localMap = new HashMap<String, Profit>();
        if (localProfitList != null && localProfitList.size() > 0) {
            for (Profit localProfit : localProfitList) {
                if (localProfit != null && StringUtils.isNotBlank(localProfit.getUniqueId())) {
                    localMap.put(localProfit.getUniqueId(),localProfit);
                }
            }
        }
        // 如果不存在记录，进行批量保存；如果存在记录进行批量更新。
        if (localMap != null && localMap.size() >= 0) {
            for (Profit profit:profits) {
                if (!localMap.containsKey(profit.getUniqueId())) {
                    needSave.add(profit);
                } else {
                    //已经存在累加四项。
                    Profit localProfit = localMap.get(profit.getUniqueId());
                    Long dayCount = localProfit.getDayCount();
                    if (dayCount == null) {
                        dayCount = Long.valueOf(0);
                    }
                    Long totalTrxCount = localProfit.getTotalTrxCount();
                    if (totalTrxCount == null) {
                        totalTrxCount = Long.valueOf(0);
                    }
                    BigDecimal trxAmt = localProfit.getTrxAmt();
                    if (trxAmt == null) {
                        trxAmt = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    BigDecimal profitAmt = localProfit.getProfitAmt();
                    if (profitAmt == null) {
                        profitAmt = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }

                    Long dayCount1 = profit.getDayCount();
                    if (dayCount1 != null) {
                        dayCount = dayCount + dayCount1;
                        localProfit.setDayCount(dayCount);
                    }
                    Long totalTrxCount1 = profit.getTotalTrxCount();
                    if (totalTrxCount1 != null) {
                        totalTrxCount = totalTrxCount + totalTrxCount1 ;
                        localProfit.setTotalTrxCount(totalTrxCount);
                    }
                    BigDecimal trxAmt1 = profit.getTrxAmt();
                    if (trxAmt1 != null) {
                        trxAmt = trxAmt.add(trxAmt1);
                        localProfit.setTrxAmt(trxAmt);
                    }
                    BigDecimal profitAmt1 = profit.getProfitAmt();
                    if (profitAmt1 != null) {
                        profitAmt = profitAmt.add(profitAmt1);
                        localProfit.setProfitAmt(profitAmt);
                    }
                    needUpdate.add(localProfit);
                }
            }
        }
        if (needUpdate.size() > 0) {
            logger.debug("本次需要更新：" + needUpdate.size());
            profitService.batchUpdateProfitUseJdbc(needUpdate);
        }
        //批量保存
        if (needSave.size() > 0) {
            saveCount = needSave.size();
            logger.debug("本次需要保存：" + needSave.size());
            profitService.batchSave(needSave);
        }
        //保存成功，清空数据容器
        uniqueIdSet.clear();
        profits.clear();
        needUpdate.clear();
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
            //MonitorNotify.notifyEmail("BEGIN 开始" + sb.toString(),null);

            //计算前，校验数据是否已经同步完成
            ProfitSummation sysData = profitSummationService.findByProductType(ProfitProductTypeEnum.ALL, CustomerTypeEnum.ALL, month);
            if (sysData == null || sysData.getCalculateStatus() != Status.SUCCESS) {
                throw new RuntimeException("数据未同步完成，需要先同步数据。");
            }

            ProfitCalculator calculatetor = getObeject(profitType,customerType);
            int calculateCount = calculatetor.calculate(month, reCaculate);

            //设置为计算完毕
            ProfitSummation summation = profitSummationService.findByProductType(profitType, customerType, month);

            sb.append("\n\n汇总记录：").append(logSummation(summation));
            //汇总明细表的数据
            ProfitSummation summation1 = profitService.sumByProductType(profitType, customerType, month,Status.SUCCESS);
            sb.append("\n\n从明细表汇总的已计算数据为：").append(logSummation(summation1));

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

            int trxAmtComp = mitangTotalTrxamt.compareTo(mitangTotalTrxamt1);
            int profitAmtComp = mitangTotalProfitAmt.compareTo(mitangTotalProfitAmt1);
            if (trxAmtComp != 0
                    || profitAmtComp != 0) {
                sb.append("\n【注意：】金额核对结果貌似有些不一致！请人工确认。（计算时采用保留2位小数四舍五入算法。）\n" +
                        "    交易金额差额为：" + (mitangTotalTrxamt.subtract(mitangTotalTrxamt1)) +
                        "\n    毛利金额差额为：" + (mitangTotalProfitAmt.subtract(mitangTotalProfitAmt1)));
            } else {
                sb.append("\n金额核对一致！");
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
     * 毛利汇总数据
     * @param summation
     */
    private String logSummation(ProfitSummation summation) {
        if (summation == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n总交易金额：").append(summation.getTotalTrxAmt());
        sb.append("\n总毛利：").append(summation.getTotalProfitAmt());
        sb.append("\n蜜糖总交易金额：").append(summation.getMitangTotalTrxamt());
        sb.append("\n蜜糖总毛利：").append(summation.getMitangTotalProfitAmt());
        sb.append("\n比例：").append(summation.getPercent());
        sb.append("\n总交易笔数：").append(summation.getTotalTrxCount());
        sb.append("\n总交易天数：").append(summation.getTotalDayCount());
        return sb.toString();
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
                } else if (customerType == CustomerTypeEnum.STOCK2) {
                    return skbStock2Calculator;
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
            case AUTH_PROFIT:
                if (customerType == CustomerTypeEnum.STOCK) {
                    return authStockCalculator;
                }
                return authMtCalculator;
            default:return null;
        }
    }

    @Override
    public void clearSysDatas(String month) {
        if (StringUtils.isBlank(month)) {
            throw new RuntimeException("清除月份month必须指定。");
        }
        long l = System.currentTimeMillis();
        //1. 删除汇总记录的所有数据
        profitSummationService.deleteByMonth(month);
        logger.info(month + "月，删除Summation成功");
        //2. 删除明细表的数据
        profitService.deleteByMonth(month);
        logger.info(month + "月，删除Profit成功");
        long time = System.currentTimeMillis() - l;
        MonitorNotify.notifyEmail("删除" + month + "月毛利数据成功。耗时：" + time,null);
    }
}
