package com.yeepay.g3.core.ymf.biz.impl;

import com.yeepay.bridge.pos.AccHisDTO;
import com.yeepay.g3.core.ymf.biz.CustomerSettleInfo2gBiz;
import com.yeepay.g3.core.ymf.biz.SoaBaseBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerSettle;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.CustomerSettleService;
import com.yeepay.g3.core.ymf.utils.common.Amount;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yeepay.bridge.pos.RemoteAccountFacade;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 同步二代的清算信息
 * Created by yp-tc-m-2762 on 16/10/27.
 */
@Service
public class CustomerSettleInfo2gBizImpl extends SoaBaseBiz implements CustomerSettleInfo2gBiz {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RemoteAccountFacade remoteAccountFacade;

    @Autowired
    private CustomerSettleService customerSettleService;

    private final ThreadLocal<DateFormat> local = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static final String HIS_TYPE = "MERCHANT_SETTLE";

    private static final Logger log = LoggerFactory.getLogger(CustomerSettleInfo2gBizImpl.class);

    @Override
    public void customerSettleInfo(Date trxDate) {
        List<Customer> customerList = customerService.getAllCustomerByStatus(Status.ACTIVE);
        if (customerList != null && customerList.size() > 0) {
            log.info("active customer size:" + customerList.size());
            for (Customer customer : customerList) {
                String customerNumber = customer.getCustomerNumber();
                log.info(customerNumber + "settleinfo");
                customerSettleInfo(customerNumber, trxDate);
            }
        }
    }

    @Override
    public void customerSettleInfo(String customerNumber, Date trxDate) {
        Customer customer = customerService.findByCustomerNumber(customerNumber);
        if (null == customer) {
            log.info("商户不存在,编号" + customerNumber);
            throw new IllegalArgumentException("商户不存在,编号" + customerNumber);
        }
        Date from = DateUtil.getMinTime(trxDate);
        Date to = DateUtil.getMaxTime(trxDate);
        // 先查询二代清算信息
        try {
            List<AccHisDTO> result = remoteAccountFacade.getAccHisByType(from, to, HIS_TYPE, customerNumber);
            if (null != result && result.size() > 0) {
                // 先查询商户的清算信息
                CustomerSettle customerSettle = customerSettleService.findByParam(customerNumber, trxDate);
                BigDecimal balance = BigDecimal.ZERO;
                if (null == customerSettle) {
                    customerSettle = new CustomerSettle();
                    customerSettle.setCustomerNumber(customerNumber);
                    customerSettle.setCreateTime(new Date());
                    customerSettle.setTrxDate(trxDate);
                } else {
                    customerSettle.setUpdateTime(new Date());
                    balance = customerSettle.getPostBalance();
                }
                int count = 0;
                BigDecimal realAmount = BigDecimal.ZERO;
                for (AccHisDTO accHisDTO : result) {
                    // TODO 2代接口中这两个字段被注释掉了
//                    if (accHisDTO.isSettleable() && accHisDTO.isSettleFlag()) {
                        balance = new BigDecimal(accHisDTO.getPostBalance()); // 账户余额
                        realAmount = Amount.add(realAmount, new BigDecimal(accHisDTO.getRealAmount()));
                        count++;
//                    }
                }
                if (0 != count) {
                    customerSettle.setPostBalance(balance);
                    customerSettle.setRealAmount(realAmount);
                    customerSettle.setSettleTimes(count);
                    if (null == customerSettle.getId()) {
                        customerSettleService.saveCustomerSettle(customerSettle);
                    } else {
                        customerSettleService.update(customerSettle);
                    }
                    log.info("同步商户清算信息完成,商编" + customerNumber + ",时间" + local.get().format(trxDate));
                }
            }
        } catch (Exception e) {
            log.error("同步商户清算信息失败, 商编" + customerNumber, e);
        }
    }
}
