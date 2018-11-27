package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.facade.laike.enumtype.AppSourceEnum;
import com.yeepay.g3.facade.yop.oauth2.dto.OAuth2AccessToken;


/**
 * Description: yop-oauth
 * Author: jiawen.huang
 * Date: 17/1/11
 * Time: 10:53
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
public interface YopOauthService {

	OAuth2AccessToken getGenerateToken(String memberNo, AppSourceEnum appSourceEnum);

	OAuth2AccessToken refreshToken(String grantType, String refreshToken);

	void revokeToken(String token);
}
