package com.yeepay.g3.core.ymf.vo.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeepay.g3.core.ymf.utils.web.BatchExcelBean;
import com.yeepay.g3.core.ymf.utils.web.ExcelField;

/**
 * Title: 批量绑定终端结果
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
public class BatchTermResponse extends BatchExcelBean {

    public BatchTermResponse(BatchTermRequest request, String msg) {
        this.index = request.getIndex();
        this.snSerial = request.getSnSerial();
        this.customerNumber = request.getCustomerNumber();
        this.shopNumber = request.getShopNumber();
        this.msg = msg;
    }

    public static BatchTermResponse build(BatchTermRequest request, String msg) {
        return new BatchTermResponse(request, msg);
    }

    /**
     * 行号
     */
    @JsonProperty("行号")
    @ExcelField(name = "行号")
    private int index;

    /**
     * 终端号
     */
    @JsonProperty("终端号")
    @ExcelField(name = "终端号", value = 1)
    private String snSerial;

    /**
     * 商户编号
     */
    @JsonProperty("商户编号")
    @ExcelField(name = "商户编号", value = 2)
    private String customerNumber;

    /**
     * 绑定结果
     */
    @JsonProperty("绑定结果")
    @ExcelField(name = "绑定结果", value = 3)
    private String msg;

    @JsonProperty("网点编号")
    @ExcelField(name = "网点编号", value = 4)
    private String shopNumber;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSnSerial() {
        return snSerial;
    }

    public void setSnSerial(String snSerial) {
        this.snSerial = snSerial;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }
}
