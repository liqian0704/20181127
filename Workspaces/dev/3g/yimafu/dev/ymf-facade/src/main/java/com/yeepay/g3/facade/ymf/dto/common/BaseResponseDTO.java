package com.yeepay.g3.facade.ymf.dto.common;

import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by dongxulu on 16/12/9.
 */
public class BaseResponseDTO implements Serializable {
    private static final long serialVersionUID = 3124447138920636544L;
    /**
     * 交易响应码
     */
    private TrxCode trxCode;

    private String returnCode;

    private String returnMsg;

    public TrxCode getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(TrxCode trxCode) {
        this.trxCode = trxCode;
    }

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
