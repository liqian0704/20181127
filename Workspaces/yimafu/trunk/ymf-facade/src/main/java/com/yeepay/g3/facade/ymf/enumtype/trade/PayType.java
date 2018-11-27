package com.yeepay.g3.facade.ymf.enumtype.trade;

/**
 * 统一收银台支付方式（权限）
 * Created by aoick on 2016/8/28.
 */
/**
 * @author xiaobin.liu
 *
 */
public enum PayType {
	
	/**
	 * 20：wap微信H5支付，21：微信公众号支付，22：微信主扫支付(待定)，23：银行卡WAP支付 28:支付宝支付
	 */
//	payTypeMap.put("20", "WECHAT_C_PAYMENT");
//	payTypeMap.put("21", "WECHAT_A_PAYMENT");
//	payTypeMap.put("23", "NC_PAYMENT");

	/**
	 * wap微信H5支付
	 */
	WECHAT_C_PAYMENT("20","wap微信H5支付"),

    /**
     * 微信公众号支付
     */
	WECHAT_A_PAYMENT("21","微信公众号支付"),
    
	/**
	 * 银行卡WAP支付
	 */
	NC_PAYMENT("23","银行卡WAP支付") ,

	ALIPAY_A_PAYMENT("28","支付宝支付");

    private String displayName;
    private String code;

    PayType(String code,String displayName) {
    	this.code = code ;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public String getCode() {
        return code;
    }
    
    public static void main(String[] args) {
		System.out.println(NC_PAYMENT.toString().equals("NC_PAYMENT"));
		
		System.out.println(NC_PAYMENT.equals(PayType.valueOf("NC_PAYMENT1")));
	}
    
}
