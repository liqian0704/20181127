package com.yeepay.g3.core.ymf.biz.shop.impl;

import com.yeepay.g3.core.ymf.biz.shop.ShopBiz;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.utils.ValidateParamUtil;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopRequest;
import com.yeepay.g3.core.ymf.vo.shop.BatchShopResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Shop shop = request.buildToShop();
            shop.setCity(cityCode);
            shop.setProvince(provinceCode);
            shop.setCreateUser(createUser);
            shopService.save(shop);
        }
        return respList;
    }
}
