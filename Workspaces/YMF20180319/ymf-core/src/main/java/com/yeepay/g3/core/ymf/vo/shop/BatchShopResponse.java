package com.yeepay.g3.core.ymf.vo.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeepay.g3.core.ymf.utils.web.BatchExcelBean;
import com.yeepay.g3.core.ymf.utils.web.ExcelField;

/**
 * @Description: 网点批量
 * @Author: xiaobin.liu
 * @Date: 17/6/29
 * @Time: 下午2:19
 */
public class BatchShopResponse extends BatchExcelBean {

    public BatchShopResponse(BatchShopRequest request,String msg) {
        this.index = request.getIndex();
        this.customerNumber = request.getCustomerNumber();
        this.shopName = request.getShopName();
        this.provinceName = request.getProvinceName();
        this.cityName = request.getCityName();
        this.address = request.getAddress();
        this.linkMan = request.getLinkMan();
        this.linkPhone = request.getLinkPhone();
        this.createQr = request.getCreateQr();
        this.qrNumbers = request.getQrNumbers();
        this.createUser = request.getCreateUser();

        this.msg = msg;
    }

    public static BatchShopResponse build(BatchShopRequest request, String msg) {
        return new BatchShopResponse(request,msg);
    }

    /**
     * 行号
     */
    @JsonProperty("行号")
    @ExcelField(name = "行号")
    private int index;

    /**
     * 绑定结果
     */
    @JsonProperty("绑定结果")
    @ExcelField(name = "绑定结果", value = 8)
    private String msg;

    @ExcelField(name = "商户编号",value = 1)
    private String customerNumber;

    @ExcelField(name = "网点名称",value = 2)
    private String shopName;

    @ExcelField(name = "所属省份",value = 3)
    private String provinceName;

    @ExcelField(name = "所属城市",value = 4)
    private String cityName;

    @ExcelField(name = "详细地址",value = 5)
    private String address;

    @ExcelField(name = "联系人姓名",value = 6)
    private String linkMan;

    @ExcelField(name = "电话号码",value = 7)
    private String linkPhone;

    @ExcelField(name = "是否创建二维码",value = 8)
    private String createQr;

    @ExcelField(name = "二维码数量",value = 9)
    private String qrNumbers;

    @ExcelField(name = "是否创建用户",value = 10)
    private String createUser;

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCreateQr() {
        return createQr;
    }

    public void setCreateQr(String createQr) {
        this.createQr = createQr;
    }

    public String getQrNumbers() {
        return qrNumbers;
    }

    public void setQrNumbers(String qrNumbers) {
        this.qrNumbers = qrNumbers;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
