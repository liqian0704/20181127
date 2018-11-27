package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.core.laike.biz.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractFacade {

    @Autowired
    protected UserAuthBiz userAuthBiz;

    @Autowired
    protected PushMsgBiz pushMsgBiz;

    @Autowired
    protected QueryBiz queryBiz;

    @Autowired
    protected AppVersionBiz appVersionBiz;

    @Autowired
    protected AppNotifyBiz appNotifyBiz;

    @Autowired
    protected QRCodePayBiz qrCodePayBiz;

    @Autowired
    protected OpenAccountBiz openAccountBiz;

    @Autowired
    protected AppErrorMsgBiz appErrorMsgBiz;

    @Autowired
    protected LikerRiskParamBiz likerRiskParamBiz;

    @Autowired
    protected MerchantManageBiz merchantManageBiz;

    @Autowired
    protected AppDeviceBiz appDeviceBiz;

    @Autowired
    protected CreditBiz creditBiz;

	@Autowired
	protected SettleS0Biz settleS0Biz;

	@Autowired
	protected ScheduleBiz scheduleBiz;

	@Autowired
	protected ApplyBiz applyBiz;

	@Autowired
	protected QueryPageBiz queryPageBiz;

    @Autowired
    protected AllianceAccountBiz allianceAccountBiz;

    @Autowired
    protected OfficialManageBiz officialManageBiz;

    @Autowired
    protected UserPermissionBiz userPermissionBiz;

    @Autowired
    protected NewOpenAccountBiz newOpenAccountBiz;
}
