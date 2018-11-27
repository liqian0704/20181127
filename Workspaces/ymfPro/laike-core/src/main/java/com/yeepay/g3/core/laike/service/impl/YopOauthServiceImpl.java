package com.yeepay.g3.core.laike.service.impl;

import com.yeepay.g3.common.laike.utils.config.ConfigEnum;
import com.yeepay.g3.common.laike.utils.config.ConfigUtils;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.YopOauthService;
import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import com.yeepay.g3.facade.yop.oauth2.dto.OAuth2AccessToken;
import com.yeepay.g3.facade.yop.oauth2.exception.InvalidTokenException;
import com.yeepay.g3.utils.common.StringUtils;
import org.springframework.stereotype.Service;


/**
 * Description:
 * Author: jiawen.huang
 * Date: 17/1/11
 * Time: 10:56
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Service
public class YopOauthServiceImpl extends AbstractService implements YopOauthService {

	private static String LIKER_YOP_TOKEN_SCOPE = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_YOP_TOKEN_SCOPE);

	private static String LIKER_YOP_TOKEN_INTERVAL = (String) ConfigUtils.getSysConfigParam(ConfigEnum.LIKER_YOP_TOKEN_INTERVAL);

	@Override
	public OAuth2AccessToken getGenerateToken(String memberNo, AppSourceEnum appSourceEnum) {
		try {
			OAuth2AccessToken oAuth2AccessToken = tokenFacade.generateToken("password", appSourceEnum.getDisplayName(), memberNo,
					LIKER_YOP_TOKEN_INTERVAL, "", LIKER_YOP_TOKEN_SCOPE);
			if (StringUtils.isBlank(oAuth2AccessToken.getValue())
					|| StringUtils.isBlank(oAuth2AccessToken.getRefreshToken().getValue())) {
				throw new LaikeSysException(ErrorCode.GENERATE_TOKEN_NULL);
			}
			return oAuth2AccessToken;
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.GENERATE_TOKEN_EXCEPTION, e);
		}
	}

	@Override
	public OAuth2AccessToken refreshToken(String grantType, String refreshToken) {
		try {
			OAuth2AccessToken oAuth2AccessToken = tokenFacade.refreshToken("refresh_token",
					refreshToken, LIKER_YOP_TOKEN_SCOPE);
			return oAuth2AccessToken;
		} catch (InvalidTokenException e) {
			throw new LaikeSysException(ErrorCode.REFRESH_TOKEN_INVALID, e);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.REFRESH_TOKEN_EXCEPTION, e);
		}
	}

	@Override
	public void revokeToken(String token) {
		try {
			tokenFacade.revokeToken(token);
		} catch (Throwable e) {
			throw new LaikeSysException(ErrorCode.REVOKE_TOKEN_EXCEPTION, e);
		}
	}
}
