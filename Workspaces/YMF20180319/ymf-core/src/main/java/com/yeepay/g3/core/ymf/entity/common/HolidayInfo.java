package com.yeepay.g3.core.ymf.entity.common;

import com.yeepay.g3.facade.ymf.enumtype.Status;
import java.io.Serializable;
import java.util.Date;

public class HolidayInfo implements Serializable {
    private Long id;

    private Date createTime;

    private Date lastmodifyTime;

    private Date holidayDate;

    private Integer dealTimes;

    private String remark;

    private Status status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastmodifyTime() {
        return lastmodifyTime;
    }

    public void setLastmodifyTime(Date lastmodifyTime) {
        this.lastmodifyTime = lastmodifyTime;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Integer getDealTimes() {
        return dealTimes;
    }

    public void setDealTimes(Integer dealTimes) {
        this.dealTimes = dealTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        HolidayInfo other = (HolidayInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastmodifyTime() == null ? other.getLastmodifyTime() == null : this.getLastmodifyTime().equals(other.getLastmodifyTime()))
            && (this.getHolidayDate() == null ? other.getHolidayDate() == null : this.getHolidayDate().equals(other.getHolidayDate()))
            && (this.getDealTimes() == null ? other.getDealTimes() == null : this.getDealTimes().equals(other.getDealTimes()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastmodifyTime() == null) ? 0 : getLastmodifyTime().hashCode());
        result = prime * result + ((getHolidayDate() == null) ? 0 : getHolidayDate().hashCode());
        result = prime * result + ((getDealTimes() == null) ? 0 : getDealTimes().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastmodifyTime=").append(lastmodifyTime);
        sb.append(", holidayDate=").append(holidayDate);
        sb.append(", dealTimes=").append(dealTimes);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}