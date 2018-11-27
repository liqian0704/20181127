package com.yeepay.g3.core.ymf.constants;

import com.yeepay.g3.utils.common.encrypt.AES;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 常量
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/16.
 */
public class Constants {

    public static final String SSO_SESSION_USER_ID = "yeepay_sso_session_userid";

    /**==========从统一配置中获取 开始 ================*/
    // API_MODE
    public static final String YMF_API_MODE_QA = "QA";
    public static final String YMF_API_MODE_ONLINE = "PRODUCT";

    public static final String YMF_HEART_BEAT_IP = "YMF_HEART_BEAT_IP";

    /** 统一配置的KEY最好在此处添加一个常量KEY */
    // 统一收银台参数key
    // 1oC3L9516894J0jX2k5X7Uh505G9ER
    public static final String YMF_NC_API_HMAC = "YMF_NC_API_HMAC";
    // 17
    public static final String YMF_NC_API_ACCESS_CODE = "YMF_NC_API_ACCESS_CODE";
    // COD REMOTE URL
    public static final String YMF_COD_HESSIAN_URL = "YMF_COD_HESSIAN_URL";

    // NC_API_MODE
    public static final String YMF_NC_API_MODE = "YMF_NC_API_MODE";
    // QA url
    public static final String YMF_NC_API_QA_URL = "YMF_NC_API_QA_URL";

    // 补单时间间隔
    public static final String YMF_TIMER_CONFIG = "YMF_TIMER_CONFIG";
    public static final String YMF_TIMER_CONFIG_ORDER = "ORDER";
    public static final String YMF_TIMER_CONFIG_ORDER_FROM = "ORDER_FROM";
    public static final String YMF_TIMER_CONFIG_ORDER_TO = "ORDER_TO";
    public static final String YMF_TIMER_CONFIG_REFUND_FROM = "REFUND_FROM";
    public static final String YMF_TIMER_CONFIG_REFUND_TO = "REFUND_TO";
    public static final String YMF_TIMER_CONFIG_EXPIRE_BEFORE = "EXPIRE_BEFORE";


    // 二维码签名host
    public static final String YMF_PAY_HOST="YMF_PAY_HOST";

    public static final String NC_API_CURRENCY_YMF = "NC_API_CURRENCY_YMF";


    // 下载订单单页值
    public static final String YMF_DOWNLOAD_PAGE_SIZE = "YMF_DOWNLOAD_PAGE_SIZE";

    // 商户后台退款功能开关
    public static final String YMF_G2_REFUND_FUNCTION = "YMF_G2_REFUND_FUNCTION";
    // 订单中心与统一收银台下单接口开关。opr 开启为true
    public static final String YMF_OPR_ORDER_SWITCH = "YMF_OPR_ORDER_SWITCH";

    public static final String HACK_KEY = "HACK_KEY";

    /**========== 从统一配置中获取 结束 =============*/


    /** G2Servlet 商户后台 APICODE */
    public static final String G2SERVLET_API = "apicode";
    public static final String G2SERVLET_API_PARAM = "param";
    public static final String G2SERVLET_API_ORDER_QUERY = "order_query";
    public static final String G2SERVLET_API_QUERY_ORDER = "query_order";
    public static final String G2SERVLET_API_REFUND_QUERY = "refund_query";
    public static final String G2SERVLET_API_REFUND_ORDER = "refund_order";
    public static final String G2SERVLET_API_ORDER_DOWNLOAD = "order_download";
    public static final String G2SERVLET_API_REFUND_DOWNLOAD = "refund_download";

    public static final String G2SERVLET_API_HMAC = "ymf_g2servlet_jhk";

    /** 补单定时 三代定时POST调用 */
    public static final String SUPPLY_SERVLET_METHOD = "supply_method";
    public static final String SUPPLY_SERVLET_HMAC = "ymf_supplyservlet_lalala";
    
    
    /** 银行卡号AES加解密key */
    public static final String CARD_NO_AES_KEY = "817ABBC975B0626E";

    /** 微信密钥AES加解密key */
    public static final String APP_SECRET_AES_KEY = "117ABBE975B06251";
    /** 默认打赏模板 **/
    public static final String DEFAULT_GRATUITY = "DefaultGratuity";

    /**
     * 三代计费项目
     */
    public static final String YMF  = "YMF";
    /**
     * 主扫计费产品编码
     */
    public static final String YMF_SCANSELF  = "YMF";
    /**
     * 清算中心业务方
     */
    public static final String YMFTRADE  = "YMFTRADE";
    /**
     * 支付方式 字典类型
     */
    public static final String PAY_TYPE = "PayType";
    /**
     * 收款类型 字典类型
     */
    public static final String COLLECT_TYPE = "CollectType";
    /**
     * 线程池初始化线程数
     */
    public static final String INIT_POOL_SIZE = "INIT_POOL_SIZE";
    public static final String MAX_POOL_SIZE = "MAX_POOL_SIZE";
    public static final String QUEUE_SIZE = "QUEUE_SIZE";
    /**
     * laike定制化通知标示 业务方为LAIKE才会做异步通知
     */
    public static final String LAIKE = "LAIKE";
    public static final String WECHAT_ZS_FEECODE = "WECHAT_ZS_FEECODE";
    public static final String ALIPAY_ZS_FEECODE = "ALIPAY_ZS_FEECODE";
    public static final String OPR_DATE_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
    public static final String SALES_DATA_URL = "SALES_DATA_URL";
    /**
     * 本人卡标识
     */
    public static final String ONE_SELF_FLAG = "0";
    /**
     * 日结通  结算类型 SOURCE 商户承担
     */
    public static final String SOURCE_FEETYPE = "SOURCE";
    /**
     * 日结通  结算类型 TARGET 用户承担
     */
    public static final String TARGET_FEETYPE = "TARGET";
    public static final String FTP_IP = "ftp_ip";
    public static final String FTP_USERNAME = "ftp_username";
    public static final String FTP_PWD = "ftp_pwd";
    public static final String FTP_SERVER = "ftp_img_server";
    /**
     * 来客银行编码与日结通编码转换
     */
    public static final Map<String,String> bankCodeTransferMap = new HashMap<String,String>();
    static{
        bankCodeTransferMap.put("GDB","CGB");
        bankCodeTransferMap.put("PSBC","POST");
        bankCodeTransferMap.put("SDB","SZCB");
    }

	public static void main(String[] args) {
        System.out.println(AES.encryptToBase64("c575326b9c2a81e2a31e6084b2239560", APP_SECRET_AES_KEY)); ;
    }

}
