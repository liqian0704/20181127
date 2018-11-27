package com.yeepay.g3.facade.laike.enumtype;

import java.util.HashMap;
import java.util.Map;

public enum ExternalSystem {

	LIKER("laike-hessian", "来客系统"),
	OAUTH("oauth-hessian", "认证系统"),
	G3MEMBER("member-hessian", "会员系统"),
	YMF("ymf-hessian", "易码付"),
	AGENT_BOSS("agent-boss", "AGENT_INVITE"),//value为configEnum值，其中配置了非soa服务的地址
	CS_MERCHANT("cs-merchant", "客户中心"),
	BANK_INFO("bank-info", "打款中心"),
	ACCOUNT_MANAGE("account-manage", "商户账户"),
    NOTIFY("notifier-hessian", "通知系统"),
    CONSUMER_LOAN("consumer-loan", "现金贷"),
    NC_MEMBER("nc-member-hessian", "卡账户"),
	RJT_S0("rjt-s0-web", "S0_OPEN_URL"),//value为configEnum值，其中配置了url
    RJT("rjt-txp-service", "日结通"),
    BANK_TRADE("bank-trade", "银行子系统"),
	ALLIANCE("alliance", "联盟分销"),
    OCR("ocr-hessian", "OCR系统"),
    CAL_FEE("cal-fee-hessian", "计费系统"),
    RISK_CONTROL("risk-control", "风控系统"),
    BAIDU_LBS("baidu-lbs-web", "BAIDU_LBS_URL");//value为configEnum值，其中配置了url

	private static final Map<String, ExternalSystem> KEY_MAP = new HashMap<String, ExternalSystem>();

	static {
		for (ExternalSystem item : ExternalSystem.values()) {
			KEY_MAP.put(item.key, item);
		}
	}

	private String key;

	private String value;

	ExternalSystem(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static ExternalSystem parse(String key) {
		return KEY_MAP.get(key);
	}

	public static Map<String, ExternalSystem> getKeyMap() {
		return KEY_MAP;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
