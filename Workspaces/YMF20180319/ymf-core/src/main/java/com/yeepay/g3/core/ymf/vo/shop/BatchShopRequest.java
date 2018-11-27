package com.yeepay.g3.core.ymf.vo.shop;

import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.utils.web.BatchExcelBean;
import com.yeepay.g3.core.ymf.utils.web.ExcelField;
import com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus;

import java.util.Date;

/**
 * @Description: 批量网点绑定
 * @Author: xiaobin.liu
 * @Date: 17/6/28
 * @Time: 下午3:05
 */
public class BatchShopRequest extends BatchExcelBean {

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

    /**
     * 装换为shop
     * @return
     */
    public Shop buildToShop() {
        BatchShopRequest request = this;
        Shop shop = new Shop();
        shop.setShopName(request.getShopName());
        shop.setCustomerNumber(request.getCustomerNumber());
        shop.setProvinceName(request.getProvinceName());
        shop.setCityName(request.getCityName());
        shop.setAddress(request.getAddress());
        shop.setLinkMan(request.getLinkMan());
        shop.setLinkPhone(request.getLinkPhone());
        shop.setStatus(ShopStatus.ACTIVE);
        shop.setCreateTime(new Date());
        return shop;
    }
}
