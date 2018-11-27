package com.yeepay.g3.core.ymf.constants;

import com.yeepay.g3.core.ymf.utils.ConfigureSetting;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 三代统一配置
 * @Author: xiaobin.liu
 * @Date: 17/3/1
 * @Time: 下午3:49
 */
public class CfgConstant {

    private static final Logger logger = LoggerFactory.getLogger(CfgConstant.class);

    /**
     * 支付处理器,前台回调页面地址  YMF_OPR_PAGE_URL
     * 默认值：空字符串
     * @return  前台回调url
     */
    public static String getOprPageUrl() {
        return ConfigureSetting.getValue("YMF_OPR_PAGE_URL", "");
    }

    /**
     * 支付处理器，交易成功回调地址  YMF_OPR_NOTIFY_URL
     * 默认值：空字符串
     * @return  回调url
     */
    public static String getOprNotifyUrl() {
        return ConfigureSetting.getValue("YMF_OPR_NOTIFY_URL", "");
    }

    /**
     * 支付处理器，清算回调地址  YMF_OPR_CS_URL
     * 默认值：空字符串
     * @return  清算回调url
     */
    public static String getOprCsUrl() {
        return ConfigureSetting.getValue("YMF_OPR_CS_URL", "");
    }

    /**
     * 标准收银台模式、直连模式,请求地址  YMF_OPR_STANDARD_CASHIER_URL
     * 默认值：空字符串
     * @return  请求url
     */
    public static String getOprStdCashierUrl() {
        return ConfigureSetting.getValue("YMF_OPR_STANDARD_CASHIER_URL", "");
    }

    /**
     * 被扫模式请求地址:
     * 默认值:空
     * @return 回调URL
     */
    public static String getOprPassiveCashierUrl() {
        return ConfigureSetting.getValue("YMF_OPR_PASSIVE_CASHIER_URL", "");
    }

    /**
     * 客户中心请求参数：system
     * @return      system id
     */
    public static String getMerSystem() {
        return ConfigureSetting.getValue("YMF_MER_SYSTEM", "");
    }

    /**
     * 客户中心请求参数：uuid
     * @return  YMF_MER_UUID
     */
    public static String getMerUUID() {
        return ConfigureSetting.getValue("YMF_MER_UUID", "");
    }

    /**
     * 返回ymf交易服务器的前缀
     * @return  YMF_PAY_HOST对应值 eg:https://yimafu.yeepay.com/ymf-pay
     */
    public static String gePayHostUrl() {
        return ConfigureSetting.getValue("YMF_PAY_HOST", "https://yimafu.yeepay.com/ymf-pay");
    }

    /**
     * 返回ymf交易短链的服务的开关
     * @return  默认返回：true
     */
    public static boolean isOpenShortLink() {
        return ConfigureSetting.getValue("YMF_PAY_SHORT_LINK_SWITCH", true);
    }

    /**
     * 返回YOP签名秘钥KEY值
     * @return
     */
    public static String getYOPAppKey(){
        return ConfigureSetting.getValue("YMF_OPR_YOP_APPKEY","");
    }

    /**
     * 人保公众号获取OpenId开关。默认：false(关闭),默认走非报备通道
     * @return
     */
    public static boolean isTurnOnPiccOpenId(){
        return ConfigureSetting.getValue("YMF_PICC_OPENID_SWITCH",false);
    }
    /**
     * 获取日结通打款接口url
     * @return
     */
    public static String getRemitServiceUrl(){
        return ConfigureSetting.getValue("YMF_REMIT_SERVICE_URL","");
    }
    /**
     * 获取日结通打款信息查询接口url
     * @return
     */
    public static String getRemitInfoUrl(){
        return ConfigureSetting.getValue("YMF_REMIT_INFO_URL","");
    }
    /**
     * 获取日结通打款起结金额
     * @return
     */
    public static String getRemitAmountLimit(){
        return ConfigureSetting.getValue("YMF_REMIT_AMOUNT_LIMIT","10");
    }
    /**
     * 获取日结通打款时间
     * @return
     */
    public static Map<String, String> getRemitTimeLimit(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("START","8");
        map.put("END","21");
        map = ConfigureSetting.getValue("YMF_REMIT_TIME_LIMIT",map);
        return map;
    }
    /**
     * 获取日结通产品
     * @return
     */
    public static String getRemitProductCode(){
        return ConfigureSetting.getValue("YMF_REMIT_PRODUCT_CODE","RJT");
    }
    /**
     * 获取日结通来客二级产品码
     * 二级产品码 需要产品确定下来客的二级产品码LIKER，没有就不填
     * @return
     */
    public static String getRemitSecondProductCode(){
        return ConfigureSetting.getValue("YMF_REMIT_SECOND_PRODUCT_CODE","LIKER");
    }

    /**
     * 获取打款查询 每页查询数量
     * @return
     */
    public static int getRemitQueryPageSize(){
        //默认LIKER点
        return Integer.valueOf(ConfigureSetting.getValue("YMF_REMIT_QUERY_PAGE_SIZE","1000"));
    }

    public static String getRemitFeeType(){

        return ConfigureSetting.getValue("YMF_REMIT_FEE_TYPE","TARGET");
    }

