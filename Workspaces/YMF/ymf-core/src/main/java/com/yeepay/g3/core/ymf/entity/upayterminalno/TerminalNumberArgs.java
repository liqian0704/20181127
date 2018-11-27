package com.yeepay.g3.core.ymf.entity.upayterminalno;


import com.yeepay.g3.facade.ymf.validator.annotations.LengthValidator;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;

import java.io.Serializable;

public class TerminalNumberArgs implements Serializable {
    @NotEmpty("商户编号")
    @LengthValidator(value="商户编号",length=50)
    private String customerNumber;
    @NotEmpty("商户名称")
    @LengthValidator(value="商户名称",length=30)
    private String terminalNumber;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TerminalNumberArgs)) return false;

        TerminalNumberArgs that = (TerminalNumberArgs) o;

        if (!getCustomerNumber().equals(that.getCustomerNumber())) return false;
        return getTerminalNumber().equals(that.getTerminalNumber());

    }

    @Override
    public int hashCode() {
        int result = getCustomerNumber().hashCode();
        result = 31 * result + getTerminalNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TerminalNumberArgs{" +
                "customerNumber='" + customerNumber + '\'' +
                ", terminalNumber='" + terminalNumber + '\'' +
                '}';
    }
}