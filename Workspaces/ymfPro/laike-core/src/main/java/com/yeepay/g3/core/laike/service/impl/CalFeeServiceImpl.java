package com.yeepay.g3.core.laike.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yeepay.g3.core.laike.service.AbstractService;
import com.yeepay.g3.core.laike.service.CalFeeService;
import com.yeepay.g3.facade.fee.front.dto.CalFeeFormulaDTO;
import com.yeepay.g3.facade.fee.front.dto.CalFeeModelDTO;
import com.yeepay.g3.facade.fee.front.dto.CalFeeRuleDTO;
import com.yeepay.g3.facade.fee.front.enumtype.CalFeeOwnerSourceTypeEnum;
import com.yeepay.g3.facade.fee.front.enumtype.CalFeeRoleTypeEnum;
import com.yeepay.g3.facade.laike.dto.CalFeeInfo;
import com.yeepay.g3.facade.laike.dto.FeeRateDetail;
import com.yeepay.g3.facade.laike.enumtype.InviteType;
import com.yeepay.g3.facade.laike.enumtype.PayProductEnum;
import com.yeepay.g3.facade.laike.exception.ErrorCode;
import com.yeepay.g3.facade.laike.exception.LaikeSysException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Description: 计费服务层
 * Author: wei.li
 * Date: 17/7/27
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
@Service
public class CalFeeServiceImpl extends AbstractService implements CalFeeService {

    private static String DS_VENDORS_EDITION = "DSXTSB";//大算系统商版:代理版

    private static String DS_STANDARD_EDITION = "DSBZB";//大算标准版:非代理版


    @Override
    public List<CalFeeInfo> findCalFeeModel(String merchantNo, InviteType inviteType) {
        try {
            List<CalFeeInfo> list = Lists.newArrayList();
            String calFeeItemStr = (InviteType.DIRECT_SALE.equals(inviteType) || InviteType.SELLER_DIRECT_SALE.equals(inviteType) || InviteType.BIG_MERCHANT.equals(inviteType))
                    ? DS_STANDARD_EDITION : DS_VENDORS_EDITION;
            CalFeeModelDTO calFeeModelDTO = calFeeModelManagementFacade.findCalFeeModelDTO(merchantNo, CalFeeOwnerSourceTypeEnum.MERCHANT, calFeeItemStr, new Date());
            if (null != calFeeModelDTO && calFeeModelDTO.getRules().size() > 0) {
                for (CalFeeRuleDTO calFeeRuleDTO : calFeeModelDTO.getRules()) {
                    if (null != transformFee(calFeeRuleDTO) && null != transformFee(calFeeRuleDTO).getPayProduct()) {
                        list.add(transformFee(calFeeRuleDTO));
                    }
                }
                return mergeList(list);
            }
            throw new LaikeSysException(ErrorCode.FEE_INFO_NOT_EXIST);
        } catch (LaikeSysException e) {
            throw e;
        } catch (Throwable e) {
            throw new LaikeSysException(ErrorCode.CAL_FEE_UNKNOW_EXCEPTION, e);
        }
    }

