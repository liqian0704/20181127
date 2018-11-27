package com.yeepay.g3.core.laike.biz;

import com.yeepay.g3.core.laike.service.*;
import com.yeepay.g3.utils.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/10/9
 * @Time: 下午5:23
 */
public class AbstractBiz {

    @Autowired
    protected UserService userService;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected AccountOpenService accountOpenService;

    @Autowired
    protected AttachmentService attachmentService;

    @Autowired
    protected SecurityControlService securityControlService;

    @Autowired
    protected SmsCodeService smsCodeService;

    @Autowired
    protected NotifyService notifyService;

    @Autowired
    protected PushMsgService pushMsgService;

    @Autowired
    protected JPushService jPushService;

    @Autowired
    protected AppVersionService appVersionService;

    @Qualifier("likerQueryService")
    @Autowired
    protected QueryService likerQueryService;

    @Autowired
    protected AgentRelationService agentRelationService;

    @Autowired
    protected CSMerchantService csMerchantService;

    @Autowired
    protected QRPayService qrPayService;

    @Autowired
    protected BankCodeInfoService bankCodeInfoService;

    @Autowired
    protected AccountManageService accountManageService;

    @Autowired
    protected YopOauthService yopOauthService;

    @Autowired
    protected AppErrorMsgService appErrorMsgService;

    @Autowired
    protected UploadFileService uploadFileService;

    @Autowired
    protected PlistFileGenerateService plistFileGenerateService;

    @Autowired
    protected DeviceService deviceService;

    @Autowired
    protected CreditService creditService;

    @Autowired
    protected S0RecordService s0RecordService;

    @Autowired
    protected CardService cardService;

    @Autowired
    protected SettleS0Service settleS0Service;

	@Autowired
	protected OperateRecodeService operateRecodeService;

    @Autowired
    protected OCRService ocrService;

	@Autowired
	protected AllianceService allianceService;

    @Autowired
    protected CalFeeService calFeeService;

    @Autowired
    protected TradeLimitService tradeLimitService;

    @Autowired
    protected RoleFuncService roleFuncService;

    @Autowired
    protected FunctionService functionService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected LbsService lbsService;
}
