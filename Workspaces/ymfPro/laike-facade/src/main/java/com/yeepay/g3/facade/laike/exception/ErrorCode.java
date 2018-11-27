package com.yeepay.g3.facade.laike.exception;

/**
 * Description:
 * Author: jiawen.huang
 * Date: 16/9/14
 * Time: 18:27
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class ErrorCode {

	/**
	 * 对外错误码
	 */
	//系统异常
	public static final String SYSTEM_EXCEPTION = "L10001";

	//参数异常
	public static final String PARAM_EXCEPTION = "L10002";

	//远程服务连接异常
	public static final String HESSIAN_CONNECT_EXCEPTION = "L10003";

	//登陆鉴权回参空
	public static final String GENERATE_TOKEN_NULL = "L10004";

	//登陆鉴权失败
	public static final String GENERATE_TOKEN_EXCEPTION = "L10005";

	//OAUTH注销失败
	public static final String REVOKE_TOKEN_EXCEPTION = "L10006";

	//调用会员异常
	public static final String MEMBER_SYS_EXCEPTION = "L10007";

	//会员修改密码服务异常
	public static final String MEMBER_CHANGE_PWD_EXCEPTION = "L10008";

	//会员密码验证服务异常
	public static final String MEMBER_PWD_CHECK_EXCEPTION = "L10009";

	//会员查询服务异常
	public static final String MEMBER_QUERY_EXCEPTION = "L10010";

	//会员存在，请直接用三代会员手机号密码登陆
	public static final String USER_LOGIN_UNION_MEMBER = "L10011";

	//可能状态没注册完成
	public static final String USER_PHONE_REPEAT = "L10012";

	//用户不存在
	public static final String USER_NOT_EXIST = "L10013";

	//用户已注册
	public static final String USER_HAS_REGISTER = "L10014";

	//用户验证验证码未通过
	public static final String USER_UN_REGISTER = "L10015";

	//验证验证码时用户不存在，也就是说根本没发注册验证码
	public static final String USER_UNSEND_SMS = "L10016";

	//短信发送频率超限
	public static final String SMS_SEND_LIMIT = "L10017";

    //用户密码或用户名错误
    public static final String USER_LOGIN_PWD_ERROR = "L10018";

	//短信验证失败
	public static final String SMS_VERIFY_ERROR = "L10019";

	//找回密码次数超限
	public static final String FIND_PWD_LIMIT = "L10020";

	//验证次数超限
	public static final String VERIFY_SMS_LIMIT = "L10021";

	//登陆密码错误次数超限
	public static final String LOGIN_LIMIT = "L10022";

	//同一用户不能重复发起开户申请
	public static final String USER_ACCOUNT_OPEN_REPEAT = "L10023";

	//开户单不存在
	public static final String ACCOUNT_NOT_EXIST = "L10024";

	//代理商系统未知异常
	public static final String AGENT_BOSS_UNKNOW_EXCEPTION = "L10025";

	//易码付系统未知异常
	public static final String YMF_UNKNOW_EXCEPTION = "L10026";

	//无权限生成二维码（没有关联商户）
	public static final String QR_PAY_PERMISSION_DENY = "L10027";

	//不能换邀请方式了
	public static final String CHANGE_INVITE_TYPE_DENY = "L10028";

	//同一个会话中，设备信息变更了，怀疑盗号
	public static final String USER_DEVICE_CHANGE_WARM = "L10029";

	//客户中心未知异常
	public static final String MERCHANT_UNKNOW_EXCEPTION = "L10030";

	//对应账户的附件创建重复
	public static final String ATTACH_REPEAT = "L10031";

	//代理商状态未启用或者代理商没有开来客产品
	public static final String AGENT_INACTIVE_OR_UNPERMISSION = "L10032";

	//影音信息转换json异常
	public static final String ATTACH_ATTRIBUTE_ILLEGAL = "L10033";

	//附件不存在
	public static final String ATTACH_NOT_EXIST = "L10034";

	//入网响应参数转换异常
	public static final String ATTACH_CONVERT_DTO_EXCEP = "L10035";

	//已经提交客户中心不能改资料
	public static final String COMMIT_INFO_DENY = "L10036";

	//结算资料已经提交客户中心不能改资料
	public static final String COMMIT_SETTLE_DENY = "L10037";

	//附件资料已经提交客户中心不能改资料
	public static final String COMMIT_IMG_DENY = "L10038";

	//查询账户余额异常
	public static final String ACCOUNT_BALANCE_EXCEPTION = "L10039";

	//开户状态未完成不能进行某操作
	public static final String ACCOUNT_STATUS_DENY = "L10040";

	//验证码过期or不存在
	public static final String SMS_EXPIRED_OR_UNFIND = "L10041";

	//邀请码入网没有台签
	public static final String UNHAVE_QR_PAY_BOARD = "L10042";

	//台签牌已经被注册
	public static final String ACCOUNT_SIGNEDPAPER_REGISTER = "L10043";

	//刷新tk未知异常
	public static final String REFRESH_TOKEN_EXCEPTION = "L10044";

	//tk失效无法刷新
	public static final String REFRESH_TOKEN_INVALID = "L10045";

	//收款码非法
	public static final String PASSIVE_PAY_SOURCE_ILLEGAL = "L10046";

	//设备查询异常
	public static final String DEVICE_QUERY_EXCEPTION = "L10047";

	//现金贷业务系统未知异常
	public static final String CFL_UNKNOW_EXCEPTION = "L10048";

	//该商户不能开通现金贷业务
	public static final String COMMIT_CREDIT_REQ_DENY = "L10049";

	//设备绑定关系不存在
	public static final String DEVICE_RELATION_EXCEPTION = "L10050";

	//S0当前记录存在，key冲突
	public static final String S0_RECORD_EXIST = "L10051";

	//s0记录不存在
	public static final String S0_RECORD_NOT_EXIST = "L10052";

    //开通日结通异常
    public static final String RJT_OPEN_EXCEPTION = "L10053";

    //发起日结通请求出现异常
	public static final String RJT_UNKNOW_EXCEPTION = "L10054";

	//代理商未开通秒到，请联系代理商
	public static final String AGENT_S0_STATUS_EXCEPTION = "L10055";

	//该产品码不存在
	public static final String PRODUCT_CODE_EXCEPTION = "L10056";

	//邀请方式不支持开通该业务
	public static final String INVITE_TYPE_ILLEGAL = "L10057";

	//用户状态不能开通秒到
	public static final String USER_STATUS_ILLEGAL = "L10058";

    //不支持该结算卡
    public static final String SETTLE_CARD_INVALID = "L10059";

	//T1结算金额未大于1000
	public static final String S0_SETTLE_AMOUNT_INVALID = "L10060";

	//没有绑卡记录
	public static final String BIND_CARD_NOT_EXSIT = "L10061";

	//非法提交
	public static final String COMMIT_IMG_ILLEGAL = "L10062";

	//商户类型不支持开通该业务
	public static final String COMPANY_TYPE_ILLEGAL = "L10063";

    //设置RJT费率失败
    public static final String MODIFY_RJT_FEE_DENY = "L10064";

    //用户操作记录已存在
    public static final String OPERATE_RECORD_EXIST = "L10065";

    //用户操作记录不存在
    public static final String OPERATE_RECORD_NOT_EXIST = "L10066";

	//导出报表异常
	public static final String GENERATE_REPORT_EXCEPTION = "L10067";

	//银行卡号不合法
	public static final String BANK_NO_ILLEGAL = "L10068";

	//银行子系统异常
	public static final String BANK_TRADE_UNKNOW_EXCEPTION = "L10069";

    //OCR系統异常
    public static final String OCR_UNKNOW_EXCEPTION = "L10070";

    //OCR识别失败,请重新拍照
    public static final String OCR_RECOGNIZE_FAIL = "L10071";

	//联盟异常
	public static final String ALLIANCE_UNKNOW_EXCEPTION = "L10072";

	//联盟邀请码状态异常
	public static final String ALLIANCE_INVITE_CODE_INACTIVE = "L10073";

    //开户已完成,不能进行该操作
    public static final String COMMIT_STATUS_ILLEGAL = "L10074";

    //状态有误,不能提交入网单
    public static final String ALLIANCE_OPEN_ACCOUNT_DENY = "L10075";

    //资质信息不足
    public static final String ALLIANCE_ATTACH_ILLEGAL = "L10076";

	//分组权限拒绝
	public static final String USER_PERMISSION_DENY = "L10077";

    //提交类型有误
    public static final String COMMIT_ATTACH_ILLEGAL = "L10078";

    //登录密码有误
    public static final String USER_PWD_ERROR = "L10079";

	//登录密码修改原密码验证超过限制
	public static final String LOGIN_PWD_LIMIT = "L10080";

	//单笔交易金额超限
	public static final String OVER_ORDER_LIMIT = "L10081";

    //不能使用s0服务
    public static final String S0_BALANCE_DENY = "L10082";

	//功能重复
	public static final String FUNCTION_REPEAT = "";

	//功能不存在
	public static final String FUNCTION_NOT_EXIST = "";

	//功能禁用
	public static final String FUNCTION_NOT_AVAILABLE = "";

	//角色不存在
	public static final String ROLE_NOT_EXIST = "";

	//上级不存在
	public static final String ROLE_PARENT_NOT_EXIST = "";

	//角色重复
	public static final String ROLE_REPEAT = "";

	//角色功能关系重复
	public static final String ROLE_FUNC_REPEAT = "";

	//角色功能关系不存在
	public static final String ROLE_FUNC_NOT_EXIST = "";

	public static final String CONVERT_DTO_EXCEPTION = "";

	//不支持该卡种
	public static final String CARD_TYPE_IS_ILLEGAL = "L10083";

	//未知卡信息
	public static final String UNKNOWN_CARD_INFO = "L10084";

	//费率信息不存在
	public static final String FEE_INFO_NOT_EXIST = "L10085";

	//计费系统未知异常
	public static final String CAL_FEE_UNKNOW_EXCEPTION = "L10086";

	//风控系统未知异常
	public static final String RISK_CONTROL_UNKNOW_EXCEPTION = "L10087";

	//分享链接不可用
	public static final String SHARE_PERMISSION_DENY = "L10088";

	//数据跑丢了，过一会再来吧
	public static final String CAL_AND_LIMIT_EXCEPTION = "L10089";

	//定位信息不存在
	public static final String LBS_INFO_NOT_EXIST = "L10090";

	//信息审核中，暂时无法使用此功能
	public static final String ACCOUNT_STATUS_NOT_AVAILABLE = "L10091";

	/**
	 * 内部错误码
	 */
	//通知系统异常
	public static final String NOTIFY_UNKNOWN_EXCEPTION = "L20001";

	//通知系统发送频率超限
	public static final String NOTIFY_FREQUENCY_LIMIT = "L20002";

	//通知系统接收人有误
	public static final String NOTIFY_INVALID_RECIPIENT = "L20003";

	//创建消息索引冲突
	public static final String MSG_CREATE_REPEAT = "L20004";

	//消息不存在或者因为状态而更新失败
	public static final String MSG_UPDATE_STATUS_ERROR = "L20005";

	//向用户推送连接失败
	public static final String JPUSH_SYS_CONNECT_ERROR = "L20006";

	//推送请求异常
	public static final String JPUSH_SYS_API_ERROR = "L20007";

	//没有满足条件（会员号）的推送目标
	public static final String JPUSH_USER_UNFIND = "L20008";

	//消息体超长，Android4000字节，iOS2000字节
	public static final String JPUSH_STRUCTURE_OVER_LIMIT = "L20009";

	//消息不存在
	public static final String MSG_NOT_EXIST = "L20010";

	//极光未知异常
	public static final String JPUSH_SYS_UNKNOW_ERROR = "L20011";

	//服务器端内部逻辑错误，稍后重试
	public static final String JPUSH_INNER_EXCEPTION = "L20012";

	//参数值不合法
	public static final String JPUSH_ILLEGAL_ARGUMENT = "L20013";

	//版本已存在
	public static final String APP_VERSION_EXIST = "L20014";

	//版本不存在
	public static final String APP_VERSION_NO_EXIST = "L20015";

	//当前为最新版，无需更新
	public static final String APP_DONT_NEED_UPDATE = "L20016";

	//版本编码非法，新版本的版本编码不能比老版本低
	public static final String APP_VERSION_CODE_INVALID = "L20017";

	//该开户单未关联用户，无法推送支付消息
	public static final String NULL_ACCOUNT_TO_PUSH = "L20018";

	//找不到要推送的老板
	public static final String NULL_USER_TO_PUSH = "L20019";

	//用户不是扫台签入网的，不需要绑定台签
	public static final String QR_PAY_BOARD_BIND_DENY = "L20020";

	//入网响应参数转换异常
	public static final String ACCOUNT_CONVERT_DTO_EXCEP = "L20021";

	//单子状态没提交，不应该接收回调
	public static final String GET_CALLBACK_DENY = "L20022";

	//上传文件找不到
	public static final String UPLOAD_FILE_NOTFOUND_ERROR = "L20025";

	//ftp组件报错
	public static final String UPLOAD_FILE_FTP_EXCEPTION = "L20026";

	//本地文件加载异常
	public static final String RESOURCE_NOT_FOUND_EXCEPTION = "L20027";

	//替换plist配置时异常
	public static final String REPLACE_PLIST_EXCEPTION = "L20028";

	//绑定业务员
	public static final String AGENT_BIND_BIZMAN_EXCEPTION = "L20029";

	//OP表单注册商户失败
	public static final String OP_REGISTER_MERCHANT_FAIL = "L20030";

	//手机号不合法
	public static final String PHONE_ILLEGAL_ARGUMENT = "L20031";

	//用户开户未完成不应该收到交易信息
	public static final String ACCOUNT_INVALID_TO_PUSH = "L20032";

	//该商户未开通来客业务
	public static final String MERCHANT_NOT_EXIST_IN_YMF = "L20033";

	//该商编已注册过来客，不能重复注册
	public static final String MERCHANT_IS_EXIST = "L20034";

	//入网请求号不存在
	public static final String OPEN_ID_NOT_EXIST = "L20035";

	//类型不存在
	public static final String TYPE_NOT_EXIST = "L20036";

    //查询绑卡关系参数异常
    public static final String NC_MEMBER_PARAM_ERROR = "L20037";

    //一键用户中心系统异常
    public static final String NC_MEBER_UNKNOW_EXCEPTION = "L20038";

	//找不到开户单或状态有误
	public static final String UPDATE_ALLIANCE_ACCOUNT_DENY = "20039";

	//信息不能为空
    public static final String REMARK_IS_ILLEGAL = "20040";

    //账户类型有误
    public static final String ACCOUNT_TYPE_ILLEGAL = "20041";

    //商编不存在
    public static final String MERCHANT_IS_NOT_EXIST = "L20042";

	//LBS请求异常
	public static final String LBS_REQ_EXCEPTION = "L20043";

	//LBS未知异常
	public static final String LBS_UNKNOW_EXCEPTION = "L20044";

	/**
	 * 外部系统错误码
	 */
	//必填参数异常
	public static final String MERCHANT_CENTER_PARAM_ERROR = "L30042";

	//参数验证失败
	public static final String MERCHANT_CENTER_PARAM_INVALID = "L30043";

	//用户注册时会员中心异常
	public static final String MEMBER_REGISTER_EXCEPTION = "L30064";

	//对接系统未知的默认错误码
	public static final String HESSIAN_UNKNOW_EXCEPTION = "L30999";

	/**
	 * Web 错误
	 */
	//请勿重复提交
	public static final String REPEAT_SUBMIT_EXCEPTION = "L50001";

	//请求过期，请刷新重试
	public static final String REQUEST_EXPIRD_EXCEPTION = "L50002";

}
