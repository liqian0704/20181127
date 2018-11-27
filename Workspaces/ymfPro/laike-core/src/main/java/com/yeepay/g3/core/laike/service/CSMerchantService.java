package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.core.laike.entity.AttachmentEntity;

/**
 * Description: 客户中心接口
 * Author: jiawen.huang
 * Date: 16/12/5
 * Time: 15:30
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface CSMerchantService {

	/**
	 * 商户入网基本信息
	 *
	 * @param entity
	 * @return 更新后AccountOpenEntity
	 */
	AccountOpenEntity gatherBaseInfo(AccountOpenEntity entity);

	/**
	 * 资质信息上传
	 *
	 * @param entity
	 * @param submit 是否直接提交工单
	 * @return 更新后AccountOpenEntity
	 */
	AccountOpenEntity gatherBizInfo(AccountOpenEntity entity, boolean submit);

	/**
	 * 结算信息上传
	 *
	 * @param entity
	 * @param submit 是否直接提交工单
	 * @return 更新后AccountOpenEntity
	 */
	AccountOpenEntity gatherSettleInfo(AccountOpenEntity entity, boolean submit);

	/**
	 * 影音资料上传
	 *
	 * @param accountOpenEntity
	 * @param attachmentEntity
	 * @param submit 是否直接提交工单
	 * @return 更新后AccountOpenEntity
	 */
	AccountOpenEntity gatherImageInfo(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity, boolean submit);

    /**
     * 展业app入网信息上传
     *
     * @param accountOpenEntity
     * @param attachmentEntity
     * @return 更新后AccountOpenEntity
     */
    AccountOpenEntity gatherAllianceInfo(AccountOpenEntity accountOpenEntity, AttachmentEntity attachmentEntity);
}
