package com.yeepay.g3.core.ymf.entity.billDto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "BASEINFO")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "totalAmount", "totalCount", "paytimeStart", "paytimeEnd"
})
public class BaseInfoParamDTO {

    /**
     * 交易总金额
     */
    private BigDecimal totalAmount;//有问题

    /**
     * 交易总笔数
     */
    private int totalCount;

    /**
     * 支付开始时间
     */
    private String paytimeStart;

    /**
     * 支付结束时间
     */
    private String paytimeEnd;

    public BaseInfoParamDTO() {}

    public BaseInfoParamDTO(int totalCount, BigDecimal totalAmount, String paytimeStart, String paytimeEnd) {
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.paytimeStart = paytimeStart;
        this.paytimeEnd = paytimeEnd;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaytimeStart() {
        return paytimeStart;
    }

    public void setPaytimeStart(String paytimeStart) {
        this.paytimeStart = paytimeStart;
    }

    public String getPaytimeEnd() {
        return paytimeEnd;
    }

    public void setPaytimeEnd(String paytimeEnd) {
        this.paytimeEnd = paytimeEnd;
    }
}
