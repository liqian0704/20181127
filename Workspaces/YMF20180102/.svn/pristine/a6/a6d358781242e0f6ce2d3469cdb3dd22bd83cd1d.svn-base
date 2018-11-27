package com.yeepay.g3.core.ymf.biz.shop.impl;

import com.yeepay.g3.core.ymf.biz.shop.ShopBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.impl.sequence.SequenceGeneratorImpl;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.utils.ValidateParamUtil;
import com.yeepay.g3.core.ymf.utils.qrCodeUtil.QRCodeUtil;
import com.yeepay.g3.core.ymf.utils.sequence.SequenceUtils;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopRequest;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopResponse;
import com.yeepay.g3.facade.ymf.enumtype.trade.BusinessType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/6/29
 * @Time: 上午10:58
 */
@Service
public class ShopBizImpl implements ShopBiz {

    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaExt areaExt;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SequenceGeneratorImpl sequenceGeneratorImpl;
    /**
     * 批量保存excel导入数据
     * @return
     */
    @Override
    public List<BatchShopResponse> batchAddShop(List<BatchShopRequest> list,String createUser) {
        List<BatchShopResponse> respList = new LinkedList<BatchShopResponse>();
        for (BatchShopRequest request : list) {
            String customerNumber = request.getCustomerNumber();
            if (StringUtils.isBlank(customerNumber)) {
                respList.add(BatchShopResponse.build(request, "商户编号不能为空"));
                continue;
            }
            Customer customer = customerService.findByCustomerNumber(customerNumber);
            if (customer == null) {
                respList.add(BatchShopResponse.build(request, "商户编号错误"));
                continue;
            }

            String shopName = request.getShopName();
            if (StringUtils.isBlank(shopName)) {
                respList.add(BatchShopResponse.build(request, "网点名称不能为空"));
                continue;
            }

            String provinceName = request.getProvinceName();
            if (StringUtils.isBlank(provinceName)) {
                respList.add(BatchShopResponse.build(request, "所属省份不能为空"));
                continue;
            }

            String cityName = request.getCityName();
            if (StringUtils.isBlank(cityName)) {
                respList.add(BatchShopResponse.build(request, "所属城市不能为空"));
                continue;
            }

            //省市 传的名称，需要校验并获取编码
            String provinceCode = areaExt.queryCodeByProinceName(provinceName);
            if (StringUtils.isBlank(provinceCode)) {
                respList.add(BatchShopResponse.build(request, "所属省份错误"));
                continue;
            }
            String cityCode = areaExt.queryCodeByCityName(provinceCode, cityName);
            if (StringUtils.isBlank(cityCode)) {
                respList.add(BatchShopResponse.build(request, "所属城市错误"));
                continue;
            }

            String address = request.getAddress();
            if (StringUtils.isBlank(address)) {
                respList.add(BatchShopResponse.build(request, "详细地址不能为空"));
                continue;
            }

            String linkMan = request.getLinkMan();
            if (StringUtils.isBlank(linkMan)) {
                respList.add(BatchShopResponse.build(request, "联系人姓名不能为空"));
                continue;
            }

            String linkPhone = request.getLinkPhone();
            if (StringUtils.isBlank(linkPhone)) {
                respList.add(BatchShopResponse.build(request, "电话号码不能为空"));
                continue;
            }
            //电话号码格式校验
            if (!ValidateParamUtil.isMobile(linkPhone)) {
                respList.add(BatchShopResponse.build(request, "电话号码格式错误"));
                continue;
            }
            //是否创建二维码
            List<QRCode> qrCodeList = null;
            String createQr = request.getCreateQr();
            if ("是".equals(createQr)) {
                String qrNumbers = request.getQrNumbers();
                if (!StringUtils.isNumeric(qrNumbers)) {
                    respList.add(BatchShopResponse.build(request, "二维码数量必须为正整数"));
                    continue;
                }
                int qrNumb = Integer.valueOf(qrNumbers);
                qrCodeList = batchBuildQrcode(qrNumb, customer);
            }
            //是否创建用户

            Shop shop = request.buildToShop();
            shop.setCity(cityCode);
            shop.setProvince(provinceCode);
            shop.setCreateUser(createUser);
            shopService.batchSaveShopAndQrcode(shop,qrCodeList);
        }
        return respList;
    }

    /**
     * 为指定商户与网点批量生成指定数量二维码。
     * @param qrNo      要生成的二维码数量。
     * @param customer        商户编号
     */
    @Override
    public List<QRCode> batchBuildQrcode(int qrNo, Customer customer) {
        if (qrNo < 1) {
            return null;
        }
        if (qrNo > 100) {
            throw new RuntimeException("最多只能一次生成100张二维码。");
        }
        BusinessType businessType = null;
        if("NORMAL".equals(customer.getAppType())){//生成二维码的类型
            businessType = BusinessType.STANDARD;
        } else {
            businessType = BusinessType.ORDER_PAY;
        }
        String customerLogo = customer.getCustomerLogo();
        List<QRCode> qrCodeList = new ArrayList<QRCode>();
        for (int i = 0; i < qrNo; i++) {
            QRCode code = new QRCode();
            code.setCustomerNumber(customer.getCustomerNumber());
            code.setBusinessType(businessType);
            //code.setShopNumber(shopNumber);网点保存前，并没有网点编号
            long qrcodeIdSequence = sequenceGeneratorImpl.generateSequence();//生成12位数
            String qrcodeId = SequenceUtils.createOrderSequence(qrcodeIdSequence);
            code.setQrId(Long.toString(Long.parseLong(qrcodeId),36).toUpperCase());
            code = QRCodeUtil.createQrCode(code,customerLogo);
            qrCodeList.add(code);
        }
        return qrCodeList;
    }
}
