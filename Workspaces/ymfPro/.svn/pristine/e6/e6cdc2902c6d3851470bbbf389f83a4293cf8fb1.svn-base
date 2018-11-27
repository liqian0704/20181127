package com.yeepay.g3.core.laike.service;

import com.yeepay.g3.common.Amount;
import com.yeepay.g3.core.laike.entity.AccountOpenEntity;
import com.yeepay.g3.facade.laike.enumtype.BoolEnum;
import com.yeepay.g3.facade.laike.enumtype.CompanyTypeEnum;
import com.yeepay.g3.facade.ymf.dto.laike.PassivePayResponseDTO;
import com.yeepay.g3.facade.ymf.dto.laike.ScanQrCodeResponseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaySource;

import java.util.Map;

/**
 * Description: 二维码支付service
 * Author: jiawen.huang
 * Date: 16/11/28
 * Time: 18:26
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public interface QRPayService {

	/**
	 * 绑定商户台签（物料）
	 *
	 * @param accountOpenEntity
	 */
	void bindQRCodeBoard(AccountOpenEntity accountOpenEntity);

	/**
	 * 二维码支付
	 *
	 * @param orderAmount    订单金额
	 * @param gratuityAmount 打赏金额
	 * @param paySource      支付方式
	 * @param merchantNo     商编
	 * @param merchantName   名称
	 * @param ip
	 * @return
	 */
	@Deprecated
    ScanQrCodeResponseDTO generatePayQR(Amount orderAmount, Amount gratuityAmount, PaySource paySource, String merchantNo, String merchantName, String ip, CompanyTypeEnum companyTypeEnum, BoolEnum s0Balance);

	/**
	 * 多码合一
	 *
	 * @param memberNo       三代会员号
	 * @param orderAmount
	 * @param gratuityAmount
	 * @param merchantNo
	 * @param merchantName   名称
	 * @param notSelf        false：商家一键 true：客人支付
	 * @param ip
	 * @return
	 */
    ScanQrCodeResponseDTO generateUnionPayQR(String memberNo, Amount orderAmount, PaySource paySource, Amount gratuityAmount, String merchantNo, String merchantName, boolean notSelf, String ip, CompanyTypeEnum companyTypeEnum, BoolEnum s0Balance);

	/**
	 * 主扫
	 *
	 * @param orderAmount    订单金额
	 * @param gratuityAmount 打赏金额
	 * @param userPayCode    收款码
	 * @param merchantNo     商编
	 * @param merchantName   名称
	 * @param deviceSn       设备号
	 * @param ip
	 * @return
	 */
	PassivePayResponseDTO passivePay(Amount orderAmount, Amount gratuityAmount, String userPayCode, String merchantNo, String merchantName, String ip, String deviceSn, CompanyTypeEnum companyTypeEnum);

	/**
	 * 查询 户的台签码
	 *
	 * @param merchantNo
	 * @return
	 */
	Map<String, String> getQrCodeByCustomer(String merchantNo);
}
