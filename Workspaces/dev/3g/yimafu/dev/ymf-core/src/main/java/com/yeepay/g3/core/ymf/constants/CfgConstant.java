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

        return ConfigureSetting.getValue("YMF_REMIT_FEE_TYPE","SOURCE");
    }
}
