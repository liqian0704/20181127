package Accounting_entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类名称: PayCompanyOrderFeeRecordEntity <br>
 * 类描述: <br>
 *
 * @author: hua.jin
 * @since: 2018/1/10 下午8:12
 * @version: 1.0.0
 */
public class PayCompanyOrderFeeRecordEntity {

    /**
     * 支付计费流水号
     */
    private String uniquePayFeeNo;

    /**
     * 交易流水号
     */
    private String uniqueOrderNo;

    /**
     * 版本号
     */
    private Long optimistic;

    /**
     * 支付公司计费模板ID
     */
    private Long payCompanyFeeFormulaId;

    /**
     * 支付产品
     */
    private String payProduct;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 待付支付公司金额
     */
    private BigDecimal toPayCompanyAmount;

    private Date createDate;

    private Date updateDate;

    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo == null ? null : uniqueOrderNo.trim();
    }

    public Long getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Long optimistic) {
        this.optimistic = optimistic;
    }

    public Long getPayCompanyFeeFormulaId() {
        return payCompanyFeeFormulaId;
    }

    public void setPayCompanyFeeFormulaId(Long payCompanyFeeFormulaId) {
        this.payCompanyFeeFormulaId = payCompanyFeeFormulaId;
    }

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getToPayCompanyAmount() {
        return toPayCompanyAmount;
    }

    public void setToPayCompanyAmount(BigDecimal toPayCompanyAmount) {
        this.toPayCompanyAmount = toPayCompanyAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUniquePayFeeNo() {
        return uniquePayFeeNo;
    }

    public void setUniquePayFeeNo(String uniquePayFeeNo) {
        this.uniquePayFeeNo = uniquePayFeeNo;
    }
}