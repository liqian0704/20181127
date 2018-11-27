package com.yeepay.g3.facade.ymf.dto.laike;

import com.yeepay.g3.facade.ymf.dto.common.BaseDTO;

/**
 * Created by dongxulu on 17/4/26.
 */
public class BalanceProductResponseDTO extends BaseDTO {

    private static final long serialVersionUID = -6927655074569411461L;
    String returnCode;

    String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
