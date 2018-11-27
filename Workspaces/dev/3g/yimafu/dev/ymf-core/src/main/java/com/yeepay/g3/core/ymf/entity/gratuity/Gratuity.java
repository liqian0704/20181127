package com.yeepay.g3.core.ymf.entity.gratuity;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import java.io.Serializable;

public class Gratuity implements Serializable {
    private Long id;

    private String customerNumber;

    private String gratuityTemplate;

    private Status status;

    private String gratuityRemark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getGratuityTemplate() {
        return gratuityTemplate;
    }

    public void setGratuityTemplate(String gratuityTemplate) {
        this.gratuityTemplate = gratuityTemplate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getGratuityRemark() {
        return gratuityRemark;
    }

    public void setGratuityRemark(String gratuityRemark) {
        this.gratuityRemark = gratuityRemark;
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
        Gratuity other = (Gratuity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerNumber() == null ? other.getCustomerNumber() == null : this.getCustomerNumber().equals(other.getCustomerNumber()))
            && (this.getGratuityTemplate() == null ? other.getGratuityTemplate() == null : this.getGratuityTemplate().equals(other.getGratuityTemplate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getGratuityRemark() == null ? other.getGratuityRemark() == null : this.getGratuityRemark().equals(other.getGratuityRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerNumber() == null) ? 0 : getCustomerNumber().hashCode());
        result = prime * result + ((getGratuityTemplate() == null) ? 0 : getGratuityTemplate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getGratuityRemark() == null) ? 0 : getGratuityRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", gratuityTemplate=").append(gratuityTemplate);
        sb.append(", status=").append(status);
        sb.append(", gratuityRemark=").append(gratuityRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}