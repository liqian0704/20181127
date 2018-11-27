package com.yeepay.g3.facade.ymf.dto.order;/**
 * Created by jiwei.lv on 16/10/31.
 */

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/31
 */
public class OrderProofDTO  extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -6517666404124836612L;

    private Long id; // 主键
    private String customerName; // 商户名称
    private String customerNumber; // 商户订单号
    private String customerOrderId; // 交易流水号
    private String outOrderId; // 商品标识
    private BigDecimal trxAmt; // 订单金额
    private Date payTime; // 支付时间
    private String payStatus; // 支付状态
    private String receiverName;//姓名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public BigDecimal getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(BigDecimal trxAmt) {
        this.trxAmt = trxAmt;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