    private CalFeeInfo transformFee(CalFeeRuleDTO calFeeRuleDTO) {
        CalFeeInfo calFeeInfo = new CalFeeInfo();
        List<FeeRateDetail> feeRateDetailList = Lists.newArrayList();
        List<CalFeeFormulaDTO> list = calFeeRuleDTO.getPolicys().get(CalFeeRoleTypeEnum.PAYEE).getFormulas();
        FeeRateDetail feeRateDetail = new FeeRateDetail();
        feeRateDetail.setFeeRate(transformPercent(list.get(0).getPercentFee()));
        if (null != list && list.size() > 0) {
            String s = calFeeRuleDTO.getPayProduct();
            if ("ZF_GZH".equals(s)) {
                calFeeInfo.setPayProduct(PayProductEnum.OFFICIAL_ACCOUNT_PAY);
                calFeeInfo.setPayProductName(PayProductEnum.OFFICIAL_ACCOUNT_PAY.getDisplayName());
                feeRateDetailList.add(feeRateDetail);
            } else if ("ZF_QB".equals(s)) {
                calFeeInfo.setPayProduct(PayProductEnum.WALLET_PAY_ZFB);
                calFeeInfo.setPayProductName(PayProductEnum.WALLET_PAY_ZFB.getDisplayName());
                feeRateDetailList.add(feeRateDetail);
            } else if ("ZF_YHSM".equals(s)) {
                if ("ZF_YHSM_YL".equals(calFeeRuleDTO.getPayWay())) {
                    calFeeInfo.setPayProduct(PayProductEnum.UPOP_SCAN_PAY);
                    calFeeInfo.setPayProductName(PayProductEnum.UPOP_SCAN_PAY.getDisplayName());
                    //一部分商户没配阶梯费率，取默认
                    if (null != list.get(0).getSingleIntervalMax()) {
                        feeRateDetail.setDescription("交易金额" + String.format("%.2f", Math.floor(list.get(0).getSingleIntervalMax().doubleValue())) + "及以下");
                    } else {
                        feeRateDetail.setDescription("交易金额" + "1000.00" + "及以下");
                    }
                    feeRateDetailList.add(feeRateDetail);
                    if (list.size() > 1) {
                        FeeRateDetail feeRateDetailLev1 = new FeeRateDetail();
                        StringBuffer sb = new StringBuffer();
                        sb.append(transformPercent(list.get(1).getPercentFee()));
//                        if (null != list.get(1).getSingleMaxFee() && list.get(1).getSingleMaxFee().compareTo(new BigDecimal(0)) > 0) {
//                            sb.append(",封顶" + list.get(1).getSingleMaxFee().toString() + "元");
//                        }
                        if ("API_JJK".equals(calFeeRuleDTO.getVersion())) {
                            feeRateDetailLev1.setDescription("借记卡" + String.format("%.2f", Math.floor(list.get(1).getSingleIntervalMin().doubleValue())) + "以上");
                        } else if ("API_DJK".equals(calFeeRuleDTO.getVersion())) {
                            feeRateDetailLev1.setDescription("贷记卡" + String.format("%.2f", Math.floor(list.get(1).getSingleIntervalMin().doubleValue())) + "以上");
                        }
                        feeRateDetailLev1.setFeeRate(sb.toString());
                        feeRateDetailList.add(feeRateDetailLev1);
                    }
                }
            } else if ("ZF_YJZF".equals(s)) {
                if ("ZF_YJZF_DJK".equals(calFeeRuleDTO.getPayWay())) {
                    calFeeInfo.setPayProduct(PayProductEnum.ONE_KEY_PAY);
                    calFeeInfo.setPayProductName(PayProductEnum.ONE_KEY_PAY.getDisplayName());
                    feeRateDetailList.add(feeRateDetail);
                }
            } else if ("ZF_SJSM".equals(s)) {
                calFeeInfo.setPayProduct(PayProductEnum.MERCHANT_SCAN_PAY);
                calFeeInfo.setPayProductName(PayProductEnum.MERCHANT_SCAN_PAY.getDisplayName());
                feeRateDetailList.add(feeRateDetail);
            }
        }
        calFeeInfo.setFeeInfoDesc(feeRateDetailList);
        return calFeeInfo;
    }

    /**
     * 百分比转换
     *
     * @param bigDecimal
     * @return
     */
    private String transformPercent(BigDecimal bigDecimal) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(bigDecimal.divide(new BigDecimal(100), 4));
    }

    /**
     * 合并银联主扫费率信息
     *
     * @param list
     * @return
     */
    private List<CalFeeInfo> mergeList(List<CalFeeInfo> list) {
        List<FeeRateDetail> tempFeeList = Lists.newArrayList();
        for (CalFeeInfo calFeeInfo : list) {
            if (PayProductEnum.UPOP_SCAN_PAY.equals(calFeeInfo.getPayProduct())) {
                tempFeeList.addAll(calFeeInfo.getFeeInfoDesc());
            }
        }
        //合并银联费率list信息
        HashSet<FeeRateDetail> detailSet = Sets.newLinkedHashSet(tempFeeList);
        List<FeeRateDetail> detailList = Lists.newArrayList(detailSet);
        //合并银联产品
        HashSet<CalFeeInfo> calFeeset = Sets.newLinkedHashSet(list);
        List<CalFeeInfo> calFeeList = Lists.newArrayList(calFeeset);
        for (CalFeeInfo calFeeInfo : calFeeList) {
            if (PayProductEnum.UPOP_SCAN_PAY.equals(calFeeInfo.getPayProduct())) {
                calFeeInfo.setFeeInfoDesc(detailList);
            }
        }
        return calFeeList;
    }
}
