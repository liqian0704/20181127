package com.yeepay.g3.facade.ymf.dto.qrCode;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by dongxulu on 16/12/8.
 */
public class QrCodePurchasesRequestDTO extends BaseDTO {


    private static final long serialVersionUID =-1672457968937418028L;
    /**
     * 代理商商编
     */
    private String agentNumber;
    /**
     * 商户编号
     */
    private String customerNumber;
    /**
     * 采购订单号
     */
    private String requestID;
    /**
     * 采购类型
     */
    private BusinessType businessType;
    /**
     * 采购数量
     */
    private int count;
    /**
     * 销售姓名
     */
    private String salesName;
    /**
     * 销售编号
     */
    private String salesNo;
    /**
     * 采购方标识 默认0
     * 0:代理商,1:销售,2:直销商户
     */
    private int purchaseflag;


    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public int getPurchaseflag() {
        return purchaseflag;
    }

    public void setPurchaseflag(int purchaseflag) {
        this.purchaseflag = purchaseflag;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void checkParam() throws YmfTrxException {
       if(this.count>200){
           throw new YmfTrxException(TrxCode.T1020);
       }
       if(this.purchaseflag==0){
           if(StringUtils.isEmpty(this.agentNumber)){
               throw new YmfTrxException(TrxCode.T1006);
           }
       }else if(this.purchaseflag==1){
           if(StringUtils.isEmpty(this.salesName)){
               throw new YmfTrxException(TrxCode.T1006);
           }
       }else if(this.purchaseflag==2){
           if(StringUtils.isEmpty(this.customerNumber)){
               throw new YmfTrxException(TrxCode.T1006);
           }
       }
       if(StringUtils.isEmpty(this.requestID)){
           throw new YmfTrxException(TrxCode.T1006);
       }
    }


}
