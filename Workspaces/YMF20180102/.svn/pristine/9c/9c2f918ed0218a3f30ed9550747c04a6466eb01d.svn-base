package com.yeepay.g3.ymf.pay.controller;
import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.entity.*;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.*;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
/**
 * 保险代理人管理
 * @author liuxinghua
 *
 */
@Controller
@RequestMapping("/sales")
public class SalesController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(SalesController.class) ;
	
	@Autowired
    protected GroupService groupService;
	@Autowired
    protected SalesService salesService;
	@Autowired
    protected QrCodeService qrCodeService;
	/**
	 * 跳转至注册页
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String register(Model model) {
		List<Group> groupList = groupService.getGroupList();
		model.addAttribute("groupList", groupList);
		
		model.addAttribute("groups", JSON.toJSON(groupList));
		return "sales/reg";
	}
	
	/**
	 * 记录注册信息
	 * @param sales
	 * @return
	 */
	@RequestMapping(value="/save",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String save(Sales sales) {
		Map<String,Object> res = new HashMap<String,Object>();
		try{
			salesService.insert(sales);
			res.put("result", "success");
			res.put("message", sales.getQrId());
			LOG.info("二维码ID{}",sales.getQrId());
		}catch(Exception e){
			LOG.error("error:{}",e.getMessage());
			res.put("result", "error");
			res.put("message", e.getMessage());
		}
		return JSON.toJSONString(res);
	}
	
	
	@RequestMapping(value="/customers/{groupId}",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCustomers(@PathVariable("groupId") Long groupId){
		List customers = customerService.getCustomersByGroupId(groupId);
		return JSON.toJSONString(customers);
	}
	
	/**
	 * 注册完毕后重定向至二维码的信息展示
	 * @param model
	 * @param qrId
	 * @return
	 */
	@RequestMapping(value="/qrcode/{qrId}")
	public String qrcode(Model model,@PathVariable("qrId") String qrId){
		QRCode qrCode = qrCodeService.selectByQrId(qrId);
		model.addAttribute("qrCode", qrCode.getFtpUrl());
		model.addAttribute("qrId", qrCode.getQrId());
		return "sales/qrcode";
	}
	/**
	 * 二维码分享页
	 * @param model
	 * @param qrId
	 * @return
	 */
	@RequestMapping(value="/show/{qrId}")
	public String show(Model model,@PathVariable("qrId") String qrId){
		QRCode qrCode = qrCodeService.selectByQrId(qrId);
		Sales sales = salesService.getSalesByQrId(qrId);
		Customer customer = customerService.findByCustomerNumber(qrCode.getCustomerNumber());
		Group group = groupService.getGroupById(customer.getGroupId());
		LOG.info("{}",JSON.toJSON(customer));
		LOG.info("{}",JSON.toJSON(group));
		model.addAttribute("qrCode", qrCode.getFtpUrl());
		model.addAttribute("sales",sales);
		model.addAttribute("customerName", customer.getCustomerName());
		model.addAttribute("groupName", group!=null?group.getGroupName():"");
		return "sales/show";
	}
}
