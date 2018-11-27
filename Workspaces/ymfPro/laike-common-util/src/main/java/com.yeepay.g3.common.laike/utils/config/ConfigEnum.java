package com.yeepay.g3.common.laike.utils.config;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 3g统一配置配置键
 * Author: jiawen.huang
 * Date: 15/4/23
 * Time: 15:20
 * Version: 1.0
 * Copyright © 2015 YeePay.com All rights reserved.
 */
public enum ConfigEnum {

	/**
	 * 代理商远程地址
	 */
	AGENT_INVITE("com.yeepay.agent.hessian.service.laike.GetLaikeAgentInfoService", "http://10.151.30.152:8080/agent_boss/laikeInviteCode"),

	/**
	 * 来客产品和费率
	 */
	LAIKE_PRODUCT_RATE("LAIKE_PRODUCT_RATE", "{\"status\":\"SUCCESS\",\"settleMethod\":\"PERIOD\",\"riskDeadline\":\"0\",\"minSettleAmt\":\"10\",\"productInfo\":\"[{\"id\":\"GZH\",\"feeRate\":\"0.65\",\"feePayer\":\"RECEIVER\",\"appId\":\"\",\"payUrl\":\"\",\"customerNo\":\"\"},{\"id\":\"WXZS\",\"feeRate\":\"0.65\",\"feePayer\":\"RECEIVER\",\"customerNo\":\"\"},{\"id\":\"ALI\",\"feeRate\":\"0.65\",\"feePayer\":\"RECEIVER\"},{\"id\":\"ALIZS\",\"feeRate\":\"0.65\",\"feePayer\":\"RECEIVER\"},{\"id\":\"NC_PAY_DEBIT\",\"feePayer\":\"RECEIVER\",\"feeRate\":\"0.45\",\"enterpriseLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"},\"individualLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"},\"personalLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"}},{\"id\":\"NC_PAY_CREDIT\",\"feePayer\":\"RECEIVER\",\"feeRate\":\"0.65\",\"enterpriseLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"},\"individualLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"},\"personalLimit\":{\"order\":\"5000\",\"day\":\"100000\",\"month\":\"500000\"}}]\",\"settleDate\":\"1,2,3,4,5,6,7\",\"settleCycle\":\"WEEK\"}"),

	/**
	 * 直销销售号
	 */
	LAIKE_DIRECT_SELLER("LAIKE_DIRECT_SELLER", "123456"),

	/**
	 * 直销代理商
	 */
	LAIKE_DIRECT_AGENT("LAIKE_DIRECT_AGENT", "10040011542"),

	/**
	 * 来客协议是否是单张
	 */
	LAIKE_AGREEMENT_SIGNAL("LAIKE_AGREEMENT_SIGNAL", true),

	/**
	 * 验证码有效时间(s)
	 */
	SMS_CODE_EFFECT_INTERVAL("LIKER_SMS_EFFECT_INTERVAL", "300"),

	/**
	 * 推送参数
	 */
	LIKER_APNS_PRODUCTION("LIKER_APNS_PRODUCTION", true),

	/**
	 * 极光标识
	 */
	LIKER_JPUSH_APP_KEY("LIKER_JPUSH_APP_KEY", "7cd38d599c251844b6de7318"),

	/**
	 * 极光密钥
	 */
	LIKER_JPUSH_APP_SECRET("LIKER_JPUSH_APP_SECRET", "11a4916cbd5f705c2cfc645e"),

	AES_KEY("LIKER_KEY", "u98KjMMMjSHJ22d="),

	AES_KEY_SUFFIX("LIKER_KEY1_SUFFIX", "+hoPUQ+89OuyyX++EifNE="),

	/**
	 * yop token 有效期
	 */
	LIKER_YOP_TOKEN_INTERVAL("LIKER_YOP_TOKEN_INTERVAL", "604800"),

	/**
	 * yop token 域
	 */
	LIKER_YOP_TOKEN_SCOPE("LIKER_YOP_TOKEN_SCOPE", "test"),

	/**
	 * FTP 配置文件
	 */
	LIKER_FTP_PROPERTIES_NAME("LIKER_FTP_PROPERTIES_NAME", "liker"),

	/**
	 * 同志们女士们先生们入网的错误码又改了！！！！
	 */
	LIKER_CS_MERCHANT_ERROR_KEY("LIKER_CS_MERCHANT_ERROR_KEY", new HashMap<String, String>() {
		{
			put("REG00000", "000000");
			put("REG30002", "300002");
			put("REG30003", "300003");
		}
	}),

	/**
	 * 来客错误邮件通知人
	 */
	LIKER_SYS_NOTIFY_EMAIL("LIKER_SYS_NOTIFY_EMAIL", new ArrayList<String>() {
		{
			add("jiawen.huang@yeepay.com");
			add("wei.li@yeepay.com");
			add("jiwei.lv@yeepay.com");
			add("dongxu.lu@yeepay.com");
			add("yan.zhang@yeepay.com");
		}
	}),

	/**
	 * 入网传支行号？
	 */
	LIKER_CS_MERCHANT_BRANCH_CODE("LIKER_CS_MERCHANT_BRANCH_CODE", false),

	/**
	 * 来客入网开通产品协议payType参数
	 */
	LIKER_PRODUCT_PAY_TYPE_PARAMS("LIKER_PRODUCT_PAY_TYPE_PARAMS", new HashMap<String, String>() {
		{
			put("app", "true");
			put("taiqian", "true");
		}
	}),

	/**
	 * 开通产品信息openProducts参数
	 */
    LIKER_OPEN_PRODUCTS_PARAMS("LIKER_OPEN_PRODUCTS_PARAMS", new ArrayList<String>() {
        {
			add("WXZS");
			add("ALI");
			add("ALIZS");
			add("GZH");
			add("NC_PAY_CREDIT");
			add("NC_PAY_DEBIT");
		}
	}),

	/**
	 * 直销费率信息
	 */
	LIKER_DIRECT_WORKER_FEE_RATE("LIKER_DIRECT_WORKER_FEE_RATE", new HashMap<String, String>() {
		{
			put("ONE_KEY_PAY_DEBIT", "0.5");
			put("ONE_KEY_PAY_CREDIT", "0.5");
			put("WALLET_PAY_ZFB", "0.38");//支付宝
			put("OFFICIAL_ACCOUNT_PAY", "0.38");//公众号
			put("MERCHANT_SCAN_PAY", "0.5");//商家扫码
			put("UPOP_ATIVE_SCAN_LEVEL_1", "0.38");//银联小于1000.01费率
			put("UPOP_ATIVE_SCAN_LEVEL_2_DEBIT", "0.52");//银联大于等于1000.01借记卡费率
			put("UPOP_ATIVE_SCAN_LEVEL_2_CREDIT", "0.65");//银联大于等于1000.01贷记卡费率
			put("UPOP_ATIVE_SCAN_LEVEL_2_DEBIT_TOP", "22");//银联大于等于1000.01借记卡封顶金额
		}
	}),


	/**
	 * 代理费率信息
	 */
	LIKER_WORKER_FEE_RATE("LIKER_WORKER_FEE_RATE", new HashMap<String, String>() {
		{
			put("ONE_KEY_PAY_DEBIT", "0.5");
			put("ONE_KEY_PAY_CREDIT", "0.5");
			put("WALLET_PAY_ZFB", "0.38");
			put("OFFICIAL_ACCOUNT_PAY", "0.38");
            put("UPOP_ATIVE_SCAN_LEVEL_1", "0.38");
            put("UPOP_ATIVE_SCAN_LEVEL_2_DEBIT", "0.52");
            put("UPOP_ATIVE_SCAN_LEVEL_2_CREDIT", "0.65");
            put("UPOP_ATIVE_SCAN_LEVEL_2_DEBIT_TOP", "22");
        }
	}),

	/**
	 * 企业限额信息
	 */
	LIKER_ENTERPRISE_LIMIT("LIKER_ENTERPRISE_LIMIT", new HashMap<String, String>() {
		{
			put("ONE_KEY_PAY_ORDER", "50000");//一键单笔限额
			put("ONE_KEY_PAY_DAY", "100000");//一键日限额
			put("ONE_KEY_PAY_MONTH", "200000");//一键月限额
			put("ONE_KEY_PAY_DAY_TIME", "-1");//一键日次数限制
			put("ONE_KEY_PAY_MONTH_TIME", "-1");
			put("OTHER_ORDER", "-1");//钱包/扫码/公众号 单卡单笔限额
			put("OTHER_DAY", "-1");
			put("OTHER_MONTH", "-1");
		}
	}),

	/**
	 * 小微限额信息
	 */
	LIKER_MICRO_LIMIT("LIKER_MICRO_LIMIT", new HashMap<String, String>() {
		{
			put("ONE_KEY_PAY_ORDER", "50000");
			put("ONE_KEY_PAY_DAY", "100000");
			put("ONE_KEY_PAY_MONTH", "200000");
			put("ONE_KEY_PAY_DAY_TIME", "-1");
			put("ONE_KEY_PAY_MONTH_TIME", "-1");
			put("OTHER_ORDER", "1000");
			put("OTHER_DAY", "-1");
			put("OTHER_MONTH", "-1");
		}
	}),

	/**
	 * 个体限额信息
	 */
	LIKER_INDIVIDUAL_LIMIT("LIKER_INDIVIDUAL_LIMIT", new HashMap<String, String>() {
		{
			put("ONE_KEY_PAY_ORDER", "50000");
			put("ONE_KEY_PAY_DAY", "100000");
			put("ONE_KEY_PAY_MONTH", "200000");
			put("ONE_KEY_PAY_DAY_TIME", "-1");
			put("ONE_KEY_PAY_MONTH_TIME", "-1");
			put("OTHER_ORDER", "3000");
			put("OTHER_DAY", "-1");
			put("OTHER_MONTH", "-1");
		}
	}),

	/**
	 * 直销商户默认开通产品
	 */
	LIKER_DIRECT_WORKER_PRODUCT("LIKER_DIRECT_WORKER_PRODUCT", new ArrayList<String>() {
		{
			add("ONE_KEY_PAY");
			add("WALLET_PAY");
			add("OFFICIAL_ACCOUNT_PAY");
			add("MERCHANT_SCAN_PAY");
            add("UPOP_SCAN_PAY");
        }
	}),

	/**
	 * 代理商户默认开通产品
	 */
	LIKER_WORKER_PRODUCT("LIKER_WORKER_PRODUCT", new ArrayList<String>() {
		{
			add("ONE_KEY_PAY");
			add("WALLET_PAY");
			add("OFFICIAL_ACCOUNT_PAY");
            add("UPOP_SCAN_PAY");
        }
	}),

	/**
	 * 收款码前缀
	 */
	LIKER_PAY_CODE_PREFIX("LIKER_PAY_CODE_PREFIX", new HashMap<String, String>() {
		{
			put("10", "WECHAT");
			put("11", "WECHAT");
			put("12", "WECHAT");
			put("13", "WECHAT");
			put("14", "WECHAT");
			put("28", "ALIPAY");
			put("62", "OPEN_UPOP");
		}
	}),

	/**
	 * 来客业务编码
	 */
	LIKER_BIZ_NO("LIKER_BIZ_NO", 3),

	/**
	 * 贷款产品信息
	 */
	LIKER_CREDIT_PRODUCT("LIKER_CREDIT_PRODUCT", "[{\"id\":\"SJD\",\"name\":\"手机贷\",\"maxLimit\":\"20000\",\"loanTime\":\"1-2天放款\",\"feeRate\":\"月费率最低0.9%\",\"deadline\":\"贷款期限7天-6个月\",\"icon\":\"https://yimafu.yeepay.com/ymf-pay/static/images/creditProductIcon/SJD.png\"},{\"id\":\"XEF\",\"name\":\"信而富\",\"maxLimit\":\"20000\",\"loanTime\":\"1-2天放款\",\"feeRate\":\"月费率0.9-3.8%\",\"deadline\":\"贷款期限7天-6个月\",\"icon\":\"https://yimafu.yeepay.com/ymf-pay/static/images/creditProductIcon/XEF.png\"},{\"id\":\"DM\",\"name\":\"读秒\",\"maxLimit\":\"20000\",\"loanTime\":\"1天放款\",\"feeRate\":\"月费率最低0.72%\",\"deadline\":\"贷款期限1-24个月\",\"icon\":\"https://yimafu.yeepay.com/ymf-pay/static/images/creditProductIcon/DM.png\"},{\"id\":\"AXD\",\"name\":\"爱又米\",\"maxLimit\":\"2000\",\"loanTime\":\"1-2天放款\",\"feeRate\":\"月费率最低1%\",\"deadline\":\"贷款期限1-24个月\",\"icon\":\"https://yimafu.yeepay.com/ymf-pay/static/images/creditProductIcon/AXD.png\"}]"),

	/**
	 * 默认ip地址
	 */
	LIKER_IP_ADDRESS("LIKER_IP_ADDRESS", "111.111.111.111"),

	/**
	 * 来客结算银行列表
	 */
	LIKER_SETTLE_BANK("LIKER_SETTLE_BANK", new ArrayList<String>() {
		{
			add("{\"bankName\":\"中国银行\",\"bankCode\":\"BOC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"交通银行\",\"bankCode\":\"BOCO\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国建设银行\",\"bankCode\":\"CCB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国光大银行\",\"bankCode\":\"CEB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国民生银行\",\"bankCode\":\"CMBC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"招商银行\",\"bankCode\":\"CMBCHINA\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中信银行\",\"bankCode\":\"ECITIC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"广发银行\",\"bankCode\":\"CGB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"华夏银行\",\"bankCode\":\"HXB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国工商银行\",\"bankCode\":\"ICBC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国邮政储蓄银行\",\"bankCode\":\"PSBC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"平安银行\",\"bankCode\":\"SDB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"上海浦东发展银行\",\"bankCode\":\"SPDB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"中国农业银行\",\"bankCode\":\"ABC\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"北京银行\",\"bankCode\":\"BCCB\",\"bankImg\":\"\"}");
			add("{\"bankName\":\"兴业银行\",\"bankCode\":\"CIB\",\"bankImg\":\"\"}");
		}
	}),

	/**
	 * 开通S0请求地址
	 */
	S0_OPEN_URL("S0_OPEN_URL", "http://qa.yeepay.com/boss_oltp/openSettleIndayApi.action"),

	/**
	 * S0请求参数
	 */
	S0_OPEN_PARM("S0_OPEN_PARM", new HashMap<String, String>() {
		{
			put("workdayDelay", "false");
			put("workdayUrgency", "true");
			put("workdayUrgencyFeeRate", "0");
			put("weekendUrgency", "true");
			put("weekendUrgencyFeeRate", "0");
			put("waitDaysControl", "true");
			put("waitDays", "0");
			put("autoSplit", "true");
			put("payeeLimitValid", "0");
			put("unionProxyFlag", "0");
			put("autobankIds", "ABC;BCCB;BOC;BOCO;CCB;CEB;CGB;CIB;CMBC;CMBCHINA;ECITIC;HXB;ICBC;POST;SDB;SPDB;SZCB");
			put("openRuleType", "special");

		}
	}),

	/**
	 * 开通服务url
	 */
	LIKER_OPEN_PRODUCTION_URL("LIKER_OPEN_PRODUCTION_URL", new HashMap<String, String>() {
		{
			put("BOX_PAY", "");
			put("SCAN_PAY", "");
			put("S0_SETTLE", "http://10.151.30.152:8081/ymf-pay/static/h5/s0_open.html");
		}
	}),

	/**
	 * 银行编码转换--日结通结算卡校验、展业app(卡bin转换)
	 */
	LIKER_BANK_CODE_TRANSFER("LIKER_BANK_CODE_TRANSFER", new HashMap<String, String>() {
		{
			put("GDB", "CGB");
			put("POST", "PSBC");
			put("HX", "HXB");
			put("SZPA", "SDB");
		}
	}),

	/**
	 * 银行编码转换--YMF用
	 */
	LIKER_BANK_CODE_TO_RJT("LIKER_BANK_CODE_TO_RJT", new HashMap<String, String>() {
		{
			put("GDB", "CGB");
			put("PSBC", "POST");
			put("SDB", "SZCB");
		}
	}),

	/**
	 * RJT基础费率模板id
	 */
	LIKER_RJT_BASE_FEE("LIKER_RJT_BASE_FEE", "42"),

	/**
	 * 首页广告Url
	 */
	LIKER_BANNER_URL("LIKER_BANNER_URL", "http://10.151.30.152:8081/ymf-pay/static/h5/home_ad.html"),

	/**
	 * 硬件超市Url
	 */
	LIKER_DEVICE_MALL_URL("LIKER_DEVICE_MALL_URL", "http://10.151.30.152:8081/ymf-pay/static/h5/hwmarket_list.html"),

	/**
	 * 运营联系人邮件组
	 */
	LIKER_OPERATION_CONTACT_EMAIL("LIKER_OPERATION_CONTACT_EMAIL", new ArrayList<String>() {
		{
			add("jiawen.huang@yeepay.com");
			add("wei.li@yeepay.com");
			add("zhenzheng.zhang@yeepay.com");
			add("yan.zhang@yeepay.com");
			add("qian.li-3@yeepay.com");
		}
	}),

	/**
	 * 小微商户一级分类
	 */
	MICRO_MER_LEVEL1("MICRO_MER_LEVEL1", "109"),

	/**
	 * 小微商户二级分类
	 */
	MICRO_MER_LEVEL2("MICRO_MER_LEVEL2", "109007"),

	/**
	 * OCR开关
	 */
	LIKER_OCR_SWITCH("LIKER_OCR_SWITCH", new HashMap<String, String>() {
		{
			put("MAIN_SWITCH", "0");
			put("ID_CARD_SWITCH", "0");
			put("BANK_CARD_SWITCH", "0");
		}
	}),

	/**
	 * 展业app审核邮件联系人
	 */
	ALLIANCE_OPERATION_CONTACT_EMAIL("ALLIANCE_OPERATION_CONTACT_EMAIL", new ArrayList<String>() {
		{
			add("jiawen.huang@yeepay.com");
			add("wei.li@yeepay.com");
			add("zhenzheng.zhang@yeepay.com");
			add("jingan.dong@yeepay.com");
			add("qian.li-3@yeepay.com");
		}
	}),

	/**
	 * 展业极光标识
	 */
	ALLIANCE_JPUSH_APP_KEY("ALLIANCE_JPUSH_APP_KEY", "33e6714fb1dc1b0feb983975"),

	/**
	 * tk和memberNo匹配性检查
	 */
	LIKER_OAUTH_CHECK("LIKER_OAUTH_CHECK", true),

	/**
	 * 展业极光密钥
	 */
	ALLIANCE_JPUSH_APP_SECRET("ALLIANCE_JPUSH_APP_SECRET", "a6d680fbb02a0f7682f1be6c"),

	/**
	 * 用户指导Url
	 */
	ALLIANCE_USER_GUIDE_URL("ALLIANCE_USER_GUIDE_URL", "http://10.151.32.27:30149/liker-web/static/app/alliance/alliance_help_center.html"),

	/**
	 * hessian地址
	 */
	LIKER_HESSIAN_URL("com.yeepay.g3.facade.laike.facade.app", "http://10.151.32.27:30036/laike-hessian/hessian/"),

	/**
	 * web
	 */
	LIKER_WEB_URL("LIKER_WEB_URL", "http://10.151.32.27:30149"),

	/**
	 * 官网版邀请码
	 */
	ALLIANCE_OFFICIAL_MERCHANT_NO("ALLIANCE_OFFICIAL_MERCHANT_NO", new HashMap<String, String>() {
		{
			put("Y573296", "10015350104");
			put("Y573292", "10015350019");
			put("Y573182", "10015343626");
		}
	}),

	/**
	 * 信用卡申请链接
	 */
	LIKER_CREDIT_CARD_URL("LIKER_CREDIT_CARD_URL", new HashMap<String, String>() {
		{
			put("SPDB", "https://ecentre.spdbccc.com.cn/creditcard/indexActivity.htm?data=P1772522");
			put("ECITIC", "http://creditcard.ecitic.com/h5/shenqing/shanghu/index.html?sid=SJUBJYB");
		}
	}),

	/**
	 * 信用卡产品信息
	 */
	LIKER_CREDIT_CARD_PRODUCT("LIKER_CREDIT_CARD_PRODUCT", "[{\"id\":\"SPDB\",\"name\":\"浦发银行信用卡\",\"description\":[\"1.人气爆棚!免年费!\",\"2.个性化深度定制!欧冠、海贼等你来拿\",\"3.火热新款各类联名卡,增值收益拿不停!\",\"4.刷卡消费笔笔有红包!\"],\"icon\":\"/liker-web/static/img/likerCredit/spd_logo.png\"}]"),

    /**
     * S0结算时间限制(取ymf系统配置，不要修改)
     */
    YMF_REMIT_TIME_LIMIT("YMF_REMIT_TIME_LIMIT", new HashMap<String, String>() {
        {
            put("START", "8");
            put("END", "21");
        }
    }),

    /**
     * S0金额限制(取ymf系统配置，不要修改)
     */
    YMF_REMIT_AMOUNT_LIMIT("YMF_REMIT_AMOUNT_LIMIT", "10"),

	/**
	 * 来客强制更新开关
	 */
	LIKER_FORCE_UPDATE("LIKER_FORCE_UPDATE", true),

	/**
	 * 来客cache开关
	 */
	LIKER_CACHE_SWITCH("LIKER_CACHE_SWITCH", true),

    /**
     * 银联限额开关
     */
    LIKER_UPOP_LIMIT_SWITCH("LIKER_UPOP_LIMIT_SWITCH", "1"),

	/**
	 * 权限开关
	 */
	LIKER_PERMISSION_SWITCH("LIKER_PERMISSION_SWITCH", true),

    /**
     * 分享链接后缀
	 */
	LIKER_SHARE_SUFFIX("LIKER_SHARE_SUFFIX", "/liker-web/share/link"),

	/**
	 * 百度LBS访问地址
	 */
	BAIDU_LBS_URL("BAIDU_LBS_URL", "http://api.map.baidu.com/geocoder/v2/"),

	/**
	 * 省编码匹配
	 */
	LIKER_PROVINCE_CODE("LIKER_PROVINCE_CODE", new HashMap<String, String>() {
		{
			put("北京", "110000");
			put("天津", "120000");
			put("河北", "130000");
			put("山西", "140000");
			put("内蒙古", "150000");
			put("辽宁", "210000");
			put("吉林", "220000");
			put("黑龙江", "230000");
			put("上海", "310000");
			put("江苏", "320000");
			put("浙江", "330000");
			put("安徽", "340000");
			put("福建", "350000");
			put("江西", "360000");
			put("山东", "370000");
			put("河南", "410000");
			put("湖北", "420000");
			put("湖南", "430000");
			put("广东", "440000");
			put("广西", "450000");
			put("海南", "460000");
			put("重庆", "500000");
			put("四川", "510000");
			put("贵州", "520000");
			put("云南", "530000");
			put("西藏", "540000");
			put("陕西", "610000");
			put("甘肃", "620000");
			put("青海", "630000");
			put("宁夏", "640000");
			put("新疆", "650000");
		}
	}),

	LIKER_BRANCH_BANK_CODE("LIKER_BRANCH_BANK_CODE", new HashMap<String, String>() {
		{
			put("BOC", "{\"branchBankCode\":\"104100004013\",\"branchBankName\":\"中国银行股份有限公司北京市分行\"}");
			put("BOCO", "{\"branchBankCode\":\"301100000460\",\"branchBankName\":\"交通银行北京中关村支行\"}");
			put("CCB", "{\"branchBankCode\":\"105100000017\",\"branchBankName\":\"中国建设银行北京望京支行\"}");
			put("CEB", "{\"branchBankCode\":\"303100000006\",\"branchBankName\":\"中国光大银行\"}");
			put("CMBC", "{\"branchBankCode\":\"305100001129\",\"branchBankName\":\"中国民生银行股份有限公司北京国贸支行\"}");
			put("CMBCHINA", "{\"branchBankCode\":\"308100005019\",\"branchBankName\":\"招商银行股份有限公司北京分行\"}");
			put("ECITIC", "{\"branchBankCode\":\"302100010525\",\"branchBankName\":\"中信银行北京上地支行\"}\n");
			put("CGB", "{\"branchBankCode\":\"306100004505\",\"branchBankName\":\"广发银行股份有限公司北京分行\"}");
			put("HXB", "{\"branchBankCode\":\"304100010505\",\"branchBankName\":\"华夏银行北京亦庄支行\"}");
			put("ICBC", "{\"branchBankCode\":\"102100000021\",\"branchBankName\":\"中国工商银行股份有限公司北京上地支行\"}");
			put("PSBC", "{\"branchBankCode\":\"403100000037\",\"branchBankName\":\"中国邮政储蓄银行股份有限公司北京昌平区支行\"}");
			put("SDB", "{\"branchBankCode\":\"307100003002\",\"branchBankName\":\"平安银行股份有限公司北京分行清算中心\"}");
			put("SPDB", "{\"branchBankCode\":\"310100000149\",\"branchBankName\":\"上海浦东发展银行股份有限公司北京西直门支行\"}");
			put("ABC", "{\"branchBankCode\":\"103100000018\",\"branchBankName\":\"中国农业银行股份有限公司北京丽泽分理处\"}");
			put("BCCB", "{\"branchBankCode\":\"313100000013\",\"branchBankName\":\"北京银行\"}");
			put("CIB", "{\"branchBankCode\":\"309100003270\",\"branchBankName\":\"兴业银行北京西直门支行\"}");
		}
	}),

	/**
	 * 分享有礼,推客下载链接后缀
	 */
	ALLIANCE_DOWNLOAD_SUFFIX("ALLIANCE_DOWNLOAD_SUFFIX", "/liker-web/download/alliance/share"),

	/**
	 * 分享有礼,来客注册页后缀
	 */
	LIKER_REGISTER_SUFFIX("LIKER_REGISTER_SUFFIX", "/liker-web/share/liker/");

	private static Map<String, ConfigEnum> valueMap = Maps.newHashMap();

	static {
		for (ConfigEnum item : ConfigEnum.values()) {
			valueMap.put(item.configKey, item);
		}
	}

	private String configKey;
	private Object defaultValue;

	ConfigEnum(String configKey, Object defaultValue) {
		this.configKey = configKey;
		this.defaultValue = defaultValue;
	}

	public String getConfigKey() {
		return configKey;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}
}
