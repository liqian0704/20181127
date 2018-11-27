package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.AppVersionEntity;
import com.yeepay.g3.facade.laike.enumtype.AppRoleEnum;
import com.yeepay.g3.facade.laike.enumtype.VersionPlatform;

/**
 * @Description: 版本service
 * @Author: zhaoyu.cui
 * @Date: 16/10/26
 * @Time: 下午2:17
 */
public interface AppVersionService {

    /**
     * 创建新版
     *
     * @param appVersionEntity
     */
    void save(AppVersionEntity appVersionEntity);

    /**
     * 根据平台和用户查询最新版（app）
     *
     * @param platformEnum
     * @param roleEnum
     * @return
     */
    AppVersionEntity findNewByRoleAndPlatform(VersionPlatform platformEnum, AppRoleEnum roleEnum);

    /**
     * 跟新url、操作员等上传信息
     *
     * @param appVersionEntity
     */
    void updateUrl(AppVersionEntity appVersionEntity);

    /**
     * 根据版本id查询
     *
     * @param id
     * @return
     */
    AppVersionEntity findById(String id);
}
