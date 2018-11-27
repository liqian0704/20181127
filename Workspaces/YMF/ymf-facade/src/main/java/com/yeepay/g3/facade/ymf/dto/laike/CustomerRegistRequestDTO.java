package com.yeepay.g3.facade.ymf.dto.laike;

/**
 * Created by dongxulu on 16/12/16.
 */
public class CustomerRegistRequestDTO extends CustomerBaseDTO {
    private static final long serialVersionUID = 466928992450158235L;
    /**
     * 业务方名称
     */
    private String  businessName;
    /**
     * 业务方标示
     */
    private String  businessCode;
    /**
     * 单据号
     */
    private String documentNO;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getDocumentNO() {
        return documentNO;
    }

    public void setDocumentNO(String documentNO) {
        this.documentNO = documentNO;
    }
}
