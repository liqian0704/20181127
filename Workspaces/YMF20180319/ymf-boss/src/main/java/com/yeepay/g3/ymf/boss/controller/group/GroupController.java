package com.yeepay.g3.ymf.boss.controller.group;

import com.alibaba.fastjson.JSON;
import com.yeepay.g3.core.ymf.entity.Group;
import com.yeepay.g3.core.ymf.entity.customer.Customer;
import com.yeepay.g3.core.ymf.entity.qrcode.QRCode;
import com.yeepay.g3.core.ymf.service.CustomerService;
import com.yeepay.g3.core.ymf.service.DictionaryService;
import com.yeepay.g3.core.ymf.service.GroupService;
import com.yeepay.g3.core.ymf.service.qrcode.QrCodeService;
import com.yeepay.g3.core.ymf.support.OperateEntity;
import com.yeepay.g3.facade.ymf.enumtype.MaterialStatus;
import com.yeepay.g3.facade.ymf.enumtype.OperateType;
import com.yeepay.g3.facade.ymf.enumtype.Status;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.ymf.boss.controller.ValidateController;
import com.yibao.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.*;
/**
 * 一级商户管理
 * @author liuxinghua
 *
 */
@Controller
@RequestMapping("group")
public class GroupController extends ValidateController{
	private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);
	
    @Autowired
    private GroupService groupService;
    @Autowired
    private CustomerService customerService;
    /**
     * 一级商户查询
     * @param model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model){
    	List<Group> groupList = groupService.getGroupList();
    	String startDate = DateUtils.formatDate(DateUtils.getFirstDayOfMonth(new Date()), "yyyy-MM-dd");
    	String endDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
    	model.addAttribute("startDate", startDate);
    	model.addAttribute("endDate", endDate);
    	model.addAttribute("groupList", groupList);
        return "group/groupQuery";
    }
    /**
     * 解除对应商户与一级商户的绑定关系
     * @param model
     * @param groupId
     * @param customerId
     * @return
     */
    @RequestMapping(value="/unBind",produces = "application/text; charset=utf-8")
	@ResponseBody
    public String unBind(Model model,Long groupId,Long customerId){
    	customerService.unBindGroup(customerId);
    	return "success";
    }
    /**
     * 根据输入的商户名称模糊返回商户信息
     * @param customerName
     * @return
     */
    @RequestMapping(value="/customers",produces = "application/text; charset=utf-8")
	@ResponseBody
    public String customers(String customerName){
    	List<Customer> customerList = customerService.findCustomerByName(customerName);
    	return JSON.toJSONString(customerList);
    }
    
    /**
     * 绑定一级商户
     * @param groupName
     * @param customerIds
     * @return
     */
    @RequestMapping(value="/bindCustomers",produces = "application/text; charset=utf-8")
   	@ResponseBody
    public String bindCustomers(HttpSession session,String groupSearch,Long[] customerIds){
    	
    	return groupService.bindCustomers(super.getUser(session),groupSearch, customerIds);
    }
}
