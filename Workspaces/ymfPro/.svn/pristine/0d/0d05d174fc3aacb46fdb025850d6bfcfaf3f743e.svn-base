package com.yeepay.g3.core.laike.aop;


import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.core.laike.service.AppVersionService;
import com.yeepay.g3.facade.laike.dto.BaseRequest;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: 版本拦截,看来者何人
 * Author: jiawen.huang
 * Date: 2017/6/15
 * Time: 14:18
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
@Aspect
@Order(2)
public class VersionIntercepter {

	private static Logger LOGGER = LoggerFactory.getLogger(VersionIntercepter.class);

	@Autowired
	private AppVersionService appVersionService;

	@Around(value = "execution(* com.yeepay.g3.facade.laike.facade.app..*.*(..))")
	public Object facadeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String versionId = ((BaseRequest) args[0]).getVersionId();
		String oauthMemberNo = ((BaseRequest) args[0]).getOauthMemberNo();
		String memberNo = ((BaseRequest) args[0]).getMemberNo();
		if (StringUtils.isNotBlank(versionId)) {
			AppVersionEntity appVersionEntity = appVersionService.findById(versionId);
			if (appVersionEntity.getRoleType().equals(AppRoleEnum.ALLIANCE)
					|| appVersionEntity.getRoleType().equals(AppRoleEnum.ALLIANCE_TEST)) {
				((BaseRequest) args[0]).setAppSourceEnum(AppSourceEnum.ALLIANCE);
			} else {
				((BaseRequest) args[0]).setAppSourceEnum(AppSourceEnum.LIKER);
			}
			if ((Boolean) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_OAUTH_CHECK)) {
				if (StringUtils.isNotBlank(oauthMemberNo)) {
					if (!memberNo.equals(oauthMemberNo)) {
						throw new LaikeSysException(ErrorCode.USER_PERMISSION_DENY);
					}
				}
			}
		}
		LOGGER.info("[laike_sys] - [VersionIntercepter] - [" + ((BaseRequest) args[0]).getAppSourceEnum() + "]");
		return joinPoint.proceed();
	}
}
