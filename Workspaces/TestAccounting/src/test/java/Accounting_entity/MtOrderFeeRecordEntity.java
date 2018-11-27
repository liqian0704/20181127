package Accounting_entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类名称: MtOrderFeeRecordEntity <br>
 * 类描述: MT处理费订单<br>
 *
 * @author: hua.jin
 * @since: 2018/1/8 下午4:18
 * @version: 1.0.0
 */
public class MtOrderFeeRecordEntity {

    /**
     * 交易流水号
     */
    private String uniqueMtFeeNo;

    /**
     * 交易流水号
     */
    private String uniqueOrderNo;

    /**
     * 版本号
     */
    private Long optimistic;

    /**
     * MT计费模板ID
     */
    private Long mtFeeFormulaId;

    /**
     * 支付产品
     */
    private String payProduct;

    /**
     * 取现金额
     */
    private BigDecimal orderAmount;

    /**
     * 待付蜜糖金额
     */
    private BigDecimal toMtAmount;

    private Date createDate;

    private Date updateDate;

    public String getUniqueMtFeeNo() {
        return uniqueMtFeeNo;
    }

    public void setUniqueMtFeeNo(String uniqueMtFeeNo) {
        this.uniqueMtFeeNo = uniqueMtFeeNo;
    }

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

    public Long getMtFeeFormulaId() {
        return mtFeeFormulaId;
    }

    public void setMtFeeFormulaId(Long mtFeeFormulaId) {
        this.mtFeeFormulaId = mtFeeFormulaId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getToMtAmount() {
        return toMtAmount;
    }

    public void setToMtAmount(BigDecimal toMtAmount) {
        this.toMtAmount = toMtAmount;
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

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
    }
}