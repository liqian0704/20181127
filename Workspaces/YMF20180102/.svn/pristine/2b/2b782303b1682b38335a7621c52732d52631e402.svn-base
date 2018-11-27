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
	/**
	 * wap微信H5支付
	 */
	@Deprecated
	WECHAT_C_PAYMENT("20","wap微信H5支付"),
    /**
     * 微信公众号支付
     */
	@Deprecated
	WECHAT_A_PAYMENT("21","微信公众号支付"),
	/**
	 * 银行卡WAP支付
	 */
	@Deprecated
	NC_PAYMENT("23","银行卡WAP支付") ,
	@Deprecated
	WECHAT_D_PAYMENT("","扫码-WX") ,
	@Deprecated
	ALIPAY_B_PAYMENT("","扫码—ZFB") ,
	@Deprecated
	ALIPAY_A_PAYMENT("28","支付宝支付"),
	CFL_A_PAYMENT("33","分期支付"),
	//@TODO 以下支付类型 为大算支付类型,后期切大算后,此段以上的支付类型会废弃
	WALLET_PAY("","钱包支付"),
	MERCHANT_SCAN_PAY("","商家扫码支付"),
	NCPAY_PAY("","一键支付"),
	IOU_PAY("","白条支付"),
	INSTALMENTS_3_PAY("","分期3期支付"),
	INSTALMENTS_6_PAY("","分期6期支付"),
	INSTALMENTS_9_PAY("","分期9期支付"),
	INSTALMENTS_12_PAY("","分期12期支付"),
	INSTALMENTS_24_PAY("","分期24期支付"),
	WECHAT_GZH_PAY("","公众号支付");

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
