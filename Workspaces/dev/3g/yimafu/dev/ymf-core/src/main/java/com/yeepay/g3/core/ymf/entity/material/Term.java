package com.yeepay.g3.core.ymf.entity.material;

import com.yeepay.g3.facade.ymf.enumtype.term.StockStatus;
import com.yeepay.g3.facade.ymf.enumtype.term.TermStatus;
import java.io.Serializable;
import java.util.Date;

public class Term implements Serializable {
    private Long id;

    private Date inTime;

    private Date outTime;

    private String snSerial;

    private String termType;

    private String manufact;

    private TermStatus termStatus;

    private StockStatus stockStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getSnSerial() {
        return snSerial;
    }

    public void setSnSerial(String snSerial) {
        this.snSerial = snSerial;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getManufact() {
        return manufact;
    }

    public void setManufact(String manufact) {
        this.manufact = manufact;
    }

    public TermStatus getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(TermStatus termStatus) {
        this.termStatus = termStatus;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Term other = (Term) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInTime() == null ? other.getInTime() == null : this.getInTime().equals(other.getInTime()))
            && (this.getOutTime() == null ? other.getOutTime() == null : this.getOutTime().equals(other.getOutTime()))
            && (this.getSnSerial() == null ? other.getSnSerial() == null : this.getSnSerial().equals(other.getSnSerial()))
            && (this.getTermType() == null ? other.getTermType() == null : this.getTermType().equals(other.getTermType()))
            && (this.getManufact() == null ? other.getManufact() == null : this.getManufact().equals(other.getManufact()))
            && (this.getTermStatus() == null ? other.getTermStatus() == null : this.getTermStatus().equals(other.getTermStatus()))
            && (this.getStockStatus() == null ? other.getStockStatus() == null : this.getStockStatus().equals(other.getStockStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInTime() == null) ? 0 : getInTime().hashCode());
        result = prime * result + ((getOutTime() == null) ? 0 : getOutTime().hashCode());
        result = prime * result + ((getSnSerial() == null) ? 0 : getSnSerial().hashCode());
        result = prime * result + ((getTermType() == null) ? 0 : getTermType().hashCode());
        result = prime * result + ((getManufact() == null) ? 0 : getManufact().hashCode());
        result = prime * result + ((getTermStatus() == null) ? 0 : getTermStatus().hashCode());
        result = prime * result + ((getStockStatus() == null) ? 0 : getStockStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", inTime=").append(inTime);
        sb.append(", outTime=").append(outTime);
        sb.append(", snSerial=").append(snSerial);
        sb.append(", termType=").append(termType);
        sb.append(", manufact=").append(manufact);
        sb.append(", termStatus=").append(termStatus);
        sb.append(", stockStatus=").append(stockStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}