package com.yeepay.g3.facade.ymf.dto.order;/**
 * Created by jiwei.lv on 16/10/31.
 */

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiwei.lv
 * @create 2016-10-16/10/31
 */
public class OrderProofQueryArgs extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -8321358348851005115L;
    @NotEmpty("商户编号")
    private String customerNumber; // 商户编号
    @NotEmpty("开始时间")
    private String startdate; //
    @NotEmpty("结束时间")
    private String enddate; //

    private List<String> businessType;
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public List<String> getBusinessType() {
        return businessType;
    }

    public void setBusinessType(List<String> businessType) {
        this.businessType = businessType;
    }
}
