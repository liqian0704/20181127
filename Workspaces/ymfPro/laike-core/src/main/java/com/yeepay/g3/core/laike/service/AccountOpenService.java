package com.yeepay.g3.core.laike.service;


import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.enumtype.LOLOpenStatus;

import java.util.List;

/**
 * Description: 用户账户类服务层接口
 * Author: jiawen.huang
 * Date: 16/12/1
 * Time: 16:47
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface AccountOpenService {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    AccountOpenEntity findById(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     * @throw ACCOUNT_NOT_EXIST
     */
    AccountOpenEntity findExistById(String id);

    /**
     * 根据实体创建或者更新
     *
     * @param entity
     * @throw ACCOUNT_NOT_EXIST;USER_ACCOUNT_OPEN_REPEAT
     */
    void createAndUpdate(AccountOpenEntity entity);

    /**
     * 根据用户号查询账户
     *
     * @param memberNo
     * @return
     */
    AccountOpenEntity findByMemberNo(String memberNo);

    /**
     * 查询可以交易的
     *
     * @param accountId
     * @return
     * @throw ACCOUNT_STATUS_DENY, ACCOUNT_NOT_EXIST
     */
    AccountOpenEntity findPayableById(String accountId);

    /**
     * 根据商户号查询
     *
     * @param merchantNo
     * @return
     */
    AccountOpenEntity findByMerchantNo(String merchantNo);

    /**
     * 查找可推送账户
     *
     * @param merchantNo
     * @return
     * @throw NULL_ACCOUNT_TO_PUSH;ACCOUNT_INVALID_TO_PUSH
     */
    AccountOpenEntity findAccount2Push(String merchantNo);

    /**
     * op入网,初始化开户表
     *
     * @param entity
     */
    void createAccount(AccountOpenEntity entity);

    /**
     * 展业app,按状态查询
     *
     * @param lolOpenStatus
     * @return
     */
    List<AccountOpenEntity> findByLolStatus(LOLOpenStatus lolOpenStatus);
}
