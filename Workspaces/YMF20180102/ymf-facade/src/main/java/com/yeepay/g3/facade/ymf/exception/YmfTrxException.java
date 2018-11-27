package com.yeepay.g3.facade.ymf.exception;

import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;

/**
 * 交易业务提示码
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 16/10/24
 * @Time: 下午5:24
 */
public class YmfTrxException extends Exception {

    private static final long serialVersionUID = 3720622659489663641L;

    private TrxCode trxCode;

    public YmfTrxException(TrxCode trxCode) {
        super(trxCode.toPromptMsg());
        this.trxCode = trxCode;
    }

    public YmfTrxException(TrxCode trxCode,String message) {
        super(trxCode.toPromptMsg() + "." + message);
        this.trxCode = trxCode ;
    }

    public String getCode() {
        return trxCode.getCode();
    }

    public TrxCode getTrxCode() {
        return trxCode;
    }

    @Override
    public String toString() {
        return super.toString() ;
    }

    /**
     * 系统异常,请稍后重试  1000
     */
    public static final YmfTrxException SYSTEM_ERROR = new YmfTrxException(TrxCode.T1000) ;
    /**
     * 验证签名失败  1001
     */
    public static final YmfTrxException CHECK_SIGN_ERROR = new YmfTrxException(TrxCode.T1001) ;
    /**
     * 二维码不存在或已失效  1002
     */
    public static final YmfTrxException QRCODE_INVALID_ERROR = new YmfTrxException(TrxCode.T1002) ;
    /**
     * 该商户已关闭或暂停交易,请稍后重试  1003
     */
    public static final YmfTrxException CUSTOMER_INVALID_ERROR = new YmfTrxException(TrxCode.T1003) ;
    /**
     * 公众号参数配置错误,请联系易宝支付  1004
     */
    public static final YmfTrxException GONGZHONGHAO_PARAM_ERROR = new YmfTrxException(TrxCode.T1004) ;
    /**
     * 网络异常或超时,请稍后重试  1005
     */
    public static final YmfTrxException CONNECTION_TIMEOUT_ERROR = new YmfTrxException(TrxCode.T1005) ;
    /**
     * 参数不完整  1006
     */
    public static final YmfTrxException PARAMS_INVALID_ERROR = new YmfTrxException(TrxCode.T1006) ;
    /**
     * 调用COD接口异常,请稍后重试  1007
     */
    public static final YmfTrxException COD_INTERFACE_ERROR = new YmfTrxException(TrxCode.T1007) ;
    /**
     * 该订单已支付  1008
     */
    public static final YmfTrxException ORDER_COMMPLETE_ERROR = new YmfTrxException(TrxCode.T1008) ;
    /**
     * 该订单超时失效,请重新生成订单号  1009
     */
    public static final YmfTrxException ORDER_TIMEOUT_ERROR = new YmfTrxException(TrxCode.T1009) ;
    /**
     * 此订单下单账号与支付账号不一致，请使用下单微信账号支付  1010
     */
    public static final YmfTrxException WECHAT_ACCOUNT_ERROR = new YmfTrxException(TrxCode.T1010) ;
    /**
     * 收银台下单异常,请稍后重试  1011
     */
    public static final YmfTrxException CASHIER_ORDER_ERROR = new YmfTrxException(TrxCode.T1011) ;
    /**
     * 商户未开通支付权限，请联系易宝支付  1012
     */
    public static final YmfTrxException CUSTOMER_AUTH_ERROR = new YmfTrxException(TrxCode.T1012) ;
    /**
     * 商户商品类别码未配置,请联系易宝支付. 1013
     */
    public static final YmfTrxException INDUSTRY_CATALOG_ERROR = new YmfTrxException(TrxCode.T1013) ;
    /**
     * 获取远程服务失败 1014
     */
    public static final YmfTrxException RMI_INIT_ERROR = new YmfTrxException(TrxCode.T1014) ;
    /**
     * 商户配置应用版本异常,请联系客服! 1015
     */
    public static final YmfTrxException APPTYPE_CONFIG_ERROR = new YmfTrxException(TrxCode.T1015) ;
    /**
     * 类型转换错误 1016
     */
    public static final YmfTrxException CLASS_CAST_ERROR = new YmfTrxException(TrxCode.T1016) ;
    /**
     * 二维码未开通支付版本 1017
     */
    public static final YmfTrxException QRCODE_APPTYPE_ERROR = new YmfTrxException(TrxCode.T1017) ;
    /**
     * 二维码未开通支付版本 1018  和1012有点重复
     */
    public static final YmfTrxException TRX_AUTH_ERROR = new YmfTrxException(TrxCode.T1018) ;
    /**
     * 二维码未绑定商户,请联系客服  1019
     */
    public static final YmfTrxException QRCODE_NOT_BIND_ERROR = new YmfTrxException(TrxCode.T1019) ;
    /**
     * 采购数量超限,最多采购200个  1020
     */
    public static final YmfTrxException QRCODE_LIMIT_ERROR = new YmfTrxException(TrxCode.T1020) ;
    /**
     * 未查询到代理商信息! 1020
     */
    public static final YmfTrxException AGENT_NOTFOUND_ERROR = new YmfTrxException(TrxCode.T1021) ;
    /**
     * 二维码已绑定! 1022
     */
    public static final YmfTrxException QRCODE_ALREADY_BIND_ERROR = new YmfTrxException(TrxCode.T1022) ;
    /**
     * 商户未配置,请联系客服 1023
     */
    public static final YmfTrxException CUSTOMER_NOT_CONFIG_ERROR = new YmfTrxException(TrxCode.T1023) ;
    /**
     * 商户未配置支付方式 1024
     */
    public static final YmfTrxException CUSTOMER_NOTCONFIG_PAYTYPE_ERROR = new YmfTrxException(TrxCode.T1024) ;
    /**
     * 商户未配置支付方式 1025
     */
    public static final YmfTrxException CUSTOMER_NOT_FOUND_ERROR = new YmfTrxException(TrxCode.T1025) ;
    /**
     * 业务方已存在 1026
     */
    public static final YmfTrxException BUSINESS_ALREADY_EXIST_ERROR = new YmfTrxException(TrxCode.T1026) ;
    /**
     * 商户已开通来客产品 1027
     */
    public static final YmfTrxException CUSTOMER_OPEN_LAIKE_ERROR = new YmfTrxException(TrxCode.T1027) ;
    /**
     * 商户应用类型未配置 1028
     */
    public static final YmfTrxException CUSTOMER_APPTYPE_ERROR = new YmfTrxException(TrxCode.T1028) ;
    /**
     * 订单中心下单异常,请稍后重试 1031
     */
    public static final YmfTrxException OPR_CREATE_ORDER_ERROR = new YmfTrxException(TrxCode.T1031) ;
    /**
     * YOP签名或验签失败
     */
    public static final YmfTrxException YOP_SIGN_ERROR = new YmfTrxException(TrxCode.T1032) ;
    /**
     * 客户中心查询商户关系失败
     */
    public static final YmfTrxException MER_CENTER_QUERY_ERROR = new YmfTrxException(TrxCode.T1033) ;


    public static void main(String[] args) {
        System.out.println(new YmfTrxException(TrxCode.T1001).getMessage()) ;
        System.out.println(new YmfTrxException(TrxCode.T1001,"商编不能为空"));
    }
}
