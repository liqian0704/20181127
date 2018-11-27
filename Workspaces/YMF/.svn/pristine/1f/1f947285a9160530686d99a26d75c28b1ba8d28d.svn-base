package com.yeepay.g3.core.ymf.service.impl.refund;

import com.yeepay.g3.core.ymf.dao.refund.RefundOrderDao;
import com.yeepay.g3.core.ymf.dao.refund.RefundOrderDetailMapper;
import com.yeepay.g3.core.ymf.dao.refund.RefundOrderMapper;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrder;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetail;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderDetailParam;
import com.yeepay.g3.core.ymf.entity.refund.RefundOrderParam;
import com.yeepay.g3.core.ymf.service.refund.RefundService;
import com.yeepay.g3.facade.ymf.dto.common.CountResponse;
import com.yeepay.g3.facade.ymf.dto.refund.RefundDTO;
import com.yeepay.g3.facade.ymf.dto.refund.RefundOrderArgs;
import com.yeepay.g3.facade.ymf.enumtype.refund.RefundStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title: 退款订单service
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private RefundOrderDetailMapper refundOrderDetailMapper;
    @Autowired
    private RefundOrderMapper refundOrderMapper;
    @Autowired
    private RefundOrderDao refundOrderDao;

    @Override
    public RefundOrder queryByOrderId(Long orderId) {
        RefundOrderParam param = new RefundOrderParam();
        param.createCriteria().andOrderIdEqualTo(orderId);
        List<RefundOrder> refundOrderList = refundOrderMapper.selectByFilter(param);
        if (null != refundOrderList && refundOrderList.size() > 0) {
            return refundOrderList.get(0);
        }
        return null;
    }

    @Override
    public RefundOrderDetail queryByRefundOrderId(Long refundOrderId) {
        RefundOrderDetailParam param = new RefundOrderDetailParam();
        param.createCriteria().andRefundOrderIdEqualTo(refundOrderId.toString());
        List<RefundOrderDetail> detailList = refundOrderDetailMapper.selectByFilter(param);
        if (null != detailList && detailList.size() > 0) {
            return detailList.get(0);
        }
        return null;
    }

    @Override
    public List<RefundDTO> queryRefundDTOByArgs(RefundOrderArgs args) {
        return refundOrderDao.queryRefundDTOByArgs(args);
    }

    @Override
    public List<RefundOrder> querySupplyRefund(Date from, Date to) {
        RefundOrderParam param = new RefundOrderParam();
        param.createCriteria().andCreateTimeBetween(from, to)
                .andRefundStatusEqualTo(RefundStatus.PROCESS); // 是否要补INIT和TIMEOUT
        return refundOrderMapper.selectByFilter(param);
    }

    @Override
    public List<RefundOrderDetail> querySupplyDetail(Long refundInfoId) {
        RefundOrderDetailParam param = new RefundOrderDetailParam();
        param.createCriteria().andRefundinfoIdEqualTo(refundInfoId);
        return refundOrderDetailMapper.selectByFilter(param);
    }

    @Override
    public List<RefundOrderDetail> querySupplyDetail(Long refundInfoId, String yeepayOrderId) {
        RefundOrderDetailParam param = new RefundOrderDetailParam();
        param.createCriteria().andRefundinfoIdEqualTo(refundInfoId)
                .andYeepayOrderIdEqualTo(yeepayOrderId);
        return refundOrderDetailMapper.selectByFilter(param);
    }

    @Override
    public CountResponse countRefundByArgs(RefundOrderArgs args) {
        return refundOrderDao.countRefundByArgs(args);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateRefundDetail(RefundOrderDetail refundOrderDetail) {
        return refundOrderDetailMapper.updateByPrimaryKey(refundOrderDetail);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateRefundOrder(RefundOrder refundOrder) {
        return refundOrderMapper.updateByPrimaryKey(refundOrder);
    }
}
