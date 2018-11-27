package com.yeepay.g3.facade.ymf.validator;

import com.yeepay.g3.facade.ymf.dto.common.ResponseMessage;
import com.yeepay.g3.facade.ymf.dto.laike.BaseResp;
import com.yeepay.g3.facade.ymf.dto.laike.RespStatus;

/**
 * Title: 来客接口支持类
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/11/8.
 */
public class LaikeSupport {

    private static final String FAILURE_CODE = "998";

    /**
     * 把易码付的ResponseMessage转换为来客的BaseResp
     * @param errorCode
     * @param msg
     * @return
     */
    public static BaseResp transfer(String errorCode, ResponseMessage msg) {
        BaseResp resp = new BaseResp();
        if (msg.isOk()) {
            resp.setStatus(RespStatus.SUCCESS);
            resp.setContent(msg.getContent());
            resp.setCount(msg.getCount());
        } else {
            resp.setStatus(RespStatus.FAILURE);
            resp.setErrMsg(msg.getMsg());
            resp.setErrMsg(errorCode);
        }
        return resp;
    }

    /**
     *
     * @param msg
     * @return
     */
    public static BaseResp transfer(ResponseMessage msg) {
        return transfer(FAILURE_CODE, msg);
    }
}
