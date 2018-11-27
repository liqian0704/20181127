package com.yeepay.g3.core.ymf.biz.profit.impl;

import com.yeepay.g3.core.ymf.biz.profit.PushProfitBiz;
import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.entity.profit.ProfitSummation;
import com.yeepay.g3.core.ymf.service.profit.ProfitSummationService;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.core.ymf.utils.email.MonitorNotify;
import com.yeepay.g3.facade.ymf.agent.bean.BizType;
import com.yeepay.g3.facade.ymf.agent.bean.ShareProfitType;
import com.yeepay.g3.facade.ymf.agent.bean.ShareRequestDTO;
import com.yeepay.g3.facade.ymf.agent.bean.ShareResponseDTO;
import com.yeepay.g3.facade.ymf.agent.service.ShareAddFacade;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.profit.ProfitProductTypeEnum;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description: 推送到代理商
 * @author: xiaobin.liu
 * @date: 18/1/9
 * @time: 下午6:16
 */
@Service
public class PushProfitBizImpl implements PushProfitBiz {
    private static final Logger logger = LoggerFactory.getLogger(PushProfitBizImpl.class);

    @Autowired
    private ProfitSummationService profitSummationService;
    @Autowired(required = false)
    private ShareAddFacade shareAddFacade;

    /**
     * 数据推送到代理商
     *
     * @param month 月份
     */
    @Override
    public void pushToAgent(String month) {
        if (month == null || month.length() != 6) {
            throw new RuntimeException("The param month is invalid!");
        }
        //读取计算结果。
        List<ProfitSummation> profitList = profitSummationService.findByMonth(month, Status.SUCCESS);
        if (profitList == null || profitList.size() < 10) {
            throw new RuntimeException("分润还没计算好。先等等！");
        }
        String agentCode = CfgConstant.MT_AGENT_CODE();
        if (StringUtils.isBlank(agentCode)) {
            throw new RuntimeException("蜜糖代理商编号没取到！");
        }
        Date shareTime = DateUtil.getStrToDate(month, "yyyyMM");

        List<ShareRequestDTO> list = new ArrayList<ShareRequestDTO>() ;
        ShareRequestDTO shareRequestDTO = new ShareRequestDTO();
        shareRequestDTO.setAgentCode(agentCode);
        shareRequestDTO.setShareTime(shareTime);
        shareRequestDTO.setShareProfitType(ShareProfitType.TRX);
        shareRequestDTO.setBizType(BizType.POS);
        //shareRequestDTO.setRemark("蜜堂子公司" + month + "易钱包+来客+收款宝+日结通分润总金额");
        shareRequestDTO.setSettleMode("MONTH");//月结
        list.add(shareRequestDTO);
        for (ProfitSummation summation : profitList) {
            BigDecimal mitangTotalProfitAmt = summation.getMitangTotalProfitAmt();
            if (mitangTotalProfitAmt == null) {
                mitangTotalProfitAmt = Amount.setScale(null);
            }
            ProfitProductTypeEnum productType = summation.getProfitProductType();
            if (productType == null || productType == ProfitProductTypeEnum.ALL ) {
                continue;
            } else {
                if (shareRequestDTO.getShareProfitAmount() == null) {
                    shareRequestDTO.setShareProfitAmount(mitangTotalProfitAmt);
                    continue;
                }
                shareRequestDTO.setShareProfitAmount(shareRequestDTO.getShareProfitAmount().add(mitangTotalProfitAmt));
            }
        }

        //调用代理商接口.重复调用由代理商系统进行判断。
        logger.info("开始推送代理商：" + JSONUtils.toJsonString(list));
        ShareResponseDTO shareResponseDTO = shareAddFacade.addAgentShare(list);
        logger.info("响应参数:" + JSONUtils.toJsonString(shareResponseDTO));
        if (shareResponseDTO != null && shareResponseDTO.isCode()) {
            //成功
            logger.info("推送" + month + "月份，分润成功。" );
            MonitorNotify.notifyEmail("推送，" + month + "月份分润到代理商,成功。\n推送数据：\n" +
                    JSONUtils.toJsonString(list),null);
        } else {
            logger.info("推送" + month + "月份，分润失败。" );
            MonitorNotify.notifyEmail("推送" + month + "月份，分润失败。"
                    + JSONUtils.toJsonString(shareResponseDTO),null);
        }
    }
}
