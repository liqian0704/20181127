package com.yeepay.g3.facade.ymf.dto.qrCode;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;
import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by dongxulu on 16/12/9.
 */
public class QrCodeInfoRequestDTO extends BaseDTO{
    private static final long serialVersionUID = 1513907933462753446L;


    /**
     * 二维码ID
     */
    private String qrCodeID;
    /**
     * 商户号
     */
    private String customerNumber;

    public boolean checkParam() throws YmfTrxException {

        if(StringUtils.isEmpty(this.customerNumber)){
            throw new YmfTrxException(TrxCode.T1006);
        }
        if(StringUtils.isEmpty(this.qrCodeID)){
            throw new YmfTrxException(TrxCode.T1006);
        }
        return true;
    }

    public String getQrCodeID() {
        return qrCodeID;
    }

    public void setQrCodeID(String qrCodeID) {
        this.qrCodeID = qrCodeID;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }


}