    /**
     * 打款请求延时时间（秒），默认值：10
     * @return
     */
    public static int getRemitIntervalSecond(){
        return Integer.valueOf(ConfigureSetting.getValue("YMF_REMIT_INTERVAL_SECOND","10"));
    }

    /**
     * 打款允许重跑次数,默认值1
     * @return
     */
    public static int getRemitRetryCount(){
        return Integer.valueOf(ConfigureSetting.getValue("YMF_REMIT_RETRY_COUNT","1"));
    }

    /**
     * 打款允许重跑间隔时间,默认值1分
     * @return
     */
    public static int getRemitRetryInterval(){
        return Integer.valueOf(ConfigureSetting.getValue("YMF_REMIT_RETRY_INTERVAL","1"));
    }

    /**
     * 系统异常通知地址
     */
    public static String[] YMF_NOTITY_EMAILS(){
        String ymf_notity_emails = ConfigureSetting.getValue("YMF_NOTITY_EMAILS",
                "xiaobin.liu@yeepay.com");//dongxu.lu@yeepay.com
        String[] emails = ymf_notity_emails.split(",");
        return emails;
    }

    /**
     * 系统异常通知 开关
     * @return  默认返回：false
     */
    public static boolean YMF_NOTITY_EMAILS_SWITCH() {
        return ConfigureSetting.getValue("YMF_NOTITY_EMAILS_SWITCH", true);
    }

    /**
     * FTPINFO 开关
     * @return  默认返回：false
     */
    public static Map<String,String> getYMF_FTP_SERVER_INFO() {
        return ConfigureSetting.getValue("YMF_FTP_SERVER_INFO", new HashMap<String, String>());
    }

    /**
     * FTP 地址前缀
     * @return
     */
    public static String getYMF_FTP_SERVER_PREFIX() {
        return ConfigureSetting.getValue("YMF_FTP_SERVER_PREFIX", "http://attachment.yeepay.com/ucm");
    }

    /**
     * 易码付Ftp路径前缀
     * @return
     */
    public static String getYMF_SERVER_PREFIX() {
        return ConfigureSetting.getValue("YMF_SERVER_PREFIX", "http://yimafu.yeepay.com/ymf-pay/ftp/getFtpFile");
    }

    /**
     * 秒到限制当日交易笔数
     * @return
     */
    public static int getS0_ORDER_COUNT_LIMIT(){
        return Integer.valueOf(ConfigureSetting.getValue("S0_ORDER_COUNT_LIMIT","10"));
    }

    /**
     * 商户秒到打款白名单
     * @return
     */
    public static Map<String, String> getCUSTOMER_S0_ORDER_LIMIT(){
        Map<String, String> map = new HashMap<String, String>();
        map = ConfigureSetting.getValue("CUSTOMER_S0_ORDER_LIMIT",map);
        return map;
    }

    /**
     * 毛利计算分页查询，每页笔数
     * @return
     */
    public static int MT_PROFIT_PAGE_COUNT(){
        return Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_PAGE_COUNT","3000"));
    }

    /**
     * 毛利计算，数据同步时异常终止条件.（分页查询report库时要用次值）
     * @return
     */
    public static int MT_PROFIT_SYS_END_CONDITION(){
        return Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_SYS_END_CONDITION","3"));
    }

    /**
     * 毛利同步数据接口IP；默认为生产环境ip:http://10.149.200.104:8999/
     * 开发环境：http://10.148.170.103:8099/
     */
    public static String MT_PROFIT_DATA_URL() {
        return ConfigureSetting.getValue("MT_PROFIT_DATA_URL", "http://10.149.200.104:8999/");
    }

    /**
     * 毛利计算:数据同步时，批次保存条数
     * @return
     */
    public static int MT_PROFIT_BATCH_SAVE_COUNT(){
        return Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_BATCH_SAVE_COUNT","200"));
    }

    /**
     * 蜜糖连接report同步数据连接超时时间。统一配置的单位：秒
     * @return      返回毫秒
     */
    public static int MT_PROFIT_SYS_CONNECT_TIMEOUT(){
        return 1000 * Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_SYS_CONNECT_TIMEOUT","300"));
    }

    /**
     * 蜜糖同步数据读取数据超时时间。统一配置的单位：秒
     * @return      返回毫秒
     */
    public static int MT_PROFIT_SYS_READ_TIMEOUT(){
        return 1000 * Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_SYS_READ_TIMEOUT","3000"));
    }

    /**
     * 同步数据时，最大间隔时间段。比如：10天，超过这个值会分多次请求
     */
    public static int MT_PROFIT_REQUEST_DAYS(){
        return Integer.valueOf(ConfigureSetting.getValue("MT_PROFIT_REQUEST_DAYS","11"));
    }

    /**
     * 蜜糖分润的代理商编号
     */
    public static String MT_AGENT_CODE() {
        return ConfigureSetting.getValue("MT_AGENT_CODE", "10018126689");
    }

    /**
     * 蜜糖推送毛利数据到代理商系统URL
     */
    public static String MT_PUSH_PROFIT_AGENT_URL() {
        return ConfigureSetting.getValue("MT_PUSH_PROFIT_AGENT_URL", "http://10.151.30.152:8080/agent/shareAddHttpInvoke");
    }


}
