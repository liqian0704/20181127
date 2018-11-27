package com.yeepay.g3.core.ymf.service.impl;

import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.dao.SalesDao;
import com.yeepay.g3.core.ymf.entity.Sales;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.SalesService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("salesService")
public class SalesServiceImpl implements SalesService{
	private static final Logger LOG = LoggerFactory.getLogger(SalesServiceImpl.class);
    @Autowired
    private SalesDao  salesDao;
    
    @Autowired
    private CustomerService  customerService;
    
    @Autowired
    private QrCodeService qrCodeService;
    
	@Override
	public List<Sales> getSalesPage() {
		return salesDao.getSalesPage();
	}
	
	@Override
	public Sales getSalesByMobile(String mobile){
		List<Sales> list = salesDao.getSalesByMobile(mobile);
		if(list!=null&&list.size()>0) return list.get(0);
		return null;
	}
	
//	@Transactional(rollbackFor = Exception.class)
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class, timeout = 30)
	@Override
	public void insert(Sales sales) {
		
		if(sales.getGroupId()==null||sales.getGroupId()==0) throw new RuntimeException("请选择所属商户");
		if(sales.getCustomerId()==null||sales.getCustomerId()==0) throw new RuntimeException("请选择所属分支机构");
		if(StringUtils.isEmpty(sales.getUserName())) throw new RuntimeException("姓名不能为空");
		if(StringUtils.isEmpty(sales.getMobile())) throw new RuntimeException("手机号码不能为空");
		if(StringUtils.isEmpty(sales.getBankNo())) throw new RuntimeException("银行账号不能为空");
		if(StringUtils.isEmpty(sales.getBankName())) throw new RuntimeException("银行名称不能为空");
		
		if(getSalesByMobile(sales.getMobile())!=null) throw new RuntimeException("手机号已被注册，请重新输入");
		
		sales.setCustomerNumber(null);
		if(sales.getCustomerId()!=null){
			Customer customer = customerService.findById(sales.getCustomerId());
			if(customer!=null) sales.setCustomerNumber(customer.getCustomerNumber());
		}
		
		if(StringUtils.isEmpty(sales.getCustomerNumber())) throw new RuntimeException("获取分支机构信息异常");
		
		QRCode qrCode = new QRCode();
        qrCode.setCustomerNumber(sales.getCustomerNumber());
//        OperateEntity<QRCode> en = new OperateEntity<QRCode>(sales.getMobile(), qrCode, OperateType.ADD, sales.getCustomerNumber());
//        qrCodeService.saveQrCode(en);
		qrCodeService.saveQrCode(qrCode);
        
        LOG.info("qrcode:{}",JSON.toJSONString(qrCode));
        sales.setQrId(qrCode.getQrId());
        
        LOG.info("sales:{}",JSON.toJSONString(sales));
        
		salesDao.insert(sales);
		
		LOG.info("sales id:{}",sales.getId());
	}
	@Override
	public void editBankInfo(String bankNo, String bankName, Long id) {
		salesDao.updateBankInfo(bankNo, bankName, id);
	}

	@Override
	public Sales getSalesById(Long id) {
		// TODO Auto-generated method stub
		return salesDao.getSalesById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void frozenSales(Long id) {
		Sales sales = salesDao.getSalesById(id);
		salesDao.frozenSales(id);
		qrCodeService.changeStatusByQrId(MaterialStatus.DELETE.name(), sales.getQrId());
	}

	@Override
	public Sales getSalesByQrId(String qrId) {
		// TODO Auto-generated method stub
		return salesDao.getSalesByQrId(qrId);
	}
}
