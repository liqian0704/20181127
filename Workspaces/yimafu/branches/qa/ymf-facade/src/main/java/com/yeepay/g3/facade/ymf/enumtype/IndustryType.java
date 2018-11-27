package com.yeepay.g3.facade.ymf.enumtype;

/**
 * 商户的行业别
 * 商户的行业编码
 * 	保险：6300
	教育：8299 
	商旅：4511
	电信：4814
	O2O：3101
 * @author xiaobin.liu
 *
 */
public enum IndustryType {
	
	INSURANCE("6300","保险"),
	Education("8299","教育"),
	TOURISM("4511","商旅"),
	TELECOM("4814","电信"),
	O2O("3101","O2O")
	;


    private String displayName;
    /**
     * 行业编码，传到统一收银台下单
     */
    private String industryCode;

    IndustryType(String industryCode,String displayName) {
        this.industryCode = industryCode;
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

	public String getIndustryCode() {
		return industryCode;
	}
	
	/**
	 * 获取行业对应的CODE
	 * @param industryName
	 * @return
	 */
	public static String getIndustryCode(String industryName) {
		if ("INSURANCE".equals(industryName)) {
			return INSURANCE.getIndustryCode() ;
		} else if ("Education".equals(industryName)) {
			return Education.getIndustryCode() ;
		} else if ("TOURISM".equals(industryName)) {
			return TOURISM.getIndustryCode() ;
		} else if ("TELECOM".equals(industryName)) {
			return TELECOM.getIndustryCode() ;
		} else if ("O2O".equals(industryName)) {
			return O2O.getIndustryCode() ;
		}
		return null ;
	}


}
