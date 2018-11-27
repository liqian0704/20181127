package com.yeepay.g3.core.ymf.service.impl;

import com.yeepay.app.httpinvoke.online.dto.CustomerBasicInfoDTO;
import com.yeepay.g3.core.ymf.dao.customer.CustomerDao;
import com.yeepay.g3.core.ymf.dao.customer.CustomerMapper;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.customer.CustomerParam;
import com.yeepay.g3.core.ymf.entity.shop.Shop;
import com.yeepay.g3.core.ymf.ext.AreaExt;
import com.yeepay.g3.core.ymf.ext.G2ServiceExt;
import com.yeepay.g3.core.ymf.ext.MerchantExt;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.shop.ShopService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.merchant_platform.dto.MerchantRespDTO;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.facade.ymf.enumtype.trade.BalanceType;
import com.yeepay.g3.facade.ymf.enumtype.trade.TradeSystemEnum;
import com.yeepay.g3.facade.ymf.exception.YmfTrxException;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private G2ServiceExt g2ServiceExt;
    @Autowired
    private MerchantExt merchantExt;
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaExt areaExt;

    @Override
    public Customer findById(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Customer findByCustomerNumber(String customerNumber) {
        CustomerParam param = new CustomerParam();
        param.createCriteria().andCustomerNumberEqualTo(customerNumber);
        List<Customer> customerList = customerMapper.selectByFilter(param);
        if (null == customerList || customerList.size() == 0) {
            return null;
        }
        return customerList.get(0);
    }

    @Override
    public List<Customer> findByParam(CustomerParam param) {
        return customerMapper.selectByFilter(param);
    }


    /**
     * 添加默认网点
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addDefaultShop(String customerNumber) {
        try {
            CustomerBasicInfoDTO basicInfoDTO = g2ServiceExt.queryCustomerBasicInfo(customerNumber);
            MerchantRespDTO merchant = merchantExt.queryMerchant(customerNumber);
            //{"customerstatus":"true","bindPhone":"13146759966","signedname":"航旅测试商户","createdate":"2016-06-21 19:18:38","customersiteurl":"","accountstatus":"ACTIVATION","uniquename":"chenfuming3@test.com","balance":1777.99,"customerId":40007800,"customernumber":"10040007800","userstatus":"ACTIVATION","grade":"A","shortname":"test@yeepay.com","accountSVAID":40009106,"fullname":"陈福明新版商户"}
            String provinceName = merchant.getProvince();
            String cityName = merchant.getCity();
            if (cityName != null && !cityName.contains("市")) {
                cityName = cityName + "市";
            }
            String provinceCode = areaExt.queryCodeByProinceName(provinceName);
            String cityCode = areaExt.queryCodeByCityName(provinceCode, cityName);
            Shop shop = new Shop();
            shop.setCustomerNumber(customerNumber);
            shop.setLinkPhone(basicInfoDTO.getBindPhone());
            shop.setCreateTime(new Date());
            shop.setCity(cityCode);//客户中心接口返回中文名
            shop.setProvince(provinceCode);//客户中心接口返回中文名
            shop.setCityName(cityName);//客户中心接口返回中文名
            shop.setProvinceName(provinceName);//客户中心接口返回中文名
            shop.setShopName("默认网点");
            shop.setAddress(merchant.getAddress());
            String fullName = basicInfoDTO.getFullname();
            if (StringUtils.isNotBlank(fullName)) {
                if (fullName.length() > 8) {
                    shop.setLinkMan(fullName.substring(0,8));
                } else {
                    shop.setLinkMan(fullName);
                }
            }
            int save = shopService.save(shop);
            log.info("商户编号:{} 生成默认网点成功：{}",customerNumber, JSONUtils.toJsonString(shop));
        } catch (YmfTrxException e) {
            log.error("创建默认网点失败",e);
            throw new RuntimeException("创建默认网点失败.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomerAndShop(OperateEntity<Customer> en) {
        int i = saveCustomer(en);
        //添加默认网点
        addDefaultShop(en.getEntity().getCustomerNumber());
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomer(OperateEntity<Customer> en) {
        en.getEntity().setCreateTime(new Date());
       if(null==en.getEntity().getBalanceProduct()) {
//         T1默认
           en.getEntity().setBalanceProduct(BalanceType.T1);
        }
        return customerMapper.insert(en.getEntity());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomer(Customer customer) {
        customer.setOptimisitc(0L);
        customer.setCreateTime(new Date());
        //添加默认网点
        return customerMapper.insert(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int saveCustomerAndShop(Customer customer) {
        int i = saveCustomer(customer);
        //添加默认网点
        addDefaultShop(customer.getCustomerNumber());
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateCustomer(Customer customer) {
        customer.setUpdateTime(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateCustomer(OperateEntity<Customer> customer) {
        customer.getEntity().setUpdateTime(new Date());
        return customerMapper.updateByPrimaryKeySelective(customer.getEntity());
    }

    @Override
    public List<Customer> getAllCustomerByStatus(Status status) {
        CustomerParam param = new CustomerParam();
        param.createCriteria().andStatusEqualTo(status);
        return customerMapper.selectByFilter(param);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int changeTradeSystemForId(String customerid) {
        Customer customer=customerMapper.selectByPrimaryKey(Long.parseLong(customerid));
//        if(null==customer.getCustomerFlag()){
//
//        }
        if(null==customer.getTradeSystem() || "CASHIER".equals(customer.getTradeSystem().toString())){
            customer.setTradeSystem(TradeSystemEnum.OPR);
        }else if("OPR".equals(customer.getTradeSystem().toString())){
            customer.setTradeSystem(TradeSystemEnum.CASHIER);
        }
        return customerMapper.updateByPrimaryKey(customer);
    }

	@Override
	public List<Customer> getCustomersByGroupId(Long groupId) {
		// TODO Auto-generated method stub
		return customerMapper.getCustomersByGroupId(groupId);
	}

	@Override
	public int unBindGroup(Long id) {
		// TODO Auto-generated method stub
		return customerMapper.unBindGroup(id);
	}

	@Override
	public List<Customer> findCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerByName(customerName);
	}

    @Override
    public List<Customer> findStatusAndBusiType(Status status, String bizCode, int rowCount, int rows) {

        return customerDao.findStatusAndBusiType(status,bizCode,rowCount,rows);
    }

    @Override
    public int findStatusAndBusiTypeCount(Status status, String bizCode) {
        return customerDao.findStatusAndBusiTypeCount(status,bizCode);
    }

    @Override
	public int bindGroup(Long id, Long groupId) {
		return customerMapper.bindGroup(id, groupId);
	}

    /**
     * 查询所有没有网点的商户
     * @return
     */
    @Override
    public List<Customer> findNOShopCustomers() {
        return customerDao.findNOShopCustomers();
    }

    /**
     * 查询所有商户logo的ftp路径
     * @return
     */
    @Override
    public List<Customer> findOldFtpPrefix(String prefix) {
        if (StringUtils.isBlank(prefix)) {
            throw new RuntimeException("prefix can not be null");
        }
        prefix = prefix + "%";
        return customerDao.findOldFtpPrefix(prefix);
    }


    public static void main(String[] args) {
        String cityName = "郑州";
        if (cityName != null && !cityName.contains("市")) {
            cityName = cityName + "市";
        }
        System.out.println(cityName);
    }
}
