package com.yeepay.g3.core.laike.facade.impl;

import com.yeepay.g3.core.laike.cache.CacheUtil;
import com.yeepay.g3.facade.laike.enumtype.ExternalSystem;
import com.yeepay.g3.facade.laike.facade.LikerCacheFacade;
import org.springframework.stereotype.Component;

/**
 * Description: 清空缓存专用
 * Author: jiawen.huang
 * Date: 2017/8/7
 * Time: 12:29
 * Version: 1.0
 * Copyright © 2017 YeePay.com All rights reserved.
 */
@Component
public class LikerCacheFacadeImpl extends AbstractFacade implements LikerCacheFacade {

	@Override
	public void cleanAllCache() {
		CacheUtil.cleanAll(ExternalSystem.LIKER.getKey());
	}
}
